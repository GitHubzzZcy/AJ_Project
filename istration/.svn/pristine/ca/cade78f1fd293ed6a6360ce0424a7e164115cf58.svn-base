package com.henghao.news.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.henghao.news.dao.RandomAwardDao;

@Service
public class RandomAwardService {

	@Resource
	private RandomAwardDao randomAwardDao;
	public List<?> queryUserAward() {
		List<?> list = randomAwardDao.queryUserAward();
		return list;
	}
	public Map<String, Object> updateAwardGrade(String name, int awardGrade) {
		randomAwardDao.updateAwardGrade(name, awardGrade);
		return null;
	}

}
