/**
 *<p>文件名:Data.java</p>
 * @author 16415
 *创建时间：2019年7月11日 上午12:26:17
 */
package indi.koro.Countdown.Data;

import java.util.HashMap;

import javax.swing.JPanel;

import indi.koro.Countdown.main.MainSystem;
import indi.koro.Countdown.mod.Mod;
import indi.koro.Countdown.swing.MainWindow;

/**
 *<p>项目名称：Countdown of College Entrance Examination</p>
 *<p>类名称:Data</p>
 * 作者： 16415
 * 版本：1.0
 *创建时间：2019年7月11日上午12:26:17
 *类描述:
 */
public class Data {
    static public HashMap<String, JPanel>sceneMap=new HashMap<>();
    static public HashMap<String, Mod>modsMap=new HashMap<>();
    static public MainWindow mainWindow;
    static public MainSystem mainSystem;
    static public int w=420,h=100;
    static public String timeString=new String();
    static public String kTimeString=new String("");
}
