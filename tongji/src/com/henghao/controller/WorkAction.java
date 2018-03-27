package com.henghao.controller;
/**
 * 使用远东数据库
 * @author 龙宏
 * @time 2017-4-13
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.entity.Result;
import com.henghao.service.WorkService;
@Controller
@RequestMapping("/work")
public class WorkAction {
	@Autowired
	private WorkService workService;
	@RequestMapping("/searchPersonnel")
	@ResponseBody
	public Result searchPersonnel() throws Exception{
		return workService.searchPersonnel();
	}
	
	@RequestMapping("/searchWorkByName")
	@ResponseBody
	public  Result searchWorkByName(String name) throws Exception{
		return workService.searchWorkByName(name);
	}
	

	@RequestMapping("/searchBeyondById")
	@ResponseBody
	public Result searchBeyondById(String name) throws Exception{

		return workService.searchBeyondById(name);
	}
	@RequestMapping("/searchPunishById")
	@ResponseBody
	public Result searchPunishById(String name)throws Exception{
		
		return workService.searchPunishById(name);
	} 
	
	@RequestMapping("/searchIntimacyByName")
	@ResponseBody
	public Result searchIntimacyByName(String emplyName) throws Exception{
//		if(emplyName != null){
//			emplyName = new String(emplyName.getBytes("iso-8859-1"),"gbk");
//		}
		
		return workService.searchIntimacyByName(emplyName);
	}
	@RequestMapping("/searchAbnormalRateByName")
	@ResponseBody
	public Result searchAbnormalRateByName(String name) throws Exception{
//		if(name != null){
//			name = new String(name.getBytes("iso-8859-1"),"utf-8");
//		}
		return workService.searchAbnormalRateByName(name);
	}
}
