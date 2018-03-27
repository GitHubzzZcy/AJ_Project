package com.henghao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.entity.Result;
import com.henghao.util.TimerManager;

/**
 * @Description: 项目启动时手动调用Redise线程
 * @author zcy  
 * @date 2017年11月15日 下午4:56:49
 * @version 1.0
 */
@Controller
@RequestMapping("/start")
public class ProjectStartCallController {
	
	@RequestMapping(value = "/redis", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result redisStart() {
		try {
			new TimerManager();
			return new Result(0, "启动缓存成功！接口缓存将于每次凌晨0点将数据写入缓存", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(1, "启动缓存失败！", e);
		}
	}
}
