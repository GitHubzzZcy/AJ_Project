package com.henghao.istration.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.henghao.istration.dao.IUserApeDao;
import com.henghao.istration.entity.Backlog;
import com.henghao.istration.entity.Hzlog;
import com.henghao.istration.entity.Personnel;
import com.henghao.istration.entity.Record;

@Repository("userApeDao")
public class UserApeDaoImpl implements IUserApeDao {

	@Resource
	private SessionFactory sessionFactory;
	
	
	@Override
	public String queryuserid(String name) {
		String sql = "SELECT ID FROM to_horizon_user WHERE `NAME`=?";
		String userid = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, name).uniqueResult().toString();
		return userid;
	}
	
	@Override
	public String queryApprove(String name, String lcid, String date) {
		String sql = "SELECT COUNT(*) FROM tw_hz_log WHERE USERNAME =?  AND FLOWID =? AND ACTIONTIME LIKE ? AND ACTION <>'autofirst'";
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, name).setParameter(1, lcid).setParameter(2, date+"%").uniqueResult().toString();
		return count;
	}
	@Override
	public String queryApproveNotFlow(String name, String date) {
		String sql = "SELECT COUNT(*) FROM tw_hz_worklist  WHERE SENDUSERNAME =? AND `STATUS`='Done'  AND DOTIME LIKE ?";
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, name).setParameter(1, date+"%").uniqueResult().toString();
		return count;
	}

	//待办
	@Override
	public String queryBacklog(String userid) {
		String sql = "SELECT COUNT(*) FROM tw_hz_worklist A WHERE A.AUTH_ID=? AND `STATUS`='Author'";
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, userid).uniqueResult().toString();
		return count;
	}

	//已办
	@Override
	public String queryFinish(String userid) {
		String sql = "SELECT COUNT(*) FROM tw_hz_worklist A WHERE A.SENDUSERID=? AND `STATUS`='Done'";
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, userid).uniqueResult().toString();
		return count;
	}

	@Override
	public List<Backlog> queryOvertime(String userid) {
		String sql = "SELECT A.SENDTIME,A.LIMITTIME FROM tw_hz_worklist A WHERE A.AUTH_ID=? AND `STATUS`='Author'";
		@SuppressWarnings("unchecked")
		List<Backlog> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Backlog.class)).setParameter(0, userid).list();
		return list;
	}

	//查询部门名
	@Override
	public String queryDeptno(String name) {
		String sql = "SELECT C.DEPT_NAME FROM to_horizon_user A LEFT JOIN tor_horizon_user_dept B ON A.ID=B.USER_ID LEFT JOIN to_horizon_dept C ON B.DEPT_ID = C.ID WHERE `NAME`=?";
		Object result = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, name).uniqueResult();
		if(null != result) {
			return result.toString();
		}
		return null;
	}

	//查询部门人数
	@Override
	public String findDeptPs(String dept) {
		String sql = "SELECT COUNT(*) FROM to_horizon_dept A  LEFT  JOIN tor_horizon_user_dept B ON A.ID=B.DEPT_ID WHERE A.DEPT_NAME=?";
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, dept).uniqueResult().toString();
		return count;
	}
	//查询工作流录入的承诺办件数据
	@Override
	public String queryDeptPromise(String deptnum, String ym) {
		String sql = null;
		if("安监执法一大队".equals(deptnum)) {
			sql = "SELECT  YIDD FROM AJ_ZDZFJCJHB A WHERE CONCAT(A.NIAN,'-',A.YUE)=?";
		} 
		if("安监执法二大队".equals(deptnum)) {
			sql = "SELECT  ERDD FROM AJ_ZDZFJCJHB A WHERE CONCAT(A.NIAN,'-',A.YUE)=?";
		}
		if("安监执法三大队".equals(deptnum)) {
			sql = "SELECT  SANDD FROM AJ_ZDZFJCJHB A WHERE CONCAT(A.NIAN,'-',A.YUE)=?";
		}
		if("安监执法四大队".equals(deptnum)) {
			sql = "SELECT  SIDD FROM AJ_ZDZFJCJHB A WHERE CONCAT(A.NIAN,'-',A.YUE)=?";
		}
		if("安监执法五大队".equals(deptnum)) {
			sql = "SELECT  WUDD FROM AJ_ZDZFJCJHB A WHERE CONCAT(A.NIAN,'-',A.YUE)=?";
		}
		Object result = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, ym).uniqueResult();
		if(null != result) {
			return result.toString();
		}
		return "0";
	}

	@Override
	public String queryMonthFinish(String userid, String ym) {
		String sql = "SELECT COUNT(*) FROM tw_hz_worklist A WHERE A.AUTH_ID=? AND `STATUS`='Done' AND (FLOWID='feimeikuangshan(yichu)' OR FLOWID='sichu(chucishenqing)' OR FLOWID='aqfw' OR FLOWID='djbf') AND DOTIME LIKE ?";
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, userid).setParameter(1, ym+"%").uniqueResult().toString();
		return count;
	}

	//查询个人人事信息
	@Override
	public Personnel queryPersonnel(String name) {
		String sql = "SELECT id, title, Creator, taitou, neirong, dw, riqi, ry, gerenlvli, gerenjianjie FROM AJ_RMTZ WHERE RY=? ORDER BY ID DESC";
		@SuppressWarnings("unchecked")
		List<Personnel> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Personnel.class)).setParameter(0, name).list();
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Hzlog> flowRoll(String time, String flowid) {
		String sql = "SELECT WORKID,ACTIONTIME,USERNAME FROM tw_hz_log WHERE ACTIONTIME>? AND FLOWID=?";
		@SuppressWarnings("unchecked")
		List<Hzlog> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Hzlog.class)).setParameter(0, time).setParameter(1, flowid).list();
		return list;
	}

	@Override
	public List<String> queryNode(String flowid, String workid) {
		String sql = "SELECT NODEID FROM tw_hz_log WHERE (WORKID,ID) IN (SELECT WORKID,MAX(ID) FROM tw_hz_log GROUP BY WORKID) AND FLOWID=? AND WORKID=?";
		@SuppressWarnings("unchecked")
		List<String> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, flowid).setParameter(1, workid).list();
		return list;
	}

	///个人履历
	@Override
	public List<Record> queryGongzuolvli(String id) {
		String sql = "SELECT gongzuolvli,starttime,stoptime FROM GONGZUOLVLI WHERE PARENTID=?";
		@SuppressWarnings("unchecked")
		List<Record> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Record.class)).setParameter(0, id).list();
		return list;
	}

	//查询被退回次数
	@Override
	public String querySendback(String name) {
		String sql = "SELECT COUNT(*) FROM (SELECT WORKID ,NODEID,USERID FROM tw_hz_log WHERE USERNAME=? AND ACTION='submit' GROUP BY WORKID ,NODEID,USERID HAVING COUNT(*)>1 ) AS A";
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, name).uniqueResult().toString();
		return count;
	}

	

	
	
}
