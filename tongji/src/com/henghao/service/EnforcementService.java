package com.henghao.service;

import com.henghao.entity.Result;

public interface EnforcementService {
	/**
	 * 执法次数
	 * @param name
	 * @return
	 */
	public Result enforcementCount(String name);
	/**
	 * 每月执法次数
	 * @param name
	 * @return
	 */
	public Result monthEnforcementCount(String name);
	/**
	 * 执法企业分类
	 * @param name
	 * @return
	 */
	public Result enforcementEnterprise(String name);

}
