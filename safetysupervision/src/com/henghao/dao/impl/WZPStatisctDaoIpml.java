package com.henghao.dao.impl;

import com.henghao.dao.IWZPStatisctDao;
import com.henghao.entity.Inventory;
import com.henghao.entity.wzp.AjZfdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

/**
 * @author wzp
 * @description: 统计信息Dao接口实现类
 * @create on 2017/12/5.
 */
@Repository("statisctDao")
public class WZPStatisctDaoIpml extends WZPBaseDaoImpl<AjZfdata> implements IWZPStatisctDao {

	private Map<String, Object> map = new HashMap<String, Object>();
	@Resource
	private SessionFactory sessionFactory;
	@Override
	public Map<String, Object> queryInventory(String start, String end, String name, int page, int size) {
		String countsql = "SELECT COUNT(*) FROM aj_zfdata a LEFT OUTER JOIN (SELECT PARENTID, group_concat(sm,'') AS SM ,group_concat(TIME,'') AS TIME  FROM aj_sjz GROUP BY PARENTID) AS b ON a.ID=b.PARENTID "
				+ "WHERE b.TIME>? AND b.TIME<? AND ZFRY LIKE '%"+name+"%'";
		String count = sessionFactory.getCurrentSession().createSQLQuery(countsql).setParameter(0, start).setParameter(1, end).uniqueResult().toString();
		String sql = "SELECT a.ZFLB, b.TIME, a.ZFBM, a.ZFRY, b.SM, a.YHSM AS FLYJ FROM aj_zfdata a LEFT OUTER JOIN (SELECT PARENTID, group_concat(sm,'') AS SM ,group_concat(TIME,'') AS TIME  FROM aj_sjz GROUP BY PARENTID) AS b ON a.ID=b.PARENTID "
				+ "WHERE b.TIME>? AND b.TIME<? AND ZFRY LIKE '%"+name+"%' ORDER BY b.TIME desc  LIMIT ?,?";
		@SuppressWarnings("unchecked")
		List<Inventory> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, start).setParameter(1, end).setParameter(2, (page-1)*size).setParameter(3, size)
		.setResultTransformer(Transformers.aliasToBean(Inventory.class)).list();
		map.put("total", count);
		map.put("list", list);
		return map;
	}
}
