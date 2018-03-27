package com.henghao.news.service;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.json.JSONException;
import org.springframework.stereotype.Service;

import com.henghao.news.dao.CkInspectrecordDao;
import com.henghao.news.dao.OrderChangeEntityDao;
import com.henghao.news.dao.WritDataDao;
import com.henghao.news.entity.CkInspectrecord;
import com.henghao.news.entity.DocTypeDefine;
import com.henghao.news.entity.ForceMeasuresEntity;
import com.henghao.news.entity.JianchaMaterialEntity;
import com.henghao.news.entity.OrderChangeEntity;
import com.henghao.news.entity.Plan;
import com.henghao.news.dao.ForceMeasuresDao;

@Service
public class WritDataService {
	
	@Resource
	private WritDataDao writDataDao;
	@Resource
	private ZfAppService zfAppService;
	@Resource
	private CkInspectrecordDao ckInspectrecordDao;
	@Resource
	private DocTypeDefineService docTypeDefineService;
	@Resource
	private OrderChangeEntityDao orderChangeEntityDao;
	@Resource
	private ForceMeasuresDao forceMeasuresDao;
	
	
	//查询文书内容
	public Object queryWritData(int dtid) throws ClassNotFoundException {
		DocTypeDefine docTypeDefine = docTypeDefineService.queryDocTypeDefineByDtid(dtid);
		//获取文书对应实体类
		String entityClass = docTypeDefine.getEntityClass();
		//获取文书对应的表名
		String dataTableName = docTypeDefine.getDataTableName();
		int wtId = docTypeDefine.getWtid();
		Object object = writDataDao.queryWritData(wtId, entityClass, dataTableName);
		return object;
	}
	
	//新增现场检查记录文书
	public Object addCkInspectrecord(CkInspectrecord gson, int pid, String htmlUrl) throws IOException, JSONException {
		Plan plan = zfAppService.queryPlanById(pid);
		//持久数据
		Set<JianchaMaterialEntity> set = plan.getJianchaMaterialEntityList();
		//新提交数据
		Set<JianchaMaterialEntity> gsons = gson.getCheckYinhuanList();
		Set<JianchaMaterialEntity> addset = new LinkedHashSet<JianchaMaterialEntity>();
		for (JianchaMaterialEntity jme : set) {
			for (JianchaMaterialEntity gsonset : gsons) {
				if(jme.getCid() == gsonset.getCid()) {
					jme.setCheckPosition(gsonset.getCheckPosition());
					jme.setCheckDescript(gsonset.getCheckDescript());
					addset.add(jme);
				}
			}
		}
		plan.setJianchaMaterialEntityList(addset);
		zfAppService.saveOrUpdate(plan);
		ckInspectrecordDao.add(gson);
		
		//保存文书基本信息到文书定义表
		DocTypeDefine dtd = new DocTypeDefine();
		dtd.setPid(pid);
		dtd.setWtid(gson.getWtid());
		dtd.setDataTableName("aj_writ_inspectrecord");
		dtd.setDocTypeName("现场检查记录文书");
		dtd.setEntityClass("CkInspectrecord");
		dtd.setHtmlUrl(htmlUrl);
		docTypeDefineService.add(dtd);
		 
		return gson.getWtid();
	}
	//添加责令整改文书
	public Object addOrderChangeEntity(OrderChangeEntity gson,int pid, String htmlUrl, int resultStatus)  {
		orderChangeEntityDao.add(gson);
		DocTypeDefine dtd = new DocTypeDefine();
		dtd.setPid(pid);
		dtd.setWtid(gson.getWtid());
		dtd.setDataTableName("aj_writ_orderchange");
		dtd.setDocTypeName("责令限期整改指令书");
		dtd.setEntityClass("OrderChangeEntity");
		dtd.setHtmlUrl(htmlUrl);
		docTypeDefineService.add(dtd);
		zfAppService.updatePlanResultStatus(pid, resultStatus);
		return gson.getWtid();
	}

	//添加强制措施决定文书
	public Object addForceMeasures(ForceMeasuresEntity gson, int pid,
			String htmlUrl, int resultStatus) {
		forceMeasuresDao.add(gson);
		DocTypeDefine dtd = new DocTypeDefine();
		dtd.setPid(pid);
		dtd.setWtid(gson.getWtid());
		dtd.setDataTableName("aj_writ_forcemeasures");
		dtd.setDocTypeName("强制措施决定文书");
		dtd.setEntityClass("ForceMeasuresEntity");
		dtd.setHtmlUrl(htmlUrl);
		docTypeDefineService.add(dtd);
		zfAppService.updatePlanResultStatus(pid, resultStatus);
		return gson.getWtid();
	}


	

}
