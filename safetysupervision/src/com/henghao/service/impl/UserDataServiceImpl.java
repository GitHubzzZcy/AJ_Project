package com.henghao.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.henghao.dao.IUserDataDao;
import com.henghao.service.IUserDataService;

@Service("userDataService")
public class UserDataServiceImpl implements IUserDataService{
	
	@Resource
	private IUserDataDao userDataDao;

	@Override
	public String getUserName(String userId) {
		return userDataDao.getUserName(userId);
	}

	@Override
	public String getUserId(String userName) {
		return userDataDao.getUserId(userName);
	}

	@Override
	public String getUserDeptName(String userDeptId) {
		return userDataDao.getUserDeptName(userDeptId);
	}

}
