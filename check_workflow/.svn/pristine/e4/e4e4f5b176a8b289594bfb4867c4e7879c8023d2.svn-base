package com.henghao.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.henghao.dao.ICheckingDao;
import com.henghao.dao.IWorkTimeDao;
import com.henghao.dao.Rest_dayDao;
import com.henghao.entity.Checking;
import com.henghao.entity.Kaoqin;
import com.henghao.entity.Rest_day;
import com.henghao.entity.Result;

import com.henghao.entity.WorkTime;
import com.henghao.service.ICheckingService;
import com.henghao.service.Rest_dayService;
import com.henghao.util.DateUtils;
import com.henghao.util.PageBaen;
/**
 * 考勤业务接口
 * @author NWL
 *
 */
@Service
public class CheckingService implements ICheckingService{
	@Resource
	private IWorkTimeDao workTimeDao;
	@Resource
	private ICheckingDao dao;
	@Resource
	private Rest_dayService restService;
	@Resource
	private Rest_dayDao RestDao;
	
	@Override
	public Result findAllByUserName(String startDate, String endDate,  
			PageBaen pageBaen, String user) {
		Result result=null; 
		try {			
			Map<String,Object> map = dao.findAllByUserName(startDate, endDate, pageBaen, user);
			result=new Result(1,"查询成功",map);
		} catch (Exception e) { 
			result=new Result(1,"查询失败",null);
			e.printStackTrace();
		}
		return result;
	}	

	@Override
	public Result addChecking(Checking checking) {	
		//检查当年有没有国庆节，没有的话自动补全上今年的节假日数
		/*
		 * 20170619网页手动点击更新了，不用java服务器链接调用网络i节假日
		 */
		/*int yearnum = Integer.parseInt(DateUtils.getCurrentDate("yyyy"));		
		Rest_day rd=restService.findRestDay(yearnum*10000+1001);	
		if(rd == null){
			restService.autoUpdateRestDay(yearnum);
		}*/				
		Result result=null;
		String userId=checking.getUserId();
		try {		
			if(userId!= null){
				//20170608修改获取自定义的考勤时间段
				//WorkTime workTime = workTimeDao.findById(1);
				WorkTime workTime=workTimeDao.findWorktime(userId);
				
				Map<String,Object> map = dao.findByUserId(userId,DateUtils.getCurrentymd(new Date()));
				Checking check = (Checking) map.get("list");
				
				//每天有人打卡就给每个领导人默认签到人员：'曾宇','兰天','胡正康','邓发权','安尤光','魏燕飞','胡美琪','杨亚琦',
				//查询免考勤人员
				List<String> userIds = dao.queryMkqUser();
				//String[] leader={"HZ9080955acfcfff015acfe883040409","HZ9080955acfcfff015acfe9cb0f044d","HZ9080955acfcfff015acfea2edb0456","HZ9080955acfcfff015acfea808e045d","HZ9080955acfcfff015acfef3bb30555","HZ9080955acfcfff015ad00f25bd08bd","HZ9080955acfcfff015acff073f9056f","HZ9080955acfcfff015acfeff1750561"};
				for (String uid : userIds) {
					Map<String,Object> map1 = dao.findByUserId(uid,DateUtils.getCurrentymd(new Date()));
					Checking checkingl = (Checking)map1.get("list");
					if(checkingl != null){
						continue;
					}else{
						Date clockIn = DateUtils.getHHmm("09:00");
						Date clockOut = DateUtils.getHHmm("17:00");
						
						checkingl = new Checking();
						checkingl.setUserId(uid);
//						checkingl.setClockInTime(workTime.getClockIn());
//						checkingl.setClockOutTime(workTime.getClockOut());
						checkingl.setClockInTime(clockIn);
						checkingl.setClockOutTime(clockOut);
						checkingl.setCurrentDate(new Date());
						checkingl.setWeek(DateUtils.getWeekNum());
						checkingl.setAfterCount(1);
						checkingl.setMorningCount(1);
						dao.add(checkingl);
					}
					
				}
				
				//如果用户已打卡
				if(check != null){
					//如果为上午
					if(morOrAfter(workTime.getMiddleTime())){
						//如果用户上午已打卡
						if(check.getClockInTime() != null){
							int count = check.getMorningCount()+1;
							//设置上午访问次数加1
							check.setMorningCount(count);
							dao.update(check);
							result = new Result(0,"你已签到  "+count+" 次",null);
						}
					}else{
						//如果为下午
						//如果用户下午已打卡
						if(check.getClockOutTime() != null){
							int count = check.getAfterCount()+1;
							//设置上午访问次数加1
							check.setAfterCount(count);
							dao.update(check);
							result = new Result(0,"你已签退  "+count+" 次",null);
						}else{
							//如果用户下午未打卡
							check.setClockOutTime(new Date());
							check.setAfterCount(1);							
							check.setAfterLat(checking.getLat());
							check.setAfterLon(checking.getLon());
							//判断下班是否早退，早退了多久
/*							if(workTime != null){
								long later = DateUtils.timesBetween(DateUtils.getCurrentDateT("HH:mm:ss"), workTime.getClockOut());
								//如果结果大于0，那么证明已经早退;
								if(later > 0){
									//判断迟到时间，以分钟计算
									int laterTime = (int)(later/(1000*60));
									check.setAfterLocation("早退"+laterTime+"分钟");
									result = new Result(0,"你已经早退，早退了 "+laterTime+"分钟",null);
								}else{
									result = new Result(0,"签退成功 ",null);
								}
							}else{
								result = new Result(0,"签退成功 ",null);
							}	*/	
							long later = DateUtils.timesBetween(DateUtils.getCurrentDateT("HH:mm:ss"), workTime.getClockOut());
							//如果结果大于0，那么证明已经早退;
							if(later > 0){
								//判断迟到时间，以分钟计算
								int laterTime = (int)(later/(1000*60));
								check.setAfterLocation("早退"+laterTime+"分钟");
								result = new Result(0,"你已经早退，早退了 "+laterTime+"分钟",null);
							}else{
								result = new Result(0,"签退成功 ",null);
							}
							dao.update(check);
							
							
						}
					}
				}else{
					//如果用户未打卡，判断当前时间，然后打上班卡还是下班卡
					String currentTime = DateUtils.getCurrentDate("HH:mm:ss");

					//isLate(String currentTime)方法判断传入日期是否超过12点
					if (isLate(currentTime)) {
						//超过12点，打下午卡
						checking.setUserId(userId);
						checking.setClockOutTime(new Date());
						checking.setCurrentDate(new Date());
						checking.setWeek(DateUtils.getWeekNum());
						checking.setAfterCount(1);						
						checking.setAfterLat(checking.getLat());
						checking.setAfterLon(checking.getLon());
						checking.setLat(null);
						checking.setLon(null);						
						result = new Result(0,"签退成功 ",null);
						//判断下班是否早退，早退了多久
						if(workTime != null){
							long later = DateUtils.timesBetween(DateUtils.getCurrentDateT("HH:mm:ss"), workTime.getClockOut());
							//如果结果大于0，那么证明已经早退;
							if(later > 0){
								//判断迟到时间，以分钟计算
								int laterTime = (int)(later/(1000*60));
								checking.setAfterLocation("早退"+laterTime+"分钟");
								result = new Result(0,"你已经早退，早退了 "+laterTime+"分钟",null);
							}
						}
						dao.add(checking);
					}else{
						//打早上卡
						checking.setUserId(userId);
						checking.setClockInTime(new Date());
						checking.setCurrentDate(new Date());
						checking.setWeek(DateUtils.getWeekNum());
						checking.setMorningCount(1);						
						result = new Result(0,"签到成功",null);
						//判断上班是否迟到,迟到了多久
						if(workTime != null){
							long later;
							later = DateUtils.timesBetween(DateUtils.getCurrentDateT("HH:mm:ss"), workTime.getClockIn());						
							//如果结果小于0，那么证明已经迟到;
							if(later < 0){
								//判断迟到时间，以分钟计算
								int laterTime = (int)((-later)/(1000*60));
								checking.setCheckLocation("迟到"+laterTime+"分钟");
								result = new Result(0,"你已经迟到，迟到 "+laterTime+"分钟",null);
							}
						}
						dao.add(checking);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(1,"签到系统异常",null);
		}
		return result;
	}
	//判断当前时间是否为当天上午或者下午，上午返回true，下午返回false
	//修改为和当前传入时间对比是上班还是下班卡。
	public static boolean morOrAfter(Date time) {
		boolean morOrAfter = true;
		//String str = DateUtils.getCurrentDate("yyyy-MM-dd") +" 12:00:00";
		//Date date = null;
		try {
			//date = DateUtils.parseDateFromString(str);
			//当前时间-中午12点
			Long between = DateUtils.timesBetween(time, DateUtils.getCurrentDateT("HH:mm:ss"));
			//如果差值小于0，那么证明是上午,否则就是下午
			if(between <= 0){
				morOrAfter = true;
			}else{
				morOrAfter = false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return morOrAfter;
	}
	//判断传入日期是否超过12点
	public boolean isLate(String upTime){
		//定义一个标准时间
		int[] arr = {12,0,0};
		String[] strings = upTime.split(":");
		int[] temp = new int[strings.length];
		//将字符数据转为int数组
		for (int i = 0; i < strings.length; i++) {
			temp[i]=Integer.parseInt(strings[i]);
		}
		//比较小时
		if (temp[0]>arr[0]) {
			return true;
		}
		if(temp[0]==arr[0]){
			//比较分钟
			if (temp[1]>arr[1]) {
				return true;
			}
			//如果分钟相等	
			if (temp[1]==arr[1]) {
				//比较秒的用意，是为了对刚好在时间点打卡（如：9:00:00）的判断
				if (temp[2]>arr[2]) {
					return true;
				}
			}

		}
		return false;
	}


	@Override
	public Result findQdListByUserId(String userId, String startDate,
			String endDate, PageBaen pageBaen) {
		Result result=null;
		try {
			Map<String,Object> map=dao.findQdListByUserId(userId, startDate, endDate, pageBaen);
			result=new Result(0,"查询成功",map.get("list"));
		} catch (Exception e) {
			result=new Result(1,"系统错误",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findQjListByUserId(String userId, String startDate,
			String endDate, PageBaen pageBaen) {
		Result result=null;
		try {
			Map<String,Object> map=dao.findQjListByUserId(userId, startDate, endDate, pageBaen);
			result=new Result(0,"查询成功",map.get("list"));
		} catch (Exception e) {
			result=new Result(1,"系统错误",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findCcListByUserId(String userId, String startDate,
			String endDate, PageBaen pageBaen) {
		Result result=null;
		try {
			Map<String,Object> map=dao.findCcListByUserId(userId, startDate, endDate, pageBaen);
			result=new Result(0,"查询成功",map.get("list"));
		} catch (Exception e) {
			result=new Result(1,"系统错误",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findBqListByUserId(String userId, String startDate,
			String endDate, PageBaen pageBaen) {
		Result result=null;
		try {
			Map<String,Object> map=dao.findBqListByUserId(userId, startDate, endDate, pageBaen);
			result=new Result(0,"查询成功",map.get("list"));
		} catch (Exception e) {
			result=new Result(1,"系统错误",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findCdListByUserId(String userId, String startDate,
			String endDate, PageBaen pageBaen) {
		Result result=null;
		try {
			WorkTime workTime = workTimeDao.findById(1);
			Map<String,Object> map=dao.findCdListByUserId(userId, startDate, endDate, pageBaen,workTime.getClockIn().toString());
			result=new Result(0,"查询成功",map.get("list"));
		} catch (Exception e) {
			result=new Result(1,"系统错误",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findZtListByUserId(String userId, String startDate,
			String endDate, PageBaen pageBaen) {
		Result result=null;
		try {
			WorkTime workTime = workTimeDao.findById(1);
			Map<String,Object> map=dao.findZtListByUserId(userId, startDate, endDate, pageBaen,workTime.getClockOut().toString());
			result=new Result(0,"查询成功",map.get("list"));
		} catch (Exception e) {
			result=new Result(1,"系统错误",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findKgListByUserId(String userId, String startDate,
			String endDate, PageBaen pageBaen) {
		Result result=null;
		try {
			Map<String,Object> map=dao.findKgListByUserId(userId, startDate, endDate, pageBaen);
			result=new Result(0,"查询成功",map.get("list"));
		} catch (Exception e) {
			result=new Result(1,"系统错误",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findQkListByUserId(String userId, String startDate,
			String endDate, PageBaen pageBaen) {
		Result result=null;
		try {
			Map<String,Object> map=dao.findQkListByUserId(userId, startDate, endDate, pageBaen);
			result=new Result(0,"查询成功",map.get("list"));
		} catch (Exception e) {
			result=new Result(1,"系统错误",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findAllListByDate(String startDate, String endDate,
			PageBaen pageBaen) {
		Result result=null;
		try {			
			Map<String,Object> map=dao.findAllListByDate( startDate, endDate, pageBaen);
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统错误",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Result findAllNumByUserId(String date,
			String userId,PageBaen pageBaen) {
		String startDate=DateUtils.getStardate(date);
		String endDate=DateUtils.getEnddate(date);
		WorkTime workTime = workTimeDao.findById(1);
		Result result=null;
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			/***********评分系统**start*************************/
			Map<String, Object> findKgListByUserId = dao.findKgListByUserId(userId, startDate, endDate, pageBaen);
			List<String> gradeKG = (List<String>) findKgListByUserId.get("grade");
			Map<String, Object> findZtListByUserId = dao.findZtListByUserId(userId, startDate, endDate, pageBaen, workTime.getClockOut().toString());
			List<String> gradeZT = new ArrayList<String>();
			List<Checking> list = (List<Checking>) findZtListByUserId.get("list");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (Checking checking : list) {
				Date currentDate = checking.getCurrentDate();
				String format = sdf.format(currentDate);
				gradeZT.add(format);
			}
			List<String> gradeCD = new ArrayList<String>();
			Map<String, Object> findCdListByUserId = dao.findCdListByUserId(userId, startDate, endDate, pageBaen, workTime.getClockIn().toString());
			List<Checking> listCD = (List<Checking>) findCdListByUserId.get("list");
			for (Checking checking : listCD) {
				String format = sdf.format(checking.getCurrentDate());
				gradeCD.add(format);
			}
			Map<String, Object> findQkListByUserId = dao.findQkListByUserId(userId, startDate, endDate, pageBaen);
			List<String> gradeQK = new ArrayList<String>();
			List<Checking> listQK = (List<Checking>) findQkListByUserId.get("list");
			for (Checking checking : listQK) {
				String format = sdf.format(checking.getCurrentDate());
				gradeQK.add(format);
			}
			/****************评分系统**end*******************/
			//时间段签到数据
			int qddays=((List<?>)dao.findQdListByUserId(userId, startDate, endDate, pageBaen).get("list")).size();
			//旷工数据  
			int kgdays =  ((List<?>) findKgListByUserId.get("list")).size();
			//查询早退
			int ztdays= ((List<?>)findZtListByUserId.get("list")).size();
			//查询迟到
			int cddays=((List<?>)findCdListByUserId.get("list")).size();
			//查询缺卡
			int qkdays=((List<?>)findQkListByUserId.get("list")).size();
			map.put("qddays", qddays);
			map.put("kgdays", kgdays);
			//map.put("bqdays", bqdays);			
			map.put("ztdays", ztdays);
			map.put("cddays", cddays);
			map.put("qkdays", qkdays);			
			int normalCkdays=dao.findNormalCkDays(userId, startDate, endDate);
			int ccdays=dao.findCcDays(userId, startDate, endDate);
			int qjdays=dao.findQjDays(userId, startDate, endDate);
			map.put("qjdays", qjdays);
			map.put("ccdays", ccdays);
			map.put("normalCkdays", normalCkdays);
			//出勤率
			int shouldday = DateUtils.getWorkDay(date)-RestDao.Restnum(startDate, endDate);
			int chuqingday = shouldday - kgdays - qjdays;
			if(chuqingday < 1)chuqingday = 0;
			double doublecqday = (double) shouldday;
			double cql = chuqingday / doublecqday;
			map.put("chuqingli", cql);
			map.put("gradeKG", gradeKG);
			map.put("gradeZT", gradeZT);
			map.put("gradeCD", gradeCD);
			map.put("gradeQK", gradeQK);
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"查询失败",null);
			e.printStackTrace();
		}		
		return result;
	}

	@Override
	public Result findAllListByDateAndUseName(String startDate, String endDate,
			String userName, PageBaen pageBaen) {
		Result result=null;
		try {
			Map<String,Object> map=dao.findAllListByDateAndUseName(startDate, endDate, userName, pageBaen);
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统错误",null);
			e.printStackTrace();
		}
		return result;

	}

	@Override
	public Result findCkByCkid(String ckId) {
		Result result=null;
		try {
			Map<String,Object> map=dao.findCkByCkid(ckId);
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统错误",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findAllListByDateAndDept(String startDate, String endDate, String dept,
			PageBaen pageBaen) {
		Result result=null;
		try {
			Map<String,Object> map=dao.findAllListByDateAndDept(startDate, endDate, dept, pageBaen);
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统错误",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findCurrentCk(String date) {
		Result result=null;		
		try {
			if(date == null){
				date = DateUtils.getCurrentymd(new Date());
			}
			Map<String,Object> map=dao.findCurrentCk(date);
			result=new Result(0,"查询成功",map.get("list"));
		} catch (Exception e) {
			result=new Result(1,"系统错误",null);
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Result findByUserId(String userId,String date) {
		Result result=null;
		try {
			Checking ck=new Checking();
			//判断当天是否请假出差补签还是正常打卡
			//有签到记录
			Map<String, Object> mapck = dao.findByUserId(userId, date);
			if ( mapck.get("list") != null) {
				ck=(Checking)mapck.get("list");				
			}else{
				//无签到记录
				for (int i = 0; i < 1; i++) {							
					//是否有特殊情况
					//出差
					Map<String, Object> mapcc = dao.findCcListByUserId(userId,
							date, date, new PageBaen(1, 1));
					//请假
					Map<String, Object> mapqj = dao.findQjListByUserId(userId,
							date, date, new PageBaen(1, 1));
					//补签
					Map<String, Object> mapbq = dao.findBqListByUserId(userId,
							date, date, new PageBaen(1, 1));
					//有出差
					if (((List<Kaoqin>) mapcc.get("list")).size() != 0) {
						ck.setCheckType("出差");
						break;
					}//有请假
					else if (((List<Kaoqin>) mapqj.get("list")).size() != 0) {
						ck.setCheckType("请假");
						break;
					}//有补签
					else if (((List<Kaoqin>) mapbq.get("list")).size() != 0) {
						ck.setCheckType("补签");
						break;
					}//都没有的情况旷工
					else{
						//是周末提示周末，不是周末提示旷工
						//20170606修改调为节假日						
						
						/*if(DateUtils.getWeekNum(DateUtils.getCurrent(date)).equals("星期日") || DateUtils.getWeekNum(DateUtils.getCurrent(date)).equals("星期六")){
							ck.setCheckType("周末");
						}*/
						Rest_day rd=restService.isRestDay(DateUtils.getFormatedDate(date, "yyyy-MM-dd"));
						if(rd != null){
							ck.setCheckType(rd.getStatus());
						}else if(DateUtils.getCurrentymd(new Date()).equals(date)){	//如果是当天的话提示暂未签到
							ck.setCheckType("暂未签到");
						}
						else{
							ck.setCheckType("旷工");
						}						
					}				
				}
				
			}
			Map<String,Object> map=new HashMap<String, Object>();
			WorkTime workTime = workTimeDao.findWorktime(userId);
			System.out.println();			
			map.put("ClockIn", DateUtils.getFormatedDate(workTime.getClockIn(), "HH:mm:ss"));
			map.put("ClockOut",DateUtils.getFormatedDate(workTime.getClockOut(), "HH:mm:ss"));
			map.put("MiddleTime",DateUtils.getFormatedDate(workTime.getMiddleTime(), "HH:mm:ss"));		
			map.put("ck", ck);
			result = new Result(0, "查询成功", map);
			return result;
		} catch (Exception e) {
			result=new Result(1,"系统错误",null);
			e.printStackTrace();
		}
		return result;

	}

	@Override
	public Result findSZYD(String date) {
		Result result=null;	
		String startDate =null;
		String endDate = null;
		try {
			//如果待时间则查询时间
			if(date == null  || date.equals("")){
				date=DateUtils.getCurrentDate("yyyy");
			}
			startDate=DateUtils.getStardate(date);
			endDate = DateUtils.getEnddate(date);
			Map<String,Object> map=dao.findSZYD(startDate,endDate);
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统错误",null);
			e.printStackTrace();
		}
		return result;//2017-5-16上午10:25:55宁万龙
	}

	@SuppressWarnings("unchecked")
	@Override
	public Result findck_seatByDept(String date, String dept) {
		Result result=null;	
		String startDate =null;
		String endDate = null;
		WorkTime workTime = workTimeDao.findById(1);
		try {
			//如果待时间则查询时间
			if(date == null  || date.equals("")){
				date=DateUtils.getCurrentDate("yyyy-MM");
			}
			startDate=DateUtils.getStardate(date);
			endDate = DateUtils.getEnddate(date);
			Map<String,Object> map=dao.findck_seatByDept(startDate,endDate,workTime.getClockIn().toString(),workTime.getClockOut().toString(),dept);			
			
			List<Object[]> list=(List<Object[]>)map.get("list");	//获取查询结果	
			
			for (Object[] objects : list) {				
				String userId=(String)objects[1];	//用户id						
				//设置考勤率给对应人员
				int kgdays=((List<?>)dao.findKgListByUserId(userId, startDate, endDate, null).get("list")).size();			
				int qjdays=dao.findQjDays(userId, startDate, endDate);
				int shouldday=DateUtils.getWorkDay(endDate.substring(0, 7))-RestDao.Restnum(startDate, endDate);
				int chuqingday=shouldday-kgdays-qjdays;
				if(chuqingday<1)chuqingday=0;
				double doublecqday=(double)shouldday;
				double cql=chuqingday/doublecqday;
				objects[objects.length-1]=cql;	
			/*	String userName=((String)objects[2]).trim();	//用户姓名
				//list修改自定义排序，指定固定的第一个。
				if(userName.equals("魏燕飞")){
					list.set(list.indexOf(objects), list.get(0));
					list.set(0, objects);
				} */				
			}					
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统错误",null);
			e.printStackTrace();
		}
		return result;//2017-5-16上午10:25:55宁万龙
	}

	@Override
	public Result findImg(String condition) {
		Result result=null;	
		String url="http://222.85.156.33:8082/head/";
		List<String> ulist=new ArrayList<String>();
		try {
			Map<String,Object> map=dao.findImg(condition);
			@SuppressWarnings("unchecked")
			List<String> list=(List<String>)map.get("list");
			for (String string : list) {
				ulist.add(url+string);
			}
			result=new Result(0,"查询成功",ulist);
		} catch (Exception e) {
			result=new Result(1,"系统错误",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Map<String, Object> findAllRecord(String startDate, String endDate) {
		Map<String,Object> map=null;
		try {
			map= dao.findAllRecord(startDate, endDate);//2017-6-7下午5:36:52宁万龙
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}

