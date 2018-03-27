package com.henghao.news.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.henghao.news.entity.DetailsEntity;
import com.henghao.news.entity.LawsRegulationsEntity;
import com.henghao.news.entity.WorkList;



@Repository
public class IndexDao {
	
	@Resource
	private SessionFactory sessionFactory;
	private Map<String, Object> remap = new HashMap<String, Object>();
	//查询已审批
	public List<DetailsEntity> queryLogEnd(String tabsenames, String firm_name, String flowids, String nodes, String startdate) {
		String sql = "SELECT c.ID AS id, c."+firm_name+" AS firmName, a.ACTIONTIME AS disposeTime, a.USERNAME AS disposeUserName  FROM tw_hz_log a LEFT OUTER JOIN "
				+ "twr_hz_instance b ON a.WORKID=b.WORKID LEFT OUTER JOIN "+tabsenames+" c ON b.DATAID=c.ID" 
				+" WHERE  a.ACTIONTIME >= ? AND NEXTNODEIDS=? ";
				 
		
		@SuppressWarnings("unchecked")
		List<DetailsEntity> list = sessionFactory.getCurrentSession().createSQLQuery(sql)
								.setParameter(0, startdate)
								.setParameter(1, nodes).setResultTransformer(Transformers.aliasToBean(DetailsEntity.class)).list();
								
								
		return list;
	}
	//查询上级退回
	public List<DetailsEntity> queryReject(String tabsenames, String firm_name, String flowids, String lastnodes, String nodes, String startdate) {
		String sql = "SELECT c.ID AS id, c."+firm_name+" AS firmName, a.ACTIONTIME AS disposeTime, a.USERNAME AS disposeUserName  FROM tw_hz_log a LEFT OUTER JOIN "
				+ "twr_hz_instance b ON a.WORKID=b.WORKID LEFT OUTER JOIN "+tabsenames+" c ON b.DATAID=c.ID" 
				+" WHERE (a.WORKID,a.ID) IN (SELECT WORKID,MAX(ID) FROM tw_hz_log WHERE ACTIONTIME >= ? AND NEXTNODEIDS=? AND NODEID=? GROUP BY WORKID) "
				 +" AND a.WORKID IN (SELECT z.WORKID FROM AJ_SC_TWO y LEFT OUTER JOIN twr_hz_instance z ON y.ID=z.DATAID)";
		
		@SuppressWarnings("unchecked")
		List<DetailsEntity> list = sessionFactory.getCurrentSession().createSQLQuery(sql)
								.setParameter(0, startdate)
								.setParameter(1, lastnodes)
								.setParameter(2, nodes)
								.setResultTransformer(Transformers.aliasToBean(DetailsEntity.class)).list();
								
								
		return list;
	}
	//查询流程最新log数据
	public List<DetailsEntity> queryLogNodeid(String nextnodeids,String tabsenames, String firm_name, String flowid, String nodeid, String startdate) {
		String sql = "SELECT c.ID AS id, c."+firm_name+" AS firmName, a.ACTIONTIME AS disposeTime, a.USERNAME AS disposeUserName, a.LIMITTIME AS createDate  FROM tw_hz_log a LEFT OUTER JOIN "
				+ "twr_hz_instance b ON a.WORKID=b.WORKID LEFT OUTER JOIN "+tabsenames+" c ON b.DATAID=c.ID" 
				+" WHERE (a.WORKID,a.ID) IN (SELECT WORKID,MAX(ID) FROM tw_hz_log WHERE ACTIONTIME >= ? AND NEXTNODEIDS=? AND NODEID=? GROUP BY WORKID) "
				 +" AND a.WORKID IN (SELECT z.WORKID FROM AJ_SC_TWO y LEFT OUTER JOIN twr_hz_instance z ON y.ID=z.DATAID)";
		
		@SuppressWarnings("unchecked")
		List<DetailsEntity> list = sessionFactory.getCurrentSession().createSQLQuery(sql)
								.setParameter(0, startdate)
								.setParameter(1, nextnodeids)
								.setParameter(2, nodeid)
								.setResultTransformer(Transformers.aliasToBean(DetailsEntity.class)).list();
								
								
		return list;
	}
	
	//查询待审批。已经提交至审批节点
	public List<DetailsEntity> queryLogUpToDate(String tabsenames, String firm_name, String flowids, String nodes, String startdate) {
		String sql = "SELECT c.ID AS id, c."+firm_name+" AS firmName, a.ACTIONTIME AS disposeTime, a.USERNAME AS disposeUserName  FROM tw_hz_log a LEFT OUTER JOIN "
				+ "twr_hz_instance b ON a.WORKID=b.WORKID LEFT OUTER JOIN "+tabsenames+" c ON b.DATAID=c.ID" 
				+" WHERE (a.WORKID,a.ID) IN (SELECT WORKID,MAX(ID) FROM tw_hz_log WHERE ACTIONTIME >= ? AND NEXTNODEIDS=? GROUP BY WORKID) ";
				 
		
		@SuppressWarnings("unchecked")
		List<DetailsEntity> list = sessionFactory.getCurrentSession().createSQLQuery(sql)
								.setParameter(0, startdate)
								.setParameter(1, nodes).setResultTransformer(Transformers.aliasToBean(DetailsEntity.class)).list();
								
								
		return list;
	}
	//督责问责菜单下的政策法规
	public Map<String, Object> queryLawsAndRegulations(String type, int page, int size) {
		if("".equals(type) || null == type) {
			String sqlcount = "SELECT COUNT(*) FROM aj_zcfgy";
			String string = sessionFactory.getCurrentSession().createSQLQuery(sqlcount).uniqueResult().toString();
			int total = 0;
			total = Integer.parseInt(string);
			if((page-1) * size >= total) {
				page = page - 1;
			}
			if(size > total) {
				size = total;
			}
			int start = (page-1)*size;
			String sql = "SELECT ID, LB, NAME, URL, TIME FROM  aj_zcfgy LIMIT ?,?";
			@SuppressWarnings("unchecked")
			List<LawsRegulationsEntity> list = sessionFactory.getCurrentSession().createSQLQuery(sql)
								.setParameter(0, start)
								.setParameter(1, size)
								.setResultTransformer(Transformers.aliasToBean(LawsRegulationsEntity.class)).list();
			remap.put("total", total);
			remap.put("list", list);
			return remap;
		}else{
			String sqlcount = "SELECT COUNT(*) FROM aj_zcfgy WHERE LB=?";
			String string = sessionFactory.getCurrentSession().createSQLQuery(sqlcount).setParameter(0, type).uniqueResult().toString();
			int total = 0;
			total = Integer.parseInt(string);
			if((page-1) * size >= total) {
				page = page - 1;
			}
			if(size > total) {
				size = total;
			}
			int start = (page-1)*size;
			String sql = "SELECT ID, LB, NAME, URL, TIME FROM  aj_zcfgy WHERE LB=? LIMIT ?,?";
			@SuppressWarnings("unchecked")
			List<LawsRegulationsEntity> list = sessionFactory.getCurrentSession().createSQLQuery(sql)
								.setParameter(0, type)
								.setParameter(1, start)
								.setParameter(2, size)
								.setResultTransformer(Transformers.aliasToBean(LawsRegulationsEntity.class)).list();
			remap.put("total", total);
			remap.put("list", list);
			return remap;
		}
		
	}
	public Map<String, Object> queryLawsAndRegulationSvague(String svague, int page, int size) {
		if("".equals(svague) || null == svague) {
			String sqlcount = "SELECT COUNT(*) FROM aj_zcfgy";
			String string = sessionFactory.getCurrentSession().createSQLQuery(sqlcount).uniqueResult().toString();
			int total = 0;
			total = Integer.parseInt(string);
			if((page-1) * size >= total) {
				page = page - 1;
			}
			if(size > total) {
				size = total;
			}
			int start = (page-1)*size;
			String sql = "SELECT ID, LB, NAME, URL, TIME FROM  aj_zcfgy LIMIT ?,?";
			@SuppressWarnings("unchecked")
			List<LawsRegulationsEntity> list = sessionFactory.getCurrentSession().createSQLQuery(sql)
								.setParameter(0, start)
								.setParameter(1, size)
								.setResultTransformer(Transformers.aliasToBean(LawsRegulationsEntity.class)).list();
			remap.put("total", total);
			remap.put("list", list);
			return remap;
		}else{
			String sqlcount = "SELECT COUNT(*) FROM aj_zcfgy WHERE  `NAME` LIKE ?";
			String string = sessionFactory.getCurrentSession().createSQLQuery(sqlcount).setParameter(0, "%"+svague+"%").uniqueResult().toString();
			int total = 0;
			total = Integer.parseInt(string);
			if((page-1) * size >= total) {
				page = page - 1;
			}
			if(size > total) {
				size = total;
			}
			int start = (page-1)*size;
			String sql = "SELECT ID, LB, NAME, URL, TIME FROM  aj_zcfgy WHERE `NAME` LIKE ? LIMIT ?,?";
			@SuppressWarnings("unchecked")
			List<LawsRegulationsEntity> list = sessionFactory.getCurrentSession().createSQLQuery(sql)
								.setParameter(0, "%"+svague+"%")
								.setParameter(1, start)
								.setParameter(2, size)
								.setResultTransformer(Transformers.aliasToBean(LawsRegulationsEntity.class)).list();
			remap.put("total", total);
			remap.put("list", list);
			return remap;
		}
	}
	//首页小喇叭个人代办
	public Map<String, Object> queryTrumpetNews(String userId, int page, int size) {
		String sqlcount = "SELECT COUNT(*) FROM tw_hz_worklist a	WHERE (" +
				"a.auth_id = ? "+
				"OR a.agent_id = ? " +
				")" +
			"AND isactive = '1' " +
			"AND status_no < '200' ORDER BY a.sendtime desc";
		String string = sessionFactory.getCurrentSession().createSQLQuery(sqlcount).setParameter(0, userId).setParameter(1, userId).uniqueResult().toString();
		int total = Integer.parseInt(string);
		if((page-1) * size >= total) {
			page = page - 1;
		}
		if(size > total) {
			size = total;
		}
		int start = (page-1)*size;
		String sql = "SELECT a.TITLE, a.FLOWNAME, a.SENDUSERNAME, a.SENDTIME FROM tw_hz_worklist a	WHERE (" +
					"a.auth_id = ? "+
					"OR a.agent_id = ? " +
					")" +
				"AND isactive = '1' " +
				"AND status_no < '200' ORDER BY a.sendtime desc LIMIT ?,?";
		
		@SuppressWarnings("unchecked")
		List<WorkList> list = sessionFactory.getCurrentSession().createSQLQuery(sql)
										.setParameter(0, userId)
										.setParameter(1, userId)
										.setParameter(2, start)
										.setParameter(3, size)
										.setResultTransformer(Transformers.aliasToBean(WorkList.class)).list();
		remap.clear();
		remap.put("total", total);
		remap.put("list", list);
		return remap;
	}

}
