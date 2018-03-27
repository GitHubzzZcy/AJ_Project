package com.henghao.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class TT {

	public static void main(String[] args) throws ParseException {
		Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.HOUR_OF_DAY, 0); //凌晨0点开始执行  
        calendar.set(Calendar.MINUTE, 0);  
        calendar.set(Calendar.SECOND, 1);
        Date date=calendar.getTime(); //第一次执行定时任务的时间  
        if (date.before(new Date())) {  
            date = TT.addDay(date, 1);  
        } 
        System.out.println(date);
	}
	public static Date addDay(Date date, int num) {  
        Calendar startDT = Calendar.getInstance();  
        startDT.setTime(date);  
        startDT.add(Calendar.DAY_OF_MONTH, num);  
        return startDT.getTime();  
    }  
}
