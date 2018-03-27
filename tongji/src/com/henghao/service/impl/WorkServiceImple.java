package com.henghao.service.impl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henghao.dao.AbnormalRateDao;
import com.henghao.dao.BeyondRateDao;
import com.henghao.dao.IntimacyDao;
import com.henghao.dao.PersonnelDao;
import com.henghao.dao.PunishDao;
import com.henghao.dao.WorkNumDao;
import com.henghao.entity.BeyondRate;
import com.henghao.entity.Personnel;
import com.henghao.entity.Punish;
import com.henghao.entity.Result;
import com.henghao.service.WorkService;


@Service(value="workService")
@SuppressWarnings({"unchecked","rawtypes"})
public class WorkServiceImple implements WorkService {
	@Autowired
	private WorkNumDao workNumDao ;
	@Autowired
	private BeyondRateDao beyondRateDao;
	@Autowired
	private PunishDao punishDao;
	@Autowired
	private IntimacyDao intimacyDao;
	@Autowired
	private AbnormalRateDao abnormalRateDao;
	@Autowired
	private PersonnelDao personnelDao;

	//private String[] position = new String[]{"队长","副队长","队员"};
	@Override
	public Result searchPersonnel() {
		Result result = new Result();
		Personnel personnel = null;
		List<Personnel> list = (List<Personnel>)personnelDao.searchPersonnel();
		List<Personnel> personnels = new ArrayList<Personnel>();
		for (Object object:list) {
			personnel = new Personnel();
			Object[] objects =(Object[]) object;
			personnel.setId(objects[0].toString());
			personnel.setName(objects[1] .toString());
			personnel.setRole(objects[2].toString());
//			personnel.setRole(position[Integer.valueOf(objects[2].toString())]); 
			personnels.add(personnel);
		}
		String msg = "查询成功！";
		if(personnels == null || personnels.size() == 0){
			msg = "查询无数据";
		}
		
		result.setMsg(msg);
		result.setData(personnels);
		return result; 
	}
	// 超期率
	@SuppressWarnings("unused")
	@Override
	public Result searchBeyondById(String name){
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		List list= beyondRateDao.searchByBeyondRate(name);
		Map<String, BeyondRate> map = new HashMap<String, BeyondRate>();
		for(Object object : list){
			Object[] objs = (Object[]) object;
			JSONObject jsonObject =new JSONObject();
			jsonObject.put("inspectStartTime", objs[0] == null ? "" : String.valueOf(objs[0]));
			jsonObject.put("inspectEndTime", objs[1] == null ? "" : String.valueOf(objs[1]));
//			jsonObject.put("planStartTime", objs[2] == null ? "" : String.valueOf(objs[2]));
//			jsonObject.put("planEndTime", objs[3] == null ? "" : String.valueOf(objs[3]));
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date start = format.parse(objs[0].toString());
				long s = start.getTime();//开始时间的毫秒数
				Date end = (Date)format.parse(objs[1].toString());
				long e = end.getTime();//结束时间的毫秒数
				if(objs[2] != null && objs[3] != null){
					Date planStart = (Date) format.parse(objs[2].toString());
					long p = planStart.getTime();//计划开始时间的毫秒数
					Date planEnd = (Date)format.parse(objs[3].toString());
					long pe = planEnd.getTime();//计划结束时间的毫秒数
					String state = ((e - s) / 8 * 60 * 60 * 1000)<=1 ? "正常" : "超期";//判断是否超期
					jsonObject.put("isBeyondRate", state);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			jsonArray.add(jsonObject);
		}
		String msg ="查询成功";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg="查询无数据";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}
	//处罚偏离度
	@SuppressWarnings({ "unused" })
	@Override
	public Result searchPunishById(String name) {
		Result result= new Result();
		JSONArray jsonArray = new JSONArray();
		List list=punishDao.searchPunishById(name);
		Map<String, Punish> map = new HashMap<String, Punish>();
		for(Object object :list ){
			Object[] objs = (Object[])object;
			JSONObject jsonObject= new JSONObject();
			jsonObject.put("fineMaxMoney", objs[0] == null ? "" : String.valueOf(objs[0]));
			jsonObject.put("fineminMOney", objs[1] == null ? "" : String.valueOf(objs[1]));
			jsonObject.put("standard", objs[2] == null ? "" : String.valueOf(objs[2]));
			jsonObject.put("factMoney", objs[3] == null ? "" : String.valueOf(objs[3]));
			jsonObject.put("punishTime", objs[4] == null ? "" : String.valueOf(objs[4]));
			if (list != null && list.size()>0) {
				Double max = Double.valueOf(objs[0].toString());
				Double min = Double.valueOf(objs[1].toString());
				Double fact = Double.valueOf(objs[3].toString());
				String state = ((fact - min) / (max - min))>=0 && ((fact - min) / (max - min))<=1 ? "正常":(((fact - min) / (max - min))>1 ? "正偏离" : "负偏离");
				jsonObject.put("state", state);
				Double stand = Double.valueOf(objs[2].toString());
				String price="0";
				Double offset=0d;
				if (state.equals("正常")) {	
//					if (fact > stand) {
//						price = "偏高";
//						jsonObject.put("price", price);
//					}else if (fact < stand) {
//						price = "偏低";
//						jsonObject.put("price", price);
//					}else {
//						price = "标准";
//						jsonObject.put("price", price);
//					}
					 price = (fact > stand) ? "偏高":((fact < stand) ? "偏低":"标准");
					jsonObject.put("price", price);
					offset = (double) Math.abs((fact-stand)/stand);
					jsonObject.put("offset", offset);
				} else {
					 price = (fact > stand) ?"偏高":"偏低";
					jsonObject.put("price", price);
					if (offset==0.0) {
						String s = String.valueOf(offset);
						s = null;
						jsonObject.put("offset", s);
					}
				}
 			} 
			jsonArray.add(jsonObject);
		}
		String msg="查询成功";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg="查询无数据";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}
	
	/**
	 * 工作量 
	 */
	@Override
	public Result searchWorkByName(String name) {
		Result result = new Result();
		List list = workNumDao.searchWorkByName(name);
		JSONArray jsonArray = new JSONArray();
		for (Object object : list) {
			Object[] obj = (Object[])object;
			JSONObject jsonObject = new JSONObject();
			String dayOfMonth = "0";
			String day = obj[2].toString();
			dayOfMonth = day.substring(day.lastIndexOf("-") + 1,day.lastIndexOf(" "));
			jsonObject.put("workNum", obj[0] == null ? "" : String.valueOf(obj[0]));
			jsonObject.put("time", obj[2].toString());
			jsonObject.put("days", Double.valueOf(dayOfMonth));
			jsonArray.add(jsonObject);  
		}
		String msg="查询成功";
		if (jsonArray==null || jsonArray.size() == 0) {
			msg="查询无数据";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}
	/**
	 * 亲密度
	 * @param punishNum 
	 */
	@Override
	public Result searchIntimacyByName(String emplyName) {
		Result result = new Result();
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		Map<String, List<List<String>>> maps = searchByEmplyName(emplyName);
		Map<String, String> punishNumMap = searchByPunishNum(emplyName);
		Map<String, String> enterMap=searchByEnterpriseNum(emplyName);
		Set<String> keys = maps.keySet();
		for(String key : keys){
			String punishNum = punishNumMap.get(key);
			String enforcementNum = enterMap.get(key);
			List<List<String>> list = maps.get(key);
			if(list != null && list.size()>0){
				Object punishNumMax =list.get(0).get(2);
				Object punishNumMin =list.get(0).get(3);
				Object ininspectStartTime = list.get(0).get(4);
				Object inspectEndTime = list.get(0).get(5);
//				Object planStartTime = list.get(0).get(6);
//				Object planEndTime = list.get(0).get(7);
				Object punishRateMax =list.get(0).get(8);
				Object punishRateMin =list.get(0).get(9);
				Object fineMaxMoney =list.get(0).get(10);
				Object fineMinMoney =list.get(0).get(11);
				Object factMoney =list.get(0).get(12);
				Integer num =Integer.parseInt(enforcementNum);
				Integer max = Integer.valueOf( punishNumMax.toString());
				Integer min = Integer.valueOf(punishNumMin.toString());
				jsonObject.put("factPunishNum", num);
				jsonObject.put("punishMinNum", min);
				jsonObject.put("punishMaxNum", max);
				String isNomal = num >= min && num <= max ? "正常" : "异常";//判断执法次数是否正常
				jsonObject.put("isPunishNum", isNomal);
				Integer fm = Integer.valueOf(fineMaxMoney.toString());
				Integer fs = Integer.valueOf(fineMinMoney.toString());
				Integer f = Integer.valueOf(factMoney.toString());
				jsonObject.put("fineMaxMoney", fineMaxMoney);
				jsonObject.put("fineMinMoney", fineMinMoney);
				jsonObject.put("factMoney", factMoney);
				String isRational = f >=fs && f <=fm ? "合理" : "不合理"; //判断处罚金额是否合理
				jsonObject.put("isPunishMoney", isRational);
				Double pm = Double.parseDouble(punishRateMax.toString());
				Double ps = Double.parseDouble(punishRateMin.toString()); 
				Integer pn = Integer.parseInt(punishNum); 
				jsonObject.put("punishRateMax", punishRateMax);
				jsonObject.put("punishRateMin", punishRateMin);
				String isDeviate = (pn/num) >= ps && (pn/num) <= pm ? "正常" : "偏离"; //判断处罚次数是否偏离
				jsonObject.put("isPunishRate", isDeviate);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String starDate = ininspectStartTime.toString();
				String endDate = inspectEndTime.toString();
//				String startTime= planStartTime.toString();
//				String endTime= planEndTime.toString();
				try {
					Date start = format.parse( ininspectStartTime.toString());
					long s = start.getTime();
					Date end = format.parse(inspectEndTime.toString());
					long e = end.getTime();
//					Date planStart = format.parse( planStartTime.toString());
//					long p = planStart.getTime();
//					Date planEnd = format.parse(planEndTime.toString());
//					long pe = planEnd.getTime();
					jsonObject.put("ininspectStartTime", starDate.substring(0, 19));
					jsonObject.put("inspectEndTime", endDate.substring(0, 19));
//					jsonObject.put("planStartTime", startTime.substring(0, 19));
//					jsonObject.put("planEndTime", endTime.substring(0, 19));
					String isStandard = ((e - s) / 8 * 60 * 60 * 1000)<= 1 ? "标准" : "异常"; // 标准时长8小时
					jsonObject.put("isPunishTime", isStandard);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				jsonArray.add(jsonObject); 
				
			}
			
		}
		String msg ="查询成功";
		if (jsonArray == null || jsonArray.size()==0) {
			msg="查无数据";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}
 	/**
	 * 亲密度信息
	 */
	@Override
	public Map<String, List<List<String>>> searchByEmplyName(String emplyName) {
		List list = intimacyDao.searchEnterprise(emplyName);
		Map<String, List<List<String>>> map = new HashMap<String, List<List<String>>>();
		List<List<String>> listValue = new ArrayList<List<String>>();
		if(list != null && list.size() > 0){
			for (Object object : list) {
				Object[] objs = (Object[])object;
				String key = objs[1] == null ? "" : String.valueOf(objs[1]);
				if(map.get(key) == null){
					List<String> listv = new ArrayList<String>();
					for (Object obj : objs) {
						listv.add(obj == null ? "": obj.toString());
					}
					listValue.add(listv);
					map.put(key, listValue);
				}else{
					List<List<String>> list2 = map.get(key);
					List<String> listv = new ArrayList<String>();
					for (Object obj : objs) {
						listv.add(obj == null ? "": obj.toString());
					}
					list2.add(listv);
					map.put(key, list2);
				}
				 
				
//				List<Object> values = Arrays.asList(objs);
//				if(map.get(key) == null){
//					map.put(key, values);
//				}else {
//					List<Object> vs = map.get(key);
//					Object[] objects = values.toArray();
//					vs.add(objects);
//					map.put(key, vs);
//				}
			}
		}
		return map;
	}
	/**
	 * 亲密度处罚次数
	 */
	@Override
	public Map<String, String> searchByPunishNum(String punishNum) {
		
		List<?> list = intimacyDao.searchPunishNum(punishNum);
		Map<String,String> map = new HashMap<String, String>();
		if(list != null){
			for (Object object : list) {
				Object[] objs = (Object[])object;
				map.put(String.valueOf(objs[1]), String.valueOf(objs[0]));
			}
		}
		return map;
	}
	/**
	 * 亲密度执法次数
	 */
	@Override
	public Map<String, String > searchByEnterpriseNum(String enforcementNum) {
		List<?> list = intimacyDao.searchEnterpriseNum(enforcementNum);
		Map<String, String > map = new HashMap<String, String>();
		if(list != null){
			for(Object object:list){
				Object[] objs = (Object[]) object;
				map.put(String.valueOf(objs[1]), String.valueOf(objs[0]));
			}
		}
		return map;
	
	}
	
	/**
	 * 异常率实际次数
	 */
	@Override
	public Map<String, List<List<String>>> searchAbnormalRateByNum(String name) {
		 List<?> list = abnormalRateDao.searchAbnormalRateByNum(name);
		 Map<String, List<List<String>>> map = new HashMap<String, List<List<String>>>();
			List<List<String>> listValue = new ArrayList<List<String>>();
		 if(list != null && list.size() > 0){
				for (Object object : list) {
					Object[] objs = (Object[])object;
					String key = objs[4] == null ? "" : String.valueOf(objs[4]);
					if(map.get(key) == null){
						List<String> listv = new ArrayList<String>();
						for (Object obj : objs) {
							listv.add(obj == null ? "": obj.toString());
						}
						listValue.add(listv);
						map.put(key, listValue);
					}else{
						List<List<String>> list2 = map.get(key);
						List<String> listv = new ArrayList<String>();
						for(Object obj : objs) {
							listv.add(obj == null ? "": obj.toString());
						}
						list2.add(listv);
						map.put(key, list2);
					}
					
					
//					List<Object> values = Arrays.asList(objs);
//					if(map.get(key) == null){
//						map.put(key, values);
//					}else {
//						List<Object> vs = map.get(key);
//						Object[] objects = values.toArray();
//						vs.add(objects);
//						map.put(key, vs);
//					}
				}
			}
		return map;
	}
	/**
	 * 异常率标准次数
	 */
	@Override
	public Map<String, List<List<String>>> searchAbnormalRateByStandardNum(String name) {
		List<?> list = abnormalRateDao.searchAbnormalRatByStandard(name);
		Map<String, List<List<String>>> map = new HashMap<String, List<List<String>>>();
		List<List<String>> listValue = new ArrayList<List<String>>();
		 if(list != null && list.size() > 0){
				for (Object object : list) {
					Object[] objs = (Object[])object;
					String key = objs[0] == null ? "" : String.valueOf(objs[0]);
					if(map.get(key) == null){
						List<String> listv = new ArrayList<String>();
						for(Object obj : objs) {
							listv.add(obj == null ? "": obj.toString());
						}
						listValue.add(listv);
						map.put(key, listValue);
					}else{
						List<List<String>> list2 = map.get(key);
						List<String> listv = new ArrayList<String>();
						for (Object obj : objs) {
							listv.add(obj == null ? "": obj.toString());
						}
						list2.add(listv);
						map.put(key, list2);
					}
					
					
//					List<Object> values = Arrays.asList(objs);
//					if(map.get(key) == null){
//						map.put(key, values);
//					}else {
//						List<Object> vs = map.get(key);
//						Object[] objects = values.toArray();
//						vs.add(objects);
//						map.put(key, vs);
//					}
				}
			}
		return map;
	}
	/**
	 * 异常率
	 */
	@Override
	public Result searchAbnormalRateByName(String name) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		Map<String, List<List<String>>> numMap = searchAbnormalRateByNum(name);
		Map<String, List<List<String>>> standardMap = searchAbnormalRateByStandardNum(name);
		Set<String> keys = numMap.keySet();
		for(String key: keys){
			List<List<String>> list = numMap.get(key);
			if (list != null && list.size()>0) {
				jsonObject = new JSONObject();
				String  punishNum = list.get(0).get(0).toString();
				String  checkNum = list.get(0).get(1).toString(); 
				String rectNum =list.get(0).get(2).toString();
				String reviewNum = list.get(0).get(3).toString();
				jsonObject.put("punishNum", punishNum);
				jsonObject.put("checkNum", checkNum);
				jsonObject.put("rectNum", rectNum);
				jsonObject.accumulate("reviewNum", reviewNum);		
			}
			List<List<String>> list1 = standardMap.get(key);
			if (list1 != null && list1.size()>0) {
				String standardChechNum = list1.get(0).get(1).toString();
				String standardPunishNum = list1.get(0).get(2).toString();
				String standardRectNum = list1.get(0).get(3).toString();
				String standardReviewNum = list1.get(0).get(4).toString();
				jsonObject.put("standardChechNum", standardChechNum);
				jsonObject.put("standardPunishNum", standardPunishNum);
				jsonObject.put("standardRectNum", standardRectNum);
				jsonObject.put("standardReviewNum", standardReviewNum);
				String endTime = list1.get(0).get(6).toString();
				String year ="0";
				year = endTime.substring(0, 4);
				jsonObject.put("year", year);	
				
			}
			
			jsonArray.add(jsonObject);		
		}
		String msg = "查询成功";
		if(jsonArray == null || jsonArray.size() == 0){
			msg = "查询无数据";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}
	

}
