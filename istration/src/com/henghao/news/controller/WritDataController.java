package com.henghao.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.news.entity.CkInspectrecord;
import com.henghao.news.entity.ForceMeasuresEntity;
import com.henghao.news.entity.OrderChangeEntity;
import com.henghao.news.service.WritDataService;
import com.henghao.news.util.GsonUtil;
import com.henghao.news.util.Result;



/**
 *文书相关数据控制层
 *@author: zcy
 *@date： 2017-9-21 上午10:24:08
 *@version 1.1
 */
@Controller
@RequestMapping("/writData")
public class WritDataController {

	@Autowired
	private WritDataService writDataService;
	
	/**
	 * 所有文书详情内容查询接口
	 * @param dtid
	 * @return
	 */
	@RequestMapping(value = "/queryWritData", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryWritData(int dtid) {
		try {
			Object obj = writDataService.queryWritData(dtid);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	/**
	 * 添加现场检查记录文书
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/addCkInspectrecord", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result addCkInspectrecord(String json, @RequestParam(value="pid")String pid, 
			@RequestParam(value="htmlUrl")String htmlUrl) {
		try {
			CkInspectrecord gson = GsonUtil.parseJsonWithGson(json, CkInspectrecord.class);
			Object obj = writDataService.addCkInspectrecord(gson, Integer.parseInt(pid), htmlUrl);
			return new Result(0, "新增成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "新增失败", null);
	}
	/**
	 * 添加责令限期整改指令书
	 * @param json OrderChangeEntity类json字符串
	 * @param pid 计划id
	 * @param htmlUrl  页面地址
	 * @param resultStatus  现场检查文书后的状态
	 * @return
	 */
	@RequestMapping(value = "/addOrderChangeEntity", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result addOrderChangeEntity(String json, @RequestParam(value="pid")String pid,
			@RequestParam(value="htmlUrl")String htmlUrl, @RequestParam(value="resultStatus")int resultStatus) {
		try {
			OrderChangeEntity gson = GsonUtil.parseJsonWithGson(json, OrderChangeEntity.class);
			Object obj = writDataService.addOrderChangeEntity(gson, Integer.parseInt(pid), htmlUrl, resultStatus);
			return new Result(0, "新增成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "新增失败", null);
	}
	
	/**
	 * 强制措施决定文书保存
	 * @param json
	 * @param pid
	 * @param htmlUrl
	 * @return
	 */
	@RequestMapping(value = "/addForceMeasures", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result addForceMeasures(String json, @RequestParam(value="pid")String pid, 
			@RequestParam(value="htmlUrl")String htmlUrl, @RequestParam(value="resultStatus")int resultStatus) {
		try {
			ForceMeasuresEntity gson = GsonUtil.parseJsonWithGson(json, ForceMeasuresEntity.class);
			Object obj = writDataService.addForceMeasures(gson, Integer.parseInt(pid), htmlUrl, resultStatus);
			return new Result(0, "新增成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "新增失败", null);
	}
	
}
