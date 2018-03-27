package com.henghao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.henghao.dao.PersonnelDao;
import com.henghao.entity.Personnel;
@Repository
public class PersonnelDaoImpl extends BaseDao<Personnel> implements PersonnelDao {

	@Override
	public List<?> searchPersonnel() {
		String sql = "select ID,NAME,ROLE from EL_TROOPEMP";
		List<?> list = getSession().createSQLQuery(sql).list();
		return list;
	}

}
