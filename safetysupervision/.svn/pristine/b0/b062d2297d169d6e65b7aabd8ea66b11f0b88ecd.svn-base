package com.henghao.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.henghao.util.PageBaen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.henghao.entity.ReportInformation;
import com.henghao.entity.Result;
import com.henghao.service.ReportInformationService;

/**
 * 录入投诉信息
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/report")
public class ReportInformationAction{
	@Autowired
	private ReportInformationService reportInformationService;
	protected HttpServletResponse response;
	protected HttpServletRequest request;
	/**
	 * 录入投诉信息
	 * 
	 * @param reportInformation
	 * @param file
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/addReportInformation")
	public Result addReportInformation(ReportInformation reportInformation,
			@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request,HttpServletResponse response,
			ModelMap model) throws Exception {
		response = this.response;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
//		 System.out.println("this==="+this.response);
//		 System.out.println("保存成功==="+response);
		return reportInformationService.addReportInformation(reportInformation, file, request, model);
	}
	/**
	 * http协议
	 * @param request
	 * @param response
	 */
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	/**
	 * 查询投诉信息
	 * @update: 2017年12月4日17:13:46做出修改：
	 * 原来是根据电话查询，现在改为根据被投诉人员的姓名查询
	 * by 王章鹏
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findReportByName")
	public Result findReportInformation(String name,PageBaen pageBaen) throws Exception {

		return reportInformationService.findReportByName(name, pageBaen);
	}

	/**
	 * @author: 王章鹏 wzp
	 * 查询所有的投诉举报信息
	 * @create: 2017/12/5
	 * @return {@link Result}
	 */
	@ResponseBody
	@RequestMapping(value = "/findAllReport", produces = "application/json;charset=utf-8")
	public Result findAllReport(PageBaen pageBaen){

		return reportInformationService.findAllReport(pageBaen);
	}



	/**
	 * 查询问责信息
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findAccountabilityInfo")
	public Result findAccountabilityInfo(String date1, String date2, int page, int pageSize) throws Exception {
		return reportInformationService.findAccountabilityInfo(date1, date2, page, pageSize);
	}

	/**
	 * 督责信息
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findSuperviseInfo")
	public Result findSuperviseInfo(String date1, String date2, int page, int pageSize) throws Exception {
		return reportInformationService.findSuperviseInfo(date1, date2, page, pageSize);
	}

	/**
	 * 督责问责类型条数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/superAndAccount")
	public Result superviseAndAccountability(String date1, String date2) throws Exception {
		return reportInformationService.superviseAndAccountability(date1, date2);
	}

	/**
	 * 问责人数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/accountabilityType")
	public Result accountabilityType(String date1, String date2) throws Exception {
		return reportInformationService.accountabilityType(date1, date2);
	}

	/**
	 * 督责人数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/superviseType")
	public Result superviseType(String date1, String date2) throws Exception {
		return reportInformationService.superviseType(date1, date2);
	}

	/***
	 * id查询问责信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findAccountabilityInfoById")
	public Result findAccountabilityInfoById(String id) throws Exception {
		return reportInformationService.findAccountabilityInfoById(id);
	}

	/**
	 * id查询督责信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findSuperviseInfoById")
	public Result findSuperviseInfoById(String id) throws Exception {
		return reportInformationService.findSuperviseInfoById(id);
	}

	/**
	 * code查询投诉办理信息
	 * 
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/complaintHandlingById")
	public Result complaintHandlingById(String code) throws Exception {
		return reportInformationService.ComplaintHandlingById(code);
	}

	/**
	 * 更新投诉案办理信息
	 * 
	 * @param id
	 * @param problem
	 * @param examinationName
	 * @param isTrue
	 * @param details
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/updateInfo")
	public Result updatecomplaintHandling(String code, String examinationResult, String examinationName,
			String examinationDescribe) throws Exception {
		return reportInformationService.updatecomplaintHandling(code, examinationResult, examinationName, examinationDescribe);
	}

	/**
	 * 按时间段年月周查询督责问责条数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/superviseAndAccountabilityNum")
	public Result superviseAndAccountabilityNum(String date1, String date2) throws Exception {
		return reportInformationService.superviseAndAccountabilityNum(date1, date2);
	}
	/**
	 * 按时间段年月周查询投诉类型条数
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/reportType")
	public Result reportType(String date1, String date2)throws Exception{
		return reportInformationService.reportType(date1, date2);
	}
	/**
	 * 按时间段年月周查询投诉件数
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/reportNum")
	public Result reportNum(String date1, String date2)throws Exception{
		return reportInformationService.reportNum(date1, date2);
	}
	/**
	 * 按时间段年月周查询投诉办理件数
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/handlingNum")
	public Result handlingComplaintsNum(String date1, String date2) throws Exception{
		return reportInformationService.handlingComplaintsNum(date1, date2);
	}
	/**
	 * 按时间段年月周查询被投诉人数
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/beComplainedPersonleNum")
	public Result beComplainedPersonleNum(String date1, String date2)throws Exception {
		return reportInformationService.beComplainedPersonleNum(date1, date2);
	}
	/**
	 * 按时间段年月周查询投诉属实件数
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/isTrueNum")
	public Result isTrueNum(String date1, String date2) throws Exception{
		return reportInformationService.isTrueNum(date1, date2);
	}
	/**
	 * 按时间段 根据投诉类型查询投诉信息
	 * @param date1
	 * @param date2
	 * @param type
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/reportTypeInfo")
	public Result reportTypeInfo(String date1, String date2, String type,int page,int pageSize) throws Exception{
		return reportInformationService.reportTypeInfo(date1, date2, type, page, pageSize);
	}
}
