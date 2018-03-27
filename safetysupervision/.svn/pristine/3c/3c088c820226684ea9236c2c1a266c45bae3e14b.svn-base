package com.henghao.service.impl;

import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.henghao.dao.Rest_dayDao;
import com.henghao.entity.Rest_day;
import com.henghao.service.Rest_dayService;
@Service
public class Rest_dayServiceImp implements Rest_dayService {
	@Resource
	private Rest_dayDao RestDao;
	
	@Override
	public Rest_day isRestDay(Date date) {
		Rest_day rd = null;
		try {
			rd = RestDao.isRestDay(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	
}
