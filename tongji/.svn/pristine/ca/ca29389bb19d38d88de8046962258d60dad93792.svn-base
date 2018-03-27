/**
 * 
 */
package com.henghao.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.henghao.dao.EnteriseDao;
import com.henghao.entity.Enterise;
import com.henghao.util.DateUtils;
import com.henghao.util.PageBaen;

/**2017-3-30,上午10:25:42EnteriseDaoImpl.java
 * @author Nwl
 *
 */
@Repository("endao")
public class EnteriseDaoImpl extends BaseDao<Enterise> implements EnteriseDao {

	//统一返回map   王章鹏
	Map<String, Object> map_wzp = new HashMap<String, Object>();

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> findByQy() {
		Map<String, Object> map=new HashMap<String, Object>();
		String sql="SELECT cc.citydistrictname as typeName ,tt.nums as numbers FROM (select t.District3, COUNT(*) nums from SM_ENTERPRISE t group by t.district3) tt,(select c.organcode,c.CityDistrictname from V_GIS_SYS_ORGANS c where c.CityDistrictname is not null) cc where tt.District3=cc.organcode";		
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql).list();
		map.put("findByQy", list);
		return map;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> findByJglx() {
		Map<String, Object> map=new HashMap<String, Object>();
		String sql="select NVL(c.INDUSTRY1STR,'未分类') as typeName,count(0) as numbers from V_GIS_ENTERPRISE c group by c.INDUSTRY1STR";
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql).list();
		map.put("findByJglx", list);
		return map;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> findByJgbm() {
		Map<String, Object> map=new HashMap<String, Object>();
		String sql="select t1.data1 as typeName,nvl(t2.num,0) as numbers from (select DEPTNAME,count(*) num from V_GIS_ENTERPRISE group by deptname)" +
				" t2 right join (select DATA1 from SYS_DICTTREEDATA where DATA1 like '%部门') t1 on t1.data1=t2.DEPTNAME";
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql).list();
		map.put("findByJgbm", list);
		return map;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> findByJgjb() {
		Map<String, Object> map=new HashMap<String, Object>();
		String sql="select NVL(t.industry1,'未定级企业') as typeName,count(0) as numbers from CG_CLASSFIYANDENT t group by t.industry1";
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql).list();
		map.put("findByJgjb", list);
		return map;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> findYhByCondition(String condition) {
		Map<String, Object> map=new HashMap<String, Object>();
		String stardate=DateUtils.getStardate(condition);
		String enddate=DateUtils.getEnddate(condition);
		String sql="select NVL(vso.CityDistrictname,'其他地区') as diqu,NVL(yh.bignum,0) as bignum,NVL(yh.bignonum,0) as bignonum,NVL(yh.ybnum,0) as ybnum,NVL(yh.ybnonum,0) as ybnonum from " +
				"(select * from (select zdn.district3 as zdndistrict3,zdn.bignum, zdnn.bignonum from " +
				"(select NVL(t.district3,0) district3,count(*) bignum from HT_ACCIDENTHIDDEN t where t.hiddenlevel=2 and (t.hiddentime between TO_DATE(?,'YYYY-MM-DD') AND TO_DATE(?,'YYYY-MM-DD')) group by t.district3) zdn," +
				"(select NVL(t.district3,0) district3,count(*) bignonum from HT_ACCIDENTHIDDEN t where t.dealconditioncode=1 and t.hiddenlevel=2 and (t.hiddentime between TO_DATE(?,'YYYY-MM-DD') AND TO_DATE(?,'YYYY-MM-DD')) group by t.district3) zdnn where zdn.district3=zdnn.district3(+)) big " +
				"full join (select ybn.district3 as ybndistrict3,ybn.ybnum, ybnn.ybnonum from " +
				"(select NVL(t.district3,0) district3,count(*) ybnum from HT_ACCIDENTHIDDEN t where t.hiddenlevel=1 and (t.hiddentime between TO_DATE(?,'YYYY-MM-DD') AND TO_DATE(?,'YYYY-MM-DD')) group by t.district3) ybn," +
				"(select NVL(t.district3,0) district3,count(*) ybnonum from HT_ACCIDENTHIDDEN t where t.dealconditioncode=1 and t.hiddenlevel=1 and (t.hiddentime between TO_DATE(?,'YYYY-MM-DD') AND TO_DATE(?,'YYYY-MM-DD')) group by t.district3) ybnn where ybn.district3=ybnn.district3(+)) yb " +
				"on big.zdndistrict3 = yb.ybndistrict3) yh left join V_GIS_SYS_ORGANS vso on yh.ybndistrict3=vso.organcode";
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql).setParameter(0, stardate).setParameter(1, enddate).setParameter(2, stardate).setParameter(3, enddate).setParameter(4, stardate).setParameter(5, enddate).setParameter(6, stardate).setParameter(7, enddate).list();
		map.put("findByJgjb", list);
		return map;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> findYhByBmCondttion(String condition) {
		String sql="SELECT TONGJI.DEPTNAME || BUMEN.TYPENAME, NVL (TONGJI.BIGNUMS, 0), NVL (TONGJI.BIGNNOTUMS, 0), NVL (TONGJI.YBNUMS, 0)," +
				" NVL (TONGJI.YBNNOTUMS, 0) FROM ( SELECT YBNUM.DEPTNAME, BIGNUM.BIGNUMS, BIGNUM.BIGNNOTUMS, YBNUM.YBNUMS, " +
				"YBNUM.YBNNOTUMS FROM ( SELECT ha0101.DEPTNAME, ha0101.BIGNUMS, ha0202.BIGNNOTUMS FROM ( SELECT NVL( ha01.DEPTNAME, '其他部门' ) " +
				"AS DEPTNAME, COUNT (1) AS bignums FROM ( SELECT ha01.ENTERPRISEID AS ha01enid, ha01.HIDDENLEVEL AS ha01hlevel," +
				" ha01.DEALCONDITIONCODE AS ha01scode, vgen01.DEPTNAME FROM HT_ACCIDENTHIDDEN ha01 LEFT OUTER JOIN V_GIS_ENTERPRISE vgen01 " +
				"ON ha01.ENTERPRISEID = vgen01.ENTERPRISEID WHERE ha01.HIDDENTIME BETWEEN TO_DATE (?, 'YYYY-MM-DD') AND " +
				"TO_DATE (?, 'YYYY-MM-DD')) ha01 WHERE ha01.ha01hlevel = 2 GROUP BY ha01.DEPTNAME ) ha0101 LEFT OUTER JOIN " +
				"( SELECT NVL( ha02.DEPTNAME, '其他部门' ) AS DEPTNAME, COUNT (1) AS bignnotums FROM " +
				"( SELECT ha02.ENTERPRISEID AS ha02enid, ha02.HIDDENLEVEL AS ha02hlevel, ha02.DEALCONDITIONCODE AS ha02scode," +
				" vgen02.DEPTNAME FROM HT_ACCIDENTHIDDEN ha02 LEFT OUTER JOIN V_GIS_ENTERPRISE vgen02 ON " +
				"ha02.ENTERPRISEID = vgen02.ENTERPRISEID WHERE ha02.HIDDENTIME BETWEEN TO_DATE (?, 'YYYY-MM-DD') " +
				"AND TO_DATE (?, 'YYYY-MM-DD')) ha02 WHERE ha02.ha02hlevel = 2 AND ha02.ha02scode = 1 GROUP BY ha02.DEPTNAME ) " +
				"ha0202 ON ha0101.DEPTNAME = HA0202.DEPTNAME ) bignum FULL JOIN ( SELECT ha0101.DEPTNAME, ha0101.YBNUMS," +
				" ha0202.YBNNOTUMS FROM ( SELECT NVL( ha01.DEPTNAME, '其他部门' ) AS DEPTNAME, COUNT (1) AS ybnums FROM " +
				"( SELECT ha01.ENTERPRISEID AS ha01enid, ha01.HIDDENLEVEL AS ha01hlevel, ha01.DEALCONDITIONCODE AS ha01scode, " +
				"vgen01.DEPTNAME FROM HT_ACCIDENTHIDDEN ha01 LEFT OUTER JOIN V_GIS_ENTERPRISE vgen01 ON ha01.ENTERPRISEID = vgen01.ENTERPRISEID " +
				"WHERE ha01.HIDDENTIME BETWEEN TO_DATE (?, 'YYYY-MM-DD') AND TO_DATE (?, 'YYYY-MM-DD'))" +
				" ha01 WHERE ha01.ha01hlevel = 1 GROUP BY ha01.DEPTNAME ) ha0101 LEFT OUTER JOIN ( SELECT NVL ( ha02.DEPTNAME, '其他部门' ) " +
				"AS DEPTNAME, COUNT (1) AS ybnnotums FROM ( SELECT ha02.ENTERPRISEID AS ha02enid, ha02.HIDDENLEVEL AS ha02hlevel," +
				" ha02.DEALCONDITIONCODE AS ha02scode, vgen02.DEPTNAME FROM HT_ACCIDENTHIDDEN ha02 LEFT OUTER JOIN V_GIS_ENTERPRISE vgen02 ON" +
				" ha02.ENTERPRISEID = vgen02.ENTERPRISEID WHERE ha02.HIDDENTIME BETWEEN TO_DATE (?, 'YYYY-MM-DD') AND " +
				"TO_DATE (?, 'YYYY-MM-DD')) ha02 WHERE ha02.ha02hlevel = 1 AND ha02.ha02scode = 1 GROUP BY ha02.DEPTNAME ) " +
				"ha0202 ON ha0101.DEPTNAME = HA0202.DEPTNAME ) ybnum ON YBNUM.DEPTNAME = BIGNUM.DEPTNAME ) tongji FULL JOIN " +
				"( SELECT t1.data1 AS typeName, NVL (t2.num, 0) AS numbers FROM ( SELECT DEPTNAME, COUNT (*) num FROM V_GIS_ENTERPRISE" +
				" GROUP BY deptname ) t2 RIGHT JOIN ( SELECT DATA1 FROM SYS_DICTTREEDATA WHERE DATA1 LIKE '%部门' ) t1 ON t1.data1 = t2.DEPTNAME ) " +
				"bumen ON BUMEN.TYPENAME = tongji.DEPTNAME";
		Map<String, Object> map=new HashMap<String, Object>();
		String stardate=DateUtils.getStardate(condition);
		String enddate=DateUtils.getEnddate(condition);
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql).setParameter(0, stardate).setParameter(1, enddate).setParameter(2, stardate).setParameter(3, enddate).setParameter(4, stardate).setParameter(5, enddate).setParameter(6, stardate).setParameter(7, enddate).list();
		map.put("findByJgjb", list);
		return map;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> findYhByLxCondttion(String condition) {
		String sql="SELECT YB.INDUSTRY1STR, BIG.BIGNUM, BIG.BIGNONUM, YB.YBNUM, YB.YBNONUM FROM ( SELECT BIGN.INDUSTRY1STR AS INDUSTRY1STR, NVL (BIGN.BIGNUM, 0) AS YBNUM, NVL (BIGNO.BIGNOTNUM, 0) AS YBNONUM FROM ( SELECT VGE.typeName AS INDUSTRY1STR, HA1.BIGNUM AS BIGNUM FROM (( SELECT HA0101.INDUSTRY1STR AS INDUSTRY1STR, COUNT (0) AS BIGNUM FROM ( SELECT ha01.ENTERPRISEID AS ha01enid, ha01.HIDDENLEVEL AS ha01hlevel, ha01.DEALCONDITIONCODE AS ha01scode, NVL ( vgen01.INDUSTRY1STR, '其他' ) AS INDUSTRY1STR FROM HT_ACCIDENTHIDDEN ha01 LEFT OUTER JOIN V_GIS_ENTERPRISE vgen01 ON ha01.ENTERPRISEID = vgen01.ENTERPRISEID WHERE ha01.HIDDENLEVEL = 1 AND ha01.HIDDENTIME BETWEEN TO_DATE (?, 'YYYY-MM-DD') AND TO_DATE (?, 'YYYY-MM-DD')) HA0101 GROUP BY INDUSTRY1STR ) HA1 RIGHT JOIN ( SELECT NVL (c.INDUSTRY1STR, '其他') AS typeName FROM V_GIS_ENTERPRISE c GROUP BY c.INDUSTRY1STR ) VGE ON HA1.INDUSTRY1STR = VGE.typeName )) BIGN FULL JOIN ( SELECT HA0101.INDUSTRY1STR AS INDUSTRY1STR, COUNT (0) AS BIGNOTNUM FROM ( SELECT ha01.ENTERPRISEID AS ha01enid, ha01.HIDDENLEVEL AS ha01hlevel, ha01.DEALCONDITIONCODE AS ha01scode, NVL ( vgen01.INDUSTRY1STR, '其他' ) AS INDUSTRY1STR FROM HT_ACCIDENTHIDDEN ha01 LEFT OUTER JOIN V_GIS_ENTERPRISE vgen01 ON ha01.ENTERPRISEID = vgen01.ENTERPRISEID WHERE ha01.HIDDENLEVEL = 1 AND ha01.DEALCONDITIONCODE = 1 AND ha01.HIDDENTIME BETWEEN TO_DATE (?, 'YYYY-MM-DD') AND TO_DATE (?, 'YYYY-MM-DD')) HA0101 GROUP BY INDUSTRY1STR ) BIGNO ON BIGN.INDUSTRY1STR = BIGNO.INDUSTRY1STR ) YB FULL JOIN ( SELECT BIGN.INDUSTRY1STR AS INDUSTRY1STR, NVL (BIGN.BIGNUM, 0) AS BIGNUM, NVL (BIGNO.BIGNOTNUM, 0) AS BIGNONUM FROM ( SELECT VGE.typeName AS INDUSTRY1STR, HA1.BIGNUM AS BIGNUM FROM (( SELECT HA0101.INDUSTRY1STR AS INDUSTRY1STR, COUNT (0) AS BIGNUM FROM ( SELECT ha01.ENTERPRISEID AS ha01enid, ha01.HIDDENLEVEL AS ha01hlevel, ha01.DEALCONDITIONCODE AS ha01scode, NVL ( vgen01.INDUSTRY1STR, '其他' ) AS INDUSTRY1STR FROM HT_ACCIDENTHIDDEN ha01 LEFT OUTER JOIN V_GIS_ENTERPRISE vgen01 ON ha01.ENTERPRISEID = vgen01.ENTERPRISEID WHERE ha01.HIDDENLEVEL = 2 AND ha01.HIDDENTIME BETWEEN TO_DATE (?, 'YYYY-MM-DD') AND TO_DATE (?, 'YYYY-MM-DD')) HA0101 GROUP BY INDUSTRY1STR ) HA1 RIGHT JOIN ( SELECT NVL (c.INDUSTRY1STR, '其他') AS typeName FROM V_GIS_ENTERPRISE c GROUP BY c.INDUSTRY1STR ) VGE ON HA1.INDUSTRY1STR = VGE.typeName )) BIGN FULL JOIN ( SELECT HA0101.INDUSTRY1STR AS INDUSTRY1STR, COUNT (0) AS BIGNOTNUM FROM ( SELECT ha01.ENTERPRISEID AS ha01enid, ha01.HIDDENLEVEL AS ha01hlevel, ha01.DEALCONDITIONCODE AS ha01scode, NVL ( vgen01.INDUSTRY1STR, '其他' ) AS INDUSTRY1STR FROM HT_ACCIDENTHIDDEN ha01 LEFT OUTER JOIN V_GIS_ENTERPRISE vgen01 ON ha01.ENTERPRISEID = vgen01.ENTERPRISEID WHERE ha01.HIDDENLEVEL = 2 AND ha01.DEALCONDITIONCODE = 1 AND ha01.HIDDENTIME BETWEEN TO_DATE (?, 'YYYY-MM-DD') AND TO_DATE (?, 'YYYY-MM-DD')) HA0101 GROUP BY INDUSTRY1STR ) BIGNO ON BIGN.INDUSTRY1STR = BIGNO.INDUSTRY1STR ) BIG ON BIG.INDUSTRY1STR = YB.INDUSTRY1STR";
		Map<String, Object> map=new HashMap<String, Object>();
		String stardate=DateUtils.getStardate(condition);
		String enddate=DateUtils.getEnddate(condition);
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql).setParameter(0, stardate).setParameter(1, enddate).setParameter(2, stardate).setParameter(3, enddate).setParameter(4, stardate).setParameter(5, enddate).setParameter(6, stardate).setParameter(7, enddate).list();
		map.put("findByJgjb", list);
		return map;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> findYhByConditionZczb(String condition) {
		String sql="SELECT ZCZB.CITYDISTRICTNAME, NVL (ZCZB.NUMBERS, 0) NUMBERS, NVL (ZCZB.TOTAL, 0) TOTAL, NVL (ZCZB.REPORTTOTAL, 0) REPORTTOTAL, NVL (ZCZB.SAFETYTOTAL, 0) SAFETYTOTAL, NVL (YH.BIGNUM, 0) BIGNUM, NVL (YH.BIGNONUM, 0) BIGNONUM, NVL (YH.YBNUM, 0) YBNUM, NVL (YH.YBNONUM, 0) YBNONUM FROM ( SELECT ZC.CITYDISTRICTNAME, CT.NUMBERS, ZC.TOTAL, ZC.REPORTTOTAL, ZC.SAFETYTOTAL FROM ( SELECT cc.citydistrictname AS typeName, tt.nums AS numbers FROM ( SELECT T .District3, COUNT (*) nums FROM SM_ENTERPRISE T GROUP BY T .district3 ) tt, ( SELECT c.organcode, c.CityDistrictname FROM V_GIS_SYS_ORGANS c WHERE c.CityDistrictname IS NOT NULL ) cc WHERE tt.District3 = cc.organcode ) CT LEFT JOIN ( SELECT NVL (CITYDISTRICTNAME, '其他') AS CITYDISTRICTNAME, NVL (COUNT(*), 0) total, NVL (SUM(ISREPORT), 0) ReportTotal, NVL (SUM(ISSAFETY), 0) SafetyTotal FROM ( SELECT ISSAFETY, ISREPORT, CITYDISTRICTNAME FROM ( SELECT * FROM ( SELECT CR.ENTERPRISEID, CR.ISREPORT, CR.ISSAFETY, SM.District3 FROM CR_CHECKMONTHREPORT CR LEFT JOIN SM_ENTERPRISE SM ON SM.ENTERPRISEID = CR.ENTERPRISEID WHERE CR.ISREPORT = 1 AND CR.REPORTTIME BETWEEN TO_DATE (?, 'YYYY-MM-DD') AND TO_DATE (?, 'YYYY-MM-DD')) one FULL JOIN V_GIS_SYS_ORGANS organs ON organs.organid = one.District3 ) two ) three GROUP BY three.CITYDISTRICTNAME ) ZC ON CT.typeName = ZC.citydistrictname ) ZCZB LEFT OUTER JOIN ( SELECT NVL ( vso.CityDistrictname, '其他地区' ) AS diqu, NVL (yh.bignum, 0) AS bignum, NVL (yh.bignonum, 0) AS bignonum, NVL (yh.ybnum, 0) AS ybnum, NVL (yh.ybnonum, 0) AS ybnonum FROM ( SELECT * FROM ( SELECT zdn.district3 AS zdndistrict3, zdn.bignum, zdnn.bignonum FROM ( SELECT NVL (T .district3, 0) district3, COUNT (*) bignum FROM HT_ACCIDENTHIDDEN T WHERE T .hiddenlevel = 2 AND ( T .HIDDENTIME BETWEEN TO_DATE (?, 'YYYY-MM-DD') AND TO_DATE (?, 'YYYY-MM-DD')) GROUP BY T .district3 ) zdn, ( SELECT NVL (T .district3, 0) district3, COUNT (*) bignonum FROM HT_ACCIDENTHIDDEN T WHERE T .dealconditioncode = 1 AND T .hiddenlevel = 2 AND ( T .HIDDENTIME BETWEEN TO_DATE (?, 'YYYY-MM-DD') AND TO_DATE (?, 'YYYY-MM-DD')) GROUP BY T .district3 ) zdnn WHERE zdn.district3 = zdnn.district3 (+)) big FULL JOIN ( SELECT ybn.district3 AS ybndistrict3, ybn.ybnum, ybnn.ybnonum FROM ( SELECT NVL (T .district3, 0) district3, COUNT (*) ybnum FROM HT_ACCIDENTHIDDEN T WHERE T .hiddenlevel = 1 AND ( T .HIDDENTIME BETWEEN TO_DATE (?, 'YYYY-MM-DD') AND TO_DATE (?, 'YYYY-MM-DD')) GROUP BY T .district3 ) ybn, ( SELECT NVL (T .district3, 0) district3, COUNT (*) ybnonum FROM HT_ACCIDENTHIDDEN T WHERE T .dealconditioncode = 1 AND T .hiddenlevel = 1 AND ( T .HIDDENTIME BETWEEN TO_DATE (?, 'YYYY-MM-DD') AND TO_DATE (?, 'YYYY-MM-DD')) GROUP BY T .district3 ) ybnn WHERE ybn.district3 = ybnn.district3 (+)) yb ON big.zdndistrict3 = yb.ybndistrict3 ) yh LEFT JOIN V_GIS_SYS_ORGANS vso ON yh.ybndistrict3 = vso.organcode ) YH ON ZCZB.CITYDISTRICTNAME = YH.DIQU";
		Map<String, Object> map=new HashMap<String, Object>();
		String stardate=DateUtils.getStardate(condition);
		String enddate=DateUtils.getEnddate(condition);
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql).setParameter(0, stardate).setParameter(1, enddate).setParameter(2, stardate).setParameter(3, enddate).setParameter(4, stardate).setParameter(5, enddate).setParameter(6, stardate).setParameter(7, enddate).setParameter(8, stardate).setParameter(9, enddate).list();
		map.put("findByJgqh", list);
		return map;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> findYhByBmCondttionZczb(String condition) {
		String sql="SELECT BM1.DEPARTMENT, BM1.NUMBERS, BM1.TATOL, BM1.REPORTTATOLE, BM1.SAFETYTATOLE, YH.BIGNUMS, YH.BIGNNOTUMS, YH.YBNUMS, YH.YBNNOTUMS FROM ( SELECT BM.DEPARTMENT, BM.NUMBERS, ZCZB.TATOL, ZCZB.REPORTTATOLE, ZCZB.SAFETYTATOLE FROM ( SELECT t1.data1 AS DEPARTMENT, NVL (t2.num, 0) AS numbers FROM ( SELECT DEPTNAME, COUNT (*) num FROM V_GIS_ENTERPRISE GROUP BY deptname ) t2 RIGHT JOIN ( SELECT DATA1 FROM SYS_DICTTREEDATA WHERE DATA1 LIKE '%部门' ) t1 ON t1.data1 = t2.DEPTNAME ) BM LEFT JOIN ( SELECT three.DATA1 AS DEPARTMENT, NVL (COUNT(*), 0) Tatol, NVL (SUM(ISREPORT), 0) REPORTTATOLE, NVL (SUM(ISSAFETY), 0) SAFETYTATOLE FROM ( SELECT ISSAFETY, ISREPORT, DATA1 FROM ( SELECT * FROM ( SELECT CR.ENTERPRISEID, CR.ISREPORT, CR.ISSAFETY, SM.DEPARTMENT FROM CR_CHECKMONTHREPORT CR LEFT JOIN SM_ENTERPRISE SM ON SM.ENTERPRISEID = CR.ENTERPRISEID WHERE CR.REPORTTIME BETWEEN TO_DATE (?, 'YYYY-MM-DD') AND TO_DATE (?, 'YYYY-MM-DD')) one FULL JOIN SYS_DICTTREEDATA SYS ON SYS.CODE = one.DEPARTMENT ) tow WHERE DATA1 LIKE '%部门%' ) three GROUP BY three.DATA1 ) ZCZB ON BM.DEPARTMENT = ZCZB.DEPARTMENT ) BM1 LEFT JOIN ( SELECT NVL ( TONGJI.DEPTNAME, BUMEN.TYPENAME ) AS DEPARTMENT, NVL (TONGJI.BIGNUMS, 0) AS BIGNUMS, NVL (TONGJI.BIGNNOTUMS, 0) AS BIGNNOTUMS, NVL (TONGJI.YBNUMS, 0) AS YBNUMS, NVL (TONGJI.YBNNOTUMS, 0) AS YBNNOTUMS FROM ( SELECT YBNUM.DEPTNAME, BIGNUM.BIGNUMS, BIGNUM.BIGNNOTUMS, YBNUM.YBNUMS, YBNUM.YBNNOTUMS FROM ( SELECT ha0101.DEPTNAME, ha0101.BIGNUMS, ha0202.BIGNNOTUMS FROM ( SELECT NVL ( ha01.DEPTNAME, '其他部门' ) AS DEPTNAME, COUNT (1) AS bignums FROM ( SELECT ha01.ENTERPRISEID AS ha01enid, ha01.HIDDENLEVEL AS ha01hlevel, ha01.DEALCONDITIONCODE AS ha01scode, vgen01.DEPTNAME FROM HT_ACCIDENTHIDDEN ha01 LEFT OUTER JOIN V_GIS_ENTERPRISE vgen01 ON ha01.ENTERPRISEID = vgen01.ENTERPRISEID WHERE ha01.HIDDENTIME BETWEEN TO_DATE (?, 'YYYY-MM-DD') AND TO_DATE (?, 'YYYY-MM-DD')) ha01 WHERE ha01.ha01hlevel = 2 GROUP BY ha01.DEPTNAME ) ha0101 LEFT OUTER JOIN ( SELECT NVL ( ha02.DEPTNAME, '其他部门' ) AS DEPTNAME, COUNT (1) AS bignnotums FROM ( SELECT ha02.ENTERPRISEID AS ha02enid, ha02.HIDDENLEVEL AS ha02hlevel, ha02.DEALCONDITIONCODE AS ha02scode, vgen02.DEPTNAME FROM HT_ACCIDENTHIDDEN ha02 LEFT OUTER JOIN V_GIS_ENTERPRISE vgen02 ON ha02.ENTERPRISEID = vgen02.ENTERPRISEID WHERE ha02.HIDDENTIME BETWEEN TO_DATE (?, 'YYYY-MM-DD') AND TO_DATE (?, 'YYYY-MM-DD')) ha02 WHERE ha02.ha02hlevel = 2 AND ha02.ha02scode = 1 GROUP BY ha02.DEPTNAME ) ha0202 ON ha0101.DEPTNAME = HA0202.DEPTNAME ) bignum FULL JOIN ( SELECT ha0101.DEPTNAME, ha0101.YBNUMS, ha0202.YBNNOTUMS FROM ( SELECT NVL ( ha01.DEPTNAME, '其他部门' ) AS DEPTNAME, COUNT (1) AS ybnums FROM ( SELECT ha01.ENTERPRISEID AS ha01enid, ha01.HIDDENLEVEL AS ha01hlevel, ha01.DEALCONDITIONCODE AS ha01scode, vgen01.DEPTNAME FROM HT_ACCIDENTHIDDEN ha01 LEFT OUTER JOIN V_GIS_ENTERPRISE vgen01 ON ha01.ENTERPRISEID = vgen01.ENTERPRISEID WHERE ha01.HIDDENTIME BETWEEN TO_DATE (?, 'YYYY-MM-DD') AND TO_DATE (?, 'YYYY-MM-DD')) ha01 WHERE ha01.ha01hlevel = 1 GROUP BY ha01.DEPTNAME ) ha0101 LEFT OUTER JOIN ( SELECT NVL ( ha02.DEPTNAME, '其他部门' ) AS DEPTNAME, COUNT (1) AS ybnnotums FROM ( SELECT ha02.ENTERPRISEID AS ha02enid, ha02.HIDDENLEVEL AS ha02hlevel, ha02.DEALCONDITIONCODE AS ha02scode, vgen02.DEPTNAME FROM HT_ACCIDENTHIDDEN ha02 LEFT OUTER JOIN V_GIS_ENTERPRISE vgen02 ON ha02.ENTERPRISEID = vgen02.ENTERPRISEID WHERE ha02.HIDDENTIME BETWEEN TO_DATE (?, 'YYYY-MM-DD') AND TO_DATE (?, 'YYYY-MM-DD')) ha02 WHERE ha02.ha02hlevel = 1 AND ha02.ha02scode = 1 GROUP BY ha02.DEPTNAME ) ha0202 ON ha0101.DEPTNAME = HA0202.DEPTNAME ) ybnum ON YBNUM.DEPTNAME = BIGNUM.DEPTNAME ) tongji FULL JOIN ( SELECT t1.data1 AS typeName, NVL (t2.num, 0) AS numbers FROM ( SELECT DEPTNAME, COUNT (*) num FROM V_GIS_ENTERPRISE GROUP BY deptname ) t2 RIGHT JOIN ( SELECT DATA1 FROM SYS_DICTTREEDATA WHERE DATA1 LIKE '%部门' ) t1 ON t1.data1 = t2.DEPTNAME ) bumen ON BUMEN.TYPENAME = tongji.DEPTNAME ) YH ON BM1.DEPARTMENT = YH.DEPARTMENT";
		Map<String, Object> map=new HashMap<String, Object>();
		String stardate=DateUtils.getStardate(condition);
		String enddate=DateUtils.getEnddate(condition);
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql).setParameter(0, stardate).setParameter(1, enddate).setParameter(2, stardate).setParameter(3, enddate).setParameter(4, stardate).setParameter(5, enddate).setParameter(6, stardate).setParameter(7, enddate).setParameter(8, stardate).setParameter(9, enddate).list();
		map.put("findByJgbm", list);
		return map;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> findYhByLxCondttionZczb(String condition) {
		String sql="SELECT ZC.TYPENAME, ZC.NUMBERS, ZC.TOTAL, ZC.REPORTTOTAL, ZC.SAFETYTOTAL, YH.BIGNUM, YH.BIGNONUM, YH.YBNUM, YH.YBNONUM FROM ( SELECT LX.TYPENAME, LX.NUMBERS, ZCZB.TOTAL, ZCZB.REPORTTOTAL, ZCZB.SAFETYTOTAL FROM ( SELECT NVL (c.INDUSTRY1STR, '未分类') AS typeName, COUNT (0) AS numbers FROM V_GIS_ENTERPRISE c WHERE c.INDUSTRY1STR IS NOT NULL GROUP BY c.INDUSTRY1STR ) LX LEFT JOIN ( SELECT NVL (INDUSTRY1STR, '其他') AS typeName, COUNT (*) total, NVL (SUM(ISREPORT), 0) REPORTTOTAL, NVL (SUM(ISSAFETY), 0) SAFETYTOTAL FROM ( SELECT ISSAFETY, ISREPORT, INDUSTRY1STR FROM ( SELECT * FROM ( SELECT CR.ENTERPRISEID, CR.ISREPORT, CR.ISSAFETY, SM.District3 FROM CR_CHECKMONTHREPORT CR LEFT JOIN SM_ENTERPRISE SM ON SM.ENTERPRISEID = CR.ENTERPRISEID WHERE CR.ISREPORT = 1 AND CR.REPORTTIME BETWEEN TO_DATE (?, 'YYYY-MM-DD') AND TO_DATE (?, 'YYYY-MM-DD')) one FULL JOIN V_GIS_ENTERPRISE organs ON organs.DISTRICT3 = one.District3 ) two ) three GROUP BY three.INDUSTRY1STR ) ZCZB ON LX.TYPENAME = ZCZB.typeName ) ZC LEFT JOIN ( SELECT YB.INDUSTRY1STR, BIG.BIGNUM, BIG.BIGNONUM, YB.YBNUM, YB.YBNONUM FROM ( SELECT BIGN.INDUSTRY1STR AS INDUSTRY1STR, NVL (BIGN.BIGNUM, 0) AS YBNUM, NVL (BIGNO.BIGNOTNUM, 0) AS YBNONUM FROM ( SELECT VGE.typeName AS INDUSTRY1STR, HA1.BIGNUM AS BIGNUM FROM (( SELECT HA0101.INDUSTRY1STR AS INDUSTRY1STR, COUNT (0) AS BIGNUM FROM ( SELECT ha01.ENTERPRISEID AS ha01enid, ha01.HIDDENLEVEL AS ha01hlevel, ha01.DEALCONDITIONCODE AS ha01scode, NVL ( vgen01.INDUSTRY1STR, '其他' ) AS INDUSTRY1STR FROM HT_ACCIDENTHIDDEN ha01 LEFT OUTER JOIN V_GIS_ENTERPRISE vgen01 ON ha01.ENTERPRISEID = vgen01.ENTERPRISEID WHERE ha01.HIDDENLEVEL = 1 AND ha01.HIDDENTIME BETWEEN TO_DATE (?, 'YYYY-MM-DD') AND TO_DATE (?, 'YYYY-MM-DD')) HA0101 GROUP BY INDUSTRY1STR ) HA1 RIGHT JOIN ( SELECT NVL (c.INDUSTRY1STR, '其他') AS typeName FROM V_GIS_ENTERPRISE c GROUP BY c.INDUSTRY1STR ) VGE ON HA1.INDUSTRY1STR = VGE.typeName )) BIGN FULL JOIN ( SELECT HA0101.INDUSTRY1STR AS INDUSTRY1STR, COUNT (0) AS BIGNOTNUM FROM ( SELECT ha01.ENTERPRISEID AS ha01enid, ha01.HIDDENLEVEL AS ha01hlevel, ha01.DEALCONDITIONCODE AS ha01scode, NVL ( vgen01.INDUSTRY1STR, '其他' ) AS INDUSTRY1STR FROM HT_ACCIDENTHIDDEN ha01 LEFT OUTER JOIN V_GIS_ENTERPRISE vgen01 ON ha01.ENTERPRISEID = vgen01.ENTERPRISEID WHERE ha01.HIDDENLEVEL = 1 AND ha01.DEALCONDITIONCODE = 1 AND ha01.HIDDENTIME BETWEEN TO_DATE (?, 'YYYY-MM-DD') AND TO_DATE (?, 'YYYY-MM-DD')) HA0101 GROUP BY INDUSTRY1STR ) BIGNO ON BIGN.INDUSTRY1STR = BIGNO.INDUSTRY1STR ) YB FULL JOIN ( SELECT BIGN.INDUSTRY1STR AS INDUSTRY1STR, NVL (BIGN.BIGNUM, 0) AS BIGNUM, NVL (BIGNO.BIGNOTNUM, 0) AS BIGNONUM FROM ( SELECT VGE.typeName AS INDUSTRY1STR, HA1.BIGNUM AS BIGNUM FROM (( SELECT HA0101.INDUSTRY1STR AS INDUSTRY1STR, COUNT (0) AS BIGNUM FROM ( SELECT ha01.ENTERPRISEID AS ha01enid, ha01.HIDDENLEVEL AS ha01hlevel, ha01.DEALCONDITIONCODE AS ha01scode, NVL ( vgen01.INDUSTRY1STR, '其他' ) AS INDUSTRY1STR FROM HT_ACCIDENTHIDDEN ha01 LEFT OUTER JOIN V_GIS_ENTERPRISE vgen01 ON ha01.ENTERPRISEID = vgen01.ENTERPRISEID WHERE ha01.HIDDENLEVEL = 2 AND ha01.HIDDENTIME BETWEEN TO_DATE (?, 'YYYY-MM-DD') AND TO_DATE (?, 'YYYY-MM-DD')) HA0101 GROUP BY INDUSTRY1STR ) HA1 RIGHT JOIN ( SELECT NVL (c.INDUSTRY1STR, '其他') AS typeName FROM V_GIS_ENTERPRISE c GROUP BY c.INDUSTRY1STR ) VGE ON HA1.INDUSTRY1STR = VGE.typeName )) BIGN FULL JOIN ( SELECT HA0101.INDUSTRY1STR AS INDUSTRY1STR, COUNT (0) AS BIGNOTNUM FROM ( SELECT ha01.ENTERPRISEID AS ha01enid, ha01.HIDDENLEVEL AS ha01hlevel, ha01.DEALCONDITIONCODE AS ha01scode, NVL ( vgen01.INDUSTRY1STR, '其他' ) AS INDUSTRY1STR FROM HT_ACCIDENTHIDDEN ha01 LEFT OUTER JOIN V_GIS_ENTERPRISE vgen01 ON ha01.ENTERPRISEID = vgen01.ENTERPRISEID WHERE ha01.HIDDENLEVEL = 2 AND ha01.DEALCONDITIONCODE = 1 AND ha01.HIDDENTIME BETWEEN TO_DATE (?, 'YYYY-MM-DD') AND TO_DATE (?, 'YYYY-MM-DD')) HA0101 GROUP BY INDUSTRY1STR ) BIGNO ON BIGN.INDUSTRY1STR = BIGNO.INDUSTRY1STR ) BIG ON BIG.INDUSTRY1STR = YB.INDUSTRY1STR ) YH ON ZC.typeName = YH.INDUSTRY1STR";
		Map<String, Object> map=new HashMap<String, Object>();
		String stardate=DateUtils.getStardate(condition);
		String enddate=DateUtils.getEnddate(condition);
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql).setParameter(0, stardate).setParameter(1, enddate).setParameter(2, stardate).setParameter(3, enddate).setParameter(4, stardate).setParameter(5, enddate).setParameter(6, stardate).setParameter(7, enddate).setParameter(8, stardate).setParameter(9, enddate).list();
		map.put("findByJglx", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findAllUser(String condition) {
		String sql="SELECT US.USERID,US.USERNAME,DE.ORGANNAME,US.DUTY FROM SYS_USER US LEFT JOIN SYS_DEPARTMENT DE ON US.DEPTID=DE.DEPTID WHERE LENGTH(US.USERNAME) <= 4 AND US.STATUS=1 AND US.USERNAME LIKE '%"+condition+"%'";
		//如果不带条件即查询所有人
		if(condition==null || condition.equals("")){
			sql="SELECT US.USERID,US.USERNAME,DE.ORGANNAME,US.DUTY FROM SYS_USER US LEFT JOIN SYS_DEPARTMENT DE ON US.DEPTID=DE.DEPTID WHERE LENGTH(US.USERNAME) <= 4 AND US.STATUS=1";
		}		
		Map<String, Object> map=new HashMap<String, Object>();
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql).list();
		map.put("findAllUser", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findHiddByNum(int num) {
		String sql="SELECT t1.* FROM (SELECT HA.*,ROWNUM rn FROM (SELECT HA.ENTNAME,HA.HIDDENDES,HA.HIDDENPART,HA.ENTERPRISEID,HA.HIDDENID  FROM HT_ACCIDENTHIDDEN HA  WHERE HA.DEALCONDITIONCODE = 2 ORDER BY HA.HIDDENTIME DESC ) HA) t1 WHERE t1.rn < ?";
		Map<String, Object> map=new HashMap<String, Object>();
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql).setParameter(0, num).list();
		map.put("list", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findCxjs(int num) {
		String sql="";
		Map<String, Object> map=new HashMap<String, Object>();
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql).setParameter(0, num).list();
		map.put("list", list);
		return map;
	}


	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findHiddenChaosshi(String user, String date) {
		String strartDate=DateUtils.getStardate(date);
		String endDate=DateUtils.getEnddate(date);
		String sql="SELECT COUNT(*) FROM HT_ACCIDENTHIDDEN HT WHERE HT.HIDDENREGISTER LIKE ? AND (HT.CONFIRMTIME > HT.DEALVALIDATE OR(HT.CONFIRMTIME IS NULL AND HT.DEALVALIDATE < CURRENT_DATE)) AND ( HT.HIDDENTIME BETWEEN TO_DATE(?, 'yyyy-MM-dd') AND TO_DATE(?, 'yyyy-MM-dd'))";
		Map<String, Object> map=new HashMap<String, Object>();
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql).setParameter(0, "%"+user+"%").setParameter(1, strartDate).setParameter(2, endDate).list();
		map.put("ChaosshiNum", list);
		map.put("total", this.findHiddenAllNum(user, date));
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findHiddenSuccess(String user, String date) {
		String strartDate=DateUtils.getStardate(date);
		String endDate=DateUtils.getEnddate(date);
		String sql="SELECT COUNT(0) FROM HT_ACCIDENTHIDDEN HT WHERE HT.HIDDENREGISTER LIKE ? AND HT.DEALCONDITIONCODE = 3 AND ( HT.HIDDENTIME BETWEEN TO_DATE(?, 'yyyy-MM-dd') AND TO_DATE(?, 'yyyy-MM-dd'))";
		Map<String, Object> map=new HashMap<String, Object>();
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql).setParameter(0, "%"+user+"%").setParameter(1, strartDate).setParameter(2, endDate).list();
		map.put("SuccessNum", list);
		map.put("total", this.findHiddenAllNum(user, date));
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findHiddenAllNumByzgstatus(String user,
			String date) {
		String strartDate=DateUtils.getStardate(date);
		String endDate=DateUtils.getEnddate(date);
		String sql="SELECT * FROM ( SELECT COUNT (0) AS S1 FROM HT_ACCIDENTHIDDEN HT WHERE HT.HIDDENREGISTER LIKE ? AND HT.DEALCONDITIONCODE = 1 AND ( HT.HIDDENTIME BETWEEN TO_DATE (?, 'yyyy-MM-dd') AND TO_DATE (?, 'yyyy-MM-dd'))), ( SELECT COUNT (0) AS S2 FROM HT_ACCIDENTHIDDEN HT WHERE HT.HIDDENREGISTER LIKE ? AND HT.DEALCONDITIONCODE = 2 AND ( HT.HIDDENTIME BETWEEN TO_DATE (?, 'yyyy-MM-dd') AND TO_DATE (?, 'yyyy-MM-dd'))), ( SELECT COUNT(0) AS S3 FROM HT_ACCIDENTHIDDEN HT WHERE HT.HIDDENREGISTER LIKE ? AND HT.DEALCONDITIONCODE = 3 AND ( HT.HIDDENTIME BETWEEN TO_DATE (?, 'yyyy-MM-dd') AND TO_DATE (?, 'yyyy-MM-dd'))), ( SELECT COUNT(0) AS S4 FROM HT_ACCIDENTHIDDEN HT WHERE HT.HIDDENREGISTER LIKE ? AND HT.DEALCONDITIONCODE IS NULL AND ( HT.HIDDENTIME BETWEEN TO_DATE (?, 'yyyy-MM-dd') AND TO_DATE (?, 'yyyy-MM-dd')))";
		Map<String, Object> map=new HashMap<String, Object>();
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql).setParameter(0, "%"+user+"%").setParameter(1, strartDate).setParameter(2, endDate)
				.setParameter(3, "%"+user+"%").setParameter(4, strartDate).setParameter(5, endDate)
				.setParameter(6, "%"+user+"%").setParameter(7, strartDate).setParameter(8, endDate)
				.setParameter(9, "%"+user+"%").setParameter(10, strartDate).setParameter(11, endDate).list();
		map.put("SuccessNum", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findHiddenAllNumByEnlx(String user, String date) {
		String strartDate=DateUtils.getStardate(date);
		String endDate=DateUtils.getEnddate(date);
		String sql="SELECT NVL (LX.INDUSTRY1STR, '其他'), COUNT (0) FROM ( SELECT C.ENTERPRISEID AS ENTERPRISEID, C.INDUSTRY1STR AS INDUSTRY1STR FROM V_GIS_ENTERPRISE C ) LX RIGHT JOIN ( SELECT HT.ENTERPRISEID AS ENTERPRISEID FROM HT_ACCIDENTHIDDEN HT WHERE HT.HIDDENREGISTER LIKE ? AND ( HT.HIDDENTIME BETWEEN TO_DATE (?, 'yyyy-MM-dd') AND TO_DATE (?, 'yyyy-MM-dd'))) HD ON LX.ENTERPRISEID = HD.ENTERPRISEID GROUP BY LX.INDUSTRY1STR";
		Map<String, Object> map=new HashMap<String, Object>();
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql).setParameter(0, "%"+user+"%").setParameter(1, strartDate).setParameter(2, endDate).list();
		map.put("AllNumByEnlx", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findHiddenAllNumByEnqh(String user, String date) {
		String strartDate=DateUtils.getStardate(date);
		String endDate=DateUtils.getEnddate(date);
		String sql="SELECT NVL ( VGSO.citydistrictname, '其他' ), COUNT (0) AS ENTERPRISEID FROM HT_ACCIDENTHIDDEN HT LEFT JOIN SM_ENTERPRISE SE ON HT.ENTERPRISEID = SE.ENTERPRISEID LEFT JOIN V_GIS_SYS_ORGANS VGSO ON SE.District3 = VGSO.organcode WHERE HT.HIDDENREGISTER LIKE ? AND ( HT.HIDDENTIME BETWEEN TO_DATE (?, 'yyyy-MM-dd') AND TO_DATE (?, 'yyyy-MM-dd')) GROUP BY VGSO.citydistrictname";
		Map<String, Object> map=new HashMap<String, Object>();
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql).setParameter(0, "%"+user+"%").setParameter(1, strartDate).setParameter(2, endDate).list();
		map.put("AllNumByEnqh", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findHiddenAllNumBySession(String user,
			String date) {
		Map<String, Object> map=new HashMap<String, Object>();
		String strartDate=null;
		String endDate=null;
		String sql="SELECT COUNT (0) FROM HT_ACCIDENTHIDDEN HT WHERE HT.HIDDENREGISTER LIKE ? AND ( HT.HIDDENTIME BETWEEN TO_DATE (?, 'yyyy-MM-dd') AND TO_DATE (?, 'yyyy-MM-dd'))";			
		for (int i = 1; i <= 4; i++) {
			String jidu=date+"-"+i;
			strartDate=DateUtils.getStardate(jidu);
			endDate=DateUtils.getEnddate(jidu);
			List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql).setParameter(0, "%"+user+"%").setParameter(1, strartDate.toString()).setParameter(2, endDate.toString()).list();
			map.put("quarter"+i, list);			
		}
		return map;
	}

	@Override
	public int findHiddenAllNum(String user, String date) {
		String strartDate=DateUtils.getStardate(date);
		String endDate=DateUtils.getEnddate(date);
		String sql="SELECT COUNT (0) FROM HT_ACCIDENTHIDDEN HT WHERE HT.HIDDENREGISTER LIKE ? AND ( HT.HIDDENTIME BETWEEN TO_DATE (?, 'yyyy-MM-dd') AND TO_DATE (?, 'yyyy-MM-dd'))";		
		BigDecimal total=(BigDecimal)super.getSession().createSQLQuery(sql).setParameter(0, "%"+user+"%").setParameter(1, strartDate).setParameter(2, endDate).uniqueResult();
		return total.intValue();
	}

	@Override
	public Map<String, Object> findSafeTeache(int num) {
		Map<String, Object> map=new HashMap<String, Object>();
		List<Object[]> list=new ArrayList<Object[]>();
		//查询多个字段，后面的只是查询拼成一个字段String sql="SELECT * FROM (SELECT C.ID,C.TITLE,C.LINK,C.DESCRIPTION,C.CREATE_DATE,C.UPDATE_DATE,C.REMARKS FROM CMS_ARTICLE C WHERE C.CATEGORY_ID = 'f1733a81641a4e25bb2b830a3eb7e1fe' AND C.DEL_FLAG = 0 ORDER BY CREATE_DATE DESC ) WHERE ROWNUM < ?";
		String sql="SELECT * FROM (SELECT C.TITLE || '  ' || C.DESCRIPTION || '  录入日期：' || TO_CHAR(C.CREATE_DATE,'yyyy-MM-dd') FROM CMS_ARTICLE C WHERE C.CATEGORY_ID = 'f1733a81641a4e25bb2b830a3eb7e1fe' AND C.DEL_FLAG = 0 ORDER BY CREATE_DATE DESC ) WHERE ROWNUM < ?";	
		
		//jdbc直接连接数据库另外查询
		String url = "jdbc:oracle:thin:@222.85.178.147:1521:orcl";  
		String name = "oracle.jdbc.driver.OracleDriver";  
		String user = "ajyj";  
		String password = "123456";  

		Connection conn = null;  
		PreparedStatement pst = null;  
		ResultSet rs=null;
		try {  
			Class.forName(name);//指定连接类型  
			conn = DriverManager.getConnection(url, user, password);//获取连接  
			pst = conn.prepareStatement(sql);//准备执行语句  
			pst.setInt(1, num);
			rs=pst.executeQuery();
			while(rs.next()){
				list.add(new String[]{rs.getString(1)});
			}
		} catch (Exception e) {  
			e.printStackTrace();  
			
		}
		
		try {
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//上面的是在另外一个数据库用户的表，如果改为同一个用户里的表则直接执行下一注释内容
		//list=(List<Object[]>)super.getSession().createSQLQuery(sql).setParameter(0, num).list();
		map.put("list", list);
		return map;
	}

	@Override
	public Map<String, Object> findTimeLength(String startdate,String enddate, String user) {
		Map<String, Object> map=new HashMap<String, Object>();
		System.out.println("startdate="+startdate+"enddate="+enddate);
		String sql="SELECT AVG(ROUND(TO_NUMBER(NVL(HT.CONFIRMTIME,CURRENT_DATE) - HT.HIDDENTIME) * 24 * 60)) FROM HT_ACCIDENTHIDDEN HT WHERE HT.HIDDENREGISTER LIKE ? AND HT.CONFIRMTIME IS NOT NULL AND ( HT.HIDDENTIME BETWEEN TO_DATE (?, 'yyyy-MM-dd') AND TO_DATE (?, 'yyyy-MM-dd'))";
		List<?> list=(List<?>)super.getSession().createSQLQuery(sql).setParameter(0, "%"+user+"%").setParameter(1, startdate).setParameter(2, enddate).list();
		map.put("list", list);
		return map;
	}

	@Override
	public Map<String, Object> findAllHidEnByDate(String entName,String startDate,
			String endDate) {
		Map<String, Object> map=new HashMap<String, Object>();
		String sql="SELECT SE.ENTNAME,SE.ENTERPRISEID FROM HT_ACCIDENTHIDDEN HA INNER JOIN SM_ENTERPRISE SE ON HA.ENTERPRISEID=SE.ENTERPRISEID WHERE SE.ENTNAME like ? AND HA.HIDDENTIME BETWEEN TO_DATE(?, 'yyyy-MM-dd') AND TO_DATE(?, 'yyyy-MM-dd') GROUP BY SE.ENTNAME,SE.ENTERPRISEID ORDER BY SE.ENTNAME ASC";
		List<?> list=(List<?>)super.getSession().createSQLQuery(sql).setParameter(0, "%"+entName+"%").setParameter(1, startDate).setParameter(2, endDate).list();
		map.put("list", list);
		return map;//2017-5-31上午10:16:21宁万龙
	}

	@Override
	public Map<String, Object> findHidEnByDate_EnId(String entId,
			String startDate, String endDate) {
		Map<String, Object> map=new HashMap<String, Object>();
		String sql="SELECT SE.ENTNAME, SE.ENTERPRISEID, HA.FINDHIDDENPERSON, HA.HIDDENTIME, HA.CHECKPERSON, HA.CHECKTIME, HA.CONFIRMPERSON, HA.CONFIRMTIME, HA.HIDDENLEVEL, HA.DEALCONDITIONCODE FROM HT_ACCIDENTHIDDEN HA INNER JOIN SM_ENTERPRISE SE ON HA.ENTERPRISEID = SE.ENTERPRISEID WHERE HA.ENTERPRISEID = ? AND HA.HIDDENTIME BETWEEN TO_DATE (?, 'yyyy-MM-dd') AND TO_DATE (?, 'yyyy-MM-dd') ORDER BY SE.ENTNAME, HA.DEALCONDITIONCODE, HA.HIDDENTIME DESC";
		List<?> list=(List<?>)super.getSession().createSQLQuery(sql).setParameter(0, entId).setParameter(1, startDate).setParameter(2, endDate).list();
		map.put("list", list);
		return map;//2017-5-31上午10:16:21宁万龙
	}

	@Override
	public Map<String, Object> findZfPosition(String username,
			String startDate, String endDate,PageBaen pageBaen) {
		String sql01="SELECT COUNT (*) FROM ( SELECT ZF. ID, NVL ( ZF.ENTERPRISENAME, '暂不存在的企业名' ), NVL (ZF.PRODUCTADDRESS, '贵阳'), ZF.STARTTIME, ZF.ENDTIME, NVL (ZF.LONGITUDE, 106.71), NVL (ZF.LATITUDE, 26.57), SU.EMPNAME FROM ( SELECT CI. ID, SE.ENTERPRISENAME, SE.LONGITUDE, SE.LATITUDE, CI.STARTTIME, SE.PRODUCTADDRESS, CI.ENDTIME, CI.CHKPERSON1, CI.CHKPERSON2, CI.CHKPERSON3 FROM CK_INSPECTRECORD CI LEFT JOIN SM_ENTERPRISE SE ON CI.ENTID = SE.ENTERPRISEID ) ZF LEFT JOIN SYS_ORGANEMPLOYEE SU ON SU.EMPID IN ( ZF.CHKPERSON2, ZF.CHKPERSON1, ZF.CHKPERSON3 ) WHERE ZF.ENTERPRISENAME IS NOT NULL AND SU.EMPNAME LIKE ? AND ZF.STARTTIME BETWEEN TO_DATE (?, 'yyyy-MM-dd') AND TO_DATE (?, 'yyyy-MM-dd'))";
		BigDecimal total=(BigDecimal)super.getSession().createSQLQuery(sql01).setParameter(0, "%"+username+"%").setParameter(1, startDate).setParameter(2, endDate).uniqueResult();
		int totals=total.intValue();
		int pageSize=pageBaen.getPageSize();
		int currentPage=pageBaen.getCurrentPage();
		if(totals < pageSize){
			pageSize = totals;
		}
		if((currentPage-1)*pageSize >= totals){
			currentPage = currentPage-1;
		}		
		Map<String, Object> map=new HashMap<String, Object>();
		String sql="SELECT ZF. ID, NVL ( ZF.ENTERPRISENAME, '暂不存在的企业名' ), NVL (ZF.PRODUCTADDRESS, '贵阳'), ZF.STARTTIME, ZF.ENDTIME, NVL (ZF.LONGITUDE, 106.71), NVL (ZF.LATITUDE, 26.57), SU.EMPNAME FROM ( SELECT CI. ID, SE.ENTERPRISENAME, SE.LONGITUDE, SE.LATITUDE, CI.STARTTIME, SE.PRODUCTADDRESS, CI.ENDTIME, CI.CHKPERSON1, CI.CHKPERSON2, CI.CHKPERSON3 FROM CK_INSPECTRECORD CI LEFT JOIN SM_ENTERPRISE SE ON CI.ENTID = SE.ENTERPRISEID ) ZF LEFT JOIN SYS_ORGANEMPLOYEE SU ON SU.EMPID IN ( ZF.CHKPERSON2, ZF.CHKPERSON1, ZF.CHKPERSON3 ) WHERE ZF.ENTERPRISENAME IS NOT NULL AND SU.EMPNAME LIKE ? AND ZF.STARTTIME BETWEEN TO_DATE (?, 'yyyy-MM-dd') AND TO_DATE (?, 'yyyy-MM-dd')";
		List<?> list=(List<?>)super.getSession().createSQLQuery(sql)
				.setParameter(0, "%"+username+"%").setParameter(1, startDate).setParameter(2, endDate)
				.setFirstResult(pageSize*(currentPage-1)).setMaxResults(pageSize).list();
		map.put("list", list);
		map.put("totals", totals);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public  List<Object[]> findZfByDate(String name, String startDate,
			String endDate) {
		String sql=" SELECT HT.* FROM HT_ACCIDENTHIDDEN HT WHERE HT.CONFIRMPERSON LIKE ? AND HT.DEALCONDITIONCODE = 1 AND ( HT.CONFIRMTIME BETWEEN TO_DATE (?, 'yyyy-MM-dd') AND TO_DATE (?, 'yyyy-MM-dd'))";
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql)
				.setParameter(0, "%"+name+"%").setParameter(1, startDate).setParameter(2, endDate).list();
		return list;
	}
	
	
	
}