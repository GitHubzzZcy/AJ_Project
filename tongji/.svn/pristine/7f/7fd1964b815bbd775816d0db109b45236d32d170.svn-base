package com.henghao.dao.impl;


import java.util.List;


import org.springframework.stereotype.Repository;
import com.henghao.dao.WorkNumDao;

import com.henghao.entity.WorkNum;
@Repository
public class WorkNumDaoImpl extends BaseDao<WorkNum> implements WorkNumDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> searchWorkByName(String name) {
		String sql = "SELECT COUNT(*),SIGNINFO,STARTTIME FROM CK_INSPECTRECORD  " +
				"WHERE  CHKPERSON1 = (SELECT id from EL_TROOPEMP WHERE name = ? ) OR  " +
				"CHKPERSON2 = (SELECT id FROM EL_TROOPEMP WHERE name = ? ) OR  " +
				"CHKPERSON3 = (SELECT id FROM EL_TROOPEMP WHERE name = ? ) " +
				"GROUP BY SIGNINFO,STARTTIME";
			List<Object[]> list = (List<Object[]>)getSession().createSQLQuery(sql).setParameter(0, name).setParameter(1, name).setParameter(2, name).list();
			
			
		return list;
	}


}
