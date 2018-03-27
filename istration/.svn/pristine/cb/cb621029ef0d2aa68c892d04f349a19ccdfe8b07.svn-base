package com.henghao.news.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.henghao.news.entity.JianchaMaterialEntity;
import com.henghao.news.entity.Plan;
import com.henghao.news.service.ZfAppService;
import com.henghao.news.util.GsonUtil;
import com.henghao.news.util.Result;

/**
 * 该类为处理改造远东APP数据控制层
 *@author: zcy
 *@date： 2017-9-15 上午11:13:17
 *@version 1.0
 */
@Controller
@RequestMapping("/enforceapp")
public class ZfAppController {
	
	@Autowired
	private ZfAppService zfAppService;
	
	/**
	 * 添加计划数据保存
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/savePlan", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result savePlan(@RequestBody String json) {
		try {
			Plan plan = GsonUtil.parseJsonWithGson(json, Plan.class);
			//Plan plan = this.jsonToObject(json);
			zfAppService.savePlan(plan);
			return new Result(0,"保存成功",plan.getPid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1,"保存失败",null);
	}
	
	/**
	 * 根据计划pid查询计划内容
	 * @param pid
	 * @version 1.1 增加DocTypeDefine集合返回
	 * @return
	 */
	@RequestMapping(value = "/queryplanbyid", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryPlan(int id) {
		try {
			Plan plan = zfAppService.queryPlanById(id);
			return new Result(0,"查询成功",plan);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1,"查询失败",null);
	}
	/**
	 * 根据计划id查询有隐患的检查项
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryjianchabystatus", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryJianchaByStatus(int pid) {
		try {
			List<JianchaMaterialEntity>  list = zfAppService.queryJianchaByStatus(pid);
			return new Result(0,"查询成功",list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1,"查询失败",null);
	}
	
	
	/**
	 * 根据用户名id查询计划列表
	 * @param userid
	 * @param resultStatus //计划检查的结果状态  不传默认为-1，表示查询不等于0的所有记录
	 * @version 1.2 增加resultStatus参数
	 * @return
	 */
	@RequestMapping(value = "/queryplanbyuser", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryPlanByUser(String userid, @RequestParam(value="resultStatus", defaultValue="-1") int resultStatus) {
		try {
			List<Plan> plans = zfAppService.queryPlanByUser(userid, resultStatus);
			return new Result(0,"查询成功",plans);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1,"查询失败",null);
	}
	/**
	 * 检查任务时提交检查基本数据---->我要检查
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/updateplanexaminedate", method=RequestMethod.POST)
	@ResponseBody
	public Result updatePlanExamineDate(HttpServletRequest request, String json, @RequestParam(value = "file",required = false) MultipartFile  file) {
		try {
			Plan plan = GsonUtil.parseJsonWithGson(json, Plan.class);
			zfAppService.updatePlanExamineDate(plan, file);
			return new Result(0,"保存成功",null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1,"保存失败",null);
	}
	/**
	 * 保存检查项的检查内容---->隐患添加
	 * @param json
	 * @param files
	 * @return
	 */
	@RequestMapping(value = "/updatecheckcontent", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result updateCheckContent(@RequestParam(value="pid") String pid, String json, @RequestParam(value="files",required = false) MultipartFile[]  files) {
		try {
			JianchaMaterialEntity gson = GsonUtil.parseJsonWithGson(json, JianchaMaterialEntity.class);
			zfAppService.updateCheckContent(Integer.parseInt(pid), gson, files);
			return new Result(0,"保存成功",null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1,"保存失败",null);
	}
	
	/**
	 * 删除已经添加的隐患项
	 * @param cid
	 * @return
	 */
	@RequestMapping(value = "/deletecheckcontent", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result deleteCheckContent(int cid) {
		try {
			zfAppService.deleteCheckContent(cid);
			return new Result(0,"删除成功",null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1,"删除失败",null);
	}

	
}
