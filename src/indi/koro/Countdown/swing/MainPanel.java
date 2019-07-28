/**
 *<p>文件名:MainPanel.java</p>
 * @author 16415
 *创建时间：2019年7月11日 上午12:33:44
 */
package indi.koro.Countdown.swing;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import indi.koro.Countdown.Data.Data;

/**
 *<p>项目名称：Countdown of College Entrance Examination</p>
 *<p>类名称:MainPanel</p>
 * 作者： 16415
 * 版本：1.0
 *创建时间：2019年7月11日上午12:33:44
 *类描述:
 */
public class MainPanel extends JPanel{
    Font font =new Font("宋体", Font.BOLD, 40);
    Calendar cal = Calendar.getInstance();
    static Point origin = new Point();
    Color drawColor=Color.black;
    boolean isTuo=false;
    public void scenef() {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot;
	try {
	    robot = new Robot();
	    BufferedImage image = robot.createScreenCapture(screenRectangle);
	    BufferedImage image2=new BufferedImage(420, 100, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D graphics2d=image2.createGraphics();
	    graphics2d.drawImage(image, null, -Data.mainWindow.getX(), -Data.mainWindow.getY());
	    Color color=new Color(image2.getRGB(100, 10));
	    Color color1=new Color(image2.getRGB(315, 10));
	    Color color2=new Color(image2.getRGB(100, 99));
	    Color color3=new Color(image2.getRGB(315, 99));
	    Color color4=new Color(image2.getRGB(210, 50));
	    int g=(color.getRed()*299+color.getBlue()*114+color.getGreen()*587+500)/1000;
	    int g1=(color1.getRed()*299+color1.getBlue()*114+color1.getGreen()*587+500)/1000;
	    int g2=(color2.getRed()*299+color2.getBlue()*114+color2.getGreen()*587+500)/1000;
	    int g3=(color3.getRed()*299+color3.getBlue()*114+color3.getGreen()*587+500)/1000;
	    int g4=(color4.getRed()*299+color4.getBlue()*114+color4.getGreen()*587+500)/1000;
	    int allg=(g+g1+g2+g3+g4)/5;
	    if(allg<127)drawColor=Color.white;else drawColor=Color.black;
	} catch (AWTException e) {
	    // TODO 自动生成的 catch 块
	    e.printStackTrace();
	}
        
    }
    public void load() {
	Data.mainWindow.add(this);
	this.setOpaque(false);
	this.setBackground(Color.black);
	this.setBounds(0,0,Data.w,Data.h);
	this.setLayout(null);
	JPanel exit=new JPanel();
	exit.setBounds(0, 0, 20, 20);
	exit.setBackground(new Color(221, 0, 0, 100));
	exit.addMouseListener(new MouseListener() {
	    
	    @Override
	    public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	    }
	    
	    @Override
	    public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	    }
	    
	    @Override
	    public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	    }
	    
	    @Override
	    public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	    }
	    
	    @Override
	    public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		System.exit(0);
	    }
	});
	JPanel move=new JPanel();
	move.setBounds(20, 0, 20, 20);
	move.setBackground(new Color(0, 85, 255, 100));
	move.addMouseListener(new MouseListener() {
	    // 按下（mousePressed 不是点击，而是鼠标被按下没有抬起）
	    public void mousePressed(MouseEvent e) {
	        // 当鼠标按下的时候获得窗口当前的位置
	        origin.x = e.getX();
	        origin.y = e.getY();
	        isTuo = true;
	    }
	    @Override
	    public void mouseReleased(MouseEvent e) {
	        isTuo = false;
	    }
	    @Override
	    public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	    }
	    @Override
	    public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	    }
	    @Override
	    public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	    }
	});
	move.addMouseMotionListener(new MouseMotionListener() {
	    // 拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
	    public void mouseDragged(MouseEvent e) {
	        if (!isTuo)
	            return;
	        // 当鼠标拖动时获取窗口当前位置
	        java.awt.Point p = Data.mainWindow.getLocation();
	        // 设置窗口的位置
	        // 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
	        Data.mainWindow.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
	    }

	    @Override
	    public void mouseMoved(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	    }
	});
	this.add(move);
	this.add(exit);
    }
    @Override
    public void paint(Graphics g) {
        // TODO 自动生成的方法存根
        super.paint(g);
        Graphics2D g2d=(Graphics2D)g;
        g2d.setColor(drawColor);
        RenderingHints rh=new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	g2d.setRenderingHints(rh);
	g2d.setFont(font);
	g2d.drawString("距离高考剩余", 70, 40);
	g2d.drawString(Data.timeString, 0, 80);
    }
    public void reTime() {
	//0607
	Data.mainWindow.setAlwaysOnTop(true);
	scenef();
	cal=Calendar.getInstance(TimeZone.getDefault());
	int count=0;
	Calendar c = Calendar.getInstance();
	while(!(c.get(Calendar.YEAR)==2020&&c.get(Calendar.MONTH)==5&&c.get(Calendar.DAY_OF_MONTH)==7)) {
	//终止时间是2020.6.7日，每次天数递增1
	c.add(Calendar.DAY_OF_MONTH,1);
	count++;
	}
	String year= String.format("%03d",(count-1));
	String hour= String.format("%02d", 24-cal.get(Calendar.HOUR_OF_DAY)-1);
	String min=String.format("%02d", 60-cal.get(Calendar.MINUTE)-1);
	String scr=String.format("%02d", 60-cal.get(Calendar.SECOND));
	Data.timeString=year+"天"+hour+"小时"+min +"分"+scr+"秒";
	this.repaint();
    }
}
