package com.henghao.news.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.henghao.news.entity.JianchaMaterialEntity;
import com.henghao.news.entity.Plan;
@Repository
public class ZfAppDao extends BaseDaoImpl<Plan> {

	@SuppressWarnings("unchecked")
	public List<Integer> queryUserPlanIds(String userName, int resultStatus) {
		List<Integer> list ;
		if(-1 == resultStatus) {
			//查询历史记录
			String sql = "SELECT pid FROM aj_azhifaplan WHERE (checkPeople1=? OR checkPeople2=?) AND resultStatus <> 0  ORDER BY pid DESC";
			list = super.getSession().createSQLQuery(sql).setParameter(0, userName).setParameter(1, userName).list();
		}else{
			String sql = "SELECT pid FROM aj_azhifaplan WHERE (checkPeople1=? OR checkPeople2=?) AND resultStatus=?  ORDER BY pid DESC";
			list = super.getSession().createSQLQuery(sql).setParameter(0, userName).setParameter(1, userName).setParameter(2, resultStatus).list();
		}
		return list;
	}

	public void deleteCheckContent(int cid) {
		String sql = "DELETE FROM aj_aexamineitem WHERE cid=?";
		super.getSession().createSQLQuery(sql).setParameter(0, cid).executeUpdate();
		
	}
	//根据计划Pid查询有隐患的检查项
	@SuppressWarnings("unchecked")
	public List<JianchaMaterialEntity> queryJianchaByStatus(int pid) {
		String hql = "FROM JianchaMaterialEntity WHERE pid=? AND status=1";
		return super.getSession().createQuery(hql).setParameter(0, pid).list();
	}

}
