package com.henghao.service;

import com.henghao.entity.Result;
import com.henghao.util.PageBaen;

public interface EnterpriceService {
	/**
	 * 根据区域统计企业
	 * @return
	 */
	public Result findByQy();
	/**
	 * 按监管类型查询企业统计
	 * @return
	 */
	public Result findByJglx();
	/**
	 * 按监管部门分类查询企业统计
	 * @return
	 */
	public Result findByJgbm();
	/**
	 * 按监管级别查询企业统计
	 * @return
	 */
	public Result findByJgjb();
	/**
	 * 按日期条件查询各区域企业隐患统计
	 */
	public Result findYhByCondition(String condition);
	/**
	 * 按日期条件查询各监管部门企业隐患统计
	 */
	public Result findYhByBmCondttion(String condition);
	/**
	 * 按日期条件查询各监管类型企业隐患统计
	 */
	public Result findYhByLxCondttion(String condition);
	/**
	 * 按日期条件查询个区域企业自查自报统计
	 */
	public Result findYhByConditionZczb(String condition);
	/**
	 * 按日期条件查询各监管部门企业自查自报统计
	 */
	public Result findYhByBmCondttionZczb(String condition);
	/**
	 *  按日期条件查询各监管类型企业自查自报统计
	 * @param condition
	 * @return
	 */
	
	public Result findYhByLxCondttionZczb(String condition);
	/**
	 * 查询用户信息，返回用户id，姓名，部门，岗位
	 * @param condition
	 * @return
	 */
	public Result findAllUser(String condition);
	/**
	 * 查询最新的指定数量的隐患记录,num获取数量
	 * @param num
	 */
	public Result findHiddByNum(String num);
	/**
	 * 查询最新安全宣传教育，传入最新的条数
	 * @param num
	 */
	public Result findSafeTeache(String num);
	
	/**
	 * 按照用户名查询他今年的隐患排查超时的数目，按年查询
	 */
	public Result findHiddenChaosshi(String user,String date);
	/**
	 * 按照用户名查询他今年的隐患排查成功的数目，按年查询
	 */
	public Result findHiddenSuccess(String user,String date);
	/**
	 * 按照用户名查询他今年的隐患排查的整改情况：1未整改、2整改中、3已整,其他的数量，按年查询
	 */
	public Result findHiddenAllNumByzgstatus(String user,String date);
	/**
	 * 按照用户名查询他今年的隐患排查的整改情况数量：按企业类型分类，按年查询
	 */
	public Result findHiddenAllNumByEnlx(String user,String date);
	/**
	 * 按照用户名查询他今年的隐患排查的整改情况数量：按企业区域划分，按年查询
	 */
	public Result findHiddenAllNumByEnqh(String user,String date);
	/**
	 * 按照用户名查询他今年的隐患排查的整改情况数量：按整改季度统计，按年查询
	 */
	public Result findHiddenAllNumBySession(String user,String date);
	/**
	 * 按照用户名查询分析他的各办事时间差长度统计
	 */
	public Result findTimeLength(String date,String user);
	/**
	 * 查询 指定时间 所有有隐患排查记录的企业
	 */
	public Result findAllHidEnByDate(String entName,String date);
	/**
	 * 由 企业id 查询指定企业 指定时间 的隐患排查情况
	 */
	public Result findHidEnByDate_EnId(String entId,String date); 
	/**
	 * 查询指定一个执法大队的几个人的执法数目,date指定时间段，names执法大队人员名字，人员中间用“,”（英文逗号）隔开
	 */
	public Result findZfByDate(String names, String date);
	/**
	 * 根据姓名和日前查询执法人员的执法企业和位置
	 */
	public Result findZfPosition(String username,
			String startDate, String endDate,PageBaen pb);
}
