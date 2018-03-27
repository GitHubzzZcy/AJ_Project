package com.henghao.istration.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.henghao.istration.dao.IApproveDao;
import com.henghao.istration.entity.Example;
import com.henghao.istration.entity.Head;
import com.henghao.istration.entity.Hzlog;
import com.henghao.istration.entity.Logtime;
import com.henghao.istration.entity.Plan;
import com.henghao.istration.entity.Promise;
import com.henghao.istration.entity.Transactor;
import com.henghao.istration.service.IApproveService;
import com.henghao.istration.util.DateUtils;
import com.henghao.istration.util.Result;
@Service("approveService")
public class ApproveServiceImpl  implements IApproveService{
	
	@Resource
	private IApproveDao approveDao;
	Map<String, Object> map = new HashMap<String, Object>();
	Map<String, Object> resultmap = new HashMap<String, Object>();
	
	//统计每类有多少条数据 tw_hz_instance
	@Override
	public Result queryApproveClass() {
		Result result = null;
		try {
			List<Object>  list = approveDao.queryApproveClass();
			result = new Result(0,"查询成功",list);
		} catch (Exception e) {
			result = new Result(1,"系统繁忙",null);
			e.printStackTrace();
		}
		return result;
	}

	//查询每类有那些企业
	//根据flowname动态生成sql
	@Override
	public Result queryfirmname(String flowid) {
		Result result = null;
		try {
			List<Example> list =  approveDao.queryfirmname(flowid);
			result = new Result(0,"查询成功",list);
		} catch (Exception e) {
			result = new Result(1,"系统繁忙",null);
			e.printStackTrace();
		}
		return result;
	}

	//查询企业的审批进度
	//拿到申请单id查询twr_hz_instance 表拿到workid再查询tw_hz_log表，同时拿到userid查询用户头像
	@Override
	public Result findByApplyid(String applyid) {
		Result result = null;
		String type ="";
		List<Plan> planlist = new ArrayList<Plan>();
		List<Hzlog> loglist = new ArrayList<Hzlog>();
		
		try {
			String workid = approveDao.queryWorkid(applyid);
			List<Hzlog> list = approveDao.queryLog(workid);
			//最后提交一次的时间到当前时间的天数
			System.out.println(list.size());
			String actiontime2 = list.get(list.size()-1).getACTIONTIME();
			Date date3 = DateUtils.parseDateFromString(actiontime2);
			Date currentdate = new Date();
			int cdate = DateUtils.daysBetween(date3, currentdate)+1;
			int workday = 0;
			Calendar cals = Calendar.getInstance();
			cals.setTime(date3);
			while (!cals.getTime().after(currentdate)) {																		
				int w = cals.get(Calendar.DAY_OF_WEEK);
				if (w == 1 || w == 7) {
					workday++;
				}
				cals.add(Calendar.DAY_OF_MONTH, 1);						
			}
			Date startdate=null;
			if("Start".equals(list.get(0).getNODEID())) {
				//提交资料时的上一个周日
				startdate = DateUtils.parseDateFromString(list.get(1).getACTIONTIME());
				if("星期六".equals(DateUtils.getWeekNum(startdate))) {
					startdate = DateUtils.getRearDay(startdate);
				}else{
					for(int u=0; u<7; u++) {
						String weekNum2 = DateUtils.getWeekNum(startdate);
						if("星期日".equals(weekNum2)) {
							break;						
						}
						startdate = DateUtils.getNextDay(startdate);
					}
				}
			}
			//数据库获取办理人
			 Transactor  yichublr = approveDao.queryblr("yichu");
			 Transactor  sconeblr = approveDao.queryblr("sconeblr");
			 Transactor  sctwoblr = approveDao.queryblr("sctwoblr");
			for (int i=0; i<list.size(); i++) {
				Hzlog hzlog = null;
				if(i>0) {
					hzlog = list.get(i-1);
				}else{
					hzlog = list.get(i);
				}
				//上节点的提交时间
				String actiontime = hzlog.getACTIONTIME();
				Date date = DateUtils.parseDateFromString(actiontime);
				Hzlog hzlog2 = list.get(i);
				Date date2 = DateUtils.parseDateFromString(hzlog2.getACTIONTIME());
				int daysBetween = DateUtils.daysBetween(date, date2)+1;
				if("feimeikuangshan(yichu)".equals(list.get(1).getFLOWID())) {
					type = list.get(1).getFLOWNAME();
					String[] names ={"政务大厅","一处审核","专家意见","审查会","分管局长审核","局长审核"};
					String[] nodeid ={"Node3","Node4","Node5","Node6","Node7","Node8"};
					String[] users = {yichublr.getNode3(),yichublr.getNode4(),yichublr.getNode5(),yichublr.getNode6(),yichublr.getNode7(),yichublr.getNode8()};
					int x = 0;
					for(int j=0; j<names.length; j++) {
						if(nodeid[j].equals(list.get(i).getNODEID())){
							Plan plan = new Plan();
							plan.setWeekend(DateUtils.getFormatedDate(startdate, "yyyy-MM-dd"));
							plan.setTitle(names[j]);
							if("Node4".equals(nodeid[j])) {
								String actiontime3 = list.get(i).getACTIONTIME();
								Date dateweek = DateUtils.parseDateFromString(actiontime3);
								Integer weekNum = DateUtils.getWeekNumber(dateweek);
								plan.setWeek(String.valueOf(weekNum));
							}
							plan.setDate(daysBetween);
							plan.setWorkday(daysBetween);
							plan.setApproveName(users[j]);
							plan.setState("已通过");
							plan.setApproveTime(list.get(i).getACTIONTIME());
							String dept = approveDao.queryUser(users[j]);
							plan.setDepartment(dept);
							Head head = approveDao.userhead(users[j]);
							if(null != head) {
								//String save_PATH = head.getSAVE_PATH();
								String save_NAME = head.getSAVE_NAME();
								String extention = head.getEXTENTION();
								String filepath = "/"+save_NAME+"."+extention;
								plan.setImg(filepath);
							}else{
								plan.setImg("/head.jpg");
							}
							planlist.add(plan);
							loglist.add(list.get(i));
							x=j;
						}else if (i == list.size()-1 && nodeid[x].equals(list.get(i).getNODEID())){
							if(nodeid[x+1].equals(nodeid[j])) {
								Plan plan = new Plan();
								plan.setTitle(names[j]);
								plan.setDate(cdate);
								plan.setWorkday(workday);
								plan.setApproveName(users[j]);
								
								plan.setState("办理中");
								String dept = approveDao.queryUser(users[j]);
								plan.setDepartment(dept);
								Head head = approveDao.userhead(users[j]);
								if(null != head) {
									//String save_PATH = head.getSAVE_PATH();
									String save_NAME = head.getSAVE_NAME();
									String extention = head.getEXTENTION();
									String filepath = "/"+save_NAME+"."+extention;
									plan.setImg(filepath);
								}else{
									plan.setImg("/head.jpg");
								}
								planlist.add(plan);
							}else{
								Plan plan = new Plan();
								plan.setTitle(names[j]);
								plan.setDate(2);
								plan.setWorkday(2);
								plan.setApproveName(users[j]);
								plan.setState("未进行");
								String dept = approveDao.queryUser(users[j]);
								plan.setDepartment(dept);
								Head head = approveDao.userhead(users[j]);
								if(null != head) {
									//String save_PATH = head.getSAVE_PATH();
									String save_NAME = head.getSAVE_NAME();
									String extention = head.getEXTENTION();
									String filepath = "/"+save_NAME+"."+extention;
									plan.setImg(filepath);
								}else{
									plan.setImg("/head.jpg");
								}
								planlist.add(plan);
							}
						}
					}
				}
				if("sichu(chucishenqing)".equals(list.get(1).getFLOWID())) {
					type = list.get(1).getFLOWNAME();
					String[] names ={"四处资料审查","评审机构与专家评审","现场查看","开论证会","企业改正","现场核实（评审机构）","处室审核","领导审批"};
					String[] nodeid ={"Node3","Node4","Node5","Node6","Node7","Node8","Node9","Node10"};
					//String[] users ={"胡美琪","安尤光","邓发权","胡正康","兰天","邓发权","安尤光","安尤光"};
					String[] users = {sconeblr.getNode3(),sconeblr.getNode4(),sconeblr.getNode5(),sconeblr.getNode6()
					,sconeblr.getNode7(),sconeblr.getNode8(),sconeblr.getNode9(),sconeblr.getNode10()};
					int x = 0;
					for(int j=0; j<names.length; j++) {
						if(nodeid[j].equals(list.get(i).getNODEID())){
							Plan plan = new Plan();
							plan.setWeekend(DateUtils.getFormatedDate(startdate, "yyyy-MM-dd"));
							plan.setTitle(names[j]);
							if("Node3".equals(nodeid[j])) {
								String actiontime3 = list.get(i).getACTIONTIME();
								Date dateweek = DateUtils.parseDateFromString(actiontime3);
								Integer weekNum = DateUtils.getWeekNumber(dateweek);
								plan.setWeek(String.valueOf(weekNum));
							}
							plan.setDate(daysBetween);
							plan.setWorkday(daysBetween);
							plan.setApproveName(users[j]);
							plan.setState("已通过");
							plan.setApproveTime(list.get(i).getACTIONTIME());
							String dept = approveDao.queryUser(users[j]);
							plan.setDepartment(dept);
							Head head = approveDao.userhead(users[j]);
							if(null != head) {
								//String save_PATH = head.getSAVE_PATH();
								String save_NAME = head.getSAVE_NAME();
								String extention = head.getEXTENTION();
								String filepath = "/"+save_NAME+"."+extention;
								plan.setImg(filepath);
							}else{
								plan.setImg("/head.jpg");
							}
							planlist.add(plan);
							loglist.add(list.get(i));
							x=j;
						}else if (i == list.size()-1 && nodeid[x].equals(list.get(i).getNODEID())){
							if(nodeid[x+1].equals(nodeid[j])) {
								Plan plan = new Plan();
								plan.setTitle(names[j]);
								plan.setDate(cdate);
								plan.setWorkday(workday);
								plan.setApproveName(users[j]);
								plan.setState("办理中");
								String dept = approveDao.queryUser(users[j]);
								plan.setDepartment(dept);
								Head head = approveDao.userhead(users[j]);
								if(null != head) {
									//String save_PATH = head.getSAVE_PATH();
									String save_NAME = head.getSAVE_NAME();
									String extention = head.getEXTENTION();
									String filepath = "/"+save_NAME+"."+extention;
									plan.setImg(filepath);
								}else{
									plan.setImg("/head.jpg");
								}
								planlist.add(plan);
							}else{
								Plan plan = new Plan();
								plan.setTitle(names[j]);
								plan.setDate(2);
								plan.setWorkday(2);
								plan.setApproveName(users[j]);
								plan.setState("未进行");
								String dept = approveDao.queryUser(users[j]);
								plan.setDepartment(dept);
								Head head = approveDao.userhead(users[j]);
								if(null != head) {
									//String save_PATH = head.getSAVE_PATH();
									String save_NAME = head.getSAVE_NAME();
									String extention = head.getEXTENTION();
									String filepath = "/"+save_NAME+"."+extention;
									plan.setImg(filepath);
								}else{
									plan.setImg("/head.jpg");
								}
								planlist.add(plan);
							}
						}
					}
				}
				if("sichu(fushen)".equals(list.get(1).getFLOWID())) {
					type = list.get(1).getFLOWNAME();
					String[] names ={"处室查看","处室查看","领导审批"};
					String[] nodeid ={"Node2","Node2","Node3"};
					//String[] users ={"胡美琪","兰天"};
					String[] users = {sctwoblr.getNode2(),sctwoblr.getNode4(),sctwoblr.getNode3()};
					int x = 0;
					for(int j=0; j<users.length; j++) {
						if(users[j].equals(list.get(i).getUSERNAME()) && !"Start".equals(list.get(i).getNODEID())){
							Plan plan = new Plan();
							plan.setWeekend(DateUtils.getFormatedDate(startdate, "yyyy-MM-dd"));
							plan.setTitle(names[j]);
							if("Node2".equals(nodeid[j])) {
								String actiontime3 = list.get(i).getACTIONTIME();
								Date dateweek = DateUtils.parseDateFromString(actiontime3);
								Integer weekNum = DateUtils.getWeekNumber(dateweek);
								plan.setWeek(String.valueOf(weekNum));
							}
							plan.setDate(daysBetween);
							plan.setWorkday(daysBetween);
							plan.setApproveName(users[j]);
							plan.setState("已通过");
							plan.setApproveTime(list.get(i).getACTIONTIME());
							String dept = approveDao.queryUser(users[j]);
							plan.setDepartment(dept);
							Head head = approveDao.userhead(users[j]);
							if(null != head) {
								//String save_PATH = head.getSAVE_PATH();
								String save_NAME = head.getSAVE_NAME();
								String extention = head.getEXTENTION();
								String filepath = "/"+save_NAME+"."+extention;
								plan.setImg(filepath);
							}else{
								plan.setImg("/head.jpg");
							}
							planlist.add(plan);
							loglist.add(list.get(i));
							x=j;
						}else if (i == list.size()-1 && users[x].equals(list.get(i).getUSERNAME())){
							if(users[j-1].equals(list.get(i).getUSERNAME())) {
								Plan plan = new Plan();
								plan.setTitle(names[j]);
								plan.setDate(cdate);
								plan.setWorkday(workday);
								plan.setApproveName(users[j]);
								plan.setState("办理中");
								String dept = approveDao.queryUser(users[j]);
								plan.setDepartment(dept);
								Head head = approveDao.userhead(users[j]);
								if(null != head) {
									//String save_PATH = head.getSAVE_PATH();
									String save_NAME = head.getSAVE_NAME();
									String extention = head.getEXTENTION();
									String filepath = "/"+save_NAME+"."+extention;
									plan.setImg(filepath);
								}else{
									plan.setImg("/head.jpg");
								}
								planlist.add(plan);
							}else {
								Plan plan = new Plan();
								plan.setTitle(names[j]);
								plan.setDate(2);
								plan.setWorkday(2);
								plan.setApproveName(users[j]);
								plan.setState("未进行");
								String dept = approveDao.queryUser(users[j]);
								plan.setDepartment(dept);
								Head head = approveDao.userhead(users[j]);
								if(null != head) {
									//String save_PATH = head.getSAVE_PATH();
									String save_NAME = head.getSAVE_NAME();
									String extention = head.getEXTENTION();
									String filepath = "/"+save_NAME+"."+extention;
									plan.setImg(filepath);
								}else{
									plan.setImg("/head.jpg");
								}
								planlist.add(plan);
							}
						}
					}
				}
			}
			//如果出现退回，需要把之前已通过的数据删除,还需要把审批时退后的那条数据删除
			List<String> titlelist = new ArrayList<String>();
			for(int p=0; p<planlist.size(); p++) {
				String title = planlist.get(p).getTitle();
				titlelist.add(title);
				for(int t=0; t<titlelist.size(); t++) {
					String string = titlelist.get(t);
					int indexOf = titlelist.indexOf(string);
					int lastIndexOf = titlelist.lastIndexOf(string);
					if(indexOf != lastIndexOf) {
						planlist.set(indexOf, null);
						//planlist.set(indexOf+1, null);
					}
				}
			}
			for(int p=planlist.size()-1; p>=0; p--) {
				if(planlist.get(p)==null) {
					planlist.remove(p);
				}
			}
			//日志重复审批数据处理
			List<String> nodeids = new ArrayList<String>();
			int nod2=0; int nod3=0; int nod4=0; int nod5=0; int nod6=0; 
			int nod7=0;int nod8=0; int nod9=0; int nod10=0;
			StringBuffer Node2 = new StringBuffer();
			StringBuffer Node3 = new StringBuffer();
			StringBuffer Node4 = new StringBuffer();
			StringBuffer Node5 = new StringBuffer();
			StringBuffer Node6 = new StringBuffer();
			StringBuffer Node7 = new StringBuffer();
			StringBuffer Node8 = new StringBuffer();
			StringBuffer Node9 = new StringBuffer();
			StringBuffer Node10 = new StringBuffer();
			String name2 = new String();
			String name3 = new String();
			String name4 = new String();
			String name5 = new String();
			String name6 = new String();
			String name7 = new String();
			String name8 = new String();
			String name9 = new String();
			String name10 = new String();
			for(int g=0; g<loglist.size(); g++) {
				String nodeid = loglist.get(g).getNODEID();
				nodeids.add(nodeid);
			}
			for(int n=0; n<nodeids.size(); n++) {
				if("Node2".equals(nodeids.get(n))) {
					String time = loglist.get(n).getACTIONTIME();
					name2 = loglist.get(n).getNODENAME();
					Node2.append(time+",");
					nod2++;
				}
				if("Node3".equals(nodeids.get(n))) {
					String time = loglist.get(n).getACTIONTIME();
					name3 = loglist.get(n).getNODENAME();
					Node3.append(time+",");
					nod3++;
				}
				if("Node4".equals(nodeids.get(n))) {
					String time = loglist.get(n).getACTIONTIME();
					name4 = loglist.get(n).getNODENAME();
					Node4.append(time+",");
					nod4++;
				}
				if("Node5".equals(nodeids.get(n))) {
					String time = loglist.get(n).getACTIONTIME();
					name5 = loglist.get(n).getNODENAME();
					Node5.append(time+",");
					nod5++;
				}
				if("Node6".equals(nodeids.get(n))) {
					String time = loglist.get(n).getACTIONTIME();
					name6 = loglist.get(n).getNODENAME();
					Node6.append(time+",");
					nod6++;
				}
				if("Node7".equals(nodeids.get(n))) {
					String time = loglist.get(n).getACTIONTIME();
					name7 = loglist.get(n).getNODENAME();
					Node7.append(time+",");
					nod7++;
				}
				if("Node8".equals(nodeids.get(n))) {
					String time = loglist.get(n).getACTIONTIME();
					name8 = loglist.get(n).getNODENAME();
					Node8.append(time+",");
					nod8++;
				}
				if("Node9".equals(nodeids.get(n))) {
					String time = loglist.get(n).getACTIONTIME();
					name9 = loglist.get(n).getNODENAME();
					Node9.append(time+",");
					nod9++;
				}
				if("Node10".equals(nodeids.get(n))) {
					String time = loglist.get(n).getACTIONTIME();
					name10 = loglist.get(n).getNODENAME();
					Node10.append(time+",");
					nod10++;
				}
				
				}
			String Nodes2 = null;
			if(Node2.length() != 0) {
				Nodes2 = Node2.substring(0, Node2.length()-1);
			}
			String Nodes3 = null;
			if(Node3.length() != 0) {
				Nodes3 = Node3.substring(0, Node3.length()-1);
			}
			String Nodes4 = null;
			if(Node4.length() != 0) {
				Nodes4 = Node4.substring(0, Node4.length()-1);
			}
			String Nodes5 = null;
			if(Node5.length() != 0) {
				Nodes5 = Node5.substring(0, Node5.length()-1);
			}
			String Nodes6 = null;
			if(Node6.length() != 0) {
				Nodes6 = Node6.substring(0, Node6.length()-1);
			}
			String Nodes7 = null;
			if(Node7.length() != 0) {
				Nodes7 = Node7.substring(0, Node7.length()-1);
			}
			String Nodes8 = null;
			if(Node8.length() != 0) {
				Nodes8 = Node8.substring(0, Node8.length()-1);
			}
			String Nodes9 = null;
			if(Node9.length() != 0) {
				Nodes9 = Node9.substring(0, Node9.length()-1);
			}
			String Nodes10 = null;
			if(Node10.length() != 0) {
				Nodes10 = Node10.substring(0, Node10.length()-1);
			}
			
			Logtime  log2 = new Logtime();
			Logtime  log3 = new Logtime();
			Logtime  log4 = new Logtime();
			Logtime  log5 = new Logtime();
			Logtime  log6 = new Logtime();
			Logtime  log7 = new Logtime();
			Logtime  log8 = new Logtime();
			Logtime  log9 = new Logtime();
			Logtime  log10 = new Logtime();
			log2.setTime(nod2);
			log3.setTime(nod3);
			log4.setTime(nod4);
			log5.setTime(nod5);
			log6.setTime(nod6);
			log7.setTime(nod7);
			log8.setTime(nod8);
			log9.setTime(nod9);
			log10.setTime(nod10);
			log2.setApproveTime(Nodes2);
			log3.setApproveTime(Nodes3);
			log4.setApproveTime(Nodes4);
			log5.setApproveTime(Nodes5);
			log6.setApproveTime(Nodes6);
			log7.setApproveTime(Nodes7);
			log8.setApproveTime(Nodes8);
			log9.setApproveTime(Nodes9);
			log10.setApproveTime(Nodes10);
			log2.setTitle(name2);
			log3.setTitle(name3);
			log4.setTitle(name4);
			log5.setTitle(name5);
			log6.setTitle(name6);
			log7.setTitle(name7);
			log8.setTitle(name8);
			log9.setTitle(name9);
			log10.setTitle(name10);
			List<Logtime> logslist = new ArrayList<Logtime>();
			logslist.add(log2);
			logslist.add(log3);
			logslist.add(log4);
			logslist.add(log5);
			logslist.add(log6);
			logslist.add(log7);
			logslist.add(log8);
			logslist.add(log9);
			logslist.add(log10);
			for(int log =logslist.size()-1; log>=0 ;log--) {
				if(0 == logslist.get(log).getTime()) {
					logslist.remove(log);
				}
			}
			resultmap.put("hzlog", logslist);
			resultmap.put("plan", planlist);
			resultmap.put("type", type);
			result = new Result(0,"查询成功",resultmap);
		} catch (Exception e) {
			result = new Result(1,"系统繁忙",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result queryfirmAll() {
		Result result = null;
		try {
			List<Example> list = approveDao.queryfirmAll();
			result = new Result(0,"查询成功",list);
		} catch (Exception e) {
			result = new Result(1,"查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 承诺件超限统计，承诺件提前办结统计
	 * 2017-06-05
	 */
	@Override
	public Result promise() {
		Result result = null;
		try {
			List<Integer> chaoq = new ArrayList<Integer>();
			List<Integer> tiq = new ArrayList<Integer>();
			int[] list = {0,0,0,0,0,0,0,0,0,0,0,0};
			String mothe = DateUtils.getCurrentDate("MM");
			String yer = DateUtils.getCurrentDate("yyyy");
			//查询工作流录入的承诺办件数
			List<Promise> chengnuo = approveDao.promise(yer);
			for (Promise promise : chengnuo) {
				int mouth = Integer.parseInt(promise.getMouth());
				switch (mouth) {
				case 1 : list[0] = Integer.parseInt(promise.getNumber());
					break;
				case 2 : list[1] = Integer.parseInt(promise.getNumber());
					break;
				case 3 : list[2] = Integer.parseInt(promise.getNumber());
					break;
				case 4 : list[3] = Integer.parseInt(promise.getNumber());
					break;
				case 5 : list[4] = Integer.parseInt(promise.getNumber());
					break;
				case 6 : list[5] = Integer.parseInt(promise.getNumber());
					break;
				case 7 : list[6] = Integer.parseInt(promise.getNumber());
					break;
				case 8 : list[7] = Integer.parseInt(promise.getNumber());
					break;
				case 9 : list[8] = Integer.parseInt(promise.getNumber());
					break;
				case 10 : list[9] = Integer.parseInt(promise.getNumber());
					break;
				case 11 : list[10] = Integer.parseInt(promise.getNumber());
					break;
				case 12 : list[11] = Integer.parseInt(promise.getNumber());
					break;
				default:
					break;
				}
			}
			int m = Integer.parseInt(mothe);
			//获取之前月份的办件总数
			for(int i=1; i<m; i++) {
				String ym = null;
				if(i<10) {
					ym = yer + "-0" + String.valueOf(i);
				}else{
					ym = yer + "-" + String.valueOf(i);
				}
				String counts = approveDao.before(ym);
				int count = Integer.parseInt(counts);
				int cn = list[i-1];
				int chao = 0;
				if(count > cn) {
					chao = count-cn;
				}
				chaoq.add(chao);
			}
			
			String currentDate = DateUtils.getCurrentDate("yyyy-MM");
			String currentDate2 = DateUtils.getCurrentDate("yyyy-MM-dd HH-mm-ss");
			//查询当月到当前时间的已办件数
			String currentcount = approveDao.promise(currentDate,currentDate2);
			int count = Integer.parseInt(currentcount);
			int current = list[list.length-1];
			int mchao = 0;
			if(count > current) {
				mchao = count - current;
			}
			chaoq.add(mchao);
			for(int i=12; i>m; i--) {
				chaoq.add(0);
			}
			int yij = 0;
			int erj = 0;
			int sanj = 0;
			int sij = 0;
			int yiq = 0;
			int erq = 0;
			int sanq = 0;
			int siq = 0;
			for (int i=0; i<chaoq.size(); i++) {
				if(i < 3) {
					yij = chaoq.get(i) + yij;
					yiq = list[i] + yiq;
				}
				if(i > 2 && i < 6) {
					erj = chaoq.get(i) + erj;
					erq =  list[i] + erq;
				}
				if(i > 5 && i < 9) {
					sanj = chaoq.get(i) + sanj;
					sanq = list[i] + sanq;
				}
				if(i > 8 && i < 12) {
					sij = chaoq.get(i) + sij;
					siq = list[i] +siq;
				}
			}
			if(yij > yiq) {
				tiq.add(yij-yiq);
			}else{
				tiq.add(0);
			}
			if(erj > erq) {
				tiq.add(erj-erq);
			}else{
				tiq.add(0);
			}
			if(sanj > sanq) {
				tiq.add(sanj-sanq);
			}else{
				tiq.add(0);
			}
			if(sij > siq) {
				tiq.add(sij-siq);
			}else{
				tiq.add(0);
			}
			map.put("chaoxian", chaoq);
			map.put("tiqian", tiq);
			result = new Result(0,"查询成功",map);
		} catch (Exception e) {
			result = new Result(1,"查询失败",null);
			e.printStackTrace();
		}
		
		
		return result;
	}

	

	
}
