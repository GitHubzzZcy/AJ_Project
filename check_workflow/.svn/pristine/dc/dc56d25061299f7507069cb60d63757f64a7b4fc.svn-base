package com.henghao.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.henghao.dao.Rest_dayDao;
import com.henghao.entity.Rest_day;
import com.henghao.util.DateUtils;
import com.henghao.util.PageBaen;
@Repository("RestDao")
public class Rest_dayDaoImp extends BaseDao<Rest_day> implements Rest_dayDao {
	@Override
	public void updateRestDay(Rest_day restDay) {
		super.saveOrUpdate(restDay);
	}

	@Override
	public void addRestDay(Rest_day restDay) {
		super.add(restDay);
	}

	@Override
	public void deleteRestDay(Rest_day restDay) {
		/*String hql="DELETE * FORM Rest_day WHERE id = ?";
		super.getSession().createSQLQuery(hql).setParameter(0, restDay.getId()).executeUpdate();
		System.out.println("删除成功:restDay="+ restDay.getId());*/
		super.getSession().delete(findRestDay(restDay.getId()));
	}

	@Override
	public Rest_day findRestDay(int id) {
		return super.findById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rest_day> findAllRestDay(Date startDate,Date endDate,PageBaen pageBaen) {
		int pageSize=pageBaen.getPageSize();
		int currentPage=pageBaen.getCurrentPage();
		int totals=super.findAll().size();
		if(totals < pageSize){
			pageSize = totals;
		}
		if((currentPage-1)*pageSize >= totals){
			currentPage = currentPage-1;
		}		
		String hql="FROM Rest_day WHERE date BETWEEN ? AND ? ";
		List<Rest_day> list=(List<Rest_day>)super.getSession().createQuery(hql).setParameter(0, startDate).setParameter(1, endDate).setFirstResult((currentPage-1)*pageSize).setMaxResults(pageSize).list();
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Rest_day isRestDay(Date date) {
		String hql="FROM Rest_day WHERE date = ?";
		List<Rest_day> list=(List<Rest_day>)super.getSession().createQuery(hql).setParameter(0, date).list();
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;//2017-6-6上午8:57:14宁万龙
	}

	@Override
	public int Restnum(String sdate, String edate) {
		String hql="FROM Rest_day WHERE date >= ? and date <=?";
		int num=0;
		try {
			num = super.getSession().createQuery(hql).setParameter(0, DateUtils.getCurrent(sdate)).setParameter(1,DateUtils.getCurrent(edate)).list().size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;//2017-6-6下午2:22:42宁万龙
	}
}
