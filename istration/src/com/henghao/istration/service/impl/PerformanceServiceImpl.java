package com.henghao.istration.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.henghao.istration.dao.IApproveDao;
import com.henghao.istration.dao.IPerformanceDao;
import com.henghao.istration.entity.Head;
import com.henghao.istration.entity.Transactor;
import com.henghao.istration.entity.User;
import com.henghao.istration.service.IPerformanceService;
import com.henghao.istration.util.DateUtils;
import com.henghao.istration.util.GoodMode;
import com.henghao.istration.util.Result;
import com.henghao.istration.util.Urljson;

@Service("performanceService")
public class PerformanceServiceImpl implements IPerformanceService {

	private static final String URL = "http://localhost:8082/check_workflow/CI/findAllNumByUserId";
	private static final String joblidu = "工作力度";
	private static final String jobtaidu = "工作态度";
	private static final String grxy = "个人信用";
	@Resource
	private IPerformanceDao performanceDao;
	@Resource
	private IApproveDao approveDao;
	Map<String, Object> map = new HashMap<String, Object>();
	/**
	 * 查询一处四处人员名字跟头像
	 */
	@Override
	public Result queryDeptUser() {
		Result result = null;
		try {
			List<User> users = new ArrayList<User>();
			//获取最新节点办理人
			Transactor  yichublr = approveDao.queryblr("yichu");
			Transactor  sconeblr = approveDao.queryblr("sconeblr");
			Transactor  sctwoblr = approveDao.queryblr("sctwoblr");
			List<String> yichu = GoodMode.checkGoodMode(yichublr);
			List<String> scone = GoodMode.checkGoodMode(sconeblr);
			List<String> sctwo = GoodMode.checkGoodMode(sctwoblr);
			yichu.addAll(scone);
			yichu.addAll(sctwo);
			for (int i=0; i<yichu.size(); i++) {
				User user = new User();
				String dept = performanceDao.queryDept(yichu.get(i));
				user.setDept(dept);
				//String userId = performanceDao.findByUserId(name);
				Head userhead = approveDao.userhead(yichu.get(i));
				if(null != userhead) {
					String save_NAME = userhead.getSAVE_NAME();
					String extention = userhead.getEXTENTION();
					String filepath = "/"+save_NAME+"."+extention;
					user.setHeadPath(filepath);
				}else{
					user.setHeadPath("/head.jpg");
				}
				
				//user.setID(userId);
				user.setNAME(yichu.get(i));
				users.add(user);
			}
			result = new Result(0,"查询成功",users);
		} catch (Exception e) {
			result = new Result(1,"查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result userPerformance(String name) {
		Result result = null;
		List<Double> sm = new ArrayList<Double>();
		List<Double> xm = new ArrayList<Double>();
		try {
			String userId = performanceDao.findByUserId(name);
			if(null == userId) {
				return result = new Result(0,"请正确输入名字！",null);
			}
			Date date = DateUtils.getCurrentDateT("yyyy-MM");
			String formatedDate = DateUtils.getFormatedDate(date, "yyyy-MM");
			
			//工作力度
			List<String> lidu = performanceDao.queryPerf(name,joblidu,formatedDate);
			int lidus = 0;
			double liduf = 0;
			for (int i=0; i<lidu.size();i++) {
				lidus = Integer.parseInt(lidu.get(i)) + lidus;
			}
			//百分数
			double lidoufx = 0;
			if(lidus != 0){
				liduf =  lidus/lidu.size();
				lidoufx = liduf/10;
			}
			xm.add(liduf);
			//小数点数
			sm.add(lidoufx);
			
			//工作态度
			List<String> taidu = performanceDao.queryPerf(name,jobtaidu,formatedDate);
			int taidus = 0;
			double taiduf = 0;
			for (int i=0; i<taidu.size();i++) {
				taidus = Integer.parseInt(taidu.get(i)) + taidus;
			}
			double taidufx = 0;
			if(taidus != 0) {
				//百分数
				taiduf =  taidus/(taidu.size());
				//小数点数
				taidufx = taiduf/10;
			}
			xm.add(taiduf);
			sm.add(taidufx);
			
			//个人信用
			List<String> xinyong = performanceDao.queryPerf(name,grxy,formatedDate);
			int xys = 0;
			double xyf = 0;
			for(int i=0; i<xinyong.size(); i++) {
				xys = Integer.parseInt(xinyong.get(i)) + xys;
			}
			double xffx = 0;
			if(xys != 0) {
				//百分数
				xyf = xys/(xinyong.size());
				//小数点数
				xffx = xyf/10;
			}
			xm.add(xyf);
			sm.add(xffx);
			
			//工作业绩
			//查询流程当月启动的流程数量
			Transactor  yichublr = approveDao.queryblr("yichu");
			Transactor  sconeblr = approveDao.queryblr("sconeblr");
			Transactor  sctwoblr = approveDao.queryblr("sctwoblr");
			List<String> yichu = GoodMode.checkGoodMode(yichublr);
			List<String> scone = GoodMode.checkGoodMode(sconeblr);
			List<String> sctwo = GoodMode.checkGoodMode(sctwoblr);
			String yc = null;
			String sc1 = null;
			String sc2 = null;
			if(yichu.contains(name)) {
				yc = performanceDao.querylcCount("yichu",formatedDate);
			}
			if(scone.contains(name)) {
				sc1 = performanceDao.querylcCount("scone", formatedDate);
			}
			if(sctwo.contains(name)) {
				sc2 = performanceDao.querylcCount("sctwo", formatedDate);
			}
			int yci = 0;
			int sc1i = 0;
			int sc2i = 0;
			if(null != yc) {
				yci = Integer.parseInt(yc);
			}
			if(null != sc1) {
				sc1i = Integer.parseInt(sc1);
			}
			if(null != sc2) {
				sc2i = Integer.parseInt(sc2);
			}
			
			String count = performanceDao.querylog(name,formatedDate);
			int parseInt = 0;
			if(!count.equals("") && count != null) {
				parseInt = Integer.parseInt(count);
			}
			int parseInt2 = yci + sc1i + sc2i;
			
			double yjfx = 0;
			if(parseInt != 0 || parseInt2 != 0) {
				//小数点数
				yjfx = parseInt/parseInt2;
			}
			sm.add(yjfx);
			//百分数
			double yjf = yjfx * 10;
			xm.add(yjf);
			
			//出勤率
			String url = URL+"?userId="+userId+"&date="+formatedDate;
			JSONObject json = Urljson.readJsonFromUrl(url);
			JSONObject object = json.getJSONObject("data");
			//小数点数
			String cqls = object.get("chuqingli").toString();
			double cqlx = Double.parseDouble(cqls);
			sm.add(cqlx);
			//百分数
			double cql = cqlx*10;
			xm.add(cql);
			
			//所有数保留一位小数点
		    for(int i=0; i<sm.size(); i++) {
		    	BigDecimal b = new BigDecimal(sm.get(i)); 
		    	double dd = b.setScale(1, RoundingMode.HALF_UP).doubleValue();
		    	sm.set(i, dd);
		    }
		    for(int i=0; i<xm.size(); i++) {
		    	BigDecimal b = new BigDecimal(xm.get(i));
		    	double dd = b.setScale(1, RoundingMode.HALF_UP).doubleValue();
		    	xm.set(i, dd);
		    }
			map.put("above", sm);
			map.put("below", xm);
			Object data = this.queryDeptUser(name).getData();
			map.put("user", data);
			result = new Result(0,"查询成功",map);
		} catch (Exception e) {
			result = new Result(0,"查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	public Result queryDeptUser(String name) {
		Result result = null;
		try {
			//获取最新节点办理人
			
			User user = new User();
			String dept = performanceDao.queryDept(name);
			user.setDept(dept);
			Head userhead = approveDao.userhead(name);
			if(null != userhead) {
				String save_NAME = userhead.getSAVE_NAME();
				String extention = userhead.getEXTENTION();
				String filepath = "/"+save_NAME+"."+extention;
				user.setHeadPath(filepath);
			}else{
				user.setHeadPath("/head.jpg");
			}
			user.setNAME(name);
			result = new Result(0,"查询成功",user);
		} catch (Exception e) {
			result = new Result(1,"查询失败",null);
			e.printStackTrace();
		}
		return result;
	}
}
