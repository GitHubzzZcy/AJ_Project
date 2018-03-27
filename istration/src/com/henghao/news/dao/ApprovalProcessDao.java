package com.henghao.news.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.henghao.news.entity.Hzlog;
import com.henghao.news.entity.WorkList;
import com.henghao.news.util.DateUtils;

@Repository
public class ApprovalProcessDao {
	
	@Resource
	private SessionFactory sessionFactory;
	private Map<String, Object> remap = new HashMap<String, Object>();

	//查询已审批数据
	public List<Hzlog> queryAnjianTongjiCountFinishLogList(String tableName, String flowid, String EndNode, String start, String end) {
		String sql = "SELECT ID,WORKID,NODEID,USERID,ACTIONTIME,DOTIME,NEXTNODEIDS,LIMITTIME FROM tw_hz_log WHERE FLOWID=? AND NEXTNODEIDS=? AND ACTIONTIME > ? AND ACTIONTIME < ?"
				+ " AND WORKID IN (SELECT z.WORKID FROM "+tableName+" y LEFT OUTER JOIN twr_hz_instance z ON y.ID=z.DATAID)";
		@SuppressWarnings("unchecked")
		List<Hzlog> list = sessionFactory.getCurrentSession().createSQLQuery(sql)
		.setParameter(0, flowid)
		.setParameter(1, EndNode)
		.setParameter(2, start)
		.setParameter(3, end)
		.setResultTransformer(Transformers.aliasToBean(Hzlog.class)).list();
		return list;
	}
	//查询审批中的数据
	public List<Hzlog> queryAnjianTongjiUnderway(String tableName, String flowid, String nextNode, String start, String end) {
		String sql = "SELECT ID,WORKID,NODEID,USERID,ACTIONTIME,DOTIME,NEXTNODEIDS,LIMITTIME  FROM tw_hz_log  WHERE (workid,id) IN ("+
					"SELECT workid,MAX(id) FROM tw_hz_log WHERE FLOWID=? GROUP BY workid"+
					") AND WORKID NOT IN (SELECT WORKID FROM tw_hz_log WHERE FLOWID=? AND NEXTNODEIDS=? GROUP BY WORKID)  AND ACTIONTIME > ? AND ACTIONTIME < ?"
					+ " AND WORKID IN (SELECT z.WORKID FROM "+tableName+" y LEFT OUTER JOIN twr_hz_instance z ON y.ID=z.DATAID)";
		@SuppressWarnings("unchecked")
		List<Hzlog> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, flowid)
											.setParameter(1, flowid)
											.setParameter(2, nextNode)
											.setParameter(3, start)
											.setParameter(4, end)
											.setResultTransformer(Transformers.aliasToBean(Hzlog.class)).list();
		return list;
	}
	
	
	//资料不全、资料有误、重复录入
	public int queryAnjianTongjiZiliao(String dispose_nodes, String tabseName, String type, String start, String end) {
		String sql = "SELECT COUNT(*) "
				+ "FROM "+tabseName+" a LEFT OUTER JOIN twr_hz_instance b ON a.ID=b.DATAID "
				+ "LEFT  OUTER JOIN tw_hz_log c ON c.WORKID=B.WORKID "
				+ "WHERE A.LZSH=? "
				+ "AND c.ACTIONTIME>?  "
				+ "AND c.ACTIONTIME<?  "
				+ "AND C.NODEID=? "
				+ "AND c.ACTION='submit'";
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql)
													.setParameter(0, type)
													.setParameter(1, start)
													.setParameter(2, end)
													.setParameter(3, dispose_nodes)
													.uniqueResult().toString();
		return Integer.parseInt(count);
	}
	//逾期审核、逾期审批
	public List<Integer> queryAnjianTongjiOverdueDispose(String tableName, String flowid, List<String> fLOWID1sh, List<String> fLOWID1sp, String start, String end, List<String> holidayAndWeekend) throws ParseException {
		List<Integer> relist = new ArrayList<Integer>();
		//审核
		int sh = 0;
		for (String node : fLOWID1sh) {
			String sql = "SELECT DOTIME,LIMITTIME,ACTIONTIME FROM TW_HZ_LOG WHERE FLOWID=? AND NODEID=? AND ACTION<>'stop' AND ACTIONTIME>? AND ACTIONTIME<? "
					+ " AND WORKID IN (SELECT z.WORKID FROM "+tableName+" y LEFT OUTER JOIN twr_hz_instance z ON y.ID=z.DATAID)";
			@SuppressWarnings("unchecked")
			List<Object> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, flowid)
																					.setParameter(1, node)
																					.setParameter(2, start)
																					.setParameter(3, end).list();
			for (Object object : list) {
				Object[] obj = (Object[]) object;
				String doTime = obj[0] == null ? null:obj[0].toString();
				String limitTime = obj[1] == null ? null:obj[1].toString();
				String actiontime = obj[2] == null ? null:obj[2].toString();
				if(DateUtils.getDoActionTime(holidayAndWeekend, doTime, actiontime) > getLimitTime(limitTime)) {
					sh++;
				}
			}
		}
		//审批
		int sp = 0;
		for (String node : fLOWID1sp) {
			String sql = "SELECT DOTIME,LIMITTIME,ACTIONTIME FROM TW_HZ_LOG WHERE FLOWID=? AND ACTION<>'stop' AND NODEID=? AND ACTIONTIME>? AND ACTIONTIME<?";
			@SuppressWarnings("unchecked")
			List<Hzlog> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, flowid)
																						.setParameter(1, node)
																						.setParameter(2, start)
																						.setParameter(3, end)
																						.setResultTransformer(Transformers.aliasToBean(Hzlog.class))
																						.list();
			for (Hzlog hzlog : list) {
				String doTime = hzlog.getDOTIME();
				String limitTime = hzlog.getLIMITTIME();
				String actiontime = hzlog.getACTIONTIME();
				if(DateUtils.getDoActionTime(holidayAndWeekend, doTime, actiontime) > getLimitTime(limitTime)) {
					sp++;
				}
			}
		}
		relist.add(sh);
		relist.add(sp);
		return relist;
	}
	
	public List<WorkList> queryLogNewestByFlowid(String tableName, String flowid, String start, String end) {
		String sql = "SELECT NODEID,LIMITTIME,SENDTIME FROM tw_hz_worklist  WHERE (workid,id) IN (SELECT workid,MAX(id) FROM tw_hz_worklist  GROUP BY workid) AND FLOWID=? AND SENDTIME >? AND SENDTIME<? AND TRACKSTATUS='110' AND STATUS='Author'"
				+ " AND WORKID IN (SELECT z.WORKID FROM "+tableName+" y LEFT OUTER JOIN twr_hz_instance z ON y.ID=z.DATAID)";
		@SuppressWarnings("unchecked")
		List<WorkList> list = sessionFactory.getCurrentSession().createSQLQuery(sql)
			.setParameter(0, flowid)
			.setParameter(1, start)
			.setParameter(2, end)
			.setResultTransformer(Transformers.aliasToBean(WorkList.class)).list();
		return list;
	}
	//审核违纪、审批违纪
	public int queryManageIllegal(String type, String start, String end) {
		String sql = "SELECT COUNT(*) FROM report_information a LEFT OUTER JOIN complaint_handling b ON a.`code`=b.`code` WHERE problemCategories=? AND b.examinationResult='属实' AND a.time>? AND a.time<?";
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql)
										.setParameter(0, type)
										.setParameter(1, start)
										.setParameter(2, end).uniqueResult().toString();
		return Integer.parseInt(count);
	}
	//审批汇总查询审批中的列表
	//TODO
	public WorkList queryHuiZongSPZhong(String tableName, String flowid, String nodeid, String start, String end) {
		String sql = "SELECT NODEID,LIMITTIME,SENDTIME FROM tw_hz_worklist WHERE FLOWID=? AND NODEID=? AND STATUS='Author' AND SENDTIME >? AND SENDTIME<?"
				+ " AND WORKID IN (SELECT z.WORKID FROM "+tableName+" y LEFT OUTER JOIN twr_hz_instance z ON y.ID=z.DATAID)";
		@SuppressWarnings("unchecked")
		List<WorkList> list = sessionFactory.getCurrentSession().createSQLQuery(sql)
		.setParameter(0, flowid)
		.setParameter(1, nodeid)
		.setParameter(2, start)
		.setParameter(3, end)
		.setResultTransformer(Transformers.aliasToBean(WorkList.class)).list();
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	public int queryCount(String tabseName, String start, String end) {
		String sql = "SELECT COUNT(*) FROM (SELECT ID FROM "+tabseName+" WHERE DATES>? AND DATES<? GROUP BY ID) A"; //TODO
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql)
													.setParameter(0, start)
													.setParameter(1, end).uniqueResult().toString();
		return Integer.parseInt(count);
	}
	//安全服务页面资料不全，有误，重复
	public int querySendBack(String tabase, String flowid, String type, String node, String start, String end) {
		
		String sql = "select COUNT(*) from tw_hz_log a " +
					"left  outer join twr_hz_instance b on a.workid=b.workid " +
					"left  outer join "+tabase+" c on b.dataid=c.id " +
					"where a.actiontime>? and a.actiontime<? and c.lzsh=? and a.FLOWID=? AND a.NODEID=? AND (a.ACTION='stop' OR a.NEXTNODEIDS LIKE 'End%')"; 
			
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql)
											.setParameter(0, start)
											.setParameter(1, end)
											.setParameter(2, type)
											.setParameter(3, flowid)
											.setParameter(4, node).uniqueResult().toString();
		return Integer.parseInt(count);
	}
	//审核办理页面，审核办理
	public int queryTabseCount(String tabse, String start, String end, String nextnode) {
		String sql  = "SELECT COUNT(*) FROM "+tabse+" A LEFT OUTER JOIN twr_hz_instance B ON A.ID=B.DATAID LEFT OUTER JOIN tw_hz_log C ON B.WORKID=C.WORKID WHERE C.NEXTNODEIDS=? AND A.DATES>? AND A.DATES<?";
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, nextnode).setParameter(1, start).setParameter(2, end).uniqueResult().toString();
		return Integer.parseInt(count);
	}
	public int queryTabseCount(String tabse, String start, String end) { 
		String sql  = "SELECT COUNT(*) FROM (SELECT ID FROM "+tabse+" WHERE DATES>? AND DATES<? GROUP BY ID) A"; //TODO
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, start).setParameter(1, end).uniqueResult().toString();
		return Integer.parseInt(count);
	}
	//审核审批办理页面，已审核
	public int queryAuditPass(String tableName, String flowid, String node, String start, String end) {
		String sql = "SELECT COUNT(*) FROM tw_hz_log WHERE FLOWID=? AND ACTION<>'stop' AND NEXTNODEIDS=? AND ACTIONTIME>? AND ACTIONTIME<?"
				+ " AND WORKID IN (SELECT z.WORKID FROM "+tableName+" y LEFT OUTER JOIN twr_hz_instance z ON y.ID=z.DATAID)";
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql)
										.setParameter(0, flowid)
										.setParameter(1, node)
										.setParameter(2, start)
										.setParameter(3, end)
										.uniqueResult().toString();
		return Integer.parseInt(count);
	}
	//审核、审批未通过
	public int queryAuditNotPass(String tableName, String flowid, String node, String nextnode, String start, String end) {
		String sql = "SELECT COUNT(*) FROM tw_hz_log WHERE FLOWID=? AND NEXTNODEIDS=? AND NODEID=? AND ACTIONTIME>? AND ACTIONTIME<?"
				+ " AND WORKID IN (SELECT z.WORKID FROM "+tableName+" y LEFT OUTER JOIN twr_hz_instance z ON y.ID=z.DATAID)";
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql)
				.setParameter(0, flowid)
				.setParameter(1, nextnode)
				.setParameter(2, node)
				.setParameter(3, start)
				.setParameter(4, end)
				.uniqueResult().toString();
		return Integer.parseInt(count);
	}
	//审核办理页面，未审核
	//TODO
	public int queryAuditStart(String tableName, String flowid, String node, String start, String end) {
		String sql = "SELECT COUNT(*) FROM tw_hz_log WHERE (WORKID,ID) IN (SELECT WORKID,MAX(ID) FROM tw_hz_log GROUP BY WORKID) AND ACTION<>'stop' AND FLOWID=? AND NEXTNODEIDS=? AND ACTIONTIME>? AND ACTIONTIME<?"
				+ " AND WORKID IN (SELECT z.WORKID FROM "+tableName+" y LEFT OUTER JOIN twr_hz_instance z ON y.ID=z.DATAID)";
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql)
				.setParameter(0, flowid)
				.setParameter(1, node)
				.setParameter(2, start)
				.setParameter(3, end)
				.uniqueResult().toString();
		return Integer.parseInt(count);
	}
	//审核办理页面，逾期审核
	public Map<String, Object> queryAuditOverdue(String tableName, List<String> holidayAndWeekend, String flowid, String node, String start, String end) throws ParseException {
		List<String> dataIDList = new ArrayList<String>();
		String sql = "SELECT DOTIME,LIMITTIME,ACTIONTIME,WORKID FROM tw_hz_log WHERE FLOWID=? AND ACTION<>'stop' AND NODEID=? AND ACTIONTIME>? AND ACTIONTIME<?"
				+ " AND WORKID IN (SELECT z.WORKID FROM "+tableName+" y LEFT OUTER JOIN twr_hz_instance z ON y.ID=z.DATAID)";
		@SuppressWarnings("unchecked")
		List<Hzlog> list = sessionFactory.getCurrentSession().createSQLQuery(sql)
				.setParameter(0, flowid)
				.setParameter(1, node)
				.setParameter(2, start)
				.setParameter(3, end)
				.setResultTransformer(Transformers.aliasToBean(Hzlog.class)).list();
		int count = 0;
		for (Hzlog hzlog : list) {
			if(DateUtils.getDoActionTime(holidayAndWeekend, hzlog.getDOTIME(), hzlog.getACTIONTIME()) >= this.getLimitTime(hzlog.getLIMITTIME())) {
				String dataId = this.getDataIdList(hzlog.getWORKID());
				if(dataId != null) {
					dataIDList.add(dataId);
				}
				count ++;
			}
		}
		remap.clear();
		remap.put("count", count);
		remap.put("dataIDList", dataIDList);
		return remap;
	}
	//审核办理页面，逾期未审核
	public Map<String, Object> queryAuditOverdueNot(String tableName, String flowid, String node, String start, String end, List<String> holidayAndWeekend) {
		List<String> dataIDList = new ArrayList<String>();
		String sql = "SELECT SENDTIME,LIMITTIME,WORKID FROM tw_hz_worklist WHERE (WORKID,ID) IN (SELECT WORKID,MAX(ID) FROM tw_hz_worklist GROUP BY WORKID) AND FLOWID=? AND NODEID=? AND `STATUS`='Author' AND SENDTIME>? AND SENDTIME<?"
				+ " AND WORKID IN (SELECT z.WORKID FROM "+tableName+" y LEFT OUTER JOIN twr_hz_instance z ON y.ID=z.DATAID)";
		@SuppressWarnings("unchecked")
		List<WorkList> list = sessionFactory.getCurrentSession().createSQLQuery(sql)
				.setParameter(0, flowid)
				.setParameter(1, node)
				.setParameter(2, start)
				.setParameter(3, end)
				.setResultTransformer(Transformers.aliasToBean(WorkList.class)).list();
		int count = 0;
		for (WorkList workList : list) {
			if(DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date()) >= this.getLimitTime(workList.getLIMITTIME())) {
				String dataID = this.getDataIdList(workList.getWORKID());
				if(dataID != null) {
					dataIDList.add(dataID);
				}
				count ++;
			}
		}
		remap.clear();
		remap.put("count", count);
		remap.put("dataIDList", dataIDList);
		return remap;
	}
	//审核办理页面，资料不全、有误、重复
	public int queryAuditMaterial(String tabsename, String flowid, String type, String node,String nextNode, String start,
			String end) {
		//资料错误时，审核节点提交的下一个节点不是不同意
		String sql = "select COUNT(*) from tw_hz_log a " +
				"left  outer join twr_hz_instance b on a.workid=b.workid " +
				"left  outer join "+tabsename+" c on b.dataid=c.id " +
				"where a.actiontime>? and a.actiontime<? and c.lzsh=? and a.FLOWID=? AND a.NODEID=? AND (a.ACTION<>'stop' OR a.NEXTNODEIDS<>?)"; 
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql)
							.setParameter(0, start)
							.setParameter(1, end)
							.setParameter(2, type)
							.setParameter(3, flowid)
							.setParameter(4, node)
							.setParameter(5, nextNode)
							.uniqueResult().toString();
		return Integer.parseInt(count);
	}
	//审批资料不全，资料有误，重复提交的情况
	//审批节点提交的节点不是不同意且lzsh字段为资料不全等
	public int queryAuditMaterialRatify(String tabase, String flowid, String type, String node, String nextNode, String start,
			String end) {
		return this.queryAuditMaterial(tabase, flowid, type, node, nextNode, start, end);
	}
	//根据workID查询dataID
	@SuppressWarnings("unchecked")
	private List<String> getDataIdList(List<String> workList) {
		List<String> list = new ArrayList<String>();
		for (String workid : workList) {
			String sql = "SELECT DATAID FROM twr_hz_instance WHERE WORKID=?";
			List<String> list2 = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, workid).list();
			if(list2.size() > 0) {
				list.add(list2.get(list2.size()-1));
			}
		}
		return list;
	}
	//根据workID查询dataID
	@SuppressWarnings("unchecked")
	private String getDataIdList(String workid) {
		String sql = "SELECT DATAID FROM twr_hz_instance WHERE WORKID=?";
		List<String> list2 = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, workid).list();
		if(list2.size() > 0) {
			return list2.get(list2.size()-1);
		}
		return null;
	}
	
	private int getLimitTime(String limitTime) {
		if(!(limitTime == null || "".equals(limitTime))) {
			int indexOf = limitTime.indexOf("(");
			return Integer.parseInt(limitTime.substring(0, indexOf));
		}
		return 0;
	}
	//#查询审核办理总数ID
	@SuppressWarnings("unchecked")
	public List<String> queryTabseCountID(String table, String start, String end) {
		String sql  = "SELECT ID FROM " + table + " WHERE DATES>? AND DATES<? GROUP BY ID"; //TODO
		return sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, start).setParameter(1, end).list();
	}
	//#查询已审核的ID
	@SuppressWarnings("unchecked")
	public List<String> queryAuditPassWorkIdList(String tableName, String flowid, String node, String start,
			String end) {
		String sql = "SELECT WORKID FROM tw_hz_log WHERE FLOWID=? AND ACTION<>'stop' AND NEXTNODEIDS=? AND ACTIONTIME>? AND ACTIONTIME<? "
				+ " AND WORKID IN (SELECT z.WORKID FROM "+tableName+" y LEFT OUTER JOIN twr_hz_instance z ON y.ID=z.DATAID)";
		
		List<String> workList = sessionFactory.getCurrentSession().createSQLQuery(sql)
										.setParameter(0, flowid)
										.setParameter(1, node)
										.setParameter(2, start)
										.setParameter(3, end)
										.list();
		return this.getDataIdList(workList);
	}
	//#查询审核未通过ID
	public List<String> queryAuditNotPassIDList(String tableName, String flowid, String node , String nextnode,
			String start, String end) {
		String sql = "SELECT WORKID FROM tw_hz_log WHERE FLOWID=? AND NEXTNODEIDS=? AND NODEID=? AND ACTIONTIME>? AND ACTIONTIME<? "
				+ " AND WORKID IN (SELECT z.WORKID FROM "+tableName+" y LEFT OUTER JOIN twr_hz_instance z ON y.ID=z.DATAID)";
		@SuppressWarnings("unchecked")
		List<String> workList = sessionFactory.getCurrentSession().createSQLQuery(sql)
				.setParameter(0, flowid)
				.setParameter(1, nextnode)
				.setParameter(2, node)
				.setParameter(3, start)
				.setParameter(4, end)
				.list();
		return this.getDataIdList(workList);
	}
	//#查询未审核ID
	public List<String> queryAuditStartIDList(String tableName, String flowid, String node, String start, String end) {
		String sql = "SELECT WORKID FROM tw_hz_log WHERE (WORKID,ID) IN (SELECT WORKID,MAX(ID) FROM tw_hz_log GROUP BY WORKID) AND ACTION<>'stop' AND FLOWID=? AND NEXTNODEIDS=? AND ACTIONTIME>? AND ACTIONTIME<?"
				+ " AND WORKID IN (SELECT z.WORKID FROM "+tableName+" y LEFT OUTER JOIN twr_hz_instance z ON y.ID=z.DATAID)";
		@SuppressWarnings("unchecked")
		List<String> workList = sessionFactory.getCurrentSession().createSQLQuery(sql)
				.setParameter(0, flowid)
				.setParameter(1, node)
				.setParameter(2, start)
				.setParameter(3, end)
				.list();
		return this.getDataIdList(workList);
	}
	//#资料不全无误重复的ID
	public List<String> queryAuditMaterialIDList(String tabsename, String flowid, String type, String node,String nextNode, String start,
			String end) {
		//资料错误时，审核节点提交的下一个节点不是不同意
				String sql = "select c.ID from tw_hz_log a " +
						"left  outer join twr_hz_instance b on a.workid=b.workid " +
						"left  outer join "+tabsename+" c on b.dataid=c.id " +
						"where a.actiontime>? and a.actiontime<? and c.lzsh=? and a.FLOWID=? AND a.NODEID=? AND (a.ACTION<>'stop' OR a.NEXTNODEIDS<>?)"; 
				@SuppressWarnings("unchecked")
				List<String> idList = sessionFactory.getCurrentSession().createSQLQuery(sql)
									.setParameter(0, start)
									.setParameter(1, end)
									.setParameter(2, type)
									.setParameter(3, flowid)
									.setParameter(4, node)
									.setParameter(5, nextNode)
									.list();
				return idList;
	}
	@SuppressWarnings("unchecked")
	public List<String> queryTabseCountIDList(String tabse, String start, String end, String nextNode) {
		String sql  = "SELECT A.ID FROM "+tabse+" A LEFT OUTER JOIN twr_hz_instance B ON A.ID=B.DATAID LEFT OUTER JOIN tw_hz_log C ON B.WORKID=C.WORKID WHERE C.NEXTNODEIDS=? AND A.DATES>? AND A.DATES<?";
		return sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, nextNode).setParameter(1, start).setParameter(2, end).list();
	}
	public List<String> queryAuditPassIDList(String tableName, String flowid, String node, String start, String end) {
		String sql = "SELECT WORKID FROM tw_hz_log WHERE FLOWID=? AND ACTION<>'stop' AND NEXTNODEIDS=? AND ACTIONTIME>? AND ACTIONTIME<?"
				+ " AND WORKID IN (SELECT z.WORKID FROM "+tableName+" y LEFT OUTER JOIN twr_hz_instance z ON y.ID=z.DATAID)";
		@SuppressWarnings("unchecked")
		List<String> workList = sessionFactory.getCurrentSession().createSQLQuery(sql)
										.setParameter(0, flowid)
										.setParameter(1, node)
										.setParameter(2, start)
										.setParameter(3, end)
										.list();
		return this.getDataIdList(workList);
	}
	public List<String> queryAuditMaterialRatifyIDList(String tabase, String flowid, String type, String node, String nextNode, String start,
			String end) {
		//资料错误时，审核节点提交的下一个节点不是不同意
				String sql = "select c.ID from tw_hz_log a " +
						"left  outer join twr_hz_instance b on a.workid=b.workid " +
						"left  outer join "+tabase+" c on b.dataid=c.id " +
						"where a.actiontime>? and a.actiontime<? and c.lzsh=? and a.FLOWID=? AND a.NODEID=? AND (a.ACTION<>'stop' OR a.NEXTNODEIDS<>?)"; 
				@SuppressWarnings("unchecked")
				List<String> idList = sessionFactory.getCurrentSession().createSQLQuery(sql)
									.setParameter(0, start)
									.setParameter(1, end)
									.setParameter(2, type)
									.setParameter(3, flowid)
									.setParameter(4, node)
									.setParameter(5, nextNode)
									.list();
				return idList;
	}
	//安全服务页面count的IDList
	@SuppressWarnings("unchecked")
	public List<String> queryCountIDList(String tabseName, String start, String end) {
		String sql = "SELECT ID FROM "+tabseName+" WHERE DATES>? AND DATES<? GROUP BY ID"; //TODO
		return sessionFactory.getCurrentSession().createSQLQuery(sql)
													.setParameter(0, start)
													.setParameter(1, end).list();
	}
	//安全服务页面资料不全有误重复IDList
	@SuppressWarnings("unchecked")
	public List<String> querySendBackIDList(String tabase, String flowid, String type, String node, String start, String end) {
		
		String sql = "select c.ID from tw_hz_log a " +
		"left  outer join twr_hz_instance b on a.workid=b.workid " +
		"left  outer join "+tabase+" c on b.dataid=c.id " +
		"where a.actiontime>? and a.actiontime<? and c.lzsh=? and a.FLOWID=? AND a.NODEID=? AND (a.ACTION='stop' OR a.NEXTNODEIDS LIKE 'End%')"; 
			
		return sessionFactory.getCurrentSession().createSQLQuery(sql)
											.setParameter(0, start)
											.setParameter(1, end)
											.setParameter(2, type)
											.setParameter(3, flowid)
											.setParameter(4, node).list();
	
	}
	
	
	
	
	
	
	
}
