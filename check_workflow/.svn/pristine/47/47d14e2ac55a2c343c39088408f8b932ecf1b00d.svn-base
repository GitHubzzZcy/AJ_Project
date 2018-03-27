package com.henghao.dao.impl;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.henghao.dao.ICheckingDao;
import com.henghao.dao.Rest_dayDao;
import com.henghao.entity.Checking;
import com.henghao.entity.Kaoqin;
import com.henghao.entity.Rest_day;

import com.henghao.service.Rest_dayService;
import com.henghao.util.DateUtils;
import com.henghao.util.PageBaen;


@Repository("Dao")
public class CheckingDao extends BaseDao<Checking> implements ICheckingDao{

	@Resource
	private Rest_dayService restService;
	@Resource
	private Rest_dayDao RestDao;//RestDao.Restnum("2017-01-06", "2017-06-12")
	/**
	 * 该方法safetysupervision项目的CheckingDao有复制相同的，如果该方法修改，应手动复制同步到那边去
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> findAllByUserName(String startDate,String endDate,PageBaen pageBaen,String user) {
		//如果工作流保存的是姓名，后面带 "%" 的模糊查询全部换为userName即可
		//获取用户名
		String userName=this.getUserNameByUserId(user);
		super.getSession().flush();	
		Map<String,Object> map=new HashMap<String, Object>();
		String sql01="SELECT * FROM ( SELECT AC.STARTTIME AS STARDATE, AC.ENDTIME AS ENDDATE, AC.STARTTIME AS CLOCKINTIME, AC.ENDTIME AS CLOCKOUTTIME, AC.CHUCHAI_SY AS CKSTATUS, AC.SITE AS OTHER FROM aj_chuchai AC WHERE AC.CHUCHAI_RY LIKE ? AND ( AC.AGREE1 = '同意' OR ( AC.AGREE = '同意' AND AC.`DAY` = '1' )) UNION ALL SELECT AB.BQTIME AS STARDATE, AB.ENDTIME AS ENDDATE, AB.BQTIME AS CLOCKINTIME, AB.ENDTIME AS CLOCKOUTTIME, AB.BQTYPE AS CKSTATUS, AB.BQDESC AS OTHER FROM aj_buqian AB WHERE AB.`NAME` LIKE ? AND ( AB.AGREE = '同意' OR ( AB.AGREE1 = '同意' AND AB. DAY = '1' )) UNION ALL SELECT AQ.STARTTIME AS STARDATE, AQ.ENDTIME AS ENDDATE, AQ.STARTTIME AS CLOCKINTIME, AQ.STARTTIME AS CLOCKOUTTIME, AQ.LEAVECAUSE AS CKSTATUS, AQ.LEAVEDESC AS OTHER FROM aj_qingjia AQ WHERE AQ.`NAME` LIKE ? AND ( AQ.AGREE = '同意' OR ( AQ.AGREE1 = '同意' AND AQ.LEAVEDAY = '1' )) UNION ALL SELECT CC.currentDate AS STARDATE, CC.currentDate AS ENDDATE, CC.clockInTime AS CLOCKINTIME, CC.clockOutTime AS CLOCKOUTTIME, CC.checkType AS CKSTATUS, CC.currentDate AS OTHER FROM ci_checkingin CC WHERE CC.userId =? ) KQ WHERE ( KQ.STARDATE >= ? AND KQ.STARDATE <= ? ) OR ( KQ.ENDDATE <= ? AND KQ.ENDDATE >= ? ) OR ( KQ.STARDATE <= ? AND KQ.ENDDATE >= ? ) ORDER BY KQ.STARDATE ASC";
		List<Object[]> list1=(List<Object[]>)super.getSession().createSQLQuery(sql01)				
				.setParameter(0, "%"+userName+"%")
				.setParameter(1, "%"+userName+"%")
				.setParameter(2, "%"+userName+"%")
				.setParameter(3, user)
				.setParameter(4, startDate)
				.setParameter(5, endDate)
				.setParameter(6, endDate)
				.setParameter(7, startDate)
				.setParameter(9, endDate)
				.setParameter(8, startDate)				
				.list();	
		List<Kaoqin> list2=new ArrayList<Kaoqin>();	
		for (Object[] obj : list1) {
			Kaoqin okq=new Kaoqin();		
			okq.setSTARDATE((String)obj[0]);
			okq.setENDDATE((String)obj[1]);
			okq.setCLOCKINTIME((String)obj[2]);
			okq.setCLOCKOUTTIME((String)obj[3]);			
			okq.setCKSTATUS((String)obj[4]);
			okq.setOTHER((String)obj[5]);
			list2.add(okq);
		}
			
		/**
		 * linux系统修改
		 */
		//不能group by去重,手动去重
		List<Kaoqin> list=new ArrayList<Kaoqin>();
		for (int i = 0; i < list2.size(); i++) {
			//用来接收开始时间字符串
			List<String> lstr = new ArrayList<String>();
			//如果已经添加了该时间的，继续下一条
			if(lstr.contains(list2.get(i).getSTARDATE())){
				continue;
			}else{
				lstr.add(list2.get(i).getSTARDATE());
				list.add(list2.get(i));
			}
		}
		


		//kqlist来接收时间段内的全部考勤信息
		List<Kaoqin> kqlist=new ArrayList<Kaoqin>();
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			//设置开始时间为日历startDate格式2017-11
			Calendar startcal=Calendar.getInstance();
			startcal.setTime(sdf.parse(startDate));
			//设置结束时间为时间格式
			Date endcal = DateUtils.parseDateFromString(endDate, "yyyy-MM-dd");			
			//Date endcal=sdf.parse(endDate);		
			//如果开始时间startcal在结束时间之后就就结束循环比较
			//获取第一条结果的开始时间，如果小于当月则从结束时间开始
			if(!list.isEmpty()){				
				Date onedate = DateUtils.parseDateFromString(list.get(0).getSTARDATE(), "yyyy-MM-dd");
				//onedate 比 startcal.getTime() 早，才返回 true
				if(onedate.before(startcal.getTime())){	
					Kaoqin okq=new Kaoqin();		
					okq.setSTARDATE((String)list.get(0).getSTARDATE());
					okq.setENDDATE((String)list.get(0).getENDDATE());
					okq.setCLOCKINTIME((String)list.get(0).getCLOCKINTIME());
					okq.setCLOCKOUTTIME((String)list.get(0).getCLOCKOUTTIME());			
					okq.setCKSTATUS((String)list.get(0).getCKSTATUS());
					okq.setOTHER((String)list.get(0).getOTHER());
					kqlist.add(okq);
					startcal.setTime(DateUtils.parseDateFromString(list.get(0).getENDDATE(), "yyyy-MM-dd"));
				}
			}	
			List<String> time = new ArrayList<String>();
			for (int i=0;i<list.size();i++) {
				String stardate = list.get(i).getSTARDATE();
				time.add(stardate);				
			}
			while(!startcal.getTime().after(endcal)){	 //TODO										
				//如果一个月都没有记录即list=null
				if(list.isEmpty()){	
					//查询判断这一天是不是节假日或周末
					Rest_day rd=restService.isRestDay(DateUtils.getFormatedDate(DateUtils.getCurrentymd(startcal.getTime()), "yyyy-MM-dd"));
					if(rd != null){
						Kaoqin kq=new Kaoqin();
						//设置日期和星期
						kq.setWEEKEND(DateUtils.getWeekNum(startcal.getTime()));
						kq.setSTARDATE(DateUtils.getFormatedDate(startcal.getTime(),"yyyy-MM-dd"));
						kq.setENDDATE(DateUtils.getFormatedDate(startcal.getTime(),"yyyy-MM-dd"));
						kqlist.add(kq);
						startcal.add(Calendar.DAY_OF_MONTH, 1);
						continue;
					}else{
						//如果无上情况，就是旷工执行一下操作
						Kaoqin kq=new Kaoqin();
						//设置日期和星期 ->旷工
						kq.setWEEKEND(DateUtils.getWeekNum(startcal.getTime()));
						kq.setSTARDATE(DateUtils.getFormatedDate(startcal.getTime(),"yyyy-MM-dd"));
						kq.setENDDATE(DateUtils.getFormatedDate(startcal.getTime(),"yyyy-MM-dd"));
						kq.setCKSTATUS("1");//没有任何信息的标准
						kq.setCKSTATUS("旷工");
						kqlist.add(kq);
					}
//					Kaoqin zkq=new Kaoqin();
//					//设置日期和星期
//					zkq.setWEEKEND(DateUtils.getWeekNum(startcal.getTime()));
//					zkq.setSTARDATE(DateUtils.getFormatedDate(startcal.getTime(),"yyyy-MM-dd"));
//					zkq.setENDDATE(DateUtils.getFormatedDate(endcal,"yyyy-MM-dd"));
//					zkq.setWEEKEND(DateUtils.getWeekNum(startcal.getTime()));
//					zkq.setCKSTATUS("1");//没有任何信息的标准
//					zkq.setOTHER("没有时间段的任何信息");
//					kqlist.add(zkq);
					//break;

				}else{//如果有数据				
					//如果没有这一天的时间则周末或旷工
					//20170606修改周末为节假日（休息日）。
					if(!time.contains(DateUtils.getCurrentymd(startcal.getTime()))) { //startcal这一天没有签到信息
						//查询判断这一天是不是节假日或周末
						Rest_day rd=restService.isRestDay(DateUtils.getFormatedDate(DateUtils.getCurrentymd(startcal.getTime()), "yyyy-MM-dd"));
						if(rd != null){
							Kaoqin kq=new Kaoqin();
							//设置日期和星期
							kq.setWEEKEND(DateUtils.getWeekNum(startcal.getTime()));
							kq.setSTARDATE(DateUtils.getFormatedDate(startcal.getTime(),"yyyy-MM-dd"));
							kq.setENDDATE(DateUtils.getFormatedDate(startcal.getTime(),"yyyy-MM-dd"));
							kqlist.add(kq);
							startcal.add(Calendar.DAY_OF_MONTH, 1);
							continue;
						}else{
							//如果无上情况，就是旷工执行一下操作
							Kaoqin kq=new Kaoqin();
							//设置日期和星期 ->旷工
							kq.setWEEKEND(DateUtils.getWeekNum(startcal.getTime()));
							kq.setSTARDATE(DateUtils.getFormatedDate(startcal.getTime(),"yyyy-MM-dd"));
							kq.setENDDATE(DateUtils.getFormatedDate(startcal.getTime(),"yyyy-MM-dd"));
							kq.setCKSTATUS("旷工");
							kqlist.add(kq);
						}
					}else{//如果这一天有签到信息
						//查找对应的考勤信息
						for (Kaoqin kaoq : list) {
							//遍历找到这一天的数据
							if(kaoq.getSTARDATE().equals(DateUtils.getCurrentymd(startcal.getTime()))){
								kaoq.setWEEKEND(DateUtils.getWeekNum(startcal.getTime()));
								//如果结束时间不是空
								if(! (kaoq.getENDDATE()==null || kaoq.getENDDATE().equals(""))){
									startcal.setTime(sdf.parse(kaoq.getENDDATE()));
								}
								kqlist.add(kaoq);
								break;
							}

						}
					}
				}
				startcal.add(Calendar.DAY_OF_MONTH, 1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		map.put("list", kqlist);
		return map;
	}

	//根据用户id查询当天的签到记录
	public Map<String, Object> findByUserId(String userId,String date) {		
		String hql = "FROM Checking WHERE currentDate=? AND userId=?";
		Checking checking = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			checking = (Checking) super.getSession().createQuery(hql).setParameter(0, DateUtils.parseDateFromString(date, "yyyy-MM-dd")).setParameter(1, userId).uniqueResult();
			super.getSession().clear();
			map.put("list", checking);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findQjListByUserId(String userId,
			String startDate, String endDate, PageBaen pageBaen) {
		//如果工作流保存的是姓名，后面带 "%" 的模糊查询全部换为userName即可
		//获取用户名
		String userName=this.getUserNameByUserId(userId);

		Map<String, Object> map = new HashMap<String, Object>();
		String sql="SELECT AQ.STARTTIME AS STARDATE, AQ.ENDTIME AS ENDDATE, AQ.STARTTIME AS WEEKEND, AQ.STARTTIME AS CLOCKINTIME, AQ.STARTTIME AS CLOCKOUTTIME, AQ.LEAVECAUSE AS CKSTATUS, AQ.LEAVEDESC AS OTHER  FROM aj_qingjia AQ WHERE AQ.`NAME` LIKE ? AND (AQ.AGREE = '同意' OR (AQ.AGREE1 = '同意' AND AQ.LEAVEDAY='1')) AND ((AQ.STARTTIME >= ? AND AQ.STARTTIME <= ?) OR (AQ.ENDTIME >= ? AND AQ.ENDTIME <= ?) OR (AQ.STARTTIME < ? AND AQ.ENDTIME > ?))";
		List<Object[]> list1=(List<Object[]>)super.getSession().createSQLQuery(sql).setParameter(0, "%"+userName+"%").setParameter(1, startDate).setParameter(2, endDate).setParameter(3, startDate).setParameter(4, endDate).setParameter(5, startDate).setParameter(6, endDate).list();
		List<Kaoqin> list=new ArrayList<Kaoqin>();	
		for (Object[] obj : list1) {
			Kaoqin okq=new Kaoqin();		
			okq.setSTARDATE((String)obj[0]);
			okq.setENDDATE((String)obj[1]);
			/*okq.setCLOCKINTIME((String)obj[3]);
			okq.setCLOCKOUTTIME((String)obj[4]);*/			
			okq.setCKSTATUS((String)obj[5]);
			okq.setOTHER((String)obj[6]);			
			try {
				okq.setWEEKEND(DateUtils.getWeekNum(DateUtils.getCurrent((String)obj[0])));
			} catch (Exception e) {
				e.printStackTrace();
			}
			list.add(okq);
		}
		map.put("list", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findCcListByUserId(String userId,
			String startDate, String endDate, PageBaen pageBaen) {
		//如果工作流保存的是姓名，后面带 "%" 的模糊查询全部换为userName即可
		//获取用户名
		String userName=this.getUserNameByUserId(userId);

		Map<String, Object> map = new HashMap<String, Object>();
		String sql="SELECT * FROM (SELECT AC.STARTTIME AS STARDATE, AC.ENDTIME AS ENDDATE, WEEKDAY(AC.STARTTIME) AS WEEKEND, AC.STARTTIME AS CLOCKINTIME, AC.ENDTIME AS CLOCKOUTTIME, AC.CHUCHAI_SY AS CKSTATUS, AC.SITE AS OTHER FROM aj_chuchai AC WHERE AC.CHUCHAI_RY LIKE ? AND (AC.AGREE1 = '同意' OR (AC.AGREE = '同意' AND AC.`DAY` = '1'))) KQ WHERE ((KQ.STARDATE >= ? AND KQ.STARDATE <= ?) OR (KQ.ENDDATE >= ? AND KQ.ENDDATE <= ?) OR (KQ.STARDATE < ? AND KQ.ENDDATE > ?))";
		List<Object[]> list1=(List<Object[]>)super.getSession().createSQLQuery(sql).setParameter(0, "%"+userName+"%").setParameter(1, startDate).setParameter(2, endDate).setParameter(3, startDate).setParameter(4, endDate).setParameter(5, startDate).setParameter(6, endDate).list();
		List<Kaoqin> list=new ArrayList<Kaoqin>();	
		for (Object[] obj : list1) {
			Kaoqin okq=new Kaoqin();		
			okq.setSTARDATE((String)obj[0]);
			okq.setENDDATE((String)obj[1]);
			/*okq.setCLOCKINTIME((String)obj[3]);
			okq.setCLOCKOUTTIME((String)obj[4]);*/			
			okq.setCKSTATUS((String)obj[5]);
			okq.setOTHER((String)obj[6]);			
			try {
				okq.setWEEKEND(DateUtils.getWeekNum(DateUtils.getCurrent((String)obj[0])));
			} catch (Exception e) {
				e.printStackTrace();
			}
			list.add(okq);
		}
		map.put("list", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findBqListByUserId(String userId,
			String startDate, String endDate, PageBaen pageBaen) {
		//如果工作流保存的是姓名，后面带 "%" 的模糊查询全部换为userName即可
		//获取用户名
				String userName=this.getUserNameByUserId(userId);

		Map<String, Object> map = new HashMap<String, Object>();
		String sql="SELECT * FROM (SELECT AB.BQTIME AS STARDATE, AB.ENDTIME AS ENDDATE, AB.BQTIME AS WEEKEND, AB.BQDATE AS CLOCKINTIME,  AB.BQDATE AS CLOCKOUTTIME, AB.BQTYPE AS CKSTATUS, AB.BQDESC AS OTHER FROM aj_buqian AB WHERE AB.`NAME` LIKE ? AND (AB.AGREE = '同意' OR (AB.AGREE1 = '同意' AND AB.DAY = '1'))) KQ WHERE ((KQ.STARDATE >= ? AND KQ.STARDATE <= ?) OR (KQ.ENDDATE >= ? AND KQ.ENDDATE <= ?)  OR (KQ.STARDATE < ? AND KQ.ENDDATE > ?))";			
		List<Object[]> list1=(List<Object[]>)super.getSession().createSQLQuery(sql).setParameter(0, "%"+userName+"%").setParameter(1, startDate).setParameter(2, endDate).setParameter(3, startDate).setParameter(4, endDate).setParameter(5, startDate).setParameter(6, endDate).list();
		List<Kaoqin> list=new ArrayList<Kaoqin>();	
		for (Object[] obj : list1) {
			Kaoqin okq=new Kaoqin();		
			okq.setSTARDATE((String)obj[0]);
			okq.setENDDATE((String)obj[1]);
			/*okq.setCLOCKINTIME((String)obj[3]);
			okq.setCLOCKOUTTIME((String)obj[4]);*/			
			okq.setCKSTATUS((String)obj[5]);
			okq.setOTHER((String)obj[6]);			
			try {
				okq.setWEEKEND(DateUtils.getWeekNum(DateUtils.getCurrent((String)obj[0])));
			} catch (Exception e) {
				e.printStackTrace();
			}

			list.add(okq);
		}		
		map.put("list", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findCdListByUserId(String userId,
			String startDate, String endDate, PageBaen pageBaen,String chInTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		/**
		 * 20170608修改不用每次接收chInTime来判断迟到
		 * 原sql="SELECT * FROM ci_checkingin CC WHERE CC.userId=? AND CC.clockInTime > ? AND  CC.currentDate >= ? AND CC.currentDate <= ?"
		 */
		String sql="SELECT * FROM ci_checkingin CC WHERE CC.userId=? AND  CC.currentDate >= ? AND CC.currentDate <= ? AND CC.checkLocation LIKE '迟到%'";			
		List<Checking> list=(List<Checking>)super.getSession().createSQLQuery(sql)
				.setParameter(0, userId).setParameter(1, startDate).setParameter(2, endDate).setResultTransformer(Transformers.aliasToBean(Checking.class)).list();		
		map.put("list", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findZtListByUserId(String userId,
			String startDate, String endDate, PageBaen pageBaen,String chOutTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		String sql="SELECT * FROM ci_checkingin CC WHERE CC.userId=? AND  CC.currentDate >= ? AND CC.currentDate <= ?	AND CC.afterLocation LIKE '早退%'";
		List<Checking> list = (List<Checking>)super.getSession().createSQLQuery(sql).
				setParameter(0, userId).setParameter(1, startDate).setParameter(2, endDate).setResultTransformer(Transformers.aliasToBean(Checking.class)).list();		
		map.put("list", list);
		return map;

	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findKgListByUserId(String userId,
			String startDate, String endDate, PageBaen pageBaen) {
		List<String> gradeDate = new ArrayList<String>();
		//如果工作流保存的是姓名，这里的参数无需改变，下一个方法自动调用 
		Map<String, Object> map = new HashMap<String, Object>();
		List<Kaoqin> list=(List<Kaoqin>)this.findAllByUserName(startDate, endDate, pageBaen, userId).get("list");	
		List<Kaoqin> kglist=new ArrayList<Kaoqin>();
		for (Kaoqin kaoqin : list) {
//			if(kaoqin.getOTHER() != null) {
//				if("没有时间段的任何信息".equals(kaoqin.getOTHER())) {
//					kglist.add(kaoqin);
//				}
//			}
			if(kaoqin.getCKSTATUS()!=null){
				if(kaoqin.getCKSTATUS().equals("旷工")){
					kglist.add(kaoqin);
					gradeDate.add(kaoqin.getSTARDATE());
				}
			}			
		}	
		//2017-04-27影响旷工次数不需要使用。
		/*if(kglist.isEmpty()){
			kglist.add(new Kaoqin(startDate, endDate, null, null, null, "1", "用户无该月的考勤或旷工信息"));
		}*/
		map.put("list", kglist);
		map.put("grade", gradeDate);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findQkListByUserId(String userId,
			String startDate, String endDate, PageBaen pageBaen) {
		Map<String, Object> map = new HashMap<String, Object>();
		String sql="SELECT * FROM ci_checkingin CC WHERE CC.userId=? AND (CC.clockInTime IS NULL OR CC.clockOutTime IS NULL) AND  CC.currentDate >= ? AND CC.currentDate <= ?";
		List<Checking> list=(List<Checking>)super.getSession().createSQLQuery(sql).setParameter(0, userId).setParameter(1, startDate).setParameter(2, endDate).setResultTransformer(Transformers.aliasToBean(Checking.class)).list();		
		map.put("list", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findQdListByUserId(String userId,
			String startDate, String endDate, PageBaen pageBaen) {
		String hql = "SELECT * FROM CI_checkingIn WHERE userId=? AND currentDate >= ? AND currentDate<=?";
		Map<String, Object> map = new HashMap<String, Object>();
		List<Checking> list = (List<Checking>) super.getSession().createSQLQuery(hql).setParameter(0, userId).setParameter(1, startDate).setParameter(2, endDate).setResultTransformer(Transformers.aliasToBean(Checking.class)).list();
		map.put("list", list);
		return map;
	}

	@Override
	public Map<String, Object> findAllListByDate(String startDate,
			String endDate, PageBaen pageBaen) {
		Map<String, Object> map = new HashMap<String, Object>();
		String date=startDate.substring(0,7)+"%";
		String sql01="SELECT count(1) FROM to_horizon_user a left outer join tor_horizon_user_dept b ON a.ID=b.USER_ID WHERE b.DEPT_ID<>'HZ28e7f51e816d28011e816da7f1001f'";
		BigInteger total=(BigInteger)super.getSession().createSQLQuery(sql01).uniqueResult();
		int totals=total.intValue();
		int pageSize=pageBaen.getPageSize();
		int currentPage=pageBaen.getCurrentPage();
		if(totals < pageSize){
			pageSize = totals;
		}
		if((currentPage-1)*pageSize >= totals){
			currentPage = currentPage-1;
		}
		super.getSession().flush();
		//String sql = "SELECT DE.ID AS ID, DE.`NAME` AS UNAME, DE.DEPT_NAME AS DEPT, IFNULL(CK.CKDAY,0) AS CKDAY, IFNULL(QJ.QJDAY,0) AS QJDAY FROM ( SELECT THU.ID, THU.`NAME`, THD.DEPT_NAME FROM to_horizon_user THU LEFT JOIN tor_horizon_user_dept THUD ON THU.ID = THUD.USER_ID LEFT JOIN to_horizon_dept THD ON THUD.DEPT_ID = THD.ID ) DE LEFT JOIN ( SELECT CAA.UID, CAA.UNAME, CAA.DEPT, count(1) AS CKDAY FROM ( SELECT * FROM ( SELECT cc.userId AS UID, cc.userName AS UNAME, cc.dept AS DEPT FROM ci_checkingin cc WHERE cc.currentDate >=? AND cc.currentDate<=? ) C UNION ALL ( SELECT AB.`NAME` AS UID, AB.`NAME` AS UNAME, AB.DEPTNO AS DEPT FROM aj_buqian AB WHERE (AB.AGREE = '同意' OR (AB.AGREE1 = '同意' AND AB.DAY = '1')) AND AB.BQDATE LIKE ? )) CAA GROUP BY UID,UNAME,DEPT ) CK ON DE.ID = CK.UID LEFT JOIN ( SELECT aq.`NAME` AS UID, SUM(aq.LEAVEDAY) AS QJDAY FROM aj_qingjia aq WHERE aq.STARTTIME LIKE ? GROUP BY aq.`NAME` ) QJ ON DE.`NAME` = QJ.UID WHERE DE.`NAME` NOT IN('管理员','测试壹','测试贰','测试叁','安监管理员')";		
		//THUD
		String sql = "SELECT DE.ID AS ID, DE.`NAME` AS UNAME, DE.DEPT_NAME AS DEPT, IFNULL(CK.CKDAY,0) AS CKDAY, IFNULL(QJ.QJDAY,0) AS QJDAY FROM ( SELECT THU.ID, THU.`NAME`, THD.DEPT_NAME FROM to_horizon_user THU LEFT JOIN tor_horizon_user_dept THUD ON THU.ID = THUD.USER_ID LEFT JOIN to_horizon_dept THD ON THUD.DEPT_ID = THD.ID  WHERE THD.ID<>'HZ28e7f51e816d28011e816da7f1001f') DE LEFT JOIN ( SELECT CAA.UID, CAA.UNAME, CAA.DEPT, count(1) AS CKDAY FROM ( SELECT * FROM ( SELECT cc.userId AS UID, cc.userName AS UNAME, cc.dept AS DEPT FROM ci_checkingin cc WHERE cc.currentDate >=? AND cc.currentDate<=?) C UNION ALL ( SELECT AB.`NAME` AS UID, AB.`NAME` AS UNAME, AB.DEPTNO AS DEPT FROM aj_buqian AB WHERE (AB.AGREE = '同意' OR (AB.AGREE1 = '同意' AND AB.DAY = '1')) AND AB.BQDATE LIKE ? )) CAA GROUP BY UID,UNAME,DEPT ) CK ON DE.ID = CK.UID LEFT JOIN ( SELECT aq.`NAME` AS UID, SUM(aq.LEAVEDAY) AS QJDAY FROM aj_qingjia aq WHERE aq.STARTTIME LIKE ? GROUP BY aq.`NAME` ) QJ ON DE.`NAME` = QJ.UID";
		List<?> list=(List<?>)super.getSession().createSQLQuery(sql).setParameter(0,startDate).setParameter(1,endDate).setParameter(2,date)
				.setParameter(3,date).setFirstResult((currentPage-1)*pageSize).setMaxResults(pageSize).list();
		map.put("shouldday", DateUtils.getWorkDay(endDate.substring(0, 7))-RestDao.Restnum(startDate, endDate));
		map.put("total", totals);
		map.put("list", list);
		return map;		
	}

	@Override
	public Map<String, Object> findAllListByDateAndUseName(String startDate,
			String endDate, String userName, PageBaen pageBaen) {
		Map<String, Object> map = new HashMap<String, Object>();
		String date=startDate.substring(0,7)+"%";
		String sql01="SELECT count(*) FROM to_horizon_user THU WHERE THU.NAME LIKE ?";
		BigInteger total=(BigInteger)super.getSession().createSQLQuery(sql01).setParameter(0, "%"+userName+"%").uniqueResult();
		int totals=total.intValue();
		int pageSize=pageBaen.getPageSize();		
		int currentPage=pageBaen.getCurrentPage();
		if(totals < pageSize){
			pageSize = totals;
		}
		if((currentPage-1)*pageSize >= totals){
			currentPage = currentPage-1;
		}
		String sql = "SELECT DE.ID AS ID, DE.`NAME` AS UNAME, DE.DEPT_NAME AS DEPT, IFNULL(CK.CKDAY,0) AS CKDAY, IFNULL(QJ.QJDAY,0) AS QJDAY FROM ( SELECT THU.ID, THU.`NAME`, THD.DEPT_NAME FROM to_horizon_user THU LEFT JOIN tor_horizon_user_dept THUD ON THU.ID = THUD.USER_ID LEFT JOIN to_horizon_dept THD ON THUD.DEPT_ID = THD.ID WHERE THU.`NAME` LIKE ?) DE LEFT JOIN ( SELECT CAA.UID, CAA.UNAME, CAA.DEPT, count(1) AS CKDAY FROM ( SELECT * FROM ( SELECT cc.userId AS UID, cc.userName AS UNAME, cc.dept AS DEPT FROM ci_checkingin cc WHERE cc.currentDate >=? AND cc.currentDate<=? ) C UNION ALL ( SELECT AB.`NAME` AS UID, AB.`NAME` AS UNAME, AB.DEPTNO AS DEPT FROM aj_buqian AB WHERE (AB.AGREE = '同意' OR (AB.AGREE1 = '同意' AND AB.DAY = '1')) AND AB.BQDATE LIKE ? )) CAA GROUP BY UID,UNAME,DEPT ) CK ON DE.ID = CK.UID LEFT JOIN ( SELECT aq.`NAME` AS UID, COUNT(0) AS QJDAY FROM aj_qingjia aq WHERE aq.STARTTIME LIKE ? GROUP BY aq.`NAME` ) QJ ON CK.UID = QJ.UID";		
		List<?> list=(List<?>)super.getSession().createSQLQuery(sql).setParameter(0,"%"+userName+"%").setParameter(1,startDate).setParameter(2,endDate).setParameter(3,date)
				.setParameter(4,date).setFirstResult((currentPage-1)*pageSize).setMaxResults(pageSize).list();	
		map.put("shouldday", DateUtils.getWorkDay(endDate.substring(0, 7))-RestDao.Restnum(startDate, endDate));
		map.put("total", totals);
		map.put("list", list);
		return map;		

	}

	@Override
	public Map<String, Object> findCkByCkid(String ckId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Checking ck=(Checking)super.findById(Integer.parseInt(ckId));
		map.put("ck", ck);
		return map;
	}

	@Override
	public Map<String, Object> findAllListByDateAndDept(String startDate,
			String endDate, String dept, PageBaen pageBaen) {
		Map<String, Object> map = new HashMap<String, Object>();
		String date=startDate.substring(0,7)+"%";
		String sql01="SELECT count(0) FROM to_horizon_user THU LEFT JOIN tor_horizon_user_dept THUD ON THU.ID = THUD.USER_ID LEFT JOIN to_horizon_dept THD ON THUD.DEPT_ID = THD.ID WHERE THD.DEPT_NAME LIKE ?";
		BigInteger total=(BigInteger)super.getSession().createSQLQuery(sql01).setParameter(0, "%"+dept+"%").uniqueResult();
		int totals=total.intValue();
		int pageSize=pageBaen.getPageSize();
		int currentPage=pageBaen.getCurrentPage();
		if(totals < pageSize){
			pageSize = totals;
		}
		if((currentPage-1)*pageSize >= totals){
			currentPage = currentPage-1;
		}
		String sql = "SELECT DE.ID AS ID, DE.`NAME` AS UNAME, DE.DEPT_NAME AS DEPT, IFNULL(CK.CKDAY,0) AS CKDAY, IFNULL(QJ.QJDAY,0) AS QJDAY FROM ( SELECT THU.ID, THU.`NAME`, THD.DEPT_NAME FROM to_horizon_user THU LEFT JOIN tor_horizon_user_dept THUD ON THU.ID = THUD.USER_ID LEFT JOIN to_horizon_dept THD ON THUD.DEPT_ID = THD.ID WHERE THD.DEPT_NAME LIKE ?) DE LEFT JOIN ( SELECT CAA.UID, CAA.UNAME, CAA.DEPT, count(1) AS CKDAY FROM ( SELECT * FROM ( SELECT cc.userId AS UID, cc.userName AS UNAME, cc.dept AS DEPT FROM ci_checkingin cc WHERE cc.currentDate >=? AND cc.currentDate<=? ) C UNION ALL ( SELECT AB.`NAME` AS UID, AB.`NAME` AS UNAME, AB.DEPTNO AS DEPT FROM aj_buqian AB WHERE (AB.AGREE = '同意' OR (AB.AGREE1 = '同意' AND AB.DAY = '1')) AND AB.BQDATE LIKE ? )) CAA GROUP BY UID,UNAME,DEPT ) CK ON DE.ID = CK.UID LEFT JOIN ( SELECT aq.`NAME` AS UID, COUNT(0) AS QJDAY FROM aj_qingjia aq WHERE aq.STARTTIME LIKE ? GROUP BY aq.`NAME` ) QJ ON CK.UID = QJ.UID";		
		List<?> list=(List<?>)super.getSession().createSQLQuery(sql).setParameter(0,"%"+dept+"%").setParameter(1,startDate).setParameter(2,endDate).setParameter(3,date)
				.setParameter(4,date).setFirstResult((currentPage-1)*pageSize).setMaxResults(pageSize).list();
		map.put("shouldday", DateUtils.getWorkDay(endDate.substring(0, 7))-RestDao.Restnum(startDate, endDate));
		map.put("total", totals);
		map.put("list", list);
		return map;	
	}

	@Override
	public Map<String, Object> findCurrentCk(String date) {
		String sql="SELECT * FROM ( SELECT COUNT(0) AS onjob FROM to_horizon_user ) A, ( SELECT COUNT(0) AS qjnum FROM aj_qingjia aq WHERE aq.STARTTIME <= ? AND aq.ENDTIME >= ? ) B, ( SELECT COUNT(0) AS ccnum FROM aj_chuchai ac WHERE ac.STARTTIME <= ? AND ac.ENDTIME >= ? ) C";
		List<?> list=(List<?>)super.getSession().createSQLQuery(sql).setParameter(0, date).setParameter(1, date).setParameter(2, date).setParameter(3, date).list();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int findCcDays(String userId, String startDate,
			String endDate) {
		//如果工作流保存的是姓名，后面带 "%" 的模糊查询全部换为userName即可
		//获取用户名
				String userName=this.getUserNameByUserId(userId);

		int totals=0;
		String sql="SELECT AC.STARTTIME,AC.ENDTIME,AC.`DAY` FROM aj_chuchai AC WHERE AC.CHUCHAI_RY LIKE ? AND (AC.AGREE1 = '同意' OR (AC.AGREE = '同意' AND AC.`DAY` = '1')) AND ((AC.STARTTIME >= ? AND AC.STARTTIME<= ?) OR (AC.ENDTIME >=? AND AC.ENDTIME<=?))";
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql).setParameter(0, "%"+userName+"%").setParameter(1, startDate).setParameter(2, endDate).setParameter(3, startDate).setParameter(4, endDate).list();
		for (Object[] str : list) {
			String date=startDate.substring(0,7);
			String sdate=((String)str[0]).substring(0, 7);
			String edate=((String)str[1]).substring(0, 7);
			String sdate0=(String)str[0];
			String edate0=(String)str[1];
			//如果开始时间不是当前月,请假天数算月初到结束时间的天数
			if(!sdate.equals(date)){
				System.out.println("sdate="+sdate+"date="+date);
				try {				
					int day=DateUtils.getWorkDays(DateUtils.firstDayOfMonth(DateUtils.getCurrent(sdate0), "yyyy-MM-dd"), DateUtils.getCurrent(edate0));
					totals+=day;
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				continue;
			}
			//如果结束时间不是当前月
			if(!edate.equals(edate)){
				System.out.println("edate="+edate+"date="+date);
				try {				
					int day=DateUtils.getWorkDays(DateUtils.getCurrent(sdate0),DateUtils.lastDayOfMonth(DateUtils.getCurrent(sdate0), "yyyy-MM-dd"));
					totals+=day;
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				continue;
			}
			totals+=Integer.parseInt((String)str[2]);
		}

		return totals;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int findQjDays(String userId, String startDate,
			String endDate) {
		//如果工作流保存的是姓名，后面带 "%" 的模糊查询全部换为userName即可
		//获取用户名
				String userName=this.getUserNameByUserId(userId);

		String sql="SELECT AQ.STARTTIME,AQ.ENDTIME,AQ.LEAVEDAY FROM aj_qingjia AQ WHERE AQ.`NAME` LIKE ? AND (AQ.AGREE = '同意' OR (AQ.AGREE1 = '同意' AND AQ.LEAVEDAY='1')) AND ((AQ.STARTTIME >= ? AND AQ.STARTTIME <= ?) OR (AQ.ENDTIME >= ? AND AQ.ENDTIME <= ?))";
		int totals=0;
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql).setParameter(0, "%"+userName+"%").setParameter(1, startDate).setParameter(2, endDate).setParameter(3, startDate).setParameter(4, endDate).list();
		for (Object[] str : list) {
			String date=startDate.substring(0,7);
			String sdate=((String)str[0]).substring(0, 7);
			String edate=((String)str[1]).substring(0, 7);
			String sdate0=(String)str[0];
			String edate0=(String)str[1];
			//如果开始时间不是当前月,请假天数算月初到结束时间的天数
			if(!sdate.equals(date)){
				System.out.println("sdate="+sdate+"date="+date);
				try {				
					int day=DateUtils.getWorkDays(DateUtils.firstDayOfMonth(DateUtils.getCurrent(sdate0), "yyyy-MM-dd"), DateUtils.getCurrent(edate0));
					totals+=day;
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				continue;
			}
			//如果结束时间不是当前月
			if(!edate.equals(edate)){
				System.out.println("edate="+edate+"date="+date);
				try {				
					int day=DateUtils.getWorkDays(DateUtils.getCurrent(sdate0),DateUtils.lastDayOfMonth(DateUtils.getCurrent(sdate0), "yyyy-MM-dd"));
					totals+=day;
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				continue;
			}
			totals+=Integer.parseInt((String)str[2]);
		}				
		return totals;

	}

	@Override
	public int findNormalCkDays(String userId,
			String startDate, String endDate) {
		String sql="SELECT COUNT(1) FROM ci_checkingin CC WHERE CC.userId=? AND (CC.clockInTime IS NOT NULL AND CC.clockOutTime IS NOT NULL) AND  CC.currentDate >= ? AND CC.currentDate <= ?";
		BigInteger total=(BigInteger)super.getSession().createSQLQuery(sql).setParameter(0, userId).setParameter(1, startDate).setParameter(2, endDate).uniqueResult();
		int totals=total.intValue();
		return totals;
	}

	//如果工作流更改为请假、补签、出差为保存的用户名而不是用户ID，按id先查询用户名
	public String getUserNameByUserId(String userId){
		String sql="SELECT us.`NAME` FROM to_horizon_user us WHERE us.ID = ?";
		super.getSession().flush();
		String name=(String)super.getSession().createSQLQuery(sql).setParameter(0, userId).uniqueResult();
		return name;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findSZYD(String startDate ,String endDate) {
		String sql="SELECT 'end' AS statuss, agm.MTTYPE, COUNT(agm.MTTYPE) AS NUM FROM aj_great_meeting agm WHERE LENGTH(agm.KYRY) > 0 AND LENGTH(agm.KYRY) IS NOT NULL AND agm.SXJSRQ BETWEEN ? AND ? GROUP BY agm.MTTYPE UNION ALL SELECT 'ing' AS statuss, agm.MTTYPE, COUNT(agm.MTTYPE) AS NUM FROM aj_great_meeting agm WHERE ( LENGTH(agm.KYRY) = 0 OR LENGTH(agm.KYRY) IS NULL ) GROUP BY agm.MTTYPE UNION ALL SELECT 'all' AS statuss, agm.MTTYPE, COUNT(agm.MTTYPE) AS NUM FROM aj_great_meeting agm WHERE agm.SXJSRQ BETWEEN ? AND ? GROUP BY agm.MTTYPE";
		Map<String, Object> map=new HashMap<String, Object>();
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql).setParameter(0, startDate).setParameter(2, startDate).setParameter(1, endDate).setParameter(3, endDate).list();
		List<Object[]> endlist =new ArrayList<Object[]>();
		List<Object[]> inglist =new ArrayList<Object[]>();
		List<Object[]> alllist =new ArrayList<Object[]>();
		for (Object[] objects : list) {
			String status=(String)objects[0];
			//System.out.println("status="+status);
			if(status.equals("end")){
				endlist.add(objects);
			}else if(status.equals("ing")){
				inglist.add(objects);
			}else if(status.equals("all")){
				alllist.add(objects);
			}
		}		
		map.put("endlist", endlist);
		map.put("inglist", inglist);
		map.put("alllist", alllist);
		return map;//2017-5-16上午10:26:54宁万龙
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findck_seatByDept(String startDate ,String endDate,String ckin,String ckout, String dept) {
	
		String sql="SELECT g.GROUP_NAME, u.ID, u. NAME, w.latitude, w.longitude, count(cc.userId), cc.userId FROM to_horizon_group g LEFT JOIN tor_horizon_user_group ug ON g.ID = ug.WORKGROUP_ID LEFT JOIN to_horizon_user u ON ug.USER_ID = u.ID LEFT JOIN sa_user_logitude_and_latitude w ON u.ID = w.uid LEFT JOIN ( SELECT c.userId, c.currentDate FROM ci_checkingin c WHERE c.currentDate >= ? AND c.currentDate <= ? AND c.checkLocation IS NULL AND c.afterLocation IS NULL ) cc ON u.ID = cc.userId WHERE g.GROUP_NAME NOT IN ( '群组导航', '贵阳市安全生产监督管理局' ) GROUP BY g.GROUP_NAME, u.ID, u. NAME, w.latitude, w.longitude, cc.userId ORDER BY FIELD( u. NAME, '罗斌', '胡美琪', '安尤光', '邓发权', '胡正康', '兰天', '杨亚琦', '魏燕飞' ) DESC";
		List<Object[]> list=(List<Object[]>)super.getSession().createSQLQuery(sql).setParameter(0, startDate).setParameter(1, endDate).list();		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("list", list);
		return map;//2017-5-18下午3:16:14宁万龙
	}

	@Override
	public Map<String, Object> findImg(String condition) {
		String sql="SELECT CONCAT( thi.SAVE_NAME,'.',thi.EXTENTION) FROM aj_imglr img LEFT JOIN ta_horizon_info thi ON img.ID=thi.OBJECT_ID WHERE thi.FIELD_NAME LIKE 'IMG_'";
		List<?> list=(List<?>)super.getSession().createSQLQuery(sql).list();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("list", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findAllRecord(String startDate, String endDate) {
		//sql签到、补签、出差、请假的
		String cksql="SELECT u.`NAME`,C.currentDate,C.clockInTime,C.clockOutTime FROM ci_checkingin C LEFT JOIN to_horizon_user u ON c.userId=u.ID WHERE C.currentDate >= ? AND C.currentDate <= ?";
		String bqsql="SELECT  B.BLR,B.BQTIME,B.ENDTIME,B.BQDATE,B.BQTYPE,B.BQDESC FROM aj_buqian B WHERE B.ENDTIME >= ? AND B.ENDTIME <= ? AND (B.AGREE = '同意' OR (B.AGREE1 = '同意' AND B.DAY = '1')) OR (B.BQTIME >= ? AND B.BQTIME <= ?)";
		String ccsql="SELECT B.CHUCHAI_RY,B.SINGLE_TIME,B.ENDTIME,B.`DAY`,B.CHUCHAI_SY FROM aj_chuchai B WHERE B.STARTTIME >= ? AND B.STARTTIME <= ? AND (B.AGREE = '同意' OR (B.AGREE1 = '同意' AND B.DAY = '1')) OR (B.ENDTIME >= ? AND B.ENDTIME <= ?)";
		String qjsql="SELECT b.BLR,b.STARTTIME,b.ENDTIME,b.LEAVEDAY,b.LEAVECAUSE FROM aj_qingjia B WHERE  B.STARTTIME >= ? AND B.STARTTIME <= ? AND (B.AGREE = '同意' OR (B.AGREE1 = '同意' AND B.LEAVEDAY = '1')) OR (B.ENDTIME >= ? AND B.ENDTIME <= ?)";
		Map<String, Object> map=new HashMap<String, Object>();
		
		List<Object[]> cklist=(List<Object[]>)super.getSession().createSQLQuery(cksql)
				.setParameter(0, startDate).setParameter(1, endDate).list();		
		map.put("cklist", cklist);
		
		List<Object[]> bqlist=(List<Object[]>)super.getSession().createSQLQuery(bqsql)
				.setParameter(0, startDate).setParameter(1, endDate).setParameter(2, startDate).setParameter(3, endDate).list();
		map.put("bqlist", bqlist);
		
		List<Object[]> cclist=(List<Object[]>)super.getSession().createSQLQuery(ccsql)
				.setParameter(0, startDate).setParameter(1, endDate).setParameter(2, startDate).setParameter(3, endDate).list();
		map.put("cclist", cclist);
		
		List<Object[]> qjlist=(List<Object[]>)super.getSession().createSQLQuery(qjsql)
				.setParameter(0, startDate).setParameter(1, endDate).setParameter(2, startDate).setParameter(3, endDate).list();
		map.put("qjlist", qjlist);
		
		return map;
	}
	//查询免考勤人员
	@Override
	public List<String> queryMkqUser() {
		List<String> userIds = new ArrayList<String>();
		String sql = "SELECT AJUSER FROM aj_mkqsz ORDER BY ID DESC LIMIT 0,1";
		//杨亚琦;兰天;魏燕飞;胡正康;邓发权;安尤光;胡美琪;陆建;曾宇 
		String string = super.getSession().createSQLQuery(sql).uniqueResult().toString();
		String[] split = string.split(";");
		for (String userName : split) {
			String sql2 = "SELECT ID  FROM to_horizon_user WHERE NAME=?";
			String userId = super.getSession().createSQLQuery(sql2).setParameter(0, userName).uniqueResult().toString();
			userIds.add(userId);
		}
		return userIds;
	}

}
