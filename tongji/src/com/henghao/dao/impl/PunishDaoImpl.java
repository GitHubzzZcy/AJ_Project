package com.henghao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.henghao.dao.PunishDao;
import com.henghao.entity.Punish;
@Repository
public class PunishDaoImpl extends BaseDao<Punish> implements PunishDao {

	@SuppressWarnings("rawtypes")
	@Override
	public List searchPunishById(String name) {
		String sql="SELECT FINEMAXMONEY,FINEMINMONEY,STANDARDMONEY,FACTMONEY,PUNISHTIME FROM AI_PUNISH p "+
				"INNER JOIN CK_INSPECTRECORD c on p.INSPECTRECORDID = c.ID "+
				"INNER JOIN AI_ENTERPRISE e ON e.PUNISHID = p.ID "+
				"INNER JOIN AI_ENTERPRISETYPE t ON t.ID = e.ENTERPRISETYPEID "+
				"WHERE CHKPERSON1 =(SELECT id FROM EL_TROOPEMP WHERE name = ? ) "+
				"OR CHKPERSON2  = (SELECT id FROM EL_TROOPEMP WHERE name = ? ) "+
				"OR CHKPERSON3  = (SELECT id FROM EL_TROOPEMP WHERE name = ? ) "+
				"ORDER BY PUNISHTIME DESC";
		System.out.println(sql);
		List list = (List) getSession().createSQLQuery(sql).setParameter(0, name).setParameter(1, name).setParameter(2, name).list();
		return list;
	}

}
