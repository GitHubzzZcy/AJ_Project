package com.henghao.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


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

	//判断某当前日期当前月之前的工作日有几天
	public static int getWorkDay(){ 
		String input=getCurrentDate("yyyy-MM-dd");
		int count = 0;
		int date=Integer.parseInt(input.substring(8));
		int month = Integer.parseInt(input.substring(5, 7));
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(input.substring(0, 4)));
		cal.set(Calendar.MONTH,  month - 1);
		cal.set(Calendar.DATE, 1);
		while(cal.get(Calendar.DATE) <= date){
			int day = cal.get(Calendar.DAY_OF_WEEK);

			if(!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)){
				count++;
			}

			cal.add(Calendar.DATE, 1);
		}
		return count;
	}
	//判断当天的星期几数
	public static String getWeekNum(){
		Date dt;
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		try {
			dt = getCurrentDateT("yyyy-MM-dd");			
			Calendar cal = Calendar.getInstance();
	        cal.setTime(dt);
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
	//判断指定日期是星期几
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
		/**按时间条件查询，按月查询标准格式：YYYY-MM，按年查询：YYYY，按季度查询：YYYY-J，按周查询：YYYY-00ZZ
		 * 根据输入时间格式查询时间的第一天
		 */
		public static String getStardate(String condition){
			//标准时间格式
			SimpleDateFormat formatterNorm = new SimpleDateFormat( "yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			System.out.println("condition:"+condition);
			String stardate=null;
			String enddate=null;
			//按年查询
			if(condition.length()==4){
				System.out.println("按年查询");
				SimpleDateFormat formatter = new SimpleDateFormat( "yyyy");
				try {
					stardate=formatterNorm.format(formatter.parse(condition));				
					calendar.set(Calendar.YEAR, Integer.parseInt(condition));
					calendar.set(Calendar.MONTH,0);
					calendar.set(Calendar.DATE,1);
					calendar.add(Calendar.YEAR, 1);
					calendar.add(Calendar.DATE, -1);								
					enddate=formatterNorm.format(calendar.getTime());
					System.out.println("stardate-------"+stardate+",,enddate-----"+enddate);
				} catch (ParseException e) {
					System.out.println("年时间格式转换异常");
					e.printStackTrace();
				}
			}//按季度查询
			else if(condition.length()==6){
				//获取年和季度
				int year=Integer.parseInt(condition.substring(0, 4));
				int quarter=Integer.parseInt(condition.substring(5));
				System.out.println("按季度查询year"+year+"---quarter"+quarter);
				calendar.set(Calendar.YEAR, year);
				calendar.set(Calendar.DATE,1);
				calendar.set(Calendar.MONTH, (quarter-1)*3);
				//获取第一月
				stardate=formatterNorm.format(calendar.getTime());
				calendar.add(Calendar.MONTH, 3);
				calendar.add(Calendar.DATE, -1);
				//获取季度最后一天
				enddate=formatterNorm.format(calendar.getTime());
				System.out.println("stardate-------"+stardate+",,enddate-----"+enddate);
			}//按月查询
			else if(condition.length()==7){
				System.out.println("按月查询"+condition);
				//获取年和月
				int year=Integer.parseInt(condition.substring(0, 4));
				int month=Integer.parseInt(condition.substring(5));
				calendar.set(Calendar.YEAR, year);
				calendar.set(Calendar.DATE,1);
				calendar.set(Calendar.MONTH, month);
				//获取第一天
				stardate=formatterNorm.format(calendar.getTime());
				calendar.add(Calendar.MONTH, 1);
				calendar.add(Calendar.DATE, -1);
				//获取月最后一天
				enddate=formatterNorm.format(calendar.getTime());
				System.out.println("stardate-------"+stardate+",,enddate-----"+enddate);
			}//其他错误
			else{
				System.out.println("时间格式错误..错误");
				return null;			
			}
			return stardate;
		}
		/**按时间条件查询，按月查询标准格式：YYYY-MM，按年查询：YYYY，按季度查询：YYYY-J
		 * 根据输入时间格式查询时间的最后一天
		 */
		public static String getEnddate(String condition){
			//标准时间格式
			SimpleDateFormat formatterNorm = new SimpleDateFormat( "yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			System.out.println("condition:"+condition);
			String stardate=null;
			String enddate=null;
			//按时间条件查询，按月查询标准格式：YYYY-MM，按年查询：YYYY，按季度查询：YYYY-J
			//按年查询
			if(condition.length()==4){
				System.out.println("按年查询");
				SimpleDateFormat formatter = new SimpleDateFormat( "yyyy");
				try {
					stardate=formatterNorm.format(formatter.parse(condition));				
					calendar.set(Calendar.YEAR, Integer.parseInt(condition));
					calendar.set(Calendar.MONTH,0);
					calendar.set(Calendar.DATE,1);
					calendar.add(Calendar.YEAR, 1);
					calendar.add(Calendar.DATE, -1);								
					enddate=formatterNorm.format(calendar.getTime());
					System.out.println("stardate-------"+stardate+",,enddate-----"+enddate);
				} catch (ParseException e) {
					System.out.println("年时间格式转换异常");
					e.printStackTrace();
				}
			}//按季度查询
			else if(condition.length()==6){
				//获取年和季度
				int year=Integer.parseInt(condition.substring(0, 4));
				int quarter=Integer.parseInt(condition.substring(5));
				System.out.println("按季度查询year"+year+"---quarter"+quarter);
				calendar.set(Calendar.YEAR, year);
				calendar.set(Calendar.DATE,1);
				calendar.set(Calendar.MONTH, (quarter-1)*3);
				//获取第一月
				stardate=formatterNorm.format(calendar.getTime());
				calendar.add(Calendar.MONTH, 3);
				calendar.add(Calendar.DATE, -1);
				//获取季度最后一天
				enddate=formatterNorm.format(calendar.getTime());
				System.out.println("stardate-------"+stardate+",,enddate-----"+enddate);
			}//按月查询
			else if(condition.length()==7){
				//获取年和季度
				int year=Integer.parseInt(condition.substring(0, 4));
				int month=Integer.parseInt(condition.substring(5));
				calendar.set(Calendar.YEAR, year);
				calendar.set(Calendar.DATE,1);
				calendar.set(Calendar.MONTH, month);
				//获取第一天
				stardate=formatterNorm.format(calendar.getTime());
				calendar.add(Calendar.MONTH, 1);
				calendar.add(Calendar.DATE, -1);
				//获取月最后一天
				enddate=formatterNorm.format(calendar.getTime());
				System.out.println("stardate-------"+stardate+",,enddate-----"+enddate);
			}//其他错误
			else{
				System.out.println("时间格式错误..错误");
				return null;			
			}
			return enddate;
		}
}














