package com.henghao.istration.service.impl;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.henghao.istration.dao.IApproveDao;
import com.henghao.istration.dao.IUserApeDao;
import com.henghao.istration.entity.Backlog;
import com.henghao.istration.entity.FlowRoll;
import com.henghao.istration.entity.Head;
import com.henghao.istration.entity.Hzlog;
import com.henghao.istration.entity.Personnel;
import com.henghao.istration.service.IUserApeService;
import com.henghao.istration.util.DateUtils;
import com.henghao.istration.util.Result;
import com.henghao.istration.util.Urljson;

@Service("userApeService")
public class UserApeServiceImpl implements IUserApeService {

	@Resource
	private IUserApeDao userApeDao;
	
	@Resource
	private IApproveDao approveDao;
	
	Map<String, Object> remap = new HashMap<String, Object>();
	private static final String url = "http://127.0.0.1:8082/tongji/Enterise/findZfByDate?";
	
	@Override
	public Result queryApprove(String name) {
		Result result = null;
		try {
			List<String> list1 = new ArrayList<String>();
			List<String> list2 = new ArrayList<String>();
			List<String> list3 = new ArrayList<String>();
			List<String> list4 = new ArrayList<String>();
			//获取当前月最后一天2017-06-30
			String month4 = DateUtils.month4();
			String substring = month4.substring(8, 10);
			int day = Integer.parseInt(substring);
			for(int i=1 ;i<=day; i++) {
				String date = null;
				if(i<10) {
					date = month4.substring(0, 8) + "0" + i;
				}else{
					date = month4.substring(0, 8) + i;
				}
				String count1 = userApeDao.queryApprove(name,"feimeikuangshan_yichu",date);
				String count2 = userApeDao.queryApprove(name,"aqfw",date);
				String count3 = userApeDao.queryApprove(name,"sichu_fushen",date);
				String count4 = userApeDao.queryApprove(name,"sichu_chucishenqing",date);
				list1.add(count1);
				list2.add(count2);
				list3.add(count3);
				list4.add(count4);
			}
			remap.clear();
			remap.put("feimeikuangshan_yichu", list1);
			remap.put("aqfw", list1);
			remap.put("sichu_fushen", list1);
			remap.put("sichu_chucishenqing", list1);
			result = new Result(0,"查询成功",remap);
		} catch (Exception e) {
			result = new Result(1,"查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result queryBacklog(String name) {
		Result result = null;
		try {
			List<Object> relist = new ArrayList<Object>();
			String userid = this.queryuserid(name);
			//查询待办
			String count3 = userApeDao.queryBacklog(userid);
			//查询已办
			String count4 = userApeDao.queryFinish(userid);
			//查询超期临期
			List<Backlog> list = userApeDao.queryOvertime(userid);
			int count1 = 0;
			int count2 = 0;
			for (Backlog backlog : list) {
				String times = backlog.getSENDTIME();
				Date senttime = DateUtils.parseDateFromString(times);
				String limittime = backlog.getLIMITTIME();
				//时限多少毫秒
				long ovday = Long.parseLong(limittime.substring(0, limittime.length()-3));
				if(ovday == 0) {
					//如果是0天，怎默认为一天
					ovday = 86400000;
				}else{
					ovday = 86400000 * ovday;
				}
				//实际多少毫秒
				long timesBetween = DateUtils.timesBetween(senttime, new Date());
				//超期
				if(timesBetween > ovday) {
					count1++;
				}
				//临期   离超期还差两天算临期
				if(timesBetween + 86400000*2 > ovday) {
					count2++;
				}
			}
			//被退回多少次
			String count5 = userApeDao.querySendback(name);
			relist.add(count1);
			relist.add(count2);
			relist.add(count3);
			relist.add(count4);
			relist.add(count5);
			result = new Result(0,"查询成功",relist);
		} catch (Exception e) {
			result = new Result(1,"查询失败",null);
			e.printStackTrace();
		}
		return result;
	}
	//根据人名查id
	public String queryuserid (String name) {
		return userApeDao.queryuserid(name);
	}

	@Override
	public Result queryPromise(String name) {
		Result result = null;
		try {
			NumberFormat df = NumberFormat.getNumberInstance();
			//保留两位小数
			df.setMaximumFractionDigits(2);
			//四舍五入
			df.setRoundingMode(RoundingMode.UP);
			String year = DateUtils.getCurrentDate("yyyy-");
			int month = Integer.parseInt(DateUtils.getCurrentDate("MM"));
			//查询执法队伍名
			String dept = userApeDao.queryDeptno(name);
			//查询执法队伍人数
			String pscount = userApeDao.findDeptPs(dept);
			//返回超限图
			List<String> list = new ArrayList<String>();
			//个人承诺
			List<Double> grlist = new ArrayList<Double>();
			//个人已完成办件
			List<Integer> grbj = new ArrayList<Integer>();
			//查询工作流录入的数据
			for(int i=1; i<13; i++) {
				String ym = null;
				if(i < 10) {
					ym = year + "0" + i;
				}else{
					ym = year + i;
				}
				//查询部门录入的承诺数据
				String deptcount = userApeDao.queryDeptPromise(dept,ym);
				double promise = 0;
				if(Integer.parseInt(deptcount) != 0) {
					//本月个人承诺件数
					promise = Integer.parseInt(deptcount) / Integer.parseInt(pscount);
				}
				grlist.add(promise);
				//获取实际办件数
				//从执法获取
				JSONObject urljson = Urljson.readJsonFromUrl(url + "?names=" + name + "&date=" + ym);
				int zfcount = Integer.parseInt(urljson.get("data").toString());
				//从工作流获取
				int spcount = Integer.parseInt(userApeDao.queryMonthFinish(this.queryuserid(name),ym));
				double chao = zfcount + spcount - promise;
				if(chao < 0) {
					chao = 0;
				}
				grbj.add(zfcount + spcount);
				double redouble = chao / promise;
				if(i <= month) {
					if(promise == 0) {
						list.add("0");
					}
					list.add(df.format(redouble));
				}else{
					list.add("0");
				}
			}
			
			double yij = 0;
			int yijb = 0;
			double erj = 0;
			int erjb = 0;
			double sanj = 0;
			int sanjb = 0;
			double sij = 0;
			int sijb = 0;
			//返回提前
			List<String> rejlist = new ArrayList<String>();
			for (int i=0; i<12; i++) {
				if(i<3) {
					//季度总承诺办件
					yij = grlist.get(i)+yij;
					//季度已办件总数
					yijb = grbj.get(i) + yijb;
				}
				if(i<6 & i>2) {
					erj = grlist.get(i)+erj;
					erjb = grbj.get(i) + erjb;
				}
				
				if(i<9 & i>5) {
					sanj = grlist.get(i)+sanj;
					sanjb = grbj.get(i) + sanjb;
				}else{
					sij = grlist.get(i)+sij;
					sijb = grbj.get(i) + sijb;
				}
			}
			//一季度
			double reyj = 0;
			if(yijb-yij < 0) {
				reyj = 0;
			}else{
				reyj = yijb-yij;
			}
			rejlist.add(df.format(reyj/yij));
			//二季度
			double reerj = 0;
			if(erjb-erj < 0) {
				reerj = 0;
			}else{
				reerj = erjb-erj;
			}
			rejlist.add(df.format(reerj/erj));
			//三季度
			double resanj = 0;
			if(sanjb-sanj < 0) {
				resanj = 0;
			}else{
				resanj = sanjb-sanj;
			}
			rejlist.add(df.format(resanj/sanj));
			//四季度
			double resij = 0;
			if(sijb-sij < 0) {
				resij = 0;
			}else{
				resij = sijb-sij;
			}
			rejlist.add(df.format(resij/sij));
			remap.clear();
			remap.put("chaoxian", list);
			remap.put("tiqian", rejlist);
			result = new Result(0,"查询成功",remap);
		} catch (Exception e) {
			result = new Result(1,"查询失败",null);
			e.printStackTrace();
		}
		return result;
	}


	@Override
	public Result personnel(String name) {
		Result result = null;
		try {
			Head head = approveDao.userhead(name);
			Personnel p = userApeDao.queryPersonnel(name);
			if(null != p) {
				if(null != head) {
					String save_NAME = head.getSAVE_NAME();
					String extention = head.getEXTENTION();
					String filepath = "/"+save_NAME+"."+extention;
					p.setHeadPath(filepath);
					p.setList(userApeDao.queryGongzuolvli(p.getId()));
				}else{
					p.setHeadPath("/head.jpg");
				}
			}else{
				return result = new Result(0,"没有任免信息",null);
			}
			result = new Result(0,"查询成功", p);
		} catch (Exception e) {
			result = new Result(1,"查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result flowRoll(String name) {
		Result result = null;
		try {
			List<FlowRoll> relist = new ArrayList<FlowRoll>();
			int nian = Integer.parseInt(DateUtils.getCurrentDate("yyyy"));
			String month = DateUtils.getCurrentDate("MM-dd");
			String time = String.valueOf(nian-1) + "-" + month;
			List<Hzlog> hzlogs1 = userApeDao.flowRoll(time, "sichu(fushen)");
			List<String> nodes1 = null;
			String status1 = "未完成";
			if(hzlogs1.size() > 0) {
				nodes1 = userApeDao.queryNode("sichu(fushen)", hzlogs1.get(0).getWORKID());
				if(null != nodes1) {
					if(nodes1.contains("End4")){
						status1 = "已完成";
					}
				}
			}
			
			List<String> namelist = new ArrayList<String>();
			for (Hzlog hzlog : hzlogs1) {
				namelist.add(hzlog.getUSERNAME());
			}
			//最后一次出现的索引
			int lastIndexOf = namelist.lastIndexOf(name);
			if(-1 != lastIndexOf) {
				FlowRoll fr = new FlowRoll();
				fr.setType("行政审批");
				fr.setStatus(status1);
				fr.setTime(hzlogs1.get(lastIndexOf).getACTIONTIME());
				fr.setTitle("安全生产标准化审批流程(复审)");
				relist.add(fr);
			}
			
			
			/*********/
			List<Hzlog> hzlogs2 = userApeDao.flowRoll(time, "djbf");
			List<String> nodes2 = null;
			String status2 = "未完成";
			if(hzlogs2.size() > 0) {
				nodes2 = userApeDao.queryNode("djbf", hzlogs2.get(0).getWORKID());
				if(null != nodes2) {
					if(nodes2.contains("End1")){
						status2 = "已完成";
					}
				}
			}
			
			List<String> namelist2 = new ArrayList<String>();
			for (Hzlog hzlog : hzlogs2) {
				namelist2.add(hzlog.getUSERNAME());
			}
			//最后一次出现的索引
			int lastIndexOf2 = namelist2.lastIndexOf(name);
			if(-1 != lastIndexOf2) {
				FlowRoll fr = new FlowRoll();
				fr.setType("行政审批");
				fr.setStatus(status2);
				fr.setTime(hzlogs2.get(lastIndexOf2).getACTIONTIME());
				fr.setTitle("安全生产标准化审批流程(初次申请)");
				relist.add(fr);
			}
			/*************/
			List<Hzlog> hzlogs3 = userApeDao.flowRoll(time, "sichu(chucishenqing)");
			List<String> nodes3 = null;
			String status3 = "未完成";
			if(hzlogs3.size() > 0) {
				nodes3 = userApeDao.queryNode("sichu(chucishenqing)", hzlogs3.get(0).getWORKID());
				if(null != nodes3) {
					if(nodes3.contains("End4")){
						status3 = "已完成";
					}
				}
			}
			
			List<String> namelist3 = new ArrayList<String>();
			for (Hzlog hzlog : hzlogs3) {
				namelist3.add(hzlog.getUSERNAME());
			}
			//最后一次出现的索引
			int lastIndexOf3 = namelist3.lastIndexOf(name);
			if(-1 != lastIndexOf3) {
				FlowRoll fr = new FlowRoll();
				fr.setType("行政审批");
				fr.setStatus(status3);
				fr.setTime(hzlogs3.get(lastIndexOf3).getACTIONTIME());
				fr.setTitle("权限内非煤矿山安全生产许可登记颁发");
				relist.add(fr);
			}
			/*********/
			List<Hzlog> hzlogs4 = userApeDao.flowRoll(time, "aqfw");
			List<String> nodes4 = null;
			String status4 = "未完成";
			if(hzlogs4.size() > 0) {
				nodes4 = userApeDao.queryNode("aqfw", hzlogs4.get(0).getWORKID());
				if(null != nodes4) {
					if(nodes4.contains("End5")){
						status4 = "已完成";
					}
				}
			}
			
			List<String> namelist4 = new ArrayList<String>();
			for (Hzlog hzlog : hzlogs4) {
				namelist4.add(hzlog.getUSERNAME());
			}
			//最后一次出现的索引
			int lastIndexOf4 = namelist4.lastIndexOf(name);
			if(-1 != lastIndexOf4) {
				FlowRoll fr = new FlowRoll();
				fr.setType("行政审批");
				fr.setStatus(status4);
				fr.setTime(hzlogs4.get(lastIndexOf4).getACTIONTIME());
				fr.setTitle("权限内非煤矿山安全设施设计审查_安全服务");
				relist.add(fr);
			}
			/**********/
			List<Hzlog> hzlogs5 = userApeDao.flowRoll(time, "feimeikuangshan(yichu)");
			List<String> nodes5 = null;
			String status5 = "未完成";
			if(hzlogs5.size() > 0) {
				nodes5 = userApeDao.queryNode("feimeikuangshan(yichu)", hzlogs5.get(0).getWORKID());
				if(null != nodes5) {
					if(nodes5.contains("End9")){
						status5 = "已完成";
					}
				}
			}
			List<String> namelist5 = new ArrayList<String>();
			for (Hzlog hzlog : hzlogs5) {
				namelist5.add(hzlog.getUSERNAME());
			}
			//最后一次出现的索引
			int lastIndexOf5 = namelist5.lastIndexOf(name);
			if(-1 != lastIndexOf5) {
				FlowRoll fr = new FlowRoll();
				fr.setType("行政审批");
				fr.setStatus(status5);
				fr.setTime(hzlogs5.get(lastIndexOf5).getACTIONTIME());
				fr.setTitle("权限内非煤矿山安全设施设计审查");
				relist.add(fr);
			}
			
			/**********/
			List<Hzlog> hzlogs6 = userApeDao.flowRoll(time, "sanzhongyidahuiyishenq");
			List<String> nodes6 = null;
			String status6 = "未完成";
			if(hzlogs6.size() > 0) {
				nodes6 = userApeDao.queryNode("sanzhongyidahuiyishenq", hzlogs6.get(0).getWORKID());
				if(null != nodes6) {
					if(nodes6.contains("End1")){
						status6 = "已完成";
					}
				}
			}
			List<String> namelist6 = new ArrayList<String>();
			for (Hzlog hzlog : hzlogs6) {
				namelist6.add(hzlog.getUSERNAME());
			}
			//最后一次出现的索引
			int lastIndexOf6 = namelist6.lastIndexOf(name);
			if(-1 != lastIndexOf6) {
				FlowRoll fr = new FlowRoll();
				fr.setType("行政审批");
				fr.setStatus(status6);
				fr.setTime(hzlogs6.get(lastIndexOf6).getACTIONTIME());
				fr.setTitle("三重一大事项");
				relist.add(fr);
			}
			result = new Result(0,"查询成功", relist);
		} catch (Exception e) {
			result = new Result(1,"查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result yearCount(String name) {
		Result result = null;
		try {
			List<Integer> relist = new ArrayList<Integer>();
			for(int i=1; i<13; i++) {
				String date = null;
				if(i<10) {
					date = DateUtils.getCurrentDate("yyyy-0")+String.valueOf(i);
				}else{
					date = DateUtils.getCurrentDate("yyyy-")+String.valueOf(i);
				}
				System.out.println(name +"    " + date);
				int count = Integer.parseInt(userApeDao.queryApproveNotFlow(name, date));
				System.out.println(count);
				//int monthcount = count ;
				relist.add(count);
			}
			result = new Result(0,"查询成功", relist);
		} catch (Exception e) {
			result = new Result(1,"查询失败",null);
			e.printStackTrace();
		}
		return result;
	}
	

}
