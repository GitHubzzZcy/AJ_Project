package com.henghao.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.henghao.dao.ImportantMatterDao;
import com.henghao.entity.Personle;
@Repository
public class ImportantMatterDaoImpl extends BaseDao<Personle>  implements ImportantMatterDao {
	/**
	 * 项目安排月份情况
	 */
	@Override
	public List<?> importantMatterMonth(String date) {
		String sql = "SELECT COUNT(*) FROM aj_great_meeting WHERE  DATE_FORMAT(`SXJSRQ`,'%Y-%m-%d') LIKE ('"
							+ date + "%') AND MTTYPE = '重大项目安排' ";
		List<?> list = getSession().createSQLQuery(sql).list();
		return list;
	}

	@Override
	public List<?> importantMatterYear(String date) {
		String sql = "SELECT  COUNT(*) FROM aj_great_meeting WHERE  DATE_FORMAT(`SXJSRQ`,'%Y-%m-%d') LIKE ('"
							+ date + "%') AND MTTYPE = '重大项目安排'";
		List<?> list = getSession().createSQLQuery(sql).list();
		//System.out.println(sql);
		return list;
	}
	/**
	 * 重大事项分布情况
	 */
	@Override
	public List<?> importantMatterDistribution(String deptName) {
		String sql ="SELECT COUNT(*) FROM aj_great_meeting m,to_horizon_dept d,to_horizon_user u,"
				+ "tor_horizon_user_dept ud "
				+ "WHERE u.ID = ud.USER_ID"
				+ " AND ud.DEPT_ID = d.ID"
				+ " AND m.MTINITIATOR = u.NAME"
				+ " AND m.MTTYPE = '重大项目安排'"
				+ " AND d.DEPT_NAME = '"+deptName+"'";
		List<?> list = getSession().createSQLQuery(sql).list();
			System.out.println(sql);
			//System.out.println(list);
		return list;
	}
	
	/**
	 * 重大事项积累事件数
	 */
	@Override
	public List<?> importantMatterNumber() {
			String sql ="SELECT DEPT_NAME from to_horizon_dept";
			List<?> list = getSession().createSQLQuery(sql).list();			 
			System.out.println(sql);	
		return list;
	}



}
