package com.henghao.news.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.henghao.news.entity.GreatMeeting;
import com.henghao.news.entity.WorkList;

@Repository
public class GreatMeetingDao {

	@Resource
	private SessionFactory sessionFactory;
	private Map<String, Object> map = new HashMap<String, Object>();
	
	public String queryTypeCount(String start, String end, String typename) {
		String sql = "SELECT COUNT(*) FROM aj_great_meeting WHERE SXJSRQ>=? AND SXJSRQ<=? AND MTTYPE=?";
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, start).setParameter(1, end).setParameter(2, typename).uniqueResult().toString();
		return count;
	}

	public Map<String, Object> queryGreatListByType(String type, int page,
			int size) {
		int total = 0;
		List<?> list = null;
		
		if("".equals(type)) {
			String sql = "SELECT COUNT(*) FROM AJ_GREAT_MEETING";
			total = Integer.parseInt(sessionFactory.getCurrentSession().createSQLQuery(sql).uniqueResult().toString());
			if(total < size){
				size = total;
			}
			if((page-1)*size >= total){
				page = page-1;
			}
			String sqllist = "SELECT a.ID, a.Title, a.MTTYPE, a.MTINITIATOR, a.LOADFILE, a.FGLDSH, a.ZYLDSH, a.OPINION, a.MTDESC, a.HYFGLDSHYJ, a.HYZYLDSHYJ,  a.SXJSRQ, a.KYRY, a.HYSJ ,b.WORKID,c.ID AS TRACKID "
						+ "FROM AJ_GREAT_MEETING a LEFT OUTER JOIN twr_hz_instance b ON a.ID=b.DATAID "
						+ "LEFT OUTER JOIN tw_hz_track c ON b.WORKID=c.WORKID ORDER BY a.ID DESC LIMIT ?,?";
			list = sessionFactory.getCurrentSession().createSQLQuery(sqllist).setParameter(0, (page-1)*size).setParameter(1, size).setResultTransformer(Transformers.aliasToBean(GreatMeeting.class)).list();
		}else{
			String sql = "SELECT COUNT(*) FROM AJ_GREAT_MEETING WHERE MTTYPE=? ";
			Object result = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, type).uniqueResult();
			if(null != result) {
				total = Integer.parseInt(result.toString());
			}
			if(total < size){
				size = total;
			}
			if((page-1)*size >= total){
				page = page-1;
			}
			String sqllist = "SELECT a.ID, a.Title, a.MTTYPE, a.MTINITIATOR, a.LOADFILE, a.FGLDSH, a.ZYLDSH, a.OPINION, a.MTDESC, a.HYFGLDSHYJ, a.HYZYLDSHYJ,  a.SXJSRQ, a.KYRY, a.HYSJ ,b.WORKID,c.ID AS TRACKID "
					+ "FROM AJ_GREAT_MEETING a LEFT OUTER JOIN twr_hz_instance b ON a.ID=b.DATAID "
					+ "LEFT OUTER JOIN tw_hz_track c ON b.WORKID=c.WORKID WHERE a.MTTYPE=? ORDER BY a.ID DESC LIMIT ?,?";
			list = sessionFactory.getCurrentSession().createSQLQuery(sqllist).setParameter(0, type).setParameter(1, (page-1)*size).setParameter(2, size).setResultTransformer(Transformers.aliasToBean(GreatMeeting.class)).list();
		}
		map.clear();
		map.put("total", total);
		map.put("list", list);
		return map;
	}

	public GreatMeeting queryById(String id) {
		String sql = "SELECT * FROM AJ_GREAT_MEETING WHERE ID=?";
		GreatMeeting uniqueResult = (GreatMeeting) sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, id).setResultTransformer(Transformers.aliasToBean(GreatMeeting.class)).uniqueResult();
		return uniqueResult;
	}
	//查询所有三重一大的办理记录
	public List<WorkList> queryWorkListAll(String tmtype, String start, String end) {
		String sql = "SELECT a.SENDTIME,a.NODEID,a.LIMITTIME,a.DOTIME,a.`STATUS` FROM tw_hz_worklist a LEFT  OUTER JOIN twr_hz_instance b ON a.WORKID=b.WORKID LEFT  OUTER JOIN aj_great_meeting c ON b.DATAID=c.ID WHERE c.MTTYPE=? AND a.SENDTIME>? AND a.SENDTIME<?";
		@SuppressWarnings("unchecked")
		List<WorkList> list = sessionFactory.getCurrentSession().createSQLQuery(sql)
		.setParameter(0, tmtype)
		.setParameter(1, start)
		.setParameter(2, end).setResultTransformer(Transformers.aliasToBean(WorkList.class)).list();
		return list;
	}

}
