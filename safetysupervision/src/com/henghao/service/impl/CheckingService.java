package com.henghao.service.impl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.henghao.dao.ICheckingDao;
import com.henghao.entity.Result;
import com.henghao.service.ICheckingService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 考勤业务接口
 * @author NWL
 *
 */
@Service
public class CheckingService implements ICheckingService{
	@Resource
	private ICheckingDao dao;

	@Override
	public Result findKgListByUserId( String startDate,
			String endDate) {
		Result result=new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<Object> list1 = dao.findGroup();  // 查询分组名称
		for (int i = 0; i < list1.size(); i++) {
			String groupName = list1.get(i).toString();
			List<Object> list2 = dao.findGroupPersonle(groupName); // 按组名分组查询人员
			int count = 0;
			for (int j = 0; j < list2.size(); j++) {
				String userId = list2.get(j).toString();
				try {
					//查询人员时间段旷工天数
					count += dao.findKgListByUserId(userId, startDate, endDate);
				} catch (Exception e) {
					result=new Result(1,"系统错误",null);
					e.printStackTrace();
				}
			}
			jsonObject.put("groupName", list1.get(i));
			jsonObject.put("count", count);
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

	@Override
	public Result findAbsenteeismNum(String startDate, String endDate) {
		Result result=new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<Object> list1 = dao.findGroup();
		int count = 0;
		for (int i = 0; i < list1.size(); i++) {
			String groupName = list1.get(i).toString();
			List<Object> list2 = dao.findGroupPersonle(groupName);
			for (int j = 0; j < list2.size(); j++) {
				String userId = list2.get(j).toString();
				try {
					count += dao.findKgListByUserId(userId, startDate, endDate);
				} catch (Exception e) {
					result=new Result(1,"系统错误",null);
					e.printStackTrace();
				}
			}
		}
		jsonObject.put("personleNum", count);
		jsonArray.add(jsonObject);
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

	

	
}

