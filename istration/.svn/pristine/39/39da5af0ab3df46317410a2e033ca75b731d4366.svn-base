package com.henghao.istration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.istration.service.IUserDataServeService;
import com.henghao.istration.util.Result;

/**
 * 用户数据服务类
 * @author 周承耀
 *
 */
@Controller
@RequestMapping("/userData")
public class UserDataServeController {

	@Autowired
	private IUserDataServeService userDataServeService;
	
	/**
	 * 根据人名查询电话等信息
	 * @param username
	 * @return
	 */
	@RequestMapping(value="/getUserPhone", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result getUserPhone(String username) {
		
		Result result = userDataServeService.getUserPhone(username);
		return result;
	}
	/**
	 * 根据人名查询他的上级领导信息
	 * @param username
	 * @return
	 */
	@RequestMapping(value="/getUserLead", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result getUserLead(String username) {
		Result result = userDataServeService.getUserLead(username);
		return result;
	}
	
}
