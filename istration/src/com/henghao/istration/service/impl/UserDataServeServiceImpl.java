package com.henghao.istration.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.henghao.istration.dao.IUserDataServeDao;
import com.henghao.istration.service.IUserDataServeService;
import com.henghao.istration.util.Result;

@Service("userDataServeService")
public class UserDataServeServiceImpl implements IUserDataServeService {

	@Resource
	private IUserDataServeDao userDataServeDao;
	private Map<String, Object> map = new HashMap<String, Object>();

	@Override
	public Result getUserPhone(String username) {
		Result result = null;
		try {
			String data = java.net.URLDecoder.decode(username, "UTF-8");  
			String phone = userDataServeDao.getUserPhone(data);
			result = new Result(0,"查询成功",phone);
			if("".equals(phone)) {
				result = new Result(2,"查询成功,没有电话信息",-1);
			}
			if(null == phone) {
				result = new Result(2,"查询成功,没有电话信息",-1);
			}
		} catch (Exception e) {
			result = new Result(1,"查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result getUserLead(String username) {
		Result result = null;
		//人名--->人id----->部门id---->to_horizon_dept_admin上级领导id---->上级领导电话
		//C是主管领导，F是部门负责人
		try {
			String data = java.net.URLDecoder.decode(username, "UTF-8"); 
			String cphone = userDataServeDao.getUserLead(data,"C");
			String fphone = userDataServeDao.getUserLead(data,"F");
			if(null != cphone && cphone.length() < 11) {
				cphone = null;
			}
			if(null != fphone && fphone.length() < 11) {
				fphone = null;
			}
			if(null != cphone && cphone.length() > 11) {
				cphone = cphone.substring(0, 11);
			}
			if(null != fphone && fphone.length() > 11) {
				fphone = fphone.substring(0, 11);
			}
			map.put("cphone", cphone);
			map.put("fphone", fphone);
			result = new Result(0,"查询成功",map);
		} catch (Exception e) {
			result = new Result(1,"查询失败",null);
			e.printStackTrace();
		}
		return result;
	} 
}
