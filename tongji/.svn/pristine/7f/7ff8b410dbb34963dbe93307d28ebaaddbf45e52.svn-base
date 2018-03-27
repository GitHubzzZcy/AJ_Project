package com.henghao.service.impl;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.henghao.dao.EnforcementDao;
import com.henghao.entity.Result;
import com.henghao.service.EnforcementService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class EnforcementServiceImpl implements EnforcementService {

	@Autowired
	private EnforcementDao enforcementDao;

	@Override
	public Result enforcementCount(String name) {
		Result result =new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject =new JSONObject();
		DateFormat df = new SimpleDateFormat("yyyy");
		String year = df.format(new Date());
		int y = Integer.parseInt(year);
		String date1 = year + "-" +"01";
		String date2 = (y+1) + "-" +"01";
		List<?> list= enforcementDao.enforcementCount(name, date1, date2);
		jsonObject.put("enforcementCount", list.get(0));
		jsonArray.add(jsonObject);
//		for(Object object : list){
//			Object[] objs = (Object[]) object;
//			JSONObject jsonObject =new JSONObject();
//			jsonObject.put("inspectStartTime", objs[0] == null ? "" : String.valueOf(objs[0]));
//			jsonObject.put("inspectEndTime", objs[1] == null ? "" : String.valueOf(objs[1]));
//		}
		String msg ="";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg="查询无数据";
		}
		else {
			msg ="查询成功";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}

	@Override
	public Result monthEnforcementCount(String name) {
		Result result =new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject =new JSONObject();
		DateFormat df = new SimpleDateFormat("yyyy");
		String year = df.format(new Date());
		int y = Integer.parseInt(year);
		String date1="";
		String date2 ="";
		for (int i = 1; i < 13; i++) {
			if (i < 10) {
				date1 = year + "-"+"0"+i;
				if (i==9) {
					date2=year + "-"+(i+1);
				}else{
					date2 = year + "-"+"0"+(i+1);
				}
			} else if(i==12){
				date1 = year + "-"+i;
				date2 = (y+1) + "-"+"01";
			} else {
				date1 = year + "-"+i;
				date2 = year + "-"+(i+1);	
		}
			List<?> list= enforcementDao.monthEnforcementCount(name, date1, date2);
			jsonObject.put("month", i);
			jsonObject.put("count", list.get(0));
			jsonArray.add(jsonObject);
		}
		
		
//		for(Object object : list){
//			Object[] objs = (Object[]) object;
//			JSONObject jsonObject =new JSONObject();
//			jsonObject.put("inspectStartTime", objs[0] == null ? "" : String.valueOf(objs[0]));
//			jsonObject.put("inspectEndTime", objs[1] == null ? "" : String.valueOf(objs[1]));
//		}
		String msg ="";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg="查询无数据";
		}
		else {
			msg ="查询成功";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}

	/**
	 * 执法企业分类
	 */
	@Override
	public Result enforcementEnterprise(String name) {
		Result result =new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject =new JSONObject();
		List<?> list= enforcementDao.enforcementEnterprise(name);
		for(Object object : list){
			Object[] objs = (Object[]) object;
			jsonObject.put("count", objs[0] == null ? "" : String.valueOf(objs[0]));
			jsonObject.put("classificationName", objs[1] == null ? "" : String.valueOf(objs[1]));
			jsonArray.add(jsonObject);
		}
		String msg ="";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg="查询无数据";
		}
		else {
			msg ="查询成功";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}
}
