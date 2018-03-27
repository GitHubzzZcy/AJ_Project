package com.henghao.istration.dao;

import java.util.List;

import com.henghao.istration.entity.Example;
import com.henghao.istration.entity.Head;
import com.henghao.istration.entity.Hzlog;
import com.henghao.istration.entity.Promise;
import com.henghao.istration.entity.Transactor;

public interface IApproveDao {

	public List<Object> queryApproveClass();

	public List<Example> queryfirmname(String flowname);

	public String queryWorkid(String applyid);

	public List<Hzlog> queryLog(String workid);

	public Head userhead(String userid);

	public String queryUser(String string);

	public List<Example> queryfirmAll();


	public Transactor queryblr(String string);

	public String before(String ym);

	public String promise(String currentDate, String currentDate2);
	public List<Promise> promise(String yer);

}
