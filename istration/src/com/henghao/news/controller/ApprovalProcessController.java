package com.henghao.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.news.service.ApprovalProcessService;
import com.henghao.news.util.Result;
/**
 * 审批主菜单数据控制层
 *@author: zcy
 *@date： 2017-9-25 下午4:12:43
 *@version 1.0
 */
@Controller
@RequestMapping("/approvalProcess")
public class ApprovalProcessController {

	@Autowired
	private ApprovalProcessService approvalProcessService;
	
	/**
	 * 审批中、已审批案件统计
	 * @return
	 */
	@RequestMapping(value = "/queryAnjianTongji", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryAnjianTongji(@RequestParam(required=false) String start, @RequestParam(required=false) String end) {
		try {
			Object obj = approvalProcessService.queryAnjianTongji(start, end);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	/**
	 * 资料不全、资料有误、重复录入
	 * @return
	 */
	@RequestMapping(value = "/queryAnjianTongjiZiliao", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryAnjianTongjiZiliao(@RequestParam(required=false) String start, @RequestParam(required=false) String end) {
		try {
			Object obj = approvalProcessService.queryAnjianTongjiZiliao(start, end);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	/**
	 * 逾期审核、逾期审批
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping(value = "/queryAnjianTongjiOverdueDispose", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryAnjianTongjiOverdueDispose(@RequestParam(required=false) String start, @RequestParam(required=false) String end) {
		try {
			Object obj = approvalProcessService.queryAnjianTongjiOverdueDispose(start, end);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	/**
	 * 逾期未审核、逾期未审批
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping(value = "/queryAnjianTongjiOverdueNotDispose", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryAnjianTongjiOverdueNotDispose(@RequestParam(required=false) String start, @RequestParam(required=false) String end) {
		try {
			Object obj = approvalProcessService.queryAnjianTongjiOverdueNotDispose(start, end);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	/**
	 * 违规审核、违规审批
	 * @param start
	 * @param end
	 * @return
	 */
	
	@RequestMapping(value = "/queryManageIllegal", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryManageIllegal(@RequestParam(required=false) String start, @RequestParam(required=false) String end) {
		try {
			Object obj = approvalProcessService.queryManageIllegal(start, end);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	/**
	 * 异常审批汇总
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping(value = "/queryAnjianTongjiAbnormal", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryAnjianTongjiAbnormal(@RequestParam(required=false) String start, @RequestParam(required=false) String end) {
		try {
			Object obj = approvalProcessService.queryAnjianTongjiAbnormal(start, end);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	/**
	 * 安全服务页面
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping(value = "/querySafety", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result querySafety(@RequestParam(required=false) String start, @RequestParam(required=false) String end) {
		try {
			Object obj = approvalProcessService.querySafety(start, end);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	/**
	 * 审核办理统计页面      # 11月24日为保证数据一致性，修改页面“逾期审核” “逾期未审核” 统计数据和详情数据与审批总页面处用同一接口
	 * @param start
	 * @param end
	 * @return 
	 */
	@RequestMapping(value = "/queryAuditManage", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryAuditManage(@RequestParam(required=false) String start, @RequestParam(required=false) String end) {
		try {
			Object obj = approvalProcessService.queryAuditManage(start, end);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	/**
	 * 审批办理统计页面    # 11月24日为保证数据一致性，修改页面“逾期审批” “逾期未审批” 统计数据和详情数据与审批总页面处用同一接口
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping(value = "/queryRatify", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryRatify(@RequestParam(required=false) String start, @RequestParam(required=false) String end) {
		try {
			Object obj = approvalProcessService.queryRatify(start, end);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	
	
	
	
	
}
