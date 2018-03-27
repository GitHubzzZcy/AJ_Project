package com.henghao.news.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.news.service.DetailsService;
import com.henghao.news.util.Result;

/**
 * @Description:审批页面数据对应详细数据获取
 * @author zcy  
 * @date 2017年10月16日 下午上午9:53:07
 * @version 1.0
 */
@Controller
@RequestMapping("/details")
public class DetailsController {

	@Autowired
	private DetailsService detailsService;
	/**
	 * 资料不全、资料有误、重复录入详情
	 * @param itemType
	 * @param page
	 * @param size
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping(value = "/queryDataIncomplete", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryDataIncomplete(String itemType,  
					@RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "10")int size,
					@RequestParam(required=false) String start, @RequestParam(required=false) String end) {
		try {
			Object obj = detailsService.queryDataIncomplete(itemType, page, size, start, end);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	/**
	 * 逾期审核、逾期审批
	 * @param overdueType
	 * @param page
	 * @param size
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping(value = "/queryDataOverdue", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryDataOverdue(String overdueType,  
					@RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "10")int size,
					@RequestParam(required=false) String start, @RequestParam(required=false) String end) {
		try {
			Object obj = detailsService.queryDataOverdue(overdueType, page, size, start, end);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	
	/**
	 * 逾期未审核、逾期未审批
	 * @param overdueType
	 * @param page
	 * @param size
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping(value = "/queryDataOverdueUnfinished", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryDataOverdueUnfinished(String overdueType,  
					@RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "10")int size,
					@RequestParam(required=false) String start, @RequestParam(required=false) String end) {
		try {
			Object obj = detailsService.queryDataOverdueUnfinished(overdueType, page, size, start, end);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	
	/**
	 * 安全服务页面已受理详情
	 * @param overdueType
	 * @param page
	 * @param size
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping(value = "/queryDataYetTransactionList", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryDataYetTransactionList(String type,  
					@RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "10")int size,
					@RequestParam(required=false) String start, @RequestParam(required=false) String end) {
		try {
			Object obj = detailsService.queryDataYetTransactionList(type, page, size, start, end);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	
	
	
	
	
	
	
	//审核办理页面按钮详细数据
	@RequestMapping(value = "/queryManageListData", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryManageListData(@RequestParam(defaultValue = "1")int page, 
									@RequestParam(defaultValue = "10")int size,
									@RequestParam(required=false, value = "idList") String idList) {
		try {
			String[] idLists = null;
			if(idList != null) {
				idLists = idList.split(",");
			}
			Object obj = detailsService.queryManageListData(idLists, page, size);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	
	
	//审核办理审批办理页面的资料不全、资料有误、重复录入按钮详细数据
	@RequestMapping(value = "/queryDataIncompleteList", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryDataIncompleteList(String type,
									@RequestParam(defaultValue = "1")int page, 
									@RequestParam(defaultValue = "10")int size,
									@RequestParam(required=false, value = "idList") String idList) {
		try {
			String[] idLists = null;
			if(idList != null) {
				idLists = idList.split(",");
			}
			Object obj = detailsService.queryDataIncompleteList(type, idLists, page, size);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	
	//安全服务页面按钮详细数据
	@RequestMapping(value = "/querySafetyDataList", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result querySafetyDataList(@RequestParam(defaultValue = "1")int page, 
									@RequestParam(defaultValue = "10")int size,
									@RequestParam(required=false, value = "idList") String idList) {
		try {
			String[] idLists = null;
			if(idList != null) {
				idLists = idList.split(",");
			}
			Object obj = detailsService.querySafetyDataList(idLists, page, size);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	
}
