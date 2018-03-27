package com.henghao.news.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.henghao.news.dao.ApprovalProcessDao;
import com.henghao.news.dao.GreatMeetingDao;
import com.henghao.news.entity.GreatMeeting;
import com.henghao.news.entity.WorkList;
import com.henghao.news.util.DateUtils;

@Service
public class GreatMeetingService {
	@Resource
	private GreatMeetingDao greatMeetingDao;
	@Resource
	private UserDataService userDataService;
	@Resource
	private ApprovalProcessDao approvalProcessDao;

	public List<Object> queryTypeCount(String start, String end) throws ParseException {
		if("".equals(start) || null == start) {
			start = DateUtils.getCurrentDate("yyyy")+"-01-01";
		}
		if("".equals(end) || null == end) {
			end = DateUtils.getCurrentDate("yyyy-MM-dd");
		}
		List<Object> relist = new ArrayList<Object>();
		String[] type = {"重大问题决策","重要干部任免","重大项目安排","大额资金使用"};
		List<String> holidayAndWeekend = userDataService.getHolidayAndWeekend();
		for (String typename : type) {
			List<Integer> tpyeList = new ArrayList<Integer>();
			String count = greatMeetingDao.queryTypeCount(start, end, typename);
			tpyeList.add(Integer.parseInt(count));
			//查询worklist//
			int zc = 0;//正常
			int yj = 0;//预警
			int jg = 0;//警告
			int yzjg = 0;// 严重警告
			int dzwz = 0; //督责问责
			List<WorkList> list = greatMeetingDao.queryWorkListAll(typename, start, end);
			for (WorkList workList : list) {
				//规定时限
				int limitTime = this.getLimitTime(workList.getLIMITTIME());
				if(limitTime == 0) {
					continue;
				}
				if("Done".equals(workList.getSTATUS())) {
					//实际用时
					int yetday = DateUtils.getDoActionTimeWorkList(holidayAndWeekend, workList.getDOTIME(), workList.getSENDTIME());
					if(yetday < limitTime) {
						zc++;
					}
					if(limitTime-1 < yetday && yetday < limitTime) {
						yj++;
					}
					if(yetday == limitTime) {
						jg++;
					}
					if(yetday >= limitTime) {
						yzjg++;
					}
				}
				if("Author".equals(workList.getSTATUS())) {
					//已经用时
					int sendTime = DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date());
					if(sendTime < limitTime) {
						zc++;
					}
					if(limitTime-1 < sendTime && sendTime < limitTime) {
						yj++;
					}
					if(sendTime == limitTime) {
						jg++;
					}
					if(sendTime >= limitTime) {
						yzjg++;
					}
				}
			}
			tpyeList.add(zc);
			tpyeList.add(yj);
			tpyeList.add(jg);
			tpyeList.add(yzjg);
			tpyeList.add(dzwz);
			relist.add(tpyeList);
		}
		return relist;
		}
		
		

	public Map<String, Object> queryGreatListByType(String type, int page,
			int size) {
		Map<String, Object> map = greatMeetingDao.queryGreatListByType(type, page, size);
		@SuppressWarnings("unchecked")
		List<GreatMeeting> list = (List<GreatMeeting>) map.get("list");
		for (GreatMeeting greatMeeting : list) {
			//查询发起人部门
			String userDept = userDataService.getUserDept(greatMeeting.getMTINITIATOR());
			greatMeeting.setUserdept(userDept);
			String kyry = greatMeeting.getKYRY();
			String[] split = kyry.split(";");
			greatMeeting.setKYRY(String.valueOf(split.length));
		}
		map.put("list", list);
		return map;
	}

	public GreatMeeting queryById(String id) {
		GreatMeeting gm = greatMeetingDao.queryById(id);
		return gm;
	}
	
	
	
	
	private int getLimitTime(String limitTime) {
		if(limitTime != null) {
			int indexOf = limitTime.indexOf("(");
			return Integer.parseInt(limitTime.substring(0, indexOf));
		}
		return 0;
	}
	
}
