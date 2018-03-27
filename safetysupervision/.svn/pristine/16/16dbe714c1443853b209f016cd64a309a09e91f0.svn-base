package com.henghao.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.henghao.entity.Result;
import com.henghao.service.EfficiencyImprovementService;
/**
 * 效能提升统计分析
 * @author 龙宏
 *
 */
@Controller
@RequestMapping("/efficiency")
public class EfficiencyImprovementAction {
	@Autowired
	private EfficiencyImprovementService efficiencyImprovementService;
	/**
	 * 人员信息
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/personleSex")
	public Result personleSex() throws Exception{
		return efficiencyImprovementService.personleSex();
	}
	/**
	 *部门人数查询
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/deptPersonleNum")
	public Result deptPersonleNum()throws Exception{
		return efficiencyImprovementService.deptPersonleNum();
	}
	
	/**
	 * 按时间段查询出差 请假 休假人数
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/personleNum")
	public Result personleNum(String date1,String date2) throws Exception {
		return efficiencyImprovementService.personleNum(date1, date2);
	}
	
	/**
	 * 年龄阶段人数查询
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/ageAll")
	public Result ageAll(String name)throws Exception{
		return efficiencyImprovementService.ageAll();
	}
}
