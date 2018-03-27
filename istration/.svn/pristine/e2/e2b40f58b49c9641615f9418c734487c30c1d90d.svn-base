package com.henghao.news.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.news.service.IndexService;
import com.henghao.news.util.Result;

/**
 * 首页
 * @author 周
 *
 */
@Controller
@RequestMapping("/index")
public class IndexController {

	@Autowired
	private IndexService indexService;
	
	/**
	 * 本月审批情况
	 */
	@RequestMapping(value = "/queryapprovemonth", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryApproveMonth(){
		try {
			Map<String, Object> map = indexService.queryApproveMonth();
			return new Result(0,"查询成功", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1,"查询失败", null);
	}
	/**
	 * 督责问责菜单下的政策法规
	 */
	@RequestMapping(value = "/queryLawsAndRegulations", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryLawsAndRegulations(@RequestParam(required = false)String type, @RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "10")int size){
		try {
			return new Result(0,"查询成功", indexService.queryLawsAndRegulations(type, page, size));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1,"查询失败", null);
	}
	/**
	 * 督责问责菜单下的政策法规模糊查询
	 */
	@RequestMapping(value = "/queryLawsAndRegulationSvague", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryLawsAndRegulationSvague(@RequestParam(required = false)String svague, @RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "10")int size){
		try {
			return new Result(0,"查询成功", indexService.queryLawsAndRegulationSvague(svague, page, size));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1,"查询失败", null);
	}
	/**
	 * 首页小喇叭
	 */
	@RequestMapping(value = "/queryTrumpetNews", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryTrumpetNews(String userId, @RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "10")int size){
		try {
			return new Result(0,"查询成功", indexService.queryTrumpetNews(userId, page, size));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1,"查询失败", null);
	}
}
