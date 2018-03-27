package com.henghao.dao.impl;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.henghao.dao.EnforcementDao;
import com.henghao.entity.Personnel;

@Repository
public class EnforcementDaoImpl extends BaseDao<Personnel> implements EnforcementDao {

	/**
	 * 查询执法次数
	 */
	@Override
	public List<?> enforcementCount(String name,String date1,String date2) {
		String sql = "SELECT COUNT(*) FROM CK_INSPECTRECORD "
				+ "WHERE (CHKPERSON1 = (SELECT id from EL_TROOPEMP WHERE name = ? ) "
				+ "OR CHKPERSON2 = (SELECT id FROM EL_TROOPEMP WHERE name = ? ) "
				+ "OR CHKPERSON3 = (SELECT id FROM EL_TROOPEMP WHERE name = ? ) )"
				+ "AND STARTTIME between to_date('"+date1+"','yyyy-MM') "
				+ "and to_date('"+date2+"','yyyy-MM')";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, name).setParameter(1, name).setParameter(2, name).list();
//		System.out.println(sql);
//		System.out.println(list);
		return list;
	}

	/**
	 * 每月执法次数
	 */
	@Override
	public List<?> monthEnforcementCount(String name,String date1,String date2) {
		String sql = "SELECT COUNT(*) FROM CK_INSPECTRECORD "
				+ "WHERE (CHKPERSON1 = (SELECT USERID from SYS_USER WHERE USERNAME = ? ) "
				+ "OR CHKPERSON2 = (SELECT USERID from SYS_USER WHERE USERNAME = ? ) "
				+ "OR CHKPERSON3 = (SELECT USERID from SYS_USER WHERE USERNAME = ? ) )"
				+ "AND ENDTIME between to_date('"+date1+"','yyyy-MM') "
				+ "and to_date('"+date2+"','yyyy-MM')";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, name).setParameter(1, name).setParameter(2, name).list();
//		System.out.println(sql);
//		System.out.println(list);
		return list;
	}
	
	/**
	 * 执法企业分类
	 */
	@Override
	public List<?> enforcementEnterprise(String name) {
		String sql = "SELECT COUNT(*),SY.DATA1 FROM CK_INSPECTRECORD ck,SM_ENTERPRISE sm,SYS_DICTTREEDATA sy"
				+ " WHERE (CHKPERSON1 = (SELECT id from EL_TROOPEMP WHERE name =? ) "
				+ "OR CHKPERSON2 = (SELECT id FROM EL_TROOPEMP WHERE name = ? ) "
				+ "OR CHKPERSON3 = (SELECT id FROM EL_TROOPEMP WHERE name = ?))"
				+ " AND CK.ENTID = SM.ENTERPRISEID"
				+ " AND SM.INDUSTRY1 = SY.CODE "
				+ "AND SY.TREEID='EnterpriseIndustryType'"
				+ " GROUP BY SY.DATA1 "
				+ "ORDER BY COUNT(*)";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, name).setParameter(1, name).setParameter(2, name).list();
		return list;
	}
}
