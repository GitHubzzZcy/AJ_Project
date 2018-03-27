package com.henghao.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.internal.LinkedTreeMap;
import com.henghao.dao.Rest_dayDao;
import com.henghao.entity.RestJson;
import com.henghao.entity.Rest_day;
import com.henghao.entity.Result;
import com.henghao.service.Rest_dayService;
import com.henghao.util.DateUtils;
import com.henghao.util.GsonUtil;
import com.henghao.util.PageBaen;
@Service
public class Rest_dayServiceImp implements Rest_dayService {
	@Resource
	private Rest_dayDao RestDao;
	@Override
	public Result updateRestDay(Rest_day restDay) {
		//传入休息日格式合法的的操作
		if(restDay.getId() <19700101 || restDay.getStatus() == null){
			return new Result(1, "日期或休息日名称不合法，更新失败", restDay);
		}else{
			try {
				restDay.setDate(DateUtils.getFormatedDate((restDay.getId()+""), "yyyyMMdd"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}	
		try {
			RestDao.updateRestDay(restDay);
			return new Result(0, "更新成功", restDay);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(1, "更新失败", restDay);
		}
	}

	@Override
	public Result addRestDay(Rest_day restDay) {
		//传入休息日格式合法的的操作
		if(restDay.getId() <19700101 || restDay.getStatus() == null){
			return new Result(1, "日期或休息日名称不合法，添加失败", restDay);
		}else{
			try {
				restDay.setDate(DateUtils.getFormatedDate((restDay.getId()+""), "yyyyMMdd"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		try {
			RestDao.updateRestDay(restDay);
			return new Result(0, "添加节假日成功", restDay);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(1, "添加节假日失败", restDay);
		}
	}

	@Override
	public Result deleteRestDay(Rest_day restDay) {
		//传入休息日格式合法的的操作
		if(restDay.getId() <19700101){
			return new Result(1, "日期不合法，删除失败", restDay);
		}else{
			try {
				restDay.setDate(DateUtils.getFormatedDate((restDay.getId()+""), "yyyyMMdd"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		try {
			RestDao.deleteRestDay(restDay);
			return new Result(0, "删除成功", restDay);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(1, "删除失败", restDay);
		}
	}

	@Override
	public Rest_day findRestDay(int id) {	
		Rest_day restDay=new Rest_day(0, null, "无节假日信息");
		//传入格式合法的的操作
		if(id<19700101){
			return restDay;
		}
		try {
			restDay=RestDao.findById(id);
			if(restDay==null){
				restDay=new Rest_day(0, null, "无节假日信息");
			}
			return restDay;
		} catch (Exception e) {
			e.printStackTrace();
			return restDay;
		}
	}

	@Override
	public Result findAllRestDay(String startDate,String endDate,PageBaen pagebean) {	
		List<Rest_day> restDay=null;
		try {
			if(startDate==null ||startDate.equals("")){
				startDate=DateUtils.getCurrentDate("yyyy")+"-01-01";
			}
			if(endDate==null ||endDate.equals("")){
				endDate=DateUtils.getCurrentDate("yyyy")+"-12-31";
			}
			Date sd=DateUtils.getFormatedDate(startDate, "yyyy-MM-dd");
			Date ed=DateUtils.getFormatedDate(endDate, "yyyy-MM-dd");
			restDay=RestDao.findAllRestDay(sd, ed,pagebean);
			Map<String,Object> map=new HashMap<String, Object>();
			int total=RestDao.findAllRestDay(sd, ed,new PageBaen(1, 10000000)).size();
			map.put("total", total);
			map.put("restDay", restDay);			
			return new Result(0, "查询成功", map);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(1, "查询失败", null);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result autoUpdateRestDay(int year) {		
		Result result=null;
		//传入休息日格式合法的的操作
		int curentyear=Integer.parseInt(DateUtils.getCurrentDate("yyyy"));
		if(year<1970 || year>curentyear){
			result = new Result(1,"暂时找不到当前日期的节假日信息,添加节假日失败",null);
			return result;
		}
		String GET_URL = "http://tool.bitefu.net/jiari/vip.php?d="+year+"&apikey=123456&type=3";  
		try {  
			URL url = new URL(GET_URL);    // 把字符串转换为URL请求地址  
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 打开连接  
			connection.connect();// 连接会话  
			// 获取输入流  
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));  
			String line;  
			StringBuilder sb = new StringBuilder();  
			while ((line = br.readLine()) != null) {// 循环读取流  
				sb.append(line);  
			}  
			br.close();// 关闭流  
			connection.disconnect();// 断开连接  
			//获取json节假日
			String restDay_json=sb.toString();			
			RestJson rest = GsonUtil.parseJsonWithGson(restDay_json,RestJson.class);
			Map<String,Object> map1=(Map<String,Object>)rest.getData();			
			ArrayList<Map<String,Object>> arr1=(ArrayList<Map<String,Object>>)map1.get(""+year);	
			//周末日期
			LinkedTreeMap ltm1=(LinkedTreeMap) arr1.get(0);
			Object[] a1= ltm1.keySet().toArray();
			Rest_day restday=new Rest_day();
			Date date=new Date();
			for (Object object : a1) {				
				String sdate=year+(String)object;	//年月日的string
				int rid=Integer.parseInt(sdate);	//id
				date=DateUtils.getFormatedDate(sdate, "yyyyMMdd");	//日期						
				restday.setId(rid); //保存年月日的int为id
				restday.setDate(date);
				restday.setStatus("周末");	//状态是周末
				RestDao.saveOrUpdate(restday);
			}
			//如果返回有节假日，排查添加未来几年的
			if(a1.length>1){
				//节假日期
				LinkedTreeMap ltm2=(LinkedTreeMap) arr1.get(1);
				Object[] a2= ltm2.keySet().toArray();
				for (Object object : a2) {
					String sdate=year+(String)object;	//年月日的string
					int rid=Integer.parseInt(sdate);	//id
					date=DateUtils.getFormatedDate(sdate, "yyyyMMdd");	//日期						
					restday.setId(rid); //保存年月日的int为id
					restday.setDate(date);
					restday.setStatus("法定假日");	//状态是周末
					RestDao.saveOrUpdate(restday);
				}
			}
			result = new Result(0,"添加节假日成功",null);
		} catch (Exception e) {  
			e.printStackTrace();  
			result = new Result(1,"出现异常,添加节假日失败",null);
		}  

		return result;
	}

	@Override
	public Rest_day isRestDay(Date date) {
		Rest_day rd = null;
		try {
			rd = RestDao.isRestDay(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	/**
	 * 手动更新节假日
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Result addOrUpdateRestDay(String restDay_json,int year){		
		Result result=null;
		//传入休息日格式合法的的操作
		int curentyear=Integer.parseInt(DateUtils.getCurrentDate("yyyy"));
		if(year<1970 || year>curentyear){
			result = new Result(1,"暂时找不到当前日期的节假日信息,添加节假日失败",restDay_json);
			return result;
		}
		try {  		
			RestJson rest = GsonUtil.parseJsonWithGson(restDay_json,RestJson.class);
			Map<String,Object> map1=(Map<String,Object>)rest.getData();			
			ArrayList<Map<String,Object>> arr1=(ArrayList<Map<String,Object>>)map1.get(""+year);	
			//周末日期
			LinkedTreeMap ltm1=(LinkedTreeMap) arr1.get(0);
			Object[] a1= ltm1.keySet().toArray();
			Rest_day restday=new Rest_day();
			Date date=new Date();
			for (Object object : a1) {				
				String sdate=year+(String)object;	//年月日的string
				int rid=Integer.parseInt(sdate);	//id
				date=DateUtils.getFormatedDate(sdate, "yyyyMMdd");	//日期						
				restday.setId(rid); //保存年月日的int为id
				restday.setDate(date);
				restday.setStatus("周末");	//状态是周末
				RestDao.saveOrUpdate(restday);
			}
			//如果返回有节假日，排查添加未来几年的
			if(a1.length>1){
				//节假日期
				LinkedTreeMap ltm2=(LinkedTreeMap) arr1.get(1);
				Object[] a2= ltm2.keySet().toArray();
				for (Object object : a2) {
					String sdate=year+(String)object;	//年月日的string
					int rid=Integer.parseInt(sdate);	//id
					date=DateUtils.getFormatedDate(sdate, "yyyyMMdd");	//日期						
					restday.setId(rid); //保存年月日的int为id
					restday.setDate(date);
					restday.setStatus("法定假日");	//状态是周末
					RestDao.saveOrUpdate(restday);
				}
			}
			result = new Result(0,"添加节假日成功",restDay_json);
		} catch (Exception e) {  
			e.printStackTrace();  
			result = new Result(1,"出现异常,添加节假日失败",restDay_json);
		}  

		return result;
	}
}
