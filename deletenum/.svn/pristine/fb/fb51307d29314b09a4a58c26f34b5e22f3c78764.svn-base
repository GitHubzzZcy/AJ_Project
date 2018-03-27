package com.henghao.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public Result scoreStatistics(String name) throws Exception{
		return integrityManagementService.scoreStatistics(name);
	}
	/**
	 * 个人综合评分
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/comprehensiveScore")
	public Result comprehensiveScore(String name) throws Exception {
		return integrityManagementService.comprehensiveScore(name);
	}
	/**
	 * 加分条数及详情
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/plusCountAndDetails")
	@ResponseBody
	public Result plusCountAndDetails(String name)throws Exception{
		return integrityManagementService.plusCountAndDetails(name);
	}
	/**
	 * 减分条数及详情
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/reductionCountAndDetails")
	public Result reductionCountAndDetails(String name)throws Exception{
		return integrityManagementService.reductionCountAndDetails(name);
	}
	/**
	 * 季度分数比较
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/quarterScore")
	public Result quarterScore(String name)throws Exception{
		return integrityManagementService.quarterScore(name);
	}
	/**
	 * 录入选项
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/option")
	public Result option() throws Exception{
		return integrityManagementService.option();
	}
}
