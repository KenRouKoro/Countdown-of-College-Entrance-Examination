/**
 *<p>文件名:System.java</p>
 * @author 16415
 *创建时间：2019年7月11日 上午12:41:37
 */
package indi.koro.Countdown.main;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import indi.koro.Countdown.Data.Data;
import indi.koro.Countdown.mod.Mod;
import indi.koro.Countdown.swing.MainPanel;
import indi.koro.Countdown.swing.MainWindow;
import indi.koro.Countdown.swing.SettingPanel;


/**
 *<p>项目名称：Countdown of College Entrance Examination</p>
 *<p>类名称:System</p>
 * 作者： 16415
 * 版本：1.0
 *创建时间：2019年7月11日上午12:41:37
 *类描述:
 */
public class MainSystem {
    public Thread mainThread,swingThread;
    public Timer timer = new Timer();  

    
    public void load() {
	first();
	mainThread=new Thread(new Runnable() {
	    
	    @Override
	    public void run() {
		// TODO 自动生成的方法存根
		if (swingThread.isAlive()) {
		    try {
			swingThread.join();
		    } catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		    }
		}
		
	    }
	});
	swingThread=new Thread(new Runnable() {
	    
	    @Override
	    public void run() {
		// TODO 自动生成的方法存根
		Data.sceneMap.put("mainPanel", new MainPanel());
		Data.sceneMap.put("settingPanel", new SettingPanel());
		Data.mainWindow=new MainWindow();
		Data.mainWindow.load();
		MainPanel mainPanel=(MainPanel)Data.sceneMap.get("mainPanel");
		mainPanel.load();
		SettingPanel settingPanel=(SettingPanel)Data.sceneMap.get("settingPanel");
		settingPanel.load();
	    }
	});
	swingThread.start();
	try {
	    swingThread.join();
	} catch (InterruptedException e) {
	    // TODO 自动生成的 catch 块
	    e.printStackTrace();
	}
	mainThread.start();
	
	for (String key : Data.modsMap.keySet()) {
	    Data.modsMap.get(key).load();
	}
    }
    protected void first() {
	Data.mainSystem=this;
    }
    public void start() {
	    timer.schedule(new TimerTask() {  
		    @Override  
		    public void run() {  
		       MainPanel mainPanel=(MainPanel)Data.sceneMap.get("mainPanel"); 
		       mainPanel.reTime();
		    }  
		}, 100,100); 
	    Data.mainWindow.setVisible(true);
	for (String key : Data.modsMap.keySet()) {
	    Data.modsMap.get(key).start();
	}
    }
    protected void modload() {
        String path = "/mods";
        File file=new File(path);
        File files[]=file.listFiles();
        File nowFile;
        int mods=0;
        int index=0;
        int maxIndex=files.length;
         Set<Class<?>> classes = new LinkedHashSet<Class<?>>();//所有的Class对象  
         Map<Class<?>, Annotation[]> classAnnotationMap = new HashMap<Class<?>, Annotation[]>();//每个Class对象上的注释对象  
         Map<Class<?>, Map<Method, Annotation[]>> classMethodAnnoMap = new HashMap<Class<?>, Map<Method,Annotation[]>>();//每个Class对象中每个方法上的注释对象  
         while(index<maxIndex) {
             
             nowFile=files[index];
            if (nowFile.isDirectory()) {
        	index++;
        	continue;
            }
             
         try {  
          JarFile jarFile = new JarFile(nowFile);  
          URL url = nowFile.toURL();
          ClassLoader loader = new URLClassLoader(new URL[]{url});//自己定义的classLoader类，把外部路径也加到load路径里，使系统去该路经load对象  
          Enumeration<JarEntry> es = jarFile.entries();  
          while (es.hasMoreElements()) {  
           JarEntry jarEntry = (JarEntry) es.nextElement();  
           String name = jarEntry.getName();  
           if(name != null && name.endsWith(".class")){//只解析了.class文件，没有解析里面的jar包  
            //默认去系统已经定义的路径查找对象，针对外部jar包不能用  
            //Class<?> c = Thread.currentThread().getContextClassLoader().loadClass(name.replace("/", ".").substring(0,name.length() - 6));  
            Class<?> c = loader.loadClass(name.replace("/", ".").substring(0,name.length() - 6));//自己定义的loader路径可以找到  
            System.out.println(c);  
            classes.add(c);  
            Annotation[] classAnnos = c.getDeclaredAnnotations();  
            classAnnotationMap.put(c, classAnnos);  
            Method[] classMethods = c.getDeclaredMethods();  
            Map<Method, Annotation[]> methodAnnoMap = new HashMap<Method, Annotation[]>();  
            for(int i = 0;i<classMethods.length;i++){  
             Annotation[] a = classMethods[i].getDeclaredAnnotations();  
             methodAnnoMap.put(classMethods[i], a);  
            }  
            classMethodAnnoMap.put(c, methodAnnoMap);  
           }  
          }  
          System.out.println(classes.size());     
          Class<?> clazz = Class.forName("main.Main",true,loader);
        	Object ojb = clazz.newInstance();
        	Mod mod = (Mod) ojb;
        	System.out.println("已加载mod：" + mod.name());
        	mods++;
        	Data.modsMap.put(mod.name(), mod);
         } catch (IOException e) {  
          e.printStackTrace();  
         } catch (ClassNotFoundException e) {  
          e.printStackTrace();  
         } catch (InstantiationException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }finally {
            index++;
        }}
         System.out.println("已加载mod数："+mods);
    }
}
