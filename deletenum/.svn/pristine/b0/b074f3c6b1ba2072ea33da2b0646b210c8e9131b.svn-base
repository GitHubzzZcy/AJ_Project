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
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/personnelInformation")
	public Result personnelInformation(String name) throws Exception{
		return efficiencyImprovementService.personnelInformation(name);
	}
	/**
	 * 局领导查询
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/bureauLeader")
	public Result bureauLeader()throws Exception{
		return efficiencyImprovementService.bureauLeader();
	}
	/**
	 * 得分统计详情
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/scoreStatistics")
	@ResponseBody
	public Result scoreStatistics(String name) throws Exception{
		return efficiencyImprovementService.scoreStatistics(name);
	}
	/**
	 * 个人综合评分
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/comprehensiveScore")
	public Result comprehensiveScore(String name) throws Exception {
		return efficiencyImprovementService.comprehensiveScore(name);
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
		return efficiencyImprovementService.plusCountAndDetails(name);
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
		return efficiencyImprovementService.reductionCountAndDetails(name);
	}
	/**
	 * 车辆申请情况统计
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/vehicleStatistics")
	public Result vehicleStatistics(String name)throws Exception{
		return efficiencyImprovementService.vehicleStatistics(name);
	}
	/**
	 * 获取事件的信息及图片------> “接口已注销，现在用UserEventTrackController类的查询”
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/eventInformationAndImg")
	public Result eventInformationAndImg(String userId,String date)throws Exception{
		return efficiencyImprovementService.eventInformationAndImg(userId,date);
	}
	/**
	 * 领导评分
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/leadershipScore")
	public Result leadershipScore(String userId) throws Exception{
		return efficiencyImprovementService.leadershipScore(userId);
	}
	/**
	 * 会议派车查询
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/sendCarQuery")
	public Result sendCarQuery() throws Exception {
		return efficiencyImprovementService.sendCarQuery();
	}
}
