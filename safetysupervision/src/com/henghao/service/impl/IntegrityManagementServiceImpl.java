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

import com.henghao.dao.IntegrityManagementDao;
import com.henghao.dao.PersonNotCarDao;
import com.henghao.entity.CarBookEntity;
import com.henghao.entity.PersonNotCar;
import com.henghao.entity.Result;
import com.henghao.entity.ReturnCar;
import com.henghao.service.IUserDataService;
import com.henghao.service.IntegrityManagementService;
import com.henghao.util.DateUtils;
import com.henghao.util.GsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("IntegrityManagementService")
public class IntegrityManagementServiceImpl implements IntegrityManagementService {
	@Autowired
	private IntegrityManagementDao integrityManagementDao;
	@Autowired
	private PersonNotCarDao personNotCarDao ;
	@Resource
	private RedisService redisService;
	@Resource
	private IUserDataService userDataService;
	
	
	private Map<String, Object> remap = new HashMap<String, Object>();
	/**
	 * 得分统计详情
	 */
	@Override
	public Result scoreStatistics(String deptName, String name) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<Object> list = new ArrayList<Object>();
		if (name == "" || name == null) {
			list = integrityManagementDao.deptPersonle(deptName);
		} else {
			list = integrityManagementDao.deptAndPersonleName(deptName, name);
		}
		for (int i = 0; i < list.size(); i++) {
			String name1 = list.get(i).toString();
			List<?> list1 = integrityManagementDao.scoreStatistics(name1);
			// List<?> list2 =
			// integrityManagementDao.personnelInformation(personleaNme);
			if (list1.size() != 0) {
				jsonObject.put("name", name1);
				jsonObject.put("score", list1);
				jsonArray.add(jsonObject);
				// for (Object obj : list1) {
				// Object[] objs = (Object[]) obj;
				// jsonObject.put("name", name1);
				// jsonObject.put("classification", objs[1] == null ? "" :
				// String.valueOf(objs[1]));
				// jsonObject.put("score", objs[0] == null ? "" :
				// String.valueOf(objs[0]));
				// jsonArray.add(jsonObject);
				// }
			} else {
				String score = "0";
				jsonObject.put("name", name1);
				jsonObject.put("score", score);
				jsonArray.add(jsonObject);

			}
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
	 * 人员信息
	 */
	@Override
	public Result personnelInformation(String deptName, String name) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<Object> list = new ArrayList<Object>();
		if ((name == "" || name == null) && (deptName == "" || deptName == null)) {
			list = integrityManagementDao.personnelInfoAll(deptName, name);
		} else if (name == "" || name == null) {
			list = integrityManagementDao.personnelInformation(deptName);
		} else {
			list = integrityManagementDao.personnelInfo(deptName, name);
		}
		if (list.size() != 0) {
			for (Object obj : list) {
				Object[] objs = (Object[]) obj;
				jsonObject.put("name", objs[0] == null ? "" : String.valueOf(objs[0]));
				jsonObject.put("position", objs[1] == null ? "" : String.valueOf(objs[1]));
				jsonObject.put("phone", objs[2] == null ? "" : String.valueOf(objs[2]));
				if (objs[3] == null || objs[3] == "") {
					jsonObject.put("fileName", "");
				}else {
					String saveName=objs[3].toString();
					String exception = objs[4].toString();
					String fileName = saveName+"."+exception;
					jsonObject.put("fileName", fileName);
				}
				jsonObject.put("deptName", objs[5] == null ? "" : String.valueOf(objs[5]));
				jsonObject.put("orderNo", objs[6] == null ? "" : String.valueOf(objs[6]));
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
	 * 车辆座位类型
	 */
	@Override
	public Result carNumType() {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = integrityManagementDao.carNumType();
		if (list.size() != 0) {
			for (Object obj : list) {
				Object[] objs = (Object[]) obj;
				jsonObject.put("seatNumber", objs[0] == null ? "" : String.valueOf(objs[0]));
				jsonObject.put("carNum", objs[1] == null ? "" : String.valueOf(objs[1]));
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
	 * 按时间查询车辆使用次数
	 */
	@Override
	public Result useCarType(String date1, String date2) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = integrityManagementDao.useCarType(date1, date2);
		if (list.size() != 0) {
			for (Object obj : list) {
				Object[] objs = (Object[]) obj;
				jsonObject.put("type", objs[0] == null ? "" : String.valueOf(objs[0]));
				jsonObject.put("count", objs[1] == null ? "" : String.valueOf(objs[1]));
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
	 * 车辆基本信息
	 */
	@Override
	public Result carInfo(String carNo) {

		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = integrityManagementDao.carInfo(carNo);
		if (list.size() != 0) {
			for (Object obj : list) {
				Object[] objs = (Object[]) obj;
				jsonObject.put("carBrand", objs[0] == null ? "" : String.valueOf(objs[0]));
				jsonObject.put("carNo", objs[1] == null ? "" : String.valueOf(objs[1]));
				jsonObject.put("color", objs[2] == null ? "" : String.valueOf(objs[2]));
				jsonObject.put("seatNum", objs[3] == null ? "" : String.valueOf(objs[3]));
				jsonObject.put("carType", objs[4] == null ? "" : String.valueOf(objs[4]));
				jsonObject.put("displacement", objs[5] == null ? "" : String.valueOf(objs[5]));
				jsonObject.put("createTime", objs[6] == null ? "" : String.valueOf(objs[6]));
				String deptName = "";
				if(objs[7] != null) {
					String D_deptId = String.valueOf(objs[7]);
					deptName = userDataService.getUserDeptName(D_deptId.substring(2, D_deptId.length()));
				}
				jsonObject.put("dept", objs[7] == null ? "" : deptName);
				jsonObject.put("driver", objs[8] == null ? "" : String.valueOf(objs[8]));
				jsonObject.put("phone", objs[9] == null ? "" : String.valueOf(objs[9]));
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
	 * 车辆保养信息
	 */
	@Override
	public Result maintainInfo(String carNo) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = integrityManagementDao.maintainInfo(carNo);
		if (list.size() != 0) {
			for (Object obj : list) {
				Object[] objs = (Object[]) obj;
				jsonObject.put("type", objs[0] == null ? "" : String.valueOf(objs[0]));
				jsonObject.put("mileageNum", objs[1] == null ? "" : String.valueOf(objs[1]));
				jsonObject.put("startTime", objs[2] == null ? "" : String.valueOf(objs[2]));
				jsonObject.put("endTime", objs[3] == null ? "" : String.valueOf(objs[3]));
				jsonObject.put("explain", objs[4] == null ? "" : String.valueOf(objs[4]));
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
	 * 所有车辆信息
	 */
	@Override
	public Result carInfomation() {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = integrityManagementDao.carInfomation();
		if (list.size() != 0) {
			for (Object obj : list) {
				Object[] objs = (Object[]) obj;
				jsonObject.put("carBrand", objs[0] == null ? "" : String.valueOf(objs[0]));
				jsonObject.put("carNo", objs[1] == null ? "" : String.valueOf(objs[1]));
				String saveName=objs[2].toString();
				String exception=objs[3].toString();
				String fileName = saveName +"."+exception;
				jsonObject.put("fileName", fileName);
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
	 * 车辆状态查询
	 */
	@Override
	public Result carsState(String date1, String date2) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = integrityManagementDao.carInfo();
		if (list.size()!=0) {
		for (Object object: list) {
			Object[] objects=(Object[]) object;
			String carNo = objects[0].toString();
			String saveName = objects[1].toString();
			String exception = objects[2].toString();
			String fileName = saveName +"."+exception;
 			//String carNo = list.get(i).toString();
			List<?> list2 = integrityManagementDao.useCarApply(carNo, date1, date2);				
			if (list2.size() != 0) {
				jsonObject.put("fileName", fileName);
				jsonObject.put("carNo", carNo);
				jsonObject.put("type", "使用中");
				jsonArray.add(jsonObject);
				jsonObject.clear();
			} else {
				List<?> list3 = integrityManagementDao.maintainType(carNo, date1, date2);
				if (list3.size() != 0) {
					jsonObject.put("fileName", fileName);
					jsonObject.put("carNo", carNo);
					jsonObject.put("type", list3.get(0));
					jsonArray.add(jsonObject);
					jsonObject.clear();
				} else {
					List<?> list1 = integrityManagementDao.useCar(carNo, date1, date2);
					if (list1.size() != 0) {
						jsonObject.put("fileName", fileName);
						jsonObject.put("carNo", carNo);
						jsonObject.put("type", "预定中");
						jsonArray.add(jsonObject);
						jsonObject.clear();
					} else {
						jsonObject.put("fileName", fileName);
						jsonObject.put("carNo", carNo);
						jsonObject.put("type", "空闲中");
						jsonArray.add(jsonObject);
						jsonObject.clear();
					}
				}
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
	 * 用车信息查询
	 */
	@Override
	public Result carUseInfo(String carNo) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = integrityManagementDao.carUseInfo(carNo);
		if (list.size() != 0) {
			for (Object obj : list) {
				Object[] objs = (Object[]) obj;
				jsonObject.put("dept", objs[0] == null ? "" : String.valueOf(objs[0]));
				jsonObject.put("startTime", objs[1] == null ? "" : String.valueOf(objs[1]));
				jsonObject.put("endtime", objs[2] == null ? "" : String.valueOf(objs[2]));
				jsonObject.put("placeDeparture", objs[3] == null ? "" : String.valueOf(objs[3]));
				jsonObject.put("destination", objs[4] == null ? "" : String.valueOf(objs[4]));
				jsonObject.put("range", objs[5] == null ? "" : String.valueOf(objs[5]));
				jsonObject.put("category", objs[6] == null ? "" : String.valueOf(objs[6]));
				jsonObject.put("reason", objs[7] == null ? "" : String.valueOf(objs[7]));
				String userName = "";
				if(objs[8] != null) {
					String U_userId = String.valueOf(objs[8]);
					userName = userDataService.getUserName(U_userId.substring(2, U_userId.length()));
				}
				jsonObject.put("useCarName", objs[8] == null ? "" : userName);
				String approvalName = "";
				if(objs[9] != null) {
					String U_userId = String.valueOf(objs[9]);
					approvalName = userDataService.getUserName(U_userId.substring(2, U_userId.length()));
				}
				jsonObject.put("approvalName", objs[9] == null ? "" : approvalName);
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
	 * 按年月周时间段查询非工作日用车次数
	 */
	@Override
	public Result weekendUseCar(String date1, String date2) {
		int num = 0;
		try {
			String string = redisService.get("weekendUseCar" + date1 + date2);
			if(string != null) {
				return new Result(0, "缓存命中成功", Integer.parseInt(string));
			}
		} catch (Exception e1) {
			System.out.println("redis + weekendUseCar-->GET错误");
		}
		try {
			List<?> list = integrityManagementDao.findWeekend(date1, date2);
			if (list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					String date = list.get(i).toString();
					List<?> list2 = integrityManagementDao.findUseCarInfo(date);
					num = num + list2.size();
				}
			}
			//写入缓存
			try {
				//Redis缓存时间
				int TIME = DateUtils.redisTime();
				redisService.del("weekendUseCar" + date1 + date2);
				redisService.set("weekendUseCar" + date1 + date2, String.valueOf(num), TIME);
			} catch (Exception e) {
				System.out.println("redis + weekendUseCar-->SET错误");
			}
			return new Result(0, "查询成功", num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}

	/**
	 * 按年月周时间段查询节假日用车次数
	 */
	@Override
	public Result findVacationsUseCar(String date1, String date2) {
		int num = 0;
		try {
			String string = redisService.get("findVacationsUseCar" + date1 + date2);
			if(string != null) {
				return new Result(0, "缓存命中成功", Integer.parseInt(string));
			}
		} catch (Exception e1) {
			System.out.println("redis + findVacationsUseCar-->GET错误");
		}
		try {
			List<?> list = integrityManagementDao.findVacations(date1, date2);
			if (list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					String date = list.get(i).toString();
					List<?> list2 = integrityManagementDao.findUseCarInfo(date);
					num = num + list2.size();
				}
			}
			//写入缓存
			try {
				//Redis缓存时间
				int TIME = DateUtils.redisTime();
				redisService.del("findVacationsUseCar" + date1 + date2);
				redisService.set("findVacationsUseCar" + date1 + date2, String.valueOf(num), TIME);
			} catch (Exception e) {
				System.out.println("redis + findVacationsUseCar-->SET错误");
			}
			return new Result(0, "查询成功", num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}

	/**
	 * 按时间段 年月周查询驾驶员与车不符次数
	 */
	@Override
	public Result driverByCarNo(String date1, String date2) {
		int num = 0;
		try {
			String string = redisService.get("driverByCarNo" + date1 + date2);
			if(string != null) {
				return new Result(0, "缓存命中成功", Integer.parseInt(string));
			}
		} catch (Exception e1) {
			System.out.println("redis + driverByCarNo-->GET错误");
		}
		try {
			List<?> list = integrityManagementDao.driverByCarNo(date1, date2);
			if (list.size() != 0) {
				for (Object obj : list) {
					Object[] objs = (Object[]) obj;
					String name1 = objs[0].toString();
					String name2 = objs[1].toString();
					if (name1.equals(name2) != true) {
						num = num + 1;
					}
				}
			}
			//写入缓存
			try {
				//Redis缓存时间
				int TIME = DateUtils.redisTime();
				redisService.del("driverByCarNo" + date1 + date2);
				redisService.set("driverByCarNo" + date1 + date2, String.valueOf(num), TIME);
			} catch (Exception e) {
				System.out.println("redis + driverByCarNo-->SET错误");
			}
			return new Result(0, "查询成功", num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}

	/**
	 * 按时间段查询用车超时次数
	 */
	@Override
	public Result useCarByOvertime(String date1, String date2) {
		int num = 0;
		try {
			String string = redisService.get("useCarByOvertime" + date1 + date2);
			if(string != null) {
				return new Result(0, "缓存命中成功", Integer.parseInt(string));
			}
		} catch (Exception e1) {
			System.out.println("redis + useCarByOvertime-->GET错误");
		}
		try {
			List<?> list = integrityManagementDao.useCarByOvertime(date1, date2);
			num = list.size();
			//写入缓存
			try {
				//Redis缓存时间
				int TIME = DateUtils.redisTime();
				redisService.del("useCarByOvertime" + date1 + date2);
				redisService.set("useCarByOvertime" + date1 + date2, String.valueOf(num), TIME);
			} catch (Exception e) {
				System.out.println("redis + useCarByOvertime-->SET错误");
			}
			return new Result(0, "查询成功", num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}

	/**
	 * 保存车辆偏离信息
	 */
	
	/**
	 * 查询是否偏离目的地
	 */
	@Override
	public Result addressInconsistent(String date1, String date2) {
		try {
			String redis = redisService.get("addressInconsistent" + date1 + date2);
			if(redis != null) {
				String[] Ids = redis.split(",");
				remap.clear();
				remap.put("count", Ids.length);
				remap.put("list", Ids);
				return new Result(0, "缓存命中成功", remap);
			}
		} catch (Exception e1) {
			System.out.println("redis + addressInconsistent-->GET错误");
		}
		try {
			//获取申请用车列表
			List<CarBookEntity> list = integrityManagementDao.carUseTime(date1, date2);
			List<String> relist = new ArrayList<String>();
			for (CarBookEntity carBookEntity : list) {
				String carno = carBookEntity.getCARNO();
				String starttime = carBookEntity.getSTARTTIME();
				String endtime = carBookEntity.getENDTIME();
				String destinations = carBookEntity.getDESTINATIONS();
				if(starttime == null || endtime == null || "".equals(starttime) || "".equals(endtime)) {
					continue;
				}
				//时间是毫秒
				long startHm = DateUtils.parseDateFromString(starttime+":00").getTime();
				long endHm = DateUtils.parseDateFromString(endtime+":00").getTime() ;
				if(carno != null || !"".equals(carno)) {
					//获取车辆安装的GPS硬件信息
					String url = "http://218.244.129.243:89/gpsonline/GPSAPI?version=1&method=vLoginSystem&name=" + carno
							+ "&pwd=000000";
					String json = GsonUtil.loadJSON(url);
					JSONObject parseObject = JSONObject.fromObject(json);
					try {
						String vid = parseObject.get("vid").toString();
						String vKey = parseObject.get("vKey").toString();
						//查询时间段内车辆的经纬度数据
						url = "http://218.244.129.243:89/gpsonline/GPSAPI?version=1&method=loadHistory&vid=" + vid
								+ "&vKey=" + vKey + "&bTime=" + startHm + "&eTime=" + endHm + "&datafile=true";
						String json1 = GsonUtil.loadJSON(url);
						//测试用，小镇经纬度26.6269564504,106.6614119706
						//String json1 = "{\"history\":[{\"lat\":26.6269564504,\"lng\":106.6614119706},{\"lat\":26.6269564505,\"lng\":106.6614119707}]}";
						JSONObject jsonObject = JSONObject.fromObject(json1);
						//获取用车目的地的经纬度数据
						JSONObject jsonObject2 = BaiduUtil.getLngLat(destinations);
						String lat = jsonObject2.get("lat").toString();
						String lng = jsonObject2.get("lng").toString();
						double Lat_A = Double.parseDouble(lat);
						double Lng_A = Double.parseDouble(lng);
						//获取经纬度数据
						Object object = jsonObject.get("history");
						JSONArray jsonArray = JSONArray.fromObject(object);
						//比较目的地数据与车辆经纬度数据
						if (jsonArray.size() != 0) {
							double distance = 1;
							for (Object arr : jsonArray) {
								if (destinations != null || destinations != "") {
									try { //假如其中一条数据获取或者比较出现问题时进行下一条数据的比较
										//获取一条数据
										double Lat_B = (double) JSONObject.fromObject(arr).get("lat");
										double Lng_B = (double) JSONObject.fromObject(arr).get("lng");
										//比较
										distance = BaiduUtil.getDistance(Lat_A, Lng_A, Lat_B, Lng_B);
									} catch (Exception e) {
										continue;
									}
									if (distance < 1) {
										//出现一条数据值小于1 数据正常
										distance = 0;
										break;
									}
								}
							}
							//等于0，说明有其中一条数据是比较结果正常的，说明本次出车目的地不偏离
							if(distance != 0) {
								relist.add(carBookEntity.getID());
							}
						}
					} catch (Exception e) {
						//JSON数据解析或URL异常
						System.out.println("用车偏离JSON--->数据解析或URL异常");
						e.printStackTrace();
					}
				}
			}
			remap.clear();
			remap.put("count", relist.size());
			remap.put("list", relist);
			try {
				//将id以逗号隔开存入redis
				String value = null;
				StringBuffer sb = new StringBuffer();
				for (String string : relist) {
					sb.append(string+",");
				}
				if(sb.length() > 0) {
					value = sb.substring(0, sb.length()-1);
				}
				//Redis缓存时间
				int TIME = DateUtils.redisTime();
				redisService.del("addressInconsistent" + date1 + date2);
				redisService.set("addressInconsistent" + date1 + date2, value, TIME);
			} catch (Exception e) {
				System.out.println("redis + addressInconsistent-->SET错误");
			}
			return new Result(0, "查询成功", remap);
			//写入缓存
	} catch (Exception e) {
		e.printStackTrace();
	}
	return new Result(1, "查询失败", null);
	}

	/**
	 * 车回人不回查询
	 */
	@Override
	public Result carReturnAndPersonle(String date1, String date2) {
		int num = 0;
		try {
			String string = redisService.get("carReturn" + date1 + date2);
			if(string != null) {
				return new Result(0, "缓存命中成功", Integer.parseInt(string));
			}
		} catch (Exception e1) {
			System.out.println("redis + carReturn-->GET错误");
		}
		try {
			List<?> list = integrityManagementDao.carUseTime(date1, date2);
			String url = "";
			if (list.size() != 0) {
				for (Object object : list) {
					Object[] objects = (Object[]) object;
					String carNo = objects[0].toString();
					String carno = carNo.substring(1);
					if (carNo != null || carNo != "") {
						url = "http://218.244.129.243:89/gpsonline/GPSAPI?version=1&method=vLoginSystem&name=" + carno
								+ "&pwd=000000";
						String json = GsonUtil.loadJSON(url);
						JSONObject jsonObjs = JSONObject.fromObject(json);
						String vid = jsonObjs.get("vid").toString();
						String vKey = jsonObjs.get("vKey").toString();
						String time = objects[1].toString();
						String name = objects[3].toString();
						String time1 = time.substring(0, 10) + " 16:00";
						String time2 = time.substring(0, 10) + " 17:00";
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
						if (time != "" || time != null) {
							long startHm = sdf.parse(time1).getTime() ;
							long endHm = sdf.parse(time2).getTime() ;
							url = "http://218.244.129.243:89/gpsonline/GPSAPI?version=1&method=loadHistory&vid=" + vid
									+ "&vKey=" + vKey + "&bTime=" + startHm + "&eTime=" + endHm + "&datafile=true";
							String json1 = GsonUtil.loadJSON(url);
							JSONObject jsonObjects = JSONObject.fromObject(json1);
							Object obj = jsonObjects.get("history");
							JSONArray jsonAr = new JSONArray();
							jsonAr = JSONArray.fromObject(obj);
							if (jsonAr.size() != 0) {
								for (Object arr : jsonAr) {
									List<?> list2 = integrityManagementDao.personleLatLng(name, time1, time2);
									double Lat_A = 0d;
									double Lng_A = 0d;
									for (Object object2 : list2) {
										Object[] objects2 = (Object[]) object2;
										String lat = objects2[1].toString();
										String lng = objects2[0].toString();
										Lat_A = Double.parseDouble(lat);
										Lng_A = Double.parseDouble(lng);
										break;
									}
									double Lat_B = (double) JSONObject.fromObject(arr).get("lat");
									double Lng_B = (double) JSONObject.fromObject(arr).get("lng");
									double distance = BaiduUtil.getDistance(Lat_A, Lng_A, Lat_B, Lng_B);
									if (distance > 2) {
										num = num + 1;
									}
									break;
								}
							}
							
						}
					}
				}
			}
			//写入缓存
			try {
				//Redis缓存时间
				int TIME = DateUtils.redisTime();
				redisService.del("carReturn" + date1 + date2);
				redisService.set("carReturn" + date1 + date2, String.valueOf(num), TIME);
			} catch (Exception e) {
				System.out.println("redis + carReturn-->SET错误");
			}
			return new Result(0, "查询成功", num);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}

	/**
	 * 人车不符查询
	 */
	@Override
	public Result menAndCarNot(String date1, String date2) {
		int num = 0;
		int nums = 0;
		try {
			String string = redisService.get("menAndCarNot" + date1 + date2);
			if(string != null) {
				return new Result(0, "缓存命中成功", Integer.parseInt(string));
			}
		} catch (Exception e1) {
			System.out.println("redis + menAndCarNot-->GET错误");
		}
		try {
			List<?> list = integrityManagementDao.carUseTime(date1, date2);
			String url = "";
			if (list.size() != 0) {
				for (Object object : list) {
					Object[] objects = (Object[]) object;
					String carNo = objects[0].toString();
					String carno = carNo.substring(1);
					if (carNo != null || carNo != "") {
						url = "http://218.244.129.243:89/gpsonline/GPSAPI?version=1&method=vLoginSystem&name=" + carno
								+ "&pwd=000000";
						String json = GsonUtil.loadJSON(url);
						JSONObject jsonObjs = JSONObject.fromObject(json);
						String vid = jsonObjs.get("vid").toString();
						String vKey = jsonObjs.get("vKey").toString();
						String time1 = objects[2].toString();
						String time2 = objects[1].toString();
						String name = objects[3].toString();
						List<?> list1 = integrityManagementDao.personleLatLng(name, time1, time2);
						if (list1.size() != 0) {
							for (Object object2 : list1) {
								Object[] objects2 = (Object[]) object2;
								String lat = objects2[1].toString();
								String lng = objects2[0].toString();
								double Lat_A = Double.parseDouble(lat);
								double Lng_A = Double.parseDouble(lng);
								String time = objects2[2].toString();
								SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
								if (time != null || time != "") {
									long startHm = sdf.parse(time).getTime() * 1000;
									long endHm = (startHm + 10 ) *1000;
									url = "http://218.244.129.243:89/gpsonline/GPSAPI?version=1&method=loadHistory&vid="
											+ vid + "&vKey=" + vKey + "&bTime=" + startHm + "&eTime=" + endHm
											+ "&datafile=true";
									String json1 = GsonUtil.loadJSON(url);
									JSONObject jsonObjects = JSONObject.fromObject(json1);
									Object obj = jsonObjects.get("history");
									JSONArray jsonAr = JSONArray.fromObject(obj);
									if (jsonAr.size() != 0) {
										if (jsonAr.size() != 0) {
											for (Object arr : jsonAr) {
												double Lat_B = (double) JSONObject.fromObject(arr).get("lat");
												double Lng_B = (double) JSONObject.fromObject(arr).get("lng");
												double distance = BaiduUtil.getDistance(Lat_A, Lng_A, Lat_B, Lng_B);
												if (distance > 2) {
													num = num + 1;
												}
												break;
											}
										}
									}
								}
							}
						}
					}
					if (num > 3) {
						nums = nums + 1;
					}
				}
			}
			//写入缓存
			try {
				//Redis缓存时间
				int TIME = DateUtils.redisTime();
				redisService.del("menAndCarNot" + date1 + date2);
				redisService.set("menAndCarNot" + date1 + date2, String.valueOf(nums), TIME);
			} catch (Exception e) {
				System.out.println("redis + menAndCarNot-->SET错误");
			}
			return new Result(0, "查询成功", nums);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}

	/**
	 * 保存车回人不回信息
	 */
	@Override
	public Result addCarReturnAndPersonle() {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String t=sf.format(new Date());
		String date2 = t.substring(0,5);
		String date1 = t+"01-01";
		List<?> list = integrityManagementDao.carUseTime(date1, date2);
		int num = 0;
		String url = "";
		if (list.size() != 0) {
			for (Object object : list) {
				Object[] objects = (Object[]) object;
				String carNo = objects[0].toString();
				String carno = carNo.substring(1);
				if (carNo != null || carNo != "") {
					url = "http://218.244.129.243:89/gpsonline/GPSAPI?version=1&method=vLoginSystem&name=" + carno
							+ "&pwd=000000";
					String json = GsonUtil.loadJSON(url);
					// JSONObject jsonObjs = new JSONObject();
					JSONObject jsonObjs = JSONObject.fromObject(json);
					String vid = jsonObjs.get("vid").toString();
					String vKey = jsonObjs.get("vKey").toString();
					String time = objects[1].toString();
					String name = objects[3].toString();
					String time1 = time.substring(0, 10) + " 16:00";
					String time2 = time.substring(0, 10) + " 17:00";
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
					if (time != "" || time != null) {
						try {
							long startHm = sdf.parse(time1).getTime();
							long endHm = sdf.parse(time2).getTime();
							url = "http://218.244.129.243:89/gpsonline/GPSAPI?version=1&method=loadHistory&vid=" + vid
									+ "&vKey=" + vKey + "&bTime=" + startHm + "&eTime=" + endHm + "&datafile=true";
							String json1 = GsonUtil.loadJSON(url);
							JSONObject jsonObjects = JSONObject.fromObject(json1);
							Object obj = jsonObjects.get("history");
							JSONArray jsonAr = new JSONArray();
							jsonAr = JSONArray.fromObject(obj);
							if (jsonAr.size() != 0) {
								for (Object arr : jsonAr) {
									List<?> list2 = integrityManagementDao.personleLatLng(name, time1, time2);
									double Lat_A = 0d;
									double Lng_A = 0d;
									for (Object object2 : list2) {
										Object[] objects2 = (Object[]) object2;
										String lat = objects2[1].toString();
										String lng = objects2[0].toString();
										Lat_A = Double.parseDouble(lat);
										Lng_A = Double.parseDouble(lng);
										break;
									}
									double Lat_B = (double) JSONObject.fromObject(arr).get("lat");
									double Lng_B = (double) JSONObject.fromObject(arr).get("lng");
									double distance = BaiduUtil.getDistance(Lat_A, Lng_A, Lat_B, Lng_B);
									if (distance > 2) {
										String type="不回";
										ReturnCar returnCar = new ReturnCar();
										returnCar.setType(type);
										returnCar.setCarNo(carNo);
										returnCar.setName(name);
										returnCar.setTime(time);
										List<?> list3 = integrityManagementDao.findReturnCarInfo(name, time);
										if (list3.size() == 0) {
											integrityManagementDao.add(returnCar);	
										}
									}
									break;
								}
							}
						} catch (ParseException e) {
							System.out.println("时间转化错误");
							e.printStackTrace();
						}
					}
				}
			}
		}
		jsonObject.put("personnelNotReturn", num);
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
	 * 保存人车不符信息
	 */
	@Override
	public Result addMenAndCarNot() {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String t=sf.format(new Date());
		 String date2 = t.substring(0,5);
		 String date1 = t+"01-01";
		List<?> list = integrityManagementDao.carUseTime(date1, date2);
		int num = 0;
		int nums = 0;
		String url = "";
		String carNo="";
		String time2="";
		String name="";
		if (list.size() != 0) {
			for (Object object : list) {
				Object[] objects = (Object[]) object;
			    carNo = objects[0].toString();
				String carno = carNo.substring(1);
				if (carNo != null || carNo != "") {
					url = "http://218.244.129.243:89/gpsonline/GPSAPI?version=1&method=vLoginSystem&name=" + carno
							+ "&pwd=000000";
					String json = GsonUtil.loadJSON(url);
					// JSONObject jsonObjs = new JSONObject();
					JSONObject jsonObjs = JSONObject.fromObject(json);
					String vid = jsonObjs.get("vid").toString();
					String vKey = jsonObjs.get("vKey").toString();
					String time1 = objects[2].toString();
				    time2 = objects[1].toString();
					name = objects[3].toString();
					List<?> list1 = integrityManagementDao.personleLatLng(name, time1, time2);
					if (list1.size() != 0) {
						for (Object object2 : list1) {
							Object[] objects2 = (Object[]) object2;
							String lat = objects2[1].toString();
							String lng = objects2[0].toString();
							double Lat_A = Double.parseDouble(lat);
							double Lng_A = Double.parseDouble(lng);
							String time = objects2[2].toString();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
							if (time != null || time != "") {
								try {
									long startHm = sdf.parse(time).getTime();
									long endHm = startHm + 10;
									url = "http://218.244.129.243:89/gpsonline/GPSAPI?version=1&method=loadHistory&vid="
											+ vid + "&vKey=" + vKey + "&bTime=" + startHm + "&eTime=" + endHm
											+ "&datafile=true";
									String json1 = GsonUtil.loadJSON(url);
									JSONObject jsonObjects = JSONObject.fromObject(json1);
									Object obj = jsonObjects.get("history");
									JSONArray jsonAr = JSONArray.fromObject(obj);
									if (jsonAr.size() != 0) {
										if (jsonAr.size() != 0) {
											for (Object arr : jsonAr) {
												double Lat_B = (double) JSONObject.fromObject(arr).get("lat");
												double Lng_B = (double) JSONObject.fromObject(arr).get("lng");
												double distance = BaiduUtil.getDistance(Lat_A, Lng_A, Lat_B, Lng_B);
												if (distance > 2) {
													num = num + 1;
												}
												break;
											}
										}

									}
								} catch (ParseException e) {
									System.out.println("时间转换错误");
									e.printStackTrace();
								}
							}
						}
					}
				}
				if (num > 3) {
					String type = "人车不符";
					PersonNotCar person = new PersonNotCar();
					person.setTime(time2);
					person.setCarNo(carNo);
					person.setName(name);
					person.setType(type);
					List<?> list2 = personNotCarDao.personNotCarInfo(name, time2);
					if (list2.size()==0) {
						personNotCarDao.add(person);
					}
				}
			}
		}
		jsonObject.put("menAndCarNot", nums);
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
	//车辆管理菜单下，用车次数详情查看
	@Override
	public Map<String, Object> queryUseCarDetails(String start, String end, int page, int size) {
		Map<String, Object> map = integrityManagementDao.queryUseCarDetails(start, end, page,  size);
		return map;
	}
}
