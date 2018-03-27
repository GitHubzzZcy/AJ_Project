package com.henghao.dao;

import java.util.List;

import com.henghao.entity.Personle;

public interface DeleteTableDao extends IBaseDao<Personle>  {
	
	/***
	 * 获取actionname为终止的对象
	 * @return
	 */
	public List<Object> getTableDataId();
	
	/**
	 * 根据指定的表名和ID删除对象
	 * @return
	 */
	public Integer deleteTableId();
	
	
}
