package com.henghao.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.henghao.dao.IWZPUserDao;
import com.henghao.entity.wzp.ToHorizonUserEntity;
import com.henghao.vo.ComplaintVo;
import org.apache.commons.lang.StringEscapeUtils;
import org.henghao.utils.PageBaen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.henghao.dao.ComplaintHandlingDao;
import com.henghao.dao.ReportInformationDao;
import com.henghao.dao.ReportInformationFileDao;
import com.henghao.entity.ComplaintHandling;
import com.henghao.entity.ReportInformation;
import com.henghao.entity.ReportInformationFile;
import com.henghao.entity.Result;
import com.henghao.service.ReportInformationService;
import com.henghao.util.DateUtils;
import com.henghao.util.Md5Test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("ReportInformationService")
@SuppressWarnings("all")
public class ReportInformationServiceimpl implements ReportInformationService {
	@Autowired
	private ReportInformationDao reportInformationDao;
	@Autowired
	private ReportInformationFileDao reportInformationFileDao;
	@Autowired
	private ComplaintHandlingDao complaintHandlingDao;

	// dao接口,因为继承了baseDao，所以只用这个dao来执行baseDao里面的通用方法
	@Resource
	private IWZPUserDao<ToHorizonUserEntity> userDao;

	/**
	 * 保存投诉信息
	 */
	@Override
	public Result addReportInformation(ReportInformation reportInformation, MultipartFile file,
			HttpServletRequest request, ModelMap model) {
		// 生成随机的18位编码
		String words = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String token = "";
		for (int i = 0; i < 18; i++) {
			int rand = (int) (Math.random() * words.length());
			token += words.charAt(rand);
		}
		String tokens = Md5Test.toMD5(token);
		String code = token.substring(0, 7);
		// 得到long类型当前时间
		long l = System.currentTimeMillis();
		// new日期对象
		Date date = new Date(l);
		// 获取当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(date);// 当前时间
		reportInformation.setToken(tokens);
		reportInformation.setCode(code);
		reportInformation.setTime(time);
		reportInformationDao.add(reportInformation);
		ComplaintHandling complaintHandling = new ComplaintHandling();
		complaintHandling.setCode(code);
		complaintHandlingDao.add(complaintHandling);
		// System.out.println(tokens);
		Result result = new Result();
		ReportInformationFile report = new ReportInformationFile();
		// MultipartFile fileNAme = file.get(i);
		// System.out.println("开始上传文件");
		// 获取tomcat保存文件地址
		String path = request.getSession().getServletContext().getRealPath("/") + "tousu";
		if (!new File(path).exists()) {
			new File(path).mkdirs();
		}
		//System.out.println(path);
		// 获取文件名称
		String fileName = file.getOriginalFilename();
		// System.out.println(fileName);
		// 获取文件类型
		String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
		// 文件重命名
		String file_ture_name = DateUtils.getCurrentDate("yyyyMMddHHmmssms" + ".") + extensionName;
		// 路径分隔符
		String separator = File.separator;
		// 文件路径名
		String pathFileName = path + separator + fileName;
		// System.out.println(pathFileName);
		// FileInputStream in = new FileInputStream(fileNAme);
		File targetFile = new File(path, file_ture_name);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			// 保存图片到物理磁盘
			file.transferTo(targetFile);
			// 保存数据库
			report.setFILENAME(file_ture_name);
			report.setFILETYPE(extensionName);
			report.setSAVE_TIME(time);
			report.setSAVE_NAME(pathFileName);
			report.setTOKEN(tokens);
			reportInformationFileDao.add(report);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("fileUrl", request.getContextPath() + "/tousu/" + fileName);
		model.addAttribute("downFileUrl", path + "/" + fileName);
		String msg = "";
		if (reportInformation.equals(null)) {
			msg = "保存失败";
		} else {
			msg = "保存成功";
		}
		result.setMsg(msg);
		result.setData(reportInformation);
		return result;
	}

	/**
	 * 查询投诉信息
	 * @update: 2017年12月4日17:13:46做出修改：
	 * 原来是根据电话查询，现在改为根据被投诉人员的姓名查询
	 * by 王章鹏 wzp
	 */
	@Override
	@SuppressWarnings("all")
	public Result findReportByName(String name, com.henghao.util.PageBaen pageBaen) {

		/*
		 * 修改 - Start
		 */
		// 分页参数
		Integer page = pageBaen.getCurrentPage();	// 当前页
		Integer pageSize = pageBaen.getPageSize();	// size
		// name为空时，直接返回，不查询
		if (name == null || "".equals(name) || page == null || pageSize == null)
			return new Result(1,"查询名字或分页参数为空",null);
		// 防止SQL注入
		String eName = StringEscapeUtils.escapeSql(name);
		try {
			// 分页设置
			// 因为初始页（第一页）page为1，所以这里需要处理一下
			page = page == 1?0:page;
			// 数据库分页LIMIT start,size
			int start = page * pageSize;
			// 查询总条数
			String sql_count = "SELECT COUNT(*)\n " +
					"FROM report_information r,complaint_handling c \n " +
					"WHERE c.`code`= r.`code` \n " +
					"AND r.informantsName='" + eName + "'";

			Integer total = userDao.getCount(sql_count);
			// 将总条数设置进分页工具类中
			pageBaen.setTotal(total);

			// 根据被举报人的名字，分页查询举报信息
			String sql = "SELECT r.`code`,r.informantsName,r.problemCategories,\n" +
					"r.time,r.content,c.examinationResult,c.examinationName \n" +
					"FROM report_information r,complaint_handling c \n" +
					"WHERE c.`code`= r.`code` \n" +
					"AND r.informantsName='" + eName + "' LIMIT "+start+","+pageSize;
			// 返回集合
			List<ComplaintVo> voList = userDao.getVoList(sql, ComplaintVo.class);
			pageBaen.setList(voList);
			return new Result(0,"查询成功",pageBaen);
		}catch (Exception e){
			e.printStackTrace();
		}
		return new Result(1,"查询失败,分页参数错误",null);
		/*
		 * 修改 - End
		 */

		/*
		 * 以下为龙宏之前写的代码
		 *
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONArray jsonArrays = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObjects = new JSONObject();
		List<?> list = new ArrayList<>();
		if (name == null || name =="") {
			list = reportInformationDao.findReportInformation();
		}else {
			list = reportInformationDao.findReportInformation(phone);
		}
		int size = list.size();
		PageBaen pagebean = new PageBaen(size);// 初始化PageBean对象
		// 设置当前页
		pagebean.setCurPage(page); // 这里page是从页面上获取的一个参数，代表页数
		pagebean.setPageSize(pageSize);
		pagebean.setRowsCount(list.size());
		// 获得分页大小
		int pagesize = pagebean.getPageSize();
		// 获得分页数据在list集合中的索引
		int firstIndex = (page - 1) * pagesize;
		int toIndex = page * pagesize;
		if (toIndex > list.size()) {
			toIndex = list.size();
		}
		if (firstIndex > toIndex) {
			firstIndex = 0;
			pagebean.setCurPage(1);
		}
		// 截取数据集合，获得分页数据
		List<?> courseList = list.subList(firstIndex, toIndex);
		List<JSONArray> list2 = new ArrayList<JSONArray>();
		for (Object object : courseList) {
			Object[] objects = (Object[]) object;
			jsonObject.put("code", objects[0] == null ? "" : String.valueOf(objects[0]));
			jsonObject.put("name", objects[1] == null ? "" : String.valueOf(objects[1]));
			jsonObject.put("type", objects[2] == null ? "" : String.valueOf(objects[2]));
			jsonObject.put("time", objects[3] == null ? "" : String.valueOf(objects[3]));
			jsonObject.put("problem", objects[4] == null ? "" : String.valueOf(objects[4]));
			jsonObject.put("result", objects[5] == null ? "" : String.valueOf(objects[5]));
			jsonObject.put("examinationName", objects[6] == null ? "" : String.valueOf(objects[6]));
			jsonArrays.add(jsonObject);
		}
		list2.add(jsonArrays);
		jsonObjects.put("total", list.size());
		jsonObjects.put("list", list2);
		jsonArray.add(jsonObjects);
		String msg = "";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg = "查询无数据";
		} else {
			msg = "查询成功";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
		*/
	}

	/**
	 * 问责信息查询
	 */
	@Override
	public Result findAccountabilityInfo(String date1, String date2, int page, int pageSize) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONArray jsonArrays = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObjects = new JSONObject();
		String tableName="AJ_WZ";
		List<?> list1 = reportInformationDao.findAccountAndSupervi(tableName);
		List<?> list = reportInformationDao.findAccountabilityInfo(date1, date2);
		int size = list.size();
		PageBaen pagebean = new PageBaen(size);// 初始化PageBean对象
		// 设置当前页
		pagebean.setCurPage(page); // 这里page是从页面上获取的一个参数，代表页数
		pagebean.setPageSize(pageSize);
		pagebean.setRowsCount(list.size());
		// 获得分页大小
		int pagesize = pagebean.getPageSize();
		// 获得分页数据在list集合中的索引
		int firstIndex = (page - 1) * pagesize;
		int toIndex = page * pagesize;
		if (toIndex > list.size()) {
			toIndex = list.size();
		}
		if (firstIndex > toIndex) {
			firstIndex = 0;
			pagebean.setCurPage(1);
		}
		// 截取数据集合，获得分页数据
		List<?> courseList = list.subList(firstIndex, toIndex);
		List<JSONArray> list2 = new ArrayList<JSONArray>();
		for (Object object : courseList) {
			Object[] objects = (Object[]) object;
			jsonObject.put("id", objects[0] == null ? "" : String.valueOf(objects[0]));
			jsonObject.put("time", objects[1] == null ? "" : String.valueOf(objects[1]));
			jsonObject.put("mechanism", objects[2] == null ? "" : String.valueOf(objects[2]));
			jsonObject.put("name", objects[3] == null ? "" : String.valueOf(objects[3]));
			jsonObject.put("reason", objects[4] == null ? "" : String.valueOf(objects[4]));
			jsonObject.put("address", objects[5] == null ? "" : String.valueOf(objects[5]));
			jsonObject.put("leader", objects[6] == null ? "" : String.valueOf(objects[6]));
			jsonObject.put("decision", objects[7] == null ? "" : String.valueOf(objects[7]));
			jsonObject.put("fromId", list1.get(0));
			jsonArrays.add(jsonObject);
		}
		list2.add(jsonArrays);
		jsonObjects.put("total", list.size());
		jsonObjects.put("list", list2);
		jsonArray.add(jsonObjects);
		String msg = "";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg = "查询无数据";
		} else {
			msg = "查询成功";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}

	/**
	 * 督责信息查询
	 */
	@Override
	public Result findSuperviseInfo(String date1, String date2, int page, int pageSize) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONArray jsonArrays = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObjects = new JSONObject();
		String tableName="AJ_DZ";
		List<?> list1 = reportInformationDao.findAccountAndSupervi(tableName);
		List<?> list = reportInformationDao.findSuperviseInfo(date1, date2);
		int size = list.size();
		PageBaen pagebean = new PageBaen(size);// 初始化PageBean对象
		// 设置当前页
		pagebean.setCurPage(page); // 这里page是从页面上获取的一个参数，代表页数
		pagebean.setPageSize(pageSize);
		pagebean.setRowsCount(list.size());
		// 获得分页大小
		int pagesize = pagebean.getPageSize();
		// 获得分页数据在list集合中的索引
		int firstIndex = (page - 1) * pagesize;
		int toIndex = page * pagesize;
		if (toIndex > list.size()) {
			toIndex = list.size();
		}
		if (firstIndex > toIndex) {
			firstIndex = 0;
			pagebean.setCurPage(1);
		}
		// 截取数据集合，获得分页数据
		List<?> courseList = list.subList(firstIndex, toIndex);
		List<JSONArray> list2 = new ArrayList<JSONArray>();
		for (Object object : courseList) {
			Object[] objects = (Object[]) object;
			jsonObject.put("id", objects[0] == null ? "" : String.valueOf(objects[0]));
			jsonObject.put("time", objects[1] == null ? "" : String.valueOf(objects[1]));
			jsonObject.put("mechanism", objects[2] == null ? "" : String.valueOf(objects[2]));
			jsonObject.put("name", objects[3] == null ? "" : String.valueOf(objects[3]));
			jsonObject.put("reason", objects[4] == null ? "" : String.valueOf(objects[4]));
			jsonObject.put("address", objects[5] == null ? "" : String.valueOf(objects[5]));
			jsonObject.put("leader", objects[6] == null ? "" : String.valueOf(objects[6]));
			jsonObject.put("decision", objects[7] == null ? "" : String.valueOf(objects[7]));
			jsonObject.put("formId", list1.get(0));
			jsonArrays.add(jsonObject);

		}
		list2.add(jsonArrays);
		jsonObjects.put("total", list.size());
		jsonObjects.put("list", list2);
		jsonArray.add(jsonObjects);
		String msg = "";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg = "查询无数据";
		} else {
			msg = "查询成功";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}

	/**
	 * 督责问责类型条数
	 */
	@Override
	public Result superviseAndAccountability(String date1, String date2) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = reportInformationDao.accountabilityAndSupervise(date1, date2);
		for (Object object : list) {
			Object[] objects = (Object[]) object;
			jsonObject.put("type", objects[0] == null ? "" : String.valueOf(objects[0]));
			jsonObject.put("num", objects[1] == null ? "" : String.valueOf(objects[1]));
			jsonArray.add(jsonObject);
		}
		String msg = "";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg = "查询无数据";
		} else {
			msg = "查询成功";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}

	/**
	 * 问责人数
	 */
	@Override
	public Result accountabilityType(String date1, String date2) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = reportInformationDao.accountabilityNum(date1, date2);
		jsonObject.put("num", list.get(0));
		jsonArray.add(jsonObject);
		String msg = "";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg = "查询无数据";
		} else {
			msg = "查询成功";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}

	/**
	 * 督责人数
	 */
	@Override
	public Result superviseType(String date1, String date2) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = reportInformationDao.superviseNum(date1, date2);
		jsonObject.put("num", list.get(0));
		jsonArray.add(jsonObject);
		String msg = "";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg = "查询无数据";
		} else {
			msg = "查询成功";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}

	/**
	 * id查询问责信息
	 */
	@Override
	public Result findAccountabilityInfoById(String id) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = reportInformationDao.findAccountabilityInfoById(id);
		for (Object object : list) {
			Object[] objects = (Object[]) object;
			jsonObject.put("id", objects[0] == null ? "" : String.valueOf(objects[0]));
			jsonObject.put("reason", objects[1] == null ? "" : String.valueOf(objects[1]));
			jsonObject.put("name", objects[2] == null ? "" : String.valueOf(objects[2]));
			jsonObject.put("time", objects[3] == null ? "" : String.valueOf(objects[3]));
			jsonObject.put("mechanism", objects[4] == null ? "" : String.valueOf(objects[4]));
			jsonObject.put("address", objects[5] == null ? "" : String.valueOf(objects[5]));
			jsonObject.put("leader", objects[6] == null ? "" : String.valueOf(objects[6]));
			jsonObject.put("decision", objects[7] == null ? "" : String.valueOf(objects[7]));
			jsonArray.add(jsonObject);
		}
		String msg = "";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg = "查询无数据";
		} else {
			msg = "查询成功";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}

	/**
	 * id 查询督责信息
	 */
	@Override
	public Result findSuperviseInfoById(String id) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = reportInformationDao.findSuperviseInfoById(id);
		for (Object object : list) {
			Object[] objects = (Object[]) object;
			jsonObject.put("id", objects[0] == null ? "" : String.valueOf(objects[0]));
			jsonObject.put("reason", objects[1] == null ? "" : String.valueOf(objects[1]));
			jsonObject.put("name", objects[2] == null ? "" : String.valueOf(objects[2]));
			jsonObject.put("time", objects[3] == null ? "" : String.valueOf(objects[3]));
			jsonObject.put("mechanism", objects[4] == null ? "" : String.valueOf(objects[4]));
			jsonObject.put("address", objects[5] == null ? "" : String.valueOf(objects[5]));
			jsonObject.put("leader", objects[6] == null ? "" : String.valueOf(objects[6]));
			jsonObject.put("decision", objects[7] == null ? "" : String.valueOf(objects[7]));
			jsonArray.add(jsonObject);
		}
		;
		String msg = "";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg = "查询无数据";
		} else {
			msg = "查询成功";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}

	/**
	 * id查询投诉案办理信息
	 */
	@Override
	public Result ComplaintHandlingById(String code) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = complaintHandlingDao.complaintHandlingById(code);
		for (Object object : list) {
			Object[] objects = (Object[]) object;
			jsonObject.put("informantName", objects[0] == null ? "" : String.valueOf(objects[0]));
			jsonObject.put("informantIdCard", objects[1] == null ? "" : String.valueOf(objects[1]));
			jsonObject.put("informantphone", objects[2] == null ? "" : String.valueOf(objects[2]));
			jsonObject.put("informantPoliticalOutlook", objects[3] == null ? "" : String.valueOf(objects[3]));
			jsonObject.put("informantAddress", objects[4] == null ? "" : String.valueOf(objects[4]));
			jsonObject.put("informantLevel", objects[5] == null ? "" : String.valueOf(objects[5]));
			jsonObject.put("informantsName", objects[6] == null ? "" : String.valueOf(objects[6]));
			jsonObject.put("informantsCompany", objects[7] == null ? "" : String.valueOf(objects[7]));
			jsonObject.put("informantsPosition", objects[8] == null ? "" : String.valueOf(objects[8]));
			jsonObject.put("informantsAddress", objects[9] == null ? "" : String.valueOf(objects[9]));
			jsonObject.put("informantsLevel", objects[10] == null ? "" : String.valueOf(objects[10]));
			jsonObject.put("problemCategories", objects[11] == null ? "" : String.valueOf(objects[11]));
			jsonObject.put("content", objects[12] == null ? "" : String.valueOf(objects[12]));
			jsonObject.put("time", objects[13] == null ? "" : String.valueOf(objects[13]));
			jsonObject.put("fileName", objects[14] == null ? "" : String.valueOf(objects[14]));
			jsonObject.put("examinationResult", objects[15] == null ? "" : String.valueOf(objects[15]));
			jsonObject.put("examinationName", objects[16] == null ? "" : String.valueOf(objects[16]));
			jsonObject.put("examinationDescribe", objects[17] == null ? "" : String.valueOf(objects[17]));
			jsonObject.put("code", objects[18] == null ? "" : String.valueOf(objects[18]));
			jsonArray.add(jsonObject);
		}
		String msg = "";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg = "查询无数据";
		} else {
			msg = "查询成功";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}

	/**
	 * 更新投诉办理信息
	 */
	@Override
	public Result updatecomplaintHandling(String code, String examinationResult, String examinationName,
			String examinationDescribe) {
		Result result = new Result();
		complaintHandlingDao.updatecomplaintHandling(code, examinationResult, examinationName, examinationDescribe);
		String msg = "更新成功";
		result.setMsg(msg);
		return result;
	}

	/**
	 * 按年月周时间段查询投诉类型条数
	 */
	@Override
	public Result reportType(String date1, String date2) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = complaintHandlingDao.reportType(date1, date2);
		for (Object object : list) {
			Object[] objects = (Object[]) object;
			jsonObject.put("type", objects[0] == null ? "" : String.valueOf(objects[0]));
			jsonObject.put("num", objects[1] == null ? "" : String.valueOf(objects[1]));
			jsonArray.add(jsonObject);
		}
		String msg = "";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg = "查询无数据";
		} else {
			msg = "查询成功";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}

	/**
	 * 按时间段年月周查询 督问责条数
	 */
	@Override
	public Result superviseAndAccountabilityNum(String date1, String date2) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = reportInformationDao.superviseAndAccountabilityNum(date1, date2);
		jsonObject.put("num", list.get(0));
		jsonArray.add(jsonObject);
		String msg = "";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg = "查询无数据";
		} else {
			msg = "查询成功";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}

	/**
	 * 按时间段年月周查询投诉件数
	 */
	@Override
	public Result reportNum(String date1, String date2) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = reportInformationDao.reportNum(date1, date2);
		jsonObject.put("num", list.get(0));
		jsonArray.add(jsonObject);
		String msg = "";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg = "查询无数据";
		} else {
			msg = "查询成功";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}

	/**
	 * 按时间段年月周查询投诉办理件数
	 */
	@Override
	public Result handlingComplaintsNum(String date1, String date2) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = reportInformationDao.handlingComplaintsNum(date1, date2);
		jsonObject.put("num", list.get(0));
		jsonArray.add(jsonObject);
		String msg = "";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg = "查询无数据";
		} else {
			msg = "查询成功";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}

	/**
	 * 按时间段年月周查询被投诉人数
	 */
	@Override
	public Result beComplainedPersonleNum(String date1, String date2) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = reportInformationDao.beComplainedPersonleNum(date1, date2);
		jsonObject.put("num", list.get(0));
		jsonArray.add(jsonObject);
		String msg = "";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg = "查询无数据";
		} else {
			msg = "查询成功";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}

	/**
	 * 按时间段年月周查询投诉属实件数
	 */
	@Override
	public Result isTrueNum(String date1, String date2) {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> list = reportInformationDao.isTrueNum(date1, date2);
		jsonObject.put("num", list.get(0));
		jsonArray.add(jsonObject);
		String msg = "";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg = "查询无数据";
		} else {
			msg = "查询成功";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}

	/**
	 * 按时间段根据 投诉类型查询投诉信息
	 */
	@Override
	public Result reportTypeInfo(String date1, String date2, String type,int page,int pageSize) {
	    Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONArray jsonArrays = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObjects = new JSONObject();
		List<?> list = reportInformationDao.reportTypeInfo(date1, date2, type);
		int size = list.size();
		PageBaen pagebean = new PageBaen(size);// 初始化PageBean对象
		// 设置当前页
		pagebean.setCurPage(page); // 这里page是从页面上获取的一个参数，代表页数
		pagebean.setPageSize(pageSize);
		pagebean.setRowsCount(list.size());
		// 获得分页大小
		int pagesize = pagebean.getPageSize();
		// 获得分页数据在list集合中的索引
		int firstIndex = (page - 1) * pagesize;
		int toIndex = page * pagesize;
		if (toIndex > list.size()) {
			toIndex = list.size();
		}
		if (firstIndex > toIndex) {
			firstIndex = 0;
			pagebean.setCurPage(1);
		}
		// 截取数据集合，获得分页数据
		List<?> courseList = list.subList(firstIndex, toIndex);
		List<JSONArray> list2 = new ArrayList<JSONArray>();
		for (Object object : courseList) {
			Object[] objects = (Object[]) object;
			jsonObject.put("code", objects[0] == null ? "" : String.valueOf(objects[0]));
			jsonObject.put("name", objects[1] == null ? "" : String.valueOf(objects[1]));
			jsonObject.put("type", objects[2] == null ? "" : String.valueOf(objects[2]));
			jsonObject.put("time", objects[3] == null ? "" : String.valueOf(objects[3]));
			jsonObject.put("problem", objects[4] == null ? "" : String.valueOf(objects[4]));
			jsonObject.put("result", objects[5] == null ? "" : String.valueOf(objects[5]));
			jsonObject.put("examinationName", objects[6] == null ? "" : String.valueOf(objects[6]));
			jsonArrays.add(jsonObject);
		}
		list2.add(jsonArrays);
		jsonObjects.put("total", list.size());
		jsonObjects.put("list", list2);
		jsonArray.add(jsonObjects);
		String msg = "";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg = "查询无数据";
		} else {
			msg = "查询成功";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}

	/**
	 * @author: 王章鹏 wzp
	 * 查询所有的投诉举报信息
	 * @create: 2017/12/5
	 * @return {@link Result}
	 */
	@Override
	public Result findAllReport(com.henghao.util.PageBaen pageBaen) {

		// 分页参数
		Integer page = pageBaen.getCurrentPage();	// 当前页
		Integer pageSize = pageBaen.getPageSize();	// size
		// name为空时，直接返回，不查询
		if (page == null || pageSize == null)
			return new Result(1,"查询名字或分页参数为空",null);
		/*
		 * 分页设置
		 */
		// 因为初始页（第一页）page为1，所以这里需要处理一下
		page = page == 1?0:page;
		// 数据库分页LIMIT start,size
		int start = page * pageSize;
		try {
			// 查询总条数
			String sql_count = "SELECT COUNT(*)\n " +
					"FROM report_information r,complaint_handling c \n " +
					"WHERE c.`code`= r.`code` \n " ;

			Integer total = userDao.getCount(sql_count);
			// 将总条数设置进分页工具类中
			pageBaen.setTotal(total);

			// 分页查询投诉举报信息
			String sql = "SELECT r.`code`,r.informantsName,r.problemCategories,\n" +
					"r.time,r.content,c.examinationResult,c.examinationName \n" +
					"FROM report_information r,complaint_handling c \n" +
					"WHERE c.`code`= r.`code` \n" +
					" LIMIT "+start+","+pageSize;
			// 返回集合
			List<ComplaintVo> voList = userDao.getVoList(sql, ComplaintVo.class);
			pageBaen.setList(voList);
			return new Result(0,"查询成功",pageBaen);
		}catch (Exception e){
			e.printStackTrace();
		}
		return new Result(1,"查询失败,分页参数错误",null);
	}

}
