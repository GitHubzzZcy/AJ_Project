package com.henghao.news.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.henghao.news.entity.MeetingUploadEntity;
@Repository
public class MeetingUploadDao extends BaseDaoImpl<MeetingUploadEntity> {

	private Map<String, Object> remap = new HashMap<String, Object>();
	public Map<String, Object> queryMeetingUploadEntityList(int page, int size) {
		String hqlcount = "SELECT COUNT(*) FROM MeetingUploadEntity";
		String string = super.getSession().createQuery(hqlcount).uniqueResult().toString();
		int total = Integer.parseInt(string);
		if((page-1) * size >= total) {
			page = page-1;
		}
		if(size > total) {
			size = total;
		}
		int start = (page-1) * size;
		String hql = "FROM MeetingUploadEntity ORDER BY muid DESC";
		@SuppressWarnings("unchecked")
		List<MeetingUploadEntity> list = super.getSession().createQuery(hql)
				.setFirstResult(start).setMaxResults(size).list();
		remap.put("total", total);
		remap.put("list", list);
		return remap;
	}

}
