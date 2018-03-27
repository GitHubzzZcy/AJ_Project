package com.henghao.news.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.henghao.news.entity.DetailsEntity;
import com.henghao.news.entity.Hzlog;
import com.henghao.news.entity.WorkList;
import com.henghao.news.util.DateUtils;

@Repository
public class DetailsDao {

	@Resource
	private SessionFactory sessionFactory;
	//资料不全、资料有误、重复录入详情数据
	public List<DetailsEntity> queryDataIncomplete(String tabsename, String firm_name, String dispose_nodes, String itemType,
			String start, String end) {
		String sql = "SELECT a.ID AS id, a."+firm_name+" AS firmName,c.USERNAME AS disposeUserName, c.ACTIONTIME AS disposeTime  "
					+ "FROM "+tabsename+" a LEFT OUTER JOIN twr_hz_instance b ON a.ID=b.DATAID "
					+ "LEFT  OUTER JOIN tw_hz_log c ON c.WORKID=B.WORKID "
					+ "WHERE A.LZSH=? "
					+ "AND c.ACTIONTIME>?  "
					+ "AND c.ACTIONTIME<?  "
					+ "AND C.NODEID=? "
					+ "AND c.ACTION='submit'";
		@SuppressWarnings("unchecked")
		List<DetailsEntity> list = sessionFactory.getCurrentSession().createSQLQuery(sql)
										.setParameter(0, itemType)
										.setParameter(1, start)
										.setParameter(2, end)
										.setParameter(3, dispose_nodes)
										.setResultTransformer(Transformers.aliasToBean(DetailsEntity.class)).list();
		return list;
	}
	public List<DetailsEntity> queryAnjianTongjiOverdueDispose(String tabsename, String firm_name, String flowid, List<String> fLOWID1sh,
			String start, String end) throws ParseException {
		List<DetailsEntity> relist = new ArrayList<DetailsEntity>();
		List<Hzlog> loglist = new ArrayList<Hzlog>();
		for (String node : fLOWID1sh) {
			String sql = "SELECT WORKID,USERNAME,ACTIONTIME,DOTIME,LIMITTIME FROM TW_HZ_LOG WHERE FLOWID=? AND NODEID=? AND ACTION<>'stop' AND ACTIONTIME>? AND ACTIONTIME<? "
					+ "AND WORKID IN (SELECT z.WORKID FROM "+tabsename+" y LEFT OUTER JOIN twr_hz_instance z ON y.ID=z.DATAID)";
			@SuppressWarnings("unchecked")
			List<Hzlog> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, flowid)
																					.setParameter(1, node)
																					.setParameter(2, start)
																					.setParameter(3, end)
																					.setResultTransformer(Transformers.aliasToBean(Hzlog.class)).list();
			for (Hzlog hzlog : list) {
				String doTime = hzlog.getDOTIME();
				String limitTime = hzlog.getLIMITTIME();
				String actiontime = hzlog.getACTIONTIME();
				if(DateUtils.getDoActionTime(this.getHolidayAndWeekend(), doTime, actiontime) >= getLimitTime(limitTime)) {
					loglist.add(hzlog);
				}
			}
		}
		for (Hzlog hzlog : loglist) {
			String sql = "SELECT b.ID AS id, b."+firm_name+" AS firmName FROM twr_hz_instance a RIGHT  OUTER JOIN "+tabsename+" b ON a.DATAID=b.ID WHERE a.WORKID=?";
			DetailsEntity detailsEntity = (DetailsEntity) sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, hzlog.getWORKID()).setResultTransformer(Transformers.aliasToBean(DetailsEntity.class)).uniqueResult();
			detailsEntity.setDisposeTime(hzlog.getACTIONTIME());
			detailsEntity.setDisposeUserName(hzlog.getUSERNAME());
			relist.add(detailsEntity);
		}
		return relist;
	}
	//逾期未审核、逾期未审批
	@SuppressWarnings("unchecked")
	public List<DetailsEntity> queryDataOverdueUnfinished(String[] flownames, String[] tabsenames, String[] firmName, String[] flowids,
							String overdueType, String start, String end) {
		List<DetailsEntity> shlist = new ArrayList<DetailsEntity>();
		List<DetailsEntity> splist = new ArrayList<DetailsEntity>();
		String sql = "SELECT AUTH_ID AS ID,WORKID,NODEID,LIMITTIME,SENDTIME FROM tw_hz_worklist  WHERE (workid,id) IN (SELECT workid,MAX(id) FROM tw_hz_worklist  GROUP BY workid) AND FLOWID=? AND SENDTIME >? AND SENDTIME<? AND TRACKSTATUS='110' AND STATUS='Author'";
		//登记颁发
		List<WorkList> list1 = sessionFactory.getCurrentSession().createSQLQuery(sql)
					.setParameter(0, flowids[0])
					.setParameter(1, start)
					.setParameter(2, end)
					.setResultTransformer(Transformers.aliasToBean(WorkList.class)).list();
		for (WorkList workList : list1) {
			if("Node2".equals(workList.getNODEID())) {
				if(DateUtils.getCurrentAndTime(this.getHolidayAndWeekend(), workList.getSENDTIME(), new Date()) >= this.getLimitTime(workList.getLIMITTIME())) {
					DetailsEntity entity = this.getDBDetailsEntity("安监一处", "逾期未审核", "没有在限定时间内完成审核", 0, workList.getWORKID(), workList.getID(), workList.getSENDTIME(), flownames, tabsenames, firmName);
					if(entity != null) {
						shlist.add(entity);
					}
				}
			}
			if("Node3".equals(workList.getNODEID())) {
				if(DateUtils.getCurrentAndTime(this.getHolidayAndWeekend(), workList.getSENDTIME(), new Date()) >= this.getLimitTime(workList.getLIMITTIME())) {
					DetailsEntity entity = this.getDBDetailsEntity("安监一处", "逾期未审批", "没有在限定时间内完成审批", 0, workList.getWORKID(), workList.getID(), workList.getSENDTIME(), flownames, tabsenames, firmName);
					if(entity != null) {
						splist.add(entity);
					}
				}
			}
		}
		//安全服务
		List<WorkList> list2 = sessionFactory.getCurrentSession().createSQLQuery(sql)
				.setParameter(0, flowids[1])
				.setParameter(1, start)
				.setParameter(2, end)
				.setResultTransformer(Transformers.aliasToBean(WorkList.class)).list();
		for (WorkList workList : list2) {
			if("Node4".equals(workList.getNODEID()) || "Node6".equals(workList.getNODEID())) {
				if(DateUtils.getCurrentAndTime(this.getHolidayAndWeekend(), workList.getSENDTIME(), new Date()) >= this.getLimitTime(workList.getLIMITTIME())) {
					DetailsEntity entity = this.getDBDetailsEntity("安监一处", "逾期未审核", "没有在限定时间内完成审核", 1, workList.getWORKID(), workList.getID(), workList.getSENDTIME(), flownames, tabsenames, firmName);
					if(entity != null) {
						shlist.add(entity);
					}
				}
			}
		}
		//权限内非煤矿山安全设施设计审查   
		List<WorkList> list3 = sessionFactory.getCurrentSession().createSQLQuery(sql)
				.setParameter(0, flowids[2])
				.setParameter(1, start)
				.setParameter(2, end)
				.setResultTransformer(Transformers.aliasToBean(WorkList.class)).list();
		for (WorkList workList : list3) {
			if("Node3".equals(workList.getNODEID()) || 
				"Node10".equals(workList.getNODEID()) ||
				"Node4".equals(workList.getNODEID())  ||
				"Node7".equals(workList.getNODEID())) {
				if(DateUtils.getCurrentAndTime(this.getHolidayAndWeekend(), workList.getSENDTIME(), new Date()) >= this.getLimitTime(workList.getLIMITTIME())) {
					DetailsEntity entity = this.getDBDetailsEntity("安监一处", "逾期未审核", "没有在限定时间内完成审核", 2, workList.getWORKID(), workList.getID(), workList.getSENDTIME(), flownames, tabsenames, firmName);
					if(entity != null) {
						shlist.add(entity);
					}
				}
				
			}
			if("Node8".equals(workList.getNODEID())) {
				if(DateUtils.getCurrentAndTime(this.getHolidayAndWeekend(), workList.getSENDTIME(), new Date()) >= this.getLimitTime(workList.getLIMITTIME())) {
					DetailsEntity entity = this.getDBDetailsEntity("安监一处", "逾期未审批", "没有在限定时间内完成审批", 2, workList.getWORKID(), workList.getID(), workList.getSENDTIME(), flownames, tabsenames, firmName);
					if(entity != null) {
						splist.add(entity);
					}
				}
			}
		}
		//安全生产标准化审批流程(初次申请)
		List<WorkList> list4 = sessionFactory.getCurrentSession().createSQLQuery(sql)
				.setParameter(0, flowids[3])
				.setParameter(1, start)
				.setParameter(2, end)
				.setResultTransformer(Transformers.aliasToBean(WorkList.class)).list();
		for (WorkList workList : list4) {
			if("Node3".equals(workList.getNODEID())  || 
				"Node9".equals(workList.getNODEID()) ) {
				if(DateUtils.getCurrentAndTime(this.getHolidayAndWeekend(), workList.getSENDTIME(), new Date()) >= this.getLimitTime(workList.getLIMITTIME())) {
					DetailsEntity entity = this.getDBDetailsEntity("安监四处", "逾期未审核", "没有在限定时间内完成审核", 3, workList.getWORKID(), workList.getID(), workList.getSENDTIME(), flownames, tabsenames, firmName);
					if(entity != null) {
						shlist.add(entity);
					}
				}
			}
			if("Node10".equals(workList.getNODEID())) {
				if(DateUtils.getCurrentAndTime(this.getHolidayAndWeekend(), workList.getSENDTIME(), new Date()) >= this.getLimitTime(workList.getLIMITTIME())) {
					DetailsEntity entity = this.getDBDetailsEntity("安监四处", "逾期未审批", "没有在限定时间内完成审批", 3, workList.getWORKID(), workList.getID(), workList.getSENDTIME(), flownames, tabsenames, firmName);
					if(entity != null) {
						splist.add(entity);
					}
				}
			}
		}
		//安全生产标准化审批流程(复审)
		List<WorkList> list5 = sessionFactory.getCurrentSession().createSQLQuery(sql)
				.setParameter(0, flowids[4])
				.setParameter(1, start)
				.setParameter(2, end)
				.setResultTransformer(Transformers.aliasToBean(WorkList.class)).list();
		for (WorkList workList : list5) {
			if("Node2".equals(workList.getNODEID())) {
				if(DateUtils.getCurrentAndTime(this.getHolidayAndWeekend(), workList.getSENDTIME(), new Date()) >= this.getLimitTime(workList.getLIMITTIME())) {
					DetailsEntity entity = this.getDBDetailsEntity("安监四处", "逾期未审核", "没有在限定时间内完成审核", 4, workList.getWORKID(), workList.getID(), workList.getSENDTIME(), flownames, tabsenames, firmName);
					if(entity != null) {
						shlist.add(entity);
					}
				}
				
			}
			if("Node3".equals(workList.getNODEID())) {
				if(DateUtils.getCurrentAndTime(this.getHolidayAndWeekend(), workList.getSENDTIME(), new Date()) >= this.getLimitTime(workList.getLIMITTIME())) {
					DetailsEntity entity = this.getDBDetailsEntity("安监四处", "逾期未审批", "没有在限定时间内完成审批", 4, workList.getWORKID(), workList.getID(), workList.getSENDTIME(), flownames, tabsenames, firmName);
					if(entity != null) {
						splist.add(entity);
					}
				}
			}
		}
		if("逾期未审核".equals(overdueType)) {
			return shlist;
		}
		if("逾期未审批".equals(overdueType)) {
			return splist;
		}
		return null;
	}
	
	//安全服务页面已受理详情
	public List<DetailsEntity> queryDataYetTransactionList(String tablesName,String firm_name, String flowid, String type, String node,
			String nextNode, String start, String end) {
		String sql = "select c.ID AS id, c."+firm_name+" AS firmName,a.USERNAME AS disposeUserName, a.ACTIONTIME AS disposeTime from tw_hz_log a " +
				"left  outer join twr_hz_instance b on a.workid=b.workid " +
				"left  outer join "+tablesName+" c on b.dataid=c.id " +
				"where a.actiontime>? and a.actiontime<? and a.FLOWID=? AND a.NODEID=? AND (a.ACTION<>'stop' OR a.NEXTNODEIDS<>?)"; 
		@SuppressWarnings("unchecked")
		List<DetailsEntity> list = sessionFactory.getCurrentSession().createSQLQuery(sql)
										.setParameter(0, start)
										.setParameter(1, end)
										.setParameter(2, flowid)
										.setParameter(3, node)
										.setParameter(4, nextNode)
										.setResultTransformer(Transformers.aliasToBean(DetailsEntity.class)).list();
		return list;
	}
	
	private DetailsEntity getDBDetailsEntity(String disposeDept, String errorType, String errorDesc, int i, String workid, String userid, String disposeTime, String[] flownames, 
			String[] tabsenames, String[] firmName) {
		DetailsEntity detailsEntity = new DetailsEntity();
		detailsEntity.setDisposeUserName(this.getUsernameById(userid));
		detailsEntity.setDisposeTime(disposeTime);
		detailsEntity.setDisposeDept(disposeDept);
		String ajsql = "SELECT b.ID AS id, b."+firmName[i]+" AS firmName FROM twr_hz_instance a RIGHT  OUTER JOIN "+tabsenames[i]+" b ON a.DATAID=b.ID WHERE a.WORKID=?";
		DetailsEntity dE = (DetailsEntity) sessionFactory.getCurrentSession().createSQLQuery(ajsql).setParameter(0, workid).setResultTransformer(Transformers.aliasToBean(DetailsEntity.class)).uniqueResult();
		if(dE != null) {
			detailsEntity.setId(dE.getId());
			detailsEntity.setFirmName(dE.getFirmName());
			detailsEntity.setItemType(flownames[i]);
			detailsEntity.setErrorType(errorType);
			detailsEntity.setErrorDesc(errorDesc);
			return detailsEntity;
		}
		return null;
	}
	private String getUsernameById(String userid) {
		String sql = "SELECT NAME FROM to_horizon_user WHERE ID=?";
		Object result = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, userid).uniqueResult();
		if(result != null) {
			return result.toString();
		}
		return  null;
	}
	
	private int getLimitTime(String limitTime) {
		if(!(limitTime == null || "".equals(limitTime))) {
			int indexOf = limitTime.indexOf("(");
			return Integer.parseInt(limitTime.substring(0, indexOf));
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	private List<String> getHolidayAndWeekend() {
		int year = Integer.parseInt(DateUtils.getCurrentDate("yyyy"));
		String sql = "SELECT date FROM rest_day WHERE date LIKE ? OR date LIKE ?";
		return sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, year + "%").setParameter(1, (year-1) + "%").list();
	}
	//查询IDList对应表
	public Object queryTableName(String dataId) {
		String sql = "SELECT WORKID, TABLEID FROM twr_hz_instance WHERE DATAID=?";
		Object uniqueResult = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, dataId).uniqueResult();
		return uniqueResult;
	}
	public DetailsEntity queryDBFlowEntity(String dataId, String tableName, String firm_name) {
		String sql = "SELECT ID AS id, "+firm_name+" AS firmName, DATES AS createDate, LZSH AS errorType FROM "+tableName+" WHERE ID=?";
		//to TODO 未解决工作流的bug  修改返回List
		@SuppressWarnings("unchecked")
		List<DetailsEntity> uniqueResult = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, dataId).setResultTransformer(Transformers.aliasToBean(DetailsEntity.class)).list();
		if(uniqueResult.size() == 2) {
			return uniqueResult.get(1);
		}
		if(uniqueResult.size() > 2) {
			DetailsEntity detailsEntity = uniqueResult.get(uniqueResult.size()-1);
			detailsEntity.setErrorType(uniqueResult.get(1).getErrorType());
			return uniqueResult.get(1);
		}
		if(uniqueResult.size() == 1) {
			return uniqueResult.get(0);
		}
		return new DetailsEntity();
	}
	//查询日志表最新数据
	public DetailsEntity queryNewsLogFlow(String workid) {
		String sql = "SELECT USERNAME AS disposeUserName, ACTIONTIME AS disposeTime FROM tw_hz_log WHERE WORKID=? ORDER BY ID DESC LIMIT 0,1";
		Object uniqueResult = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, workid).setResultTransformer(Transformers.aliasToBean(DetailsEntity.class)).uniqueResult();
		if(uniqueResult != null) {
			return (DetailsEntity) uniqueResult;
		}
		return null;
	}
	//查询日志表节点数据
	public DetailsEntity queryNodeLogFlow(String Node, String workid) {
		String sql = "SELECT USERNAME AS disposeUserName, ACTIONTIME AS disposeTime FROM tw_hz_log WHERE WORKID=? AND NODEID=? ORDER BY ID DESC LIMIT 0,1";
		Object uniqueResult = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, workid).setParameter(1, Node).setResultTransformer(Transformers.aliasToBean(DetailsEntity.class)).uniqueResult();
		if(uniqueResult != null) {
			return (DetailsEntity) uniqueResult;
		}
		return null;
	}
	
	
}
