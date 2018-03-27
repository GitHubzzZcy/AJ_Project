package com.henghao.istration.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.henghao.istration.dao.ILoginDao;
import com.henghao.istration.entity.User;

@Repository("loginDao")
public class LoginDaoImpl implements ILoginDao {

	@Resource
	private SessionFactory sessionFactory;
	@Override
	public String query(String username) {
		String sqlpass = null;
		String sql = "SELECT PASSWORD FROM to_horizon_user WHERE LOGIN_NAME=:username";
		Object result = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter("username", username).uniqueResult();
		if(null != result) {
			sqlpass = result.toString();
		}
		return sqlpass;
	}
	@Override
	public User queryuser(String username) {
		String sql = "SELECT NAME FROM to_horizon_user WHERE LOGIN_NAME=:username";
		String sqls = "SELECT ID FROM to_horizon_user WHERE LOGIN_NAME=:username";
		Object name = sessionFactory.getCurrentSession().createSQLQuery(sql).
				setParameter("username", username).uniqueResult();
		Object id = sessionFactory.getCurrentSession().createSQLQuery(sqls).
				setParameter("username", username).uniqueResult();
		String deptid = null;
		if(id != null) {
			//查询部门id
			String sqldept = "SELECT DEPT_ID FROM tor_horizon_user_dept WHERE USER_ID=?";
			deptid = sessionFactory.getCurrentSession().createSQLQuery(sqldept).setParameter(0, id.toString()).uniqueResult().toString();
		}
		User user = new User();
		user.setDept(deptid);
		//
		if(null != name) {
			String sqlq = "SELECT COUNT(*) FROM aj_qxgl WHERE KYRY LIKE ? OR KYRY IS NULL OR LENGTH(REPLACE(KYRY, ' ', '') ) = 0";
			String uniqueResult = sessionFactory.getCurrentSession().createSQLQuery(sqlq).setParameter(0, "%"+name+"%").uniqueResult().toString();
			int count = Integer.parseInt(uniqueResult);
			if(count != 0) {
				//数据表字段更改，为不改变PHP对接数据的逻辑
				List<String> list = new ArrayList<String>();
				list.add("有平台权限");
				user.setJurisdiction(list);
			}
			user.setID(id.toString());
			user.setNAME(name.toString());
		}
		return user;
	}
	@Override
	public List<User> findAllUser() {
		String sql = "SELECT Y.NAME,Y.headPath,D.DEPT_NAME AS dept FROM (SELECT Z.*,C.DEPT_ID FROM (SELECT A.ID,A.`NAME`,A.ORDER_NO, CONCAT(B.SAVE_NAME,'.',B.EXTENTION) AS headPath FROM  to_horizon_user A  LEFT OUTER JOIN ta_horizon_info B  ON A.ID =B.OBJECT_ID ) AS Z LEFT JOIN tor_horizon_user_dept C ON Z.ID=C.USER_ID ) AS Y LEFT  OUTER JOIN to_horizon_dept D ON Y.DEPT_ID=D.ID ORDER BY Y.ORDER_NO";
		@SuppressWarnings("unchecked")
		List<User> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(User.class)).list();
		
		return list;
	}
	@Override
	public String queryHeadPath(String userid) {
		String sql = "SELECT CONCAT(SAVE_NAME,'.',EXTENTION) AS headPath FROM ta_horizon_info WHERE OBJECT_ID=? AND field_name='headImg' ORDER BY save_time DESC";
		Object result = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, userid).uniqueResult();
		if(result != null) {
			return result.toString();
		}
		return "head.jpg";
	}

}
