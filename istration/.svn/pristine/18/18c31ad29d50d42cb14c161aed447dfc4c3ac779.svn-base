package com.henghao.news.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.henghao.news.dao.MeetingStatisticsDao;
import com.henghao.news.entity.MeetingEntity;
import com.henghao.news.entity.MeetingTrajectoryEntity;
import com.henghao.news.util.DateUtils;
/**
 * @Description:融合平台会议管理页面统计数据
 * @author zcy  
 * @date 2017年11月14日 上午10:45:41
 * @version 1.0
 */
@Service
public class MeetingStatisticsService {

	@Resource
	private MeetingStatisticsDao meetingStatisticsDao;
	@Resource
	private FirmdataService firmdataService;
	@Resource
	private JPushService jPushService;
	@Resource
	private MeetingTrajectoryService meetingTrajectoryService;
	@Resource
	private UserDataService userDataService;
	private static final int HTIME = 60 * 60 * 1000;
	
	private Map<String, Object> remap = new HashMap<String, Object>();
	//会议总数，会议类型统计
	public Object queryMeetingTypeCount(String start, String end) {
		if("".equals(end) || end == null) {
			end = DateUtils.getCurrentDate("yyyy-MM-dd");
		}
		if("".equals(start) || start == null) {
			start = DateUtils.getCurrentDate("yyyy")+"-01-01";
		}
		//查询会议总数
		int meetingCount = meetingStatisticsDao.queryMeetingCount(start, end);
		String[] meetingTypes = {"小型会议","中型会议","大型会议","其他类型"};
		List<Integer> list = new ArrayList<Integer>();
		for (String meetingType : meetingTypes) {
			//分类查询会议
			int meetingTypeCount = meetingStatisticsDao.queryMeetingTypeCount(meetingType ,start, end);
			list.add(meetingTypeCount);
		}
		remap.clear();
		remap.put("meetingCount", meetingCount);
		remap.put("meetingTypeCount", list);
		return remap;
	}
	//各部门会议统计
	public Object queryDeptMeetingCount(String start, String end) {
		if("".equals(end) || end == null) {
			end = DateUtils.getCurrentDate("yyyy-MM-dd");
		}
		if("".equals(start) || start == null) {
			start = DateUtils.getCurrentDate("yyyy")+"-01-01";
		}
		List<Integer> list = new ArrayList<Integer>();
		int dept1 = 0;
		int dept2 = 0;
		int dept3 = 0;
		int dept4 = 0;
		int dept5 = 0;
		//安监一处，局领导，局办公室，职安处
		String[] deptIds = {"HZ9080955acfcfff015acfe13e040277", "HZ9080955acfcfff015acfdf964b0205", "HZ9080955acfcfff015acfdfc12e0241", "HZ9080955acfcfff015acfe052af0257"};
		List<MeetingEntity> queryPassAll = jPushService.queryPassAll(start, end);
		for (MeetingEntity meetingEntity : queryPassAll) {
			String deptId = firmdataService.queryDeptIdByUserId(meetingEntity.getUid());
			if(deptId.equals(deptIds[0])) {
				dept1 ++;
			}else{
				if(deptId.equals(deptIds[1])) {
					dept2 ++;
				}else{
					if(deptId.equals(deptIds[2])) {
						dept3 ++;
					}else{
						if(deptId.equals(deptIds[3])) {
							dept4 ++;
						}else{
							dept5 ++;
						}
					}
				}
			}
		}
		list.add(dept1);
		list.add(dept2);
		list.add(dept3);
		list.add(dept4);
		list.add(dept5);
		return list;
	}
	//会议时长统计
	public Object queryMeetingTimeCount(String start, String end) {
		if("".equals(end) || end == null) {
			end = DateUtils.getCurrentDate("yyyy-MM-dd");
		}
		if("".equals(start) || start == null) {
			start = DateUtils.getCurrentDate("yyyy")+"-01-01";
		}
		List<Integer> relist = new ArrayList<Integer>();
		int time1 = 0;
		int time2 = 0;
		int time3 = 0;
		int time4 = 0;
		int time5 = 0;
		List<MeetingEntity> queryPassAll = jPushService.queryPassAll(start, end);
		for (MeetingEntity meetingEntity : queryPassAll) {
			if(meetingEntity.getIsEnd() != 1) {
				//会议未开始，或者未结束  #本次会议不进入计算
				continue;
			}else{
				//根据mid查询所有签到数据
				List<MeetingTrajectoryEntity> list = meetingTrajectoryService.querymeetingTrajectoryEntityListByMid(meetingEntity.getMid());
				//会议完成，计算平均值
				long time = 0L;
				int size = 0;
				for (MeetingTrajectoryEntity meetingTrajectoryEntity : list) {
					if(meetingTrajectoryEntity.getStartSignInTime() != null) {
						if(meetingTrajectoryEntity.getEndSignInTime() != null) {
							time = meetingTrajectoryEntity.getEndSignInTime().getTime() 
										- meetingTrajectoryEntity.getStartSignInTime().getTime() + time;
							size ++;
						}
					}
				}
				time = time / size;
				if(time >= HTIME && time < HTIME*2) {
					time1 ++;
				}
				if(time >= HTIME*2 && time < HTIME*3) {
					time2 ++;
				}
				if(time >= HTIME*3 && time < HTIME*4) {
					time3 ++;
				}
				if(time >= HTIME*4 && time < HTIME*5) {
					time4 ++;
				}
				if(time >= HTIME*5) {
					time5 ++;
				}
			}
		}
		relist.add(time1);
		relist.add(time2);
		relist.add(time3);
		relist.add(time4);
		relist.add(time5);
		return relist;
	}
	public Object queryExceptionMeetingCount(String start, String end) {
		//列表展示所需分类数据
		List<MeetingTrajectoryEntity> midList1 = new ArrayList<MeetingTrajectoryEntity>();//未签到
		List<MeetingTrajectoryEntity> midList2 = new ArrayList<MeetingTrajectoryEntity>();//未拍照
		List<MeetingTrajectoryEntity> midList3 = new ArrayList<MeetingTrajectoryEntity>();//未上报
		List<MeetingTrajectoryEntity> midList4 = new ArrayList<MeetingTrajectoryEntity>();//未参加 
		if("".equals(end) || end == null) {
			end = DateUtils.getCurrentDate("yyyy-MM-dd");
		}
		if("".equals(start) || start == null) {
			start = DateUtils.getCurrentDate("yyyy")+"-01-01";
		}
		List<Integer> relist = new ArrayList<Integer>();
		int data1 = 0;
		int data2 = 0;
		int data3 = 0;
		int data4 = 0;
		List<MeetingEntity> queryPassAll = jPushService.queryPassAll(start, end);
		for (MeetingEntity meetingEntity : queryPassAll) {
			if(meetingEntity.getIsEnd() != 1) {
				//会议还未结束
				continue;
			}
			//根据mid查询所有签到数据
			List<MeetingTrajectoryEntity> list = meetingTrajectoryService.querymeetingTrajectoryEntityListByMid(meetingEntity.getMid());
			for (MeetingTrajectoryEntity meetingTrajectoryEntity : list) {
				if(meetingTrajectoryEntity.getStartSignInTime() == null && meetingTrajectoryEntity.getEndSignInTime() == null) {
					//未参加 ++
					data4 ++;
					midList4.add(meetingTrajectoryEntity);
				}else{
					if(meetingTrajectoryEntity.getStartSignInTime() == null || meetingTrajectoryEntity.getEndSignInTime() == null) {
						//未签到 ++
						data1 ++;
						midList1.add(meetingTrajectoryEntity);
						
					}
					if(meetingTrajectoryEntity.getMeetingImagePath() == null) {
						//未拍照++
						data2 ++;
						midList2.add(meetingTrajectoryEntity);
					}
					if(meetingTrajectoryEntity.getMeetingSummary() == null) {
						//未上报++
						data3 ++;
						midList3.add(meetingTrajectoryEntity);
					}
				}
			}
		}
		relist.add(data1);
		relist.add(data2);
		relist.add(data3);
		relist.add(data4);
		remap.clear();
		remap.put("count", data1 + data2 + data3 + data4);
		remap.put("dataTypeList", relist);
		remap.put("midList1", midList1);
		remap.put("midList2", midList2);
		remap.put("midList3", midList3);
		remap.put("midList4", midList4);
		return remap;
	}
	//会议总数列表查询
	public Object queryMeetingCountList(int page, int size, String start, String end) {
		List<MeetingEntity> relist = new ArrayList<MeetingEntity>();
		Map<String, Object> map = jPushService.queryMeetingCountList(page, size, start, end);
		@SuppressWarnings("unchecked")
		List<MeetingEntity> dblist = (List<MeetingEntity>) map.get("list");
		//将userId转成name
		for (MeetingEntity meetingEntity : dblist) {
			meetingEntity.setUid(userDataService.getUserName(meetingEntity.getUid()));
			String userIds2 = meetingEntity.getUserIds();
			StringBuffer sb = new StringBuffer();
			if(userIds2 != null) {
				String[] userIds = meetingEntity.getUserIds().split(",");
				for (String userId : userIds) {
					sb.append(userDataService.getUserName(userId) + ",");
				}
			}
			if(sb.length() > 0) {
				meetingEntity.setUserIds(sb.substring(0, sb.length()-1));
			}
			relist.add(meetingEntity);
		}
		map.put("list", relist);
		return map;
	}
	//参会异常列表
	@SuppressWarnings("unchecked")
	public Object queryExceptionMeetingCountList(int page, int size, String start, String end) {
		if("".equals(end) || end == null) {
			end = DateUtils.getCurrentDate("yyyy-MM-dd");
		}
		if("".equals(start) || start == null) {
			start = DateUtils.getCurrentDate("yyyy")+"-01-01";
		}
		List<MeetingEntity> list = new ArrayList<MeetingEntity>();
		List<MeetingEntity> relist = new ArrayList<MeetingEntity>();
		Map<String, Object> map = (Map<String, Object>) this.queryExceptionMeetingCount(start, end);
		
		List<MeetingTrajectoryEntity> midList1 = (List<MeetingTrajectoryEntity>) map.get("midList1");
		List<MeetingTrajectoryEntity> midList2 = (List<MeetingTrajectoryEntity>) map.get("midList2");
		List<MeetingTrajectoryEntity> midList3 = (List<MeetingTrajectoryEntity>) map.get("midList3");
		List<MeetingTrajectoryEntity> midList4 = (List<MeetingTrajectoryEntity>) map.get("midList4");
		
		for(MeetingTrajectoryEntity mte : midList1) {
			//获取会议信息
			MeetingEntity queryByMid = jPushService.queryByMid(mte.getMid());
			queryByMid = new MeetingEntity(queryByMid);
			//获取本身参会人员
			String userName = userDataService.getUserName(mte.getShouldAttendMetting());
			queryByMid.setWifiSSID(userName+"未签到");
			list.add(queryByMid);
		}
		for(MeetingTrajectoryEntity mte : midList2) {
			//获取会议信息
			MeetingEntity queryByMid = jPushService.queryByMid(mte.getMid());
			queryByMid = new MeetingEntity(queryByMid);
			//获取本身参会人员
			String userName = userDataService.getUserName(mte.getShouldAttendMetting());
			queryByMid.setWifiSSID(userName+"未拍照");
			list.add(queryByMid);
		}
		for(MeetingTrajectoryEntity mte : midList3) {
			//获取会议信息
			MeetingEntity queryByMid = jPushService.queryByMid(mte.getMid());
			queryByMid = new MeetingEntity(queryByMid);
			//获取本身参会人员
			String userName = userDataService.getUserName(mte.getShouldAttendMetting());
			queryByMid.setWifiSSID(userName+"未上报");
			list.add(queryByMid);
		}
		for(MeetingTrajectoryEntity mte : midList4) {
			//获取会议信息
			MeetingEntity queryByMid = jPushService.queryByMid(mte.getMid());
			queryByMid = new MeetingEntity(queryByMid);
			//获取本身参会人员
			String userName = userDataService.getUserName(mte.getShouldAttendMetting());
			queryByMid.setWifiSSID(userName+"未参加");
			list.add(queryByMid);
		}
		int total = list.size();
		if(size > total) {
			size = total;
		}
		int max = page * size;
		if(page * size > total) {
			page = total / size;
			if(total % size != 0) {
				page ++;
			}
			max = total;
		}
		List<MeetingEntity> subList = list.subList((page-1) * size, max);
		//将userId转成name
		for (MeetingEntity meetingEntity : subList) {
			meetingEntity.setUid(userDataService.getUserName(meetingEntity.getUid()));
			String userIds2 = meetingEntity.getUserIds();
			StringBuffer sb = new StringBuffer();
			if(userIds2 != null) {
				String[] userIds = meetingEntity.getUserIds().split(",");
				for (String userId : userIds) {
					sb.append(userDataService.getUserName(userId) + ",");
				}
			}
			if(sb.length() > 0) {
				meetingEntity.setUserIds(sb.substring(0, sb.length()-1));
			}
			relist.add(meetingEntity);
		}
		remap.clear();
		remap.put("total", total);
		remap.put("list", relist);
		return remap;
	}
	
}
