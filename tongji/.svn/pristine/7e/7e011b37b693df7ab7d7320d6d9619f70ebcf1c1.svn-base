package com.henghao.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.henghao.dao.IWZPEnterpriseDao;
import com.henghao.vo.VoEnterprise;
import com.henghao.vo.ZFTimeVo;

/**
 * dao实现类
 * @author 王章鹏
 * @time 2017-4-13
 */
@Repository("enterpriseDao")
public class WZPEnterpriseDaoImpl  implements IWZPEnterpriseDao {

	//session工厂
	@Resource
	private SessionFactory sessionFactory;
	//统一返回map
	Map<String, Object> map_wzp = new HashMap<String, Object>();
	
	
	/**
	 * 统计所有信息
	 * @author 王章鹏
	 * @time 2017-4-13
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<VoEnterprise> getEnterpriseList() throws Exception {

		//交通运输、仓储和邮政业
		//企业分类
		//SELECT DISTINCT INDUSTRY1STR FROM V_GIS_ENTERPRISE WHERE INDUSTRY1STR IS NOT NULL
		String sql = "SELECT s.ENTERPRISEID,s.ENTNAME,NVL(v.INDUSTRY1STR,'未分类') AS TYPENAME,"
				+ "s.LONGITUDE,s.LATITUDE,s.REGADDRESS,h.FINDHIDDENSTYLE,h.HIDDENLEVEL,"
				+ "nvl(h.HIDDENDES,'无描述信息') AS HIDDENDES "
				+ "FROM HT_ACCIDENTHIDDEN h,SM_ENTERPRISE s,V_GIS_ENTERPRISE v "
				+ "WHERE h.ENTERPRISEID=s.ENTERPRISEID AND v.ENTERPRISEID=s.ENTERPRISEID "
				+ "AND (s.longitude IS NOT NULL AND s.latitude IS NOT NULL)";

		List<VoEnterprise> list = sessionFactory
				.getCurrentSession()
				.createSQLQuery(sql)
				.setResultTransformer(
						Transformers.aliasToBean(VoEnterprise.class)).list();
		return list;
	}

	/**
	 * 企业统计，包括共有企业数和上报企业数
	 * @author 王章鹏
	 * @time 2017-4-13
	 */
	@Override
	public Map<String, Object> EnterpriseCount() throws Exception {
		
		String sql_all = "SELECT COUNT(*) num_all FROM sm_enterprise";
		String sql_report = "SELECT COUNT(*) num_report FROM cr_checkmonthreport c,sm_enterprise s WHERE c.isreport='1' AND s.enterpriseid=c.enterpriseid";
		//所有企业
		Integer allEnterprise = (Integer) sessionFactory.getCurrentSession().createSQLQuery(sql_all).addScalar("NUM_ALL", StandardBasicTypes.INTEGER).uniqueResult();
		//上报企业
		Integer reportEnterprise = (Integer) sessionFactory.getCurrentSession().createSQLQuery(sql_report).addScalar("NUM_REPORT", StandardBasicTypes.INTEGER).uniqueResult();
		map_wzp.put("allEnterprise", allEnterprise);
		map_wzp.put("reportEnterprise", reportEnterprise);
		return map_wzp;
	}

	/* 执行sql返回int
	 * @see com.henghao.dao.IWZPEnterpriseDao#getCount(java.lang.String)
	 */
	@Override
	public int getCount(String sql,String countName) {
		String string = sessionFactory.getCurrentSession().createSQLQuery(sql).addScalar(countName, StandardBasicTypes.INTEGER).uniqueResult().toString();
		if (string==null || "".equals(string)) {
			return 0;
		}
		return Integer.parseInt(string);
		
	}

	// 返回包装类
	@SuppressWarnings("unchecked")
	public List<Object> getObjVo(String sql, Class<? extends Object> cls) throws Exception {
		List<Object> list = sessionFactory.getCurrentSession()
				.createSQLQuery(sql)
				.setResultTransformer(Transformers.aliasToBean(cls))
				.list();
		return list;
	}

	// 查询ZFTimeVo 集合
	@SuppressWarnings("unchecked")
	public List<ZFTimeVo> getZFTimeVoList(String sql) throws Exception {
		// TODO Auto-generated method stub
		
		List<ZFTimeVo> list = sessionFactory.getCurrentSession()
				.createSQLQuery(sql)
				.setResultTransformer(Transformers.aliasToBean(ZFTimeVo.class))
				.list();
		return list;
	}


}
