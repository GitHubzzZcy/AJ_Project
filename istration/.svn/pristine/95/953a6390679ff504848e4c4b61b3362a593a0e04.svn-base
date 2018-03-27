package com.henghao.istration.dao;

import java.util.List;

import com.henghao.istration.entity.Backlog;
import com.henghao.istration.entity.Hzlog;
import com.henghao.istration.entity.Personnel;
import com.henghao.istration.entity.Record;

public interface IUserApeDao {
	
	public String queryuserid(String name);

	public String queryApprove(String name, String lcid, String date);
	public String  queryApproveNotFlow(String name,  String date);

	public String queryBacklog(String userid);

	public String queryFinish(String userid);

	public List<Backlog> queryOvertime(String userid);

	public String queryDeptno(String name);

	public String findDeptPs(String dept);

	public String queryDeptPromise(String deptnum, String ym);

	public String queryMonthFinish(String userid, String ym);

	public Personnel queryPersonnel(String name);

	public List<Hzlog> flowRoll(String time, String flowid);

	public List<String> queryNode(String flowid, String workid);

	public List<Record> queryGongzuolvli(String id);
	//查询退回
	public String querySendback(String name);

	

}
