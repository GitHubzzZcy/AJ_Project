package com.henghao.dao;

import java.util.List;
import java.util.Map;

import com.henghao.entity.Complain;


public interface IComplainDao extends IBaseDao<Complain> {


	public Map<String, Object> queryNotFinish(int currentPage, int pageSize);

	public Map<String, Object> querySeek(String classs, String types, String condition, int currentPage, int pageSize);

	public List<Complain> findByPhone(String name, String phone);

	public String regionquery(String items, String region, String currentDate);

	public String monthquery(String ym);

	public String yearquery(String items, String year);

	public List<Complain> queryUserGrade(String userName, String start, String end);

	


	

}
