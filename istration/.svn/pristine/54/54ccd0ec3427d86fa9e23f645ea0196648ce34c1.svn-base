package com.henghao.istration.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.henghao.istration.dao.IApproveDao;
import com.henghao.istration.entity.Example;
import com.henghao.istration.entity.Flow;
import com.henghao.istration.entity.Head;
import com.henghao.istration.entity.Hzlog;
import com.henghao.istration.entity.Promise;
import com.henghao.istration.entity.Transactor;
@Repository("approveDao")
public class ApproveDaoImpl implements IApproveDao {

	@Resource
	private SessionFactory sessionFactory;
	Map<String, Object> map = new HashMap<String, Object>();
	
	@Override
	public List<Object> queryApproveClass() {
		List<Object> list = new ArrayList<Object>();
		String sql1 = "SELECT COUNT(*) FROM aj_yichu";
		String sql2 = "SELECT COUNT(*) FROM aj_sc_one";
		String sql3 = "SELECT COUNT(*) FROM aj_sc_two";
		String yichu = sessionFactory.getCurrentSession().createSQLQuery(sql1).uniqueResult().toString();
		String sichu = sessionFactory.getCurrentSession().createSQLQuery(sql2).uniqueResult().toString();
		String sichutwo = sessionFactory.getCurrentSession().createSQLQuery(sql3).uniqueResult().toString();
		String sql11 = "SELECT FLOWNAME FROM tw_hz_instance WHERE FLOWID='feimeikuangshan(yichu)' ORDER BY STARTTIME DESC LIMIT 0,1";
		String sql22 = "SELECT FLOWNAME FROM tw_hz_instance WHERE FLOWID='sichu(chucishenqing)' ORDER BY STARTTIME DESC LIMIT 0,1";
		String sql33 = "SELECT FLOWNAME FROM tw_hz_instance WHERE FLOWID='sichu(fushen)' ORDER BY STARTTIME DESC LIMIT 0,1";
		Object ycnames = sessionFactory.getCurrentSession().createSQLQuery(sql11).uniqueResult();
		Object scnames = sessionFactory.getCurrentSession().createSQLQuery(sql22).uniqueResult();
		Object sichunames = sessionFactory.getCurrentSession().createSQLQuery(sql33).uniqueResult();
		String ycname = null;
		String scname = null;
		String sichuname = null;
		if(null != ycnames) {
			ycname = ycnames.toString();
		}
		if(null != scnames) {
			scname = scnames.toString();
		}
		if(null != sichunames) {
			sichuname = sichunames.toString();
		}
		
		
		Flow f1 = new Flow();
		Flow f2 = new Flow();
		Flow f3 = new Flow();
		f1.setId("feimeikuangshan(yichu)");
		f1.setName(ycname);
		f1.setCounts(yichu);
		
		f2.setId("sichu(chucishenqing)");
		f2.setName(scname);
		f2.setCounts(sichu);
		
		f3.setId("sichu(fushen)");
		f3.setName(sichuname);
		f3.setCounts(sichutwo);
		
		
		list.add(f1);
		list.add(f2);
		list.add(f3);
		
		return list;
	}

	@Override
	public List<Example> queryfirmname(String flowid) {
		String sql = null;
		if("feimeikuangshan(yichu)".equals(flowid)) {
			sql = "SELECT D.ID,D.FIRMNAME,C.ACTIONTIME FROM tw_hz_log C INNER JOIN (SELECT A.ID,B.WORKID,A.FLRMBNAME AS FIRMNAME FROM aj_yichu A INNER JOIN twr_hz_instance B ON A.ID=B.DATAID) D  ON C.WORKID=D.WORKID WHERE NODEID='Start'";
		}
		if("sichu(chucishenqing)".equals(flowid)) {
			sql = "SELECT D.ID,D.FIRMNAME,C.ACTIONTIME FROM tw_hz_log C INNER JOIN (SELECT A.ID,B.WORKID,A.FIRMNAME FROM aj_sc_one A INNER JOIN twr_hz_instance B ON A.ID=B.DATAID) D  ON C.WORKID=D.WORKID WHERE NODEID='Start'";
		}
		if("sichu(fushen)".equals(flowid)) {
			sql = "SELECT D.ID,D.FIRMNAME,C.ACTIONTIME FROM tw_hz_log C INNER JOIN (SELECT A.ID,B.WORKID,A.FIRMNAME FROM aj_sc_two A INNER JOIN twr_hz_instance B ON A.ID=B.DATAID) D  ON C.WORKID=D.WORKID WHERE NODEID='Start'";
		}
		//System.out.println("flowname!!!!!!!!!!"+flowid);
		@SuppressWarnings("unchecked")
		List<Example> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Example.class)).list();
		
		return list;
	}

	@Override
	public String queryWorkid(String applyid) {
		String sql = "SELECT WORKID FROM twr_hz_instance WHERE DATAID=?";
		List<?> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, applyid).list();
		if(list.size() > 0) {
			String workid = list.get(0).toString();
			return workid;
		}
		return null;
	}

	@Override
	public List<Hzlog> queryLog(String workid) {
		String sql = "SELECT ID,NODENAME,FLOWID,FLOWNAME,USERID,USERNAME,ACTIONTIME,DOTIME,LIMITTIME,TITLE,NODEID,MEMO,NEXTNODEIDS FROM tw_hz_log WHERE WORKID=?";
		@SuppressWarnings("unchecked")
		List<Hzlog> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Hzlog.class)).
				setParameter(0, workid).list();
		return list;
	}

	@Override
	public Head userhead(String name) {
		try {
			String sql = "SELECT SAVE_NAME,EXTENTION,SAVE_PATH FROM ta_horizon_info WHERE OBJECT_ID=(SELECT ID FROM to_horizon_user WHERE NAME =?) AND FIELD_NAME='headImg'";
			@SuppressWarnings("unchecked")
			List<Head> list = sessionFactory.getCurrentSession().createSQLQuery(sql).
			setResultTransformer(Transformers.aliasToBean(Head.class)).
			setParameter(0, name).list();
			if(0 != list.size()) {
				return list.get(list.size()-1);
			}
		} catch (Exception e) {
			
		}
		return null;
	}

	@Override
	public String queryUser(String string) {
		String sql = "SELECT DEPT_NAME AS department FROM to_horizon_dept WHERE ID=" +
						"(SELECT DEPT_ID FROM tor_horizon_user_dept WHERE USER_ID=" +
								"(SELECT ID FROM to_horizon_user WHERE NAME=?))";
		
		return sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, string).uniqueResult().toString();
	}

	@Override
	public List<Example> queryfirmAll() {
		String sql = "SELECT D.ID,D.FIRMNAME,C.ACTIONTIME FROM tw_hz_log C INNER JOIN " +
				"(SELECT A.ID,B.WORKID,A.FIRMNAME FROM aj_sc_two A INNER JOIN twr_hz_instance B ON A.ID=B.DATAID) D  " +
				"ON C.WORKID=D.WORKID WHERE NODEID='Start' UNION SELECT D.ID,D.FIRMNAME,C.ACTIONTIME FROM tw_hz_log C INNER JOIN " +
				"(SELECT A.ID,B.WORKID,A.FIRMNAME FROM aj_sc_one A INNER JOIN twr_hz_instance B ON A.ID=B.DATAID) D  " +
				"ON C.WORKID=D.WORKID WHERE NODEID='Start' UNION SELECT D.ID,D.FIRMNAME,C.ACTIONTIME FROM tw_hz_log C INNER JOIN " +
				"(SELECT A.ID,B.WORKID,A.FLRMBNAME AS FIRMNAME FROM aj_yichu A INNER JOIN twr_hz_instance B ON A.ID=B.DATAID) D  " +
				"ON C.WORKID=D.WORKID WHERE NODEID='Start'";
		@SuppressWarnings("unchecked")
		List<Example> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Example.class)).list();
		
		return list;
	}

	@Override
	public Transactor queryblr(String chart) {
		String sql = null;
		if("yichu".equals(chart)) {
			sql = "SELECT * FROM aj_yichu_blrs ORDER BY ID DESC LIMIT 0,1";
		}
		if("sconeblr".equals(chart)) {
			sql = "SELECT * FROM aj_sc_one_blrs ORDER BY ID DESC LIMIT 0,1";
		}
		if("sctwoblr".equals(chart)) {
			sql = "SELECT * FROM aj_sc_two_blrs ORDER BY ID DESC LIMIT 0,1";
		}
		@SuppressWarnings("unchecked")
		List<Transactor> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Transactor.class)).list();
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public String before(String ym) {
		String sql = "SELECT COUNT(*) FROM tw_hz_log A WHERE (A.FLOWID='feimeikuangshan(yichu)' OR A.FLOWID='aqfw' OR A.FLOWID='sichu(chucishenqing)' OR A.FLOWID='sichu(fushen)') AND A.NEXTNODEIDS LIKE 'End%' AND A.ACTIONTIME LIKE ? ";
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, ym+"%").uniqueResult().toString();
		return count;
	}

	@Override
	public String promise(String currentDate, String currentDate2) {
		String sql = "SELECT COUNT(*) FROM tw_hz_log A WHERE (A.FLOWID='feimeikuangshan(yichu)' OR A.FLOWID='aqfw' OR A.FLOWID='sichu(chucishenqing)' OR A.FLOWID='sichu(fushen)') AND A.NEXTNODEIDS LIKE 'End%' AND A.ACTIONTIME LIKE ? AND A.ACTIONTIME < ?";
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, currentDate+"%").setParameter(1, currentDate2).uniqueResult().toString();
		return count;
	}

	@Override
	public List<Promise> promise(String yer) {
		String sql = "SELECT A.MOUTH AS mouth,A.MOUTH_J AS number FROM aj_promise A  WHERE A.YEAR=?";
		@SuppressWarnings("unchecked")
		List<Promise> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Promise.class)).setParameter(0, yer).list();
		return list;
	}

}
