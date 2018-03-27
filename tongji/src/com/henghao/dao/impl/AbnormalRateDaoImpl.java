package com.henghao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.henghao.dao.AbnormalRateDao;
import com.henghao.entity.AbnormalRate;

@Repository(value="abnormalRateDao")
public class AbnormalRateDaoImpl extends BaseDao<AbnormalRate> implements
		AbnormalRateDao {

	@SuppressWarnings("rawtypes")
	@Override
	public List searchAbnormalRateByNum(String name) {
		String sql = "SELECT count(ISPUNISH), COUNT(TYPE),COUNT(HASHIDDEN),COUNT(ISREVIEW),ENTERPRISE from AI_PUNISH p "+
				"left join AI_ENTERPRISE e on p.ID = e.PUNISHID "+
				"inner join CK_INSPECTRECORD c on c.ID = p.INSPECTRECORDID "+
				"where CHKPERSON1 =(SELECT id from EL_TROOPEMP WHERE name = ? ) "+
				"or CHKPERSON2 =(SELECT id from EL_TROOPEMP WHERE name = ?) "+
				"or CHKPERSON3 =(SELECT id from EL_TROOPEMP WHERE name = ?) "+
				"GROUP BY ENTERPRISE";
		List list = getSession().createSQLQuery(sql).setParameter(0, name).setParameter(1, name).setParameter(2, name).list();
		return list;
	}

	@Override
	public List<?> searchAbnormalRatByStandard(String name) {
		String sql = "SELECT ENTERPRISE ,STANDARDCHECKNUM,STANDARDPUNISHNUM,STANDARDRECT,STANDARDREVIEW,STARTTIME,ENDTIME from AI_PUNISH p "+
				"left join AI_ENTERPRISE e on p.ID = e.PUNISHID "+
				"inner join CK_INSPECTRECORD c on c.ID = p.INSPECTRECORDID "+
				"where CHKPERSON1 =(SELECT id from EL_TROOPEMP WHERE name = ? )"+
				"or CHKPERSON2 =(SELECT id from EL_TROOPEMP WHERE name = ? )"+
				"or CHKPERSON3 =(SELECT id from EL_TROOPEMP WHERE name = ? )";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, name).setParameter(1, name).setParameter(2, name).list();
		return list;
	}

}
