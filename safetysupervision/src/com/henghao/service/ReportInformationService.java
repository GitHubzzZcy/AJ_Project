package com.henghao.service;

import javax.servlet.http.HttpServletRequest;

import com.henghao.util.PageBaen;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.henghao.entity.ReportInformation;
import com.henghao.entity.Result;

@SuppressWarnings("all")
public interface ReportInformationService {
	/**
	 * 投诉信息及文件
	 * 
	 * @param reportInformation
	 * @return
	 */
	public Result addReportInformation(ReportInformation reportInformation,
			@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request,
			ModelMap model);





	/**
	 * 问责信息查询
	 * 
	 * @return
	 */
	public Result findAccountabilityInfo(String date1, String date2, int page, int pageSize);

	/**
	 * 督责信息查询
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public Result findSuperviseInfo(String date1, String date2, int page, int pageSize);

	/**
	 * 督责问责类型条数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public Result superviseAndAccountability(String date1, String date2);

	/**
	 * 问责人数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public Result accountabilityType(String date1, String date2);

	/**
	 * 督责人数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public Result superviseType(String date1, String date2);

	/**
	 * 根据id查询问责信息
	 * 
	 * @param id
	 * @return
	 */
	public Result findAccountabilityInfoById(String id);

	/**
	 * 根据id查询督责信息
	 * 
	 * @param id
	 * @return
	 */
	public Result findSuperviseInfoById(String id);

	/**
	 * id查询投诉信息
	 * 
	 * @param code
	 * @return
	 */
	public Result ComplaintHandlingById(String code);

	/**
	 * 更新投诉办理信息
	 * 
	 * @return
	 */
	public Result updatecomplaintHandling(String code, String examinationResult, String examinationName,
			String examinationDescribe);

	/**
	 * 按时间段查询投诉类型条数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public Result reportType(String date1, String date2);

	/**
	 * 按时间段查询督责问责条数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public Result superviseAndAccountabilityNum(String date1, String date2);
	/**
	 * 按时间段查询投诉件数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public Result reportNum(String date1, String date2);
	/**
	 * 按时间段查询办理投诉件数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public Result handlingComplaintsNum(String date1, String date2);
	/**
	 * 按时间段查询被投诉人数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public Result beComplainedPersonleNum(String date1, String date2);
	/**
	 * 按时间段查询属实件数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public Result isTrueNum(String date1, String date2);
	/**
	 * 按时间段 根据投诉类型查询投诉信息
	 * @param date1
	 * @param date2
	 * @param type
	 * @return
	 */
	public Result reportTypeInfo(String date1, String date2, String type,int page,int pageSize);

	/**
	 * @author: 王章鹏 wzp
	 * 查询所有的投诉举报信息
	 * @create: 2017/12/5
	 * @return {@link Result}
	 */
	Result findAllReport(PageBaen pageBaen);

	/**
	 * 查询投诉信息
	 * @update: 2017年12月4日17:13:46做出修改：
	 * 原来是根据电话查询，现在改为根据被投诉人员的姓名查询
	 * by 王章鹏
	 * @return
	 */
	Result findReportByName(String name,PageBaen pageBaen);
}
