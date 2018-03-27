package com.henghao.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.henghao.entity.Result;
import com.henghao.service.SignificantAmountService;
/**
 * 安检金额安排
 * @author 龙宏
 *
 */
@Controller
@RequestMapping("/sign")
public class SignificantAmountrAction {
	@Autowired
	private SignificantAmountService significantAmountService;
	/**
	 * 重大资金使用比例
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/significantAmountProportion")
	@ResponseBody
	public Result significantAmountProportion() throws Exception{
		return significantAmountService.significantAmountProportion();
	}
	/**
	 * 重大资金使用详情
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/significantAmountDetails")
	public Result significantAmountDetails() throws Exception {
		return significantAmountService.significantAmountDetails();
	}
}
