package com.henghao.news.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.henghao.news.dao.FirmdataDao;
import com.henghao.news.entity.Dept;
import com.henghao.news.entity.PersonnelEntity;
import com.henghao.news.entity.Troop;
import com.henghao.news.entity.Troopemp;
import com.horizon.util.encrypt.DESEDE;

@Service
public class FirmdataService {

	@Resource
	private FirmdataDao firmdateDao;
	public Object queryTroopemp(String id) {
		List<Troopemp> list = firmdateDao.queryTroopemp(id);
		for (Troopemp troopemp : list) {
			//慧正解密
			String decryptIt = DESEDE.decryptIt(troopemp.getPASSWORD());
			troopemp.setPASSWORD(decryptIt);
		}
		return list;
	}
	
	public Object queryTroop() {
		List<Troop> list = firmdateDao.queryTroop();
		return list;
	}

	public Troopemp queryTroopByUserName(String username) {
		Troopemp troopemp = firmdateDao.queryTroopByUserName(username);
		String decryptIt = DESEDE.decryptIt(troopemp.getPASSWORD());
		troopemp.setPASSWORD(decryptIt);
		return troopemp;
	}

	public Object queryDeptAll() {
		List<Dept> list = firmdateDao.queryDeptAll();
		List<Dept> relist = new ArrayList<Dept>();
		for (Dept dept : list) {
			if(!("管理员".equals(dept.getDEPT_NAME()) || "部门导航".equals(dept.getDEPT_NAME()))) {
				relist.add(dept);
			}
		}
		return relist;
	}

	public Object queryDeptByIdUser(String id) {
		List<PersonnelEntity> list = firmdateDao.queryDeptByIdUser(id);
		return list;
	}
	
	public PersonnelEntity queryByIdUser(String userid) {
		PersonnelEntity  user = firmdateDao.queryByIdUser(userid);
		return user;
	}

	//根据推送别名即登录名查询用户信息
	public PersonnelEntity queryByIdLoginName(String login_name) {
		PersonnelEntity  user = firmdateDao.queryByIdLoginName(login_name);
		return user;
	}
	//会议管理平台统计，根据人员id查询人员部门id
	public String queryDeptIdByUserId(String userId) {
		return firmdateDao.queryDeptIdByUserId(userId);
	}
	
}
