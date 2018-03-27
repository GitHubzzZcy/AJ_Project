package com.henghao.service;

import java.util.Map;

import com.henghao.entity.Complain;
import com.henghao.util.PageBaen;
import com.henghao.util.Result;


public interface IComplainService {

	public Result loadAdd(Complain complain, String pathRoot);
	public Result findAll(String currentPage, String pageSize);
	public Result findByPhone(PageBaen pageBaen);
	public Result findById(String id);
	public Result consent(String id);
	public Result repulse(String id, String desc);
	public Result finish(String id);
	public Result queryFinish(String currentPage, String pageSize);
	public Result querySeek(String classs, String types, String condition, String currentPage, String pageSize);
	public Result comquery(String name,  String code);
	public Result tofinish(String id, String feedback);
	public Result evaluate(String id, String satisfaction, String evaluate);
	public Result statement();
	public Map<String, Object> queryUserGrade(String userName, String start, String end);
	

}
