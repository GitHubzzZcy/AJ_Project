package com.henghao.dao;

import java.util.List;

import com.henghao.entity.Personle;

public interface SignificantAmountDao extends IBaseDao<Personle>  {
	/**
	 * 重大资金使用比例
	 * @param date
	 * @return
	 */
	public List<?> significantAmountProportion();
	/**
	 * 重大资金使用详情
	 * @param date
	 * @return
	 */
	public List<?> significantAmountDetails();
}
