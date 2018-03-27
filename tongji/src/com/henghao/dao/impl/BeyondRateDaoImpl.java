package com.henghao.dao.impl;



import java.util.List;

import org.springframework.stereotype.Repository;

import com.henghao.dao.BeyondRateDao;
import com.henghao.entity.BeyondRate;
@Repository
public class BeyondRateDaoImpl extends BaseDao<BeyondRate> implements
		BeyondRateDao {

	@SuppressWarnings("rawtypes")
	@Override
	public List searchByBeyondRate(String name) {
		String sql="SELECT CK.STARTTIME start1,CK.ENDTIME end1, PL.STARTTIME start2,PL.ENDTIME end2 "+
				"FROM CK_INSPECTRECORD ck inner join PLAN_INSPECTPLAN pl ON CK.SOURCEID= PL.ID "+
				"WHERE CHKPERSON1 = (SELECT id FROM EL_TROOPEMP WHERE name = ? ) OR "+
				"CHKPERSON2 = (SELECT id FROM EL_TROOPEMP WHERE name = ? ) OR "+
				"CHKPERSON3 = (SELECT id FROM EL_TROOPEMP WHERE name = ? ) ORDER BY start2 DESC ";
		List list = (List) getSession().createSQLQuery(sql).setParameter(0, name).setParameter(1, name).setParameter(2, name).list();
		return list;
	}

}
