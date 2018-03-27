package com.henghao.dao.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.henghao.dao.IInspDao;
import com.henghao.entity.Accident;
import com.henghao.entity.ExamineFirm;
import com.henghao.entity.Inspect;
import com.henghao.entity.Plan;
import com.henghao.entity.Roll;
import com.henghao.util.DateUtils;
@Repository("inspDao")
public class InspDaoImpl implements IInspDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public String getUserId(String name) {
		String sql = "SELECT USERID FROM SYS_USER WHERE USERNAME=?";
		Object result = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, name).uniqueResult();
		if(null != result) {
			return result.toString();
		}
		return null;
	}
	
	@Override
	public String getUserName(String userid) {
		String sql = "SELECT USERNAME FROM SYS_USER WHERE USERID=?";
		String username = null;
		try {
			username = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, userid).uniqueResult().toString();
		} catch (NullPointerException e) {
			return null;
		}
		return username;
	}
	@Override
	public String queryCount(String start, String ent) {
		String sql = "SELECT COUNT(*) FROM CK_INSPECTRECORD WHERE ENDTIME BETWEEN TO_DATE (?, 'yyyy') AND TO_DATE (?, 'yyyy')";
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, start).setParameter(1, ent).uniqueResult().toString();
		return count;//2017-5-24上午11:04:23宁万龙
	}

	@Override
	public String querymm(String start, String ent) {
		String sql = "SELECT COUNT(*) FROM CK_INSPECTRECORD WHERE ENDTIME BETWEEN TO_DATE (?, 'yyyy-MM') AND TO_DATE (?, 'yyyy-MM')";
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, start).setParameter(1, ent).uniqueResult().toString();
		return count;//2017-5-24上午11:04:23宁万龙
	}

	@Override
	public String queryDay(String yymd) {
		String sql = "SELECT COUNT(*) FROM CK_INSPECTRECORD WHERE ENDTIME BETWEEN TO_DATE (?, 'yyyy-MM-dd') AND TO_DATE (?, 'yyyy-MM-dd')";
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, yymd).setParameter(1, yymd).uniqueResult().toString();
		return count;//2017-5-24上午11:04:23宁万龙
	}

	@Override
	public String querychao() {
		String sql = "SELECT COUNT(*) FROM PLAN_PLANCHECKENT A WHERE A.PLANTIME < A.CHECKTIME";
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql).uniqueResult().toString();
		return count;//2017-5-24上午11:04:23宁万龙
	}

	//未完成的
	//在计划时间内没有去完成就属于未完成
	@Override
	public String queryundone() {
		String formatedDate = DateUtils.getFormatedDate(new Date(), "yyyy-MM-dd HH:mm:ss");
		String sql = "SELECT COUNT(*) FROM PLAN_PLANCHECKENT A WHERE  A.PLANTIME < TO_DATE(?, 'yyyy-MM-dd HH24:mi:ss') AND A.CHECKTIME IS NULL";
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, formatedDate).uniqueResult().toString();
		return count;//2017-5-24上午11:04:23宁万龙
		
	}

	@Override
	public String eachMonth(String userid, String start, String end) {
		String sql = "SELECT COUNT(*) FROM CK_INSPECTRECORD WHERE ENDTIME BETWEEN TO_DATE (?, 'yyyy-MM') AND TO_DATE (?, 'yyyy-MM') AND (CHKPERSON1=? OR CHKPERSON2=? OR CHKPERSON3=?)";
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, start).setParameter(1, end).setParameter(2, userid).setParameter(3, userid).setParameter(4, userid).uniqueResult().toString();
		return count;
	}

	//月下令整改数
		@Override
		public String monthOrder(String userid, String start, String end,
				String code) {
			String sql = "SELECT COUNT(*) FROM CK_INSPECTRECORD WHERE ENDTIME BETWEEN TO_DATE (?, 'yyyy-MM') AND TO_DATE (?, 'yyyy-MM') AND (CHKPERSON1=? OR CHKPERSON2=? OR CHKPERSON3=?) AND HANDLETYPE=?";
			String count = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, start).setParameter(1, end).setParameter(2, userid).setParameter(3, userid).setParameter(4, userid).setParameter(5, code).uniqueResult().toString();
			return count;
		}
	@Override
	public String eachQuarter(String string, String name, String stardate,
			String enddate) {
		String sql = "SELECT COUNT(*) FROM HT_ACCIDENTHIDDEN WHERE HIDDENTIME BETWEEN TO_DATE (?, 'yyyy-MM-dd') AND TO_DATE (?, 'yyyy-MM-dd') AND HIDDENLEVEL=? AND FINDHIDDENPERSON LIKE ?";
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, stardate).setParameter(1, enddate).setParameter(2, string).setParameter(3, "%"+name+"%").uniqueResult().toString();
		return count;
	}

	@Override
	public String eachClassify(String userid, String code, String startdate,
			String currentDate) {
		String sql = null;
		String count = "0";
		if(code.equals("WFL")) {
			sql = "SELECT COUNT(*) FROM CK_INSPECTRECORD A LEFT OUTER JOIN SM_ENTERPRISE B ON A.ENTID=B.ENTERPRISEID WHERE B.INDUSTRY1 <> 'A' AND B.INDUSTRY1 <> 'B' AND B.INDUSTRY1 <> 'C'  AND B.INDUSTRY1 <> 'E'  AND B.INDUSTRY1 <> 'F'  AND B.INDUSTRY1 <> 'G'  AND B.INDUSTRY1 <> 'H'  AND B.INDUSTRY1 <> 'R'  AND B.INDUSTRY1 <> 'M' AND (CHKPERSON1=? OR CHKPERSON2=? OR CHKPERSON3=?) AND A.ENDTIME BETWEEN TO_DATE (?,'yyyy-MM-dd') AND TO_DATE (?, 'yyyy-MM-dd')";
			count = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, userid).setParameter(1, userid).setParameter(2, userid).setParameter(3, startdate).setParameter(4, currentDate).uniqueResult().toString();
		}else{
			sql = "SELECT COUNT(*) FROM CK_INSPECTRECORD A LEFT OUTER JOIN SM_ENTERPRISE B ON A.ENTID=B.ENTERPRISEID WHERE B.INDUSTRY1=? AND (CHKPERSON1=? OR CHKPERSON2=? OR CHKPERSON3=?) AND A.ENDTIME BETWEEN TO_DATE (?,'yyyy-MM-dd') AND TO_DATE (?, 'yyyy-MM-dd')";
			count = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, code).setParameter(1, userid).setParameter(2, userid).setParameter(3, userid).setParameter(4, startdate).setParameter(5, currentDate).uniqueResult().toString();
		}
		return count;
	}
	@Override
	public List<ExamineFirm> eachLately(String userId, String startdate,
			String enddate) {
		String sql ="SELECT B.ENTNAME,B.LONGITUDE,B.LATITUDE,C.DATA1,D.DATA1 AS DISTRICT3 FROM SM_ENTERPRISE B LEFT OUTER JOIN CK_INSPECTRECORD A ON B.ENTERPRISEID=A.ENTID  JOIN SYS_DICTTREEDATA C ON B.INDUSTRY1=C.CODE JOIN SYS_DICTTREEDATA D ON B.DISTRICT3=D.CODE   WHERE  ( C.TREEID='EnterpriseIndustryType' OR C.TREEID='CityDistrict' ) AND " +
				"(A.CHKPERSON1=? OR A.CHKPERSON2=? OR A.CHKPERSON3=?) " +
				"AND A.ENDTIME BETWEEN TO_DATE (?, 'yyyy-MM-dd') AND TO_DATE (?, 'yyyy-MM-dd')";
		@SuppressWarnings("unchecked")
		List<ExamineFirm> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(ExamineFirm.class))
		.setParameter(0, userId).setParameter(1, userId).setParameter(2, userId).setParameter(3, startdate).setParameter(4, enddate).list();
		return list;
	}
	@Override
	public List<Inspect> inspectOA(String userId, String starttime, String currentDate) {
		String sql = "SELECT   A.ID, B.ENTNAME, A.SOURCETYPE, A.SIGNINFO, A.ENTPERSON1, A.ENTPERSON2, A.ENTLINKPHONE, A.CHECKPLACE, A.CHKPERSON1, A.CHKPERSON2, A.CHKPERSON3, A.STARTTIME, A.ENDTIME, A.CHECKINFO, A.HASHIDDEN, A.CORRECTINFO, A.RECORDTIME, A.HANDLETYPE " +
				"FROM CK_INSPECTRECORD A LEFT  OUTER JOIN SM_ENTERPRISE B ON B.ENTERPRISEID=A.ENTID " +
				"WHERE (A.CHKPERSON1=? OR A.CHKPERSON2=? OR A.CHKPERSON3=?) AND A.ENDTIME BETWEEN TO_DATE (?, 'yyyy-MM-dd') AND TO_DATE (?, 'yyyy-MM-dd')  " +
				"GROUP BY B.ENTNAME,A.ENTID,A.ID,A.SOURCETYPE, A.SIGNINFO, A.ENTPERSON1, A.ENTPERSON2, A.ENTLINKPHONE, A.CHECKPLACE, A.CHKPERSON1, A.CHKPERSON2, A.CHKPERSON3, A.STARTTIME, A.ENDTIME, A.CHECKINFO, A.HASHIDDEN, A.CORRECTINFO, A.RECORDTIME, A.HANDLETYPE " +
				"ORDER BY B.ENTNAME";
		@SuppressWarnings("unchecked")
		List<Inspect> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Inspect.class))
		.setParameter(0, userId).setParameter(1, userId).setParameter(2, userId).setParameter(3, starttime).setParameter(4, currentDate).list();
		return list;
	}
	@Override
	public Accident queryAccident(String id) {
		String sql = "SELECT JID FROM EL_TESTIMONY WHERE INSPECTID=? AND JTYPE=2";
		Object result = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, id).uniqueResult();
		if(null == result) {
			return null;
		}else{
			String hiddenid = result.toString();
			String hql = "SELECT HIDDENID, ENTERPRISEID, FINDHIDDENSTYLE, SOURCEID, HIDDENBIGTYPE, HIDDENSMALLTYPE, LINEMINISTRY, SPECIALMANAGEDEPART, BUSINESSPLACE, LOCATION, DETAILADDRESS, HIDDENPART, HIDDENLEVEL, HIDDENDES, HIDDENREASON, HIDDENSCOPE, HIDDENTIME, FINDHIDDENPERSON, HIDDENPIC, DEALREQUEST, DEALSTYLE,  DEALTYPE, DEALVALIDATE,  DEALCONDITIONCODE, DEALCONDITIONDES, DEALORGAN, DEALPERSON, LINKPHONE, DEALFINISHTIME, DEALFINISHPIC, CHECKTIME, CHECKORGAN, CHECKPERSON, CHECKCONDITION, DISTRICT2, DISTRICT1, DISTRICT3, DISTRICT4, DISTRICT5,  ISCONFIRM, CONFIRMTIME, CONFIRMPERSON, CONFIRMORGAN, HIDDENREGISTER, ENTNAME, STATE FROM HT_ACCIDENTHIDDEN WHERE HIDDENID=?";
			Accident accident = (Accident) sessionFactory.getCurrentSession().createSQLQuery(hql).setResultTransformer(Transformers.aliasToBean(Accident.class)).setParameter(0, hiddenid).uniqueResult();
			return accident;
		}
	}

	@Override
	public List<Roll> queryroll(String userId, String start, String end) {
		String sql = "SELECT B.ENTNAME,A.STARTTIME,A.ENDTIME FROM CK_INSPECTRECORD A LEFT OUTER JOIN SM_ENTERPRISE B ON A.ENTID=B.ENTERPRISEID WHERE CHKPERSON1=? OR CHKPERSON2=? OR CHKPERSON3=?  AND ENDTIME BETWEEN TO_DATE (?, 'yyyy-MM-dd') AND TO_DATE (?, 'yyyy-MM-dd')";
		@SuppressWarnings("unchecked")
		List<Roll> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Roll.class)).
		setParameter(0, userId).setParameter(1, userId).setParameter(2, userId).setParameter(3, start).setParameter(4, end).list();
		return list;
	}

	@Override
	public List<Accident> queryAllAccident() {
		String sql = "SELECT HIDDENID,DEALVALIDATE,ENTERPRISEID FROM HT_ACCIDENTHIDDEN WHERE (ENTERPRISEID,HIDDENID) IN (" +
						"SELECT  ENTERPRISEID,MAX(HIDDENID) FROM HT_ACCIDENTHIDDEN " +
						"WHERE DEALFINISHTIME IS NULL GROUP BY ENTERPRISEID ) ";
		@SuppressWarnings("unchecked")
		List<Accident> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Accident.class)).list();
		return list;
	}



	@Override
	public ExamineFirm queryEntname(String enterpriseid) {
		String sql = "SELECT ENTNAME,LEGALMOBILEPHONE,REGADDRESS FROM SM_ENTERPRISE WHERE ENTERPRISEID=?";
		ExamineFirm result = (ExamineFirm) sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(ExamineFirm.class)).setParameter(0, enterpriseid).uniqueResult();
		if(null != result) {
			return result;
		}
		return null;
	}

	//待执法//查询计划表
	@Override
	public String queryDai(String userid) {
		String currentDate = DateUtils.getCurrentDate("yyyy") + "-01-01";
		String sql = "SELECT COUNT(*) FROM PLAN_PLANCHECKENT WHERE PLANTIME > TO_DATE(?, 'yyyy-MM-dd') AND ISCOMPLETE=0 AND (PERSON1=? OR PERSON2=? OR PERSON3=? OR PERSON4=?)";
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, currentDate).setParameter(1, userid).setParameter(2, userid).setParameter(3, userid).setParameter(4, userid).uniqueResult().toString();
		return count;
	}

	//正常执法
	@Override
	public String queryZc(String userId) {
		String currentDate = DateUtils.getCurrentDate("yyyy") + "-01-01";
		String sql = "SELECT COUNT(*) FROM CK_INSPECTRECORD WHERE ENDTIME > TO_DATE(?, 'yyyy-MM-dd') AND (CHKPERSON1=? OR CHKPERSON2=? OR CHKPERSON3=?)";
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, currentDate).setParameter(1, userId).setParameter(2, userId).setParameter(3, userId).uniqueResult().toString();
		return count;
	}

	@Override
	public String queryCq(String userId) {
		String sql = "SELECT COUNT(*) FROM PLAN_PLANCHECKENT WHERE CHECKTIME > PLANTIME AND STATUS=1 AND (PERSON1=? OR PERSON2=? OR PERSON3=? OR PERSON4=? )";
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, userId).setParameter(1, userId).setParameter(2, userId).setParameter(3, userId).uniqueResult().toString();
		return count;
	}

	//短信查询计划表
	@Override
	public List<Plan> queryPlan() {
		String sql = "SELECT ID,PLANID,ENTID,PERSON1,PERSON2,PERSON3,PERSON4,PLANTIME,CHECKTIME FROM PLAN_PLANCHECKENT WHERE STATUS=1 AND ISCOMPLETE=0";
		@SuppressWarnings("unchecked")
		List<Plan> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Plan.class)).list();
		return list;
	}
	
	

	


}
