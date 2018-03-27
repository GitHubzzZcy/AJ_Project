package com.henghao.dao.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.henghao.dao.IWorkTimeDao;
import com.henghao.entity.WorkTime;
import com.henghao.util.DateUtils;

@Repository
public class WorkTimeDao extends BaseDao<WorkTime> implements IWorkTimeDao{
	//设置考勤时间段
	public Map<String, Object> setWorkTime(Integer deptNo,String starTime,String endTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		/*WorkTime wk=super.findById(1);
		System.out.println(wk.getClockIn()+"---"+wk.getClockOut());
		try {
			wk.setClockIn(DateUtils.parseDateFromString(starTime,"HH:mm"));
			wk.setClockOut(DateUtils.parseDateFromString(endTime,"HH:mm"));
			System.out.println(wk.getClockIn()+"---"+wk.getClockOut());
			super.update(wk);
		} catch (ParseException e) {
			System.out.println("时间异常");
			e.printStackTrace();
		}*/
		String hql="update WorkTime set clockIn=?,clockOut=? where id=1";
		try {
			super.getSession().createQuery(hql).setParameter(0, DateUtils.parseDateFromString(starTime,"HH:mm")).setParameter(1, DateUtils.parseDateFromString(endTime,"HH:mm")).executeUpdate();
		} catch (ParseException e) {
			System.out.println("时间异常");
			e.printStackTrace();
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public WorkTime findWorktime(String userid) throws Exception {
		String findusersql="SELECT THU.ID,THU.`NAME`,THD.DEPT_NAME FROM to_horizon_user THU LEFT JOIN tor_horizon_user_dept THUD ON THU.ID = THUD.USER_ID LEFT JOIN to_horizon_dept THD ON THUD.DEPT_ID = THD.ID WHERE THU.ID = ?";
		List<Object[]> userInfo=(List<Object[]>)super.getSession().createSQLQuery(findusersql).setParameter(0, userid).list();
		//获取用户的姓名和部门去查找时间段
		String userName,userDept;
		//如果用户不存在默认所有人
		if(userInfo == null || userInfo.isEmpty()){
			userName = "_";
			userDept = "_";
		}else{
			userName = (String) userInfo.get(0)[1];
			userDept = (String) userInfo.get(0)[2];
		}
		List<Object[]> list1 = this.findWorktimeByName(userName);
		WorkTime worktime = super.findById(1);
		String clockin;
		String clockout;		
		//如果没有姓名的考勤时间段分配
		if(list1 == null || list1.isEmpty()){
			List<Object[]> list2=this.findWorktimeByDept(userDept);
			//如果也没有部门的考勤时间段分配
			if(list2==null || list2.isEmpty()){
				List<Object[]> list3=this.findFirstWorktime();
				//如果也没有任何时间设置,默认给下面时间
				if(list2==null || list2.isEmpty()){
					clockin="09:00";
					clockout="17:00";
				}else{	//如果有
					clockin= (String) list3.get(0)[0];
					clockout= (String) list3.get(0)[1];
				}

			}else{	////如果有部门的考勤时间段分配
				clockin= (String) list2.get(0)[0];
				clockout= (String) list2.get(0)[1];
			}
		}else{	//如果有姓名的考勤时间段分配
			clockin= (String) list1.get(0)[0];
			clockout= (String) list1.get(0)[1];
		}	
		Date ed =DateUtils.getCurrentDateT("HH:mm:ss");
		Date sd=new Date();	
		ed=DateUtils.getHHmm(clockout);
		sd=DateUtils.getHHmm(clockin);
		Date zjd=DateUtils.getMiddleTime(sd, ed);
		worktime.setMiddleTime(zjd);
		worktime.setClockIn(sd);
		worktime.setClockOut(ed);				
		return worktime;//2017-6-8下午2:21:36宁万龙
	}
/*	@SuppressWarnings("unchecked")
	@Override
	public WorkTime findById(int id) {
		String sql="SELECT cw.CLOCKIN,cw.CLOCKOUT FROM ci_worktime cw LIMIT 1";
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql).list();
		String clockin= (String) list.get(0)[0];
		String clockout= (String) list.get(0)[1];
		try {
			Date ed=DateUtils.getHHmm(clockout);	
			Date sd=DateUtils.getHHmm(clockin);
			System.out.println(ed+"ed,sd"+sd);
			Date zjd=DateUtils.getMiddleTime(sd, ed);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return super.findById(id);//2017-6-8下午4:52:07宁万龙
	}*/
	//按姓名查询考勤时间段
	@SuppressWarnings("unchecked")
	public List<Object[]> findWorktimeByName(String name){
		String sql="SELECT CLOCKIN,CLOCKOUT FROM ci_worktime"; //cw WHERE cw.BCRY LIKE ? ORDER BY cw.SXRQ DESC";
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql)/*.setParameter(0, "%"+name+"%")*/.list();
		return list;
	}
	//按部门查询考勤时间段
	@SuppressWarnings("unchecked")
	public List<Object[]> findWorktimeByDept(String dept){
		String sql="SELECT cw.CLOCKIN,cw.CLOCKOUT FROM ci_worktime cw WHERE cw.BCBM LIKE ? ORDER BY cw.SXRQ DESC";
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql).setParameter(0, "%"+dept+"%").list();
		return list;
	}
	//获取第一条考勤时间段
	@SuppressWarnings("unchecked")
	public List<Object[]> findFirstWorktime(){
		String sql="SELECT cw.CLOCKIN,cw.CLOCKOUT FROM ci_worktime cw LIMIT 1";
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql).list();
		return list;
	}

	
}
