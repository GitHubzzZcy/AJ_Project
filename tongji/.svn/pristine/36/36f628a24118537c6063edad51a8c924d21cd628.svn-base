package com.henghao.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.entity.Enterprise;
import com.henghao.entity.Result;
import com.henghao.entity.Troop;
import com.henghao.service.IFirmdataService;
import com.henghao.util.GsonUtil;

@Controller
@RequestMapping("/firmdate")
public class FirmdataController {
	
	@Autowired
	private IFirmdataService firmdataService;

	/**
	 * 查询所有企业信息
	 * @param page
	 * @param size
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/query", method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public Result queryFirmdata(int page, int size) {
		try {
			return new Result(0,"查询成功", firmdataService.queryFirmdata(page, size));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	/**
	 * 搜索查询企业
	 * @param page
	 * @param size
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryseek", method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public Result queryFirmdataSeek(String firmname, @RequestParam(required=false,defaultValue="1")int page, @RequestParam(required=false,defaultValue="10")int size) {
		try {
			return new Result(0,"查询成功", firmdataService.queryFirmdataSeek(firmname, page, size));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
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
	 * 根据企业id查询企业信息
	 * @param enterpriseid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/findFirmById", method=RequestMethod.GET)
	public Result findFirmById(String enterpriseid) {
		try {
			Enterprise enter = firmdataService.findFirmById(enterpriseid);
			return new Result(0,"查询成功", enter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
}
