package com.henghao.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.henghao.entity.Result;
import com.henghao.service.IntegrityManagementService;
/**
 * 诚信管理 得分统计分析
 * @author 龙宏
 *
 */
@Controller
@RequestMapping("/integrity")
@SuppressWarnings("all")
public class IntegrityManagementAction {
	@Autowired
	private IntegrityManagementService integrityManagementService;
	
	/**
	 * 得分统计详情
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/scoreStatistics")
	@ResponseBody
	public Result scoreStatistics(String deptName,String name) throws Exception{
		return integrityManagementService.scoreStatistics(deptName, name);
	}
	/**
	 * 人员信息
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/comprehensiveScore")
	public Result comprehensiveScore(String deptName, String name) throws Exception {
		return integrityManagementService.personnelInformation(deptName,name);
	}
	/**
	 * 车辆座位类型
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/carNumType")
	@ResponseBody
	public Result carNumType() throws Exception{
		return integrityManagementService.carNumType();
	}
	/**
	 * 按日期查询用车次数
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/useCarType")
	@ResponseBody
	public Result useCarType(String date1, String date2) throws Exception{
		return integrityManagementService.useCarType(date1, date2);
	}
	/**
	 * 车辆基本信息查询
	 * @param carNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/carInfo")
	@ResponseBody
	public Result carInfo(String carNo) throws Exception{
		return integrityManagementService.carInfo(carNo);
	}
	/**
	 * 车辆保养信息
	 * @param carNo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/maintainInfo")
	public Result maintainInfo(String carNo) throws Exception{
		return integrityManagementService.maintainInfo(carNo);
	}
	/**
	 * 所有车辆信息
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/carInfomation")
	public Result carInfomation() throws  Exception{
		return integrityManagementService.carInfomation();
	}
	/**
	 * 车辆状态
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/carsState")
	public Result carsState(String date1, String date2)throws Exception{
		return integrityManagementService.carsState(date1, date2);
	}
	/**
	 * 用车信息
	 * @param carNo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/carUseInfo")
	public Result carUseInfo(String carNo)throws Exception{
		return integrityManagementService.carUseInfo(carNo);
	}
	/**
	 * 按年月周时间段查询非工作日用车次数
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/weekendUseCar")
	@ResponseBody
	public Result weekendUseCar(String date1,String date2)throws Exception{
		return integrityManagementService.weekendUseCar(date1, date2);
	}

	/**
	 * 按年月周时间段查询节假日用车次数
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findVacationsUseCar")
	@ResponseBody
	public Result findVacationsUseCar(String date1, String date2)throws Exception{
		return integrityManagementService.findVacationsUseCar(date1, date2);
	}
	/**
	 * 按年月周时间段查询驾驶员与车不符次数
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/driverByCarNo")
	@ResponseBody
	public Result driverByCarNo(String date1, String date2)throws Exception{
		return integrityManagementService.driverByCarNo(date1, date2);
	}
	/**
	 * 按年月周时间段查询用车超时次数
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/useCarByOvertime")
	@ResponseBody
	public Result useCarByOvertime(String date1, String date2)throws Exception{
		return integrityManagementService.useCarByOvertime(date1, date2);
	}
	/**
	 * 保存车辆偏离信息
	 * @return
	 * @throws Exception
	 */
	
	/**
	 * 按时间段 年月周查询用车偏离 目的地
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addressInconsistent")
	@ResponseBody
	public Result addressInconsistent(String date1, String date2)throws Exception{
		Result result = integrityManagementService.addressInconsistent(date1, date2);
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) result.getData();
		if(map != null) {
			Integer object = (Integer) map.get("count");
			return new Result(result.getStatus(), result.getMsg(), object);
		}
		return result;
		
	}
	/**
	 * 按时间段年月周查询车回人不回
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/carReturn")
	@ResponseBody
	public Result carReturnAndPersonle(String date1, String date2)throws Exception{
		return integrityManagementService.carReturnAndPersonle(date1, date2);
	}
	/**
	 * 按时间段年月周查询人车不符
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/menAndCarNot")
	@ResponseBody
	public Result menAndCarNot(String date1, String date2)throws Exception{
		return integrityManagementService.menAndCarNot(date1, date2);
	}
	/**
	 * 保存车回人不回信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/carReturnAndPerson")
	@ResponseBody
	public Result addCarReturnAndPersonle()throws Exception{
		return integrityManagementService.addCarReturnAndPersonle();
	}
	/**
	 * 保存人车不符信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addMenAndCarNot")
	@ResponseBody
	public Result addMenAndCarNot()throws Exception{
		return integrityManagementService.addMenAndCarNot();
	}
	/**
	 * zcy
	 * 1月3号
	 * 车辆管理菜单下，用车次数详情查看
	 * 
	 */
	@RequestMapping(value = "/queryUseCarDetails", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryUseCarDetails(String start, String end, 
									@RequestParam(defaultValue = "1")int page, 
									@RequestParam(defaultValue = "10")int size) {
		try {
			Map<String, Object> map = integrityManagementService.queryUseCarDetails(start, end, page, size);
			return new Result(0, "查询成功", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
}
