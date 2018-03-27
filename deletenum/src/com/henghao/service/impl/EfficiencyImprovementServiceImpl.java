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
import com.henghao.dao.EfficiencyImprovementDao;
import com.henghao.entity.Result;
import com.henghao.service.EfficiencyImprovementService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("EfficiencyImprovementService")
public class EfficiencyImprovementServiceImpl implements EfficiencyImprovementService {
	@Autowired
	private EfficiencyImprovementDao efficiencyImprovementDao;

	/**
	 * 人员信息查询
	 */
	@Override
	public Result personnelInformation(String name) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = efficiencyImprovementDao.personnelInformation(name);
		for (Object object : list) {
			Object[] objects = (Object[]) object;
			jsonObject.put("userId", objects[0] == null ? "" : String.valueOf(objects[0]));
			jsonObject.put("name", objects[1] == null ? "" : String.valueOf(objects[1]));
			jsonObject.put("position", objects[2] == null ? "" : String.valueOf(objects[2]));
			jsonObject.put("depat", objects[3] == null ? "" : String.valueOf(objects[3]));
			jsonObject.put("workDuty", objects[4] == null ? "" : String.valueOf(objects[4]));
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
	 * 局领导查询
	 */
	@Override
	public Result bureauLeader() {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = efficiencyImprovementDao.bureauLeader();
		for (Object object : list) {
			Object[] objects = (Object[]) object;
			jsonObject.put("userId", objects[0] == null ? "" : String.valueOf(objects[0]));
			jsonObject.put("name", objects[1] == null ? "" : String.valueOf(objects[1]));
			jsonObject.put("position", objects[2] == null ? "" : String.valueOf(objects[2]));
			jsonObject.put("depat", objects[3] == null ? "" : String.valueOf(objects[3]));
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
	 * 得分统计详情
	 */
	@Override
	public Result scoreStatistics(String name) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		String time = df.format(new Date());
		String date = "";
		String year = time.substring(0, 4);
		date = year;
		List<?> list = efficiencyImprovementDao.scoreStatistics(name, date);
		jsonObject.put("year", list);
		String month = time.substring(5);
		date = year + "-" + month;
		List<?> list1 = efficiencyImprovementDao.scoreStatistics(name, date);
		jsonObject.put("month", list1);
		int m = Integer.parseInt(month);
		if (m == 1) {
			return null;
		} else {
			date = year + "-" + "0" + (m - 1);
			List<?> list2 = efficiencyImprovementDao.scoreStatistics(name, date);
			jsonObject.put("lastmonth", list2);
		}

		jsonArray.add(jsonObject);
		// for (Object object : list) {
		// Object[] objects = (Object[]) object;
		// jsonObject.put("classification", objects[1] == null ? "" :
		// String.valueOf(objects[1]));
		// jsonObject.put("score", objects[0] == null ? "" :
		// String.valueOf(objects[0]));
		// jsonArray.add(jsonObject);
		// }
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
		DateFormat df = new SimpleDateFormat("yyyy");
		String year = df.format(new Date());
		List<?> list = efficiencyImprovementDao.comprehensiveScore(name, year);
		if (list.size() == 0 || list.get(0) == null) {
			jsonObject.put("score", 0);
		} else {
			jsonObject.put("score", list.get(0));
		}
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

	/**
	 * 加分条数及详情
	 */
	@Override
	public Result plusCountAndDetails(String name) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<String> list1 = new ArrayList<String>();
		DateFormat df = new SimpleDateFormat("yyyy");
		String year = df.format(new Date());
		List<?> list = efficiencyImprovementDao.plusCount(name, year);
		List<?> lists = efficiencyImprovementDao.plusCountDetails(name, year);
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
		DateFormat df = new SimpleDateFormat("yyyy");
		String year = df.format(new Date());
		List<?> list = efficiencyImprovementDao.reductionCount(name, year);
		List<?> lists = efficiencyImprovementDao.reductionCountDetails(name, year);
		for (int i = 0; i < lists.size(); i++) {
			String names = lists.get(i).toString();
			list1.add(names);
		}
		jsonObject.put("count", list.get(0));
		jsonArray.add(jsonObject);
		jsonArray.add(list1);
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
	 * 行车情况统计分析
	 */
	@Override
	public Result vehicleStatistics(String name) {
		Result result = new Result();
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> lists1 = new ArrayList<Map<String, Object>>();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		DateFormat df = new SimpleDateFormat("yyyy");
		String year = df.format(new Date());
		List<?> list = efficiencyImprovementDao.travelTotalCount(name, year);
		jsonObject.put("totalCount", list.get(0));
		jsonArray.add(jsonObject);
		List<?> list1 = efficiencyImprovementDao.vehicleStatisticsCount(name, year);// 市内市外行车次数
		for (Object object : list1) {
			Map<String, Object> map = new HashMap<String, Object>();
			Object[] objects = (Object[]) object;
			map.put("name", objects[1] == null ? "" : String.valueOf(objects[1]));
			map.put("count", objects[0] == null ? "" : String.valueOf(objects[0]));
			lists.add(map);
		}
		jsonArray.add(lists);
		List<?> list2 = efficiencyImprovementDao.travelCategoryCount(name, year);// 单程
																					// 往返行车次数
		for (Object object : list2) {
			Map<String, Object> map = new HashMap<String, Object>();
			Object[] objects = (Object[]) object;
			map.put("name", objects[1] == null ? "" : String.valueOf(objects[1]));
			map.put("count", objects[0] == null ? "" : String.valueOf(objects[0]));
			lists1.add(map);
		}
		jsonArray.add(lists1);
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
	 * 获取事件信息和图片
	 */
	@Override
	public Result eventInformationAndImg(String userId, String date) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		// DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		// String date = df.format(new Date());
		List<?> list = efficiencyImprovementDao.eventInformation(userId, date);
		if (list.size() > 0) {
			for (Object object : list) {
				List<Object> list2 = new ArrayList<Object>();
				Object[] objects = (Object[]) object;
				// jsonObject.put("eventDate", objects[0] == null ? "" :
				// String.valueOf(objects[0]));
				jsonObject.put("eventName", objects[0] == null ? "" : String.valueOf(objects[0]));
				jsonObject.put("eventAddress", objects[1] == null ? "" : String.valueOf(objects[1]));
				jsonObject.put("eventTime", objects[2] == null ? "" : String.valueOf(objects[2]));
				String token = String.valueOf(objects[3]);
				List<?> list1 = efficiencyImprovementDao.eventInPicture(token);
				for (int i = 0; i < list1.size(); i++) {
					// Map<String, Object> map = new HashMap<String, Object>();
					// Object[] objs = (Object[]) object;
					// map.put("filePatn"+i, list1.get(i));
					list2.add(list1.get(i));
				}
				jsonObject.put("eventImageNameList", list2);
				jsonArray.add(jsonObject);
			}
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
	 * 领导评分
	 */
	@Override
	public Result leadershipScore(String userId) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		DateFormat df = new SimpleDateFormat("yyyy");
		String date = df.format(new Date());
		int score = 0;
		List<?> list = efficiencyImprovementDao.leadershipScore(userId, date);
		for (Object object : list) {
			Object[] objects = (Object[]) object;
			String leater = objects[1].toString();
			if (leater.equals("非常好（90-100）")) {
				score = score + 3;
			} else if (leater.equals("很好（80-90）")) {
				score = score + 2;
			} else if (leater.equals("好（70-80）")) {
				score = score + 1;
			} else if (leater.equals("不满意（60以下）")) {
				score = score - 1;
			} else {
				score = score + 0;
			}

		}
		jsonObject.put("score", score);
		jsonArray.add(jsonObject);
		String msg = "查询成功";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg = "查询无数据";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}

	/**
	 * 会议派车查询
	 */
	@Override
	public Result sendCarQuery() {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = efficiencyImprovementDao.sendCarQuery();
		for (Object object : list) {
			Object[] objects = (Object[]) object;
			String time = objects[1].toString();
			String date="";
			if (time.equals("")) {
				date = "";
			}else {
				date = time.substring(0, 10);
			}
			jsonObject.put("userName", objects[3] == null ? "" : String.valueOf(objects[3]));
			jsonObject.put("title", objects[0] == null ? "" : String.valueOf(objects[0]));
			jsonObject.put("date", date == null ? "" : date);
			jsonObject.put("address", objects[2] == null ? "" : String.valueOf(objects[2]));
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

	
}
