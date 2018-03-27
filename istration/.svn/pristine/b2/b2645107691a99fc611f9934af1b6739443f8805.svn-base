package com.henghao.istration.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.istration.service.ILoginService;
import com.henghao.istration.util.Result;
/**
 * 融合平台登录
 * @author 周承耀
 *
 */
@Controller
@RequestMapping("/user")
public class LoginController {

	@Autowired
	private ILoginService loginService;
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login", method=RequestMethod.POST, produces="application/json; charset=UTF-8")
	@ResponseBody
	public Result login(String username, String password, HttpServletRequest request,
            HttpServletResponse response) {
		Result result = loginService.login(username, password,request,response);
		return result;
	}
	/**
	 * 对外接口，查询所有人的头像名
	 * @return
	 */
	@RequestMapping(value = "/findAll", method=RequestMethod.GET, produces="application/json; charset=UTF-8")
	@ResponseBody
	public Result findAll() {
		Result result = loginService.findAll();
		return result;
	}
	/**
	 * 根据人员id查询人员头像
	 * @return
	 */
	@RequestMapping(value = "/queryHeadPath", method=RequestMethod.GET, produces="application/json; charset=UTF-8")
	@ResponseBody
	public Result queryHeadPath(String userid) {
		try {
			String pathName = loginService.queryHeadPath(userid);
			return new Result(0,"查询成功",pathName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1,"查询失败",null);
	}
	
}
