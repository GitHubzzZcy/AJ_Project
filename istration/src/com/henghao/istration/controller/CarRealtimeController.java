 package com.henghao.istration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.istration.service.ICarRealtimeService;
import com.henghao.istration.util.Result;
/**
 * 车辆实时信息控制层
 * @author 周承耀
 *
 */
@Controller
@RequestMapping("/carreal")
public class CarRealtimeController {

	@Autowired
	private ICarRealtimeService carRealtimeService;
	/**
	 * 获取车辆实时数据并存储
	 * @param carNumber
	 * @param longitude
	 * @param latitude
	 * @param carTime
	 * @return
	 */
	@RequestMapping(value="/add", produces="application/json; charset=UTF-8 ")
	@ResponseBody
	public Result add(String carNumber, String longitude, String latitude, String carTime) {
		Result result = carRealtimeService.add(carNumber, longitude, latitude, carTime);
		return result;
	}
	
	/**
	 * 查询所有车辆当前车辆实时信息及申请单信息
	 * @param carNumber
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryCarlist", produces="application/json; charset=UTF-8 ")
	public Result queryCarlist() {
		Result result = carRealtimeService.queryCarlist();
		return result;
	}
	
	/**
	 * 查询车辆实时数据
	 * @param carNumber
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryRealtime", produces="application/json; charset=UTF-8 ")
	public Result queryRealtime(String carNumber) {
		Result result = carRealtimeService.queryRealtime(carNumber);
		return result;
	}
	
	

	/**
	 * 车辆里程统计
	 * @param carNumber
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/mileage", produces="application/json; charset=UTF-8 ")
	public Result mileage() {
		Result result = carRealtimeService.mileage();
		return result;
	}
	
	
	/**
	 * 超时统计
	 */
	@ResponseBody
	@RequestMapping(value="/exceed", produces="application/json; charset=UTF-8 ")
	public Result exceed(int currentPage, int pageSize) {
		Result result = carRealtimeService.exceed(currentPage, pageSize);
		return result;
	}
	//http://api.map.baidu.com/geocoder/v2/?callback=renderOption&output=json&location=26.627047019466095,106.66074596701164&ak=Fun6r9EE1RPtZ8roRyy7tpM3dL0OQEEl
	
	/**
	 * 偏航统计
	 */
	@ResponseBody
	@RequestMapping(value="/sheer", produces="application/json; charset=UTF-8 ")
	public Result sheer(int currentPage, int pageSize) {
		Result result = carRealtimeService.sheer(currentPage, pageSize);
		return result;
	}
	/**
	 * 停车统计
	 */
	@ResponseBody
	@RequestMapping(value="/park", produces="application/json; charset=UTF-8 ")
	public Result park(int currentPage, int pageSize) {
		Result result = carRealtimeService.park(currentPage, pageSize);
		return result;
	}
	/**
	 * 跨区统计
	 */
	
	
	@ResponseBody
	@RequestMapping(value="/transregional", produces="application/json; charset=UTF-8 ")
	public Result transregional(int currentPage, int pageSize) {
		Result result = carRealtimeService.transregional(currentPage, pageSize);
		return result;
	}
}
