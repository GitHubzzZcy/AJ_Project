package com.henghao.news.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.news.entity.Dept;
import com.henghao.news.service.RedisService;
import com.henghao.news.service.RedisTestService;
import com.henghao.news.util.Result;
import com.henghao.news.util.SerializeUtil;

@Controller
@RequestMapping("/redis")
public class RedisTestController {

	@Resource
	private RedisService redisService;
	@Resource
	private RedisTestService redisTestService;
	
	@RequestMapping(value = "/test", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryRedisTest(String type) {
		System.out.println("进入****");
		try {
			String value = redisService.get(type);
			if(value != null) {
				return new Result(0," 直接走缓存",value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//业务逻辑
		String string = redisService.get("hah");
		System.out.println(string);
		String desc = redisTestService.queryRedisTest();
		
		try {
			redisService.set(type, desc, 30);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(0," 直接走数据库",desc);
	}
	
	@RequestMapping(value = "/saveObject", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryTestSaveObject() {
		List<Dept> list = new ArrayList<Dept>();
		List<Dept> relist = new ArrayList<Dept>();
		Dept d = new Dept();
		d.setDEPT_NAME("测试部门名");
		d.setID("1");
		Dept d2 = new Dept();
		d2.setDEPT_NAME("部门2");
		d2.setID("2");
		list.add(d);
		list.add(d2);
		for (int i=0; i<list.size(); i++) {
			byte[] serialize = SerializeUtil.serialize(d);
			System.out.println("serialize.toString()---->"+serialize.toString());
			redisService.set(("testObject"+i).getBytes(), serialize, 60);
			System.out.println("写入成功！！！");
		}
		for(int i=0; i<10; i++) {
			byte[] bs = redisService.get(("testObject*").getBytes());
			System.out.println("get--->"+bs);
			Dept unserialize;
			try {
				unserialize = (Dept) SerializeUtil.unserialize(bs);
				relist.add(unserialize);
			} catch (Exception e) {
				break;
			}
		}
			
		
		
		return new Result(0," 直接走缓存",relist);
	}
}
