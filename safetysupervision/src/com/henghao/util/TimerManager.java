package com.henghao.util;
import java.util.Calendar;  
import java.util.Date;  
import java.util.Timer;

 /**
  * 线程工具类  
  * @Description: 线程定时调用，设置为每天凌晨0点调用，缓存时间为12小时，缓存对象为Result类的data
  * @author zcy  
  * @date 2017年11月15日 下午2:49:14
  * @version 1.0
  */
  
public class TimerManager {  
  
	public static void main(String[] args) {
		new TimerManager();
	}
    //时间间隔(一天调用一次)  
    private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;  
    public TimerManager() {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.HOUR_OF_DAY, 0); //凌晨0点开始执行  
        calendar.set(Calendar.MINUTE, 0);  
        calendar.set(Calendar.SECOND, 1);  
        Date date=calendar.getTime(); //第一次执行定时任务的时间  
        //如果第一次执行定时任务的时间 小于当前的时间  
        //此时要在 第一次执行定时任务的时间加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。  
//        if (date.before(new Date())) {  
//            date = this.addDay(date, 1);  
//        }  
        Timer timer = new Timer();  
        Task task = new Task();  
        //安排指定的任务在指定的时间开始进行重复的固定延迟执行。  
        timer.schedule(task,date,PERIOD_DAY);    
    }  
    // 增加或减少天数  
    public Date addDay(Date date, int num) {  
        Calendar startDT = Calendar.getInstance();  
        startDT.setTime(date);  
        startDT.add(Calendar.DAY_OF_MONTH, num);  
        return startDT.getTime();  
    }  
  
} 