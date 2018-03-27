package com.henghao.news.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.henghao.news.dao.EfficiencyCarDataDao;
import com.henghao.news.entity.CarBookEntity;
import com.henghao.news.entity.GradeResultEntity;
import com.henghao.news.util.BaiduUtil;
import com.henghao.news.util.Urljson;


@Service
public class EfficiencyCarDataService {
	@Resource
	private EfficiencyCarDataDao efficiencyCarDataDao;
	@Resource
	private UserDataService userDataService;
	@Value("${BAIDU_AK}")
	private String BAIDU_AK;
	@Value("${BAIDU_URL}")
	private String BAIDU_URL;
	@Value("${GRADE_TO_CARDATA}")
	private String GRADE_TO_CARDATA;
	
	@Value("${GRADE_CL_RENCHE}")
	private float GRADE_CL_RENCHE; //人车不符扣分
	@Value("${GRADE_CL_PIANLI_NUM}") //偏离扣分
	private int GRADE_CL_PIANLI_NUM;
	@Value("${GRADE_CL_PIANLI}") 
	private float GRADE_CL_PIANLI;
	@Value("${GRADE_CL_CHAOSHI_NUM}") //用车超时
	private int GRADE_CL_CHAOSHI_NUM;
	@Value("${GRADE_CL_CHAOSHI}")
	private float GRADE_CL_CHAOSHI;
	
	
	private Map<String, Object> remap = new HashMap<String, Object>();
	//用车异常次数
	public Object queryCarBookException(String start, String end) {
		String carDeparture = null;
		try {
			 carDeparture = this.queryCarDeparture(null, start, end).get("count").toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String carManage = null;
		try {
			carManage = this.queryCarManage(null, start, end).get("count").toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String carOvertime = this.queryCarOvertime(null, start, end).get("count").toString();
		remap.put("carDeparture", carDeparture);
		remap.put("carManage", carManage);
		remap.put("carOvertime", carOvertime);
		return remap;
	}
	//评分扣分
	public float queryCarBookException(String userId, String start, String end) {
		return 0;
	}
	//#人车不符 //userId为空时查询全部人员
	public Map<String, Object> queryCarManage(String userId, String start, String end) {
		try {
			List<GradeResultEntity> relist = new ArrayList<GradeResultEntity>();
			int rcbf = 0;
			//查询时间段内用车信息#通过申请的的用车
			String Applnglat = userId;
			if(userId != null || !"".equals(userId)) {
				userId = "U_"+userId;
			}
			List<CarBookEntity> queryCarUseTime = efficiencyCarDataDao.queryCarUseTime(userId, start, end);
			for (CarBookEntity carBookEntity : queryCarUseTime) {
				double distance = 0;
				if(carBookEntity.getCARNO() != null) {
					String carno = carBookEntity.getCARNO().substring(1);
					//获取车辆基本信息
					String url = GRADE_TO_CARDATA + "&method=vLoginSystem&name="+carno+"&pwd=000000";
					JSONObject json = Urljson.readFastJsonFromUrl(url);
					Object object = json.get("vid");
					Object object2 = json.get("vKey");
					//获取到车辆基本信息后
					if(object != null && object2 != null) {
						String vid = object.toString();
						String vKey = object2.toString();
						//用车时间段内人员定位数据，人员定位数据是每分钟上传一次
						List<?> queryPersonleLatLng = efficiencyCarDataDao.queryPersonleLatLng(Applnglat, carBookEntity.getSTARTTIME(), carBookEntity.getENDTIME());
						for (Object PersonleLatLng : queryPersonleLatLng) {
							Object[] leLatLng =  (Object[]) PersonleLatLng;
							double lat_U;
							double lng_U;
							try {
								lat_U = Double.parseDouble(leLatLng[0].toString());
								lng_U = Double.parseDouble(leLatLng[1].toString());
							} catch (Exception e) {
								continue;
							}
							String ctime = leLatLng[2].toString(); //yyyy-MM-dd HH:mm:ss
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							//人员定位数据两分钟内 
							long startTime = (sdf.parse(ctime).getTime() - 120000 );//毫秒值
							long endTime = (startTime + 120000);
							//查询两分钟内车辆定位数据
							url = GRADE_TO_CARDATA + "&method=loadHistory&vid="
									+ vid + "&vKey=" + vKey + "&bTime=" + startTime + "&eTime=" + endTime
									+ "&datafile=true";
							JSONObject carjson = Urljson.readFastJsonFromUrl(url);
							Object object3 = carjson.get("history");
							if(object3 != null) {
								String jsonString = JSON.toJSONString(object3);
								JSONArray jsonAr = JSON.parseArray(jsonString);
								//JSONArray jsonAr = JSONArray.fromObject(object3);
								if(jsonAr.size() > 0) {
									for (Object arr : jsonAr) {
										JSONObject jo = (JSONObject) arr; 
										double Lat_B = Double.parseDouble(jo.get("lat").toString());
										double Lng_B = Double.parseDouble(jo.get("lng").toString());
										//比较当前人员定位数据与两分钟内车辆定位数据
										distance = BaiduUtil.getDistance(lat_U, lng_U, Lat_B, Lng_B);
										if (distance <= 2) {
											//假如两分钟内出现一次小于4千米，则不属于人车不符，再比较下一组人员定位信息
											break;
										}
									}
								}
							}
						}
					}
				}
				//该次用车出现人车不符情况
				if(distance >= 2) {
					relist.add(new GradeResultEntity("减", "德", "人车不符",String.valueOf(GRADE_CL_RENCHE), carBookEntity.getSTARTTIME()));
					rcbf ++ ;
				}
			}
			remap.put("relist_queryCarManage", relist);
			remap.put("count_queryCarManage", rcbf);
			remap.put("grade_queryCarManage", -rcbf * GRADE_CL_RENCHE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return remap;
	}
	//路线偏离，其实这里是目的地偏离
	public Map<String, Object> queryCarDeparture(String userId, String start, String end) {
		try {
			List<GradeResultEntity> relist = new ArrayList<GradeResultEntity>();
			List<String> dates = new ArrayList<String>();
			int count = 0;
			//查询时间段内用车信息#通过申请的的用车
			if(userId != null || !"".equals(userId)) {
				userId = "U_"+userId;
			}
			List<CarBookEntity> queryCarUseTime = efficiencyCarDataDao.queryCarUseTime(userId, start, end);
			int carCount = queryCarUseTime.size();
			for (CarBookEntity carBookEntity : queryCarUseTime) {
				try {
					double distance = 0;
					String carno = carBookEntity.getCARNO();
					carno = carno.substring(1, carno.length());
					//success=true时查询成功，登录车辆获取vid和vKey
					String carurl = GRADE_TO_CARDATA + "&method=vLoginSystem&name="+carno+"&pwd=000000";
					JSONObject carjson = null;
					
					carjson = Urljson.readFastJsonFromUrl(carurl);
					String success = carjson.get("success").toString();
					if("false".equals(success)) {
						continue;
					}
					
					String vid = carjson.get("vid").toString();
					String vKey = carjson.get("vKey").toString();
					//调用百度地图接口查询该地址的经纬度
					String destinations = carBookEntity.getDESTINATIONS();//目的地
					String url = BAIDU_URL+"?address=" + destinations + "&output=json&ak="+BAIDU_AK;
					double lng = 0;
					double lat = 0;
					
					JSONObject json = Urljson.readFastJsonFromUrl(url);
					int parseInt = Integer.parseInt(json.get("status").toString());
					if(0 != parseInt) {
						continue;
					}
					JSONObject result = (JSONObject) json.get("result");
					JSONObject location = (JSONObject) result.get("location");
					lng = Double.parseDouble(location.get("lng").toString());
					lat = Double.parseDouble(location.get("lat").toString());

					//查询用车时间段内的车辆经纬度数据
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					long startTime = sdf.parse(carBookEntity.getSTARTTIME()).getTime();
					long endTime = sdf.parse(carBookEntity.getENDTIME()).getTime();
					url = GRADE_TO_CARDATA + "&method=loadHistory&vid="
							+ vid + "&vKey=" + vKey + "&bTime=" + startTime + "&eTime=" + endTime
							+ "&datafile=true";
					JSONObject jsonhistory = Urljson.readFastJsonFromUrl(url);
					Object history = jsonhistory.get("history");
					String jsonString = JSON.toJSONString(history);
					JSONArray jsonArray = JSON.parseArray(jsonString);
					//JSONArray jsonArray = JSONArray.fromObject(history);
					for (Object array : jsonArray) {
						JSONObject jo = (JSONObject) array; 
						double Lat_B = Double.parseDouble(jo.get("lat").toString());
						double Lng_B = Double.parseDouble(jo.get("lng").toString());
						//比较目的地经纬度与行车经纬度比较
						distance = BaiduUtil.getDistance(lat, lng, Lat_B, Lng_B);
						if (distance <= 2) {
							//假如出现一次小于2千米，则不属于终点偏离
							break;
						}
					}
					if(distance > 2) {
						//偏离
						count ++;
						dates.add(carBookEntity.getSTARTTIME());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//扣分
			float grade = 0;
			if(count > GRADE_CL_PIANLI_NUM) {
				grade = -(count - GRADE_CL_PIANLI_NUM) * GRADE_CL_PIANLI;
				for(int i=0; i< dates.size()-GRADE_CL_PIANLI_NUM; i++) {
					relist.add(new GradeResultEntity("减", "德", "用车路线偏离",String.valueOf(GRADE_CL_RENCHE), dates.get(i)));
				}
				
			}
			remap.put("relist_queryCarDeparture", relist);
			remap.put("count_queryCarDeparture", carCount);
			remap.put("grade_queryCarDeparture", grade);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return remap;
	}
	//用车超时，从工作流表获取数据
	public Map<String, Object> queryCarOvertime(String userId, String start, String end) {
		List<GradeResultEntity> relist = new ArrayList<GradeResultEntity>();
		if(userId != null || !"".equals(userId)) {
			userId = "U_"+userId;
		}
		List<String> queryCarOvertime = efficiencyCarDataDao.queryCarOvertime(userId, start, start);
		int count = queryCarOvertime.size();
		float grade = 0;
		if(count > GRADE_CL_CHAOSHI_NUM) {
			grade = -(count - GRADE_CL_CHAOSHI_NUM) * GRADE_CL_CHAOSHI;
			for(int i=0; i<count - GRADE_CL_CHAOSHI_NUM; i++) {
				relist.add(new GradeResultEntity("减分", "德", "用车超时", String.valueOf(GRADE_CL_CHAOSHI), queryCarOvertime.get(i)));
			}
		}
		remap.put("relist_queryCarOvertime", relist);
		remap.put("count_queryCarOvertime", count);
		remap.put("grade_queryCarOvertime", grade);
		return remap;
	}
}
