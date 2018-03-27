package com.henghao.dao.impl;

import java.util.List;


import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.henghao.dao.IntimacyDao;
import com.henghao.entity.Intimacy;
@Repository
public class IntimacyDaoImpl extends BaseDao<Intimacy> implements IntimacyDao {

//	@Override
//	public List<?> searchIntimacyByName(String name) {
//		String sql="";
//		List<?> list = getSession().createSQLQuery(sql).setParameter(0, name).setParameter(1, name).setParameter(2, name).list();
//		return list;
//	}

	@SuppressWarnings("rawtypes")
	@Override
	public List searchEnterprise(String employName) {
//		String sql = "SELECT sum(ISPUNISH), ENTERPRISE  from AI_PUNISH p " +
//				" left join AI_ENTERPRISE e on p.ID = e.PUNISHID " +
//				" inner join CK_INSPECTRECORD c on c.ID = p.INSPECTRECORDID " +
//				" where CHKPERSON1 =(SELECT id from EL_TROOPEMP WHERE name = ?) " +
//				"  OR CHKPERSON2  = (SELECT id from EL_TROOPEMP WHERE name = ?)  " +
//				"  OR CHKPERSON3  = (SELECT id from EL_TROOPEMP WHERE name = ?) " +
//			"  GROUP BY ENTERPRISE";
		
		String sql = "SELECT ISPUNISH, ENTERPRISE,PUNISHNUMMAX,PUNISHNUMMIN,c.STARTTIME START1,c.ENDTIME END1,s.STARTTIME START2,s.ENDTIME END2,PUNISHRATEMAX,PUNISHRATEMIN,FINEMAXMONEY,FINEMINMONEY,FACTMONEY "+ 
				 "FROM AI_PUNISH p "+
				 "LEFT JOIN AI_ENTERPRISE e ON p.ID = e.PUNISHID "+
				 "INNER JOIN CK_INSPECTRECORD c ON c.ID = p.INSPECTRECORDID "+
				 "INNER JOIN PLAN_INSPECTPLAN s ON s.ID = c.SOURCEID "+
				 "INNER JOIN AI_ENTERPRISETYPE t ON t.ID = e.ENTERPRISETYPEID "+
				 "WHERE CHKPERSON1 =(SELECT id FROM EL_TROOPEMP WHERE name = ? ) "+
				 "OR CHKPERSON2  = (SELECT id FROM EL_TROOPEMP WHERE name = ? ) "+ 
				 "OR CHKPERSON3  = (SELECT id FROM EL_TROOPEMP WHERE name = ?)";
		List list = (List) getSession().createSQLQuery(sql).setParameter(0, employName).setParameter(1, employName).setParameter(2, employName).list();
		System.out.println(list);
		return list;
	}


	@Override
	public List<?> searchPunishNum(String punishName) {
		String sql="SELECT COUNT(ISPUNISH),ENTERPRISE FROM AI_PUNISH p " +
				"LEFT JOIN AI_ENTERPRISE e on p.ID = e.PUNISHID " +
				"INNER JOIN CK_INSPECTRECORD c on c.ID = p.INSPECTRECORDID WHERE CHKPERSON1 =(SELECT id FROM EL_TROOPEMP WHERE name = ? )" +
				"OR CHKPERSON2 =(SELECT id FROM EL_TROOPEMP WHERE name = ?)" +
				"OR CHKPERSON3 =(SELECT id FROM EL_TROOPEMP WHERE name = ?)" +
				"GROUP BY ENTERPRISE";
		SQLQuery query = getSession().createSQLQuery(sql);
		List<?> list = query.setParameter(0, punishName).setParameter(1, punishName).setParameter(2, punishName).list();
		return list;
	}

	@Override
	public List<?> searchEnterpriseNum(String name) {
		String sql = "SELECT COUNT(TYPE), ENTERPRISE FROM AI_PUNISH p "+
				" LEFT JOIN AI_ENTERPRISE e ON p.ID = e.PUNISHID"+
				" INNER JOIN CK_INSPECTRECORD c on c.ID = p.INSPECTRECORDID"+
				" WHERE CHKPERSON1 =(SELECT id FROM EL_TROOPEMP WHERE name = ? )"+
				" OR CHKPERSON2  = (SELECT id FROM EL_TROOPEMP WHERE name = ? ) "+
				" OR CHKPERSON3  = (SELECT id FROM EL_TROOPEMP WHERE name = ? )"+
				" GROUP BY ENTERPRISE";
		SQLQuery query = getSession().createSQLQuery(sql);
		List<?> list = query.setParameter(0, name).setParameter(1, name).setParameter(2, name).list();
		return list;
	}
	

}
