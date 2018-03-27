package com.henghao.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.henghao.dao.SignificantAmountDao;
import com.henghao.entity.Result;
import com.henghao.service.SignificantAmountService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("SignificantAmountService")
public class SignificantAmountServiceImpl implements SignificantAmountService {
	@Autowired
	private SignificantAmountDao significantAmountDao;
	/**
	 * 重大资金使用比例
	 */
	@Override
	public Result significantAmountProportion() {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list =  significantAmountDao.significantAmountProportion();
		for(Object object:list){
			Object[] objects = (Object[]) object;
			jsonObject.put("deptName", objects[1]==null ? "" : String.valueOf(objects[1]));
			jsonObject.put("countMoney", objects[0]==null ? "" : String.valueOf(objects[0]));
			jsonArray.add(jsonObject);
		}
		String msg = "查询成功";
		if (jsonArray==null || jsonArray.size()==0) {
			msg ="查询无数据";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}
	

	/**
	 * 重大资金使用详情
	 */
	@Override
	public Result significantAmountDetails() {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list =  significantAmountDao.significantAmountDetails();
		for(Object object:list){
			Object[] objects = (Object[]) object;
			jsonObject.put("title", objects[0]==null ? "" : String.valueOf(objects[0]));
			jsonObject.put("money", objects[1]==null ? "" : String.valueOf(objects[1]));
			jsonObject.put("time", objects[2]==null ? "" : String.valueOf(objects[2]));
			jsonArray.add(jsonObject);
		}
		String msg = "查询成功";
		if (jsonArray==null || jsonArray.size()==0) {
			msg ="查询无数据";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}

}
