package com.henghao.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henghao.dao.EnteriseDao;
import com.henghao.dao.UserPosionDao;
import com.henghao.entity.Result;
import com.henghao.entity.User_position_log;
import com.henghao.service.EnterpriceService;
import com.henghao.util.DateUtils;
import com.henghao.util.DistanceUtil;
/**
 * 统计业务类
 * @author NWL
 *
 */
@Service
public class EnterpriceServiceImpl implements EnterpriceService {
	@Autowired
	private EnteriseDao endao;
	@Autowired
	private UserPosionDao Pdao;
	
	@Override
	public Result findLeaderWhere(String num) {
		Result result=null;
		int no=3;
		if(num !=null){
			no = Integer.parseInt(num);
		}
		try {			
			Map<String,Object> map=endao.findLeaderWhere(no);
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findCxjs(String num) {
		Result result=null;
		int no=1;
		if(num !=null){
			no = Integer.parseInt(num);
		}
		try {			
			Map<String,Object> map=endao.findCxjs(no);
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findAllUser(String condition) {
		Result result=null;		
		try {			
			Map<String,Object> map=endao.findAllUser(condition);
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;
	}
//--------------------------------------我是华丽 的 “分割线” 20170517---------------------------------------------------------
	@Override
	public Result findTSnum(String date, String user) {
		Result result=null;
		if(date==null || date.equals("")){
			date=DateUtils.getCurrentDate("yyyy");
		}
		try {
			String startdate=DateUtils.getStardate(date);
			String enddate=DateUtils.getEnddate(date);
			Map<String,Object> map=endao.findTSnum(startdate, enddate, user);
			result=new Result(0, "查询成功", map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;//2017-5-17下午1:47:47宁万龙
	}

	@Override
	public Result findSPnum(String date, String user) {
		Result result=null;
		if(date==null || date.equals("")){
			date=DateUtils.getCurrentDate("yyyy");
		}
		try {
			String startdate=DateUtils.getStardate(date);
			String enddate=DateUtils.getEnddate(date);
			Map<String,Object> map=endao.findSPnum(startdate, enddate, user);
			result=new Result(0, "查询成功", map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;//2017-5-17下午1:47:47宁万龙
	}

	@Override
	public Result findSPstatusNum(String date, String user) {
		Result result=null;
		if(date==null || date.equals("")){
			date=DateUtils.getCurrentDate("yyyy");
		}
		try {
			String startdate=DateUtils.getStardate(date);
			String enddate=DateUtils.getEnddate(date);
			Map<String,Object> map=endao.findSPstatusNum(startdate, enddate, user);
			result=new Result(0, "查询成功", map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;//2017-5-17下午1:47:47宁万龙
	}

	@Override
	public Result findSPNumByjidu(String date, String user) {
		Result result=null;
		if(date==null || date.equals("")){
			date=DateUtils.getCurrentDate("yyyy");
		}
		try {
			String startdate=DateUtils.getStardate(date);
			String enddate=DateUtils.getEnddate(date);
			Map<String,Object> map=endao.findSPNumByjidu(startdate, enddate, user);
			result=new Result(0, "查询成功", map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;//2017-5-17下午1:47:47宁万龙
	}

	@Override
	public Result findSPtimeSize(String date, String user) {
		Result result=null;
		if(date==null || date.equals("")){
			date=DateUtils.getCurrentDate("yyyy");
		}
		try {
			String startdate=DateUtils.getStardate(date);
			String enddate=DateUtils.getEnddate(date);
			Map<String,Object> map=endao.findSPtimeSize(startdate, enddate, user);
			List<?> list= (List<?>)map.get("list");
			int allnum=list.size();
			String sb=null;
			String day="0";
			String hours="0";
			String mins="1";
			Integer time=1;
			for (Object object : list) {
				sb=object.toString();
				day=sb.substring(0, sb.indexOf("天")).trim();
				hours=sb.substring(sb.indexOf("天")+1,sb.indexOf("时")).trim();
				mins=sb.substring(sb.indexOf("时")+1,sb.indexOf("分")).trim();
				System.out.println(sb+"day="+day+"hours="+hours+"mins="+mins);
				time+=Integer.parseInt(day)*24+Integer.parseInt(hours)*60+Integer.parseInt(mins);
			}			
			result=new Result(0, "查询成功", time/(double)allnum);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;//2017-5-17下午1:47:47宁万龙
	}
	@Override
	public Result findZfddPeople(String name, String daduiNum){
		Result result=null;
		try {
			if(null==name || "".equals(name)){
				name="_";
			}
			if(null==daduiNum || "".equals(daduiNum)){
				daduiNum="_";
			}
			Map<String,Object> map=endao.findZfddPeople(name,daduiNum);
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}		
		return result;
	}

	@Override
	public Result findZfcxNum(String daduiName, String date) {
		Result result=null;
		try {
			if(null==daduiName || "".equals(daduiName)){
				return new Result(1,"传入参数：daduiName（大队名字）不能为空",0);
			}
			if(null==date || "".equals(date)){
				return new Result(1,"传入参数：date（时间）不能为空",0);
			}
			String startdate=DateUtils.getStardate(date);			//开始时间
			String enddate=DateUtils.getEnddateExceptCurrent(date);				//结束时间
			//获取时间段来查询每个月的数目
			int sint=Integer.parseInt(startdate.substring(5, 7));	//开始月份
			int eint=Integer.parseInt(enddate.substring(5, 7));		//结束月份
			int zfNum=0;	//执法数目
			for (int i = sint; i <= eint; i++) {
				//如果i是个位数
				if(i<10){
					zfNum+=endao.findZfcxNum(daduiName,date.substring(0, 4)+"-"+'0'+i);
				}else{
					zfNum+=endao.findZfcxNum(daduiName,date.substring(0, 4)+"-"+i);
				}
				
			
			}
		
			result=new Result(0,"查询成功,执法数如后",zfNum);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",0);
			e.printStackTrace();
		}		
		return result;
	}

	@Override
	public Result findPositionByDate(String uname, String sdate, String edate,
			double lat_a, double lng_a) {
		Date stratDate;
		Date endDate;
		Result result=null;
		try {
			stratDate=DateUtils.getFormatedDate(sdate, "yyyy-MM-dd HH:mm:ss");
			endDate = DateUtils.getFormatedDate(edate, "yyyy-MM-dd HH:mm:ss");
			List<User_position_log> list= Pdao.findPositionByDate(uname, stratDate, endDate);
			//如果有经个人的纬度信息
			if(list != null && !list.isEmpty()){
				double jili=0;
				for (User_position_log upl : list) {				
					jili += this.findDistance(lat_a, lng_a, upl.getLatitude(), upl.getLongitude());					
				}
				double avgjili=jili/list.size();
				result=new Result(0,"查询成功","人员平均偏离距离："+avgjili+"米");
			}else{
				result=new Result(1,"找不到人员该时间段的位置信息","该人员此时间段未上传任何位置信息,无法比较偏离情况");
			}					
		} catch (ParseException e) {
			result=new Result(1,"系统错误查询失败","找不到人员该时间段的位置信息");
			e.printStackTrace();
		}		
		return result;//2017-6-15下午4:57:33宁万龙
	}

	public double findDistance(double lat_a, double lng_a, double lat_b,
			double lng_b) {		
		double distance = DistanceUtil.getDistanceFromTwoPoints(lat_a, lng_a, lat_b, lng_b);
			
		return distance;
	}
	
	
}
