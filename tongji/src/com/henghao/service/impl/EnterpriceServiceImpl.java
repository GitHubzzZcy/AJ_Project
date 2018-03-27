package com.henghao.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henghao.dao.EnteriseDao;
import com.henghao.entity.Result;
import com.henghao.service.EnterpriceService;
import com.henghao.util.DateUtils;
import com.henghao.util.PageBaen;
@Service
public class EnterpriceServiceImpl implements EnterpriceService {
	@Autowired
	private EnteriseDao endao;
	@Override
	public Result findByQy() {
		Result result=null;
		try {
			Map<String,Object> map=endao.findByQy();
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findByJglx() {
		Result result=null;
		try {
			Map<String,Object> map=endao.findByJglx();
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;	
	}

	@Override
	public Result findByJgbm() {
		Result result=null;
		try {
			Map<String,Object> map=endao.findByJgbm();
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findByJgjb() {
		Result result=null;
		try {
			Map<String,Object> map=endao.findByJgjb();
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findYhByCondition(String condition) {
		Result result=null;
		try {
			Map<String,Object> map=endao.findYhByCondition(condition);
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findYhByBmCondttion(String condition) {
		Result result=null;
		try {
			Map<String,Object> map=endao.findYhByBmCondttion(condition);
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findYhByLxCondttion(String condition) {
		Result result=null;
		try {
			Map<String,Object> map=endao.findYhByLxCondttion(condition);
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findYhByConditionZczb(String condition) {
		Result result=null;
		try {
			Map<String,Object> map=endao.findYhByConditionZczb(condition);
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findYhByBmCondttionZczb(String condition) {
		Result result=null;
		try {
			Map<String,Object> map=endao.findYhByBmCondttionZczb(condition);
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findYhByLxCondttionZczb(String condition) {
		Result result=null;
		try {
			Map<String,Object> map=endao.findYhByLxCondttionZczb(condition);
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findAllUser(String condition) {
		Result result=null;
		try {
			Map<String,Object> map=endao.findAllUser(condition);
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findHiddByNum(String num) {		
		Result result=null;
		int no=3;
		if(num !=null){
			no = Integer.parseInt(num);
		}
		no++;
		try {			
			Map<String,Object> map=endao.findHiddByNum(no);
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findSafeTeache(String num) {
		Result result=null;
		int no=1;
		if(num !=null){
			no = Integer.parseInt(num);
		}
		no++;
		try {			
			Map<String,Object> map=endao.findSafeTeache(no);
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findHiddenChaosshi(String user, String date) {
		Result result=null;
		try {			
			Map<String,Object> map=endao.findHiddenChaosshi(user,date);
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findHiddenSuccess(String user, String date) {
		Result result=null;
		try {			
			Map<String,Object> map=endao.findHiddenSuccess(user,date);
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findHiddenAllNumByzgstatus(String user, String date) {
		Result result=null;
		try {			
			Map<String,Object> map=endao.findHiddenAllNumByzgstatus(user,date);
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findHiddenAllNumByEnlx(String user, String date) {
		Result result=null;
		try {			
			Map<String,Object> map=endao.findHiddenAllNumByEnlx(user,date);
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findHiddenAllNumByEnqh(String user, String date) {
		Result result=null;
		try {			
			Map<String,Object> map=endao.findHiddenAllNumByEnqh(user,date);
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;
	}
	//20170518更改调用其他方法
	@Override
	public Result findHiddenAllNumBySession(String user, String date) {
		Result result=null;
		Map<String,Object> map = new HashMap<String, Object>();
		try {			
			if(date == null || date.equals("")){
				date=DateUtils.getCurrentDate("yyyy");
			}			
			for (int i = 1; i <= 4; i++) {
				String jidu=date+"-"+i;
				List<?> list=(List<?>)endao.findHiddenAllNumByzgstatus(user,jidu).get("SuccessNum");				
				map.put("quarter"+i, list);			
			}
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findTimeLength(String date, String user) {
		if(date.equals("") || date == null){
			date = DateUtils.getCurrentDate("yyyy");
		}
		Result result=null;
		String startdate=DateUtils.getStardate(date);
		String enddate=DateUtils.getEnddate(date);
		try {			
			Map<String,Object> map=endao.findTimeLength(startdate,enddate,user);
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findAllHidEnByDate(String entName,String date) {
		if(date == null || date.equals("")){
			date = DateUtils.getCurrentDate("yyyy");
		}
		Result result=null;
		String startdate=DateUtils.getStardate(date);
		String enddate=DateUtils.getEnddate(date);
		try {			
			Map<String,Object> map=endao.findAllHidEnByDate(entName,startdate,enddate);
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findHidEnByDate_EnId(String entId, String date) {
		if(date == null || date.equals("")){
			date = DateUtils.getCurrentDate("yyyy");
		}
		Result result=null;
		String startdate=DateUtils.getStardate(date);
		String enddate=DateUtils.getEnddate(date);
		try {			
			Map<String,Object> map=endao.findHidEnByDate_EnId(entId,startdate,enddate);
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findZfByDate(String names, String date) {
		if(date == null || date.equals("")){
			date = DateUtils.getCurrentDate("yyyy");
		}
		if(null==names || "".equals(names)){
			names="_";
		}
		Result result=null;
		String startdate=DateUtils.getStardate(date);
		String enddate=DateUtils.getEnddate(date);
		try {	
			String[] str=names.split(",");
			List<Object[]> list =new ArrayList<Object[]>();
			for (int i = 0; i < str.length; i++) {
				list.addAll(endao.findZfByDate(names,startdate,enddate));
			}
			//去重
			List<Object[]> qcAfterList =new ArrayList<Object[]>();
			for (Object[] objects : list) {
				//如果不不包含就添加过来
				if(!qcAfterList.contains(objects)){
					qcAfterList.add(objects);
				}
			}
			
			result=new Result(0,"查询成功,执法数如后",qcAfterList.size());
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findZfPosition(String username, String startDate,
			String endDate, PageBaen pb) {
		Result result=null;		
		if(null==username || "".equals(username)){
			return new Result(1,"姓名不能为空",null);
		}else if(null==startDate || "".equals(startDate)){
			startDate=DateUtils.getStardate(DateUtils.getCurrentDate("yyyy-MM"));
			
		}else if(null==endDate || "".equals(endDate)){
			endDate=DateUtils.getEnddate(DateUtils.getCurrentDate("yyyy-MM"));
		}
		
		try {
			Map<String,Object> map=endao.findZfPosition(username,startDate,endDate,pb);
			result=new Result(0,"查询成功",map);
		} catch (Exception e) {
			result=new Result(1,"系统繁忙，查询失败",null);
			e.printStackTrace();
		}
		return result;//2017-6-15下午2:04:27宁万龙
	}


}
