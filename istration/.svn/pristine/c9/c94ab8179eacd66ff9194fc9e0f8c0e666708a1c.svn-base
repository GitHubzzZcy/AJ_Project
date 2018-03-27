package com.henghao.news.util;
import java.util.TimerTask;



import com.henghao.istration.util.Urljson;
import com.henghao.news.service.RedisService;
  
public class Task extends TimerTask {  
	private static final String IP = "http://127.0.0.1:8080/istration";
		//缓存时间为一天
	@Override
	public void run() {
			RedisService redisService = (RedisService) SpringBeanUtil.getBeanByName("redisService");
			String querytypecount = Urljson.readJsonRedisUrl(IP+"/greatmeeting/querytypecount");
			System.out.println("querytypecount"+querytypecount);
			System.out.println("redisService:"+redisService);
		
    }
} 