package com.henghao.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.news.service.FirmdataService;
import com.henghao.news.util.Result;

@Controller
@RequestMapping("/firmdate")
public class FirmdataController {

	@Autowired
	private FirmdataService firmdataService;
	/**
	 * 查询执法队伍人员
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/querytroopemp", method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public Result queryTroopemp(@RequestParam(required=false)String id) {
		try {
			return new Result(0,"查询成功", firmdataService.queryTroopemp(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	/**
	 * 查询执法队伍
	 */
	@ResponseBody
	@RequestMapping(value = "/querytroop", method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public Result queryTroop() {
		try {
			return new Result(0,"查询成功", firmdataService.queryTroop());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	/**
	 * 根据人名查询信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryTroopByUserName", method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public Result queryTroopByUserName(String username) {
		try {
			return new Result(0,"查询成功", firmdataService.queryTroopByUserName(username));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	//查询所有部门####11月23日改GET
	@ResponseBody
	@RequestMapping(value = "/queryDeptAll", method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public Result queryDeptAll() {
		try {
			return new Result(0,"查询成功", firmdataService.queryDeptAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	/**
	 * 根据部门id查询部门下所有人
	 */
	@ResponseBody
	@RequestMapping(value = "/queryDeptByIdUser", method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public Result queryDeptByIdUser(@RequestParam(required=false) String id) {
		try {
			return new Result(0,"查询成功", firmdataService.queryDeptByIdUser(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	
}
