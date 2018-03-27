package com.henghao.istration.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.henghao.istration.dao.ICarapplyDao;
import com.henghao.istration.entity.Carapply;
import com.henghao.istration.service.ICarapplyService;
import com.henghao.istration.util.DateUtils;
import com.henghao.istration.util.Result;
@Service("carapplyService")
public class CarapplyServiceImpl implements ICarapplyService {

	//1经纬度约等于111千米
//	private static final int latlong = 111;
	Map<String, Object> map = new HashMap<String, Object>();
	Map<String, Object> resultmap = new HashMap<String, Object>();
	@Resource
	private ICarapplyDao carapplyDao;
	
	
	
	@Override
	public Result findApply(String carNumber) {
		Result result = null;
		//List<Object> lists = new ArrayList<Object>();
		Date days = new Date();
		try {   
			List<Carapply> apply = new ArrayList<Carapply>();
			String dates = null;
			for (int i=0; i<7; i++) {
				if(apply.size() == 0) {
					String nextDays = DateUtils.getCurrents(days);
					String[] split = nextDays.split(" ", 0);
					dates = split[0];
					//查询当前时间yyyy-MM-dd在不在某一申请单的开始结束时间之内
					apply = carapplyDao.findApply(carNumber,dates);
					//获取前一天
					days = DateUtils.getNextDay(days);
				}else{
					//获取到结束
					break;
				}
			}
			if(apply.size()==0) {
				//该车辆没有匹配到申请单的开始结束时间
				return result = new Result(0,carNumber+"没有人使用",null);
			}
			for (Carapply carapply : apply) {
				//查询到的申请单信息时间段必须包含当前时间
				String starttime = carapply.getSTARTTIME()+":00";
				String endtime = carapply.getENDTIME()+":00";
				Date start = DateUtils.parseDateFromString(starttime);
				Date end = DateUtils.parseDateFromString(endtime);
				Date date2 = new Date(); 
				if(DateUtils.isBefore(start, date2) && DateUtils.isBefore(date2, end)) {
					//查到一个包含当前时间的，返回
					return result = new Result(0,"查询成功",carapply);
				}
			}
			result = new Result(0,carNumber+"未被使用",null);
		} catch (Exception e) {
			result = new Result(1,"查询失败",null);
			e.printStackTrace();
		}
		
		return result;
		
	}

	


	
}
