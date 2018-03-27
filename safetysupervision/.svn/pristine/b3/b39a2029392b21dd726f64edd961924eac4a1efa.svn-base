package com.henghao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.henghao.dao.PersonNotCarDao;
import com.henghao.entity.PersonNotCar;
@Repository
public class PersonNotCarDaoImpl extends BaseDao<PersonNotCar> implements PersonNotCarDao {

	/**
	 * 查询人车不符信息
	 */
	@Override
	public List<?> personNotCarInfo(String name, String time) {
		String sql="SELECT * FROM person_not_car WHERE `name`=? AND time=?";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, name).setParameter(1, time).list();
		return list;
	}

}
