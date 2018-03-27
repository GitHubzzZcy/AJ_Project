package com.henghao.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.henghao.entity.Result;
import com.henghao.service.ImportantMatterService;
/**
 * 安检重大事项
 * @author 龙宏
 *
 */
@Controller
@RequestMapping("/matter")
public class ImportantMatterAction {
	@Autowired
	private ImportantMatterService importantMatterService;
	/**
	 * 重大事件月安排
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/importantMatterMonth")
	@ResponseBody
	 public Result importantMatterMonth() throws Exception{
	    	return importantMatterService.importantMatterMonth();
	    }
	/**
	 * 重大事件年安排
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/importantMatterYear")
	@ResponseBody
	public Result importantMatterYear() throws Exception{
		return importantMatterService.importantMatterYear();
	}
	/**
	 * 重大事项分布情况
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/importantMatterDistribution")
	public Result importantMatterDistribution() throws Exception{
		return importantMatterService.importantMatterDistribution();
	}
}
