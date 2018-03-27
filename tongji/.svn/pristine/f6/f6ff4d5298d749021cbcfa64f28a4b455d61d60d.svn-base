package com.henghao.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.henghao.dao.IWZPEnterpriseDao;
import com.henghao.entity.Result;
import com.henghao.service.IWZPEnterpriseService;
import com.henghao.util.DateUtils;
import com.henghao.vo.CaseVo;
import com.henghao.vo.SelectedEntVo;
import com.henghao.vo.VoEnterprise;
import com.henghao.vo.ZFTimeVo;

/**
 * 企业统计service实现类
 * 
 * @author 王章鹏
 * @time 2017-4-13
 */
@Service("enterpriseService")
public class WZPEnterpriseServiceImpl implements IWZPEnterpriseService {

	// dao接口
	@Resource
	private IWZPEnterpriseDao enterpriseDao;
	
	// service 统一map
	Map<String, Object> mapService = new HashMap<String, Object>();
	//执法情况统计map
	Map<String, Object> map_zhifa = new HashMap<String, Object>();
	//执法情况统计 - 企业被执法时间 map
	Map<String, Object> map_zhifa_times = new HashMap<String, Object>();
	
	/**
	 * 统计所有信息
	 * 
	 * @author 王章鹏
	 * @time 2017-4-13
	 */
	@Override
	public Result getAllInfo() {
		Result result = null;
		try {
			List<VoEnterprise> list = enterpriseDao.getEnterpriseList();
			result = new Result(0, "查询成功", list);
		} catch (Exception e) {
			result = new Result(1, "系统繁忙", null);
		}
		return result;
	}

	/**
	 * 企业统计，包括共有企业数和上报企业数
	 * 
	 * @author 王章鹏
	 * @time 2017-4-13
	 */
	@Override
	public Result getEnterpriseCount() {
		Result result = null;
		try {
			result = new Result(0, "查询成功", enterpriseDao.EnterpriseCount());
		} catch (Exception e) {
			result = new Result(1, "系统繁忙", null);
		}
		return result;
	}

	/*
	 * 行政执法统计(statistics enforcement)
	 * 
	 * @time 2017年4月27日09:23:16
	 * 
	 * @see com.henghao.service.IWZPEnterpriseService#getEnforcement()
	 */
	@Override
	public Result getEnforcement() {

		// 查询sql语句---未检查企业数量统计
		String sql_unCheck = "SELECT COUNT(DISTINCT ENTID) AS num_unCheck FROM ck_checksheet WHERE ISCHECKED=0";
		// 查询sql语句---已检查数量统计
		String sql_checked = "SELECT COUNT(DISTINCT ENTID) AS num_checked FROM ck_checksheet WHERE ISCHECKED=1";
		// 查询sql语句---已执法数量统计
		String sql_enforcement = "SELECT COUNT(DISTINCT ENTID) AS num_enforcement FROM el_docinfo WHERE DOCSTATUS<>3";
		// 未检查数
		int unCheck = enterpriseDao.getCount(sql_unCheck, "num_unCheck");
		// 已检查
		int checked = enterpriseDao.getCount(sql_checked, "num_checked");
		// 已执法
		int enforceed = enterpriseDao.getCount(sql_enforcement,
				"num_enforcement");
		mapService.put("unCheck", unCheck);
		mapService.put("checked", checked);
		mapService.put("enforceed", enforceed);

		return new Result(0, "行政执法统计查询成功", mapService);
	}

	/*
	 * 执法综合查询
	 */
	public Result getSsynthesize(String selectType) {

		Result result = null;
		boolean bi = "businessInfo".equals(selectType);
		boolean sa = "situation_all".equals(selectType);
		// 若传入类型为空，则返回
		if (!bi && !sa) {
			result = new Result(1, "系统繁忙", null);
			return result;
		}
		List<Object> list = null;
		/*
		 * 1. 查询执法业务信息 SELECT
		 */
		String sql_business = "SELECT "
				+ "c.DATASOURCETYPE,c.CASETYPE,c.CASESOURCETYPE,c.CASENAME,"
				+ "c.CASEPARTY,t1.ORGNAME DISTRICT,c.CREATETIME,o.orgname ORG,"
				+ "d.ORGANNAME DEPT,e.EMPNAME,c.TYPE "
				+ "FROM "
				+ "EL_CASE c,SYS_DICTTREEDATA s ,SYS_ORGANS o,SYS_DEPARTMENT d,"
				+ "sys_organemployee e, (SELECT ORGNAME,DISTRICTCODE FROM SYS_ORGANS) t1 "
				+ "WHERE " 
				+ "c.ID IN (SELECT CASEID FROM EL_DOCINFO) "
				+ "AND s.CODE(+)=c.DISTRICTCODE " 
				+ "AND o.ORGID(+)=c.ORGID "
				+ "AND d.DEPTID(+)=c.DEPTID " 
				+ "AND e.EMPID(+)=c.USERID "
				+ "AND t1.DISTRICTCODE=s.CODE ";

		/*
		 * 2. 各被执法单位有关情况: 
		 */
		String sql_situation_all = "SELECT e.ENTNAME,e.REGNUM,t1.DATA1 DISTRICT1,"
				+ "t2.DATA1 DISTRICT2,t3.DATA1 DISTRICT3,t4.DATA1 DISTRICT4,"
				+ "t6.DATA1 INDUSTRY1,e.STATE, t5.DATA1 SCALE ,e.REGADDRESS,e.REGTIME,"
				+ "e.BUSINESSCOPE,e.LEGALPEOPLE,e.LINKMAN,e.LINKTELEPHONE,e.SCJYCSMJ "
				+ "FROM "
				+ "SM_ENTERPRISE e,SYS_DICTTREEDATA t1,SYS_DICTTREEDATA t2,"
				+ "SYS_DICTTREEDATA t3,SYS_DICTTREEDATA t4,"
				+ "(SELECT CODE,DATA1 FROM SYS_DICTTREEDATA WHERE TREEID = 'EnterpriseScale') t5, "
				+ "(SELECT CODE,DATA1 FROM SYS_DICTTREEDATA WHERE TREEID = 'EnterpriseIndustryType') t6 "
				+ "WHERE "
				+ "e.ENTERPRISEID IN (SELECT ENTID FROM EL_DOCINFO) AND e.DISTRICT1=t1.CODE(+) "
				+ "AND e.DISTRICT2=t2.CODE(+) "
				+ "AND e.DISTRICT3=t3.CODE(+) "
				+ "AND e.district4=t4.CODE(+) "
				+ "AND t5.CODE=e.SCALE "
				+ "AND t6.CODE=e.INDUSTRY1";
		try {
			if (bi) {
				list = enterpriseDao.getObjVo(sql_business, CaseVo.class);
			}
			if (sa) {
				list = enterpriseDao.getObjVo(sql_situation_all,
						SelectedEntVo.class);
			}
			result = new Result(0, "查询成功", list);

		} catch (Exception e) {

			result = new Result(1, "系统繁忙", null);
		}
		return result;
	}

	// 根据企业名称查询执法情况的统计信息
	public Result getZhifaSituatinCount(String ENTName) {
		
		Result result = null;
		// 企业名非空
		if (ENTName == null || "".equals(ENTName)) {
			result = new Result(1, "企业名为空", null);
			return result;
		}
		// 查询企业被执法的时间，查询执法文书表中，执法文书建立时间。
		// 用ZFTimeVo类接收
		String sql_date = "SELECT to_char(c.CREATETIME,'yyyy-mm-dd') CREATEDATE FROM EL_CASE c WHERE c.CASEPARTY ='"+ENTName+"' ORDER BY c.CREATETIME";
		try {
			List<ZFTimeVo> list = enterpriseDao.getZFTimeVoList(sql_date);
			int count = list.size();
			if (count > 0) {
				
				for (int i = 0; i < count; i++) {
					// 将所有执法时间放入集合
					int times = i+1;
					ZFTimeVo timeVo = list.get(i);
					timeVo.setFrequency("第"+times+"次执法");
					map_zhifa_times.put("time"+times, timeVo);
					
				}
				// 总执法数
				map_zhifa.put("total", count);
				map_zhifa.put("times", map_zhifa_times);
				result = new Result(0, "查询成功", map_zhifa);
				return result;
			}
			result = new Result(-1, "找不到企业执法记录", null);
		} catch (Exception e) {
			result = new Result(1, "系统繁忙", null);
		}
		
		return result;
	}

	// 查询执法详情
	public Result getZhifaDetail(String ENTName, String date) {
		Result result = null;
		// 企业名非空，时间非空
		if (ENTName == null || "".equals(ENTName) || date == null || "".equals(date)) {
			result = new Result(1, "系统繁忙", null);
			return result;
		}
		// date2的目的是判断传入的date是否满足要求的时间格式(yyyy-MM-dd)，若不满足，则转换
		// 转换：若传入时间戳，则转为要求的时间格式(yyyy-MM-dd)
		// 若转换异常，则说明传入的不是时间格式，则返回
		Date date2 = null;
		// 将满足要求的时间格式，以字符串的形式传入sql语句中
		String date3 = null;
		try {
			date2 = DateUtils.parseDateFromString(date,"yyyy-MM-dd");
			date3 = DateUtils.getFormatedDate(date2, "yyyy-MM-dd");
		} catch (Exception e) {
			result = new Result(1, "系统繁忙", null);
			return result;
		}		
		String sql_caseInfo = "SELECT S.DATA1 DISTRICT,O.ORGNAME ORG,"
				+ "D.ORGANNAME DEPT,E.EMPNAME,C.DATASOURCETYPE,C.CASETYPE,"
				+ "C.CASESOURCETYPE,C.CASENAME,C.CASEPARTY,C.CREATETIME "
				+ "FROM "
				+ "EL_CASE C,SYS_DICTTREEDATA S,SYS_ORGANS O,SYS_DEPARTMENT D ,SYS_ORGANEMPLOYEE E "
				+ "WHERE "
				+ "C.ID IN (SELECT CASEID FROM EL_DOCINFO WHERE CASEPARTY='" + ENTName	+ "') " 
				+ "AND S.CODE(+)=C.DISTRICTCODE "
				+ "AND O.ORGID(+)=C.ORGID " 
				+ "AND D.DEPTID(+)=C.DEPTID "
				+ "AND E.EMPID(+)=C.USERID " 
				+ "AND C.CREATETIME LIKE TO_DATE('"+date3+"','yyyy-MM-dd')";
		
		try {
			List<Object> list = enterpriseDao.getObjVo(sql_caseInfo, CaseVo.class);
			result = new Result(0, "详情查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			result = new Result(1, "系统繁忙", null);
		}
		
		return result;
	}

}
