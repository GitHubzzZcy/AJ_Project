package com.henghao.istration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.istration.service.ICarapplyService;
import com.henghao.istration.util.Result;
/**
 * 申请用车数据查询信息控制层
 * @author 周承耀
 *
 */
@Controller
@RequestMapping("/carapply")
public class CarapplyController {

	@Autowired
	private ICarapplyService carapplyService;
	
	
	/**
	 * 查询aj_car_apply表得到申请单的详情信息
	 * 拿到aj_car_apply的id去查询tw_hz_instance表拿到workid
	 * 拿到workid去查询tw_hz_log表获取流程进度信息
	 * 并查询车辆当天的运行实时数据
	 */
	@ResponseBody
	@RequestMapping(value="/findApply", produces="application/json; charset=UTF-8 ")
	public Result findApply(String carNumber) {
		Result result = carapplyService.findApply(carNumber);
		return result;
	}
	

}
