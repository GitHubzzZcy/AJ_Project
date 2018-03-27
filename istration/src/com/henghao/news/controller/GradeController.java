                       package com.henghao.news.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.news.entity.GradeResultEntity;
import com.henghao.news.service.GradeService;
import com.henghao.news.service.RedisService;
import com.henghao.news.service.UserDataService;
import com.henghao.news.util.DateUtils;
import com.henghao.news.util.Result;
import com.henghao.news.util.SerializeUtil;

/**
 * 
 * @Description: 评分系统控制层
 * @author zcy  
 * @date 2017年12月1日 上午11:43:08
 * @version 1.0
 */

@Controller
@RequestMapping("/userGrade")
public class GradeController {

	
	@Resource
	private GradeService gradeService;
	@Resource
	private RedisService redisService;
	@Resource
	private UserDataService userDataService;
	/**
	 * 根据用户名查询一年的分数结果及扣分详情，并将每月每项扣分数写入缓存 
	 * @param userId  //因为页面传ID麻烦，现在改为传用户名   如userId=邓发权
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping(value = "/querySaveRedisGrade", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result querySaveRedisGrade(String userId, String start, String end) {
		Object obj = null;
		Map<String, Object> remap = new HashMap<String, Object>();
		int flag = 0;
		try {
			List<GradeResultEntity> de_list = new ArrayList<GradeResultEntity>();
			List<GradeResultEntity> neng_list = new ArrayList<GradeResultEntity>();
			List<GradeResultEntity> qin_list = new ArrayList<GradeResultEntity>();
			List<GradeResultEntity> ji_list = new ArrayList<GradeResultEntity>();
			List<GradeResultEntity> lian_list = new ArrayList<GradeResultEntity>();
			String yar = DateUtils.getCurrentDate("yyyy-");
			//因为页面传ID麻烦，现在改为传用户名
			userId = userDataService.getUserId(userId);
			try {
				for(int i=0; i<1000; i++) {
					byte[] bs = redisService.get(("Grede_entity-"+yar + userId + "_" + i).getBytes());
					if(i == 0) {
						if(bs != null) {
							//缓存中有至少一个对象
							flag = 1;
						}
					}
					if(bs == null) {
						break;
					}
					GradeResultEntity entity = (GradeResultEntity) SerializeUtil.unserialize(bs);
					de_list.add(entity);
				}
				remap.put("de_list", de_list);
				remap.put("neng_list", neng_list);
				remap.put("qin_list", qin_list);
				remap.put("ji_list", ji_list);
				remap.put("lian_list", lian_list);
				/**********************分数获取*****************/
				int month = Integer.parseInt(DateUtils.getCurrentDate("MM"));
				float de_grade = 0;
				float neng_grade = 0;
				float qin_grade = 0;
				float ji_grade = 0;
				float lian_grade = 0;
				//循坏获取各月份数据
				for(int i=1; i<month+1; i++) {
					String currentDate = DateUtils.getCurrentDate("yyyy-");
					String endDate = null;
					String startDate = null;
					if(i <= 8) {
						startDate = start.substring(0, 4)+"-0"+i+"-01 00:00:00";
						endDate = start.substring(0, 4)+"-0"+ (i+1) +"-01 00:00:00";
					}else{
						startDate = start.substring(0, 4)+"-"+i+"-01 00:00:00";
						endDate = start.substring(0, 4)+"-"+ (i+1) +"-01 00:00:00";
					}
					if(i == 9) {
						startDate = start.substring(0, 4)+"-09-01 00:00:00";
						endDate = start.substring(0, 4)+"-10-01 00:00:00";
					}if(i == 12) {
						startDate = start.substring(0, 4)+"-12-01 00:00:00";
						endDate = start.substring(0, 4)+"-12-31 23:59:59";
					}
					//从redis中获取各月份数据
					currentDate = currentDate + i;
					String de = redisService.get("Grede_de-"+ userId + currentDate);
					String neng = redisService.get("Grede_neng-"+ userId + currentDate);
					String qin = redisService.get("Grede_qin-"+ userId + currentDate);
					String ji = redisService.get("Grede_ji-"+ userId + currentDate);
					String lian = redisService.get("Grede_lian-"+ userId + currentDate);
					if(de != null && neng != null && qin != null && ji != null && lian != null) {
						de_grade += Float.parseFloat(de);
						neng_grade += Float.parseFloat(neng);
						qin_grade += Float.parseFloat(qin);
						ji_grade += Float.parseFloat(ji);
						lian_grade += Float.parseFloat(lian);
						//获取到数据，循环下一个月
						continue;
					}else{
						//Rdies中获取不到该月数据，就去查询该月数据，type传值1
						Map<String, Object> map = gradeService.querySaveRedisGrade(userId, startDate, endDate, 1);
						de_grade += Float.parseFloat(map.get("de_grade_deduct").toString());
						neng_grade += Float.parseFloat(map.get("neng_grade_deduct").toString());
						qin_grade += Float.parseFloat(map.get("qin_grade_deduct").toString());
						ji_grade += Float.parseFloat(map.get("ji_grade_deduct").toString());
						lian_grade += Float.parseFloat(map.get("lian_grade_deduct").toString());
					}
				}
				if(de_grade > 0) {
					de_grade = 0;
				}
				if(neng_grade > 0) {
					neng_grade = 0;
				}
				if(qin_grade > 0) {
					qin_grade = 0;
				}
				if(ji_grade > 0) {
					ji_grade = 0;
				}
				if(lian_grade > 0) {
					lian_grade = 0;
				}
				remap.put("de_grade", 15.0 + de_grade);
				remap.put("neng_grade", 15.0 + neng_grade);
				remap.put("qin_grade", 20.0 + qin_grade);
				remap.put("ji_grade", 35.0 + ji_grade);
				remap.put("lian_grade", 15.0 + lian_grade);
				/*********************************************************************/
				if(flag == 1) {
					return new Result(0, "缓存命中", remap);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (flag == 0) {
				//没有从缓存中获取到对象
				obj = gradeService.querySaveRedisGrade(userId, start, end, 0);
			}
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	/**
	 * 根据部门名字查询部门所有人的评分详情
	 * @param deptName 部门名字，不传默认查所有人
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping(value = "/queryUserGradeAll", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryUserGradeAll(@RequestParam(required=false) String deptName, String start, String end) {
		List<Object> list = new ArrayList<Object>();
		try {
			int month = Integer.parseInt(DateUtils.getCurrentDate("MM"));
			List<String> userIdAll = new ArrayList<String>();
			if("".equals(deptName) || deptName == null){
				userIdAll = userDataService.getUserIdAll();
			}else{
				userIdAll = userDataService.getDeptNameUserIdAll(deptName);
			}
			
			for (String userId : userIdAll) {
				float de_grade = 0;
				float neng_grade = 0;
				float qin_grade = 0;
				float ji_grade = 0;
				float lian_grade = 0;
				//循坏获取各月份数据
				for(int i=1; i<month+1; i++) {
					String currentDate = DateUtils.getCurrentDate("yyyy-");
					String endDate = null;
					String startDate = null;
					if(i <= 8) {
						startDate = start.substring(0, 4)+"-0"+i+"-01 00:00:00";
						endDate = start.substring(0, 4)+"-0"+ (i+1) +"-01 00:00:00";
					}else{
						startDate = start.substring(0, 4)+"-"+i+"-01 00:00:00";
						endDate = start.substring(0, 4)+"-"+ (i+1) +"-01 00:00:00";
					}
					if(i == 9) {
						startDate = start.substring(0, 4)+"-09-01 00:00:00";
						endDate = start.substring(0, 4)+"-10-01 00:00:00";
					}if(i == 12) {
						startDate = start.substring(0, 4)+"-12-01 00:00:00";
						endDate = start.substring(0, 4)+"-12-31 23:59:59";
					}
					//从redis中获取各月份数据
					currentDate = currentDate + i;
					String de = redisService.get("Grede_de-"+ userId + currentDate);
					String neng = redisService.get("Grede_neng-"+ userId + currentDate);
					String qin = redisService.get("Grede_qin-"+ userId + currentDate);
					String ji = redisService.get("Grede_ji-"+ userId + currentDate);
					String lian = redisService.get("Grede_lian-"+ userId + currentDate);
					if(de != null && neng != null && qin != null && ji != null && lian != null) {
						de_grade += Float.parseFloat(de);
						neng_grade += Float.parseFloat(neng);
						qin_grade += Float.parseFloat(qin);
						ji_grade += Float.parseFloat(ji);
						lian_grade += Float.parseFloat(lian);
						//获取到数据，循环下一个月
						continue;
					}else{
						//Rdies中获取不到该月数据，就去查询该月数据，type传值1
						Map<String, Object> map = gradeService.querySaveRedisGrade(userId, startDate, endDate, 1);
						de_grade += Float.parseFloat(map.get("de_grade_deduct").toString());
						neng_grade += Float.parseFloat(map.get("neng_grade_deduct").toString());
						qin_grade += Float.parseFloat(map.get("qin_grade_deduct").toString());
						ji_grade += Float.parseFloat(map.get("ji_grade_deduct").toString());
						lian_grade += Float.parseFloat(map.get("lian_grade_deduct").toString());
					}
				}
				Map<String, Object> rmap = new HashMap<String, Object>();
				List<Float> grade = new ArrayList<Float>();
				if(de_grade > 0) {
					de_grade = 0;
				}
				if(neng_grade > 0) {
					neng_grade = 0;
				}
				if(qin_grade > 0) {
					qin_grade = 0;
				}
				if(ji_grade > 0) {
					ji_grade = 0;
				}
				if(lian_grade > 0) {
					lian_grade = 0;
				}
				grade.add((float) (15.0 + de_grade));
				grade.add((float) (15.0 + neng_grade));
				grade.add((float) (20.0 + qin_grade));
				grade.add((float) (35.0 + ji_grade));
				grade.add((float) (15.0 + lian_grade));
				rmap.put("name", userDataService.getUserName(userId));
				rmap.put("score", grade);
				list.add(rmap);
			}
			return new Result(0, "查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	
	
	
	@RequestMapping(value = "/queryMeetingGrade", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryMeetingGrade(String userId, String start, String end) {
		try {
			return new Result(0, "查询成功", gradeService.queryMeetingGrade(userId, start, end));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	
	
	@RequestMapping(value = "/queryCheckData", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryCheckData(String userId, String start, String end) {
		String date = start.substring(0, 7);
		try {
			return new Result(0, "查询成功", gradeService.queryCheckData(userId, date));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	@RequestMapping(value = "/queryComplain", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryComplain(String userId, String start, String end) {
		try {
			return new Result(0, "查询成功", gradeService.queryComplain(userId, start, end));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	
}
