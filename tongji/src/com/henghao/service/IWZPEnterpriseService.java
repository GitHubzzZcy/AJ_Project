package com.henghao.service;

import com.henghao.entity.Result;

/**
 * 企业统计service接口
 * @author 王章鹏
 * @time 2017-4-13
 */
public interface IWZPEnterpriseService {

	/**
	 * 行政执法统计(statistics enforcement)
	 * <br>统计 未检查企业数、已检查企业数、已执法企业数
	 * @author 王章鹏
	 * @time 2017年4月27日09:20:36
	 * @return {@link Result}
	 */
	public Result getEnforcement();
	
	/**
	 * @author 王章鹏
	 * @time 2017-4-13
	 * 查询存在隐患的所有企业
	 * @return {@link Result}
	 */
	public Result getAllInfo();
	
	/**
	 * @author 王章鹏
	 * @time 2017-4-13
	 * 企业统计，包括共有企业数和上报企业数
	 * @return {@link Result}
	 */
	public Result getEnterpriseCount();
	
	/**
	 * 执法综合查询<br>
	 * 查询执法业务信息、查询执法情况的统计信息、各被执法单位有关情况
	 * @author 王章鹏
	 * @param selectType 查询类型
	 * 创建时间：2017-5-25 上午11:02:25
	 * @return {@link Result}
	 */
	public Result getSsynthesize(String selectType);
	
	/**
	 * 根据企业名称查询执法情况的统计信息
	 * 创建时间：2017-5-26 上午10:48:34
	 * @param ENTName 企业名称
	 * @return {@link Result}
	 */
	public Result getZhifaSituatinCount(String ENTName);
	
	/**
	 * 根据企业名称和时间，查询执法情况统计的详情
	 * 创建时间：2017-6-2 下午2:15:54
	 * @param ENTName
	 * @param date
	 * @return
	 */
	public Result getZhifaDetail(String ENTName,String date);
}
