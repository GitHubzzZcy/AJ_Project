package com.henghao.dao.impl;

import org.springframework.stereotype.Repository;

import com.henghao.dao.DeleteDataDao;
import com.henghao.entity.Personle;
@Repository
public class DeleteDataDaoImpl extends BaseDao<Personle> implements DeleteDataDao {
	@Override
	public void deleteData() {
		String sql = "DELETE tw_hz_instance,tw_hz_log,tw_hz_worklist,TW_HZ_TRACK,twr_hz_instance  "
				+ "from tw_hz_log,tw_hz_instance,tw_hz_worklist,TW_HZ_TRACK,twr_hz_instance  "
				+ "WHERE tw_hz_log.workid IN ("+
				"SELECT * from (SELECT WORKID from TW_HZ_LOG WHERE ACTIONNAME = '终止') log"+
				")AND tw_hz_instance.ID = tw_hz_log.WORKID AND tw_hz_worklist.WORKID = tw_hz_log.WORKID AND TW_HZ_TRACK.WORKID = tw_hz_log.WORKID and tw_hz_log.WORKID = twr_hz_instance.WORKID";
		
		System.out.println(sql+"--------------");
		super.getSession().createSQLQuery(sql).executeUpdate();
		System.out.println(sql+"--------------");
	}

}
