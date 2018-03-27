package com.henghao.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.henghao.dao.IntegrityManagementDao;
import com.henghao.entity.Result;
import com.henghao.service.IntegrityManagementService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("IntegrityManagementService")
public class IntegrityManagementServiceImpl implements IntegrityManagementService {
	@Autowired
	private IntegrityManagementDao integrityManagementDao;

	/**
	 * 得分统计详情
	 */
	@Override
	public Result scoreStatistics(String name) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
//		DateFormat df = new SimpleDateFormat("yyyy");
//		String date = df.format(new Date());
		List<?> list = integrityManagementDao.scoreStatistics(name);
		for (Object object : list) {
			Object[] objects = (Object[]) object;
			jsonObject.put("classification", objects[1] == null ? "" : String.valueOf(objects[1]));
			jsonObject.put("score", objects[0] == null ? "" : String.valueOf(objects[0]));
			jsonArray.add(jsonObject);
		}
		String msg = "查询成功";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg = "查询无数据";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}

	/**
	 * 个人综合评分
	 */
	@Override
	public Result comprehensiveScore(String name) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = integrityManagementDao.comprehensiveScore(name);
		jsonObject.put("score", list.get(0));
		jsonArray.add(jsonObject);
		String msg = "";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg = "查询无数据";
		}else{
			msg = "查询成功";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}

	/**
	 * 加分条数及详情
	 */
	@Override
	public Result plusCountAndDetails(String name) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<String> list1 = new ArrayList<String>();
		List<?> list = integrityManagementDao.plusCount(name);
		List<?> lists = integrityManagementDao.plusCountDetails(name);
		for (int i = 0; i < lists.size(); i++) {
			if (lists.get(i) != null) {
				String names = lists.get(i).toString();
				list1.add(names);
			}
		}
		jsonObject.put("count", list.get(0));
		jsonArray.add(jsonObject);
		jsonArray.add(list1);
		String msg = "查询成功";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg = "查询无数据";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}

	/**
	 * 减分条数及详情
	 */
	@Override
	public Result reductionCountAndDetails(String name) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<String> list1 = new ArrayList<String>();
		List<?> list = integrityManagementDao.reductionCount(name);
		List<?> lists = integrityManagementDao.reductionCountDetails(name);
		for (int i = 0; i < lists.size(); i++) {
			String names = lists.get(i).toString();
			list1.add(names);
		}
		jsonObject.put("count", list.get(0));
		jsonArray.add(jsonObject);
		jsonArray.add(list1);
		String msg = "查询成功";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg = "查询无数据";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}

	/**
	 * 季度分数比较
	 */
	@Override
	public Result quarterScore(String name) {
		Result result = new Result();
		List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list3 = new ArrayList<Map<String, Object>>();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		String time = df.format(new Date());
		String year = time.substring(0, 4);
		String month = time.substring(5, 6);
		int m = Integer.parseInt(month);
		String month1 = "";
		String date1 = "";
		String date2 = "";
		if (m == 0) {
			month1 = time.substring(6, 7);
			int t = Integer.parseInt(month1);
			if (0 < t && t < 4) {
				date1 = year + "-" + "01" + "-" + "01";
				date2 = year + "-" + "04" + "-" + "01";
				List<?> list = integrityManagementDao.quarterScore(name, date1, date2);
				for (Object object : list) {
					Object[] objects = (Object[]) object;
					jsonObject.put("name", objects[1] == null ? "" : String.valueOf(objects[1]));
					jsonObject.put("score", objects[0] == null ? "" : String.valueOf(objects[0]));
					jsonArray.add(jsonObject);
				}

			} else if (3 < t && t < 7) {
				date1 = year + "-" + "04" + "-" + "01";
				date2 = year + "-" + "07" + "-" + "01";
				List<?> list = integrityManagementDao.quarterScore(name, date1, date2);
				for (Object object : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					Object[] objects = (Object[]) object;
					map.put("name", objects[1]);
					map.put("score", objects[0]);
					list2.add(map);

				}
				jsonArray.add(list2);
				date1 = year + "-" + "01" + "-" + "01";
				date2 = year + "-" + "04" + "-" + "01";
				List<?> list1 = integrityManagementDao.quarterScore(name, date1, date2);
				for (Object object : list1) {
					Object[] objects = (Object[]) object;
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("name", objects[1]);
					map.put("lastQuarterScore", objects[0]);
					list3.add(map);
				}
				jsonArray.add(list3);

			} else {
				date1 = year + "-" + "07" + "-" + "01";
				date2 = year + "-" + 10 + "-" + "01";
				List<?> list = integrityManagementDao.quarterScore(name, date1, date2);
				for (Object object : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					Object[] objects = (Object[]) object;
					map.put("name", objects[1]);
					map.put("score", objects[0]);
					list2.add(map);
				}
				jsonArray.add(list2);
				date1 = year + "-" + "04" + "-" + "01";
				date2 = year + "-" + "07" + "-" + "01";
				List<?> list1 = integrityManagementDao.quarterScore(name, date1, date2);
				for (Object object : list1) {
					Map<String, Object> map = new HashMap<String, Object>();
					Object[] objects = (Object[]) object;
					map.put("name", objects[1]);
					map.put("lastQuarterScore", objects[0]);
					list3.add(map);
				}
				jsonArray.add(list3);	
			}
			

		} else {
			month1 = time.substring(5, 7);
			date1 = year + "-" + 10 + "-" + "01";
			date2 = year + "-" + 12 + "-" + 31;
			List<?> list = integrityManagementDao.quarterScore(name, date1, date2);
			for (Object object : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				Object[] objects = (Object[]) object;
				map.put("name", objects[1]);
				map.put("score", objects[0]);
				list2.add(map);
			}
			jsonArray.add(list2);
			date1 = year + "-" + "07" + "-" + "01";
			date2 = year + "-" + 10 + "-" + "01";
			List<?> list1 = integrityManagementDao.quarterScore(name, date1, date2);
			for (Object object : list1) {
				Map<String, Object> map = new HashMap<String, Object>();
				Object[] objects = (Object[]) object;
				map.put("name", objects[1]);
				map.put("lastQuarterScore", objects[0]);
				list3.add(map);
			}
			jsonArray.add(list3);

		}

		String msg = "";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg = "查询无数据";
		}else{
			msg = "查询成功";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}

	/**
	 * 录入选项
	 */
	@Override
	public Result option() {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = integrityManagementDao.option();
			jsonObject.put("name",list);
			jsonArray.add(jsonObject);
		String msg = "查询成功";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg = "查询无数据";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}

}
