package com.henghao.news.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.henghao.news.dao.DetailsDao;
import com.henghao.news.entity.DetailsEntity;
import com.henghao.news.util.DateUtils;

@Service
public class DetailsService {

	@Resource
	private DetailsDao detailsDao;
	private Map<String, Object> remap = new HashMap<String, Object>();
	private static final String[] FLOWIDS = {"djbf", "aqfw", "feimeikuangshan(yichu)", "sichu(chucishenqing)", "sichu(fushen)"};
	private static final String[] TABSENAMES = {"AJ_QXNFMKSAQSCXKDJBF","AJ_QXNFMKSAQSSSJSCAQFF","AJ_QXNFMKSAQSSSJSC","AJ_SC_ONE","AJ_SC_TWO"};
	private static final String[] FLOWNAMES = {"权限内非煤矿山安全生产许可登记颁发", "权限内非煤矿山安全设施设计_安全服务", "权限内非煤矿山安全设施设计审查", "安全生产标准化审批流程(初次申请)", "安全生产标准化审批流程(复审)"};
	private static final String[] FIRM_NAME = {"SQDW", "QY", "QY", "FIRMNAME", "DWMC"};
	private static final String[] DISPOSE_NODES = {"Node2", "Node6", "Node3", "Node3", "Node2"};
	private static final String[] FLOW_RATIFY = {"Node3", "0", "Node8", "Node10", "Node3"};
	private static final String[] DISPOSE_DEPT = {"安监一处", "安监一处", "政务大厅", "安监四处", "安监四处"};
	//资料不全、资料有误、重复录入详情
	public Object queryDataIncomplete(String itemType, int page, int size, String start, String end) {
		if("".equals(end) || end == null) {
			end = DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
		}
		if("".equals(start) || start == null) {
			start = DateUtils.getCurrentDate("yyyy")+"-01-01 00:00:00";
		}
		List<DetailsEntity> list1 = detailsDao.queryDataIncomplete(TABSENAMES[0], FIRM_NAME[0], DISPOSE_NODES[0], itemType, start, end);
		for (DetailsEntity detailsEntity : list1) {
			detailsEntity.setErrorType(itemType);
			detailsEntity.setItemType(FLOWNAMES[0]);
			detailsEntity.setDisposeDept(DISPOSE_DEPT[0]);
		}
		List<DetailsEntity> list2 = detailsDao.queryDataIncomplete(TABSENAMES[1], FIRM_NAME[1], DISPOSE_NODES[1], itemType, start, end);
		for (DetailsEntity detailsEntity : list2) {
			detailsEntity.setErrorType(itemType);
			detailsEntity.setItemType(FLOWNAMES[1]);
			detailsEntity.setDisposeDept(DISPOSE_DEPT[1]);
		}
		List<DetailsEntity> list3 = detailsDao.queryDataIncomplete(TABSENAMES[2], FIRM_NAME[2], DISPOSE_NODES[2], itemType, start, end);
		for (DetailsEntity detailsEntity : list3) {
			detailsEntity.setErrorType(itemType);
			detailsEntity.setItemType(FLOWNAMES[2]);
			detailsEntity.setDisposeDept(DISPOSE_DEPT[2]);
		}
		List<DetailsEntity> list4 = detailsDao.queryDataIncomplete(TABSENAMES[3], FIRM_NAME[3], DISPOSE_NODES[3], itemType, start, end);
		for (DetailsEntity detailsEntity : list4) {
			detailsEntity.setErrorType(itemType);
			detailsEntity.setItemType(FLOWNAMES[3]);
			detailsEntity.setDisposeDept(DISPOSE_DEPT[3]);
		}
		List<DetailsEntity> list5 = detailsDao.queryDataIncomplete(TABSENAMES[4], FIRM_NAME[4], DISPOSE_NODES[4], itemType, start, end);
		for (DetailsEntity detailsEntity : list5) {
			detailsEntity.setErrorType(itemType);
			detailsEntity.setItemType(FLOWNAMES[4]);
			detailsEntity.setDisposeDept(DISPOSE_DEPT[4]);
		}
		list1.addAll(list2);
		list1.addAll(list3);
		list1.addAll(list4);
		list1.addAll(list5);
		int total = list1.size();
		if((page-1) * size >= total) {
			page = page - 1;
		}
		if(size > total) {
			size = total;
		}
		int max = page * size;
		if(max > total) {
			max = total;
		}
		List<DetailsEntity> relist = list1.subList((page-1) * size, max);
		remap.clear();
		remap.put("total", total);
		remap.put("list", relist);
		return remap;
	}
	//逾期审核、逾期审批
	public Object queryDataOverdue(String overdueType, int page, int size, String start, String end) throws ParseException {
		
		if("".equals(end) || end == null) {
			end = DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
		}
		if("".equals(start) || start == null) {
			start = DateUtils.getCurrentDate("yyyy")+"-01-01 00:00:00";
		}
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
		
		List<DetailsEntity> list = new ArrayList<DetailsEntity>();
		if("逾期审核".equals(overdueType)) {
			List<DetailsEntity> list1 = detailsDao.queryAnjianTongjiOverdueDispose(TABSENAMES[0], FIRM_NAME[0], FLOWIDS[0], FLOWID1sh,  start, end);
			for (DetailsEntity detailsEntity : list1) {
				detailsEntity.setErrorType("逾期审核");
				detailsEntity.setDisposeDept("安监一处");
				detailsEntity.setItemType(TABSENAMES[0]);
			}
			List<DetailsEntity> list2 = detailsDao.queryAnjianTongjiOverdueDispose(TABSENAMES[1], FIRM_NAME[1], FLOWIDS[1], FLOWID2sh,  start, end);
			for (DetailsEntity detailsEntity : list2) {
				detailsEntity.setErrorType("逾期审核");
				detailsEntity.setDisposeDept("安监一处");
				detailsEntity.setItemType(TABSENAMES[1]);
			}
			List<DetailsEntity> list3 = detailsDao.queryAnjianTongjiOverdueDispose(TABSENAMES[2], FIRM_NAME[2], FLOWIDS[2], FLOWID3sh,  start, end);
			for (DetailsEntity detailsEntity : list3) {
				detailsEntity.setErrorType("逾期审核");
				detailsEntity.setDisposeDept("安监一处");
				detailsEntity.setItemType(TABSENAMES[2]);
			}
			List<DetailsEntity> list4 = detailsDao.queryAnjianTongjiOverdueDispose(TABSENAMES[3], FIRM_NAME[3], FLOWIDS[3], FLOWID4sh,  start, end);
			for (DetailsEntity detailsEntity : list4) {
				detailsEntity.setErrorType("逾期审核");
				detailsEntity.setDisposeDept("安监四处");
				detailsEntity.setItemType(TABSENAMES[3]);
			}
			List<DetailsEntity> list5 = detailsDao.queryAnjianTongjiOverdueDispose(TABSENAMES[4], FIRM_NAME[4], FLOWIDS[4], FLOWID5sh,  start, end);
			for (DetailsEntity detailsEntity : list5) {
				detailsEntity.setErrorType("逾期审核");
				detailsEntity.setDisposeDept("安监四处");
				detailsEntity.setItemType(TABSENAMES[4]);
			}
			list.addAll(list1);
			list.addAll(list2);
			list.addAll(list3);
			list.addAll(list4);
			list.addAll(list5);
		}
		if("逾期审批".equals(overdueType)) {
			List<DetailsEntity> list1 = detailsDao.queryAnjianTongjiOverdueDispose(TABSENAMES[0], FIRM_NAME[0], FLOWIDS[0], FLOWID1sp,  start, end);
			for (DetailsEntity detailsEntity : list1) {
				detailsEntity.setErrorType("逾期审批");
				detailsEntity.setDisposeDept("安监一处");
				detailsEntity.setItemType(TABSENAMES[0]);
			}
			List<DetailsEntity> list2 = detailsDao.queryAnjianTongjiOverdueDispose(TABSENAMES[1], FIRM_NAME[1], FLOWIDS[1], FLOWID2sp,  start, end);
			for (DetailsEntity detailsEntity : list2) {
				detailsEntity.setErrorType("逾期审批");
				detailsEntity.setDisposeDept("安监一处");
				detailsEntity.setItemType(TABSENAMES[1]);
			}
			List<DetailsEntity> list3 = detailsDao.queryAnjianTongjiOverdueDispose(TABSENAMES[2], FIRM_NAME[2], FLOWIDS[2], FLOWID3sp,  start, end);
			for (DetailsEntity detailsEntity : list3) {
				detailsEntity.setErrorType("逾期审批");
				detailsEntity.setDisposeDept("安监一处");
				detailsEntity.setItemType(TABSENAMES[2]);
			}
			List<DetailsEntity> list4 = detailsDao.queryAnjianTongjiOverdueDispose(TABSENAMES[3], FIRM_NAME[3], FLOWIDS[3], FLOWID4sp,  start, end);
			for (DetailsEntity detailsEntity : list4) {
				detailsEntity.setErrorType("逾期审批");
				detailsEntity.setDisposeDept("安监四处");
				detailsEntity.setItemType(TABSENAMES[3]);
			}
			List<DetailsEntity> list5 = detailsDao.queryAnjianTongjiOverdueDispose(TABSENAMES[4], FIRM_NAME[4], FLOWIDS[4], FLOWID5sp,  start, end);
			for (DetailsEntity detailsEntity : list5) {
				detailsEntity.setErrorType("逾期审批");
				detailsEntity.setDisposeDept("安监四处");
				detailsEntity.setItemType(TABSENAMES[4]);
			}
			list.addAll(list1);
			list.addAll(list2);
			list.addAll(list3);
			list.addAll(list4);
			list.addAll(list5);
		}
		int total = list.size();
		if((page-1) * size >= total) {
			page = page - 1;
		}
		if(size > total) {
			size = total;
		}
		int max = page * size;
		if(max > total) {
			max = total;
		}
		List<DetailsEntity> relist = list.subList((page-1) * size, max);
		remap.clear();
		remap.put("total", total);
		remap.put("list", relist);
		return remap;
	}
	//逾期未审核、逾期未审批
	public Object queryDataOverdueUnfinished(String overdueType, int page, int size, String start, String end) {
		if("".equals(end) || end == null) {
			end = DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
		}
		if("".equals(start) || start == null) {
			start = DateUtils.getCurrentDate("yyyy")+"-01-01 00:00:00";
		}
		List<DetailsEntity> list = detailsDao.queryDataOverdueUnfinished(FLOWNAMES, TABSENAMES, FIRM_NAME, FLOWIDS, overdueType, start, end);
		int total = list.size();
		if((page-1) * size >= total) {
			page = page - 1;
		}
		if(size > total) {
			size = total;
		}
		int max = page * size;
		if(max > total) {
			max = total;
		}
		List<DetailsEntity> relist = list.subList((page-1) * size, max);
		remap.clear();
		remap.put("total", total);
		remap.put("list", relist);
		return remap;
	}
	//安全服务页面下已受理详情
	public Object queryDataYetTransactionList(String type, int page, int size, String start, String end) {
		List<DetailsEntity> list = new ArrayList<DetailsEntity>();
		List<DetailsEntity> list1 = detailsDao.queryDataYetTransactionList(TABSENAMES[0], FIRM_NAME[0], FLOWIDS[0], type, "Node2", "End5;", start, end);
		for (DetailsEntity detailsEntity : list1) {
			//TODO
			list.add(detailsEntity);
		}
		List<DetailsEntity> list2 = detailsDao.queryDataYetTransactionList(TABSENAMES[1], FIRM_NAME[1], FLOWIDS[1], type, "Node6", "End7;", start, end);
		for (DetailsEntity detailsEntity : list2) {
			//TODO
			list.add(detailsEntity);
		}
		List<DetailsEntity> list3 = detailsDao.queryDataYetTransactionList(TABSENAMES[2], FIRM_NAME[2], FLOWIDS[2], type, "Node3", "End11;", start, end);
		for (DetailsEntity detailsEntity : list3) {
			//TODO
			list.add(detailsEntity);
		}
		List<DetailsEntity> list4 = detailsDao.queryDataYetTransactionList(TABSENAMES[3], FIRM_NAME[3], FLOWIDS[3], type, "Node3", "End12;", start, end);
		for (DetailsEntity detailsEntity : list4) {
			
			list.add(detailsEntity);
		}
		List<DetailsEntity> list5 = detailsDao.queryDataYetTransactionList(TABSENAMES[4], FIRM_NAME[4], FLOWIDS[4], type, "Node2", "End6;",start, end);
		for (DetailsEntity detailsEntity : list5) {
			
			list.add(detailsEntity);
		}
		int total = list.size();
		if((page-1) * size >= total) {
			page = page - 1;
		}
		if(size > total) {
			size = total;
		}
		int max = page * size;
		if(max > total) {
			max = total;
		}
		List<DetailsEntity> relist = list.subList((page-1) * size, max);
		remap.clear();
		remap.put("total", total);
		remap.put("list", relist);
		return remap;
	}
	
	/**
	 * 审核、审批办理页面上部分按钮详情数据
	 * @param type
	 * @param idLists
	 * @return
	 */
	public Object queryManageListData(String[] idLists, int page, int size) {
		List<DetailsEntity> list = new ArrayList<DetailsEntity>();
		for (String dataId : idLists) {
			Object obj = detailsDao.queryTableName(dataId);
			if(obj != null) {
				Object[] objects = (Object[]) obj;
				String workid = objects[0].toString();
				//获取日志最新数据
				DetailsEntity log = detailsDao.queryNewsLogFlow(workid);
				//获取表面查询实例
				String tableName = objects[1].toString();
				for(int i=0; i<TABSENAMES.length; i++) {
					if(TABSENAMES[i].equals(tableName)) {
						//查询实例数据
						DetailsEntity detailsEntity = detailsDao.queryDBFlowEntity(dataId, tableName, FIRM_NAME[i]);
						if(log != null) {
							detailsEntity.setDisposeUserName(log.getDisposeUserName());
							detailsEntity.setDisposeTime(log.getDisposeTime());
						}
						switch (i) {
						case 0:
							detailsEntity.setDisposeDept(DISPOSE_DEPT[0]);
							detailsEntity.setItemType(FLOWNAMES[0]);
							break;
						case 1:
							detailsEntity.setDisposeDept(DISPOSE_DEPT[1]);
							detailsEntity.setItemType(FLOWNAMES[1]);
							break;
						case 2:
							detailsEntity.setDisposeDept(DISPOSE_DEPT[2]);
							detailsEntity.setItemType(FLOWNAMES[2]);
							break;
						case 3:
							detailsEntity.setDisposeDept(DISPOSE_DEPT[3]);
							detailsEntity.setItemType(FLOWNAMES[3]);
							break;
						case 4:
							detailsEntity.setDisposeDept(DISPOSE_DEPT[4]);
							detailsEntity.setItemType(FLOWNAMES[4]);
							break;
						default:
							break;
						}
						list.add(detailsEntity);
					}
				}
			}
		}
		int total = list.size();
		if((page-1) * size >= total) {
			page = page - 1;
		}
		if(size > total) {
			size = total;
		}
		int max = page * size;
		if(max > total) {
			max = total;
		}
		List<DetailsEntity> relist = list.subList((page-1) * size, max);
		remap.clear();
		remap.put("total", total);
		remap.put("list", relist);
		return remap;
	}
	
	//审核办理审批办理资料不全、资料有误、重复录入详情
	public Object queryDataIncompleteList(String type, String[] idLists, int page, int size) {
		//审核节点 DISPOSE_NODES   审批节点 FLOW_RATIFY
		List<DetailsEntity> list = new ArrayList<DetailsEntity>();
		if("审核".equals(type)) {
			for (String dataId : idLists) {
				//获取流程id对应的表和workid
				Object obj = detailsDao.queryTableName(dataId);
				if(obj != null) {
					Object[] objects = (Object[]) obj;
					String workid = objects[0].toString();
					//获取节点数据
					String tableName = objects[1].toString();
					for(int i=0; i<TABSENAMES.length; i++) {
						if(TABSENAMES[i].equals(tableName)) {
							//获取流程信息
							DetailsEntity detailsEntity = detailsDao.queryDBFlowEntity(dataId, tableName, FIRM_NAME[i]);
							//获取日志表节点办理人信息
							DetailsEntity log = detailsDao.queryNodeLogFlow(DISPOSE_NODES[i], workid);
							if(log != null) {
								detailsEntity.setDisposeUserName(log.getDisposeUserName());
								detailsEntity.setDisposeTime(log.getDisposeTime());
							}
							switch (i) {
							case 0:
								detailsEntity.setDisposeDept(DISPOSE_DEPT[0]);
								detailsEntity.setItemType(FLOWNAMES[0]);
								break;
							case 1:
								detailsEntity.setDisposeDept(DISPOSE_DEPT[1]);
								detailsEntity.setItemType(FLOWNAMES[1]);
								break;
							case 2:
								detailsEntity.setDisposeDept(DISPOSE_DEPT[2]);
								detailsEntity.setItemType(FLOWNAMES[2]);
								break;
							case 3:
								detailsEntity.setDisposeDept(DISPOSE_DEPT[3]);
								detailsEntity.setItemType(FLOWNAMES[3]);
								break;
							case 4:
								detailsEntity.setDisposeDept(DISPOSE_DEPT[4]);
								detailsEntity.setItemType(FLOWNAMES[4]);
								break;
							default:
								break;
							}
							list.add(detailsEntity);
						}
					}
				}
				
			}
		}
		if("审批".equals(type)) {
			for (String dataId : idLists) {
				//获取流程id对应表名和workid
				Object obj = detailsDao.queryTableName(dataId);
				if(obj != null) {
					Object[] objects = (Object[]) obj;
					String workid = objects[0].toString();
					//获取节点数据
					String tableName = objects[1].toString();
					for(int i=0; i<TABSENAMES.length; i++) {
						if(i == 1) {
							//流程无审批
							continue;
						}
						if(TABSENAMES[i].equals(tableName)) {
							//获取流程信息
							DetailsEntity detailsEntity = detailsDao.queryDBFlowEntity(dataId, tableName, FIRM_NAME[i]);
							//获取日志表节点办理人信息
							DetailsEntity log = detailsDao.queryNodeLogFlow(FLOW_RATIFY[i], workid);
							if(log != null) {
								detailsEntity.setDisposeUserName(log.getDisposeUserName());
								detailsEntity.setDisposeTime(log.getDisposeTime());
							}
							switch (i) {
							case 0:
								detailsEntity.setDisposeDept(DISPOSE_DEPT[0]);
								detailsEntity.setItemType(FLOWNAMES[0]);
								break;
							case 1:
								detailsEntity.setDisposeDept(DISPOSE_DEPT[1]);
								detailsEntity.setItemType(FLOWNAMES[1]);
								break;
							case 2:
								detailsEntity.setDisposeDept(DISPOSE_DEPT[2]);
								detailsEntity.setItemType(FLOWNAMES[2]);
								break;
							case 3:
								detailsEntity.setDisposeDept(DISPOSE_DEPT[3]);
								detailsEntity.setItemType(FLOWNAMES[3]);
								break;
							case 4:
								detailsEntity.setDisposeDept(DISPOSE_DEPT[4]);
								detailsEntity.setItemType(FLOWNAMES[4]);
								break;
							default:
								break;
							}
							list.add(detailsEntity);
						}
					}
				}
			}
		}
		int total = list.size();
		if((page-1) * size >= total) {
			page = page - 1;
		}
		if(size > total) {
			size = total;
		}
		int max = page * size;
		if(max > total) {
			max = total;
		}
		List<DetailsEntity> relist = list.subList((page-1) * size, max);
		remap.clear();
		remap.put("total", total);
		remap.put("list", relist);
		return remap;
	}
	//安全服务页面按钮详细数据
	public Object querySafetyDataList(String[] idLists, int page, int size) {
		List<DetailsEntity> list = new ArrayList<DetailsEntity>();
		for (String dataId : idLists) {
			//获取流程id对应表名和workid
			Object obj = detailsDao.queryTableName(dataId);
			if(obj != null) {
				Object[] objects = (Object[]) obj;
				String workid = objects[0].toString();
				//获取节点数据
				String tableName = objects[1].toString();
				for(int i=0; i<TABSENAMES.length; i++) {
					if(TABSENAMES[i].equals(tableName)) {
						//获取流程信息
						DetailsEntity detailsEntity = detailsDao.queryDBFlowEntity(dataId, tableName, FIRM_NAME[i]);
						//获取日志表节点办理人信息
						DetailsEntity log = detailsDao.queryNodeLogFlow(DISPOSE_NODES[i], workid);
						if(log != null) {
							detailsEntity.setDisposeUserName(log.getDisposeUserName());
							detailsEntity.setDisposeTime(log.getDisposeTime());
						}
						switch (i) {
						case 0:
							detailsEntity.setDisposeDept(DISPOSE_DEPT[0]);
							detailsEntity.setItemType(FLOWNAMES[0]);
							break;
						case 1:
							detailsEntity.setDisposeDept(DISPOSE_DEPT[1]);
							detailsEntity.setItemType(FLOWNAMES[1]);
							break;
						case 2:
							detailsEntity.setDisposeDept(DISPOSE_DEPT[2]);
							detailsEntity.setItemType(FLOWNAMES[2]);
							break;
						case 3:
							detailsEntity.setDisposeDept(DISPOSE_DEPT[3]);
							detailsEntity.setItemType(FLOWNAMES[3]);
							break;
						case 4:
							detailsEntity.setDisposeDept(DISPOSE_DEPT[4]);
							detailsEntity.setItemType(FLOWNAMES[4]);
							break;
						default:
							break;
						}
						list.add(detailsEntity);
					}
				}
			}
		}
		int total = list.size();
		if((page-1) * size >= total) {
			page = page - 1;
		}
		if(size > total) {
			size = total;
		}
		int max = page * size;
		if(max > total) {
			max = total;
		}
		List<DetailsEntity> relist = list.subList((page-1) * size, max);
		remap.clear();
		remap.put("total", total);
		remap.put("list", relist);
		return remap;
	}
	
}
