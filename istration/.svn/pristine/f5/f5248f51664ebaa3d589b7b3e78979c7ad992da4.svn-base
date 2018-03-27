package com.henghao.news.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.henghao.news.dao.ApprovalProcessDao;
import com.henghao.news.entity.Hzlog;
import com.henghao.news.entity.WorkList;
import com.henghao.news.util.DateUtils;

@Service
public class ApprovalProcessService {

	@Resource
	private ApprovalProcessDao approvalProcessDao;
	@Resource
	private UserDataService userDataService;
	private Map<String, Object> remap = new HashMap<String, Object>();
	private static final String[] FLOWIDS = {"djbf", "aqfw", "feimeikuangshan(yichu)", "sichu(chucishenqing)", "sichu(fushen)"};
	private static final String[] TABSENAMES = {"AJ_QXNFMKSAQSCXKDJBF","AJ_QXNFMKSAQSSSJSCAQFF","AJ_QXNFMKSAQSSSJSC","AJ_SC_ONE","AJ_SC_TWO"};
	//审批中、已审批案件统计
	public Object queryAnjianTongji(String start, String end) throws ParseException {
		
		if("".equals(end) || end == null) {
			end = DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
		}
		if("".equals(start) || start == null) {
			start = DateUtils.getCurrentDate("yyyy")+"-01-01 00:00:00";
		}
		//获取节假日和周末
		List<String> holidayAndWeekend = userDataService.getHolidayAndWeekend();
		List<Integer> spz = new ArrayList<Integer>();
		List<Integer> ysp = new ArrayList<Integer>();
		int yspcount = 0; 
		int yspzc = 0; //正常 <a
		int yspyj = 0; //预警 <b
		int yspjg = 0; //警告=c
		int yspyzjg = 0; //严重警告>c
		//查询审批中的数据
		int spzcount = 0; 
		int spzzc = 0; //正常 <a
		int spzyj = 0; //预警 <b
		int spzjg = 0; //警告=c
		int spzyzjg = 0; //严重警告>c
		//查询已审批的
		//TODO
		int audit1 = approvalProcessDao.queryAuditPass(TABSENAMES[0], FLOWIDS[0],"End4;", start, end);
		int audit3 = approvalProcessDao.queryAuditPass(TABSENAMES[2], FLOWIDS[2],"End9;", start, end);
		int audit4 = approvalProcessDao.queryAuditPass(TABSENAMES[3], FLOWIDS[3],"End1;", start, end);
		int audit5 = approvalProcessDao.queryAuditPass(TABSENAMES[4], FLOWIDS[4],"End4;", start, end);
		yspcount = audit1  + audit3 + audit4 + audit5;
		//审批中，提交至审批节点，查日志表
		int notStart1 = approvalProcessDao.queryAuditStart(TABSENAMES[0], FLOWIDS[0],"Node3;", start, end);
		int notStart3 = approvalProcessDao.queryAuditStart(TABSENAMES[2], FLOWIDS[2],"Node8;", start, end);
		int notStart4 = approvalProcessDao.queryAuditStart(TABSENAMES[3], FLOWIDS[3],"Node10;", start, end);
		int notStart5 = approvalProcessDao.queryAuditStart(TABSENAMES[4], FLOWIDS[4],"Node3;", start, end);
		spzcount = notStart1 + notStart3 + notStart4 + notStart5;
		//查询已审批数据
		List<Hzlog> list = approvalProcessDao.queryAnjianTongjiCountFinishLogList(TABSENAMES[0], FLOWIDS[0],"End4;", start, end);
		list.addAll(approvalProcessDao.queryAnjianTongjiCountFinishLogList(TABSENAMES[2], FLOWIDS[2],"End9;", start, end));
		list.addAll(approvalProcessDao.queryAnjianTongjiCountFinishLogList(TABSENAMES[3], FLOWIDS[3],"End1;", start, end));
		list.addAll(approvalProcessDao.queryAnjianTongjiCountFinishLogList(TABSENAMES[4], FLOWIDS[4],"End4;", start, end));
		//分析已经审批的数据
		for (Hzlog hzlog : list) {
			//办理时限5
			String limitTime = hzlog.getLIMITTIME();
			int data = this.getLimitTime(limitTime);
			if(data == 0) {
				continue;
			}
			//办理用时5
			String dotime = hzlog.getDOTIME();
			String actiontime = hzlog.getACTIONTIME();
			int day = DateUtils.getDoActionTime(holidayAndWeekend, dotime, actiontime);
			//String[] split = dotime.split(" 天");
			//int day = Integer.parseInt(split[0]);
			if(day < data) {
				yspzc++;
			}
			if(day > data-data/2) {
				yspyj++;
			}else{
				if(data-data/2<day && day< data) {
					yspjg++;
				}else{
					if(day >= data){
						yspyzjg++;
					}
				}
			}
		}
		//分析审批中的数据
		List<Hzlog> unlist = approvalProcessDao.queryAnjianTongjiUnderway(TABSENAMES[0], FLOWIDS[0],"Node3;", start, end);
		unlist.addAll(approvalProcessDao.queryAnjianTongjiUnderway(TABSENAMES[2], FLOWIDS[2],"Node8;", start, end));
		unlist.addAll(approvalProcessDao.queryAnjianTongjiUnderway(TABSENAMES[3], FLOWIDS[3],"Node10;", start, end));
		unlist.addAll(approvalProcessDao.queryAnjianTongjiUnderway(TABSENAMES[4], FLOWIDS[4],"Node3;", start, end));
		for (Hzlog hzlog : unlist) {
			if("djbf".equals(hzlog.getFLOWID())) {
				if("Node3;".equals(hzlog.getNEXTNODEIDS())) {
					//办理时限
					String limitTime = hzlog.getLIMITTIME();
					int data = this.getLimitTime(limitTime);
					if(data == 0) {
						continue;
					}
					//提交到审批节点的时间
					//int day = DateUtils.daysBetween(DateUtils.parseDateFromString(hzlog.getACTIONTIME()), new Date());
					int day = DateUtils.getCurrentAndTime(holidayAndWeekend, hzlog.getACTIONTIME(), new Date());
					if(day < data) {
						spzzc++;
					}
					if(day > data-data/2) {
						spzyj++;
					}else{
						if(data-data/2<day && day< data) {
							spzjg++;
						}else{
							if(day >= data){
								spzyzjg++;
							}
						}
					}
				}
			}
			if("feimeikuangshan(yichu)".equals(hzlog.getFLOWID())) {
				if("Node8;".equals(hzlog.getNEXTNODEIDS())) {
					//办理时限
					String limitTime = hzlog.getLIMITTIME();
					int data = this.getLimitTime(limitTime);
					if(data == 0) {
						continue;
					}
					//提交到审批节点的时间
					int day = DateUtils.getCurrentAndTime(holidayAndWeekend, hzlog.getACTIONTIME(), new Date());
					if(day < data) {
						spzzc++;
					}
					if(day > data-data/2) {
						spzyj++;
					}else{
						if(data-data/2<day && day< data) {
							spzjg++;
						}else{
							if(day >= data){
								spzyzjg++;
							}
						}
					}
				}
			}
			if("sichu(chucishenqing)".equals(hzlog.getFLOWID())) {
				if("Node10;".equals(hzlog.getNEXTNODEIDS())) {
					//办理时限
					String limitTime = hzlog.getLIMITTIME();
					int data = this.getLimitTime(limitTime);
					if(data == 0) {
						continue;
					}
					//提交到审批节点的时间
					int day = DateUtils.getCurrentAndTime(holidayAndWeekend, hzlog.getACTIONTIME(), new Date());
					if(day < data) {
						spzzc++;
					}
					if(day > data-data/2) {
						spzyj++;
					}else{
						if(data-data/2<day && day< data) {
							spzjg++;
						}else{
							if(day >= data){
								spzyzjg++;
							}
						}
					}
				}
			}
			if("sichu(fushen)".equals(hzlog.getFLOWID())) {
				if("Node3;".equals(hzlog.getNEXTNODEIDS()) || "".equals(hzlog.getNEXTNODEIDS())) {
					//办理时限
					String limitTime = hzlog.getLIMITTIME();
					int data = this.getLimitTime(limitTime);
					if(data == 0) {
						continue;
					}
					//提交到审批节点的时间
					int day = DateUtils.getCurrentAndTime(holidayAndWeekend, hzlog.getACTIONTIME(), new Date());
					if(day < data) {
						spzzc++;
					}
					if(day > data-data/2) {
						spzyj++;
					}else{
						if(data-data/2<day && day< data) {
							spzjg++;
						}else{
							if(day >= data){
								spzyzjg++;
							}
						}
					}
				}
			}
		}
		
		spz.add(spzcount);
		spz.add(spzzc);
		spz.add(spzyj);
		spz.add(spzjg);
		spz.add(spzyzjg);
		ysp.add(yspcount);
		ysp.add(yspzc);
		ysp.add(yspyj);
		ysp.add(yspjg);
		ysp.add(yspyzjg);
		remap.clear();
		remap.put("spz", spz);
		remap.put("ysp", ysp);
		return remap;
	}
	
	//资料不全、资料有误、重复录入
	public Object queryAnjianTongjiZiliao(String start, String end) {
		if("".equals(end) || end == null) {
			end = DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
		}
		if("".equals(start) || start == null) {
			start = DateUtils.getCurrentDate("yyyy")+"-01-01 00:00:00";
		}
		
		List<Integer> list = new ArrayList<Integer>();
		String types[] = {"资料不全", "资料有误", "重复录入"};
		String[] DISPOSE_NODES = {"Node2", "Node6", "Node3", "Node3", "Node2"};
		for (String type : types) {
			int reStr = 0;
			reStr = approvalProcessDao.queryAnjianTongjiZiliao(DISPOSE_NODES[0], TABSENAMES[0], type, start, end) + 
					approvalProcessDao.queryAnjianTongjiZiliao(DISPOSE_NODES[1], TABSENAMES[1], type, start, end) + 
					approvalProcessDao.queryAnjianTongjiZiliao(DISPOSE_NODES[2], TABSENAMES[2], type, start, end) + 
					approvalProcessDao.queryAnjianTongjiZiliao(DISPOSE_NODES[3], TABSENAMES[3], type, start, end) + 
					approvalProcessDao.queryAnjianTongjiZiliao(DISPOSE_NODES[4], TABSENAMES[4], type, start, end) ;
			list.add(reStr);
		}
		return list;
	}
	//逾期审核、逾期审批
	public Object queryAnjianTongjiOverdueDispose(String start, String end) throws ParseException {
		
		if("".equals(end) || end == null) {
			end = DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
		}
		if("".equals(start) || start == null) {
			start = DateUtils.getCurrentDate("yyyy")+"-01-01 00:00:00";
		}
		//获取节假日和周末
		List<String> holidayAndWeekend = userDataService.getHolidayAndWeekend();
		//登记颁发Node2审核，Node3审批
		List<String> FLOWID1sh = new ArrayList<String>();
		List<String> FLOWID1sp = new ArrayList<String>();
		FLOWID1sh.add("Node2");
		FLOWID1sp.add("Node3");
		//安全服务
		List<String> FLOWID2sh = new ArrayList<String>();
		List<String> FLOWID2sp = new ArrayList<String>();
		FLOWID2sh.add("Node4");
		FLOWID2sh.add("Node6");
		//权限内非煤矿山安全设施设计审查   
		List<String> FLOWID3sh = new ArrayList<String>();
		List<String> FLOWID3sp = new ArrayList<String>();
		FLOWID3sh.add("Node3");
		FLOWID3sh.add("Node10");
		FLOWID3sh.add("Node4");
		FLOWID3sh.add("Node7");
		FLOWID3sp.add("Node8");
		//安全生产标准化审批流程(初次申请)
		List<String> FLOWID4sh = new ArrayList<String>();
		List<String> FLOWID4sp = new ArrayList<String>();
		FLOWID4sh.add("Node3");
		FLOWID4sh.add("Node9");
		FLOWID4sp.add("Node10");
		//安全生产标准化审批流程(复审)
		List<String> FLOWID5sh = new ArrayList<String>();
		List<String> FLOWID5sp = new ArrayList<String>();
		FLOWID5sh.add("Node2");
		FLOWID5sp.add("Node3");
		List<Integer> list1 = approvalProcessDao.queryAnjianTongjiOverdueDispose(TABSENAMES[0], FLOWIDS[0], FLOWID1sh, FLOWID1sp, start, end, holidayAndWeekend);
		List<Integer> list2 = approvalProcessDao.queryAnjianTongjiOverdueDispose(TABSENAMES[1], FLOWIDS[1], FLOWID2sh, FLOWID2sp, start, end, holidayAndWeekend);
		List<Integer> list3 = approvalProcessDao.queryAnjianTongjiOverdueDispose(TABSENAMES[2], FLOWIDS[2], FLOWID3sh, FLOWID3sp, start, end, holidayAndWeekend);
		List<Integer> list4 = approvalProcessDao.queryAnjianTongjiOverdueDispose(TABSENAMES[3], FLOWIDS[3], FLOWID4sh, FLOWID4sp, start, end, holidayAndWeekend);
		List<Integer> list5 = approvalProcessDao.queryAnjianTongjiOverdueDispose(TABSENAMES[4], FLOWIDS[4], FLOWID5sh, FLOWID5sp, start, end, holidayAndWeekend);
		int sh = list1.get(0) + list2.get(0) + list3.get(0) + list4.get(0)+ list5.get(0);
		int sp = list1.get(1) + list2.get(1) + list3.get(1) + list4.get(1)+ list5.get(1);
		remap.clear();
		remap.put("OverdueSH", sh);
		remap.put("OverdueSP", sp);
		return remap;
	}
	//逾期未审核、逾期未审批
	public Object queryAnjianTongjiOverdueNotDispose(String start, String end) {
		if("".equals(end) || end == null) {
			end = DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
		}
		if("".equals(start) || start == null) {
			start = DateUtils.getCurrentDate("yyyy")+"-01-01 00:00:00";
		}
		//获取节假日和周末
		List<String> holidayAndWeekend = userDataService.getHolidayAndWeekend();
		int wsh = 0;
		int wsp = 0;
		//根据流程查询出对应workid的最新一条数据
		//登记颁发
		List<WorkList> list1 = approvalProcessDao.queryLogNewestByFlowid(TABSENAMES[0],FLOWIDS[0], start, end);
		for (WorkList workList : list1) {
			if("Node2".equals(workList.getNODEID())) {
				if(DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date()) > this.getLimitTime(workList.getLIMITTIME())) {
					wsh++;
				}
				
			}
			if("Node3".equals(workList.getNODEID())) {
				if(DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date()) > this.getLimitTime(workList.getLIMITTIME())) {
					wsp++;
				}
			}
		}
		//安全服务
		List<WorkList> list2 = approvalProcessDao.queryLogNewestByFlowid(TABSENAMES[1], FLOWIDS[1], start, end);
		for (WorkList workList : list2) {
			if("Node4".equals(workList.getNODEID()) || "Node6".equals(workList.getNODEID())) {
				if(DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date()) > this.getLimitTime(workList.getLIMITTIME())) {
					wsh++;
				}
			}
		}
		//权限内非煤矿山安全设施设计审查   
		List<WorkList> list3 = approvalProcessDao.queryLogNewestByFlowid(TABSENAMES[2], FLOWIDS[2], start, end);
		for (WorkList workList : list3) {
			if("Node3".equals(workList.getNODEID()) || 
				"Node10".equals(workList.getNODEID()) ||
				"Node4".equals(workList.getNODEID()) ||
				"Node7".equals(workList.getNODEID())) {
				if(DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date()) > this.getLimitTime(workList.getLIMITTIME())) {
					wsh++;
				}
				
			}
			if("Node8".equals(workList.getNODEID())) {
				if(DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date()) > this.getLimitTime(workList.getLIMITTIME())) {
					wsp++;
				}
			}
		}
		//安全生产标准化审批流程(初次申请)
		List<WorkList> list4 = approvalProcessDao.queryLogNewestByFlowid(TABSENAMES[3], FLOWIDS[3], start, end);
		for (WorkList workList : list4) {
			if("Node3".equals(workList.getNODEID())  || 
				"Node9".equals(workList.getNODEID()) ) {
				if(DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date()) > this.getLimitTime(workList.getLIMITTIME())) {
					wsh++;
				}
			}
			if("Node10".equals(workList.getNODEID())) {
				if(DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date()) > this.getLimitTime(workList.getLIMITTIME())) {
					wsp++;
				}
			}
		}
		//安全生产标准化审批流程(复审)
		List<WorkList> list5 = approvalProcessDao.queryLogNewestByFlowid(TABSENAMES[4], FLOWIDS[4], start, end);
		for (WorkList workList : list5) {
			if("Node2".equals(workList.getNODEID())) {
				if(DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date()) > this.getLimitTime(workList.getLIMITTIME())) {
					wsh++;
				}
				
			}
			if("Node3".equals(workList.getNODEID())) {
				if(DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date()) > this.getLimitTime(workList.getLIMITTIME())) {
					wsp++;
				}
			}
		}
		remap.clear();
		remap.put("OverdueNotSH", wsh);
		remap.put("OverdueNotSP", wsp);
		return remap;
		
		
	}
	
	//违规审核、审批
	public Object queryManageIllegal(String start, String end) {
		if("".equals(end) || end == null) {
			end = DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
		}
		if("".equals(start) || start == null) {
			start = DateUtils.getCurrentDate("yyyy")+"-01-01 00:00:00";
		}
		int spIllegal = approvalProcessDao.queryManageIllegal("审批违纪", start, end);
		int shIllegal = approvalProcessDao.queryManageIllegal("审核违纪", start, end);
		remap.clear();
		remap.put("spIllegal", spIllegal);
		remap.put("shIllegal", shIllegal);
		return remap;
	}
	//异常审批汇总
	public Object queryAnjianTongjiAbnormal(String start, String end) throws Exception {
		if("".equals(end) || end == null) {
			end = DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
		}
		if("".equals(start) || start == null) {
			start = DateUtils.getCurrentDate("yyyy")+"-01-01 00:00:00";
		}
		//获取节假日和周末
		List<String> holidayAndWeekend = userDataService.getHolidayAndWeekend();
		//已审批的数据
		int yqbl = 0; //逾期办理
		int aqfw = 0; //安全服务总数
		//登记颁发
		List<Hzlog> list1 = approvalProcessDao.queryAnjianTongjiCountFinishLogList(TABSENAMES[0], FLOWIDS[0], "End4;", start, end);
		for (Hzlog hzlog : list1) {
			if("Node3".equals(hzlog.getNODEID())) {
				aqfw++;
				if(DateUtils.getDoActionTime(holidayAndWeekend, hzlog.getDOTIME(), hzlog.getACTIONTIME()) >= this.getLimitTime(hzlog.getLIMITTIME())) {
					yqbl++;
				}
			}
		}
		//权限内非煤矿山安全设施设计审查
		List<Hzlog> list3 = approvalProcessDao.queryAnjianTongjiCountFinishLogList(TABSENAMES[2], FLOWIDS[2], "End9;", start, end);
		for (Hzlog hzlog : list3) {
			if("Node8".equals(hzlog.getNODEID())) {
					aqfw++;
				if(DateUtils.getDoActionTime(holidayAndWeekend, hzlog.getDOTIME(), hzlog.getACTIONTIME()) >= this.getLimitTime(hzlog.getLIMITTIME())) {
					yqbl++;
				}
			}
		}
		//安全生产标准化审批流程(初次申请)
		List<Hzlog> list4 = approvalProcessDao.queryAnjianTongjiCountFinishLogList(TABSENAMES[3], FLOWIDS[3], "End1;", start, end);
		for (Hzlog hzlog : list4) {
			if("Node10".equals(hzlog.getNODEID())) {
				aqfw++;
				if(DateUtils.getDoActionTime(holidayAndWeekend, hzlog.getDOTIME(), hzlog.getACTIONTIME()) >= this.getLimitTime(hzlog.getLIMITTIME())) {
					yqbl++;
				}
			}
		}
		//安全生产标准化审批流程(复审)
		List<Hzlog> list5 = approvalProcessDao.queryAnjianTongjiCountFinishLogList(TABSENAMES[4], FLOWIDS[4], "End4;", start, end);
		for (Hzlog hzlog : list5) {
			if("Node3".equals(hzlog.getNODEID())) {
				aqfw++;
				if(DateUtils.getDoActionTime(holidayAndWeekend, hzlog.getDOTIME(), hzlog.getACTIONTIME()) >= this.getLimitTime(hzlog.getLIMITTIME())) {
					yqbl++;
				}
			}
		}
		//审批中的数据
		//TODO
		int spz = 0;//逾期未审批
		int spzcount = 0; //安全服务总数
		
		WorkList workList1 = approvalProcessDao.queryHuiZongSPZhong(TABSENAMES[0], FLOWIDS[0], "Node3", start, end);
		if(null != workList1) {
			spzcount++;
			if(DateUtils.getCurrentAndTime(holidayAndWeekend, workList1.getSENDTIME(), new Date()) >= this.getLimitTime(workList1.getLIMITTIME())){
				spz++;
			}
		}
		//权限内非煤矿山安全设施设计审查
		WorkList workList31 = approvalProcessDao.queryHuiZongSPZhong(TABSENAMES[2], FLOWIDS[2], "Node8", start, end);
		if(null != workList31) {
			if(null != workList31) {
				if(DateUtils.getCurrentAndTime(holidayAndWeekend, workList31.getSENDTIME(), new Date()) >= this.getLimitTime(workList31.getLIMITTIME())){
					spz++;
				}
			}
		}
		//安全生产标准化审批流程(初次申请)
		WorkList workList4 = approvalProcessDao.queryHuiZongSPZhong(TABSENAMES[3], FLOWIDS[3], "Node10", start, end);
		if(null != workList4) {
			spzcount++;
			if(DateUtils.getCurrentAndTime(holidayAndWeekend, workList4.getSENDTIME(), new Date()) >= this.getLimitTime(workList4.getLIMITTIME())){
				spz++;
			}
		}
		//安全生产标准化审批流程(复审)
		WorkList workList5 = approvalProcessDao.queryHuiZongSPZhong(TABSENAMES[4], FLOWIDS[4], "Node3", start, end);
		if(null != workList5) {
			spzcount++;
			if(DateUtils.getCurrentAndTime(holidayAndWeekend, workList5.getSENDTIME(), new Date()) >= this.getLimitTime(workList5.getLIMITTIME())){
				spz++;
			}
		}
		//违规办理
		@SuppressWarnings("unchecked")
		Map<String, Integer> map  = (Map<String, Integer>) this.queryManageIllegal(start, end);
		//已审批
		List<Integer> already = new ArrayList<Integer>();
		already.add(aqfw);
		already.add(yqbl);
		already.add(0);
		already.add(map.get("spIllegal"));
		//审批中
		List<Integer> underway = new ArrayList<Integer>();
		underway.add(spzcount);
		underway.add(0);
		underway.add(spz);
		underway.add(map.get("shIllegal"));
		remap.clear();
		remap.put("already", already);
		remap.put("underway", underway);
		return remap;
	}
	//安全服务页面
	public Object querySafety(String start, String end) {
		if("".equals(end) || end == null) {
			end = DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
		}
		if("".equals(start) || start == null) {
			start = DateUtils.getCurrentDate("yyyy")+"-01-01 00:00:00";
		}
		int count = 0;
		List<String> countIdList = new ArrayList<String>();
		for(String tabseName : TABSENAMES)  {
			count = approvalProcessDao.queryCount(tabseName, start, end) + count;
			countIdList.addAll(approvalProcessDao.queryCountIDList(tabseName, start, end));
		}
		//查询未受理//类别  # 日志第二个节点提交到终止，且流程表出现lzsh字段不为空，
		String types[] = {"资料不全", "资料有误", "重复录入"};
		List<Integer> list = new ArrayList<Integer>();
		List<List<String>> typeListIdList = new ArrayList<List<String>>();
		list.add(count);
		for (String type : types) {
			List<String> idList = new ArrayList<String>();
			int sendBack1 = approvalProcessDao.querySendBack(TABSENAMES[0], FLOWIDS[0], type, "Node2", start, end);
			int sendBack2 = approvalProcessDao.querySendBack(TABSENAMES[1], FLOWIDS[1], type, "Node6", start, end);
			int sendBack3 = approvalProcessDao.querySendBack(TABSENAMES[2], FLOWIDS[2], type, "Node3", start, end);
			int sendBack4 = approvalProcessDao.querySendBack(TABSENAMES[3], FLOWIDS[3], type, "Node3", start, end);
			int sendBack5 = approvalProcessDao.querySendBack(TABSENAMES[4], FLOWIDS[4], type, "Node2", start, end);
			list.add(sendBack1 + sendBack2 + sendBack3 + sendBack4 + sendBack5);
			idList.addAll(approvalProcessDao.querySendBackIDList(TABSENAMES[0], FLOWIDS[0], type, "Node2", start, end));
			idList.addAll(approvalProcessDao.querySendBackIDList(TABSENAMES[1], FLOWIDS[1], type, "Node6", start, end));
			idList.addAll(approvalProcessDao.querySendBackIDList(TABSENAMES[2], FLOWIDS[2], type, "Node3", start, end));
			idList.addAll(approvalProcessDao.querySendBackIDList(TABSENAMES[3], FLOWIDS[3], type, "Node3", start, end));
			idList.addAll(approvalProcessDao.querySendBackIDList(TABSENAMES[4], FLOWIDS[4], type, "Node2", start, end));
			typeListIdList.add(idList);
		}
		remap.clear();
		remap.put("count", count);
		remap.put("countIdList", countIdList);
		remap.put("typeList", list);
		remap.put("typeListIdList", typeListIdList);
		return remap;
	}
	//审核办理统计页面
	@SuppressWarnings("unchecked")
	public Object queryAuditManage(String start, String end) throws ParseException {
		if("".equals(end) || end == null) {
			end = DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
		}
		if("".equals(start) || start == null) {
			start = DateUtils.getCurrentDate("yyyy")+"-01-01 00:00:00";
		}
		//获取节假日和周末
		List<String> holidayAndWeekend = userDataService.getHolidayAndWeekend();
		//审核办理
		int count = 0;
		List<String> countIdList = new ArrayList<String>();
		for(String tabse : TABSENAMES) {
			count = approvalProcessDao.queryTabseCount(tabse, start, end) + count;
			//#查询ID
			countIdList.addAll(approvalProcessDao.queryTabseCountID(tabse, start, end));
		}
		//查询已审核，提交至审批节点，审批完成的也算,查日志表
		int audit = 0;
		List<String> auditIdList = new ArrayList<String>();
		//###查询Id
		auditIdList.addAll(approvalProcessDao.queryAuditPassWorkIdList(TABSENAMES[0], FLOWIDS[0],"Node3;", start, end));
		auditIdList.addAll(approvalProcessDao.queryAuditPassWorkIdList(TABSENAMES[1], FLOWIDS[1],"End5;", start, end));
		auditIdList.addAll(approvalProcessDao.queryAuditPassWorkIdList(TABSENAMES[2], FLOWIDS[2],"Node7;", start, end));
		auditIdList.addAll(approvalProcessDao.queryAuditPassWorkIdList(TABSENAMES[3], FLOWIDS[3],"Node10;", start, end));
		auditIdList.addAll(approvalProcessDao.queryAuditPassWorkIdList(TABSENAMES[4], FLOWIDS[4],"Node3;", start, end));
		//###
		int audit1 = approvalProcessDao.queryAuditPass(TABSENAMES[0], FLOWIDS[0],"Node3;", start, end);
		int audit2 = approvalProcessDao.queryAuditPass(TABSENAMES[1], FLOWIDS[1],"End5;", start, end);
		int audit3 = approvalProcessDao.queryAuditPass(TABSENAMES[2], FLOWIDS[2],"Node7;", start, end);
		int audit4 = approvalProcessDao.queryAuditPass(TABSENAMES[3], FLOWIDS[3],"Node10;", start, end);
		int audit5 = approvalProcessDao.queryAuditPass(TABSENAMES[4], FLOWIDS[4],"Node3;", start, end);
		audit = audit1 + audit2 + audit3 + audit4 + audit5;
		//审核未通过,#log审核节点提交到不通过节点
		int auditNotPass = 0;
		//###查询ID
		List<String> auditNotPassIdList = new ArrayList<String>();
		auditNotPassIdList.addAll(approvalProcessDao.queryAuditNotPassIDList(TABSENAMES[0], FLOWIDS[0],"Node2", "End5;", start, end));
		auditNotPassIdList.addAll(approvalProcessDao.queryAuditNotPassIDList(TABSENAMES[1], FLOWIDS[1],"Node4", "End7;", start, end));
		auditNotPassIdList.addAll(approvalProcessDao.queryAuditNotPassIDList(TABSENAMES[1], FLOWIDS[1],"Node6", "End7;", start, end));
		auditNotPassIdList.addAll(approvalProcessDao.queryAuditNotPassIDList(TABSENAMES[2], FLOWIDS[2], "Node3", "End11;", start, end));
		auditNotPassIdList.addAll(approvalProcessDao.queryAuditNotPassIDList(TABSENAMES[2], FLOWIDS[2], "Node10", "End11;", start, end));
		auditNotPassIdList.addAll(approvalProcessDao.queryAuditNotPassIDList(TABSENAMES[2], FLOWIDS[2], "Node4", "End11;", start, end));
		auditNotPassIdList.addAll(approvalProcessDao.queryAuditNotPassIDList(TABSENAMES[2], FLOWIDS[2], "Node7", "End11;", start, end));
		auditNotPassIdList.addAll(approvalProcessDao.queryAuditNotPassIDList(TABSENAMES[3], FLOWIDS[3],"Node3", "End12;", start, end));
		auditNotPassIdList.addAll(approvalProcessDao.queryAuditNotPassIDList(TABSENAMES[3], FLOWIDS[3],"Node9", "End12;", start, end));
		auditNotPassIdList.addAll(approvalProcessDao.queryAuditNotPassIDList(TABSENAMES[4], FLOWIDS[4],"Node2", "End6;", start, end));
		//###
		int auditNotPass1 = approvalProcessDao.queryAuditNotPass(TABSENAMES[0], FLOWIDS[0],"Node2", "End5;", start, end);
		
		int auditNotPass2 = approvalProcessDao.queryAuditNotPass(TABSENAMES[1], FLOWIDS[1],"Node4", "End7;", start, end);
		int auditNotPass2_1 = approvalProcessDao.queryAuditNotPass(TABSENAMES[1], FLOWIDS[1],"Node6", "End7;", start, end);
		
		int auditNotPass3 = approvalProcessDao.queryAuditNotPass(TABSENAMES[2], FLOWIDS[2], "Node3", "End11;", start, end);
		auditNotPass3 = approvalProcessDao.queryAuditNotPass(TABSENAMES[2], FLOWIDS[2], "Node10", "End11;", start, end) + auditNotPass3;
		auditNotPass3 = approvalProcessDao.queryAuditNotPass(TABSENAMES[2], FLOWIDS[2], "Node4", "End11;", start, end) + auditNotPass3;
		auditNotPass3 = approvalProcessDao.queryAuditNotPass(TABSENAMES[2], FLOWIDS[2], "Node7", "End11;", start, end) + auditNotPass3;
		
		int auditNotPass4 = approvalProcessDao.queryAuditNotPass(TABSENAMES[3], FLOWIDS[3],"Node3", "End12;", start, end);
		int auditNotPass4_1 = approvalProcessDao.queryAuditNotPass(TABSENAMES[3], FLOWIDS[3],"Node9", "End12;", start, end);
		
		int auditNotPass5 = approvalProcessDao.queryAuditNotPass(TABSENAMES[4], FLOWIDS[4],"Node2", "End6;", start, end);
		auditNotPass = auditNotPass1 + auditNotPass2 + auditNotPass2_1 + auditNotPass3 + auditNotPass4 + auditNotPass4_1 + auditNotPass5;
		//未审核，提交至审核节点，查日志表
		int notStart = 0;
		List<String> notStartIdList = new ArrayList<String>();
		notStartIdList.addAll(approvalProcessDao.queryAuditStartIDList(TABSENAMES[0], FLOWIDS[0],"Node2;", start, end));
		notStartIdList.addAll(approvalProcessDao.queryAuditStartIDList(TABSENAMES[1], FLOWIDS[1],"Node4;", start, end));
		notStartIdList.addAll(approvalProcessDao.queryAuditStartIDList(TABSENAMES[2], FLOWIDS[2],"Node3;", start, end));
		notStartIdList.addAll(approvalProcessDao.queryAuditStartIDList(TABSENAMES[3], FLOWIDS[3],"Node3;", start, end));
		notStartIdList.addAll(approvalProcessDao.queryAuditStartIDList(TABSENAMES[4], FLOWIDS[4],"Node2;", start, end));
		int notStart1 = approvalProcessDao.queryAuditStart(TABSENAMES[0], FLOWIDS[0],"Node2;", start, end);
		int notStart2 = approvalProcessDao.queryAuditStart(TABSENAMES[1], FLOWIDS[1],"Node4;", start, end);
		int notStart3 = approvalProcessDao.queryAuditStart(TABSENAMES[2], FLOWIDS[2],"Node3;", start, end);
		int notStart4 = approvalProcessDao.queryAuditStart(TABSENAMES[3], FLOWIDS[3],"Node3;", start, end);
		int notStart5 = approvalProcessDao.queryAuditStart(TABSENAMES[4], FLOWIDS[4],"Node2;", start, end);
		notStart = notStart1 + notStart2 + notStart3 + notStart4 + notStart5;
		//逾期审核，所有审核节点出现逾期审核的情况
		int overdue = 0;
		List<String> overdueIdList = new ArrayList<String>();
		Map<String, Object> _1overdue1m = approvalProcessDao.queryAuditOverdue(TABSENAMES[0], holidayAndWeekend, FLOWIDS[0], "Node2", start, end);
		
		Map<String, Object> _1overdue2m = approvalProcessDao.queryAuditOverdue(TABSENAMES[1], holidayAndWeekend, FLOWIDS[1], "Node4", start, end);
		Map<String, Object> _2overdue2m = approvalProcessDao.queryAuditOverdue(TABSENAMES[1], holidayAndWeekend, FLOWIDS[1], "Node6", start, end);
		
		Map<String, Object> _1overdue3m = approvalProcessDao.queryAuditOverdue(TABSENAMES[2], holidayAndWeekend, FLOWIDS[2], "Node3", start, end);
		Map<String, Object> _2overdue3m = approvalProcessDao.queryAuditOverdue(TABSENAMES[2], holidayAndWeekend, FLOWIDS[2], "Node10", start, end);
		Map<String, Object> _3overdue3m = approvalProcessDao.queryAuditOverdue(TABSENAMES[2], holidayAndWeekend, FLOWIDS[2], "Node4", start, end);
		Map<String, Object> _4overdue3m = approvalProcessDao.queryAuditOverdue(TABSENAMES[2], holidayAndWeekend, FLOWIDS[2], "Node7", start, end);
		
		Map<String, Object> _1overdue4m = approvalProcessDao.queryAuditOverdue(TABSENAMES[3], holidayAndWeekend, FLOWIDS[3], "Node3", start, end);
		Map<String, Object> _2overdue4m = approvalProcessDao.queryAuditOverdue(TABSENAMES[3], holidayAndWeekend, FLOWIDS[3], "Node9", start, end);
		
		Map<String, Object> _1overdue5m = approvalProcessDao.queryAuditOverdue(TABSENAMES[4], holidayAndWeekend, FLOWIDS[4], "Node2", start, end);
		int _1overdue1 = Integer.parseInt(_1overdue1m.get("count").toString());
		int _1overdue2 = Integer.parseInt(_1overdue2m.get("count").toString());
		int _2overdue2 = Integer.parseInt(_2overdue2m.get("count").toString());
		int _1overdue3 = Integer.parseInt(_1overdue3m.get("count").toString());
		int _2overdue3 = Integer.parseInt(_2overdue3m.get("count").toString());
		int _3overdue3 = Integer.parseInt(_3overdue3m.get("count").toString());
		int _4overdue3 = Integer.parseInt(_4overdue3m.get("count").toString());
		int _1overdue4 = Integer.parseInt(_1overdue4m.get("count").toString());
		int _2overdue4 = Integer.parseInt(_2overdue4m.get("count").toString());
		int _1overdue5 = Integer.parseInt(_1overdue5m.get("count").toString());
		
		overdueIdList.addAll((List<String>) _1overdue1m.get("dataIDList"));
		overdueIdList.addAll((List<String>) _1overdue2m.get("dataIDList"));
		overdueIdList.addAll((List<String>) _2overdue2m.get("dataIDList"));
		overdueIdList.addAll((List<String>) _1overdue3m.get("dataIDList"));
		overdueIdList.addAll((List<String>) _2overdue3m.get("dataIDList"));
		overdueIdList.addAll((List<String>) _3overdue3m.get("dataIDList"));
		overdueIdList.addAll((List<String>) _4overdue3m.get("dataIDList"));
		overdueIdList.addAll((List<String>) _1overdue4m.get("dataIDList"));
		overdueIdList.addAll((List<String>) _2overdue4m.get("dataIDList"));
		overdueIdList.addAll((List<String>) _1overdue5m.get("dataIDList"));

		overdue = _1overdue1 + _1overdue2 + _2overdue2 + _1overdue3 + _2overdue3 + _3overdue3 + _4overdue3 + _1overdue4
				 + _2overdue4  + _1overdue5;
		//资料不全，所有流程出现资料不全，资料有误，重复提交的情况
		//#最新办理节点为审核的某节点，且流程表出现lzsh字段不为空
		List<Integer> materialList = new ArrayList<Integer>();
		List<List<String>> materialListIdList = new ArrayList<List<String>>();
		String types[] = {"资料不全", "资料有误", "重复录入"};
		for (String type : types) {
			int material = 0;
			List<String> materialIdList = new ArrayList<String>();
			int _1material1 = approvalProcessDao.queryAuditMaterial(TABSENAMES[0], FLOWIDS[0], type, "Node2", "End5;", start, end);
			
			int _2material2 = approvalProcessDao.queryAuditMaterial(TABSENAMES[1], FLOWIDS[1], type, "Node6", "End7;", start, end);
			
			int _3material3 = approvalProcessDao.queryAuditMaterial(TABSENAMES[2], FLOWIDS[2], type, "Node3", "End11;", start, end);
			
			int _1material4 = approvalProcessDao.queryAuditMaterial(TABSENAMES[3], FLOWIDS[3], type, "Node3", "End12;", start, end);
			
			int _1material5 = approvalProcessDao.queryAuditMaterial(TABSENAMES[4], FLOWIDS[4], type, "Node2", "End6;", start, end);
			material = _1material1  + _2material2  + _3material3 + _1material4 + _1material5;
			materialList.add(material);
			materialIdList.addAll(approvalProcessDao.queryAuditMaterialIDList(TABSENAMES[0], FLOWIDS[0], type, "Node2", "End5;", start, end));
			materialIdList.addAll(approvalProcessDao.queryAuditMaterialIDList(TABSENAMES[1], FLOWIDS[1], type, "Node6", "End7;", start, end));
			materialIdList.addAll(approvalProcessDao.queryAuditMaterialIDList(TABSENAMES[2], FLOWIDS[2], type, "Node3", "End11;", start, end));
			materialIdList.addAll(approvalProcessDao.queryAuditMaterialIDList(TABSENAMES[3], FLOWIDS[3], type, "Node3", "End12;", start, end));
			materialIdList.addAll(approvalProcessDao.queryAuditMaterialIDList(TABSENAMES[4], FLOWIDS[4], type, "Node2", "End6;", start, end));
			materialListIdList.add(materialIdList);
		}
		//逾期未审核，所有审核节点出现逾期未审核的情况,查询worklist表
		int overdueNot = 0;
		List<String> overdueNotIdList = new ArrayList<String>();
		Map<String, Object> _1overdueNot1m = approvalProcessDao.queryAuditOverdueNot(TABSENAMES[0], FLOWIDS[0],"Node2", start, end, holidayAndWeekend);
		
		Map<String, Object> _1overdueNot2m = approvalProcessDao.queryAuditOverdueNot(TABSENAMES[1], FLOWIDS[1],"Node4", start, end, holidayAndWeekend);
		Map<String, Object> _2overdueNot2m = approvalProcessDao.queryAuditOverdueNot(TABSENAMES[1], FLOWIDS[1],"Node6", start, end, holidayAndWeekend);
		
		Map<String, Object> _1overdueNot3m = approvalProcessDao.queryAuditOverdueNot(TABSENAMES[2], FLOWIDS[2],"Node3", start, end, holidayAndWeekend);
		Map<String, Object> _2overdueNot3m = approvalProcessDao.queryAuditOverdueNot(TABSENAMES[2], FLOWIDS[2],"Node10", start, end, holidayAndWeekend);
		Map<String, Object> _3overdueNot3m = approvalProcessDao.queryAuditOverdueNot(TABSENAMES[2], FLOWIDS[2],"Node4", start, end, holidayAndWeekend);
		Map<String, Object> _4overdueNot3m = approvalProcessDao.queryAuditOverdueNot(TABSENAMES[2], FLOWIDS[2],"Node7", start, end, holidayAndWeekend);
		
		Map<String, Object> _1overdueNot4m = approvalProcessDao.queryAuditOverdueNot(TABSENAMES[3], FLOWIDS[3],"Node3", start, end, holidayAndWeekend);
		Map<String, Object> _6overdueNot4m = approvalProcessDao.queryAuditOverdueNot(TABSENAMES[3], FLOWIDS[3],"Node9", start, end, holidayAndWeekend);
		
		Map<String, Object> _1overdueNot5m = approvalProcessDao.queryAuditOverdueNot(TABSENAMES[4], FLOWIDS[4],"Node2", start, end, holidayAndWeekend);
		int _1overdueNot1 = Integer.parseInt(_1overdueNot1m.get("count").toString());
		int _1overdueNot2 = Integer.parseInt(_1overdueNot2m.get("count").toString());
		int _2overdueNot2 = Integer.parseInt(_2overdueNot2m.get("count").toString());
		int _1overdueNot3 = Integer.parseInt(_1overdueNot3m.get("count").toString());
		int _2overdueNot3 = Integer.parseInt(_2overdueNot3m.get("count").toString());
		int _3overdueNot3 = Integer.parseInt(_3overdueNot3m.get("count").toString());
		int _4overdueNot3 = Integer.parseInt(_4overdueNot3m.get("count").toString());
		int _1overdueNot4 = Integer.parseInt(_1overdueNot4m.get("count").toString());
		int _6overdueNot4 = Integer.parseInt(_6overdueNot4m.get("count").toString());
		int _1overdueNot5 = Integer.parseInt(_1overdueNot5m.get("count").toString());
		overdueNot = _1overdueNot1 + _1overdueNot2 + _2overdueNot2 + _1overdueNot3 + _2overdueNot3 
				+ _3overdueNot3 + _4overdueNot3 + _1overdueNot4 + _6overdueNot4 + _1overdueNot5;
		
		overdueNotIdList.addAll((List<String>) _1overdueNot1m.get("dataIDList"));
		overdueNotIdList.addAll((List<String>) _1overdueNot2m.get("dataIDList"));
		overdueNotIdList.addAll((List<String>) _2overdueNot2m.get("dataIDList"));
		overdueNotIdList.addAll((List<String>) _1overdueNot3m.get("dataIDList"));
		overdueNotIdList.addAll((List<String>) _2overdueNot3m.get("dataIDList"));
		overdueNotIdList.addAll((List<String>) _3overdueNot3m.get("dataIDList"));
		overdueNotIdList.addAll((List<String>) _4overdueNot3m.get("dataIDList"));
		overdueNotIdList.addAll((List<String>) _1overdueNot4m.get("dataIDList"));
		overdueNotIdList.addAll((List<String>) _6overdueNot4m.get("dataIDList"));
		overdueNotIdList.addAll((List<String>) _1overdueNot5m.get("dataIDList"));

		remap.clear();
		remap.put("count", count);
		remap.put("countIdList", countIdList);
		remap.put("audit", audit);
		remap.put("auditIdList", auditIdList);
		remap.put("auditNotPass", auditNotPass);
		remap.put("auditNotPassIdList", auditNotPassIdList);
		remap.put("notStart", notStart);
		remap.put("notStartIdList", notStartIdList);
		//审核中underway
		List<String> idlist = new ArrayList<String>();
		List<String> underwayIdList = new ArrayList<String>();
		idlist.addAll(auditIdList);
		idlist.addAll(auditNotPassIdList);
		idlist.addAll(notStartIdList);
		for (String id : countIdList) {
			if(!idlist.contains(id)) {
				underwayIdList.add(id);
			}
		}
		remap.put("underway", underwayIdList.size());
		remap.put("underwayIdList", underwayIdList);
		
		remap.put("overdue", overdue);
		remap.put("overdueIdList", overdueIdList);
		remap.put("materialList", materialList);
		remap.put("materialListIdList", materialListIdList);
		remap.put("overdueNot", overdueNot);
		remap.put("overdueNotIdList", overdueNotIdList);
		return remap;
	}
	//审批办理页面
	@SuppressWarnings("unchecked")
	public Map<String, Object> queryRatify(String start, String end) throws ParseException {
		if("".equals(end) || end == null) {
			end = DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
		}
		if("".equals(start) || start == null) {
			start = DateUtils.getCurrentDate("yyyy")+"-01-01 00:00:00";
		}
		//获取节假日和周末
		List<String> holidayAndWeekend = userDataService.getHolidayAndWeekend();
		//审批办理
		int count = 0;
		List<String> countIdList = new ArrayList<String>();
		//安全服务没有审批
		//节点已经提交至审批时才   +1 
		count = approvalProcessDao.queryTabseCount(TABSENAMES[0], start, end, "Node3;");
		count += approvalProcessDao.queryTabseCount(TABSENAMES[2], start, end, "Node8;");
		count += approvalProcessDao.queryTabseCount(TABSENAMES[3], start, end, "Node10;");
		count += approvalProcessDao.queryTabseCount(TABSENAMES[4], start, end, "Node3;");
		countIdList.addAll(approvalProcessDao.queryTabseCountIDList(TABSENAMES[0], start, end, "Node3;"));
		countIdList.addAll(approvalProcessDao.queryTabseCountIDList(TABSENAMES[2], start, end, "Node8;"));
		countIdList.addAll(approvalProcessDao.queryTabseCountIDList(TABSENAMES[3], start, end, "Node10;"));
		countIdList.addAll(approvalProcessDao.queryTabseCountIDList(TABSENAMES[4], start, end, "Node3;"));
		//查询已审批，提交至结束节点，查日志表
		int audit = 0;
		List<String> auditIdList = new ArrayList<String>();
		int audit1 = approvalProcessDao.queryAuditPass(TABSENAMES[0], FLOWIDS[0],"End4;", start, end);
		int audit3 = approvalProcessDao.queryAuditPass(TABSENAMES[2], FLOWIDS[2],"End9;", start, end);
		int audit4 = approvalProcessDao.queryAuditPass(TABSENAMES[3], FLOWIDS[3],"End1;", start, end);
		int audit5 = approvalProcessDao.queryAuditPass(TABSENAMES[4], FLOWIDS[4],"End4;", start, end);
		auditIdList.addAll(approvalProcessDao.queryAuditPassIDList(TABSENAMES[0], FLOWIDS[0],"End4;", start, end));
		auditIdList.addAll(approvalProcessDao.queryAuditPassIDList(TABSENAMES[2], FLOWIDS[2],"End9;", start, end));
		auditIdList.addAll(approvalProcessDao.queryAuditPassIDList(TABSENAMES[3], FLOWIDS[3],"End1;", start, end));
		auditIdList.addAll(approvalProcessDao.queryAuditPassIDList(TABSENAMES[4], FLOWIDS[4],"End4;", start, end));
		audit = audit1  + audit3 + audit4 + audit5;
		//审批未通过,#log审批节点提交到不通过节点
		int auditNotPass = 0;
		//###查询ID
		List<String> auditNotPassIdList = new ArrayList<String>();
		auditNotPassIdList.addAll(approvalProcessDao.queryAuditNotPassIDList(TABSENAMES[0], FLOWIDS[0],"Node3", "End5;", start, end));
		auditNotPassIdList.addAll(approvalProcessDao.queryAuditNotPassIDList(TABSENAMES[2], FLOWIDS[2],"Node8", "End11;", start, end));
		auditNotPassIdList.addAll(approvalProcessDao.queryAuditNotPassIDList(TABSENAMES[3], FLOWIDS[3],"Node10", "End12;", start, end));
		auditNotPassIdList.addAll(approvalProcessDao.queryAuditNotPassIDList(TABSENAMES[4], FLOWIDS[4],"Node3", "End6;", start, end));
		//###
		int auditNotPass1 = approvalProcessDao.queryAuditNotPass(TABSENAMES[0], FLOWIDS[0],"Node3", "End5;", start, end);
		int	auditNotPass3 = approvalProcessDao.queryAuditNotPass(TABSENAMES[2], FLOWIDS[2],"Node8", "End11;", start, end);
		int auditNotPass4 = approvalProcessDao.queryAuditNotPass(TABSENAMES[3], FLOWIDS[3],"Node10", "End12;", start, end);
		int auditNotPass5 = approvalProcessDao.queryAuditNotPass(TABSENAMES[4], FLOWIDS[4],"Node3", "End6;", start, end);
		auditNotPass = auditNotPass1 + auditNotPass3 + auditNotPass4 + auditNotPass5;
		//未审批，提交至审批节点，查日志表
		int notStart = 0;
		List<String> notStartIdList = new ArrayList<String>();
		notStartIdList.addAll(approvalProcessDao.queryAuditStartIDList(TABSENAMES[0], FLOWIDS[0],"Node3;", start, end));
		notStartIdList.addAll(approvalProcessDao.queryAuditStartIDList(TABSENAMES[2], FLOWIDS[2],"Node7;", start, end));
		notStartIdList.addAll(approvalProcessDao.queryAuditStartIDList(TABSENAMES[3], FLOWIDS[3],"Node10;", start, end));
		notStartIdList.addAll(approvalProcessDao.queryAuditStartIDList(TABSENAMES[4], FLOWIDS[4],"Node3;", start, end));
		
		int notStart1 = approvalProcessDao.queryAuditStart(TABSENAMES[0], FLOWIDS[0],"Node3;", start, end);
		int notStart3 = approvalProcessDao.queryAuditStart(TABSENAMES[2], FLOWIDS[2],"Node7;", start, end);
		int notStart4 = approvalProcessDao.queryAuditStart(TABSENAMES[3], FLOWIDS[3],"Node10;", start, end);
		int notStart5 = approvalProcessDao.queryAuditStart(TABSENAMES[4], FLOWIDS[4],"Node3;", start, end);
		notStart = notStart1 + notStart3 + notStart4 + notStart5;
		//逾期审批，所有审批节点出现逾期审批的情况
		int overdue = 0;
		List<String> overdueIdList = new ArrayList<String>();
		Map<String, Object> _1overdue1m = approvalProcessDao.queryAuditOverdue(TABSENAMES[0], holidayAndWeekend, FLOWIDS[0], "Node3", start, end);
		Map<String, Object> _1overdue3m = approvalProcessDao.queryAuditOverdue(TABSENAMES[2], holidayAndWeekend, FLOWIDS[2], "Node8", start, end);
		Map<String, Object> _1overdue4m = approvalProcessDao.queryAuditOverdue(TABSENAMES[3], holidayAndWeekend, FLOWIDS[3], "Node10", start, end);
		Map<String, Object> _1overdue5m = approvalProcessDao.queryAuditOverdue(TABSENAMES[4], holidayAndWeekend, FLOWIDS[4], "Node3", start, end);
		int _1overdue1 = Integer.parseInt(_1overdue1m.get("count").toString());
		int _1overdue3 = Integer.parseInt(_1overdue3m.get("count").toString());
		int _1overdue4 = Integer.parseInt(_1overdue4m.get("count").toString());
		int _1overdue5 = Integer.parseInt(_1overdue5m.get("count").toString());
		overdue = _1overdue1 + _1overdue3  + _1overdue4 + _1overdue5;
		overdueIdList.addAll((List<String>) _1overdue1m.get("dataIDList"));
		overdueIdList.addAll((List<String>) _1overdue3m.get("dataIDList"));
		overdueIdList.addAll((List<String>) _1overdue4m.get("dataIDList"));
		overdueIdList.addAll((List<String>) _1overdue5m.get("dataIDList"));
		//资料不全，所有流程出现资料不全，资料有误，重复提交的情况
		//资料错误时，审批节点提交的下一个节点不是不同意
		List<Integer> materialList = new ArrayList<Integer>();
		List<List<String>> materialListIdList = new ArrayList<List<String>>();
		String types[] = {"资料不全", "资料有误", "重复录入"};
		for (String type : types) {
			int material = 0;
			List<String> materialIdList = new ArrayList<String>();
			int material1 = approvalProcessDao.queryAuditMaterialRatify(TABSENAMES[0], FLOWIDS[0], type, "Node3", "End5;", start, end);
			int	material3 = approvalProcessDao.queryAuditMaterialRatify(TABSENAMES[2], FLOWIDS[2], type, "Node8", "End11;", start, end);
			int material4 = approvalProcessDao.queryAuditMaterialRatify(TABSENAMES[3], FLOWIDS[3], type, "Node9", "End12;", start, end);
			int material5 = approvalProcessDao.queryAuditMaterialRatify(TABSENAMES[4], FLOWIDS[4], type, "Node2", "End6;", start, end);
			material = material1 + material3 + material3 + material4 + material5;
			materialList.add(material);
			materialIdList.addAll(approvalProcessDao.queryAuditMaterialRatifyIDList(TABSENAMES[0], FLOWIDS[0], type, "Node3", "End5;", start, end));
			materialIdList.addAll(approvalProcessDao.queryAuditMaterialRatifyIDList(TABSENAMES[2], FLOWIDS[2], type, "Node8", "End11;", start, end));
			materialIdList.addAll(approvalProcessDao.queryAuditMaterialRatifyIDList(TABSENAMES[3], FLOWIDS[3], type, "Node9", "End12;", start, end));
			materialIdList.addAll(approvalProcessDao.queryAuditMaterialRatifyIDList(TABSENAMES[4], FLOWIDS[4], type, "Node2", "End6;", start, end));
			materialListIdList.add(materialIdList);
		}
		//逾期未审批，所有审批节点出现逾期未审批的情况,查询worklist表
		int overdueNot = 0;
		List<String> overdueNotIdList = new ArrayList<String>();
		Map<String, Object> _1overdueNot1m = approvalProcessDao.queryAuditOverdueNot(TABSENAMES[0], FLOWIDS[0],"Node3", start, end, holidayAndWeekend);
		Map<String, Object> _1overdueNot3m = approvalProcessDao.queryAuditOverdueNot(TABSENAMES[2], FLOWIDS[2],"Node8", start, end, holidayAndWeekend);
		Map<String, Object> _1overdueNot4m = approvalProcessDao.queryAuditOverdueNot(TABSENAMES[3], FLOWIDS[3],"Node10", start, end, holidayAndWeekend);
		Map<String, Object> _1overdueNot5m = approvalProcessDao.queryAuditOverdueNot(TABSENAMES[4], FLOWIDS[4],"Node3", start, end, holidayAndWeekend);
		int _1overdueNot1 = Integer.parseInt(_1overdueNot1m.get("count").toString());
		int _1overdueNot3 = Integer.parseInt(_1overdueNot3m.get("count").toString());
		int _1overdueNot4 = Integer.parseInt(_1overdueNot4m.get("count").toString());
		int _1overdueNot5 = Integer.parseInt(_1overdueNot5m.get("count").toString());
		overdueNot = _1overdueNot1 + _1overdueNot3  + _1overdueNot4 +  _1overdueNot5;
		overdueNotIdList.addAll((List<String>) _1overdueNot1m.get("dataIDList"));
		overdueNotIdList.addAll((List<String>) _1overdueNot3m.get("dataIDList"));
		overdueNotIdList.addAll((List<String>) _1overdueNot4m.get("dataIDList"));
		overdueNotIdList.addAll((List<String>) _1overdueNot5m.get("dataIDList"));
		remap.clear();
		remap.put("count", count);
		remap.put("audit", audit);
		remap.put("auditNotPass", auditNotPass);
		remap.put("notStart", notStart);
		remap.put("overdue", overdue);
		remap.put("materialList", materialList);
		remap.put("overdueNot", overdueNot);
		
		remap.put("countIdList", countIdList);
		remap.put("auditIdList", auditIdList);
		remap.put("auditNotPassIdList", auditNotPassIdList);
		remap.put("notStartIdList", notStartIdList);
		remap.put("overdueIdList", overdueIdList);
		remap.put("materialListIdList", materialListIdList);
		remap.put("overdueNotIdList", overdueNotIdList);
		return remap;
	}
	
	
	
	
	private int getLimitTime(String limitTime) {
		if(!(limitTime == null || "".equals(limitTime))) {
			int indexOf = limitTime.indexOf("(");
			return Integer.parseInt(limitTime.substring(0, indexOf));
		}
		return 0;
	}


	

	
	
	
	
	
}
