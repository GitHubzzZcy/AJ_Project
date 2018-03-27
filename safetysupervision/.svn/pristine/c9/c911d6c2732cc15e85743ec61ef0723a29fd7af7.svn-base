package com.henghao.dao;
import java.util.List;
import com.henghao.entity.ReportInformation;


public interface ReportInformationDao extends IBaseDao<ReportInformation> {
	/**
	 * 举报信息
	 * @param reportInformation
	 */
	public void addReportInformation(ReportInformation reportInformation); 
	/**
	 * 查询投诉信息
	 */
	public List<?> findReportInformation(String phone);


	public List<?> findReportInformation(); 
	/**
	 * 问责信息查询
	 * @param date1
	 * @param date2
	 * @return
	 */
	public List<?> findAccountabilityInfo(String date1,String date2);
	/**
	 * 根据id查询问责信息
	 * @param id
	 * @return
	 */
	public List<?> findAccountabilityInfoById(String id);
	/**
	 * 督责信息查询
	 * @param date1
	 * @param date2
	 * @return
	 */
	public List<?> findSuperviseInfo(String date1,String date2);
	/**
	 * 查询formID
	 * @param tableName
	 * @return
	 */
	public List<?> findAccountAndSupervi(String tableName);
	/**
	 * 根据ID查询督责信息
	 * @param id
	 * @return
	 */
	public List<?> findSuperviseInfoById(String id);
	/**
	 * 督责问责类型条数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public List<?> accountabilityAndSupervise(String date1,String date2);

	/**
	 * 问责人数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public List<?> accountabilityNum(String date1,String date2);
	/**
	 * 督责人数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public List<?> superviseNum(String date1,String date2);
	/**
	 * 督责问责总条数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public List<?> superviseAndAccountabilityNum(String date1,String date2);
	/**
	 * 按时间段查询投诉总数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public List<?> reportNum(String date1,String date2);
	/**
	 * 按时间段办理投诉件数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public List<?> handlingComplaintsNum(String date1,String date2);
	/**
	 * 按时间段查询被投诉人数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public List<?> beComplainedPersonleNum(String date1,String date2);
	/**
	 * 按时间段查询属实条数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public List<?> isTrueNum(String date1,String date2);
	/**
	 * 按时间段 根据类型查询 投诉类型信息
	 * @param date1
	 * @param date2
	 * @param type
	 * @return
	 */
	public List<?> reportTypeInfo(String date1,String date2,String type);
}
