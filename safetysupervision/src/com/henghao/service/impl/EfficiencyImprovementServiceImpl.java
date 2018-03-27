package com.henghao.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.henghao.dao.EfficiencyImprovementDao;
import com.henghao.entity.Result;
import com.henghao.service.EfficiencyImprovementService;
import com.henghao.util.AgeUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("EfficiencyImprovementService")
public class EfficiencyImprovementServiceImpl implements EfficiencyImprovementService {
	@Autowired
	private EfficiencyImprovementDao efficiencyImprovementDao;

	/**
	 * 人员性别查询
	 */
	@Override
	public Result personleSex() {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = efficiencyImprovementDao.personleSex();
		for (Object object : list) {
			Object[] objects = (Object[]) object;
			jsonObject.put("sex", objects[0] == null ? "" : String.valueOf(objects[0]));
			jsonObject.put("count", objects[1] == null ? "" : String.valueOf(objects[1]));
			jsonArray.add(jsonObject);
		}
		String msg = "";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg = "查询无数据";
		} else {
			msg = "查询成功";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}

	/**
	 * 部门人数查询
	 */
	@Override
	public Result deptPersonleNum() {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
			List<?> list = efficiencyImprovementDao.deptPersonleNum();
			for (Object object : list) {
				Object[] objects = (Object[]) object;
				jsonObject.put("groupName", objects[0] == null ? "" : String.valueOf(objects[0]));
				jsonObject.put("num", objects[1] == null ? "" : String.valueOf(objects[1]));
				jsonArray.add(jsonObject);
			}
		String msg = "";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg = "查询无数据";
		} else {
			msg = "查询成功";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}
	

	/**
	 * 按时间段查询请假  休假 出差人数
	 */
	@Override
	public Result personleNum(String date1,String date2) {

		// 非空验证
		if ("".equals(date1) || date1 == null ||
			"".equals(date2) || date2 == null){
			return  new Result(0,"传入日期为空",null);
		}

		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();	
		 // 请假人数		 
		String qjCount = efficiencyImprovementDao.leaveNum(date1, date2);
		//出差人数
		String ccCount = efficiencyImprovementDao.travelNum(date1, date2);	
		//休假人数
		String xjCount = efficiencyImprovementDao.vacationNum(date1, date2);
		jsonObject.put("travelNum",ccCount);
		jsonObject.put("leaveNum", qjCount);
		jsonObject.put("vacationNum", xjCount);
		jsonArray.add(jsonObject);
		String msg;
		if (jsonObject.size() == 0 || jsonObject == null) {
			msg = "查询无数据";
		} else {
			msg = "查询成功";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}

	/**
	 * 获取所有人员的年龄
	 */
	@Override
	public Result ageAll() {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();	
		 // 查询出生日期		 
		List<String> list = efficiencyImprovementDao.ageAll();
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		int e = 0;
		for (int i = 0; i < list.size(); i++) {
			if(!"".equals(list.get(i))) {		
		        String birthTimeString = list.get(i).toString();
				int age = AgeUtils.getAgeFromBirthTime(birthTimeString);
				if (age>=20 && age< 30) {
					a =a + 1;
				}else if (age>=30 && age< 40) {
					b = b +1;
				}else if (age>=40 && age< 50) {
					c=c+1;
				}else if (age>=50 && age< 60) {
					d=d+1;
				}else {
					e=e+1;
				}
			}
		}	
		jsonObject.put("toThirty",a);
		jsonObject.put("toForty", b);
		jsonObject.put("toFifty", c);
		jsonObject.put("toSixty", d);
		jsonObject.put("moreSixty", e);
		jsonArray.add(jsonObject);
		String msg = "";
		System.out.println(jsonArray.size());
		if (jsonObject.size() == 0 || jsonObject == null) {
			msg = "查询无数据";
		} else {
			msg = "查询成功";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}
	
}
