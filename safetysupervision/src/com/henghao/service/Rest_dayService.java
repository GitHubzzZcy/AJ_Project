package com.henghao.service;

import java.util.Date;

import com.henghao.entity.Rest_day;
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
	 * 查询某天是不是节假日
	 */
	public Rest_day isRestDay(Date date);
	
}
