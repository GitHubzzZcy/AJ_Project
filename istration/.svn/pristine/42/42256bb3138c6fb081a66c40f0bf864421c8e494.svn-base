package com.henghao.news.service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.henghao.news.dao.MeetingUploadDao;
import com.henghao.news.entity.MeetingUploadEntity;
import com.henghao.news.util.DateUtils;

@Service
public class MeetingUploadService {

	@Resource
	private MeetingUploadDao meetingUploadDao;
	@Value("${MEETINGIMAGE}")
	private String MEETINGIMAGE;//图片地址在filepath.properties中
	
	//会议上传新增实体
	public Object addMeetingUploadEntity(MeetingUploadEntity gson, MultipartFile[] files) throws IllegalStateException, IOException {
		File file = new File(MEETINGIMAGE);
		if(!file.exists()) {
			file.mkdirs();
		}
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for (MultipartFile multipartFile : files) {
			String filename = multipartFile.getOriginalFilename();
			filename = filename.substring(filename.lastIndexOf("."));
			String path = MEETINGIMAGE + "/" + DateUtils.getCurrentDate("yyyyMMdd-HHmmss") + i + filename;
			String dbimagename = DateUtils.getCurrentDate("yyyyMMdd-HHmmss")+i+filename;
			File imgepath = new File(path);
			multipartFile.transferTo(imgepath);
			sb.append(dbimagename + ",");
			i++;
		}
		String meetingUploadImagePath = null;
		if(sb.length() > 0) {
			meetingUploadImagePath = sb.substring(0, sb.length()-1);
		}
		gson.setMeetingUploadImagePath(meetingUploadImagePath);
		meetingUploadDao.add(gson);
		return gson.getMuid();
	}

	public Object queryMeetingUploadEntityList(int page, int size) {
		Map<String, Object> map = meetingUploadDao.queryMeetingUploadEntityList(page, size);
		return map;
	}
	

}
