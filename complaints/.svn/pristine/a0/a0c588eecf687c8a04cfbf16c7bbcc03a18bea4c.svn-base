package com.henghao.controller;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.entity.Complain;
import com.henghao.service.IComplainService;
import com.henghao.util.GsonUtil;
import com.henghao.util.Result;

/**
 * 投诉举报控制层
 * 
 * @author 周承耀
 * 
 */
@Controller
@RequestMapping("/complain")
public class ComplainController {

	@Autowired
	private IComplainService complainService;

	/**
	 * 提交投诉表单
	 * 
	 * @param json_complain
	 * @return
	 */
	@RequestMapping(value = "/add", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public Result add(String json_complain, HttpServletRequest request) {
		Complain complain = GsonUtil.parseJsonWithGson(json_complain, Complain.class);
		String pathRoot = request.getSession().getServletContext().getRealPath("/")+ "tousu";
		if(!new File(pathRoot).exists())   {
		    new File(pathRoot).mkdirs();
		  }
		Result result = complainService.loadAdd(complain,pathRoot);
		return result;
	}

	/**
	 * 查询所有未结案的投诉表单
	 * 
	 * @param pageBaen
	 * @return
	 */
	@RequestMapping(value = "/findAll", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result findAll(String currentPage, String pageSize) {
		Result result = complainService.findAll(currentPage, pageSize);
		return result;
	}

	/**
	 * 根据id查询申请单详细
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findById", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result findById(String id) {
		Result result = complainService.findById(id);
		return result;
	}

	/**
	 * 同意立案
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/consent", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result consent(String id) {
		Result result = complainService.consent(id);
		return result;
	}

	/**
	 * 拒绝立案
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/repulse", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result repulse(String id, String desc) {
		Result result = complainService.repulse(id, desc);
		return result;
	}

	/**
	 * 结案，同意或拒绝的都需要结案操作
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/finish", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result finish(String id) {
		Result result = complainService.finish(id);
		return result;
	}
	/**
	 * 结案，同意或拒绝的都需要结案操作
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/tofinish", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result tofinish(String id, String feedback) {
		Result result = complainService.tofinish(id,feedback);
		return result;
	}

	/**
	 * 查询已结案的投诉
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryFinish", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result queryFinish(String currentPage, String pageSize) {
		Result result = complainService.queryFinish(currentPage, pageSize);
		return result;
	}

	/**
	 * 搜索
	 * @param classs 搜索的类别
	 * @param condition 搜索的条件
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/seek", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result querySeek(String classs, String types, String condition, String currentPage, String pageSize) {
		Result result = complainService.querySeek(classs,types,condition, currentPage, pageSize);
		return result;
	}


	/**
	 * 举报查询
	 */
	@RequestMapping(value = "/query", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result comquery(String name, String code) {
		Result result = complainService.comquery(name,code);
		return result;
	}

	/**
	 * 举报评价
	 */
	@RequestMapping(value = "/evaluate", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result evaluate(String id, String satisfaction, String evaluate) {
		Result result = complainService.evaluate(id, satisfaction, evaluate);
		return result;
	}

	/**
	 * 报表管理
	 */
	
	@RequestMapping(value = "/statement", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result statement() {
		Result result = complainService.statement();
		return result;
	}
	/**
	 * 评分模块调用评分接口
	 * 2017-12-05
	 * @param userName
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping(value = "/queryUserGrade", method=RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Result queryUserGrade(String userName, String start, String end) {
		try {
			userName = java.net.URLDecoder.decode(userName, "UTF-8");
			Map<String, Object> map = complainService.queryUserGrade(userName, start, end);
			return new Result(0, "查询成功", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	} 
	
}
