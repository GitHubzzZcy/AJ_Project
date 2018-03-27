package com.henghao.dao;

import java.util.Map;

import com.henghao.entity.wzp.AjZfdata;

/**
 * @author wzp
 * @description: 统计信息Dao接口，数据库操作
 * @create on 2017/12/5.
 */
public interface IWZPStatisctDao extends IWZPBaseDao<AjZfdata> {

	Map<String, Object> queryInventory(String start, String end, String name, int page, int size);
}
