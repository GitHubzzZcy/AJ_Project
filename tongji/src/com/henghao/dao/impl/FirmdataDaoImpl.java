package com.henghao.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.henghao.dao.IFirmdataDao;
import com.henghao.entity.Enterprise;
import com.henghao.entity.Troop;
import com.henghao.entity.Troopemp;

@Repository("firmdataDao")
public class FirmdataDaoImpl implements IFirmdataDao {
	
	@Resource
	private SessionFactory sessionFactory;
	private Map<String, Object> map = new HashMap<String, Object>();

	@Override
	public Map<String, Object> queryFirmdata(int page, int size) {
		String sqlcount = "SELECT COUNT(*) FROM SM_ENTERPRISE";
		int total = Integer.parseInt(sessionFactory.getCurrentSession().createSQLQuery(sqlcount).uniqueResult().toString());
		if(total < size){
			size = total;
		}
		if((page-1)*size >= total){
			page = page-1;
		}
		String sql = "SELECT ENTERPRISEID, ENTNAME, REGNUM, DISTRICT1, DISTRICT2, DISTRICT3, DISTRICT4, HASCOMPANY, PCOMPANYREGCODE,"+ 
					"INDUSTRY1, INDUSTRY2, DEPARTMENT, RELEVEL, ENTERPRISECODE, STATE, CREATETIME, CHECKTIME, CHECKER, SCALE, REGADDRESS,"+
					"REGTIME, EMPLOYEENUM, LONGITUDE, LATITUDE, BUSINESSCOPE, POST, LEGALPEOPLE, LEGALMOBILEPHONE, LINKMAN, LINKMOBILEPHONE,"+ 
					"PRODUCTADDRESS, SCZCH, OFFICEADDRESS, ZCZB, NYYSR, QYZCLX, SCJYCSMJ, ZZJGT, DWPMT, SUPERVISECLASSIFY, ISABOVE, SDORGAN, UPDATETIME  FROM " +
					"(SELECT A.*, ROWNUM RN  FROM (SELECT * FROM SM_ENTERPRISE) A  ) WHERE RN BETWEEN ? AND ?";
		@SuppressWarnings("unchecked")
		List<Enterprise> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, (page-1)*size + 1).setParameter(1, page*size).setResultTransformer(Transformers.aliasToBean(Enterprise.class)).list();
		map.put("total", total);
		map.put("list", list);
		return map;
	}

	@Override
	public String queryTerrCode(String code, String TREEID) {
		try {
			if(code != null) {
				String sql = "SELECT DATA1 from SYS_DICTTREEDATA WHERE CODE=? AND TREEID=?";
				Object result = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, code).setParameter(1, TREEID).uniqueResult();
				if(result != null) {
					return result.toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//是否有计划
	@Override
	public String queryIsplan(String enterpriseid) {
		try {
			String sql = "SELECT COUNT(*) FROM PLAN_PLANCHECKENT WHERE ENTID=? AND CHECKTIME IS NULL AND ISCOMPLETE <> 2";
			String string = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, enterpriseid).uniqueResult().toString();
			if(!"0".equals(string)) {
				return "有";
			}else{
				return "无";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, Object> queryFirmdataSeek(String firmname, int page,
			int size) {
		String sqlcount = "SELECT COUNT(*) FROM SM_ENTERPRISE WHERE  ENTNAME LIKE '%"+firmname+"%'";
		int total = Integer.parseInt(sessionFactory.getCurrentSession().createSQLQuery(sqlcount).uniqueResult().toString());
		if(total < size){
			size = total;
		}
		if((page-1)*size >= total){
			page = page-1;
		}
		String sql = "SELECT ENTERPRISEID, ENTNAME, REGNUM, DISTRICT1, DISTRICT2, DISTRICT3, DISTRICT4, HASCOMPANY, PCOMPANYREGCODE,"+ 
					"INDUSTRY1, INDUSTRY2, DEPARTMENT, RELEVEL, ENTERPRISECODE, STATE, CREATETIME, CHECKTIME, CHECKER, SCALE, REGADDRESS,"+
					"REGTIME, EMPLOYEENUM, LONGITUDE, LATITUDE, BUSINESSCOPE, POST, LEGALPEOPLE, LEGALMOBILEPHONE, LINKMAN, LINKMOBILEPHONE,"+ 
					"PRODUCTADDRESS, SCZCH, OFFICEADDRESS, ZCZB, NYYSR, QYZCLX, SCJYCSMJ, ZZJGT, DWPMT, SUPERVISECLASSIFY, ISABOVE, SDORGAN, UPDATETIME  FROM " +
					"(SELECT A.*, ROWNUM RN  FROM (SELECT * FROM SM_ENTERPRISE WHERE ENTNAME LIKE '%"+firmname+"%') A  ) WHERE RN BETWEEN ? AND ?";
		@SuppressWarnings("unchecked")
		List<Enterprise> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, (page-1)*size + 1).setParameter(1, page*size).setResultTransformer(Transformers.aliasToBean(Enterprise.class)).list();
		map.put("total", total);
		map.put("list", list);
		return map;
	}
	//查询执法队伍人员
	@SuppressWarnings("unchecked")
	@Override
	public List<Troopemp> queryTroopemp(String id) {
		String sql = null;
		List<Troopemp> list;
		if("".equals(id) || null == id) {
			sql = "SELECT A.ID,A.NAME,A.CIDNO,A.PHONE,A.ROLE,B.TROOPNAME,C.LOGINID,C.PASSWORD  FROM EL_TROOPEMP A LEFT OUTER JOIN EL_TROOP B ON A.TROOPID=B.ID  LEFT OUTER JOIN SYS_USER C ON A.NAME=C.USERNAME";
			list = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Troopemp.class)).list();
		}else{
			sql = "SELECT A.ID,A.NAME,A.CIDNO,A.PHONE,A.ROLE,B.TROOPNAME,C.LOGINID,C.PASSWORD  FROM EL_TROOPEMP A LEFT OUTER JOIN EL_TROOP B ON A.TROOPID=B.ID LEFT OUTER JOIN SYS_USER C ON A.NAME=C.USERNAME WHERE A.TROOPID=?";
			list = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, id).setResultTransformer(Transformers.aliasToBean(Troopemp.class)).list();
			
		}
		return list;
	}

	//查询执法队伍
	@Override
	public List<Troop> queryTroop() {
		String sql = "SELECT ID,TROOPNAME FROM EL_TROOP";
		@SuppressWarnings("unchecked")
		List<Troop> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Troop.class)).list();
		return list;
	}
	//根据id查询企业
	@Override
	public Enterprise findFirmById(String enterpriseid) {
		String sql = "SELECT ENTERPRISEID, ENTNAME, REGNUM, DISTRICT1, DISTRICT2, DISTRICT3, DISTRICT4, HASCOMPANY, PCOMPANYREGCODE,"+ 
				"INDUSTRY1, INDUSTRY2, DEPARTMENT, RELEVEL, ENTERPRISECODE, STATE, CREATETIME, CHECKTIME, CHECKER, SCALE, REGADDRESS,"+
				"REGTIME, EMPLOYEENUM, LONGITUDE, LATITUDE, BUSINESSCOPE, POST, LEGALPEOPLE, LEGALMOBILEPHONE, LINKMAN, LINKMOBILEPHONE,"+ 
				"PRODUCTADDRESS, SCZCH, OFFICEADDRESS, ZCZB, NYYSR, QYZCLX, SCJYCSMJ, ZZJGT, DWPMT, SUPERVISECLASSIFY, ISABOVE, SDORGAN, UPDATETIME  FROM "+
				"SM_ENTERPRISE WHERE ENTERPRISEID = ?";
		Enterprise result = (Enterprise) sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, enterpriseid).setResultTransformer(Transformers.aliasToBean(Enterprise.class)).uniqueResult();
		return result;
	}

}
