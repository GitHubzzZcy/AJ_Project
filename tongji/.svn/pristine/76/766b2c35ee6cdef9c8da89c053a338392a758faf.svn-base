package com.henghao.dao;

import java.util.List;
import com.henghao.entity.Personnel;



public interface EnforcementDao extends IBaseDao<Personnel> {

	/**
	 * 查询执法次数
	 * @param name
	 * @return
	 */
	public List<?> enforcementCount(String name,String date1,String date2);
	/**
	 * 每月的执法次数
	 * @param name
	 * @return
	 */
	public List<?> monthEnforcementCount(String name,String date1,String date2);
	/**
	 * 执法企业分类
	 * @param name
	 * @return
	 */
	public List<?> enforcementEnterprise(String name);

}
