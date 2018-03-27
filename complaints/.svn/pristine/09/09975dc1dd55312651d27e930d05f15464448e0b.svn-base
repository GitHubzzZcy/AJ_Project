package com.henghao.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.henghao.dao.IComplainDao;
import com.henghao.entity.Complain;


@Repository("complainDao")
public class ComplainDaoImpl extends BaseDao<Complain> implements IComplainDao {
	
	Map<String, Object> map = new HashMap<String, Object>();


	@Override
	public Map<String, Object> queryNotFinish(int currentPage, int pageSize) {
		
		
		String sql = "SELECT COUNT(*) FROM aj_cn_complain WHERE complainStatus <> '已结案' OR complainStatus IS NULL";
		String string = super.getSession().createSQLQuery(sql).uniqueResult().toString();
		int total = Integer.parseInt(string);
		if(total< pageSize){
			pageSize = total;
		 }
		 if((currentPage-1)*pageSize >= total){
			 currentPage = currentPage-1;
		 }
		 String sqllist = "SELECT * FROM aj_cn_complain WHERE complainStatus <> '已结案' OR complainStatus IS NULL ORDER BY id DESC";
		 @SuppressWarnings("unchecked")
		List<Complain> list = super.getSession().createSQLQuery(sqllist).
				 setResultTransformer(Transformers.aliasToBean(Complain.class)).
				 setFirstResult((currentPage-1)*pageSize).setMaxResults(pageSize).list();
		 map.put("total", total);
		 map.put("list", list);
		return map;
	}


	@Override
	public Map<String, Object> querySeek(String classs, String types, String condition, int currentPage, int pageSize) {
		condition = "%"+condition+"%";
		String sql="";
		String sqllist="";
		if("yijiean".equals(types)) {
			sql = "SELECT COUNT(*) FROM aj_cn_complain WHERE complainStatus='已结案' AND items=? AND (nophone LIKE ? OR objects LIKE ?)";
			sqllist = "SELECT * FROM aj_cn_complain WHERE complainStatus='已结案' AND items=?  AND (nophone LIKE ? OR objects LIKE ? )ORDER BY id DESC";
		}else{
			sql = "SELECT COUNT(*) FROM aj_cn_complain WHERE complainStatus <> '已结案' AND items=?  AND (nophone LIKE ? OR objects LIKE ?)";
			sqllist = "SELECT * FROM aj_cn_complain WHERE complainStatus <> '已结案' AND items=?  AND (nophone LIKE ? OR objects LIKE ?) ORDER BY id DESC";
		}
		String string = super.getSession().createSQLQuery(sql).setParameter(0, classs)
				.setParameter(1, condition).setParameter(2, condition).uniqueResult().toString();
		int total = Integer.parseInt(string);
		if(total< pageSize){
			pageSize = total;
		 }
		 if((currentPage-1)*pageSize >= total){
			 currentPage = currentPage-1;
		 }
		 @SuppressWarnings("unchecked")
		List<Complain> list = super.getSession().createSQLQuery(sqllist).setParameter(0, classs).
					setParameter(1, condition).setParameter(2, condition).
				 setResultTransformer(Transformers.aliasToBean(Complain.class)).
				 setFirstResult((currentPage-1)*pageSize).setMaxResults(pageSize).list();
		 map.put("total", total);
		 map.put("list", list);
		return map;
	}


	@Override
	public List<Complain> findByPhone(String name, String phone) {
		String sql = "SELECT * FROM aj_cn_complain WHERE complainName=? AND nophone=?";
		@SuppressWarnings("unchecked")
		List<Complain> list = super.getSession().createSQLQuery(sql).
				 setResultTransformer(Transformers.aliasToBean(Complain.class)).setParameter(0, name).setParameter(1, phone).list();
		return list;
	}


	@Override
	public String regionquery(String items, String region, String currentDate) {
		String sql = "SELECT COUNT(*) FROM aj_cn_complain A WHERE A.complainStatus='已结案' AND A.disposeDesc IS NULL AND A.items=? AND A.region=? AND DATE_FORMAT(A.complainTime,'%Y-%m')=? ";
		Object object = super.getSession().createSQLQuery(sql).setParameter(0, items).setParameter(1, region).setParameter(2, currentDate).uniqueResult();
		String count = object.toString();
		return count;
	}


	@Override
	public String monthquery(String ym) {
		String sql = "SELECT COUNT(*) FROM aj_cn_complain A WHERE DATE_FORMAT(A.complainTime,'%Y-%m')=? AND A.complainStatus='已结案' AND A.disposeDesc IS NULL" ;
		Object object = super.getSession().createSQLQuery(sql).setParameter(0, ym).uniqueResult();
		String count = object.toString();
		return count;
	}


	@Override
	public String yearquery(String items, String year) {
		String sql = "SELECT COUNT(*) FROM aj_cn_complain A WHERE A.items=?  AND DATE_FORMAT(A.complainTime,'%Y')=? AND A.complainStatus='已结案' AND A.disposeDesc IS NULL";
		Object object = super.getSession().createSQLQuery(sql).setParameter(0, items).setParameter(1, year).uniqueResult();
		String count = object.toString();
		return count;
	}


	//评分
	@Override
	public List<Complain> queryUserGrade(String userName, String start, String end) {
		//yyyy-MM-dd HH:mm:ss
		if(start.length() == 10) {
			start = start + " 00:00:00";
		}
		if(end.length() == 10) {
			end = end + " 23:59:59";
		}
		if(start.length() == 16) {
			start = start + ":00";
		}
		if(end.length() == 16) {
			end = end + ":00";
		}
		//查询同意立案和已结案且被拒绝理由为空
		String hql = "FROM Complain WHERE items='举报执法人' AND ((complainStatus='已结案' AND disposeDesc IS NULL) OR complainStatus='同意立案')   AND objects LIKE ? AND complainTime >= STR_TO_DATE(?, '%Y-%m-%d %H:%i:%s') AND complainTime < STR_TO_DATE(?, '%Y-%m-%d %H:%i:%s')";
		@SuppressWarnings("unchecked")
		List<Complain> list = super.getSession().createQuery(hql).setParameter(0, "%"+userName+"%").setParameter(1, start).setParameter(2, end).list();
		return list;
	}


}
