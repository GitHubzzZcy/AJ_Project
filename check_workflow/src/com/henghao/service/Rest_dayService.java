package com.henghao.service;

import java.util.Date;

import com.henghao.entity.Rest_day;
import com.henghao.entity.Result;
import com.henghao.util.PageBaen;
/**
 * 节假日业务类
 * @类功能说明：  
 * @类修改者：  
 * @修改日期：  
 * @修改说明：  
 * @公司名称：恒昊  
 * @作者： 宁万龙
 * @创建时间：2017-6-5 下午4:22:46  
 * @版本：V1.0
 */
public interface Rest_dayService {
	/**
	 * 更新、设置自定义节假日
	 */
	public Result updateRestDay(Rest_day restDay);
	/**
	 * 添加节假日
	 */
	public Result addRestDay(Rest_day restDay);
	/**
	 * 删除某个节假日
	 */
	public Result deleteRestDay(Rest_day restDay);
	/**
	 * 查询节假日
	 */
	public Rest_day findRestDay(int id);
	/**
	 * 查询全部节假日
	 */
	public Result findAllRestDay(String startDate,String endDate,PageBaen pagenean);
	/**
	 * 自动初始化一年的节假日，调用网络接口
	 */
	public Result autoUpdateRestDay(int year);
	/**
	 * 查询某天是不是节假日
	 */
	public Rest_day isRestDay(Date date);
	/**
	 * 手动更新节假日
	 */
	public Result addOrUpdateRestDay(String restDay_json,int year);
}
