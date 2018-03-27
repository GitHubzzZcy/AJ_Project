package com.henghao.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.entity.Result;
import com.henghao.service.IInspService;
import com.henghao.util.DateUtils;
import com.henghao.util.SpringBeanUtil;

/**
 * 执法数据统计
 * @类功能说明：  
 * @修改说明：  
 * @公司名称：恒昊  
 * @作者： 周承耀
 * @创建时间：2017-5-24 下午4:58:17  
 * @版本：V1.0
 */
@RequestMapping("/zhifa")
@Controller
public class InspContrller {

	@Autowired
	private IInspService inspService;
	
	@RequestMapping(value = "/count",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryCount() {
		Result result = inspService.queryCount(); 
		return result;
	}
	/**
	 * 6-28
	 * 个人行政执法月度办理
	 */
	@RequestMapping(value = "/eachMonth", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result eachMonth(String name) {
		Result result = inspService.eachMonth(name); 
		return result;
	}
	/**
	 * 个人执法季度分类
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/eachQuarter", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result eachQuarter(String name) {
		Result result = inspService.eachQuarter(name); 
		return result;
	}
	/**
	 * 个人处理分类
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/eachClassify", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result eachClassify(String name) {
		Result result = inspService.eachClassify(name); 
		return result;
	}
	/**
	 * 个人处罚偏离
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/eachDeviate", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result eachDeviate(String name) {
		Result result = inspService.eachDeviate(name); 
		return result;
	}
	/**
	 * 个人工作高幸苦分布
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/eachWork", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result eachWork(String name) {
		Result result = inspService.eachWork(name); 
		return result;
	}
	/**
	 * 企业诚信情况
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/eachFirmFaith", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result eachFirmFaith(String name) {
		Result result = inspService.eachFirmFaith(name); 
		return result;
	}
	/**
	 * 最近去过的企业
	 */
	@RequestMapping(value = "/eachLately", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result eachLately(String name) {
		Result result = inspService.eachLately(name); 
		return result;
	}
	/**
	 * 执法OA
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/inspectOA", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result inspectOA(String name) {
		Result result = inspService.inspectOA(name); 
		return result;
	}
	/**
	 * 个人月度办件分类
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/monthCount", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result monthCount(String name) {
		Result result = inspService.monthCount(name); 
		return result;
	}
	/**
	 * 个人执法页面滚动
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/queryroll", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryroll(String name) {
		Result result = inspService.queryroll(name); 
		return result;
	}
	
	/**
	 * 累计执法统计
	 */
	@RequestMapping(value="/accumulativeCount", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result accumulativeCount(String name) {
		Result result = inspService.accumulativeCount(name); 
		return result;
	}
	/**
	 * 短信服务
	 * 预留手动触发接口
	 */
	@RequestMapping(value = "/noteserve", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public  Result noteserve() {
		Result result = inspService.noteserve(); 
		return result;
	}
	
//	public InspContrller() {
//		//startnote st = new startnote();
//		//st.start();
//	}
//	
}
///**
// * 短信触发线程
// */
//class startnote extends Thread {
//	@Override
//	public void run() {
//		while(true){
//			IInspService inspService = (IInspService) SpringBeanUtil.getBeanByName("inspService");
//			try {
//				if(Integer.parseInt(DateUtils.getCurrentDate("HH"))>18 && Integer.parseInt(DateUtils.getCurrentDate("HH"))<8){
//					//非工作时间半小时刷新一次
//					System.out.println("非工作时间，短信线程等待睡眠30分钟*******");
//					sleep(1800000);
//				}else{
//					System.out.println("短信线程等待睡眠10分钟*******");
//					//上班时间10分钟调用一次
//					sleep(600000);
//					inspService.noteserve();
//					System.gc();
//				}
//			} catch (InterruptedException e) {
//				return;
//			}
//		}
//	}
//	
//}