package com.henghao.dao;

import java.util.List;

import com.henghao.entity.Accident;
import com.henghao.entity.ExamineFirm;
import com.henghao.entity.Inspect;
import com.henghao.entity.Plan;
import com.henghao.entity.Roll;

public interface IInspDao {

	public String getUserId(String name);
	public String getUserName(String userid);
	
	public String queryCount(String start, String ent);

	public String querymm(String start, String ent);

	public String queryDay(String yymd);

	public String querychao();

	public String queryundone();

	public String eachMonth(String id, String start, String format);

	public String eachQuarter(String string, String name, String stardate,
			String enddate);

	public String eachClassify(String userid, String code, String startdate,
			String currentDate);

	public List<ExamineFirm> eachLately(String userId, String startdate,
			String enddate);

	public List<Inspect> inspectOA(String userId, String starttime, String currentDate);

	public Accident queryAccident(String id);

	public String monthOrder(String userId, String start, String format,
			String string);
	public List<Roll> queryroll(String userId, String start, String end);
	//查询企业最新执法数据
	public List<Accident> queryAllAccident();
	public ExamineFirm queryEntname(String enterpriseid);
	public String queryDai(String userid);
	public String queryZc(String userId);
	public String queryCq(String userId);
	public List<Plan> queryPlan();

	

	


}
