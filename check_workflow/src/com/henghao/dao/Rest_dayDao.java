package com.henghao.dao;

import java.util.Date;
import java.util.List;

import com.henghao.entity.Rest_day;
import com.henghao.util.PageBaen;

public interface Rest_dayDao extends IBaseDao<Rest_day> {
	/**
	 * 更新、设置自定义节假日
	 */
	public void updateRestDay(Rest_day restDay);
	/**
	 * 添加一个节假日
	 */
	public void addRestDay(Rest_day restDay);
	/**
	 * 删除某个节假日
	 */
	public void deleteRestDay(Rest_day restDay);
	/**
	 * 查询节假日
	 */
	public Rest_day findRestDay(int id);
	/**
	 * 查询某个日期是不是节假日,是返回假日，不是返回null
	 */
	public Rest_day isRestDay(Date date);
	/**
	 * 获取指定时间段的节假日数
	 */
	public int Restnum(String sdate,String edate);
	/**
	 * 查询指定节假日
	 */
	public List<Rest_day> findAllRestDay(Date startDate,Date endDate,PageBaen pagebean);
}
