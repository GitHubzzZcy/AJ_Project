package com.henghao.news.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


/**
 * 日期工具类
 * @author 
 */
public class DateUtils {

	/**
	 * <b>获取当前时间</b><br>
	 * y 年 M 月 d 日 H 24小时制 h 12小时制 m 分 s 秒
	 * @param format 日期格式
	 * @return String
	 */
	public static String getCurrentDate(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}
	public static Date getCurrentDateT(String format) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String dateStr=  sdf.format(new Date());
		return sdf.parse(dateStr);
	}
	public static Date getCurrent(String format) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateStr=  sdf.parse(format);
		return dateStr;
	}
	public static String getCurrents(Date date) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = sdf.format(date);
		return format;
	}
	public static Date getymddate(Date date) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String yymd = sdf.format(date);
		String dates = yymd+" 8:00:00";
		Date date2 = parseDateFromString(dates);
		return date2;
	}
	/**
	 * 获取制定日期的格式化字符串
	 * @param date Date 日期
	 * @param format String 格式
	 * @return String
	 */
	public static String getFormatedDate(Date date,String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * 判断哪个日期在前 日过日期一在日期二之前，返回true,否则返回false
	 * @param date1 日期一
	 * @param date2 日期二
	 * @return boolean
	 */
	public static boolean isBefore(Date date1,Date date2){
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		
		if(c1.before(c2))return true;
		
		return false;
	}
	
	/**
	 * 将字符串转换成日期
	 * @param date String 日期字符串
	 * @return Date
	 * @throws ParseException
	 */
	public static Date parseDateFromString(String date) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(date);
	}
	public static Date parseDateFromString(String date,String format) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.parse(date);
	}
	
	/**
	 * 获取指定月份的最后一天，
	 * @param date Date类型
	 * @param date1 String类型 yyyy-MM-dd mm:HH:ss 或 yyyy-MM-dd
	 * @return Date
	 * @throws ParseException
	 */
	public static Date lastDayOfMonth(Date date,String date1) throws ParseException{
		Date _date=null;
		if(null!=date)_date=date;
		if(null==date&&null!=date1)_date=parseDateFromString(date1);
		
		Calendar cal=Calendar.getInstance();
		cal.setTime(_date);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}
	public static Date lastDayOfMonthGrade(Date date,String date1) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		Date _date=null;
		if(null!=date)_date=date;
		if(null==date&&null!=date1) {
			_date=sdf.parse(date1);
		}
		
		Calendar cal=Calendar.getInstance();
		cal.setTime(_date);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}
	/**
	 * 是否是闰年
	 * @param year 年份
	 * @return boolean
	 */
	public static boolean isLeapYear(int year) {
	       GregorianCalendar calendar = new GregorianCalendar();
	       return calendar.isLeapYear(year);
	}
	
	/**
     * 获得指定日期的前一天
     * @param specifiedDay 指定的日期
     * @return String
     * @throws Exception
     */
    public static String getSpecifiedDayBefore(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c
                .getTime());
        return dayBefore;
    }
    
    /**
     * 获得指定日期的后一天
     * 
     * @param specifiedDay 指定的日期
     * @return String
     */
    public static String getSpecifiedDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd")
                .format(c.getTime());
        return dayAfter;
    }
    /**
     * 获得指定日期的后几年
     * 
     * @param specifiedDay 指定的日期
     * @return String
     */
    public static String getSpecifiedYearAfter(Date date,int year) {
        Calendar c = Calendar.getInstance();
//        Date date = null;
//        try {
//            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        c.setTime(date);
        int nowYear = c.get(Calendar.YEAR);
        c.set(Calendar.YEAR, nowYear + year);

        String yearAfter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(c.getTime());
        return yearAfter;
    }
    
    /**
     * 获取一天开始时间 如 2014-12-12 00:00:00
     * @return
     */
    public static Date getDayStart(){
    	Calendar calendar = Calendar.getInstance();
	    calendar.setTime(new Date());
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    return calendar.getTime();
    }
    
    /**
     * 获取一天结束时间 如 2014-12-12 23:59:59
     * @return
     */
    public static Date getDayEnd(){
    	Calendar calendar = Calendar.getInstance();
	    calendar.setTime(new Date());
	    calendar.set(Calendar.HOUR_OF_DAY, 23);
	    calendar.set(Calendar.MINUTE, 59);
	    calendar.set(Calendar.SECOND, 59);
	    return calendar.getTime();
    }
    
    
    /**
     * 时间分段
     * 比如：2014-12-12 10:00:00 ～ 2014-12-12 14:00:00 
     * 分成两段就是 2014-12-12 10：00：00 ～ 2014-12-12 12：00：00 和2014-12-12 12：00：00 ～ 2014-12-12 14：00：00
     * @param start 起始日期
     * @param end 结束日期
     * @param pieces 分成几段
     */
    public static Date[] getDatePiece(Date start,Date end,int pieces){
    	
    	Long sl=start.getTime();
		Long el=end.getTime();
		
		Long diff=el-sl;
		
		Long segment=diff/pieces; 
		
		Date[] dateArray=new Date[pieces+1];
		
		for(int i=1;i<=pieces+1;i++){
			dateArray[i-1]=new Date(sl+(i-1)*segment);
		}
		
		//校正最后结束日期的误差，可能会出现偏差，比如14:00:00 ,会变成13:59:59之类的
		dateArray[pieces]=end;
		
		return dateArray;
    }
    /**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    } 
    /**
     * 计算两个日期之间相差的毫秒时间
     * @param start 较大时间
     * @param end 较小时间
     * @return
     */
    public static long timesBetween(Date start,Date end){
    	return end.getTime() -start.getTime();
    }
    
    /**
	 * 该方法用来计算两个时间之间的差值,以天计算
	 * @param start 开始时间
	 * @param end 截止时间
	 * @return 相差的天数
	 */
	public static int getSubDays(Date start,Date end){
		long subTime = end.getTime()-start.getTime();
		int days = (int)(subTime/(60*60*1000*24L));
		return days;
	}
	/**
	 * 返回此刻时间到当天24点的时间的差值，以毫秒值计算
	 */
	public static long differenceTo24() throws ParseException{
		Date date = new Date();
		String format1 = "HH:mm:ss";
		String format2 = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format2);
		String dateStr = sdf.format(date);
		String dateStr24 = dateStr+" 24:00:00";
		sdf = new SimpleDateFormat(format2+" "+format1);
		Date date24 = sdf.parse(dateStr24);
		long time = date24.getTime()-date.getTime();
		return time;
	}
	
	/**
	  * 得到本周周一
	  * 
	  * @return yyyy-MM-dd
	  */
	 public static Date getMondayOfThisWeek() {
	  Calendar c = Calendar.getInstance();
	  int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
	  if (day_of_week == 0)
	   day_of_week = 7;
	  c.add(Calendar.DATE, -day_of_week + 1);
	  return c.getTime();
	 }
	/**
	  * 得到本周周日
	  * 
	  * @return yyyy-MM-dd
	  */
	 public static Date getSundayOfThisWeek() {
	  Calendar c = Calendar.getInstance();
	  int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
	  if (day_of_week == 0)
	   day_of_week = 7;
	  c.add(Calendar.DATE, -day_of_week + 7);
	  return c.getTime();
	 }
	 /**
	  * 获取当前日期的前一天
	  * @param args
	  * @throws ParseException
	  */
	 public static Date getNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return date;
	 }
	 /**
	  * 获取当前日期的后一天
	  * @param args
	  * @throws ParseException
	  */
	 public static Date getRearDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		date = calendar.getTime();
		return date;
	 }
	 
	 
	 
	public static void main(String[] args) throws ParseException {
//		String year = getSpecifiedYearAfter(new Date(),3);
//		Date invalidTime = DateUtils.parseDateFromString(DateUtils.getSpecifiedYearAfter(new Date(), 1));
//		System.out.println(invalidTime);
		System.out.println(getNextDay(new Date()));
	}
	
	
	/** 
     * 返回当月最后一天的日期 
     */  
    public static Date getLastDayOfMonth(Date date) {  
        Calendar calendar = convert(date);  
        calendar.set(Calendar.DATE, calendar.getMaximum(Calendar.DATE));  
        return calendar.getTime();  
    }  
    /** 
     * 将日期转换为日历 
     * @param date 日期 
     * @return 日历 
     */  
    private static Calendar convert(Date date) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        return calendar;  
    }  
    
    
  //获取前月的第一天
    public static String month1() {
    	
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
	    
	    Calendar   cal_1=Calendar.getInstance();//获取当前日期
	    cal_1.add(Calendar.MONTH, -1);
	    cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
	    String firstDay = format.format(cal_1.getTime());
	   // System.out.println("-----1------firstDay:"+firstDay);
	    return firstDay;
    }
    
    //获取前月的最后一天
    public static String month2() {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		  
		 Calendar cale = Calendar.getInstance();  
		 cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天
		 String lastDay = format.format(cale.getTime());
		// System.out.println("-----2------lastDay:"+lastDay);
		 return lastDay; 
    }
    
    //获取当前月第一天：
    public static String month3() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
	   
	    Calendar c = Calendar.getInstance();   
	    c.add(Calendar.MONTH, 0);
	    c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
	    String first = format.format(c.getTime());
	   // System.out.println("===============first:"+first);
	    return first;
    }
    
  //获取当前月最后一天
    public static String month4() {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
	    
	    Calendar ca = Calendar.getInstance();   
	    ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH)); 
	    String last = format.format(ca.getTime());
	   // System.out.println("===============last:"+last);
	    return last;
    }
    
    public static List<Date> rankDate(List<Date> dateList) {
        //ArrayList<Date> dateList = new ArrayList<Date>();  
    	    Date tempDate = null;  
    	    for (int i = 0; i < dateList.size()-1; ++i) {  
    	        for (int j = 0; j < dateList.size()-1-i; ++j) {  
    	            if(dateList.get(j+1).after(dateList.get(j))){  
    	                tempDate = dateList.get(j);  
    	                dateList.set(j, dateList.get(j+1));  
    	                dateList.set(j+1, tempDate);  
    	            } 
    	        }  
    	    }  
    	    return dateList;
    	}
    
    /**
	 * 判断指定日期是星期几
	 * @param date
	 * @return
	 */
		public static String getWeekNum(Date date){
			String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
			try {		
				Calendar cal = Calendar.getInstance();
		        cal.setTime(date);
		        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		        if (w < 0)
		            w = 0;
				 return weekDays[w];
		       
			} catch (Exception e) {
				System.out.println("获取星期几失败");
				e.printStackTrace();
			}
			return "星期零";	
		}
		/**
		 * 判断指定日期是星期几
		 * @param date
		 * @return
		 */
		public static Integer getWeekNumber(Date date){
			Integer[] weekDays = {1, 2, 3, 4, 5, 6, 7};
			try {		
				Calendar cal = Calendar.getInstance();
		        cal.setTime(date);
		        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		        if (w < 0)
		            w = 0;
				 return weekDays[w];
		       
			} catch (Exception e) {
				System.out.println("获取星期几失败");
				e.printStackTrace();
			}
			return -1;	
		}
	//判断开始时间到结束时间段中有多少天除节假日和周末的工作日
	public static Integer getDoActionTime(List<String> list, String dotime, String actiontime) throws ParseException {
		if(dotime == null || actiontime == null) {
			return 0;
		} 
		String[] split = dotime.split(" 天 ");
		int HH = Integer.parseInt(split[0]);
		String[] split2 = split[1].split(" 时 ");
		int mm = Integer.parseInt(split2[0]);
		String[] split3 = split2[1].split(" 分");
		int ss = Integer.parseInt(split3[0]);
		//办理用时毫秒数
		long time = (HH * 24 * 60 * 60 + mm * 60 * 60 + ss * 60) * 1000;
		Date date = DateUtils.parseDateFromString(actiontime, "yyyy-MM-dd HH:mm:ss");
		long time2 = date.getTime();
		long start = time2 - time;
		//办理开始时间
		Date startdate = new Date(start);
		String startTime = DateUtils.getFormatedDate(startdate, "yyyy-MM-dd HH:mm:ss");
		String[] startsplit = startTime.split(" ");
		startTime = startsplit[0];
		String[] endsplit = actiontime.split(" ");
		actiontime = endsplit[0];
		int day = 0;
		for(int i=0; i<30; i++) {
			if(!startTime.equals(actiontime)) {
				if(list.contains(startTime)) {
					day++;
				}
			}
			//后一天s
			startTime = DateUtils.getSpecifiedDayAfter(startTime);
		} 
		return HH-day;
	}
	//判断开始时间到结束时间段中有多少天除节假日和周末的工作日
		public static Integer getDoActionTimeWorkList(List<String> list, String dotime, String sendtime) throws ParseException {
			if(dotime == null || sendtime == null) {
				return 0;
			} 
			//办理开始时间
			String[] startsplit = sendtime.split(" ");
			sendtime = startsplit[0];
			String[] endsplit = dotime.split(" ");
			dotime = endsplit[0];
			int day = 0;
			for(int i=0; i<30; i++) {
				if(!sendtime.equals(dotime)) {
					if(list.contains(sendtime)) {
						day++;
					}
				}
				//后一天s
				sendtime = DateUtils.getSpecifiedDayAfter(sendtime);
			} 
			return day;
		}
	//指定时间到当前时间中有多少个工作日
	public static Integer getCurrentAndTime(List<String> list, String time, Date currentDate) {
		if(time == null || currentDate == null) {
			return 0;
		} 
		int day = 0;
		String[] split = time.split(" ");
		time = split[0];
		String endtime = DateUtils.getFormatedDate(currentDate, "yyyy-MM-dd");
		for(int i=0; i<30; i++) {
			if(!time.equals(endtime)) {
				if(!list.contains(time)) {
					day++;
				}
			}else{
				break;
			}
			time = DateUtils.getSpecifiedDayAfter(time);
		}
		return day;
	}
	
	/**
	 * 获取一个日期段中的所有日期
	 * @param dBegin
	 * @param dEnd
	 * @return
	 */
	 public static List<Date> findDates(Date dBegin, Date dEnd)  
	 {  
	  List<Date> lDate = new ArrayList<Date>();  
	  lDate.add(dBegin);  
	  Calendar calBegin = Calendar.getInstance();  
	  // 使用给定的 Date 设置此 Calendar 的时间  
	  calBegin.setTime(dBegin);  
	  Calendar calEnd = Calendar.getInstance();  
	  // 使用给定的 Date 设置此 Calendar 的时间  
	  calEnd.setTime(dEnd);  
	  // 测试此日期是否在指定日期之后  
	  while (dEnd.after(calBegin.getTime()))  
	  {  
	   // 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
	   calBegin.add(Calendar.DAY_OF_MONTH, 1);  
	   lDate.add(calBegin.getTime());  
	  }  
	  return lDate;  
	 }  
	 
	 /**
	 * Redis缓存时间为当前时间到当前日 23:59:59 的秒数
	 * @return
	 */
	public static int redisTime(){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String format = sdf.format(new Date()) +" 23:59:59";
			Date parse = sdfd.parse(format);
			return  (int) (parse.getTime() - new Date().getTime());
		} catch (ParseException e) {
		}
		return 0;
	}
}














