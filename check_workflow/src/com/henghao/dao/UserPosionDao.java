package com.henghao.dao;

import java.util.Date;
import java.util.List;

import com.henghao.entity.User_position_log;
/**
 * 人员位置dao
 * @类功能说明：  
 * @类修改者：  
 * @修改日期：  
 * @修改说明：  
 * @公司名称：恒昊  
 * @作者： 宁万龙
 * @创建时间：2017-6-15 下午4:13:57  
 * @版本：V1.0
 */
public interface UserPosionDao extends IBaseDao<User_position_log> {
	/**
	 * 查询指定时间段位置信息记录
	 */
	public List<User_position_log> findPositionByDate(String uname,Date stratDate,Date endDate);
}
