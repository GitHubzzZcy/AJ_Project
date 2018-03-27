package com.henghao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.henghao.dao.AttendanceDetailsDao;
import com.henghao.entity.Result;
import com.henghao.service.AttendanceDetailsService;
import com.henghao.util.GsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("AttendanceDetailsService")
public class AttendanceDetailsServiceImpl implements AttendanceDetailsService {

	@Autowired
	private AttendanceDetailsDao attendanceDetailsDao;
	private static final String IP_URL = "http://127.0.0.1:8082/check_workflow/";

	/**
	 * 规定日期出差情况
	 */
	@Override
	public Result evection(String date) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = attendanceDetailsDao.evection(date);
		if (list.size() != 0) {
			for (Object object : list) {
				Object[] objects = (Object[]) object;
				jsonObject.put("userId", objects[0] == null ? "" : String.valueOf(objects[0]));
				jsonObject.put("name", objects[1] == null ? "" : String.valueOf(objects[1]));
				jsonObject.put("dept", objects[2] == null ? "" : String.valueOf(objects[2]));
				jsonObject.put("evection", "出差");
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
	 * 规定日期请假情况
	 */
	@Override
	public Result leave(String date) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = attendanceDetailsDao.leave(date);
		if (list.size() != 0) {
			for (Object object : list) {
				Object[] objects = (Object[]) object;
				jsonObject.put("userId", objects[0] == null ? "" : String.valueOf(objects[0]));
				jsonObject.put("name", objects[1] == null ? "" : String.valueOf(objects[1]));
				jsonObject.put("dept", objects[2] == null ? "" : String.valueOf(objects[2]));
				jsonObject.put("leave", "请假");	
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
	 * 规定日期迟到情况
	 */
	@Override
	public Result late(String date) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = attendanceDetailsDao.late(date);
		if (list.size() != 0) {
			for (Object object : list) {
				Object[] objects = (Object[]) object;
				jsonObject.put("userId", objects[0] == null ? "" : String.valueOf(objects[0]));
				jsonObject.put("name", objects[1] == null ? "" : String.valueOf(objects[1]));
				jsonObject.put("dept", objects[2] == null ? "" : String.valueOf(objects[2]));
				jsonObject.put("late", objects[3] == null ? "" : String.valueOf(objects[3]));
				jsonObject.put("clockInTime", objects[4] == null ? "" : String.valueOf(objects[4]));
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
	 * 规定日期早退情况
	 */
	@Override
	public Result early(String date) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = attendanceDetailsDao.early(date);
		if (list.size() != 0) {
			for (Object object : list) {
				Object[] objects = (Object[]) object;
				jsonObject.put("userId", objects[0] == null ? "" : String.valueOf(objects[0]));
				jsonObject.put("name", objects[1] == null ? "" : String.valueOf(objects[1]));
				jsonObject.put("dept", objects[2] == null ? "" : String.valueOf(objects[2]));
				jsonObject.put("early", objects[3] == null ? "" : String.valueOf(objects[3]));
				jsonObject.put("clockOutTime", objects[4] == null ? "" : String.valueOf(objects[4]));
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
	 * 
	 * 规定日期未签到签到情况
	 */
	@Override
	public Result noSignIn(String date) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = attendanceDetailsDao.attendancePersonle();
		System.out.println(list.size());
		List<?> list1 = attendanceDetailsDao.morningAttendance(date);
		System.out.println(list1.size());
		for (int i = 0; i < list.size(); i++) {
			if (list1.contains(list.get(i))) {
				list.remove(i);
				i--;
			}
		}
		if (list.size() != 0) {
			for (int j = 0; j < list.size(); j++) {
				String userId = list.get(j).toString();
				List<?> list3 = attendanceDetailsDao.attendInfo(userId);
				for (Object object : list3) {
					Object[] objects = (Object[]) object;
					jsonObject.put("userId", objects[0] == null ? "" : String.valueOf(objects[0]));
					jsonObject.put("name", objects[1] == null ? "" : String.valueOf(objects[1]));
					jsonObject.put("dept", objects[2] == null ? "" : String.valueOf(objects[2]));
					jsonObject.put("noSignIn", "未签到");
					jsonArray.add(jsonObject);
				}
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
	 * 
	 * 规定日期已签到情况
	 */
	@Override
	public Result signedIn(String date) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = attendanceDetailsDao.signedIn(date);
		if (list.size() != 0) {
			for (Object object : list) {
				Object[] objects = (Object[]) object;
				jsonObject.put("userId", objects[0] == null ? "" : String.valueOf(objects[0]));
				jsonObject.put("name", objects[1] == null ? "" : String.valueOf(objects[1]));
				jsonObject.put("dept", objects[2] == null ? "" : String.valueOf(objects[2]));
				jsonObject.put("signedIn", "已签到");
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
	 * 按月份查询迟到记录
	 */
	@Override
	public Result monthLate(String date) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = attendanceDetailsDao.monthLate(date);
		if (list.size() != 0) {
			for (Object object : list) {
				Object[] objects = (Object[]) object;
				jsonObject.put("userId", objects[0] == null ? "" : String.valueOf(objects[0]));
				jsonObject.put("name", objects[1] == null ? "" : String.valueOf(objects[1]));
				jsonObject.put("dept", objects[2] == null ? "" : String.valueOf(objects[2]));
				jsonObject.put("late", objects[3] == null ? "" : String.valueOf(objects[3]));
				jsonObject.put("clockInTime", objects[4] == null ? "" : String.valueOf(objects[4]));
				jsonObject.put("currentDate", objects[5] == null ? "" : String.valueOf(objects[5]));
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
	 * 按月份查询早退记录
	 */
	@Override
	public Result monthEarly(String date) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = attendanceDetailsDao.monthEarly(date);
		if (list.size() != 0) {
			for (Object object : list) {
				Object[] objects = (Object[]) object;
				jsonObject.put("userId", objects[0] == null ? "" : String.valueOf(objects[0]));
				jsonObject.put("name", objects[1] == null ? "" : String.valueOf(objects[1]));
				jsonObject.put("dept", objects[2] == null ? "" : String.valueOf(objects[2]));
				jsonObject.put("early", objects[3] == null ? "" : String.valueOf(objects[3]));
				jsonObject.put("clockOutTime", objects[4] == null ? "" : String.valueOf(objects[4]));
				jsonObject.put("currentDate", objects[5] == null ? "" : String.valueOf(objects[5]));
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

	@Override
	public Result missingCard(String date) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = attendanceDetailsDao.missingCard(date);
		if (list.size() != 0) {
			for (Object object : list) {
				Object[] objects = (Object[]) object;
				jsonObject.put("userId", objects[0] == null ? "" : String.valueOf(objects[0]));
				jsonObject.put("name", objects[1] == null ? "" : String.valueOf(objects[1]));
				jsonObject.put("dept", objects[2] == null ? "" : String.valueOf(objects[2]));
				jsonObject.put("clockInTime", objects[3] == null ? "" : String.valueOf(objects[3]));
				jsonObject.put("clockOutTime", objects[4] == null ? "" : String.valueOf(objects[4]));
				jsonObject.put("currentDate", objects[5] == null ? "" : String.valueOf(objects[5]));
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

	@Override
	public Result absenteeism(String date) {
		// 返回对象
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();	
		JSONObject jsonObject = new JSONObject();
		List<?> list = attendanceDetailsDao.absenteeism();
		for (Object objects : list) {
			Object[] objs = (Object[]) objects;
			String userId = objs[0].toString();
			String url = IP_URL+"CI/findKgListByUserId?date=" + date + "&userId="
					+ userId;
			// 接口返回json传
			String json = GsonUtil.loadJSON(url);
			// 将json串转为json对象
			JSONObject obj = JSONObject.fromObject(json);
			Object object = obj.get("data");
			JSONArray array = JSONArray.fromObject(object);
			if (array != null) {
				for (Object arr : array) {
						
					String absenteeismDate = JSONObject.fromObject(arr).get("stardate").toString();
					String ckstatus = JSONObject.fromObject(arr).get("ckstatus").toString();
					jsonObject.put("userID", objs[0] == null ? "" : String.valueOf(objs[0]));
					jsonObject.put("name", objs[1] == null ? "" : String.valueOf(objs[1]));
					jsonObject.put("dept", objs[2] == null ? "" : String.valueOf(objs[2]));
					jsonObject.put("absenteeismDate", absenteeismDate);
					jsonObject.put("absenteeism", ckstatus);
					jsonArray.add(jsonObject);
				}
			}

		}
		String msg;
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
