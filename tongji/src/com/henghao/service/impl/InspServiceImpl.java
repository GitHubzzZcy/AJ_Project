package com.henghao.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.henghao.dao.IInspDao;
import com.henghao.dao.INoteDao;
import com.henghao.entity.Accident;
import com.henghao.entity.ExamineFirm;
import com.henghao.entity.Inspect;
import com.henghao.entity.Note;
import com.henghao.entity.Plan;
import com.henghao.entity.Result;
import com.henghao.entity.Roll;
import com.henghao.service.IInspService;
import com.henghao.service.WorkService;
import com.henghao.util.DateUtils;
import com.henghao.util.Log;
import com.henghao.util.SpringBeanUtil;
import com.henghao.util.StringToXmlUtil;
import com.henghao.util.Urljson;
import com.ruanwei.interfacej.SmsClientSend;
@Service("inspService")
public class InspServiceImpl implements IInspService {
	
	private Map<String, Object> map = new HashMap<String, Object>();
	//获取个人带电话号码
	private static final String gethzphoneurl = "http://127.0.0.1:8082/istration/userData/getUserPhone?username=";
	//获取领导电话号码
	private static final String gethzleadphoneurl = "http://127.0.0.1:8082/istration/userData/getUserLead?username=";
	private static final String url = "http://115.29.242.32:8888/sms.aspx";
	private static final String userid = "1214";
	private static final String account = "hhrj";
	private static final String password = "336699";
	//纪委
	private static final String jiwei = "兰天";
	@Resource
	private IInspDao inspDao;
	@Resource
	private WorkService workService;
	
	
	private String getUserId(String name) {
		return inspDao.getUserId(name);
	}
	private String getUserName(String userid) {
		if(null == userid) {
			return null;
		}
		return inspDao.getUserName(userid);
	}
	
	@Override
	public Result queryCount() {
		Result result = null;
		List<String> ycounts  = new ArrayList<String>();
		List<String> mcounts  = new ArrayList<String>();
		List<String> daycount  = new ArrayList<String>();
		try {
			Date ny = DateUtils.getCurrentDateT("yyyy-MM");
			Date nian = DateUtils.getCurrentDateT("yyyy");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			String format = sdf.format(nian);
			map.clear();
			map.put("year", format);
			String start;
			int parseInt = Integer.parseInt(format);
			//查询年办件总数
			String ycount = inspDao.queryCount(format, String.valueOf(parseInt+1));
			//查询超期
			String chao = inspDao.querychao();
			//查询未完成
			String undone = inspDao.queryundone();
			ycounts.add(ycount);
			ycounts.add(chao);
			ycounts.add(undone);
			
			
			for(int i=1; i<13; i++) {
				format = sdf.format(nian);
				if(i == 12) {
					start = format + "-" + i;
					format = String.valueOf(parseInt+1) + "-" + 1;
				}else{
					start = format + "-" + i;
					format = format + "-" + (i+1);
				}
				String mcount = inspDao.querymm(start,format);
				mcounts.add(mcount);
			}
			//获取当月最后一天
			Date lastDayOfMonth = DateUtils.getLastDayOfMonth(new Date());
			//获得本月有多少天
			String formatedDate = DateUtils.getFormatedDate(lastDayOfMonth, "dd");
			
			int length = Integer.parseInt(formatedDate);
			map.put("day", length);
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
			
			for(int i=1; i<length+1; i++) {
				String yymd = sdf2.format(ny);
				yymd = yymd + "-" + i;
				String dcount = inspDao.queryDay(yymd);
				daycount.add(dcount);
			}
			
			map.put("yytj", ycounts);
			map.put("mmtj", mcounts);
			map.put("ddtj", daycount);
			result = new Result(0,"统计查询成功",map);
		}catch (Exception e) {
			result = new Result(1,"统计查询失败",null);
			Log log = Log.getLogger();
			log.logger.error("执法年月日统计", e);
			e.printStackTrace();
		}
		return result;//2017-5-24上午9:52:09宁万龙
	}
	
	@Override
	public Result eachMonth(String name) {
		Result result = null;
		try {
			List<String> mcounts  = new ArrayList<String>();
			Date nian = DateUtils.getCurrentDateT("yyyy");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			String format = sdf.format(nian);
			String start;
			int parseInt = Integer.parseInt(format);
			for(int i=1; i<13; i++) {
				format = sdf.format(nian);
				if(i == 12) {
					start = format + "-" + i;
					format = String.valueOf(parseInt+1) + "-" + 1;
				}else{
					start = format + "-" + i;
					format = format + "-" + (i+1);
				}
				String mcount = inspDao.eachMonth(this.getUserId(name),start,format);
				mcounts.add(mcount);
			}
			result = new Result(0,"查询成功",mcounts);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result = new Result(1,"查询失败",null);
			Log log = Log.getLogger();
			log.logger.error("个人执法月度统计", e);
			e.printStackTrace();
		}
		return result;
	}
	
	
	@Override
	public Result eachQuarter(String name) {
		Result result = null;
		try {
			List<String> yibanlist  = new ArrayList<String>();
			List<String> zhongdalist  = new ArrayList<String>();
			String year = DateUtils.getCurrentDate("yyyy");
			for(int i=1; i<5; i++) {
				String stardate = DateUtils.getStardate(year+"-"+i);
				String enddate = DateUtils.getEnddate(year+"-"+i);
				//一般隐患
				//查询隐患表
				String yiban = inspDao.eachQuarter("1", name, stardate, enddate);
				yibanlist.add(yiban);
				//重大隐患//查询隐患表
				String zhongda = inspDao.eachQuarter("2", name, stardate, enddate);
				zhongdalist.add(zhongda);
			}
			map.clear();
			map.put("yiban", yibanlist);
			map.put("zhongda", zhongdalist);
			result = new Result(0,"查询成功",map);
		} catch (Exception e) {
			// TODO 隐患类别统计
			result = new Result(1,"查询失败",null);
			Log log = Log.getLogger();
			log.logger.error("个人执法季度分类", e);
			e.printStackTrace();
		}
		return result;
	}
	
	
	@Override
	public Result eachClassify(String name) {
		Result result = null;
		List<String> relist  = new ArrayList<String>();
		try {
			String startdate = DateUtils.getCurrentDate("yyyy-")+"01-01";
			String currentDate = DateUtils.getCurrentDate("yyyy-MM-dd");
			String[] classs = {"A","B","C","E","F","G","H","R","M","WFL"};
			for (String code : classs) {
				String count = inspDao.eachClassify(this.getUserId(name),code,startdate,currentDate);
				relist.add(count);
			}
			result = new Result(0,"查询成功",relist);
		} catch (Exception e) {
			result = new Result(1,"查询失败",null);
			Log log = Log.getLogger();
			log.logger.error("个人执法分类统计", e);
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	@Override
	public Result eachDeviate(String name) {
		Result result = null;
		try {
			String year = DateUtils.getCurrentDate("yyyy");
			int zc = 0;
			int pl = 0;
			Result pianli = workService.searchPunishById(name);
			List<?> list = (List<?>) pianli.getData();
			for (Object object : list) {
				JSONObject obj = (JSONObject) object;
				String chengdu = obj.get("state").toString();
				String date = obj.get("punishTime").toString();
				String dateyaer = date.substring(0, 4);
				if(year.equals(dateyaer)) {
					if("正常".equals(chengdu)) {
						zc++;
					}else{
						pl++;
					}
				}
			}
			map.clear();
			map.put("normal", zc);
			map.put("departure", pl);
			result = new Result(0,"查询成功",map);
		} catch (Exception e) {
			// TODO 个人执法处罚分析
			result = new Result(1,"查询失败",null);
			Log log = Log.getLogger();
			log.logger.error("个人执法处罚分析", e);
			e.printStackTrace();
		}
		return result;
	}
	
	
	@Override
	public Result eachWork(String name) {
		Result result = null;
		try {
			String year = DateUtils.getCurrentDate("yyyy");
			Result urljson = workService.searchWorkByName(name);
			List<?> list = (List<?>) urljson.getData();
			List<Integer> relist = new ArrayList<Integer>();
			int hour1 = 0;
			int hour2 = 0;
			int hour3 = 0;
			int hour4 = 0;
			int hour5 = 0;
			int hour6 = 0;
			int hour7 = 0;
			int hour8 = 0;
			for (Object object : list) {
				JSONObject obj = (JSONObject) object;
				String date = obj.get("time").toString();
				String dateyear = date.substring(0, 4);
				if(year.equals(dateyear)) {
					//获取时间小时
					int hour = Integer.parseInt(date.substring(11, 13));
					switch (hour) {                                 
						case 9: hour1 ++;
							break;
						case 10: hour2 ++;
							break;
						case 11: hour3 ++;
							break;
						case 12: hour4 ++;
							break;
						case 13: hour5 ++;
							break;
						case 14: hour6 ++;
							break;
						case 15: hour7 ++;
							break;
						case 16: hour8 ++;
							break;
						default:
							break;
					}
				}
			}
			relist.add(hour1);	
			relist.add(hour2);	
			relist.add(hour3);	
			relist.add(hour4);	
			relist.add(hour5);	
			relist.add(hour6);	
			relist.add(hour7);	
			relist.add(hour8);
			result = new Result(0,"查询成功",relist);
		} catch (Exception e) {
			//TODO 个人工作时间分布
			result = new Result(1,"查询失败",null);
			Log log = Log.getLogger();
			log.logger.error("个人工作时间分布", e);
			e.printStackTrace();
		}	
		return result;
	}

	@Override
	public Result eachFirmFaith(String name) {
		Result result = null;
		try {
			String year = DateUtils.getCurrentDate("yyyy");
			Result urljson = workService.searchBeyondById(name);
			List<?> list = (List<?>) urljson.getData();
			int zc = 0;
			int cq = 0;
			for (Object object : list) {
				JSONObject obj = (JSONObject) object;
				String status = obj.get("isBeyondRate").toString();
				//检查结束时间
				String enddate = obj.get("inspectEndTime").toString();
				String dateyaer = enddate.substring(0, 4);
				if(year.equals(dateyaer)) {
					if("正常".equals(status)) {
						zc++;
					}
					if("超期".equals(status)) {
						cq++;
					}
				}
			}
			map.clear();
			map.put("zhengc", zc);
			map.put("chaoq", cq);
			result = new Result(0,"查询成功",map);
		} catch (Exception e) {
			//TODO 企业诚信情况
			result = new Result(1,"查询失败",null);
			Log log = Log.getLogger();
			log.logger.error("企业诚信情况", e);
			e.printStackTrace();
		}
		return result;
	}



	@Override
	public Result eachLately(String name) {
		Result result = null;
		try {
			String userId = this.getUserId(name);
			int currentday = Integer.parseInt(DateUtils.getCurrentDate("dd"));
			String currentmonth = DateUtils.getCurrentDate("yyyy-MM");
			String enddate = DateUtils.getCurrentDate("yyyy-MM-dd");
			String startdate = null;
			if(currentday >= 28) {
				startdate = currentmonth + "-01";
			}else{
				//前月最后一天
				String month2 = DateUtils.month2();
				startdate = month2.substring(0, 8) +DateUtils.getCurrentDate("dd");
			}
			List<ExamineFirm> relist =  inspDao.eachLately(userId, startdate, enddate);
			List<String> diqu = new ArrayList<String>();
			List<String> classes = new ArrayList<String>();
			for (ExamineFirm examineFirm : relist) {
				String district3 = examineFirm.getDISTRICT3();
				String data1 = examineFirm.getDATA1();
				if(!diqu.contains(district3)){
					diqu.add(district3);
				}
				if(!classes.contains(data1)) {
					classes.add(data1);
				}
			}
			map.clear();
			map.put("relist", relist);
			map.put("diqu", diqu);
			map.put("classes", classes);
			result = new Result(0,"查询成功",map);
		} catch (Exception e) {
			//TODO最近去执法企业所属地区和类别
			result = new Result(1,"查询失败",null);
			Log log = Log.getLogger();
			log.logger.error("最近去执法企业所属地区和类别", e);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result inspectOA(String name) {
		Result result = null;
		try {
			String userId = this.getUserId(name);
			String currentDate = DateUtils.getCurrentDate("yyyy-MM-dd");
			int parseInt = Integer.parseInt(DateUtils.getCurrentDate("yyyy"));
			String starttime = String.valueOf(parseInt-1)+DateUtils.getCurrentDate("-MM")+"-01";
			List<Inspect> list = inspDao.inspectOA(userId, starttime, currentDate);
			List<String> qiye =  new ArrayList<String>();
			for (Inspect inspect : list) {
				inspect.setCHKPERSON1(this.getUserName(inspect.getCHKPERSON1()));
				inspect.setCHKPERSON2(this.getUserName(inspect.getCHKPERSON2()));
				inspect.setCHKPERSON3(this.getUserName(inspect.getCHKPERSON3()));
				String entname = inspect.getENTNAME();
				if(!qiye.contains(entname)) {
					qiye.add(entname);
				}
				qiye.remove(null);
			}
			List<Object> relist = new ArrayList<Object>();
			for (int i=0; i<qiye.size(); i++) {
				List<Object> entlist = new ArrayList<Object>();
				List<Inspect> slist = new ArrayList<Inspect>();
				for (int y=0; y<list.size(); y++) {
					if(qiye.get(i).equals(list.get(y).getENTNAME())) {
						//存在隐患
						if(null == list.get(y).getHASHIDDEN()) {
							BigDecimal bigDecimal = new BigDecimal(Double.toString(0));
							list.get(y).setHASHIDDEN(bigDecimal);
						}
						if("1".equals(list.get(y).getHASHIDDEN().toString())) {
							/*********存在隐患，去隐患表查询隐患信息**********/
							String id = list.get(y).getID();
							Accident accident = inspDao.queryAccident(id);
							list.get(y).setAccident(accident);
							/******************************************/
							slist.add(list.get(y));
							//如果是最后一条
							if(y == list.size()-1) {
								entlist.add(slist);
								continue;
							}
							//如果下一条不是当前企业
							if(!qiye.get(i).equals(list.get(y+1).getENTNAME())) {
								entlist.add(slist);
							}
						}else{
							//不存在隐患
							slist.add(list.get(y));
							entlist.add(slist);
							//不存在隐患后下一次执法就属于首次执法，且属于另一组的返回数据
							slist = new ArrayList<Inspect>();
						}
					}
				}
				relist.add(entlist);
			}
			result = new Result(0,"查询成功",relist);
		} catch (Exception e) {
			//执法OA详情
			result = new Result(1,"查询失败",null);
			Log log = Log.getLogger();
			log.logger.error("执法OA详情", e);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result monthCount(String name) {
		Result result = null;
		try {
			List<String> mcounts  = new ArrayList<String>();
			List<String> orders  = new ArrayList<String>();
			Date nian = DateUtils.getCurrentDateT("yyyy");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			String format = sdf.format(nian);
			String start;
			int parseInt = Integer.parseInt(format);
			for(int i=1; i<13; i++) {
				format = sdf.format(nian);
				if(i == 1) {
					//如果是查询1月份时
					start = String.valueOf(parseInt-1) + "-" + 12;
					format = format + "-" + i;
				}else {
					if(i == 12) {
						//如果是12月
						start = format + "-" + i;
						format = String.valueOf(parseInt+1) + "-" + 1;
					}else{
						start = format + "-" + (i-1);
						format = format + "-" + i;
					}
				}
				String mcount = inspDao.eachMonth(this.getUserId(name),start,format);
				mcounts.add(mcount);
				//下令整改
				String order = inspDao.monthOrder(this.getUserId(name),start,format,"2");
				orders.add(order);
			}
			map.clear();
			map.put("mcounts", mcounts);
			map.put("orders", orders);
			result = new Result(0,"查询成功",map);
		} catch (Exception e) {
			//TODO 个人执法月度办件分类统计
			result = new Result(1,"查询失败",null);
			Log log = Log.getLogger();
			log.logger.error("个人执法月度办件分类统计", e);
			e.printStackTrace();
		}
		return result;
	}
	//滚动
	@Override
	public Result queryroll(String name) {
		Result result = null;
		try {
			List<Roll> relist = new ArrayList<Roll>();
			String end = DateUtils.getCurrentDate("yyyy-MM-dd");
			int parseInt = Integer.parseInt(DateUtils.getCurrentDate("yyyy"));
			String start = String.valueOf(parseInt-1) +"-"+ DateUtils.getCurrentDate("MM")+ "-01";
			List<Roll> list = inspDao.queryroll(this.getUserId(name), start, end);
			for (Roll roll : list) {
				if(null != roll.getENTNAME()) {
					if(null != roll.getENDTIME()) {
						roll.setSTATUS("已完成");
					}else{
						roll.setSTATUS("未完结");
					}
					relist.add(roll);
				}
			}
			result = new Result(0,"查询成功",relist);
		} catch (Exception e) {
			result = new Result(1,"查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	
	/**
	 * 执法短信服务
	 */
	@SuppressWarnings("unused")
	@Override
	public Result noteserve()   {
		Result result = null;
		try {
			List<Object> relist = new ArrayList<Object>();
			IInspDao inspDao = (IInspDao) SpringBeanUtil.getBeanByName("inspDao");
			INoteDao noteDao = (INoteDao) SpringBeanUtil.getBeanByName("noteDao");
//			System.out.println("进入发短信");
//			String test = SmsClientSend.sendSms(url, userid, account, password, "18786729676" , "第一次服务器测试"+DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss")+"。【贵阳市安监局】");
//			System.out.println(test);
//			Plan plan = new Plan();
//			plan.setPERSON1("b17dae80676f48499e85615952944568");//唐小兵
//			plan.setPLANTIME(new Date());
//			plan.setENTID("d89a743aa198405aa2805321b96e3641");//测试企业
			List<Plan> planlist = inspDao.queryPlan();
			//获取纪委电话，纪委为：“兰天”
			org.json.JSONObject readJson = Urljson.readJsonFromUrl(gethzphoneurl + java.net.URLEncoder.encode(jiwei, "UTF-8"));
			String jphone = readJson.get("data").toString();
			for (Plan plan : planlist) {
				String userName1 = this.getUserName(plan.getPERSON1());
				String userName2 = this.getUserName(plan.getPERSON2());
				String userName3 = this.getUserName(plan.getPERSON3());
				String userName4 = this.getUserName(plan.getPERSON4());
				List<String> namelist = new ArrayList<String>();
				StringBuffer names = new StringBuffer();
				if(null != userName1) {
					namelist.add(userName1);
					names.append(userName1+"、");
				}
				if(null != userName2) {
					namelist.add(userName2);
					names.append(userName2+"、");
				}
				if(null != userName3) {
					namelist.add(userName3);
					names.append(userName3+"、");
				}
				if(null != userName4) {
					namelist.add(userName4);
					names.append(userName4+"、");
				}
				StringBuffer sb = new StringBuffer();
				for (String username : namelist) {
					String data = java.net.URLEncoder.encode(username, "UTF-8");
					org.json.JSONObject readJsonFromUrl = Urljson.readJsonFromUrl(gethzphoneurl + data);
					String phone = readJsonFromUrl.get("data").toString();
					//没有号码时返回-1
					if(! "-1".equals(phone)) {
						sb.append(phone + ",");
					}
				}
				String renames = null;
				if(names.length() > 0) {
					renames = names.substring(0, names.length()-1);
				}
				String phones = null;
				if(sb.length() > 0) {
					phones = sb.substring(0, sb.length()-1);
				}
				StringBuffer fphones = new StringBuffer();
				StringBuffer cphones = new StringBuffer();
				for (String username : namelist) {
					String data = java.net.URLEncoder.encode(username, "UTF-8");
					org.json.JSONObject readJsonFromUrl = Urljson.readJsonFromUrl(gethzleadphoneurl + data);
					org.json.JSONObject object = (org.json.JSONObject) readJsonFromUrl.get("data");
					
					//部门负责人
					String fphone = null;
					Object fobject = object.get("fphone");
					if(null != fobject) {
						fphone = fobject.toString();
						fphones.append(fphone + ",");
					}
					//主管领导
					String cphone = null;
					Object cobject = object.get("cphone");
					if(null != cobject) {
						cphone = cobject.toString();
						cphones.append(cphone + ",");
					}
				}
				String fphone = null;
				if(fphones.length() > 0) {
					fphone = fphones.subSequence(0, fphones.length()-1).toString();
				}
				String cphone = null;
				if(cphones.length() > 0) {
					cphone = cphones.subSequence(0, cphones.length()-1).toString();
				}
				
				ExamineFirm ef = inspDao.queryEntname(plan.getENTID());
				String entname = null;
				String legalmobilephone = null;
				String regaddress = null;
				if(null != ef) {
					entname = ef.getENTNAME();
					legalmobilephone = ef.getLEGALMOBILEPHONE();
					regaddress = ef.getREGADDRESS();
				}
				//转换标准时间 yyyy-MM-dd 00:00:00
				Date plantime = DateUtils.parseDateFromStringT(DateUtils.getFormatedDate(plan.getPLANTIME(), "yyyy-MM-dd 00:00:00"));
				Date currentDate = DateUtils.getCurrentDateT("yyyy-MM-dd");
				int daysBetween = DateUtils.daysBetween(plantime, currentDate);
				
				String reday = DateUtils.getFormatedDate(plan.getPLANTIME(), "yyyy")+"年"+DateUtils.getFormatedDate(plan.getPLANTIME(), "MM")+"月"+DateUtils.getFormatedDate(plan.getPLANTIME(), "dd")+"日";
				//执法人员未超期
				String desc = "你有一个计划执法任务，执法企业为"+entname+"，请在"+reday+"内完成检查任务并录入数据。【贵阳市安监局】";
				if(null != legalmobilephone) {
					desc = "你有一个计划执法任务，执法企业为"+entname+"，企业联系人电话:"+legalmobilephone+"，请在"+reday+"内完成检查任务并录入数据。【贵阳市安监局】";
				}
				if(null != regaddress) {
					desc = "你有一个计划执法任务，执法企业为"+entname+"，企业地址为:"+regaddress+"，请在"+reday+"内完成检查任务并录入数据。【贵阳市安监局】";
				}
				if(null != legalmobilephone && null != regaddress) {
					desc = "你有一个计划执法任务，执法企业为"+entname+"，企业联系人电话:"+legalmobilephone+"，企业地址为:"+regaddress+"，请在"+reday+"内完成检查任务并录入数据。【贵阳市安监局】";
				}
				//执法人员超期
				if(daysBetween == -1) {
					desc = "你有一个计划执法任务，执法企业为"+entname+"，已经超出计划执法时间一天，请在尽快完成检查任务并录入数据。【贵阳市安监局】";
					if(null != legalmobilephone) {
						desc = "你有一个计划执法任务，执法企业为"+entname+"，企业联系人电话:"+legalmobilephone+"，已经超出计划执法时间一天，请在尽快完成检查任务并录入数据。【贵阳市安监局】";
					}
					if(null != regaddress) {
						desc = "你有一个计划执法任务，执法企业为"+entname+"，企业地址为:"+regaddress+"，已经超出计划执法时间一天，请在尽快完成检查任务并录入数据。【贵阳市安监局】";
					}
					if(null != legalmobilephone && null != regaddress) {
						desc = "你有一个计划执法任务，执法企业为"+entname+"，企业联系人电话:"+legalmobilephone+"，企业地址为:"+regaddress+"，已经超出计划执法时间一天，请在尽快完成检查任务并录入数据。【贵阳市安监局】";
					}
				}
				//领导未超期
				String leaddesc = renames+"等人有计划执法任务即将到期，执法企业为"+entname+"，请提醒在"+reday+"内完成检查任务并录入数据。【贵阳市安监局】";
				if(null != legalmobilephone) {
					leaddesc = renames+"等人有计划执法任务即将到期，执法企业为"+entname+"，企业联系人电话:"+legalmobilephone+"，请提醒在"+reday+"内完成检查任务并录入数据。【贵阳市安监局】";
				}
				if(null != regaddress) {
					leaddesc = renames+"等人有计划执法任务即将到期，执法企业为"+entname+"，企业地址为:"+regaddress+"，请提醒在"+reday+"内完成检查任务并录入数据。【贵阳市安监局】";
				}
				if(null != legalmobilephone && null != regaddress) {
					leaddesc = renames+"等人有计划执法任务即将到期，执法企业为"+entname+"，企业联系人电话:"+legalmobilephone+"，企业地址为:"+regaddress+"，请提醒在"+reday+"内完成检查任务并录入数据。【贵阳市安监局】";
				}
				//领导超期
				if(daysBetween == -1) {
					leaddesc = renames+"等人有计划执法任务已过期未完成，执法企业为"+entname+"，已经超出计划执法时间一天。【贵阳市安监局】";
					if(null != legalmobilephone) {
						leaddesc = renames+"等人有计划执法任务已过期未完成，执法企业为"+entname+"，企业联系人电话:"+legalmobilephone+"，已经超出计划执法时间一天。【贵阳市安监局】";
					}
					if(null != regaddress) {
						leaddesc = renames+"等人有计划执法任务已过期未完成，执法企业为"+entname+"，企业地址为:"+regaddress+"，已经超出计划执法时间一天。【贵阳市安监局】";
					}
					if(null != legalmobilephone && null != regaddress) {
						leaddesc = renames+"等人有计划执法任务已过期未完成，执法企业为"+entname+"，企业联系人电话:"+legalmobilephone+"，企业地址为:"+regaddress+"，已经超出计划执法时间一天。【贵阳市安监局】";
					}
				}
				Note notes = noteDao.queryPlanId(plan.getID());
				
				String formatedDate = DateUtils.getFormatedDate(notes.getCurrentTime(), "yyyy-MM-dd");
				String currentymd = DateUtils.getCurrentDate("yyyy-MM-dd");
				
				if(null != phones) {
					System.out.println("进入短信发送：发送号码为"+phones+"   部门负责人："+fphone+"    领导："+cphone+"  纪委："+jphone);
					//首次发送时获取到的是当前时间的前一天,currentymd.equals(formatedDate) = false,发送成功后将从数据库获取结果为true
					if(daysBetween <= 2 && daysBetween >= -1 && !currentymd.equals(formatedDate)) {
						String sendSms = SmsClientSend.sendSms(url, userid, account, password, phones , desc);
						String regl = null;
						if(daysBetween == 1 && null != fphone) {
							regl = SmsClientSend.sendSms(url, userid, account, password, fphone , leaddesc);
						}
						String reld = null;
						if(daysBetween == 0  || daysBetween == -1) {
							if(null != fphone && null != cphone) {
								SmsClientSend.sendSms(url, userid, account, password, fphone + "," + cphone , leaddesc);
							}
							if(null == fphone && null != cphone) {
								SmsClientSend.sendSms(url, userid, account, password, cphone , leaddesc);
							}
							if(null != fphone && null == cphone) {
								SmsClientSend.sendSms(url, userid, account, password, fphone , leaddesc);
							}
						}
						String rejw = null;
						if(daysBetween == -1) {
							if(!"-1".equals(jphone) ) {
								rejw = SmsClientSend.sendSms(url, userid, account, password, jphone , leaddesc);
							}
						}
						String noteserve = StringToXmlUtil.noteserve(sendSms);
						StringBuffer resb = new StringBuffer();
						if("Success".equals(noteserve)) {
							resb.append("执法人短信发送成功，");
							//发送短信成功
							Note note = new Note();
							note.setPlanId(plan.getID());
							note.setNumbers(daysBetween);
							note.setCurrentTime(new Date());
							//将计划id持久到数据库
							noteDao.add(note);
						}
						if(null != regl) {
							if("Success".equals(StringToXmlUtil.noteserve(regl))){
								resb.append("部门负责人短信发送成功，");
							}
						}
						if(null != reld) {
							if("Success".equals(StringToXmlUtil.noteserve(reld))){
								resb.append("领导短信发送成功，");
							}
						}
						if(null != rejw) {
							if("Success".equals(StringToXmlUtil.noteserve(rejw))){
								resb.append("纪委短信发送成功，");
							}
						}
						if(resb.length() > 0) {
							resb.substring(0, resb.length()-1);
						}
						relist.add(resb);
					}
				}
			}
			result = new Result(0,"短信服务未出错",relist);
		} catch (Exception e) {
			//TODO 
			result = new Result(1,"短信服务出错",null);
			Log log = Log.getLogger();
			log.logger.error("执法计划短信提醒**************", e);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 累计执法统计
	 */
	@Override
	public Result accumulativeCount(String name) {
		Result result = null;
		try {
			//待执法，正常执法，超期执法，被投诉
			String count1 = inspDao.queryDai(this.getUserId(name));
			String count2 = inspDao.queryZc(this.getUserId(name));
			String count3 = inspDao.queryCq(this.getUserId(name));
			map.clear();
			map.put("dai", count1);
			map.put("zc", count2);
			map.put("cq", count3);
			map.put("ts", "0");
			result = new Result(0,"查询成功",map);
		} catch (Exception e) {
			result = new Result(1,"查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	
	
}
