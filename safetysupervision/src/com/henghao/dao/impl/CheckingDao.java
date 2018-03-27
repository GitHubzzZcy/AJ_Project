package com.henghao.dao.impl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

import com.henghao.dao.ICheckingDao;
import com.henghao.dao.Rest_dayDao;
import com.henghao.entity.Checking;
import com.henghao.entity.Kaoqin;
import com.henghao.entity.Rest_day;

import com.henghao.service.Rest_dayService;
import com.henghao.util.DateUtils;

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
	public Map<String, Object> findAllByUserName(String startDate,String endDate,String user) {
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

	
	@SuppressWarnings("unchecked")
	@Override
	public int findKgListByUserId(String userId,
			String startDate, String endDate) {
		// 如果工作流保存的是姓名，这里的参数无需改变，下一个方法自动调用
		//#获取考勤信息
		List<Kaoqin> list = (List<Kaoqin>) this.findAllByUserName(startDate,
				endDate, userId).get("list");
		int num = 0;
		for (Kaoqin kaoqin : list) {
			if (kaoqin.getCKSTATUS() != null) {
				if (kaoqin.getCKSTATUS().equals("旷工")) {
					num ++;
				}
			}
		}
		return num;
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
			public List<Object> findGroup() {
				String sql = "SELECT GROUP_NAME FROM to_horizon_group WHERE ORDER_NO <> 1 AND PARENT_ID <> 'HZ8bb0c95e7a8638015e846861b571dd' AND ORDER_NO <> 6";
				List<Object> list = getSession().createSQLQuery(sql).list();
				return list;
			}


			@SuppressWarnings("unchecked")
			@Override
			public List<Object> findGroupPersonle(String groupName) {
				String sql = "SELECT u.ID FROM tor_horizon_user_group ug,to_horizon_group g,to_horizon_user u "
						+ "WHERE u.ID = ug.USER_ID AND ug.WORKGROUP_ID = g.ID and g.GROUP_NAME= ? ";
				List<Object> list = getSession().createSQLQuery(sql).setParameter(0, groupName).list();
				return list;
			}

}
