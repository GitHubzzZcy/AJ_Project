package com.henghao.util;
import java.util.TimerTask;





/**
 * @Description: Redis缓存线程
 * @author zcy  
 * @date 2017年11月15日 下午2:49:34
 * @version 1.0
 */
public class Task extends TimerTask {  
	private static final String IP = "http://127.0.0.1:8082/safetysupervision";
	//缓存时间为一天
	@Override
	public void run() {
		//从spring中获取RedisService实例
		//RedisService redisService = (RedisService) SpringBeanUtil.getBeanByName("redisService");
		//获取当前时间
		String currentDate = DateUtils.getCurrentDate("yyyy-MM-dd");
		//获取本周一
		String monday = DateUtils.getFormatedDate(DateUtils.getMondayOfThisWeek(), "yyyy-MM-dd");
		//获取本月1号
		String monthOne = DateUtils.getCurrentDate("yyyy-MM")+"-01";
		//获取本年1月1号
		String year = DateUtils.getCurrentDate("yyyy")+"-01-01";
		
		System.out.println("进入redis线程**********************");
		int i = 0;
// #   	attendance/withoutReason //无故外出
		
		//本周
		Urljson.redisToUrl(IP+"/attendance/withoutReason?date1="+ monday +"&date2="+currentDate);
		System.out.println("Redis线程Url成功*****"+ i++);
		//本月
		Urljson.redisToUrl(IP+"/attendance/withoutReason?date1="+ monthOne +"&date2="+currentDate);
		System.out.println("Redis线程Url成功*****"+ i++);
		//本年
		Urljson.redisToUrl(IP+"/attendance/withoutReason?date1="+ year +"&date2="+currentDate);
		System.out.println("Redis线程Url成功*****"+ i++);
		
// #   	attendance/findwithoutReasonPersonleNum //出勤异常无故外出

		//本周
		Urljson.redisToUrl(IP+"/attendance/findwithoutReasonPersonleNum?date1="+ monday +"&date2="+currentDate);
		System.out.println("Redis线程Url成功*****"+ i++);
		//本月
		Urljson.redisToUrl(IP+"/attendance/findwithoutReasonPersonleNum?date1="+ monthOne +"&date2="+currentDate);
		System.out.println("Redis线程Url成功*****"+ i++);
		//本年
		Urljson.redisToUrl(IP+"/attendance/findwithoutReasonPersonleNum?date1="+ year +"&date2="+currentDate);
		System.out.println("Redis线程Url成功*****"+ i++);
		
// #    integrity/weekendUseCar //非工作日用车次数	
		//本周
		Urljson.redisToUrl(IP+"/integrity/weekendUseCar?date1="+ monday +"&date2="+currentDate);
		System.out.println("Redis线程Url成功*****"+ i++);
		//本月
		Urljson.redisToUrl(IP+"/integrity/weekendUseCar?date1="+ monthOne +"&date2="+currentDate);
		System.out.println("Redis线程Url成功*****"+ i++);
		//本年
		Urljson.redisToUrl(IP+"/integrity/weekendUseCar?date1="+ year +"&date2="+currentDate);
		System.out.println("Redis线程Url成功*****"+ i++);
		
// #    integrity/findVacationsUseCar //节假日用车	
		//本周
		Urljson.redisToUrl(IP+"/integrity/findVacationsUseCar?date1="+ monday +"&date2="+currentDate);
		System.out.println("Redis线程Url成功*****"+ i++);
		//本月
		Urljson.redisToUrl(IP+"/integrity/findVacationsUseCar?date1="+ monthOne +"&date2="+currentDate);
		System.out.println("Redis线程Url成功*****"+ i++);
		//本年
		 Urljson.redisToUrl(IP+"/integrity/findVacationsUseCar?date1="+ year +"&date2="+currentDate);
		 System.out.println("Redis线程Url成功*****"+ i++);

// #    integrity/driverByCarNo //驾驶员与车不符	
		//本周
		Urljson.redisToUrl(IP+"/integrity/driverByCarNo?date1="+ monday +"&date2="+currentDate);
		System.out.println("Redis线程Url成功*****"+ i++);
		//本月
		Urljson.redisToUrl(IP+"/integrity/driverByCarNo?date1="+ monthOne +"&date2="+currentDate);
		System.out.println("Redis线程Url成功*****"+ i++);
		//本年
		Urljson.redisToUrl(IP+"/integrity/driverByCarNo?date1="+ year +"&date2="+currentDate);
		System.out.println("Redis线程Url成功*****"+ i++);

// #    integrity/addressInconsistent //偏离和派车地不符
		//本周
		Urljson.redisToUrl(IP+"/integrity/addressInconsistent?date1="+ monday +"&date2="+currentDate);
		System.out.println("Redis线程Url成功*****"+ i++);
		//本月
		Urljson.redisToUrl(IP+"/integrity/addressInconsistent?date1="+ monthOne +"&date2="+currentDate);
		System.out.println("Redis线程Url成功*****"+ i++);
		//本年
		Urljson.redisToUrl(IP+"/integrity/addressInconsistent?date1="+ year +"&date2="+currentDate);
		System.out.println("Redis线程Url成功*****"+ i++);

// #    integrity/carReturn //车回人不会	
		//本周
		Urljson.redisToUrl(IP+"/integrity/carReturn?date1="+ monday +"&date2="+currentDate);
		System.out.println("Redis线程Url成功*****"+ i++);
		//本月
		Urljson.redisToUrl(IP+"/integrity/carReturn?date1="+ monthOne +"&date2="+currentDate);
		System.out.println("Redis线程Url成功*****"+ i++);
		//本年
		Urljson.redisToUrl(IP+"/integrity/carReturn?date1="+ year +"&date2="+currentDate);
		System.out.println("Redis线程Url成功*****"+ i++);
		
// #    integrity/menAndCarNot //人车不符	
		//本周
		Urljson.redisToUrl(IP+"/integrity/menAndCarNot?date1="+ monday +"&date2="+currentDate);
		System.out.println("Redis线程Url成功*****"+ i++);
		//本月
		Urljson.redisToUrl(IP+"/integrity/menAndCarNot?date1="+ monthOne +"&date2="+currentDate);
		System.out.println("Redis线程Url成功*****"+ i++);
		//本年
		Urljson.redisToUrl(IP+"/integrity/menAndCarNot?date1="+ year +"&date2="+currentDate);
		System.out.println("Redis线程Url成功*****"+ i++);

// #    integrity/useCarByOvertime //用车超时
		//本周
		Urljson.redisToUrl(IP+"/integrity/useCarByOvertime?date1="+ monday +"&date2="+currentDate);
		System.out.println("Redis线程Url成功*****"+ i++);
		//本月
		Urljson.redisToUrl(IP+"/integrity/useCarByOvertime?date1="+ monthOne +"&date2="+currentDate);
		System.out.println("Redis线程Url成功*****"+ i++);
		//本年
		Urljson.redisToUrl(IP+"/integrity/useCarByOvertime?date1="+ year +"&date2="+currentDate);
		System.out.println("Redis线程Url成功*****"+ i++);
		
		
	}
} 