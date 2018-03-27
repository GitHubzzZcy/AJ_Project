package com.henghao.news.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.henghao.news.dao.ZfAppDao;
import com.henghao.news.entity.DocTypeDefine;
import com.henghao.news.entity.Enterprise;
import com.henghao.news.entity.JianchaMaterialEntity;
import com.henghao.news.entity.Plan;
import com.henghao.news.util.DateUtils;
import com.henghao.news.util.Urljson;
@Service
public class ZfAppService {
	@Resource
	private ZfAppDao zfAppDao;
	@Resource
	private UserDataService userDateService;
	@Resource
	private FirmdataService firmdataService;
	@Resource
	private DocTypeDefineService docTypeDefineService;
	
	@Value("${IMAGEPATH}")
	private String IMAGEPATH ;//图片地址在filepath.properties中
	
	private final static String getEnterprise = "http://127.0.0.1:8080/tongji/firmdate/findFirmById?enterpriseid=";
	
	public void savePlan(Plan plan) {
		plan.setCreatePlanTime(new Date());
		zfAppDao.add(plan);
		
	}
	public void saveOrUpdate(Plan plan) {
		zfAppDao.saveOrUpdate(plan);
	}
	//根据计划的pid查询计划详细数据
	public Plan queryPlanById(int pid) throws IOException, JSONException {
		//查询计划基本数据
		Plan plan = zfAppDao.findById(pid);
		//获取计划企业详细数据
		JSONObject jsonObject = Urljson.readJsonFromUrl(getEnterprise + plan.getEnterpriseid());
		if(0 == Integer.parseInt(jsonObject.get("status").toString())) {
			Enterprise enterprise = JSON.parseObject(jsonObject.get("data").toString(), Enterprise.class);
			plan.setEnterprise(enterprise);
		}
		//获取计划检查人员2的详细数据
		plan.setTroopemp(firmdataService.queryTroopByUserName(plan.getCheckPeople2()));
		//查询文书定义表，获取该执法计划下的所有文书信息列表
		Set<DocTypeDefine> typeDefineByPid = docTypeDefineService.queryDocTypeDefineByPid(pid);
		plan.setDocTypeDefineList(typeDefineByPid);
		return plan;
	}
	
	//根据用户id和执法结果状态查询用户计划列表
	public List<Plan> queryPlanByUser(String userid, int resultStatus) throws IOException, JSONException {
		List<Plan> relist = new ArrayList<Plan>();
		String userName = userDateService.getUserName(userid);
		List<Integer> ids = zfAppDao.queryUserPlanIds(userName, resultStatus);
		for (Integer integer : ids) {
			relist.add(this.queryPlanById(integer));
		}
		return relist;
	}
	//我要检查，添加检查基本数据
	public void updatePlanExamineDate(Plan plan, MultipartFile file) throws Exception {
		File files = new File(IMAGEPATH);
		if(!files.exists()) {
			files.mkdirs();
		}
		String path = null;
		String dbimagename = null;
		if(file != null) {
			String filename = file.getOriginalFilename();
			filename = filename.substring(filename.lastIndexOf("."));
			path = files +"/"+ DateUtils.getCurrentDate("yyyyMMdd-HHmmss")+filename;
			dbimagename = DateUtils.getCurrentDate("yyyyMMdd-HHmmss")+filename;
			File imgepath = new File(path);
			file.transferTo(imgepath);
		}
		Plan findById = zfAppDao.findById(plan.getPid());
		findById.setCheckSite(plan.getCheckSite());
		findById.setSiteResponse(plan.getSiteResponse());
		findById.setSiteImagePath(dbimagename);
		findById.setFinishTime(new Date());
		zfAppDao.update(findById);
	}
	//保存检查项的检查内容
	public void updateCheckContent(int pid, JianchaMaterialEntity gson, MultipartFile[] files) throws IllegalStateException, IOException {
		File file = new File(IMAGEPATH);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		Plan findById = zfAppDao.findById(pid);
		Set<JianchaMaterialEntity> list = findById.getJianchaMaterialEntityList();
		for (JianchaMaterialEntity jme : list) {
			if(gson.getCid() == jme.getCid()) {
				String path = null;
				String dbimagename = null;
				StringBuffer sb = new StringBuffer();
				if(files != null) {
					int i = 0;
					for (MultipartFile imge : files) {
						String filename = imge.getOriginalFilename();
						filename = filename.substring(filename.lastIndexOf("."));
						path = file +"/"+ DateUtils.getCurrentDate("yyyyMMdd-HHmmss")+i+filename;
						dbimagename = DateUtils.getCurrentDate("yyyyMMdd-HHmmss")+i+filename;
						File imgepath = new File(path);
							imge.transferTo(imgepath);
							sb.append(dbimagename + ",");
							i++;
					}
				}
				jme.setCheckDegree(gson.getCheckDegree());
				jme.setCheckDescript(gson.getCheckDescript());
				jme.setCheckPosition(gson.getCheckPosition());
				jme.setFindTime(gson.getFindTime());
				jme.setStatus(1);
				if(sb.length()>0) {
					String paths = sb.substring(0, sb.length()-1);
					jme.setSelectImagePath(paths);
				}
			}
			findById.setJianchaMaterialEntityList(list);
		}
		zfAppDao.update(findById);
		
	}
	//删除已近添加的隐患项
	public void deleteCheckContent(int cid) {
		zfAppDao.deleteCheckContent(cid);
		
	}
	//根据计划Pid查询有隐患的检查项
	public List<JianchaMaterialEntity> queryJianchaByStatus(int pid) {
		List<JianchaMaterialEntity> list = zfAppDao.queryJianchaByStatus(pid);
		return list;
	}
	
	//检查结果处理状态修改 WritDataService责令整改文书调用
	public void updatePlanResultStatus(int pid, int resultStatus) {
		Plan findById = zfAppDao.findById(pid);
		findById.setResultStatus(resultStatus);
		zfAppDao.update(findById);
	}



}
