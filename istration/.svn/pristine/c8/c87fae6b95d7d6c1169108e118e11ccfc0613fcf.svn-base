package com.henghao.istration.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.henghao.istration.dao.ICarRealtimeDao;
import com.henghao.istration.entity.CarRealtime;
import com.henghao.istration.entity.Carapply;
import com.henghao.istration.entity.Exceed;
import com.henghao.istration.service.ICarRealtimeService;
import com.henghao.istration.service.ICarapplyService;
import com.henghao.istration.util.DateUtils;
import com.henghao.istration.util.PageBean;
import com.henghao.istration.util.Result;
import com.henghao.istration.util.Urljson;

@Service("carRealtimeService")
public class CarRealtimeServiceImpl implements ICarRealtimeService {

	@Resource
	private ICarRealtimeDao carRealtimeDao;
	@Resource
	private ICarapplyService carapplyService;
	Map<String, Object> map = new HashMap<String, Object>();
	//一经纬度约等于111000米
	private static final double km = 111000;
	//百度密钥
	private static final String ak = "Fun6r9EE1RPtZ8roRyy7tpM3dL0OQEEl";
	
	@Override
	public Result add(String carNumber, String longitude, String latitude, String carTime) {
		Result result = null;
		Date date = new Date();
		try {
			String dates = DateUtils.getCurrentDate("yyyy-MM-dd");
			
			CarRealtime carRealtime = new CarRealtime();
			carRealtime.setCarNumber(carNumber);
			carRealtime.setLongitude(longitude);
			carRealtime.setLatitude(latitude);
			Date date2 = DateUtils.parseDateFromString(carTime);
			carRealtime.setCarTime(date2);
			carRealtime.setYTD(dates);
			carRealtime.setMyTime(date);
			carRealtimeDao.add(carRealtime);
			result = new Result(0,"实时数据更新成功",null);
		} catch (Exception e) {
			result = new Result(1,"实时数据更新失败",null);
			e.printStackTrace();
		}
		return result;
	}
	
	
	@Override
	public Result queryRealtime(String carNumber) {
		Result result = null;
		try {
			String dates = DateUtils.getCurrentDate("yyyy-MM-dd");
			List<CarRealtime> list = carRealtimeDao.findByCarnum(carNumber,dates);
			if(list.size()==0) {
				return  result = new Result(0,"查询成功，结果为空",null);
			}
			CarRealtime carRealtime = list.get(0);
			result = new Result(0,"查询车辆实时数据成功",carRealtime);
		} catch (Exception e) {
			result = new Result(1,"查询车辆实时数据失败",null);
			e.printStackTrace();
		}
		return result;
	}


	@Override
	public Result queryCarlist() {
		List<Object> list = new ArrayList<Object>();
		Result result = null;
		try {
			//查询出所有车辆
			List<String> carlist = carRealtimeDao.queryCarlist();
			for (String string : carlist) {
				//根据车牌号查询实时信息
				Result findApply = carapplyService.findApply(string);
				Object data = findApply.getData();
				list.add(data);
			}
			for(int i=list.size()-1;i>-1; i--){
				if(null==list.get(i)) {
					list.remove(i);
				}
			}
			result = new Result(0,"查询车辆列表成功",list);
		} catch (Exception e) {
			result = new Result(1,"查询车辆列表失败",null);
			e.printStackTrace();
		}
		return result;
	}


	@Override
	public Result mileage() {
		
		return null;
	}


	/**
	 * 超时统计
	 */
	@Override
	public Result exceed(int currentPage, int pageSize) {
		Result result = null;
		try {
			List<Exceed> list = carRealtimeDao.exceed();
			int size = list.size();
			int max = currentPage*pageSize;
			int min = (currentPage-1)*pageSize;
			if(max > size) {
				max = size;
			}
			if(min < 0) {
				min = 0;
			}
			List<Exceed> subList = list.subList(min, max);
			PageBean pb = new PageBean();
			pb.setCurrentPage(currentPage);
			pb.setPageSize(pageSize);
			pb.setTotal(size);
			pb.setList(subList);
			result = new Result(0,"查询成功",pb);
		} catch (Exception e) {
			result = new Result(1,"查询失败",null);
			e.printStackTrace();
		}
		return result;
	}


	//偏航统计
	@Override
	public Result sheer(int currentPage, int pageSize) {
		Result result = null;
		try {
			List<String> wrokidlist = carRealtimeDao.carlog();
			//查询日志End2;
			List<Object> relist = new ArrayList<Object>();
			for (String wrokid : wrokidlist) {
				try {
					Carapply apply = carRealtimeDao.carbook(wrokid);
					if(null != apply) {
						String starttime = apply.getSTARTTIME();
						//通过目的地获取经纬度
						String destinations = apply.getDESTINATIONS();
						String url = "http://api.map.baidu.com/geocoder/v2/?callback=renderOption&output=json&address="+destinations+"&city=贵阳市&ak="+ak;
						JSONObject jsonObject = Urljson.JsonUrl(url);
						JSONObject obj;
						obj = (JSONObject) jsonObject.get("result");
						JSONObject object = (JSONObject) obj.get("location");
						String lng = object.get("lng").toString();
						String lat = object.get("lat").toString();
						double lngd = Double.parseDouble(lng);
						double latd = Double.parseDouble(lat);
						String substring = starttime.substring(0, 9);
						String carno = apply.getCARNO();
						//再查sa_test_car
						List<CarRealtime> listre = carRealtimeDao.lnglat(substring, carno);
						int index1 = 0;
						int index2 = 0;
						int index3 = 0;
						for (CarRealtime carRealtime : listre) {
							String longitude = carRealtime.getLongitude();
							String latitude = carRealtime.getLatitude();
							double longituded = Double.parseDouble(longitude);
							double latituded = Double.parseDouble(latitude);
							double log = Math.abs(lngd - longituded);
							double la = Math.abs(latd - latituded);
							double ss = log*log + la*la;
							double s = Math.sqrt(ss);
							double ms = s*km;
							//轻微
							if(ms <= 500 & ms >300) {
								index1 = 1;
							}
							if(ms < 1000 & ms >500) {
								index2 = 2;
							}
							if(ms > 1000) {
								index3 = 3;
							} 
						}
						if(index3 == 3) {
							apply.setSheer("严重");
						}else{
							if(index2 == 2) {
								apply.setSheer("一般");
							}else{
								if(index1 == 1) {
									apply.setSheer("轻微");
								}
							}
						}
						relist.add(apply);
					}
				} catch (JSONException e) {
					//如果地址百度接口查询不到怎进行下一个
				}
			}
			int size = relist.size();
			int max = currentPage*pageSize;
			int min = (currentPage-1)*pageSize;
			if(max > size) {
				max = size;
			}
			if(min < 0) {
				min = 0;
			}
			List<Object> subList = relist.subList(min, max);
			PageBean pb = new PageBean();
			pb.setCurrentPage(currentPage);
			pb.setPageSize(pageSize);
			pb.setTotal(size);
			pb.setList(subList);
			result = new Result(0,"查询成功", pb);
		} catch (Exception e) {
			result = new Result(1,"查询失败", null);
			e.printStackTrace();
		} 
		return result;
	}


	//停车统计
	@Override
	public Result park(int currentPage, int pageSize) {
		Result result = null;
		try {
			String currentDate = DateUtils.getCurrentDate("yyyy-MM-dd");
			List<CarRealtime> list = carRealtimeDao.park();
			List<CarRealtime> relist = new ArrayList<CarRealtime>();
			for (CarRealtime carRealtime : list) {
				try {
					if(currentDate != carRealtime.getYTD()) {
						String longitude = carRealtime.getLongitude();
						String latitude = carRealtime.getLatitude();
						String url = "http://api.map.baidu.com/geocoder/v2/?callback=renderOption&output=json&location="+latitude+","+longitude+"&ak="+ak;
						/**
						 * renderOption&&renderOption(
						 * {"status":0,"result":{"location":{"lng":106.76074596701162,"lat":26.627046998736924},
						 * "formatted_address":"贵州省贵阳市乌当区新添大道北段","business":"新添大道,新添寨","addressComponent":{
						 * "country":"中国","country_code":0,"province":"贵州省","city":"贵阳市","district":"乌当区","adcode":"520112","street":"新添大道北段","street_number":"","direction":"","distance":""},
						 * "pois":[],"poiRegions":[],"sematic_description":"绿谷商务酒店东南451米","cityCode":146}})
						 */
						JSONObject jsonObject = Urljson.JsonUrl(url);
						JSONObject object = (JSONObject) jsonObject.get("result");
						JSONObject dizi = (JSONObject) object.get("addressComponent");
						String site = dizi.get("city").toString() + dizi.get("district").toString() + dizi.get("street").toString();
						carRealtime.setSite(site);
						relist.add(carRealtime);
					}
				} catch (JSONException e) {
					//如果地址百度接口查询不到怎进行下一个
				}
			}
			int size = relist.size();
			int max = currentPage*pageSize;
			int min = (currentPage-1)*pageSize;
			if(max > size) {
				max = size;
			}
			if(min < 0) {
				min = 0;
			}
			List<CarRealtime> subList = relist.subList(min, max);
			PageBean pb = new PageBean();
			pb.setCurrentPage(currentPage);
			pb.setPageSize(pageSize);
			pb.setTotal(size);
			pb.setList(subList);
			result = new Result(0,"查询成功", pb);
		} catch (Exception e) {
			result = new Result(1,"查询失败", null);
			e.printStackTrace();
		} 
		return result;
	}


	//跨区统计
	@Override
	public Result transregional(int currentPage, int pageSize) {
		Result result = null;
		try {
			List<Carapply> relist = new ArrayList<Carapply>();
			List<String> wrokidlist = carRealtimeDao.carlog();
			for (String wrokid : wrokidlist) {
				try {
					Carapply apply = carRealtimeDao.carbook(wrokid);
					if(null != apply) {
						//通过目的地获取经纬度
						String destinations = apply.getDESTINATIONS();
						String url = "http://api.map.baidu.com/geocoder/v2/?callback=renderOption&output=json&address="+destinations+"&city=贵阳市&ak="+ak;
						JSONObject jsonObject = Urljson.JsonUrl(url);
						JSONObject obj = (JSONObject) jsonObject.get("result");
						JSONObject object = (JSONObject) obj.get("location");
						String lng = object.get("lng").toString();
						String lat = object.get("lat").toString();
						String url2 = "http://api.map.baidu.com/geocoder/v2/?callback=renderOption&output=json&location="+lat+","+lng+"&ak="+ak;
						JSONObject jsons = Urljson.JsonUrl(url2);
						JSONObject objects = (JSONObject) jsons.get("result");
						JSONObject dizi = (JSONObject) objects.get("addressComponent");
						if(!"乌当区".equals(dizi.get("district").toString())) {
							String site = dizi.get("district").toString() + dizi.get("street").toString();
							apply.setDESTINATIONS(site);
							relist.add(apply);
						}
					}
				} catch (JSONException e) {
					//如果地址百度接口查询不到怎进行下一个
				}
			}
			int size = relist.size();
			int max = currentPage*pageSize;
			int min = (currentPage-1)*pageSize;
			if(max > size) {
				max = size;
			}
			if(min < 0) {
				min = 0;
			}
			List<Carapply> subList = relist.subList(min, max);
			PageBean pb = new PageBean();
			pb.setCurrentPage(currentPage);
			pb.setPageSize(pageSize);
			pb.setTotal(size);
			pb.setList(subList);
			result = new Result(0,"查询成功", pb);
		} catch (Exception e) {
			result = new Result(1,"查询失败", null);
			e.printStackTrace();
		} 
		return result;
	}



}
