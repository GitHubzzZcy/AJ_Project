package com.henghao.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.henghao.utils.PageBaen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.henghao.dao.ApprovalDao;
import com.henghao.entity.Result;
import com.henghao.service.ApprovalService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("ApprovalService")
public class ApprovalServiceImpl implements ApprovalService {

	@Autowired
	private ApprovalDao approvalDao;

	/**
	 * 查询个人审批信息
	 */
	@Override
	public Result findMyApproval(String userId, int page, int pageSize) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONArray jsonArrays = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObjects = new JSONObject();
		List<?> list1 = approvalDao.findpersonnelInfo(userId);
		String name = "";
		String dept = "";
		for (Object object : list1) {
			Object[] objects = (Object[]) object;
			name = objects[0].toString();
			dept = objects[1].toString();
		}
		List<?> list = approvalDao.findMyApproval(userId);
		int size = list.size();
		PageBaen pagebean = new PageBaen(size);// 初始化PageBean对象
		// 设置当前页
		pagebean.setCurPage(page); // 这里page是从页面上获取的一个参数，代表页数
		pagebean.setPageSize(pageSize);
		pagebean.setRowsCount(list.size());
		// 获得分页大小
		int pagesize = pagebean.getPageSize();
		// 获得分页数据在list集合中的索引
		int firstIndex = (page - 1) * pagesize;
		int toIndex = page * pagesize;
		if (toIndex > list.size()) {
			toIndex = list.size();
		}
		if (firstIndex > toIndex) {
			firstIndex = 0;
			pagebean.setCurPage(1);
		}
		// 截取数据集合，获得分页数据
		List<?> courseList = list.subList(firstIndex, toIndex);
		List<JSONArray> list2 = new ArrayList<JSONArray>();
		for (Object object : courseList) {
			Object[] objects = (Object[]) object;
			jsonObject.put("id", objects[0] == null ? "" : String.valueOf(objects[0]));
			jsonObject.put("enterprise", objects[1] == null ? "" : String.valueOf(objects[1]));
			jsonObject.put("flowName", objects[2] == null ? "" : String.valueOf(objects[2]));
			jsonObject.put("time", objects[3] == null ? "" : String.valueOf(objects[3]));
			jsonObject.put("name", name);
			jsonObject.put("dept", dept);
			jsonObject.put("workId", objects[4] == null ? "" : String.valueOf(objects[4]));
			jsonObject.put("trackId", objects[5] == null ? "" : String.valueOf(objects[5]));
			jsonObject.put("status", objects[6] == null ? "" : String.valueOf(objects[6]));
			jsonArrays.add(jsonObject);
		}
		list2.add(jsonArrays);
		jsonObjects.put("total", list.size());
		jsonObjects.put("list", list2);
		jsonArray.add(jsonObjects);
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
	 * 查询所有审批信息
	 */
	@Override
	public Result findApprovalTotal() {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		// JSONArray jsonArrays = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		// JSONObject jsonObjects = new JSONObject();
		// List<JSONArray> list2 = new ArrayList<JSONArray>();
		List<?> list1 = approvalDao.findLeaderPersonnelInfo();
		String name = "";
		String dept = "";
		for (Object obj : list1) {
			Object[] objs = (Object[]) obj;
			String userId = objs[0].toString();
			name = objs[1].toString();
			dept = objs[2].toString();
			List<?> list = approvalDao.findMyApproval(userId);
			for (Object object : list) {
				Object[] objects = (Object[]) object;
				jsonObject.put("id", objects[0] == null ? "" : String.valueOf(objects[0]));
				jsonObject.put("enterprise", objects[1] == null ? "" : String.valueOf(objects[1]));
				jsonObject.put("flowName", objects[2] == null ? "" : String.valueOf(objects[2]));
				jsonObject.put("time", objects[3] == null ? "" : String.valueOf(objects[3]));
				jsonObject.put("name", name);
				jsonObject.put("dept", dept);
				jsonObject.put("workId", objects[4] == null ? "" : String.valueOf(objects[4]));
				jsonObject.put("trackId", objects[5] == null ? "" : String.valueOf(objects[5]));
				jsonObject.put("status", objects[6] == null ? "" : String.valueOf(objects[6]));
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
	 * 查询所有审核信息
	 */
	@Override
	public Result findExamineTotal() {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		// JSONArray jsonArrays = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		// JSONObject jsonObjects = new JSONObject();
		// List<JSONArray> list2 = new ArrayList<JSONArray>();
		List<?> list1 = approvalDao.findDeptPersonnelInfonation();
		String name = "";
		String dept = "";
		for (Object obj : list1) {
			Object[] objs = (Object[]) obj;
			String userId = objs[0].toString();
			name = objs[1].toString();
			dept = objs[2].toString();
			List<?> list = approvalDao.findMyApproval(userId);
			for (Object object : list) {
				Object[] objects = (Object[]) object;
				jsonObject.put("id", objects[0] == null ? "" : String.valueOf(objects[0]));
				jsonObject.put("enterprise", objects[1] == null ? "" : String.valueOf(objects[1]));
				jsonObject.put("flowName", objects[2] == null ? "" : String.valueOf(objects[2]));
				jsonObject.put("time", objects[3] == null ? "" : String.valueOf(objects[3]));
				jsonObject.put("name", name);
				jsonObject.put("dept", dept);
				jsonObject.put("workId", objects[4] == null ? "" : String.valueOf(objects[4]));
				jsonObject.put("trackId", objects[5] == null ? "" : String.valueOf(objects[5]));
				jsonObject.put("status", objects[6] == null ? "" : String.valueOf(objects[6]));
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
	 * 查询个人未办理件数
	 */
	@Override
	public Result findMyApprovalNum(String userId) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = approvalDao.findMyApprovalNum(userId);
		String num = list.get(0).toString();
		jsonObject.put("count", num);
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
	 * 判断三重一大警告条数
	 */
	@Override
	public Result findMajorStatus(String date1, String date2) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<String> list3 = new ArrayList<String>();
		list3.add("重大问题决策");
		list3.add("重要干部任免");
		list3.add("重大项目安排");
		list3.add("大额资金使用");
		for (int j = 0; j < list3.size(); j++) {
			int count1 = 0;
			int count2 = 0;
			int count3 = 0;
			String type=list3.get(j);
		List<String> list1 = new ArrayList<String>();
		list1.add("LOADFILE");
		list1.add("SZYDSQB");
		list1.add("YJJYSC");
		List<?> list = approvalDao.findMajor(date1, date2,type);
		if (list.size() !=0) {
		for (Object object : list) {
			int num = 0;
			Object[] objects = (Object[]) object;
			for (int i = 0; i < objects.length; i++) {
				if (objects[i] == null || "".equals(objects[i])) {
					num = num + 1;
				}
			}
			String id = objects[0].toString();
			for (int i = 0; i < list1.size(); i++) {
				String name = list1.get(i);
				List<?> list2 = approvalDao.findMajorImg(id, name);
				if (list2.size() == 0) {
					num = num + 1;
				}
			}
			if (num == 1) {
				count1 = count1 + 1;
			} else if (num == 2) {
				count2 = count2 + 1;
			} else if (num >= 3) {
				count3 = count3 + 1;
			}
		}
		jsonObject.put("name", type);
		jsonObject.put("earlyWarning", count1);
		jsonObject.put("notice", count2);
		jsonObject.put("seriousWarning", count3);
		jsonArray.add(jsonObject);
		}else{
			jsonObject.put("name", type);
			jsonObject.put("earlyWarning", count1);
			jsonObject.put("notice", count2);
			jsonObject.put("seriousWarning", count3);
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
