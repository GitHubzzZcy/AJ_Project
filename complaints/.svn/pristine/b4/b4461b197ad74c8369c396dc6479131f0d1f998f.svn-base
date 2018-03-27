package com.henghao.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.henghao.dao.IComplainDao;
import com.henghao.entity.Complain;
import com.henghao.service.IComplainService;
import com.henghao.util.DateUtils;
import com.henghao.util.Loadjpg;
import com.henghao.util.PageBaen;
import com.henghao.util.Result;



@Service("complainService")
public class ComplainServiceImpl implements IComplainService {

	@Resource
	private IComplainDao complainDao;
	Map<String, Object> map = new HashMap<String, Object>();
	Map<String, String> smap = new HashMap<String, String>();

	@Override
	public Result loadAdd(Complain complain,String pathRoot) {
		Result result = null;
		StringBuffer path = new StringBuffer();
		try {
			String[] serverIds = complain.getServerId();
			String access_token = complain.getAccess_token();
			List<String> list = Arrays.asList(serverIds);   
			for (String serverId : list) {
				String filePath = Loadjpg.loadjpg(access_token, serverId, pathRoot);
				path.append(filePath+",");
			}
			String jpgpath = path.substring(0, path.length()-1);
			complain.setFilePath(jpgpath);
			complain.setComplainStatus("待查看");
			complain.setComplainTime(new Date());
			complainDao.add(complain);
			result = new Result(0,"保存成功",null);
		} catch (Exception e) {
			result = new Result(1,"保存失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result findAll(String currentPage, String pageSize) {
		Result result = null;
		try {
			Map<String, Object> findAll = complainDao.queryNotFinish(Integer.parseInt(currentPage),Integer.parseInt(pageSize));
			result = new Result(0,"查询所有成功",findAll);
		} catch (Exception e) {
			result = new Result(1,"查询所有失败",null);
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public Result findById(String id) {
		Result result = null;
		try {
			Complain complain = complainDao.findById(Integer.parseInt(id));
			
			result = new Result(0,"查询成功",complain);
		} catch (NumberFormatException e) {
			result = new Result(1,"查询失败",null);
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public Result consent(String id) {
		Result result = null;
		try {
			Complain complain = complainDao.findById(Integer.parseInt(id));
			complain.setComplainStatus("同意立案");
			complainDao.update(complain);
			result = new Result(0,"立案成功",null);
		} catch (Exception e) {
			result = new Result(1,"同意立案失败",null);
			e.printStackTrace();
		}
		
		return result;
	}
	@Override
	public Result repulse(String id,String desc) {
		Result result = null;
		try {
			Complain complain = complainDao.findById(Integer.parseInt(id));
			complain.setComplainStatus("拒绝立案");
			complain.setDisposeDesc(desc);
			complainDao.update(complain);
			result = new Result(0,"拒绝立案成功",null);
		} catch (Exception e) {
			result = new Result(1,"拒绝立案失败",null);
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public Result finish(String id) {
		Result result = null;
		try {
			Complain complain = complainDao.findById(Integer.parseInt(id));
			complain.setComplainStatus("已结案");
			complainDao.update(complain);
			result = new Result(0,"结案成功",null);
		} catch (Exception e) {
			result = new Result(1,"结案失败",null);
			e.printStackTrace();
		}
		
		return result;
	}
	@Override
	public Result queryFinish(String currentPage, String pageSize) {
		Result result = null;
		try {
			PageBaen pageBaen = new PageBaen();
			pageBaen.setCurrentPage(Integer.parseInt(currentPage));
			pageBaen.setPageSize(Integer.parseInt(pageSize));
			smap.put("complainStatus", "已结案");
			pageBaen.setConditionMap(smap);
			complainDao.findConditionString(pageBaen);
			List<?> list = pageBaen.getList();
			Integer total = pageBaen.getTotal();
			map.put("list", list);
			map.put("total", total);
			result = new Result(0,"查询已结案成功",map);
		} catch (Exception e) {
			result = new Result(1,"查询已结案失败",null);
			e.printStackTrace();
		}
		return result;
	}

	
	@Override
	public Result findByPhone(PageBaen pageBaen) {
		Result result = null;
		try {
			complainDao.findConditionString(pageBaen);
			result = new Result(0,"查询成功",pageBaen);
		} catch (Exception e) {
			result = new Result(1,"系统繁忙,查询失败",pageBaen);
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Result querySeek(String classs, String types, String condition, String currentPage, String pageSize) {
		Result result = null;
		try {
			Map<String, Object> seek = complainDao.querySeek(classs, types, condition,Integer.parseInt(currentPage),Integer.parseInt(pageSize));
			result = new Result(0,"搜索查询成功", seek);
		} catch (Exception e) {
			result = new Result(1,"搜索查询失败", null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result comquery(String name, String phone) {
		Result result = null;
		try {
			List<Complain> list = complainDao.findByPhone(name,phone);
			result = new Result(0,"查询成功", list);
		} catch (Exception e) {
			result = new Result(1,"系统繁忙",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result tofinish(String id, String feedback) {
		Result result = null;
		try {
			Complain complain = complainDao.findById(Integer.parseInt(id));
			complain.setComplainStatus("已结案");
			complain.setFeedback(feedback);
			complainDao.update(complain);
			result = new Result(0,"结案成功",null);
		} catch (Exception e) {
			result = new Result(1,"结案失败",null);
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Result evaluate(String id, String satisfaction, String evaluate) {
		Result result = null;
		try {
			Complain complain = complainDao.findById(Integer.parseInt(id));
			complain.setSatisfaction(Integer.parseInt(satisfaction));
			complain.setEvaluate(evaluate);
			complainDao.update(complain);
			result = new Result(0,"评价成功",null);
		} catch (Exception e) {
			result = new Result(1,"系统繁忙，评价失败！",null);
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Result statement() {
		Result result = null;
		try {
			String currentDate = DateUtils.getCurrentDate("yyyy-MM");
			String year = DateUtils.getCurrentDate("yyyy");
			List<Object> itmlist = new ArrayList<Object>();
			List<String> ymlist = new ArrayList<String>();
			List<String> yearlist = new ArrayList<String>();
			String[] itemss = {"安全隐患","事故瞒报","举报执法人","其他"};
			String[] regions = {"观山湖区","南明区","云岩区","花溪区","乌当区","白云区","开阳县","息烽县","修文县","清镇市"};
			//区域类型统计
			for (String items : itemss) {
				List<String> list = new ArrayList<String>();
				for (String region : regions) {
					String count = complainDao.regionquery(items, region, currentDate);
					list.add(count);
				}
				itmlist.add(list);
			}
			//月份统计
			for(int i=1; i<13; i++) {
				String ym;
				if(i < 10) {
					ym = year + "-0" + i;
				}else{
					ym = year + "-" + i;
				}
				String count = complainDao.monthquery(ym);
				ymlist.add(count);
			}
			//年份类型统计
			for (String items : itemss) {
				String count = complainDao.yearquery(items,year);
				yearlist.add(count);
			}
			map.put("regions", itmlist);
			map.put("month", ymlist);
			map.put("year", yearlist);
			result = new Result(0,"查询统计成功",map);
		} catch (Exception e) {
			result = new Result(1,"系统繁忙，查询失败！",null);
			e.printStackTrace();
		}
		
		return result;
	}

	//评分系统
	@Override
	public Map<String, Object> queryUserGrade(String userName, String start, String end) {
		Map<String, Object> remap = new HashMap<String, Object>();
		List<String> car_list = new ArrayList<String>();
		List<String> anquan_list = new ArrayList<String>();
		List<String> weiji_list = new ArrayList<String>();
		List<String> huihuo_list = new ArrayList<String>();
		List<String> jiufen_list = new ArrayList<String>();
		List<String> banshi_list = new ArrayList<String>();
		
		List<Complain> list = complainDao.queryUserGrade(userName, start, end);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (Complain complain : list) {
			if("公车私用".equals(complain.getExecutorType())) {
				car_list.add(sdf.format(complain.getComplainTime()));
			}
			if("发生安全生产事故".equals(complain.getExecutorType())) {
				anquan_list.add(sdf.format(complain.getComplainTime()));
			}
			if("发现违纪行为".equals(complain.getExecutorType())) {
				weiji_list.add(sdf.format(complain.getComplainTime()));
			}
			if("挥霍公款、铺张浪费".equals(complain.getExecutorType())) {
				huihuo_list.add(sdf.format(complain.getComplainTime()));
			}
			if("与同事、执法对象发生打架斗殴等纠纷".equals(complain.getExecutorType())) {
				jiufen_list.add(sdf.format(complain.getComplainTime()));
			}
			if("办事推诿，逃避责任".equals(complain.getExecutorType())) {
				banshi_list.add(sdf.format(complain.getComplainTime()));
			}
		}
		remap.put("car_list", car_list);
		remap.put("anquan_list", anquan_list);
		remap.put("weiji_list", weiji_list);
		remap.put("huihuo_list", huihuo_list);
		remap.put("jiufen_list", jiufen_list);
		remap.put("banshi_list", banshi_list);
		return remap;
	}

	
	

	

	
	

	
}
