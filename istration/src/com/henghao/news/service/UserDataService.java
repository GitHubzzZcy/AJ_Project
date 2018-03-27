package com.henghao.news.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.henghao.news.dao.UserDataDao;


@Service
public class UserDataService {
	@Resource
	private UserDataDao userDataDao;

	public String getUserId(String username){
		return userDataDao.getUserId(username);
	}
	public String getUserName(String id){
		return userDataDao.getUserName(id);
	}
	
	public String getUserDept(String username) {
		return userDataDao.getUserDept(username);
	}
	//查询节假日和周末
	public List<String> getHolidayAndWeekend() {
		return userDataDao.getHolidayAndWeekend();
	}
	public List<String> getUserIdAll() {
		return userDataDao.getUserIdAll();
	}
	public List<String> getDeptNameUserIdAll(String deptName) {
		return userDataDao.getDeptNameUserIdAll(deptName);
	}
}
