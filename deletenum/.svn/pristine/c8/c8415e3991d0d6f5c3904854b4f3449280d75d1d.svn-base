package com.henghao.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IUserEventTrackService {

	Object addEntity(String userId, String eventName, String eventAddress, String eventTime, String eventEndTime, String eventDate,
			MultipartFile[] files) throws IllegalStateException, IOException;

	Object queryByUserIdAndDate(String userId, String eventDate);

}
