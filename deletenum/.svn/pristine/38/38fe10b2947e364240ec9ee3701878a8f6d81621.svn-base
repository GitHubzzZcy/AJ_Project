package com.henghao.dao.impl;

import org.springframework.stereotype.Repository;

import com.henghao.dao.EventImageDao;
import com.henghao.entity.EventImage;


@Repository
public class EventImageDaoImpl extends BaseDao<EventImage> implements EventImageDao {

//	@Override
//	public void addAuditReservation() {
//		String sql = "INSERT INTO xfyy(DWMC,DWDZ,CONTACTS,CONTACTTEL,YYBM,STARTTIME,ENDTIME,BEIZHU,YYTYPE) VALUES(?,?,?,?,?,?,?,?,?)";
//		getSession().createSQLQuery(sql).addEntity(getClass());
//		System.out.println(sql+"--------------");
//	}

	/**
	 * 保存图片信息
	 */

	@Override
	public void addEventImage(EventImage eventImage) {
		String sql = "INSERT INTO aj_eventimag(ID,TOKEN,SAVE_NAME,FILENAME,FILETYPE,SAVE_TIME) VALUES(?,?,?,?,?)";
		getSession().createSQLQuery(sql).addEntity(getClass());
//		System.out.println(sql+"++++++++++++++");
	}



}
