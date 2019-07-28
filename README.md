# Countdown of College Entrance Examination 高考倒计时程序
**这个程序是给吾班上的seewo一体机用的，实现在屏幕顶层显示至2020年高考剩余时间（以秒计时）。**
****
**支持mod开发，只需引用项目jar包后在main.Main类中继承即可。**
```java
package main;
import indi.koro.Countdown.mod.Mod;
public void Main() extends mod {
	@Override
    public void load(){
    //do sth
    }
    @Override
    public void start(){
    //do sth
    }
    @Override
    public String name(){
    return "mod Name"
    }
}
```