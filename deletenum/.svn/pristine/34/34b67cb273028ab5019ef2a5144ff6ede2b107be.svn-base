package com.henghao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henghao.dao.EventImageDao;
import com.henghao.dao.EventInformationDao;
import com.henghao.entity.EventImage;
import com.henghao.entity.EventInformation;
import com.henghao.service.EventInformationAndImageService;

/**
 * 事件信息及图片保存
 * @author Administrator
 *
 */
@Service("EventInformationAndImageService")
public class EventInformationAndImageServiceImpl implements EventInformationAndImageService {
	@Autowired
	private EventImageDao eventImageDao;
	@Autowired
	private EventInformationDao eventInformationDao;
	
	/**
	 * 保存图片信息
	 */
	@Override
	public void addEventImage(EventImage eventImage) {
		eventImageDao.add(eventImage);
		
	}
	/**
	 * 保存事件信息
	 */
	@Override
	public void addEventInformation(EventInformation eventInformation) {
		eventInformationDao.add(eventInformation);
		
	}
	
	
	

}
