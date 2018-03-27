package com.henghao.news.service;

import org.springframework.stereotype.Service;

@Service
public class RedisTestService {

	public String queryRedisTest() {
		System.out.println("进入业务逻辑查询数据库打印sql");
		return "[{\"late\":0,\"early\":0}]";
	}

}
