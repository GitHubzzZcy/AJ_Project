package com.henghao.service.impl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.henghao.utils.BaiduUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.henghao.dao.AttendanceDetailsDao;
import com.henghao.entity.Result;
import com.henghao.service.AttendanceDetailsService;
import com.henghao.util.DateUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("AttendanceDetailsService")
public class AttendanceDetailsServiceImpl implements AttendanceDetailsService {

	@Autowired
	private AttendanceDetailsDao attendanceDetailsDao;
	@Resource
	private RedisService redisService;
	private Map<String, Object> remap = new HashMap<String, Object>();
	//无故外出统计部门
	private static final String[] groupNames = {"局干部","执法支队干部职工","应急救援指挥中心","工勤人员"};
	/**
	 * 时间段查询迟到情况
	 */
	@Override
	public Result leave(String date1, String date2) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = attendanceDetailsDao.late(date1, date2);
		if (list.size() != 0) {
			for (Object object : list) {
				Object[] objects = (Object[]) object;
				jsonObject.put("groupName", objects[0] == null ? "" : String.valueOf(objects[0]));
				jsonObject.put("count", objects[1] == null ? "" : String.valueOf(objects[1]));
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
	 * 按时间段查询早退情况
	 */
	@Override
	public Result early(String date1, String date2) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = attendanceDetailsDao.early(date1, date2);
		if (list.size() != 0) {
			for (Object object : list) {
				Object[] objects = (Object[]) object;
				jsonObject.put("groupName", objects[0] == null ? "" : String.valueOf(objects[0]));
				jsonObject.put("count", objects[1] == null ? "" : String.valueOf(objects[1]));
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
	 * 按月份和年查询迟到记录
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
				jsonObject.put("groupName", objects[0] == null ? "" : String.valueOf(objects[0]));
				jsonObject.put("count", objects[1] == null ? "" : String.valueOf(objects[1]));
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
	 * 按月份和年查询早退记录
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
				jsonObject.put("groupName", objects[0] == null ? "" : String.valueOf(objects[0]));
				jsonObject.put("count", objects[1] == null ? "" : String.valueOf(objects[1]));
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
	 * 无故外出数据
	 */
	@Override
	public Result withoutReason(String date1, String date2) {
		List<Integer> relist = new ArrayList<Integer>();
		try {
			String string1 = redisService.get("withoutReason" + date1 + date2 + "List0");
			String string2 = redisService.get("withoutReason" + date1 + date2 + "List1");
			String string3 = redisService.get("withoutReason" + date1 + date2 + "List2");
			String string4 = redisService.get("withoutReason" + date1 + date2 + "List3");
			if(string1 != null) {
				relist.add(Integer.parseInt(string1));
				relist.add(Integer.parseInt(string2));
				relist.add(Integer.parseInt(string3));
				relist.add(Integer.parseInt(string4));
				return new Result(0, "缓存命中成功", relist);
			}
		} catch (Exception e) {
			System.out.println("redis + withoutReason->GET错误");
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			double distance = 0d;
			for (String groupName : groupNames) {
				int count = 0;
				Date dBegin = sdf.parse(date1);
				Date dEnd = sdf.parse(date2);
				//获取一个日期段中的所有日期
				List<Date> lDate = DateUtils.findDates(dBegin, dEnd);
				for (Date dates : lDate) {
					String date = sdf.format(dates);
					//获取部门下所有人员name
					List<?> list1 = attendanceDetailsDao.personleInfo(groupName);
					for (int j = 0; j < list1.size(); j++) {
						String name = list1.get(j).toString();
						//查询请假情况
						List<?> list2 = attendanceDetailsDao.leave(date, name);
						//查询出差情况
						List<?> list3 = attendanceDetailsDao.travel(date, name);
						//查询节假日期
						List<?> list4 = attendanceDetailsDao.vacations(date);
						//查询任务安排
						List<?> list5 = attendanceDetailsDao.distribution(date, name);
						//以上都没有则获取经纬度判断是否为无故外出
						if (list2.size() == 0 && list3.size() == 0 && list4.size() == 0 && list5.size() == 0) {
							String date3 = date + " " + "09:00:00";
							String date4 = date + " " + "12:00:00";
							//获取一天的经纬度
							List<?> list6 = attendanceDetailsDao.latAndLon(date, name, date3, date4);
							//安监局经纬度
							double Lat_A = 106.638782;
							double Lng_A = 26.651684;
							for (Object object : list6) {
								Object[] objects = (Object[]) object;
								if("5e-324".equals(objects[0])) {
									continue;
								}
								double Lat_B = (double) objects[1];
								double Lng_B = (double) objects[0];
								distance = BaiduUtil.getDistance(Lat_A, Lng_A, Lat_B, Lng_B);
								if (distance > 2) {
									count ++;
								}
							}
						}
						if (distance > 2) {
							break;
						}
					}
					
				}
				relist.add(count);
			}
			
			
			try {
				int TIME = DateUtils.redisTime();
				redisService.del("withoutReason" + date1 + date2 + "List0");
				redisService.del("withoutReason" + date1 + date2 + "List1");
				redisService.del("withoutReason" + date1 + date2 + "List2");
				redisService.del("withoutReason" + date1 + date2 + "List3");
				
				redisService.set("withoutReason" + date1 + date2 + "List0", String.valueOf(relist.get(0)), TIME);
				redisService.set("withoutReason" + date1 + date2 + "List1", String.valueOf(relist.get(1)), TIME);
				redisService.set("withoutReason" + date1 + date2 + "List2", String.valueOf(relist.get(2)), TIME);
				redisService.set("withoutReason" + date1 + date2 + "List3", String.valueOf(relist.get(3)), TIME);
			} catch (Exception e) {
				System.out.println("redis->SET错误");
			}
			return new Result(0, "查询成功", relist);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Result(0, "查询失败", null);
	}

	/**
	 * 按时间段 年月周查询迟到早退 人数 #  加入缓存
	 */
	@Override
	public Result findLateAndearlyNum(String date1, String date2) {
		String late = null;
		String early = null;
		try {
			late = redisService.get("findLateAndearlyNum"+ "late" + date1 + date2);
			early = redisService.get("findLateAndearlyNum"+ "early" + date1 + date2);
			if(late != null && early != null) {
				remap.put("late", late);
				remap.put("early", early);
				return new Result(0, "缓存命中成功", remap);
			}
		} catch (Exception e) {
			System.out.println("redis->GET错误");
		}
		
		try {
			List<?> list = attendanceDetailsDao.findPersonleLateNum(date1, date2);
			List<?> list2 = attendanceDetailsDao.findPersonleEarly(date1, date2);
			Object object1 = list.get(0);
			if(object1 != null) {
				late = object1.toString();
			}
			Object object2 = list2.get(0);
			if(object2 != null) {
				early = object2.toString();
			}
			remap.put("late", late);
			remap.put("early", early);
			
			try {
				int TIME = DateUtils.redisTime();
				redisService.del("findLateAndearlyNum"+ "late" + date1 + date2);
				redisService.del("findLateAndearlyNum"+ "early" + date1 + date2);
				redisService.set("findLateAndearlyNum"+ "late" + date1 + date2, late, TIME);
				redisService.set("findLateAndearlyNum"+ "early" + date1 + date2, early, TIME);
			} catch (Exception e) {
				System.out.println("redis->SET错误");
			}
			return new Result(0, "查询成功", remap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Result(1, "查询失败", null);
	}

	/**
	 * 按年月周 时间段查询无故外出人数
	 */
	@Override
	public Result findwithoutReasonPersonleNum(String date1, String date2) {
		int a = 0;
		try {
			String string = redisService.get("findwithoutReasonPersonleNum" + date1 + date2);
			if(string != null) {
				return new Result(0, "缓存命中成功", Integer.parseInt(string));
			}
		} catch (Exception e) {
			System.out.println("redis + findwithoutReasonPersonleNum->GET错误");
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<?> list = attendanceDetailsDao.groupName();
			for (int i = 0; i < list.size(); i++) {
				double distance=0d;
				String groupName = list.get(i).toString();
				List<?> list1 = attendanceDetailsDao.personleInfo(groupName);
				if (list1.size() != 0) {
				for (int j = 0; j < list1.size(); j++) {
					String name = list1.get(j).toString();
					Date dBegin = sdf.parse(date1);
					Date dEnd = sdf.parse(date2);
					List<Date> lDate = DateUtils.findDates(dBegin, dEnd);
					for (Date dates : lDate) {
						String date = sdf.format(dates);
							List<?> list2 = attendanceDetailsDao.leave(date, name);
							List<?> list3 = attendanceDetailsDao.travel(date, name);
							List<?> list4 = attendanceDetailsDao.vacations(date);
							List<?> list5 = attendanceDetailsDao.distribution(date, name);
							if (list2.size() == 0 && list3.size() == 0 && list4.size() == 0 && list5.size() == 0) {
								String date3 = date + " " + "09:00:00";
								String date4 = date + " " + "12:00:00";
								List<?> list6 = attendanceDetailsDao.latAndLon(date, name, date3, date4);
								String latA = "106.638782";
								double Lat_A = Double.parseDouble(latA);
								String lngA = "26.651684";
								double Lng_A = Double.parseDouble(lngA);
								if (list6.size() != 0) {
									for (Object object : list6) {
										Object[] objects = (Object[]) object;
										if("5e-324".equals(objects[0])) {
											continue;
										}
										double Lat_B = (double) objects[1];
										double Lng_B = (double) objects[0];
									    distance = BaiduUtil.getDistance(Lat_A, Lng_A, Lat_B, Lng_B);
										if (distance > 2) {
											a = a + 1;
											break;
										}
									}
								}
							}
							if (distance > 2) {
								break;
							} 
						}
					}
				}
			}
			try {
				int TIME = DateUtils.redisTime();
				redisService.del("findwithoutReasonPersonleNum" + date1 + date2);
				redisService.set("findwithoutReasonPersonleNum" + date1 + date2, String.valueOf(a), TIME);
			} catch (Exception e) {
				System.out.println("redis + findwithoutReasonPersonleNum->SET错误");
			}
			return new Result(0, "查询成功", a);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}

}
