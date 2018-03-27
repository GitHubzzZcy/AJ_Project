package com.henghao.news.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.henghao.news.entity.Dept;
import com.henghao.news.entity.PersonnelEntity;
import com.henghao.news.entity.Troop;
import com.henghao.news.entity.Troopemp;

@Repository
public class FirmdataDao {
	@Resource
	private SessionFactory sessionFactory;

	public List<Troop> queryTroop() {
		String sql = "SELECT ID,GROUP_NAME AS TROOPNAME FROM to_horizon_group WHERE PARENT_ID='HZ8bb0c95e7a8638015e846861b571dd'";
		@SuppressWarnings("unchecked")
		List<Troop> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Troop.class)).list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Troopemp> queryTroopemp(String id) {
		String sql ;
		if("".equals(id) || id == null) {
			sql = "SELECT b.ID, b.`NAME`, b.`PASSWORD`, b.TELEPHONE AS PHONE,b.EMP_NUM, " +
					"b.LOGIN_NAME AS LOGINID, c.GROUP_NAME AS TROOPNAME FROM " +
					"tor_horizon_user_group a LEFT OUTER JOIN to_horizon_user b " +
					"ON a.USER_ID=b.ID LEFT OUTER JOIN to_horizon_group c " +
					"ON a.WORKGROUP_ID=c.ID WHERE WORKGROUP_ID " +
					"IN (SELECT ID FROM to_horizon_group  WHERE PARENT_ID='HZ8bb0c95e7a8638015e846861b571dd')";
		List<Troopemp> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Troopemp.class)).list();
		return list;
		}
		sql = "SELECT B.ID, B.`NAME`, B.`PASSWORD`, B.TELEPHONE AS PHONE,B.EMP_NUM, " +
				"B.LOGIN_NAME AS LOGINID, C.GROUP_NAME AS TROOPNAME FROM " +
				"TOR_HORIZON_USER_GROUP A LEFT OUTER JOIN TO_HORIZON_USER B " +
				"ON A.USER_ID=B.ID LEFT OUTER JOIN TO_HORIZON_GROUP C " +
				"ON A.WORKGROUP_ID=C.ID WHERE WORKGROUP_ID=?";
		List<Troopemp> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, id).setResultTransformer(Transformers.aliasToBean(Troopemp.class)).list();
		return list;
	}
	public Troopemp queryTroopByUserName(String username) {
		String sql = "SELECT B.ID, B.`NAME`, B.`PASSWORD`, B.TELEPHONE AS PHONE,B.EMP_NUM, " +
				"B.LOGIN_NAME AS LOGINID, C.GROUP_NAME AS TROOPNAME FROM " +
				"TOR_HORIZON_USER_GROUP A LEFT OUTER JOIN TO_HORIZON_USER B " +
				"ON A.USER_ID=B.ID LEFT OUTER JOIN TO_HORIZON_GROUP C " +
				"ON A.WORKGROUP_ID=C.ID WHERE B.NAME=?";
		Troopemp result = (Troopemp) sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, username).setResultTransformer(Transformers.aliasToBean(Troopemp.class)).uniqueResult();
		return result;
	}
	public List<Dept> queryDeptAll() {
		String sql = "SELECT ID,DEPT_NAME FROM TO_HORIZON_DEPT";
		@SuppressWarnings("unchecked")
		List<Dept> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Dept.class)).list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<PersonnelEntity> queryDeptByIdUser(String id) {
		List<PersonnelEntity> list;
		if("".equals(id) || null == id) {
			String sql ="SELECT B.ID AS id,B.NAME AS name,LOGIN_NAME AS login_name FROM TOR_HORIZON_USER_DEPT A LEFT OUTER JOIN TO_HORIZON_USER B ON A.USER_ID=B.ID WHERE A.DEPT_ID <> 'HZ28e7f51e816d28011e816da7f1001f'";
			list =  sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(PersonnelEntity.class)).list();
		}else{
			String sql ="SELECT B.ID AS id,B.NAME AS name,LOGIN_NAME AS login_name FROM TOR_HORIZON_USER_DEPT A LEFT OUTER JOIN TO_HORIZON_USER B ON A.USER_ID=B.ID WHERE A.DEPT_ID=?";
			list =  sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, id).setResultTransformer(Transformers.aliasToBean(PersonnelEntity.class)).list();
		}
		return list;
	}
	public PersonnelEntity queryByIdUser(String userid) {
		String sql = "SELECT ID AS id,NAME AS name,LOGIN_NAME AS login_name FROM TO_HORIZON_USER WHERE ID=?";
		PersonnelEntity  user = (PersonnelEntity) sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, userid).setResultTransformer(Transformers.aliasToBean(PersonnelEntity.class)).uniqueResult();
		return user;
	}
	
	public PersonnelEntity queryByIdLoginName(String login_name) {
		String sql = "SELECT ID AS id,NAME AS name,LOGIN_NAME AS login_name FROM to_horizon_user WHERE LOGIN_NAME=?";
		PersonnelEntity  user = (PersonnelEntity) sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, login_name).setResultTransformer(Transformers.aliasToBean(PersonnelEntity.class)).uniqueResult();
		return user;
	}
	//根据人员id查询部门id
	public String queryDeptIdByUserId(String userId) {
		String sql = "SELECT DEPT_ID FROM tor_horizon_user_dept WHERE USER_ID=?";
		Object result = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, userId).uniqueResult();
		if(null == result) {
			return null;
		}
		return result.toString();
	}

}
