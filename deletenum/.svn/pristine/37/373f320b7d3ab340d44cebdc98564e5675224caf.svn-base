package com.henghao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.henghao.dao.DeleteTableDao;
import com.henghao.entity.Personle;
@Repository
public class DeleteTableDaoImpl extends BaseDao<Personle> implements DeleteTableDao {

	@Override
	@SuppressWarnings("unchecked")
	public  List<Object> getTableDataId() {
		String sql = "select TABLEID,DATAID from twr_hz_instance where WORKID in(SELECT WORKID from tw_hz_log where ACTIONNAME = '终止')";
		//System.out.println(sql + "-------------------");
		List<Object> list = getSession().createSQLQuery(sql).list();
		return list;
		
	}

	@Override
	public Integer deleteTableId() {
		List<Object> list = getTableDataId();
		Integer status = 0;
		if(list != null && list.size() > 0){
			for (Object obj : list) { 
				Object[] objs = (Object[])obj;
				String tName = objs[0].toString();
				String dataId = objs[1].toString();
				if(tName == null || "".equals(tName) || dataId == null || "".equals(dataId) ){
					continue;
				}else{
					String sql = "delete from " + tName + " where id = '"+dataId+"'";
					try {
						getSession().createSQLQuery(sql).executeUpdate();
						status = 1;
					} catch (Exception e) {
						System.out.println(e.getMessage());
						status = 0;
						continue;
					}
				} 
			}
		}
		return status;
	}

}
