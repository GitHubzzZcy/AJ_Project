package com.henghao.news.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.henghao.news.dao.GradeDao;
import com.henghao.news.entity.GradeResultEntity;
import com.henghao.news.entity.HzGradeDate;
import com.henghao.news.entity.MeetingEntity;
import com.henghao.news.entity.MeetingTrajectoryEntity;
import com.henghao.news.entity.WorkList;
import com.henghao.news.util.BaiduUtil;
import com.henghao.news.util.DateUtils;
import com.henghao.news.util.HzGradeType;
import com.henghao.news.util.SerializeUtil;
import com.henghao.news.util.Urljson;


/**
 * @Description: 评分系统业务层
 * @author zcy  
 * @date 2017年12月1日 上午11:44:13
 * @version 1.0
 */
@Service
public class GradeService {
	
	@Value("${GRADE_TO_CHECK_WORKFLOW}")
	private String GRADE_TO_CHECK_WORKFLOW;
	@Value("${GRADE_TO_CARDATA}")
	private String GRADE_TO_CARDATA;
	@Value("${GRADE_TO_COMPLAIN}") //投诉举报
	private String GRADE_TO_COMPLAIN;
	
	@Resource
	private UserDataService userDataService;
	@Resource
	private GradeDao gradeDao;
	@Resource
	private JPushService jPushService;
	@Resource
	private MeetingTrajectoryService meetingTrajectoryService;
	@Resource
	private EfficiencyCarDataService efficiencyCarDataService;//异常用车
	
	@Value("${GRADE_OVERDUE_SH}") 
	private float GRADE_OVERDUE_SH;//逾期审核
	@Value("${GRADE_OVERDUE_SP}")
	private float GRADE_OVERDUE_SP;//逾期审批
	@Value("${GRADE_OVERDUE_WSH}")
	private float GRADE_OVERDUE_WSH;//逾期未审核
	@Value("${GRADE_OVERDUE_WSP}")
	private float GRADE_OVERDUE_WSP;//逾期未审批
	@Value("${GRADE_OVERDUE_NO_SHSP}")
	private float GRADE_OVERDUE_NO_SHSP;//加分   年度审批数量达到50件以上，每20件加1分
	@Value("${GRADE_SH_ILLEGAL}")
	private float GRADE_SH_ILLEGAL;//违规审核
	@Value("${GRADE_SP_ILLEGAL}")
	private float GRADE_SP_ILLEGAL;//违规审批

	
	@Value("${GRADE_HY_QIANDAO}")
	private float GRADE_HY_QIANDAO;//	#1．未签到         每次扣0.5分
	@Value("${GRADE_HY_PAIZHAO_NUM}")
	private int GRADE_HY_PAIZHAO_NUM;//	#2．未拍照         超3次每次扣0.5分
	@Value("${GRADE_HY_PAIZHAO}")
	private float GRADE_HY_PAIZHAO;
	@Value("${GRADE_HY_SHANGBAO_NUM}")
	private int GRADE_HY_SHANGBAO_NUM;//	#3．未上报         超3次每次扣0.5分
	@Value("${GRADE_HY_SHANGBAO}")
	private float GRADE_HY_SHANGBAO;
	@Value("${GRADE_HY_CANJIA}")
	private float GRADE_HY_CANJIA;//	#4．未参加         每次扣1分
	
	@Value("${GRADE_KQ_KUANGGONG}")
	private float GRADE_KQ_KUANGGONG; //旷工扣分
	@Value("${GRADE_KQ_ZAOTUI_NUM}")
	private float GRADE_KQ_ZAOTUI_NUM; //早退超次
	@Value("${GRADE_KQ_ZAOTUI}")
	private float GRADE_KQ_ZAOTUI;  //早退扣分
	@Value("${GRADE_KQ_WAICHU_NUM}")
	private float GRADE_KQ_WAICHU_NUM; //外出超次
	@Value("${GRADE_KQ_WAICHU}")
	private float GRADE_KQ_WAICHU;  //外出扣分
	@Value("${GRADE_KQ_CHIDAO_NUM}")
	private float GRADE_KQ_CHIDAO_NUM;//迟到超次
	@Value("${GRADE_KQ_CHIDAO}")
	private float GRADE_KQ_CHIDAO;  //迟到扣分
	@Value("${GRADE_KQ_QUEKA_NUM}")
	private float GRADE_KQ_QUEKA_NUM; //缺卡超次
	@Value("${GRADE_KQ_QUEKA}")
	private float GRADE_KQ_QUEKA;  //缺卡扣分
	@Value("${GRADE_KQ_NORMAL}")
	private float GRADE_KQ_NORMAL;//无旷工、早退、迟到、缺卡加分
	
	@Value("${GRADE_DUZE}")
	private float GRADE_DUZE; //督责
	@Value("${GRADE_WENZE}")
	private float GRADE_WENZE;//问责
	
	@Value("${GRADE_CL_GONGCHE}")
	private float GRADE_CL_GONGCHE;//公车私用扣分
	
	@Value("${GRADE_CHECK}")
	private int GRADE_CHECK;//加分从几月份开始算起
	
	private static final String[] FLOWIDS = {"djbf", "aqfw", "feimeikuangshan(yichu)", "sichu(chucishenqing)", "sichu(fushen)"};
	private static final String[] TABSENAMES = {"AJ_QXNFMKSAQSCXKDJBF","AJ_QXNFMKSAQSSSJSCAQFF","AJ_QXNFMKSAQSSSJSC","AJ_SC_ONE","AJ_SC_TWO"};
	
	@Resource
	private RedisService redisService;
	/**
	 * @param userId
	 * @param start
	 * @param end
	 * @param type   0时表示查询一年数据，1表示传入月份时间段的数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> querySaveRedisGrade(String userId, String start, String end, int type) {
		Map<String, Object> remap = new HashMap<String, Object>();
		//获取当前月，当前日
		int month = Integer.parseInt(DateUtils.getCurrentDate("MM"));
		int day = Integer.parseInt(DateUtils.getCurrentDate("dd"));
		int count = 0;
		float de_grade_n = 0;
		float neng_grade_n = 0;
		float qin_grade_n = 0;
		float ji_grade_n = 0;
		float lian_grade_n = 0;
		String nian = DateUtils.getCurrentDate("yyyy");
		List<GradeResultEntity> de_list = new ArrayList<GradeResultEntity>();
		List<GradeResultEntity> neng_list = new ArrayList<GradeResultEntity>();
		List<GradeResultEntity> qin_list = new ArrayList<GradeResultEntity>();
		List<GradeResultEntity> ji_list = new ArrayList<GradeResultEntity>();
		List<GradeResultEntity> lian_list = new ArrayList<GradeResultEntity>();
		for(int i=1; i<month+1; i++) {
			String endDate = null;
			String startDate = null;
			
			if(i <= 8) {
				startDate = start.substring(0, 4)+"-0"+i+"-01 00:00:00";
				endDate = start.substring(0, 4)+"-0"+ (i+1) +"-01 00:00:00";
			}else{
				startDate = start.substring(0, 4)+"-"+i+"-01 00:00:00";
				endDate = start.substring(0, 4)+"-"+ (i+1) +"-01 00:00:00";
			}
			if(i == 9) {
				startDate = start.substring(0, 4)+"-09-01 00:00:00";
				endDate = start.substring(0, 4)+"-10-01 00:00:00";
			}if(i == 12) {
				startDate = start.substring(0, 4)+"-12-01 00:00:00";
				endDate = start.substring(0, 4)+"-12-31 23:59:59";
			}
			//查询的是传入月份
			if(type == 1) {
				endDate = end;
				startDate = start;
			}
			String date = startDate.substring(0, 7);
			int parseInt = Integer.parseInt(date.substring(0, 4) + date.subSequence(5, 7));
			//工作流录入
			Map<String, Object> queryHzGradeClass = this.queryHzGradeClass(userId, startDate, endDate);
			//投诉举报
			Map<String, Object> queryComplain = this.queryComplain(userId, startDate, endDate);
			
			/********德**Start***/
			
			float de_grade = 0;
			float neng_grade = 0;
			float qin_grade = 0;
			float ji_grade = 0;
			float lian_grade = 0;
			try {
				/*工作流录入包括1.违反法律法规扣2分/次 2.个人受市区级表彰加2分/次  。 1.着装不正式扣0.1分/次    2.文明检查被查扣1分/次*/
				de_list.addAll((List<GradeResultEntity>) queryHzGradeClass.get("de_list_queryHzGradeClass"));
				de_grade = Float.parseFloat(queryHzGradeClass.get("de_grade_queryHzGradeClass").toString());
				//int de_count = (Integer) queryHzGradeClass.get("de_count_queryHzGradeClass");
				/*会议管理*/
				//判断从几月开始计算
				if(parseInt >= GRADE_CHECK) {
					Map<String, Object> queryMeetingGrade = this.queryMeetingGrade(userId, startDate, endDate);
					de_grade += Float.parseFloat( queryMeetingGrade.get("grade_queryMeetingGrade").toString());
					//每月无扣分加1分
					float parseFloat = Float.parseFloat( queryMeetingGrade.get("grade_queryMeetingGrade").toString());
					int meetingCount = Integer.parseInt(queryMeetingGrade.get("count_queryMeetingGrade").toString());
					if(parseFloat == 0 && meetingCount != 0) {
						de_grade += 1.0;
						de_list.add(new GradeResultEntity("加分", "德", "本月会议无异常", "1", nian+"年"+i+"月份"));
					}
					de_list.addAll((List<GradeResultEntity>) queryMeetingGrade.get("relist_queryMeetingGrade"));
				}
				
				/*车辆系统*/
				if(parseInt >= GRADE_CHECK) {
					Map<String, Object> queryCarDeparture = efficiencyCarDataService.queryCarDeparture(userId, startDate, endDate);
					Map<String, Object> queryCarManage = efficiencyCarDataService.queryCarManage(userId, startDate, endDate);
					Map<String, Object> queryCarOvertime = efficiencyCarDataService.queryCarOvertime(userId, startDate, endDate);
					de_list.addAll((List<GradeResultEntity>) queryCarDeparture.get("relist_queryCarDeparture"));
					de_list.addAll((List<GradeResultEntity>) queryCarManage.get("relist_queryCarManage"));
					de_list.addAll((List<GradeResultEntity>) queryCarOvertime.get("relist_queryCarOvertime"));
					de_grade += Float.parseFloat( queryCarDeparture.get("grade_queryCarDeparture").toString());
					de_grade += Float.parseFloat( queryCarManage.get("grade_queryCarManage").toString());
					de_grade += Float.parseFloat( queryCarOvertime.get("grade_queryCarOvertime").toString());
					//每月无异常加1分
					//本月用车次数
					int carCount = Integer.parseInt(queryCarDeparture.get("count_queryCarDeparture").toString());
					float in = Float.parseFloat( queryCarDeparture.get("grade_queryCarDeparture").toString());
					in += Float.parseFloat( queryCarManage.get("grade_queryCarManage").toString());
					in += Float.parseFloat( queryCarOvertime.get("grade_queryCarOvertime").toString());
					in += Float.parseFloat( queryComplain.get("car_grade_queryComplain").toString());
					/*车辆公车私用，从投诉举报获取car_relist*/
					List<GradeResultEntity> lis = (List<GradeResultEntity>) queryComplain.get("car_relist_queryComplain");
					de_list.addAll(lis);
					de_grade += Float.parseFloat( queryComplain.get("car_grade_queryComplain").toString());
					if(in == 0 && carCount != 0) {
						de_grade += 1.0;
						de_list.add(new GradeResultEntity("加分", "德", "本月用车无异常", "1", nian+"年"+i+"月份"));
					}
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			/********德***End*******/
			
			/********能**Start***/
			try {
				/*工作流数据--->1.与同事、执法对象发生打架斗殴等纠纷。办事推诿，逃避责任。出色解决部门或单位的紧急事件。出色完成特别任务*/
				neng_list.addAll((List<GradeResultEntity>) queryHzGradeClass.get("neng_list_queryHzGradeClass"));
				neng_grade = Float.parseFloat( queryHzGradeClass.get("neng_grade_queryHzGradeClass").toString());
				/*投诉举报数据---> 。与同事、执法对象发生打架斗殴等纠纷。办事推诿，逃避责任。*/
				neng_list.addAll((List<GradeResultEntity>) queryComplain.get("neng_relist_queryComplain"));
				neng_grade += Float.parseFloat( queryComplain.get("neng_grade_queryComplain").toString());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			/********能***End*******/
			
			/********勤**Start***/
			try {
				/*考勤系统*/
				//考勤从几月份开始算起
				if(parseInt >= GRADE_CHECK) {
					Map<String, Object> queryCheckData = this.queryCheckData(userId, date);
					qin_list.addAll((List<GradeResultEntity>) queryCheckData.get("relist_queryCheckData"));
					qin_grade = Float.parseFloat( queryCheckData.get("grade_queryCheckData").toString());
					if(qin_grade == 0){
						qin_grade += 1.0;
						qin_list.add(new GradeResultEntity("加分", "勤", "本月考勤无异常", "1", nian+"年"+i+"月份"));
					}
				}
				/*工作流录入---->不服从工作分配，影响工作的扣1分/次*/
				qin_list.addAll((List<GradeResultEntity>) queryHzGradeClass.get("qin_list_queryHzGradeClass"));
				qin_grade += Float.parseFloat( queryHzGradeClass.get("qin_grade_queryHzGradeClass").toString());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			/********勤***End*******/
			
			/********绩**Start***/
			try {
				/*逾期审核审批，逾期未审核审批*/
				if(parseInt >= GRADE_CHECK) {
					Map<String, Object> queryAnjianTongjiOverdueDispose = this.queryAnjianTongjiOverdueDispose(userId, startDate, endDate);
					ji_list.addAll((List<GradeResultEntity>) queryAnjianTongjiOverdueDispose.get("relist_queryAnjianTongjiOverdueDispose"));
					ji_grade = Float.parseFloat( queryAnjianTongjiOverdueDispose.get("grade_queryAnjianTongjiOverdueDispose").toString());
					count += Integer.parseInt(queryAnjianTongjiOverdueDispose.get("count_queryAnjianTongjiOverdueDispose").toString()); 
					Map<String, Object> queryAnjianTongjiOverdueNotDispose = this.queryAnjianTongjiOverdueNotDispose(userId, startDate, endDate);
					ji_list.addAll((List<GradeResultEntity>) queryAnjianTongjiOverdueNotDispose.get("relist_queryAnjianTongjiOverdueNotDispose"));
					ji_grade += Float.parseFloat( queryAnjianTongjiOverdueNotDispose.get("grade_queryAnjianTongjiOverdueNotDispose").toString());
					Map<String, Object> queryManageIllegal = this.queryManageIllegal(userId, startDate, endDate);
					ji_list.addAll((List<GradeResultEntity>) queryManageIllegal.get("relist_queryManageIllegal"));
					ji_grade += Float.parseFloat( queryManageIllegal.get("grade_queryManageIllegal").toString());
					if(ji_grade == 0 && count != 0) {
						ji_grade += 1.0;
						de_list.add(new GradeResultEntity("加分", "绩", "本月办件无异常", nian+"年"+"1", nian+"年"+i+"月份"));
					}
				}
				/*投诉举报---->发生安全生产事故：扣5分/起*/
				ji_list.addAll((List<GradeResultEntity>) queryComplain.get("ji_relist_queryComplain"));
				ji_grade += Float.parseFloat( queryComplain.get("ji_grade_queryComplain").toString());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			/********绩***End*******/
			
			/********廉**Start***/
			/*工作流录入————————>督责问责*/
			try {
				Map<String, Object> queryHzduzeAndWenze = this.queryHzduzeAndWenze(userId, startDate, endDate);
				lian_list.addAll((List<GradeResultEntity>) queryHzduzeAndWenze.get("relist_queryHzduzeAndWenze"));
				lian_grade = Float.parseFloat( queryHzduzeAndWenze.get("grade_queryHzduzeAndWenze").toString());
				/*投诉举报————>发现违纪行为：。挥霍公款、铺张浪费*/
				lian_list.addAll((List<GradeResultEntity>) queryComplain.get("lian_relist_queryComplain"));
				lian_grade += Float.parseFloat( queryComplain.get("lian_grade_queryComplain").toString());
				/*工作流录入————>发现违纪行为：。挥霍公款、铺张浪费*/
				lian_list.addAll((List<GradeResultEntity>) queryHzGradeClass.get("lian_list_queryHzGradeClass"));
				lian_grade += Float.parseFloat( queryHzGradeClass.get("lian_grade_queryHzGradeClass").toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
			/********廉***End*******/
			
			//年度审批数量达到50件以上，每20件加1分
			if(i == month) {
				if(count > 50) {
					int ind = count - 50;
					int chao = ind/20;
					ji_grade += chao;
				}
			}
			List<GradeResultEntity> redis_list = new ArrayList<GradeResultEntity>();
			redis_list.addAll(de_list);
			redis_list.addAll(neng_list);
			redis_list.addAll(qin_list);
			redis_list.addAll(ji_list);
			redis_list.addAll(lian_list);
			
			String currentDate = DateUtils.getCurrentDate("yyyy-") + i;
			String yar = DateUtils.getCurrentDate("yyyy-");
			//获取传入时间的月份
			if(type == 1) {
				currentDate = DateUtils.getCurrentDate("yyyy-") + Integer.parseInt(start.substring(5, 7));
			}
			//获取缓存时间，缓存到当前日期的23:59:59
			int TIME = DateUtils.redisTime();
			//将返回对象写入redis一天
			if(type != 1) {
				//只有是通过用户查询该用户所有数据时才写入
				for (int r=0; r<redis_list.size(); r++) {
					byte[] entity = SerializeUtil.serialize(redis_list.get(r));
					//key形式-----> 2018-userId_100
					redisService.set(("Grede_entity-"+yar + userId + "_" + r).getBytes(), entity, TIME);
				}
			}
			try {
				if(type == 1) {
					//当前月数据写入缓存一天
					redisService.set("Grede_de-"+ userId + currentDate, String.valueOf(de_grade), TIME);
					redisService.set("Grede_neng-"+ userId + currentDate, String.valueOf(neng_grade), TIME);
					redisService.set("Grede_qin-"+ userId + currentDate, String.valueOf(qin_grade), TIME);
					redisService.set("Grede_ji-"+ userId + currentDate, String.valueOf(ji_grade), TIME);
					redisService.set("Grede_lian-"+ userId + currentDate, String.valueOf(lian_grade), TIME);
				}else{
					//把上上月之前的所有数据永久写入缓存
					if(i < month-1) {
						redisService.set("Grede_de-"+ userId + currentDate, String.valueOf(de_grade));
						redisService.set("Grede_neng-"+ userId + currentDate, String.valueOf(neng_grade));
						redisService.set("Grede_qin-"+ userId + currentDate, String.valueOf(qin_grade));
						redisService.set("Grede_ji-"+ userId + currentDate, String.valueOf(ji_grade));
						redisService.set("Grede_lian-"+ userId + currentDate, String.valueOf(lian_grade));
					}
					//当前日期是小于10号时，吧上月数据写入缓存一天，否则永久写入
					if(day < 10) {
						//上月数据写缓存、时间为1天
						if(i == month-1) {
							redisService.set("Grede_de-"+ userId + currentDate, String.valueOf(de_grade), TIME);
							redisService.set("Grede_neng-"+ userId + currentDate, String.valueOf(neng_grade), TIME);
							redisService.set("Grede_qin-"+ userId + currentDate, String.valueOf(qin_grade), TIME);
							redisService.set("Grede_ji-"+ userId + currentDate, String.valueOf(ji_grade), TIME);
							redisService.set("Grede_lian-"+ userId + currentDate, String.valueOf(lian_grade), TIME);
						}
					}else{
						if(i == month-1) {
							redisService.set("Grede_de-"+ userId + currentDate, String.valueOf(de_grade));
							redisService.set("Grede_neng-"+ userId + currentDate, String.valueOf(neng_grade));
							redisService.set("Grede_qin-"+ userId + currentDate, String.valueOf(qin_grade));
							redisService.set("Grede_ji-"+ userId + currentDate, String.valueOf(ji_grade));
							redisService.set("Grede_lian-"+ userId + currentDate, String.valueOf(lian_grade));
						}
					}
					if(i == month) {
						//当前月数据写入缓存一天
						redisService.set("Grede_de-"+ userId + currentDate, String.valueOf(de_grade), TIME);
						redisService.set("Grede_neng-"+ userId + currentDate, String.valueOf(neng_grade), TIME);
						redisService.set("Grede_qin-"+ userId + currentDate, String.valueOf(qin_grade), TIME);
						redisService.set("Grede_ji-"+ userId + currentDate, String.valueOf(ji_grade), TIME);
						redisService.set("Grede_lian-"+ userId + currentDate, String.valueOf(lian_grade), TIME);
					}
				}
			} catch (Exception e) {
				System.out.println("Redis---->SET错误querySaveRedisGrade");
			}
			
			de_grade_n += de_grade;
			neng_grade_n += neng_grade;
			qin_grade_n += qin_grade;
			ji_grade_n += ji_grade;
			lian_grade_n += lian_grade;
			//表示只差传入时间段内的数据，不用循环de_grade_n就是该时间段（该月）的分值
			if(type == 1) {
				break;
			}
		}	
		remap.put("de_grade_deduct", de_grade_n);
		remap.put("neng_grade_deduct", neng_grade_n);
		remap.put("qin_grade_deduct", qin_grade_n);
		remap.put("ji_grade_deduct", ji_grade_n);
		remap.put("lian_grade_deduct", lian_grade_n);
		
		remap.put("de_list", de_list);
		remap.put("neng_list", neng_list);
		remap.put("qin_list", qin_list);
		remap.put("ji_list", ji_list);
		remap.put("lian_list", lian_list);
		if(de_grade_n > 0) {
			de_grade_n = 0;
		}
		if(neng_grade_n > 0) {
			neng_grade_n = 0;
		}
		if(qin_grade_n > 0) {
			qin_grade_n = 0;
		}
		if(ji_grade_n > 0) {
			ji_grade_n = 0;
		}
		if(lian_grade_n > 0) {
			lian_grade_n = 0;
		}
		remap.put("de_grade", 15.0 + de_grade_n);
		remap.put("neng_grade", 15.0 + neng_grade_n);
		remap.put("qin_grade", 20.0 + qin_grade_n);
		remap.put("ji_grade", 35.0 + ji_grade_n);
		remap.put("lian_grade", 15.0 + lian_grade_n);
		return remap;
	}
	//投诉举报URL
	@SuppressWarnings("unchecked")
	public Map<String, Object> queryComplain(String userId, String start, String end)  {
		Map<String, Object> remap = new HashMap<String, Object>();
		try {
			List<GradeResultEntity> car_reList = new ArrayList<GradeResultEntity>();
			List<GradeResultEntity> neng_reList = new ArrayList<GradeResultEntity>();
			List<GradeResultEntity> ji_reList = new ArrayList<GradeResultEntity>();
			List<GradeResultEntity> lian_reList = new ArrayList<GradeResultEntity>();
			float neng_grade = 0;
			float ji_grade = 0;
			float lian_grade = 0;
			float de_grade = 0;
			String userName = userDataService.getUserName(userId);
			if(userName != null) {
				userName = java.net.URLEncoder.encode(userName, "UTF-8");
				//url不能传空格
				start = start.substring(0, 10);
				end = end.substring(0, 10);
				com.alibaba.fastjson.JSONObject jsonObject = Urljson.readFastJsonFromUrl(GRADE_TO_COMPLAIN+"?userName="+userName+"&start="+start+"&end="+end);
				Object object = jsonObject.get("data");
				String jsonString = JSON.toJSONString(object);
				com.alibaba.fastjson.JSONObject map = JSON.parseObject(jsonString);
				//Map<String, Object> map = (Map<String, Object>) jsonObject.get("data");
				/*	remap.put("car_list", car_list);
				  	remap.put("anquan_list", anquan_list);
					remap.put("weiji_list", weiji_list);
					remap.put("huihuo_list", huihuo_list);
					remap.put("jiufen_list", jiufen_list);
					remap.put("banshi_list", banshi_list); 时间List*/
				List<String> car_list  = (List<String>) map.get("car_list"); //德 0
				List<String> anquan_list  = (List<String>) map.get("anquan_list"); //绩1
				List<String> weiji_list  = (List<String>) map.get("weiji_list");//廉2
				List<String> huihuo_list  = (List<String>) map.get("huihuo_list"); //廉3
				List<String> jiufen_list  = (List<String>) map.get("jiufen_list"); //能4
				List<String> banshi_list  = (List<String>) map.get("banshi_list"); //能5
				for (String time : car_list) {
					de_grade -= GRADE_CL_GONGCHE;
					car_reList.add(new GradeResultEntity("减分", "德", HzGradeType.complains[0], String.valueOf(GRADE_CL_GONGCHE), time));
				}
				for(String time : jiufen_list) {
					neng_grade -= 2.0;
					neng_reList.add(new GradeResultEntity("减分", "能", HzGradeType.complains[4], "2.0", time));
				}
				for(String time : banshi_list) {
					neng_grade -= 1.0;
					neng_reList.add(new GradeResultEntity("减分", "能", HzGradeType.complains[5], "1.0", time));
				}
				for(String time : anquan_list) {
					ji_grade -= 5.0;
					ji_reList.add(new GradeResultEntity("减分", "绩", HzGradeType.complains[1], "5.0", time));
				}
				for(String time : weiji_list) {
					lian_grade -= 3.0;
					lian_reList.add(new GradeResultEntity("减分", "廉", HzGradeType.complains[2], "3.0", time));
				}
				for(String time : huihuo_list) {
					lian_grade -= 2.0;
					lian_reList.add(new GradeResultEntity("减分", "廉", HzGradeType.complains[3], "2.0", time));
				}
			}
			remap.put("car_relist_queryComplain", car_reList);
			remap.put("car_grade_queryComplain", de_grade);
			remap.put("neng_relist_queryComplain", neng_reList);
			remap.put("neng_grade_queryComplain", neng_grade);
			remap.put("ji_relist_queryComplain", ji_reList);
			remap.put("ji_grade_queryComplain", ji_grade);
			remap.put("lian_relist_queryComplain", lian_reList);
			remap.put("lian_grade_queryComplain", lian_grade);
			return remap;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	//会议扣分
	public Map<String, Object> queryMeetingGrade(String userId, String start, String end) {
		Map<String, Object> remap = new HashMap<String, Object>();
		List<GradeResultEntity> relist = new ArrayList<GradeResultEntity>();
		float meeting = 0;
		int wcj = 0;
		int wqd = 0;
		int wpz = 0;
		int wsb = 0;
		int count = 0;
		List<MeetingEntity> meetingList = jPushService.queryPassUser(userId, start, end);
		for (MeetingEntity meetingEntity : meetingList) {
			if(meetingEntity.getIsEnd() != 1) {
				//会议未开始，或者未结束  #本次会议不进入计算
				continue;
			}else{
				//本月有参加会议，如果为出现异常+1分
				count ++;
			}
			//根据mid查询所有签到数据
			List<MeetingTrajectoryEntity> list = meetingTrajectoryService.querymeetingTrajectoryEntityListByMid(meetingEntity.getMid());
			for (MeetingTrajectoryEntity meetingTrajectoryEntity : list) {
				if(!userId.equals(meetingTrajectoryEntity.getShouldAttendMetting())) {
					continue;
				}
				if(userId.equals(meetingTrajectoryEntity.getShouldAttendMetting())) {
					if(meetingTrajectoryEntity.getStartSignInTime() == null && meetingTrajectoryEntity.getEndSignInTime() == null) {
						//未参加 ++
						wcj++;
						GradeResultEntity gr = new GradeResultEntity("减分", "德", "未参加会议", String.valueOf(GRADE_HY_CANJIA), meetingEntity.getMeetingStartTime());
						relist.add(gr);
					}else{
						if(meetingTrajectoryEntity.getStartSignInTime() == null || meetingTrajectoryEntity.getEndSignInTime() == null) {
							//未签到 ++
							GradeResultEntity gr = new GradeResultEntity("减分", "德", "会议未签到", String.valueOf(GRADE_HY_QIANDAO), meetingEntity.getMeetingStartTime());
							relist.add(gr);
							wqd ++;
						}
						if(meetingTrajectoryEntity.getMeetingImagePath() == null) {
							//未拍照++
							wpz++;
							if(wpz > GRADE_HY_PAIZHAO_NUM) {
								GradeResultEntity gr = new GradeResultEntity("减分", "德", "会议未拍照", String.valueOf(GRADE_HY_QIANDAO), meetingEntity.getMeetingStartTime());
								relist.add(gr);
							}
						}
						if(meetingTrajectoryEntity.getMeetingSummary() == null) {
							//未上报++
							wsb++;
							if(wsb > GRADE_HY_SHANGBAO_NUM) {
								GradeResultEntity gr = new GradeResultEntity("减分", "德", "会议未上报", String.valueOf(GRADE_HY_QIANDAO), meetingEntity.getMeetingStartTime());
								relist.add(gr);
							}
						}
					}
				}
			}
		}
		
		meeting -= wcj * GRADE_HY_CANJIA;
		meeting -= wqd * GRADE_HY_QIANDAO;
		if(wpz > GRADE_HY_PAIZHAO_NUM) {
			meeting -= (wpz-GRADE_HY_PAIZHAO_NUM) * GRADE_HY_PAIZHAO;
		}
		if(wsb > GRADE_HY_SHANGBAO_NUM) {
			meeting -= (wsb-GRADE_HY_SHANGBAO_NUM) * GRADE_HY_SHANGBAO;
		}
		
		remap.put("count_queryMeetingGrade", count);
		remap.put("grade_queryMeetingGrade", meeting);
		remap.put("relist_queryMeetingGrade", relist);
		return remap;
	}
	//考勤扣分http://127.0.0.1:8082/check_workflow/CI/findAllNumByUserId?date=2017-12&userId=admin
	public Map<String, Object> queryCheckData(String userId, String date) {
		Map<String, Object> remap = new HashMap<String, Object>();
		List<GradeResultEntity> relist = new ArrayList<GradeResultEntity>();
		try {
			JSONObject obje = Urljson.readJsonFromUrl(GRADE_TO_CHECK_WORKFLOW+"?date="+date+"&userId="+userId);
			JSONObject map =  (JSONObject) obje.get("data");
			/*
			map.put("gradeKG", gradeKG);
			map.put("gradeZT", gradeZT);
			map.put("gradeCD", gradeCD);
			map.put("gradeQK", gradeQK);*/
			//旷工
			int kgdays = (Integer) map.get("kgdays");
			JSONArray gradeKG = JSON.parseArray(map.get("gradeKG").toString());
			for (Object object : gradeKG) {
				GradeResultEntity gr = new GradeResultEntity("减分", "勤", "旷工", String.valueOf(GRADE_KQ_KUANGGONG), object.toString());
				relist.add(gr);
			}
			//早退
			int ztdays = (Integer) map.get("ztdays");
			JSONArray gradeZT = JSON.parseArray(map.get("gradeZT").toString());
			for (int i=0; i<gradeZT.size(); i++) {
				if(i >= GRADE_KQ_ZAOTUI_NUM) {
					GradeResultEntity gr = new GradeResultEntity("减分", "勤", "早退", String.valueOf(GRADE_KQ_KUANGGONG), gradeZT.get(i).toString());
					relist.add(gr);
				}
			}
			//迟到
			int cddays = (Integer) map.get("cddays");
			JSONArray gradeCD = JSON.parseArray(map.get("gradeCD").toString());
			for (int i=0; i<gradeCD.size(); i++) {
				if(i >= GRADE_KQ_CHIDAO_NUM) {
					GradeResultEntity gr = new GradeResultEntity("减分", "勤", "迟到", String.valueOf(GRADE_KQ_CHIDAO), gradeCD.get(i).toString());
					relist.add(gr);
				}
			}
			//缺卡
			int qkdays = (Integer) map.get("qkdays");
			JSONArray gradeQK = JSON.parseArray(map.get("gradeQK").toString());
			for (int i=0; i<gradeQK.size(); i++) {
				if(i >= GRADE_KQ_QUEKA_NUM) {
					GradeResultEntity gr = new GradeResultEntity("减分", "勤", "缺卡", String.valueOf(GRADE_KQ_QUEKA), gradeQK.get(i).toString());
					relist.add(gr);
				}
			}
			//无故外出
			int wgwcdays = 0;
			List<String> gradeWC =  new ArrayList<String>();
			try {
				String name = userDataService.getUserName(userId);
				Date dEnd = DateUtils.lastDayOfMonthGrade(null,date);
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				Date dBegin = sdf.parse(date+"-01");
				//获取时间段日历
				List<Date> lDate = DateUtils.findDates(dBegin, dEnd);
				//遍历分析没一天，这里无故外出只计算天数，不计算次数
				for (Date dates : lDate) {
					String cntdate = sdf.format(dates);
					//请假
					List<?> list2 = gradeDao.leave(cntdate, name);
					//出差
					List<?> list3 = gradeDao.travel(cntdate, name);
					//周末节假日
					List<?> list4 = gradeDao.vacations(cntdate);
					//任务安排
					List<?> list5 = gradeDao.distribution(cntdate, name);
					if (list2.size() == 0 && list3.size() == 0 && list4.size() == 0 && list5.size() == 0) {
						String date3 = cntdate + " 09:00:00";
						String date4 = cntdate + " 12:00:00";
						String date31 = cntdate + " 14:00:00";
						String date41 = cntdate + " 17:00:00";
						//获取时间段内的经纬度数据
						List<?> list6 = (List<?>) gradeDao.latAndLon(cntdate, name, date3, date4);
						List<?> latAndLon = (List<?>) gradeDao.latAndLon(cntdate, name, date31, date41);
						//安监局经纬度
						double Lat_A = 106.638782;
						double Lng_A = 26.651684;
						for (Object object : list6) {
							Object[] objects = (Object[]) object;
							if("5e-324".equals(objects[0])) {
								continue;
							}
							double Lat_B = (Double) objects[1];
							double Lng_B = (Double) objects[0];
							double distance = BaiduUtil.getDistance(Lat_A, Lng_A, Lat_B, Lng_B);
							if (distance > 2) {
								wgwcdays ++;
								break;
							}
						}
						for (Object object : latAndLon) {
							Object[] objects = (Object[]) object;
							if("5e-324".equals(objects[0])) {
								continue;
							}
							double Lat_B = (Double) objects[1];
							double Lng_B = (Double) objects[0];
							double distance = BaiduUtil.getDistance(Lat_A, Lng_A, Lat_B, Lng_B);
							if (distance > 2) {
								wgwcdays ++;
								gradeWC.add(cntdate);
								break;
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			float zt = 0;
			if(ztdays > GRADE_KQ_ZAOTUI_NUM) {
				zt = -(ztdays - GRADE_KQ_ZAOTUI_NUM) * GRADE_KQ_ZAOTUI;
			}
			float cd = 0;
			if(cddays > GRADE_KQ_CHIDAO_NUM) {
				cd = -(cddays-GRADE_KQ_CHIDAO_NUM)* GRADE_KQ_CHIDAO;
			}
			float qk = 0;
			if(qkdays > GRADE_KQ_QUEKA_NUM) {
				qk = -(qkdays - GRADE_KQ_QUEKA_NUM) * GRADE_KQ_QUEKA;
			}
			float wc = 0;
			if(wgwcdays > GRADE_KQ_WAICHU_NUM) {
				wc = -(wgwcdays - GRADE_KQ_WAICHU_NUM) * GRADE_KQ_WAICHU;
			}
			for (int i=0; i<gradeWC.size(); i++) {
				if(i >= GRADE_KQ_WAICHU_NUM) {
					GradeResultEntity gr = new GradeResultEntity("减分", "勤",  "无故外出", String.valueOf(GRADE_KQ_WAICHU), gradeWC.get(i));
					relist.add(gr);
				}
			}
			float grade = -GRADE_KQ_KUANGGONG * kgdays + zt + cd + qk + wc;
			
			remap.put("relist_queryCheckData", relist);
			remap.put("grade_queryCheckData", grade);
			return remap;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	//逾期审核审批扣分
	@SuppressWarnings("unchecked")
	public Map<String, Object> queryAnjianTongjiOverdueDispose(String userId, String start, String end) {
		Map<String, Object> remap = new HashMap<String, Object>();
		int count = 0;
		List<GradeResultEntity> relist = new ArrayList<GradeResultEntity>();
		//获取节假日和周末
		List<String> holidayAndWeekend = userDataService.getHolidayAndWeekend();
		//登记颁发Node2审核，Node3审批
		List<String> FLOWID1sh = new ArrayList<String>();
		List<String> FLOWID1sp = new ArrayList<String>();
		FLOWID1sh.add("Node2");
		FLOWID1sp.add("Node3");
		//安全服务
		List<String> FLOWID2sh = new ArrayList<String>();
		List<String> FLOWID2sp = new ArrayList<String>();
		FLOWID2sh.add("Node4");
		FLOWID2sh.add("Node6");
		//权限内非煤矿山安全设施设计审查   
		List<String> FLOWID3sh = new ArrayList<String>();
		List<String> FLOWID3sp = new ArrayList<String>();
		FLOWID3sh.add("Node3");
		FLOWID3sh.add("Node10");
		FLOWID3sh.add("Node4");
		FLOWID3sh.add("Node7");
		FLOWID3sp.add("Node8");
		//安全生产标准化审批流程(初次申请)
		List<String> FLOWID4sh = new ArrayList<String>();
		List<String> FLOWID4sp = new ArrayList<String>();
		FLOWID4sh.add("Node3");
		FLOWID4sh.add("Node9");
		FLOWID4sp.add("Node10");
		//安全生产标准化审批流程(复审)
		List<String> FLOWID5sh = new ArrayList<String>();
		List<String> FLOWID5sp = new ArrayList<String>();
		FLOWID5sh.add("Node2");
		FLOWID5sp.add("Node3");
		float overdue = 0;
		try {
			Map<String, Object> map1 = gradeDao.queryAnjianTongjiOverdueDispose(TABSENAMES[0], userId, FLOWIDS[0], FLOWID1sh, FLOWID1sp, start, end, holidayAndWeekend);
			float list1 = Float.parseFloat(map1.get("grade").toString());
			relist.addAll((List<GradeResultEntity>) map1.get("relist"));
			count += Integer.parseInt(map1.get("count").toString());
			Map<String, Object> map2 = gradeDao.queryAnjianTongjiOverdueDispose(TABSENAMES[1], userId, FLOWIDS[1], FLOWID2sh, FLOWID2sp, start, end, holidayAndWeekend);
			float list2 = Float.parseFloat(map2.get("grade").toString());
			relist.addAll((List<GradeResultEntity>) map2.get("relist"));
			count += Integer.parseInt(map2.get("count").toString());
			Map<String, Object> map3 = gradeDao.queryAnjianTongjiOverdueDispose(TABSENAMES[2], userId, FLOWIDS[2], FLOWID3sh, FLOWID3sp, start, end, holidayAndWeekend);
			float list3 = Float.parseFloat(map3.get("grade").toString());
			relist.addAll((List<GradeResultEntity>) map3.get("relist"));
			count += Integer.parseInt(map3.get("count").toString());
			Map<String, Object> map4 = gradeDao.queryAnjianTongjiOverdueDispose(TABSENAMES[3], userId, FLOWIDS[3], FLOWID4sh, FLOWID4sp, start, end, holidayAndWeekend);
			float list4 = Float.parseFloat(map4.get("grade").toString());
			relist.addAll((List<GradeResultEntity>) map4.get("relist"));
			count += Integer.parseInt(map4.get("count").toString());
			Map<String, Object> map5 = gradeDao.queryAnjianTongjiOverdueDispose(TABSENAMES[4], userId, FLOWIDS[4], FLOWID5sh, FLOWID5sp, start, end, holidayAndWeekend);
			float list5 = Float.parseFloat(map5.get("grade").toString());
			relist.addAll((List<GradeResultEntity>) map5.get("relist"));
			count += Integer.parseInt(map5.get("count").toString());
			overdue = list1 + list2 + list3 +list4 + list5;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		remap.put("grade_queryAnjianTongjiOverdueDispose", overdue);
		remap.put("relist_queryAnjianTongjiOverdueDispose", relist);
		remap.put("count_queryAnjianTongjiOverdueDispose", count);
		return remap;
	}
	//逾期未审核、逾期未审批扣分
	public Map<String, Object> queryAnjianTongjiOverdueNotDispose(String userId, String start, String end) {
		Map<String, Object> remap = new HashMap<String, Object>();
		List<GradeResultEntity> relist = new ArrayList<GradeResultEntity>();
		//获取节假日和周末
		List<String> holidayAndWeekend = userDataService.getHolidayAndWeekend();
		float wsh = 0;
		float wsp = 0;
		//根据流程查询出对应workid的最新一条数据
		//登记颁发
		//List<WorkList> list1 = approvalProcessDao.queryLogNewestByFlowid(FLOWIDS[0], start, end);
		List<WorkList> list1 = gradeDao.queryLogNewestByFlowid(TABSENAMES[0], userId, FLOWIDS[0], start, end);
		for (WorkList workList : list1) {
			if("Node2".equals(workList.getNODEID())) {
				if(DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date()) > this.getLimitTime(workList.getLIMITTIME())) {
					int day = DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date()) - this.getLimitTime(workList.getLIMITTIME());
					wsh -= day * GRADE_OVERDUE_WSH;
					relist.add(new GradeResultEntity("减分", "绩", "逾期未审核", String.valueOf(day * GRADE_OVERDUE_WSH), workList.getSENDTIME()));
				}
			}
			if("Node3".equals(workList.getNODEID())) {
				if(DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date()) > this.getLimitTime(workList.getLIMITTIME())) {
					int day = DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date()) - this.getLimitTime(workList.getLIMITTIME());
					wsp -= day * GRADE_OVERDUE_WSP;
					relist.add(new GradeResultEntity("减分", "绩", "逾期未审批", String.valueOf(day * GRADE_OVERDUE_WSP), workList.getSENDTIME()));
				}
			}
		}
		//安全服务
		List<WorkList> list2 = gradeDao.queryLogNewestByFlowid(TABSENAMES[1], userId, FLOWIDS[1], start, end);
		for (WorkList workList : list2) {
			if("Node4".equals(workList.getNODEID()) || "Node6".equals(workList.getNODEID())) {
				if(DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date()) > this.getLimitTime(workList.getLIMITTIME())) {
					int day = DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date()) - this.getLimitTime(workList.getLIMITTIME());
					wsh -= day * GRADE_OVERDUE_WSH;
					relist.add(new GradeResultEntity("减分", "绩", "逾期未审核", String.valueOf(day * GRADE_OVERDUE_WSH), workList.getSENDTIME()));
				}
			}
		}
		//权限内非煤矿山安全设施设计审查   
		List<WorkList> list3 = gradeDao.queryLogNewestByFlowid(TABSENAMES[2], userId, FLOWIDS[2], start, end);
		for (WorkList workList : list3) {
			if("Node3".equals(workList.getNODEID()) || 
				"Node10".equals(workList.getNODEID()) ||
				"Node4".equals(workList.getNODEID()) ||
				"Node7".equals(workList.getNODEID())) {
				if(DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date()) > this.getLimitTime(workList.getLIMITTIME())) {
					int day = DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date()) - this.getLimitTime(workList.getLIMITTIME());
					wsh -= day * GRADE_OVERDUE_WSH;
					relist.add(new GradeResultEntity("减分", "绩", "逾期未审核", String.valueOf(day * GRADE_OVERDUE_WSH), workList.getSENDTIME()));
				}
				
			}
			if("Node8".equals(workList.getNODEID())) {
				if(DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date()) > this.getLimitTime(workList.getLIMITTIME())) {
					int day = DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date()) - this.getLimitTime(workList.getLIMITTIME());
					wsp -= day * GRADE_OVERDUE_WSP;
					relist.add(new GradeResultEntity("减分", "绩", "逾期未审批", String.valueOf(day * GRADE_OVERDUE_WSP), workList.getSENDTIME()));
				}
			}
		}
		//安全生产标准化审批流程(初次申请)
		List<WorkList> list4 = gradeDao.queryLogNewestByFlowid(TABSENAMES[3], userId, FLOWIDS[3], start, end);
		for (WorkList workList : list4) {
			if("Node3".equals(workList.getNODEID())  || 
				"Node9".equals(workList.getNODEID()) ) {
				if(DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date()) > this.getLimitTime(workList.getLIMITTIME())) {
					int day = DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date()) - this.getLimitTime(workList.getLIMITTIME());
					wsh -= day * GRADE_OVERDUE_WSH;
					relist.add(new GradeResultEntity("减分", "绩", "逾期未审核", String.valueOf(day * GRADE_OVERDUE_WSH), workList.getSENDTIME()));
				}
			}
			if("Node10".equals(workList.getNODEID())) {
				if(DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date()) > this.getLimitTime(workList.getLIMITTIME())) {
					int day = DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date()) - this.getLimitTime(workList.getLIMITTIME());
					wsp -= day * GRADE_OVERDUE_WSP;
					relist.add(new GradeResultEntity("减分", "绩", "逾期未审批", String.valueOf(day * GRADE_OVERDUE_WSP), workList.getSENDTIME()));
				}
			}
		}
		//安全生产标准化审批流程(复审)
		List<WorkList> list5 = gradeDao.queryLogNewestByFlowid(TABSENAMES[4], userId, FLOWIDS[4], start, end);
		for (WorkList workList : list5) {
			if("Node2".equals(workList.getNODEID())) {
				if(DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date()) > this.getLimitTime(workList.getLIMITTIME())) {
					int day = DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date()) - this.getLimitTime(workList.getLIMITTIME());
					wsh -= day * GRADE_OVERDUE_WSH;
					relist.add(new GradeResultEntity("减分", "绩", "逾期未审核", String.valueOf(day * GRADE_OVERDUE_WSH), workList.getSENDTIME()));
				}
				
			}
			if("Node3".equals(workList.getNODEID())) {
				if(DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date()) > this.getLimitTime(workList.getLIMITTIME())) {
					int day = DateUtils.getCurrentAndTime(holidayAndWeekend, workList.getSENDTIME(), new Date()) - this.getLimitTime(workList.getLIMITTIME());
					wsp -= day * GRADE_OVERDUE_WSP;
					relist.add(new GradeResultEntity("减分", "绩", "逾期未审批", String.valueOf(day * GRADE_OVERDUE_WSP), workList.getSENDTIME()));
				}
			}
		}
		
		remap.put("relist_queryAnjianTongjiOverdueNotDispose", relist);
		remap.put("grade_queryAnjianTongjiOverdueNotDispose", wsh+wsp);
		return remap;
	}
	//违规审核审批
	public Map<String, Object> queryManageIllegal(String userId, String start, String end) {
		Map<String, Object> remap = new HashMap<String, Object>();
		List<GradeResultEntity> relist = new ArrayList<GradeResultEntity>();
		String userName = userDataService.getUserName(userId);
		Map<String, Object> map1 = gradeDao.queryManageIllegal(userName, "审批违纪", start, end);
		Map<String, Object> map2 = gradeDao.queryManageIllegal(userName, "审核违纪", start, end);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) map1.get("list");
		for (String string : list) {
			relist.add(new GradeResultEntity("减分", "绩", "审批违纪", String.valueOf(GRADE_SP_ILLEGAL), string));
		}
		@SuppressWarnings("unchecked")
		List<String> list2 = (List<String>) map2.get("list");
		for (String string : list2) {
			relist.add(new GradeResultEntity("减分", "绩", "审核违纪", String.valueOf(GRADE_SH_ILLEGAL), string));
		}
		float grade = -(GRADE_SP_ILLEGAL * Integer.parseInt(map1.get("count").toString()) + GRADE_SH_ILLEGAL * Integer.parseInt(map2.get("count").toString()));
		
		remap.put("grade_queryManageIllegal", grade);
		remap.put("relist_queryManageIllegal", relist);
		return remap;
	}
	//工作流信息录入部分
	public Map<String, Object> queryHzGradeClass(String userId, String start, String end) {
		Map<String, Object> remap = new HashMap<String, Object>();
		List<GradeResultEntity> de_list = new ArrayList<GradeResultEntity>();
		List<GradeResultEntity> neng_list = new ArrayList<GradeResultEntity>();
		List<GradeResultEntity> qin_list = new ArrayList<GradeResultEntity>();
		List<GradeResultEntity> ji_list = new ArrayList<GradeResultEntity>();
		List<GradeResultEntity> lian_list = new ArrayList<GradeResultEntity>();
		String[] types = HzGradeType.types;
		List<GradeResultEntity> de_k = new ArrayList<GradeResultEntity>();//德扣分
		List<GradeResultEntity> de_j = new ArrayList<GradeResultEntity>();//德  +加分
		List<GradeResultEntity> neng_k = new ArrayList<GradeResultEntity>();//能扣分
		List<GradeResultEntity> neng_j = new ArrayList<GradeResultEntity>();//能 + 加分
		List<GradeResultEntity> qin_k = new ArrayList<GradeResultEntity>();//勤扣分
		List<GradeResultEntity> ji_k = new ArrayList<GradeResultEntity>();//绩扣分
		List<GradeResultEntity> lian_k = new ArrayList<GradeResultEntity>();//廉扣分
		List<GradeResultEntity> lian_j = new ArrayList<GradeResultEntity>();//廉 + 加分
		float de = 0;
		float neng = 0;
		float qin = 0;
		float ji = 0;
		float lian = 0;
		int dec = 0;
		int nengc = 0;
		int qinc = 0;
		int jic = 0;
		int lianc = 0;
		//德--> 0,1,2,3。                   加分1
		//能--> 4,5,6,7。                   加分6,7
		//勤--> 8
		//绩--> 9
		//廉--> 10,11, 12 。            加分 11,12
		List<HzGradeDate> list = gradeDao.queryHzGradeClass("U_"+userId, start, end);
		for (HzGradeDate hzGradeDate : list) {
			int type = Integer.parseInt(hzGradeDate.getXX());
			switch (type) {
			case 0:
				dec++;
				de += Float.parseFloat(hzGradeDate.getFZ());
				de_k.add(new GradeResultEntity("减分", "德", types[0], String.valueOf(Math.abs(Float.parseFloat(hzGradeDate.getFZ()))),hzGradeDate.getTIME()));
				break;
			case 1:
				dec++;
				de += Float.parseFloat(hzGradeDate.getFZ());
				de_j.add(new GradeResultEntity("加分", "德", types[1], String.valueOf(Math.abs(Float.parseFloat(hzGradeDate.getFZ()))),hzGradeDate.getTIME()));
				break;
			case 2:
				de += Float.parseFloat(hzGradeDate.getFZ());
				de_k.add(new GradeResultEntity("减分", "德", types[2], String.valueOf(Math.abs(Float.parseFloat(hzGradeDate.getFZ()))),hzGradeDate.getTIME()));
				break;
			case 3:
				dec++;
				de += Float.parseFloat(hzGradeDate.getFZ());
				de_k.add(new GradeResultEntity("减分", "德", types[3], String.valueOf(Math.abs(Float.parseFloat(hzGradeDate.getFZ()))),hzGradeDate.getTIME()));
				break;
			case 4:
				nengc++;
				neng += Float.parseFloat(hzGradeDate.getFZ());
				neng_k.add(new GradeResultEntity("减分", "能", types[4], String.valueOf(Math.abs(Float.parseFloat(hzGradeDate.getFZ()))),hzGradeDate.getTIME()));
				break;
			case 5:
				nengc++;
				neng += Float.parseFloat(hzGradeDate.getFZ());
				neng_k.add(new GradeResultEntity("减分", "能", types[5], String.valueOf(Math.abs(Float.parseFloat(hzGradeDate.getFZ()))),hzGradeDate.getTIME()));
				break;
			case 6:
				nengc++;
				neng += Float.parseFloat(hzGradeDate.getFZ());
				neng_j.add(new GradeResultEntity("加分", "能", types[6], String.valueOf(Math.abs(Float.parseFloat(hzGradeDate.getFZ()))),hzGradeDate.getTIME()));
				break;
			case 7:
				nengc++;
				neng += Float.parseFloat(hzGradeDate.getFZ());
				neng_k.add(new GradeResultEntity("加分", "能", types[7], String.valueOf(Math.abs(Float.parseFloat(hzGradeDate.getFZ()))),hzGradeDate.getTIME()));
				break;
			case 8:
				qinc++;
				qin += Float.parseFloat(hzGradeDate.getFZ());
				qin_k.add(new GradeResultEntity("减分", "勤", types[8], String.valueOf(Math.abs(Float.parseFloat(hzGradeDate.getFZ()))),hzGradeDate.getTIME()));
				break;
			case 9:
				jic++;
				ji += Float.parseFloat(hzGradeDate.getFZ());
				ji_k.add(new GradeResultEntity("减分", "绩", types[9], String.valueOf(Math.abs(Float.parseFloat(hzGradeDate.getFZ()))),hzGradeDate.getTIME()));
				break;
			case 10:
				lian += Float.parseFloat(hzGradeDate.getFZ());
				lian_k.add(new GradeResultEntity("减分", "廉", types[10], String.valueOf(Math.abs(Float.parseFloat(hzGradeDate.getFZ()))),hzGradeDate.getTIME()));
				break;
			case 11:
				lianc++;
				lian += Float.parseFloat(hzGradeDate.getFZ());
				lian_j.add(new GradeResultEntity("加分", "廉", types[11], String.valueOf(Math.abs(Float.parseFloat(hzGradeDate.getFZ()))),hzGradeDate.getTIME()));
				break;
			case 12:
				lianc++;
				lian += Float.parseFloat(hzGradeDate.getFZ());
				lian_j.add(new GradeResultEntity("加分", "廉", types[12], String.valueOf(Math.abs(Float.parseFloat(hzGradeDate.getFZ()))),hzGradeDate.getTIME()));
				break;
			default:
				break;
			}
		}
		de_list.addAll(de_k);   neng_list.addAll(neng_k);  qin_list.addAll(qin_k);   lian_list.addAll(lian_k);
		de_list.addAll(de_j);   neng_list.addAll(neng_j);  ji_list.addAll(ji_k);     lian_list.addAll(lian_j);
		
		remap.put("de_list_queryHzGradeClass", de_list);
		remap.put("neng_list_queryHzGradeClass", neng_list);
		remap.put("qin_list_queryHzGradeClass", qin_list);
		remap.put("ji_list_queryHzGradeClass", ji_list);
		remap.put("lian_list_queryHzGradeClass", lian_list);
		
		remap.put("de_grade_queryHzGradeClass", de);
		remap.put("neng_grade_queryHzGradeClass", neng);
		remap.put("qin_grade_queryHzGradeClass", qin);
		remap.put("ji_grade_queryHzGradeClass", ji);
		remap.put("lian_grade_queryHzGradeClass", lian);
		
		remap.put("de_count_queryHzGradeClass", dec);
		remap.put("neng_count_queryHzGradeClass", nengc);
		remap.put("qin_count_queryHzGradeClass", qinc);
		remap.put("ji_count_queryHzGradeClass", jic);
		remap.put("lian_count_queryHzGradeClass", lianc);
		return remap;
	}
	/**
	 * 督责问责
	 * @return
	 */
	public Map<String, Object> queryHzduzeAndWenze(String userId, String start, String end) {
		Map<String, Object> remap = new HashMap<String, Object>();
		float grade = 0;
		List<GradeResultEntity> relist = new ArrayList<GradeResultEntity>();
		List<GradeResultEntity> dz_list = new ArrayList<GradeResultEntity>();
		List<GradeResultEntity> wz_list = new ArrayList<GradeResultEntity>();
		List<String> dztimelist = gradeDao.queryHzDuze(userDataService.getUserName(userId), start, end);
		List<String> wztimelist = gradeDao.queryHzWenze(userDataService.getUserName(userId), start, end);
		for (String time : dztimelist) {
			dz_list.add(new GradeResultEntity("减分", "廉", "督责", String.valueOf(GRADE_DUZE) ,time));
			grade -= GRADE_DUZE;
		}
		for (String time : wztimelist) {
			wz_list.add(new GradeResultEntity("减分", "廉", "问责", String.valueOf(GRADE_WENZE) ,time));
			GRADE_DUZE -= GRADE_WENZE;
		}
		
		relist.addAll(dz_list);
		relist.addAll(wz_list);
		remap.put("grade_queryHzduzeAndWenze", grade);
		remap.put("dz_list_queryHzduzeAndWenze", dz_list);
		remap.put("wz_list_queryHzduzeAndWenze", wz_list);
		remap.put("relist_queryHzduzeAndWenze", relist);
		return remap;
	}
	private int getLimitTime(String limitTime) {
		if(!(limitTime == null || "".equals(limitTime))) {
			int indexOf = limitTime.indexOf("(");
			return Integer.parseInt(limitTime.substring(0, indexOf));
		}
		return 0;
	}

}
