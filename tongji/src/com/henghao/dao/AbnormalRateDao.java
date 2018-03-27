package com.henghao.dao;

import java.util.List;

import com.henghao.entity.AbnormalRate;
@SuppressWarnings("rawtypes")
public interface AbnormalRateDao extends IBaseDao<AbnormalRate> {
	
	/**
	 * 实际次数
	 * @param name
	 * @return
	 */
	public List searchAbnormalRateByNum(String  name);
	/**
	 * 标准次数
	 * @param name
	 * @return
	 */
	public List searchAbnormalRatByStandard(String name);
}
