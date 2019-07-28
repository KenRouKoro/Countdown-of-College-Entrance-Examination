/**
 *<p>文件名:MainWindow.java</p>
 * @author 16415
 *创建时间：2019年7月11日 上午12:25:39
 */
package indi.koro.Countdown.swing;

import javax.swing.JWindow;

import com.sun.awt.AWTUtilities;

import indi.koro.Countdown.Data.Data;

/**
 *<p>项目名称：Countdown of College Entrance Examination</p>
 *<p>类名称:MainWindow</p>
 * 作者： 16415
 * 版本：1.0
 *创建时间：2019年7月11日上午12:25:39
 *类描述:
 */
public class MainWindow extends JWindow {
    public void load() {
	AWTUtilities.setWindowOpaque(this, false);
	this.setSize(Data.w, Data.h);
	this.setLocation(10, 10);
	this.setAlwaysOnTop(true);
    }
}
