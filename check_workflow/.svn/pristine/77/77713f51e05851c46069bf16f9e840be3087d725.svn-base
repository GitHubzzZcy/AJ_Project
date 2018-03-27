package com.henghao.dao.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;


import com.henghao.dao.EnteriseDao;
import com.henghao.entity.Enterise;
import com.henghao.util.DateUtils;

/**2017-3-30,上午10:25:42EnteriseDaoImpl.java
 * @author Nwl
 *统计dao
 */
@Repository("endao")
public class EnteriseDaoImpl extends BaseDao<Enterise> implements EnteriseDao {
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findLeaderWhere(int num) throws Exception {
		String ldsql="SELECT AW.ID,AW.TITLE,AW.STARTIME FROM aj_where AW ORDER BY AW.STARTIME DESC LIMIT 0,? ";
		Map<String, Object> map=new HashMap<String, Object>();
		List<Object[]> ldlist=(List<Object[]>)super.getSession().createSQLQuery(ldsql).setParameter(0, num).list();
		String rysql="SELECT thu.ID AS UID,thu.`NAME` AS UNAME,IFNULL(GOO.OT,'未知') AS GOWHERE FROM to_horizon_user thu LEFT JOIN ( SELECT * FROM (SELECT AQ.`NAME` AS UID,AQ.STARTTIME AS SD,AQ.ENDTIME AS ED,'请假' AS OT FROM aj_qingjia AQ UNION SELECT AC.CHUCHAI_RY  AS UID,AC.STARTTIME AS SD,AC.ENDTIME AS ED,CONCAT(AC.SITE,AC.CHUCHAI_SY,'出差') AS OT FROM aj_chuchai AC UNION SELECT cc.userId AS UID,cc.currentDate AS SD,cc.currentDate AS ED,'在岗' AS OT FROM ci_checkingin cc)go WHERE go.SD<=? AND go.ED>=?)GOO ON thu.ID = GOO.UID";
		List<Object[]> rylist=(List<Object[]>)super.getSession().createSQLQuery(rysql).setParameter(0, DateUtils.getCurrentymd(new Date())).setParameter(1, DateUtils.getCurrentymd(new Date())).list();				
		map.put("ldlist", ldlist);
		map.put("rylist", rylist);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findCxjs(int num) {
		String sql="SELECT * FROM HONESTY_GG ORDER BY Created DESC LIMIT ?";
		Map<String, Object> map=new HashMap<String, Object>();
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql).setParameter(0, num).list();
		map.put("list", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findAllUser(String condition) {
		String sql="SELECT THU.ID,THU.`NAME`,THD.DEPT_NAME FROM to_horizon_user THU LEFT JOIN tor_horizon_user_dept THUD ON THU.ID = THUD.USER_ID LEFT JOIN to_horizon_dept THD ON THUD.DEPT_ID = THD.ID WHERE THU.`NAME` LIKE ?  AND THU.`NAME` NOT IN('管理员','测试壹','测试贰','测试叁','安监管理员') ORDER BY FIELD(THD.DEPT_NAME,'安监四处','安监三处','安监二处','安监一处','协调办','法规处','局办公室') DESC";		
		Map<String, Object> map=new HashMap<String, Object>();
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql).setParameter(0, "%"+condition+"%").list();
		map.put("list", list);
		return map;
	}

	@Override
	public Map<String, Object> findTSnum(String startdate,String enddate, String user) {
		String sql="SELECT COUNT(0) FROM aj_cn_complain acc WHERE acc.objects LIKE ? AND acc.disposeDesc IS NULL AND acc.complainStatus ='已结案' AND acc.complainTime BETWEEN ? AND ?";
		List<?> list= (List<?>)super.getSession().createSQLQuery(sql).setParameter(0, "%"+user+"%").setParameter(1, startdate).setParameter(2, enddate).list();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("toushuNum", list);
		return map;//2017-5-17下午1:46:37宁万龙
	}

	@Override
	public Map<String, Object> findSPnum(String startdate,String enddate, String user) {
		String sql="SELECT count(0) AS allnum FROM tw_hz_log l WHERE l.USERNAME LIKE ? AND l.NODEID LIKE 'Node%' AND l.ACTIONTIME BETWEEN ? AND ? UNION ALL SELECT count(0) AS allnum FROM tw_hz_log l WHERE l.USERNAME LIKE ? AND l.ACTIONNAME LIKE '%退回%' AND l.ACTIONTIME BETWEEN ? AND ? UNION ALL SELECT count(0) AS allnum FROM tw_hz_log l WHERE l.USERNAME LIKE ? AND l.ACTIONNAME LIKE '%拿回%' AND l.ACTIONTIME BETWEEN ? AND ?";
		List<?> list= (List<?>)super.getSession().createSQLQuery(sql).setParameter(0, "%"+user+"%").setParameter(1, startdate).setParameter(2, enddate)
				.setParameter(3, "%"+user+"%").setParameter(4, startdate).setParameter(5, enddate)
				.setParameter(6, "%"+user+"%").setParameter(7, startdate).setParameter(8, enddate).list();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("shengpinum", list);
		return map;//2017-5-17下午1:46:37宁万龙
	}

	@Override
	public Map<String, Object> findSPstatusNum(String startdate,String enddate, String user) {
		String sql="SELECT count(0) FROM (SELECT l.WORKID AS allnum FROM tw_hz_log l WHERE l.USERNAME LIKE ? AND l.NODEID LIKE 'Node%' AND l.ACTIONTIME BETWEEN ? AND ? GROUP BY l.WORKID) a UNION ALL SELECT count(0) FROM (SELECT l.WORKID AS allnum FROM tw_hz_log l WHERE l.USERNAME LIKE ? AND l.ACTIONNAME LIKE '%退回%' AND l.ACTIONTIME BETWEEN ? AND ? GROUP BY l.WORKID) b";
		List<?> list= (List<?>)super.getSession().createSQLQuery(sql).setParameter(0, "%"+user+"%").setParameter(1, startdate).setParameter(2, enddate)
				.setParameter(3, "%"+user+"%").setParameter(4, startdate).setParameter(5, enddate).list();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("shengpinum", list);
		return map;//2017-5-17下午1:46:37宁万龙
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findSPNumByjidu(String startdate,String enddate, String user) {
		Map<String, Object> map=new HashMap<String, Object>();
		String strartDate=null;
		String endDate=null;
		String date = startdate.substring(0, 4);
		String sql="SELECT count(0) FROM (SELECT l.WORKID AS allnum FROM tw_hz_log l WHERE l.USERNAME LIKE ? AND l.NODEID LIKE 'Node%' AND l.ACTIONTIME BETWEEN ? AND ? GROUP BY l.WORKID) a";
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
	public Map<String, Object> findSPtimeSize(String startdate,String enddate, String user) {
		String sql="SELECT l.DOTIME FROM tw_hz_log l WHERE l.USERNAME LIKE ? AND l.NODEID LIKE 'Node%' AND l.ACTIONTIME BETWEEN  ? AND ?";
		List<?> list= (List<?>)super.getSession().createSQLQuery(sql).setParameter(0, "%"+user+"%").setParameter(1, startdate).setParameter(2, enddate).list();
		Map<String, Object> map=new HashMap<String, Object>();		
		map.put("list", list);
		return map;//2017-5-17下午1:46:37宁万龙
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findZfddPeople(String name, String daduiNum) {
		String sql="SELECT THU.ID,THU.`NAME`,THD.DEPT_NAME FROM to_horizon_user THU LEFT JOIN tor_horizon_user_dept THUD ON THU.ID = THUD.USER_ID LEFT JOIN to_horizon_dept THD ON THUD.DEPT_ID = THD.ID WHERE THU.`NAME` LIKE ?  AND THD.DEPT_NAME LIKE ?";		
		List<Object[]> list= (List<Object[]>)super.getSession().createSQLQuery(sql)
				.setParameter(0, "%"+name+"%").setParameter(1, "安监执法"+daduiNum+"大队").list();
		Map<String, Object> map=new HashMap<String, Object>();		
		map.put("list", list);
		return map;//2017-5-17下午1:46:37宁万龙
	}

	@Override
	public int findZfcxNum(String daduiName, String date) {
		//数据库表字段问题，大队名
		String culmn="YIDD";
		if(daduiName.equals("安监执法一大队")){
			culmn="YIDD";
		}else if(daduiName.equals("安监执法二大队")){
			culmn="ERDD";
		}else if(daduiName.equals("安监执法三大队")){
			culmn="SANDD";
		}else if(daduiName.equals("安监执法四大队")){
			culmn="SIDD";
		}else if(daduiName.equals("安监执法五大队")){
			culmn="WUDD";
		}
		String sql="SELECT IFNULL(SUM(t."+culmn+"),0) FROM aj_zdzfjcjhb t WHERE t.NIAN = ? AND t.YUE = ?";
		Double nums= (Double)super.getSession().createSQLQuery(sql)
				.setParameter(0, date.substring(0, 4)).setParameter(1, date.substring(5)).uniqueResult();
		int num =nums.intValue();
		System.out.println("daduiName="+daduiName+",date="+date+",num="+num);
		return num;
	}
	
	
	
}
