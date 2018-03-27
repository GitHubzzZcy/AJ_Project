package com.henghao.news.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.henghao.news.dao.IndexDao;
import com.henghao.news.entity.DetailsEntity;
import com.henghao.news.util.DateUtils;

@Service
public class IndexService {

	@Resource
	private IndexDao indexDao;
	@Resource
	private DetailsService detailsService;
	@Resource
	private ApprovalProcessService approvalProcessService;
	private static final String[] FLOWIDS = {"djbf", "aqfw", "feimeikuangshan(yichu)", "sichu(chucishenqing)", "sichu(fushen)"};
	private static final String[] TABSENAMES = {"AJ_QXNFMKSAQSCXKDJBF","AJ_QXNFMKSAQSSSJSCAQFF","AJ_QXNFMKSAQSSSJSC","AJ_SC_ONE","AJ_SC_TWO"};
	private static final String[] LASTNODES = {"Node2","Node6","Node7","Node9","Node2"};
	private static final String[] NODES = {"Node3","Node4","Node8","Node10","Node3"};
	private static final String[] NEXTNODES = {"End4;","End5;","End9;","End1;","End4;"};
	private static final String[] FIRM_NAME = {"SQDW", "QY", "QY", "FIRMNAME", "DWMC"};
	private static final String[] FLOWNAMES = {"权限内非煤矿山安全生产许可登记颁发", "权限内非煤矿山安全设施设计_安全服务", "权限内非煤矿山安全设施设计审查", "安全生产标准化审批流程(初次申请)", "安全生产标准化审批流程(复审)"};
	
	public Map<String, Object> queryApproveMonth() throws ParseException {
		Map<String, Object> remap = new HashMap<String, Object>();
		//本月开始时间
		String startdate = DateUtils.month3();
		startdate = startdate+ " 00:00:00";
		//待审批//已经提交至审批节点
		
		int next = 0;
		List<DetailsEntity> upToDate = new ArrayList<DetailsEntity>();
		List<DetailsEntity> upToDate1 = indexDao.queryLogUpToDate(TABSENAMES[0], FIRM_NAME[0], FLOWIDS[0],NODES[0]+";", startdate);
		for (DetailsEntity detailsEntity : upToDate1) {
			if(detailsEntity.getId() == null) {
				continue;
			}
			detailsEntity.setItemType(FLOWNAMES[0]);
			detailsEntity.setDisposeDept("安监一处");
			next++;
			upToDate.add(detailsEntity);
		}
		List<DetailsEntity> upToDate2 = indexDao.queryLogUpToDate(TABSENAMES[2], FIRM_NAME[2], FLOWIDS[2],NODES[2]+";", startdate);
		for (DetailsEntity detailsEntity : upToDate2) {
			if(detailsEntity.getId() == null) {
				continue;
			}
			detailsEntity.setItemType(FLOWNAMES[2]);
			detailsEntity.setDisposeDept("安监一处");
			next++;
			upToDate.add(detailsEntity);
		}
		List<DetailsEntity> upToDate3 = indexDao.queryLogUpToDate(TABSENAMES[3], FIRM_NAME[3], FLOWIDS[3],NODES[3]+";", startdate);
		for (DetailsEntity detailsEntity : upToDate3) {
			if(detailsEntity.getId() == null) {
				continue;
			}
			detailsEntity.setItemType(FLOWNAMES[3]);
			detailsEntity.setDisposeDept("安监四处");
			next++;
			upToDate.add(detailsEntity);
		}
		List<DetailsEntity> upToDate4 = indexDao.queryLogUpToDate(TABSENAMES[4], FIRM_NAME[4], FLOWIDS[4],NODES[4]+";", startdate);
		for (DetailsEntity detailsEntity : upToDate4) {
			if(detailsEntity.getId() == null) {
				continue;
			}
			detailsEntity.setItemType(FLOWNAMES[4]);
			detailsEntity.setDisposeDept("安监四处");
			next++;
			upToDate.add(detailsEntity);
		}
		//next = upToDate1.size() + upToDate2.size() + upToDate3.size() + upToDate4.size();
		remap.put("count1", next);
		remap.put("list1", upToDate);
		
		//已审批的查询log表End%
		List<DetailsEntity> queryLogEnd = new ArrayList<DetailsEntity>();
		int logEnd = 0;
		List<DetailsEntity> queryLogEnd1 = indexDao.queryLogEnd(TABSENAMES[0], FIRM_NAME[0], FLOWIDS[0],NEXTNODES[0], startdate);
		for (DetailsEntity detailsEntity : queryLogEnd1) {
			detailsEntity.setItemType(FLOWNAMES[0]);
			detailsEntity.setDisposeDept("安监一处");
		}
		List<DetailsEntity> queryLogEnd2 = indexDao.queryLogEnd(TABSENAMES[2], FIRM_NAME[2], FLOWIDS[2],NEXTNODES[2], startdate);
		for (DetailsEntity detailsEntity : queryLogEnd2) {
			detailsEntity.setItemType(FLOWNAMES[2]);
			detailsEntity.setDisposeDept("安监一处");
		}
		List<DetailsEntity> queryLogEnd3 = indexDao.queryLogEnd(TABSENAMES[3], FIRM_NAME[3], FLOWIDS[3],NEXTNODES[3], startdate);
		for (DetailsEntity detailsEntity : queryLogEnd3) {
			detailsEntity.setItemType(FLOWNAMES[3]);
			detailsEntity.setDisposeDept("安监四处");
		}
		List<DetailsEntity> queryLogEnd4 = indexDao.queryLogEnd(TABSENAMES[4], FIRM_NAME[4], FLOWIDS[4],NEXTNODES[4], startdate);
		for (DetailsEntity detailsEntity : queryLogEnd4) {
			detailsEntity.setItemType(FLOWNAMES[4]);
			detailsEntity.setDisposeDept("安监四处");
		}
		logEnd = queryLogEnd1.size() + queryLogEnd2.size() + queryLogEnd3.size() + queryLogEnd4.size() ;
		queryLogEnd.addAll(queryLogEnd1);
		queryLogEnd.addAll(queryLogEnd2);
		queryLogEnd.addAll(queryLogEnd3);
		queryLogEnd.addAll(queryLogEnd4);
		remap.put("count2", logEnd);
		remap.put("list2", queryLogEnd);
		//审批中状态
		int last = 0;
		List<DetailsEntity> endLast = new ArrayList<DetailsEntity>();
		List<DetailsEntity> endLast1 = indexDao.queryLogUpToDate(TABSENAMES[0], FIRM_NAME[0], FLOWIDS[0],NODES[0]+";", startdate);
		for (DetailsEntity detailsEntity : endLast1) {
			if(detailsEntity.getId() == null) {
				continue;
			}
			detailsEntity.setItemType(FLOWNAMES[0]);
			detailsEntity.setDisposeDept("安监一处");
			last ++;
			endLast.add(detailsEntity);
		}
		List<DetailsEntity> endLast2 = indexDao.queryLogUpToDate(TABSENAMES[2], FIRM_NAME[2], FLOWIDS[2],NODES[2]+";", startdate);
		for (DetailsEntity detailsEntity : endLast2) {
			if(detailsEntity.getId() == null) {
				continue;
			}
			detailsEntity.setItemType(FLOWNAMES[2]);
			detailsEntity.setDisposeDept("安监一处");
			last ++;
			endLast.add(detailsEntity);
		}
		List<DetailsEntity> endLast3 = indexDao.queryLogUpToDate(TABSENAMES[3], FIRM_NAME[3], FLOWIDS[3],NODES[3]+";", startdate);
		for (DetailsEntity detailsEntity : endLast3) {
			if(detailsEntity.getId() == null) {
				continue;
			}
			detailsEntity.setItemType(FLOWNAMES[3]);
			detailsEntity.setDisposeDept("安监四处");
			last ++;
			endLast.add(detailsEntity);
		}
		List<DetailsEntity> endLast4 = indexDao.queryLogUpToDate(TABSENAMES[4], FIRM_NAME[4], FLOWIDS[4],NODES[4]+";", startdate);
		for (DetailsEntity detailsEntity : endLast4) {
			if(detailsEntity.getId() == null) {
				continue;
			}
			detailsEntity.setItemType(FLOWNAMES[4]);
			detailsEntity.setDisposeDept("安监四处");
			last ++;
			endLast.add(detailsEntity);
		}
		//last = endLast1.size() + endLast3.size()  + endLast4.size()+ endLast2.size();
		remap.put("count3", last);
		remap.put("list3", endLast);
		//领导退回 log 表ACTION=reject
		List<DetailsEntity> queryReject = new ArrayList<DetailsEntity>();
		List<DetailsEntity> queryReject1 = indexDao.queryReject(TABSENAMES[0], FIRM_NAME[0], FLOWIDS[0],LASTNODES[0]+";", NODES[0], startdate);
		for (DetailsEntity detailsEntity : queryReject1) {
			detailsEntity.setItemType(FLOWNAMES[0]);
			detailsEntity.setDisposeDept("安监一处");
		}
		List<DetailsEntity> queryReject2 = indexDao.queryReject(TABSENAMES[2], FIRM_NAME[2], FLOWIDS[2],LASTNODES[2]+";", NODES[2], startdate);
		for (DetailsEntity detailsEntity : queryReject2) {
			detailsEntity.setItemType(FLOWNAMES[2]);
			detailsEntity.setDisposeDept("安监一处");
		}
		List<DetailsEntity> queryReject3 = indexDao.queryReject(TABSENAMES[3], FIRM_NAME[3], FLOWIDS[3],LASTNODES[3]+";", NODES[3], startdate);
		for (DetailsEntity detailsEntity : queryReject3) {
			detailsEntity.setItemType(FLOWNAMES[3]);
			detailsEntity.setDisposeDept("安监四处");
		}
		List<DetailsEntity> queryReject4 = indexDao.queryReject(TABSENAMES[4], FIRM_NAME[4], FLOWIDS[4],LASTNODES[4]+";", NODES[4], startdate);
		for (DetailsEntity detailsEntity : queryReject4) {
			detailsEntity.setItemType(FLOWNAMES[4]);
			detailsEntity.setDisposeDept("安监四处");
		}
		int cont4 = queryReject1.size() + queryReject2.size() + queryReject3.size() + queryReject4.size();
		queryReject.addAll(queryReject1);
		queryReject.addAll(queryReject2);
		queryReject.addAll(queryReject3);
		queryReject.addAll(queryReject4);
		remap.put("count4", cont4);
		remap.put("list4", queryReject);
		
		//期限过半
		int half = 0;
		
		List<DetailsEntity> half1 = indexDao.queryLogNodeid(NODES[0]+";", TABSENAMES[0], FIRM_NAME[0], FLOWIDS[0],LASTNODES[0], startdate);
		for (DetailsEntity detailsEntity : half1) {
			detailsEntity.setItemType(FLOWNAMES[0]);
			detailsEntity.setDisposeDept("安监一处");
		}
		List<DetailsEntity> half2 = indexDao.queryLogNodeid(NODES[2]+";", TABSENAMES[2], FIRM_NAME[2], FLOWIDS[2],LASTNODES[2], startdate);
		for (DetailsEntity detailsEntity : half2) {
			detailsEntity.setItemType(FLOWNAMES[2]);
			detailsEntity.setDisposeDept("安监一处");
		}
		List<DetailsEntity> half3 = indexDao.queryLogNodeid(NODES[3]+";", TABSENAMES[3], FIRM_NAME[3], FLOWIDS[3],LASTNODES[3], startdate);
		for (DetailsEntity detailsEntity : half3) {
			detailsEntity.setItemType(FLOWNAMES[3]);
			detailsEntity.setDisposeDept("安监四处");
		}
		List<DetailsEntity> half4 = indexDao.queryLogNodeid(NODES[4]+";", TABSENAMES[4], FIRM_NAME[4], FLOWIDS[4],LASTNODES[4], startdate);
		for (DetailsEntity detailsEntity : half4) {
			detailsEntity.setItemType(FLOWNAMES[4]);
			detailsEntity.setDisposeDept("安监四处");
		}
		List<DetailsEntity> halfList = new ArrayList<DetailsEntity>();
		halfList.addAll(half1);
		halfList.addAll(half2);
		halfList.addAll(half3);
		halfList.addAll(half4);
		List<DetailsEntity> half_relist = new ArrayList<DetailsEntity>();
		for (DetailsEntity detailsEntity : halfList) {
			int limitTime = this.getLimitTime(detailsEntity.getCreateDate());
			long date = limitTime * 24 * 60 * 60 * 1000;
			String disposeTime = detailsEntity.getDisposeTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date parse = sdf.parse(disposeTime);
			long time = parse.getTime();
			long time2 = new Date().getTime();
			if(2* (time2- time) >  date) {
				half ++;
				half_relist.add(detailsEntity);
			}
		}
		List<DetailsEntity> relist = new ArrayList<DetailsEntity>();
		for (DetailsEntity detailsEntity : half_relist) {
			detailsEntity.setCreateDate(null);
			relist.add(detailsEntity);
		}
		remap.put("count5", half);
		remap.put("list5", relist);
		
		//逾期未审批      4 天 17 时 5 分   //逾期未审批查询worklist表
		@SuppressWarnings("unchecked")
		Map<String, Integer> map = (Map<String, Integer>) approvalProcessService.queryAnjianTongjiOverdueNotDispose(startdate, DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		int nots  = map.get("OverdueNotSP");
		remap.put("count6", nots);
		remap.put("list6", new ArrayList<String>());
		//逾期审批  //逾期审批查询worklist表
		@SuppressWarnings("unchecked")
		Map<String, Integer> map2 = (Map<String, Integer>) approvalProcessService.queryAnjianTongjiOverdueDispose(startdate, DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		int se= map2.get("OverdueSP");
		remap.put("count7", se);
		remap.put("list7", new ArrayList<String>());
		//无故退件
		remap.put("count8", 0);
		remap.put("list8", new ArrayList<String>());
		//资料不全
		@SuppressWarnings("unchecked")
		List<Integer> ziliao = (List<Integer>) approvalProcessService.queryAnjianTongjiZiliao(startdate, DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		remap.put("count9", ziliao.get(0));
		remap.put("list9", new ArrayList<String>());
		return remap;
	}

	//督责问责菜单下的政策法规
	public Map<String, Object> queryLawsAndRegulations(String type, int page, int size) {
		Map<String, Object> map = indexDao.queryLawsAndRegulations(type, page, size);
		return map;
	}

	public Object queryLawsAndRegulationSvague(String svague, int page, int size) {
		Map<String, Object> map = indexDao.queryLawsAndRegulationSvague(svague, page, size);
		return map;
	}
	//首页小喇叭个人待办
	public Object queryTrumpetNews(String userId, int page, int size) {
		Map<String, Object> map = indexDao.queryTrumpetNews(userId,page,size);
		return map;
	}
	private int getLimitTime(String limitTime) {
		if(!(limitTime == null || "".equals(limitTime))) {
			int indexOf = limitTime.indexOf("(");
			return Integer.parseInt(limitTime.substring(0, indexOf));
		}
		return 0;
	}
	
}
