package com.henghao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.entity.Result;
import com.henghao.service.EnterpriceService;
import com.henghao.util.PageBaen;

/**2017-3-29,下午5:50:52EnterpriseController.java
 * @author Nwl
 *远东信息统计
 */
@Controller
@RequestMapping("/Enterise")
public class EnterpriseController {
	@Resource
	private EnterpriceService serivce;

	//按区域查询企业统计
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findByQy")
	@ResponseBody
	public Result findByQy(){
		Result result=serivce.findByQy();
		return result;
	}
	//按监管类型查询企业统计
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findByJglx")
	@ResponseBody
	public Result findByJglx(){
		Result result=serivce.findByJglx();
		return result;
	}
	//按监管部门分类查询企业统计
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findByJgbm")
	@ResponseBody
	public Result findByJgbm(){
		Result result=serivce.findByJgbm();
		return result;
	}	
	//按监管级别分类查询企业统计
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findByJgjb")
	@ResponseBody
	public Result findByJgjb(){
		Result result=serivce.findByJgjb();
		return result;
	}
	//按日期条件查询各行政区划企业隐患统计
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findYhByCondition")
	@ResponseBody
	public Result findYhByCondition(String date){
		Result result=serivce.findYhByCondition(date);
		return result;
	}
	//按日期条件查询各监管部门企业隐患统计
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findYhByBmCondttion")
	@ResponseBody
	public Result findYhByBmCondttion(String date){
		Result result=serivce.findYhByBmCondttion(date);
		return result;
	}
	//按日期条件查询各监管类型企业隐患统计
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findYhByLxCondttion")
	@ResponseBody
	public Result findYhByLxCondttion(String date){
		Result result=serivce.findYhByLxCondttion(date);
		return result;
	}
	// 按日期条件查询个区域企业自查自报统计	
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findYhByConditionZczb")
	@ResponseBody
	public Result findYhByConditionZczb(String date){
		Result result=serivce.findYhByConditionZczb(date);
		return result;
	}
	// 按日期条件查询各监管部门企业自查自报统计
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findYhByBmCondttionZczb")
	@ResponseBody
	public Result findYhByBmCondttionZczb(String date){
		Result result=serivce.findYhByBmCondttionZczb(date);
		return result;
	}
	// 按日期条件查询各监管类型企业自查自报统计
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findYhByLxCondttionZczb")
	@ResponseBody
	public Result findYhByLxCondttionZczb(String date){
		Result result=serivce.findYhByLxCondttionZczb(date);
		return result;
	}

	//查询最新的指定数量的隐患记录,不传参数默认3条
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findHiddByNum")
	@ResponseBody
	public Result findHiddByNum(String num){
		Result result=serivce.findHiddByNum(num);		
		return result;
	}

	@RequestMapping(produces="application/json;charset=UTF-8",value="/jsiontest")
	@ResponseBody
	public String jsiontest(){	
		return "test success:jsiontest";
	}


	//查询所有用户信息，返回用户id，姓名，部门，岗位
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findAllUser")
	@ResponseBody
	public Result findAllUser(String uname){
		Result result=serivce.findAllUser(uname);		
		return result;
	}	


	// 按照用户名查询他今年的隐患排查超时的数目，按年查询
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findHiddenChaosshi")
	@ResponseBody
	public Result findHiddenChaosshi(String user,String date){
		Result result=serivce.findHiddenChaosshi(user,date);		
		return result;
	}
	
	// 按照用户名查询他今年的隐患排查成功的数目，按年查询	
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findHiddenSuccess")
	@ResponseBody
	public Result findHiddenSuccess(String user,String date){
		Result result=serivce.findHiddenSuccess(user,date);		
		return result;
	}
	
	// 按照用户名查询他今年的隐患排查的整改情况：1未整改、2整改中、3已整,其他的数量，按年查询
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findHiddenAllNumByzgstatus")
	@ResponseBody
	public Result findHiddenAllNumByzgstatus(String user,String date){
		Result result=serivce.findHiddenAllNumByzgstatus(user,date);		
		return result;
	}
	
	// 按照用户名查询他今年的隐患排查的整改情况数量：按企业类型分类，按年查询
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findHiddenAllNumByEnlx")
	@ResponseBody
	public Result findHiddenAllNumByEnlx(String user,String date){
		Result result=serivce.findHiddenAllNumByEnlx(user,date);		
		return result;
	}
	
	// 按照用户名查询他今年的隐患排查的整改情况数量：按企业区域划分，按年查询
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findHiddenAllNumByEnqh")
	@ResponseBody
	public Result findHiddenAllNumByEnqh(String user,String date){
		Result result=serivce.findHiddenAllNumByEnqh(user,date);		
		return result;
	}
	
	//按照用户名查询他今年的隐患排查的整改情况数量：按整改季度统计，按年查询
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findHiddenAllNumBySession")
	@ResponseBody
	public Result findHiddenAllNumBySession(String user,String date){
		Result result=serivce.findHiddenAllNumBySession(user,date);		
		return result;
	}
	

	//查询最新安全宣传教育，传入最新的条数，不传参数默认1条
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findSafeTeache")
	@ResponseBody
	public Result findSafeTeache(String num){
		Result result=serivce.findSafeTeache(num);		
		return result;
	}
	
	//按照用户名查询分析他的各办事时间差长度统计(返回平均办事时间分钟)
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findTimeLength")
	@ResponseBody
	public Result findTimeLength(String date,String user){		
		Result result=serivce.findTimeLength( date, user);		
		return result;
	}
	
	// 查询 指定时间 搜索名称企业 的所有有隐患排查记录的企业
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findAllHidEnByDate")
	@ResponseBody
	public Result findAllHidEnByDate(String date,String entName){
		if(entName==null || entName.equals("")){
			entName="_";
		}
		Result result=serivce.findAllHidEnByDate(entName,date);		
		return result;
	}
	
	// 由 企业id 查询指定企业 指定时间 的隐患排查情况
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findHidEnByDate_EnId")
	@ResponseBody
	public Result findHidEnByDate_EnId(String entId,String date){
		Result result=serivce.findHidEnByDate_EnId(entId, date);		
		return result;
	}
	
	//查询指定一个执法大队的几个人的执法数目,date指定时间段，names执法大队人员名字，人员中间用“,”（英文逗号）隔开
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findZfByDate")
	@ResponseBody
	public Result findZfByDate(String names, String date){
		Result result=serivce.findZfByDate(names, date);		
		return result;
	}
	
	//根据姓名和日前查询执法人员的执法企业和位置
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findZfPosition")
	@ResponseBody
	public Result findZfPosition(String username, String startDate,
			String endDate, PageBaen pb){
		Result result=serivce.findZfPosition(username,startDate,endDate,pb);		
		return result;
	}
}
