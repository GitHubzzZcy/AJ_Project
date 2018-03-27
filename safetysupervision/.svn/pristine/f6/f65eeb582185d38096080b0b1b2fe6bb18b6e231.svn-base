package com.henghao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henghao.dao.EfficiencyImprovementDao;
import com.henghao.dao.IntegrityManagementDao;
import com.henghao.dao.MeetingInfomationDao;
import com.henghao.entity.Result;
import com.henghao.service.MeetingInfomationService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("MettingInfomationService")
public class MeetingInfomationServiceImpl implements MeetingInfomationService {
	@Autowired
	private MeetingInfomationDao meetingInfomationDao;
	@Autowired
	private IntegrityManagementDao integrityManagementDao;
	@Autowired
	private EfficiencyImprovementDao efficiencyImprovementDao;

	/**
	 * 按时间段年月周查询未参加未签到会议
	 */
	@Override
	public Result meetSignInfo(String date1, String date2) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = meetingInfomationDao.meetSignInfo(date1, date2);
		
		int total = 0;
		int a = 0;
		for (Object object : list) {
			Object[] objects = (Object[]) object;
			int num = 0;
			int count = 0;
			String name1 = objects[0].toString();
			String[] ary = name1.split(";");
			count = ary.length;
			String name2 = objects[1].toString();
			String[] arys = name2.split(";");
			// char[] ch = name1.toCharArray();
			// char[] chs = name2.toCharArray();
			for (String item : ary) {
				for (String items : arys) {
					if (item.equals(items)) {
						num = num + 1;
						break;
					}
				}
			}
			total = count -num;
			a = total + a;
		}
		jsonObject.put("noSignNum", a);
		jsonObject.put("noParticipateNum", a);
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

	/**
	 * 按时间段年月周查询未上传会议记录
	 */
	@Override
	public Result notUploadMeetMinutes(String date1, String date2) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = meetingInfomationDao.meetSignInfo(date1, date2);
		int num = 0;
		for (Object object : list) {
			Object[] objects = (Object[]) object;
			Object photo = objects[2];
			if (photo == null || photo == "") {
				num = num + 1;
			}
		}
		jsonObject.put("notPhotographed", num);
		jsonObject.put("unreported", num);
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

	/**
	 * 按时间段年月周查询会议类型
	 */
	@Override
	public Result meetingType(String date1, String date2) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = meetingInfomationDao.meetingType(date1, date2);
		for (Object object : list) {
			Object[] objects = (Object[]) object;
			jsonObject.put("meetingType", objects[0] == null ? "" : String.valueOf(objects[0]));
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
	 * 按年月周时间段查询
	 */
	@Override
	public Result meetingLongTime(String date1, String date2) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = meetingInfomationDao.meetSignInfo(date1, date2);
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		int e = 0;
		for (Object object : list) {
			Object[] objects = (Object[]) object;
			String t = objects[3].toString();
			int time = Integer.parseInt(t);
			if (time <= 120) {
				a = a + 1;
			} else if (time >= 120 || time < 180) {
				b = b + 1;
			} else if (time >= 180 || time < 240) {
				c = c + 1;
			} else if (time >= 240 || time < 300) {
				d = d + 1;
			} else {
				e = e + 1;
			}
		}
		jsonObject.put("lessTwo", a);
		jsonObject.put("toThree", b);
		jsonObject.put("toFour", c);
		jsonObject.put("tofive", d);
		jsonObject.put("greaterFive", e);
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

	/**
	 * 部门会议统计
	 */
	@SuppressWarnings("unused")
	@Override
	public Result deptMeetingStatistics(String date1, String date2) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list1 = efficiencyImprovementDao.findDept();
		for (int i = 0; i < list1.size(); i++) {
			String deptName = list1.get(i).toString();
			List<?> list = meetingInfomationDao.meetSignInfo(date1, date2);
			int num = 0;
			for (Object object : list) {
				Object[] objects = (Object[]) object;
				String name1 = objects[0].toString();
				String[] ary = name1.split(";");
				for (String item : ary) {
					List<?> list2 = integrityManagementDao.deptPersonle(deptName);
					for (int j = 0; j < list2.size(); j++) {
						String name = list2.get(j).toString();
						if (name.equals(item)) {
							num = num + 1;
						}
						break;
					}
				}
			}
			if (num != 0) {
				jsonObject.put("deptName", deptName);
				jsonObject.put("count", num);
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

}
