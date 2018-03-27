package com.henghao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.henghao.dao.ApprovalDao;
import com.henghao.entity.Personle;

@Repository
public class ApprovalDaoImpl extends BaseDao<Personle> implements ApprovalDao {
	/**
	 * 查询个人审批案件
	 */
	@Override
	public List<?> findMyApproval(String userId) {
		String sql = "SELECT * FROM ("
				+ " SELECT o.ID as id,o.FIRMNAME as enterprise,w.FLOWNAME,w.SENDTIME,w.WORKID,w.TRACKID,w.STATUS_NO "
				+ "from aj_sc_one o,twr_hz_instance h ,tw_hz_worklist w "
				+ "WHERE o.ID = h.DATAID AND h.WORKID=w.WORKID AND (AUTH_ID=? OR AGENT_ID=?) " + "UNION ALL "
				+ "SELECT t.ID as id,t.DWMC as enterprise,w.FLOWNAME ,w.SENDTIME,w.WORKID,w.TRACKID,w.STATUS_NO "
				+ "from aj_sc_two t,twr_hz_instance h,tw_hz_worklist w "
				+ "WHERE t.ID = h.DATAID AND h.WORKID = w.WORKID " 
				+ "AND (AUTH_ID=? OR AGENT_ID=?) " 
				+ "UNION ALL "
				+ "SELECT q.ID as id,q.SQDW as enterprise,w.FLOWNAME,w.SENDTIME,w.WORKID,w.TRACKID,w.STATUS_NO "
				+ "FROM aj_qxnfmksaqscxkdjbf q,twr_hz_instance h,tw_hz_worklist w "
				+ "WHERE q.ID=h.DATAID and h.WORKID=w.WORKID AND (AUTH_ID=? OR AGENT_ID=?) " + "UNION ALL "
				+ "SELECT q.ID as id,q.QY as enterprise,w.FLOWNAME,w.SENDTIME,w.WORKID,w.TRACKID,w.STATUS_NO "
				+ "FROM aj_qxnfmksaqsssjsc q,twr_hz_instance h,tw_hz_worklist w "
				+ "WHERE q.ID=h.DATAID AND h.WORKID=w.WORKID AND (AUTH_ID=? OR AGENT_ID=?) " + "UNION ALL "
				+ "SELECT q.ID as id,q.QY as enterprise,w.FLOWNAME,w.SENDTIME,w.WORKID,w.TRACKID,w.STATUS_NO "
				+ "FROM aj_qxnfmksaqsssjscaqff q,twr_hz_instance h,tw_hz_worklist w "
				+ "WHERE q.ID=h.DATAID AND h.WORKID=w.WORKID AND (AUTH_ID=? OR AGENT_ID=? ) " + ")as A";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, userId).setParameter(1, userId)
				.setParameter(2, userId).setParameter(3, userId).setParameter(4, userId).setParameter(5, userId)
				.setParameter(6, userId).setParameter(7, userId).setParameter(8, userId).setParameter(9, userId).list();
		return list;
	}

	/**
	 * 查询人员部门 名称
	 */
	@Override
	public List<?> findpersonnelInfo(String userId) {
		String sql = "select u.NAME,DEPT_NAME from to_horizon_dept d,to_horizon_user u,tor_horizon_user_dept ud "
				+ "where u.ID = ud.USER_ID AND ud.DEPT_ID = d.ID AND u.ID=? ";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, userId).list();
		return list;
	}

	/**
	 * 查询局领导人员
	 */
	@Override
	public List<?> findLeaderPersonnelInfo() {
		String sql = "select u.ID,u.NAME,DEPT_NAME from to_horizon_dept d,to_horizon_user u,tor_horizon_user_dept ud "
				+ "where u.ID = ud.USER_ID AND ud.DEPT_ID = d.ID AND DEPT_NAME='局领导' ";
		List<?> list = super.getSession().createSQLQuery(sql).list();
		return list;
	}

	/**
	 * 查询除局领导人员
	 */
	@Override
	public List<?> findDeptPersonnelInfonation() {
		String sql = "select u.ID,u.NAME,DEPT_NAME from to_horizon_dept d,to_horizon_user u,tor_horizon_user_dept ud "
				+ "where u.ID = ud.USER_ID AND ud.DEPT_ID = d.ID AND DEPT_NAME <> '局领导' AND DEPT_NAME <> '管理员' ";
		List<?> list = super.getSession().createSQLQuery(sql).list();
		return list;
	}

	@Override
	public List<?> findMyApprovalNum(String userId) {
		String sql = "SELECT COUNT(*) FROM ("
				+ " SELECT o.ID as id,o.FIRMNAME as enterprise,w.FLOWNAME,w.SENDTIME,w.WORKID,w.TRACKID,w.STATUS_NO "
				+ "from aj_sc_one o,twr_hz_instance h ,tw_hz_worklist w "
				+ "WHERE o.ID = h.DATAID AND h.WORKID=w.WORKID AND (AUTH_ID=? OR AGENT_ID=?) " + "UNION ALL "
				+ "SELECT t.ID as id,t.DWMC as enterprise,w.FLOWNAME ,w.SENDTIME,w.WORKID,w.TRACKID,w.STATUS_NO "
				+ "from aj_sc_two t,twr_hz_instance h,tw_hz_worklist w "
				+ "WHERE t.ID = h.DATAID AND h.WORKID = w.WORKID " 
				+ "AND (AUTH_ID=? OR AGENT_ID=?) " 
				+ "UNION ALL "
				+ "SELECT q.ID as id,q.SQDW as enterprise,w.FLOWNAME,w.SENDTIME,w.WORKID,w.TRACKID,w.STATUS_NO "
				+ "FROM aj_qxnfmksaqscxkdjbf q,twr_hz_instance h,tw_hz_worklist w "
				+ "WHERE q.ID=h.DATAID and h.WORKID=w.WORKID AND (AUTH_ID=? OR AGENT_ID=?) " + "UNION ALL "
				+ "SELECT q.ID as id,q.QY as enterprise,w.FLOWNAME,w.SENDTIME,w.WORKID,w.TRACKID,w.STATUS_NO "
				+ "FROM aj_qxnfmksaqsssjsc q,twr_hz_instance h,tw_hz_worklist w "
				+ "WHERE q.ID=h.DATAID AND h.WORKID=w.WORKID AND (AUTH_ID=? OR AGENT_ID=?) " + "UNION ALL "
				+ "SELECT q.ID as id,q.QY as enterprise,w.FLOWNAME,w.SENDTIME,w.WORKID,w.TRACKID,w.STATUS_NO "
				+ "FROM aj_qxnfmksaqsssjscaqff q,twr_hz_instance h,tw_hz_worklist w "
				+ "WHERE q.ID=h.DATAID AND h.WORKID=w.WORKID AND (AUTH_ID=? OR AGENT_ID=? ) " + ")as A "
				+ "WHERE STATUS_NO <> 201";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, userId).setParameter(1, userId)
				.setParameter(2, userId).setParameter(3, userId).setParameter(4, userId).setParameter(5, userId)
				.setParameter(6, userId).setParameter(7, userId).setParameter(8, userId).setParameter(9, userId).list();
		return list;
	}

	/**
	 *时间段查询三重一大信息
	 */
	@Override
	public List<?> findMajor(String date1, String date2,String type) {
		String sql="SELECT m.ID,m.Title,m.MTTYPE,m.MTINITIATOR,m.FGLDSH,m.ZYLDSH,m.OPINION,m.HYDD,m.MTDESC,m.HYFGLDSHYJ,m.HYZYLDSHYJ "
				+ "FROM aj_great_meeting m,twr_hz_instance i,tw_hz_log l "
				+ "WHERE m.ID =i.DATAID AND i.WORKID = l.WORKID "
				+ "AND l.NODEID ='End1' AND m.SFTG='通过' "
				+ "AND SXJSRQ >=? AND SXJSRQ <=? and m.MTTYPE =? ";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, date1).setParameter(1, date2).setParameter(2, type).list();
		return list;
	}

	/**
	 * ID查询三种一大文件
	 */
	@Override
	public List<?> findMajorImg(String id,String name) {
		String sql="SELECT * FROM ta_horizon_info "
				+ "WHERE OBJECT_ID=? AND FIELD_NAME = ?";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, id).setParameter(1, name).list();
		return list;
	}

}
