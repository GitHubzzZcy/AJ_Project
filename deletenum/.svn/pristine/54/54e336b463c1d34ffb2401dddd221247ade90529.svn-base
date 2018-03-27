package com.henghao.dao.impl;

import org.springframework.stereotype.Repository;
import com.henghao.dao.EventInformationDao;
import com.henghao.entity.EventInformation;


@Repository
public class EventInformationDaoImpl extends BaseDao<EventInformation> implements EventInformationDao {

//	@Override
//	public void addAuditReservation() {
//		String sql = "INSERT INTO xfyy(DWMC,DWDZ,CONTACTS,CONTACTTEL,YYBM,STARTTIME,ENDTIME,BEIZHU,YYTYPE) VALUES(?,?,?,?,?,?,?,?,?)";
//		getSession().createSQLQuery(sql).addEntity(getClass());
//		System.out.println(sql+"--------------");
//	}



	@Override
	public void addEventInformation(EventInformation eventInformation) {
		String sql = "INSERT INTO aj_grgj(ID,USERID,TIME,SHIJIAN_EVENT,SHIJIAN_PLACE,SHIJIAN_TIME,TOKEN) VALUES(?,?,?,?,?,?)";
		getSession().createSQLQuery(sql).addEntity(getClass());
//		System.out.println(sql+"++++++++++++++");
		
	}



}
