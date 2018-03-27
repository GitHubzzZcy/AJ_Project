package com.henghao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.henghao.dao.ReportInformationDao;
import com.henghao.entity.ReportInformation;

@Repository
public class ReportInformationDaoImpl extends BaseDao<ReportInformation> implements ReportInformationDao {

	@Override
	public void addReportInformation(ReportInformation reportInformation) {

	}

	/**
	 * 查询投诉信息
	 */
	@Override
	public List<?> findReportInformation(String phone) {
		String sql = "SELECT r.`code`,r.informantsName,r.problemCategories,r.time,r.content,c.examinationResult,c.examinationName FROM report_information r,complaint_handling c WHERE c.`code`= r.`code` and informantphone=? ";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, phone).list();
		return list;
	}

	@Override
	public List<?> findReportInformation() {
		String sql = "SELECT r.`code`,r.informantsName,r.problemCategories,r.time,r.content,c.examinationResult,c.examinationName FROM report_information r,complaint_handling c WHERE c.`code`= r.`code` ";
		List<?> list = super.getSession().createSQLQuery(sql).list();
		return list;
	}

	/**
	 * 问责信息
	 */
	@Override
	public List<?> findAccountabilityInfo(String date1, String date2) {
		String sql = "SELECT ID,YTSJ,FQJG,BWZYY,WZYY,WZDD,WZLL,WZJD FROM aj_wz WHERE ytsj>='"+date1+"%' AND  ytsj<='"+date2+"%' ";
		List<?> list = super.getSession().createSQLQuery(sql).list();
		return list;
	}

	/**
	 * 督责信息
	 */
	@Override
	public List<?> findSuperviseInfo(String date1, String date2) {
		String sql = "SELECT ID,YTSJ,FQJG,BDZYY,DZYY,DZDD,ZDLL,DZJD FROM aj_dz WHERE ytsj>='"+date1+"%' AND  ytsj<='"+date2+"%' ";
		List<?> list = super.getSession().createSQLQuery(sql).list();
		return list;
	}

	/**
	 * 问责人数
	 */
	@Override
	public List<?> accountabilityNum(String date1, String date2) {
		String sql = "SELECT COUNT(DISTINCT BWZYY) FROM aj_wz WHERE ytsj>=? AND  ytsj<=?";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, date1).setParameter(1, date2).list();
		return list;
	}

	/**
	 * 督责人数
	 */
	@Override
	public List<?> superviseNum(String date1, String date2) {
		String sql = "SELECT COUNT(DISTINCT BDZYY)FROM aj_dz WHERE ytsj>=? AND  ytsj<=? ";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, date1).setParameter(1, date2).list();
		return list;
	}

	/**
	 * 督责问责类型条数
	 */
	@Override
	public List<?> accountabilityAndSupervise(String date1, String date2) {
		String sql = "SELECT LX,COUNT(LX) FROM (select LX,BWZYY AS byy FROM aj_wz "
				+ "WHERE ytsj>=? AND  ytsj<=? " 
				+ "UNION ALL " + "select LX,BDZYY AS byy FROM aj_dz "
				+ "WHERE ytsj>=? AND  ytsj<=? ) a " 
				+ "GROUP BY lx";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, date1).setParameter(1, date2)
				.setParameter(2, date1).setParameter(3, date2).list();
		return list;
	}

	/**
	 * 根据id查询问责信息
	 */
	@Override
	public List<?> findAccountabilityInfoById(String id) {
		String sql = "SELECT * FROM aj_wz WHERE Id=? ";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, id).list();
		return list;
	}

	/**
	 * 根据id查询督责信息
	 */
	@Override
	public List<?> findSuperviseInfoById(String id) {
		String sql = "SELECT * FROM aj_dz WHERE id = ? ";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, id).list();
		return list;
	}

	/**
	 * 时间段查询督责问责总条数
	 */
	@Override
	public List<?> superviseAndAccountabilityNum(String date1, String date2) {
		String sql ="SELECT COUNT(*) FROM (select WZYY AS yy,BWZYY AS byy,WZDD AS dd FROM aj_wz "
				+ "WHERE ytsj>=? AND  ytsj<=? " 
				+ "UNION ALL " 
				+ "select DZYY AS yy,BDZYY AS byy,DZDD as dd FROM aj_dz "
				+ "WHERE ytsj>=? AND  ytsj<=? ) a ";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, date1).setParameter(1, date2)
				.setParameter(2, date1).setParameter(3, date2).list();
		return list;
	}

	/**
	 * 时间段投诉件数
	 */
	@Override
	public List<?> reportNum(String date1, String date2) {
		String sql = "SELECT COUNT(*) FROM report_information WHERE time>= '" + date1 + "%' AND time <='" + date2
				+ "%'";
		List<?> list = super.getSession().createSQLQuery(sql).list();
		return list;
	}

	/**
	 * 时间段办理投诉件数
	 */
	@Override
	public List<?> handlingComplaintsNum(String date1, String date2) {
		String sql = "SELECT COUNT(*) FROM report_information r,complaint_handling c " + "WHERE c.`code`=r.`code` "
				+ "AND examinationResult IS NOT NULL " + "AND time>= '" + date1 + "%' " + "AND time <='" + date2 + "%'";
		List<?> list = super.getSession().createSQLQuery(sql).list();
		return list;
	}

	/**
	 * 时间段被投诉人数
	 */
	@Override
	public List<?> beComplainedPersonleNum(String date1, String date2) {
		String sql = "SELECT COUNT(DISTINCT informantsName) FROM report_information " + "WHERE time>='" + date1
				+ "%' AND time <='" + date2 + "%'";
		List<?> list = super.getSession().createSQLQuery(sql).list();
		return list;
	}

	/**
	 * 时间段属实件数
	 */
	@Override
	public List<?> isTrueNum(String date1, String date2) {
		String sql = "SELECT COUNT(*) FROM report_information r,complaint_handling c " + "WHERE c.`code`=r.`code` "
				+ "AND examinationResult='属实' " + "AND time>= '" + date1 + "%' " + "AND time <='" + date2 + "%'";
		List<?> list =super.getSession().createSQLQuery(sql).list();
		return list;
	}

	/**
	 * 按时间段 根据投诉类型查询投诉信息
	 */
	@Override
	public List<?> reportTypeInfo(String date1, String date2, String type) {
		String sql = "SELECT r.`code`,r.informantsName,r.problemCategories,r.time,r.content,c.examinationResult,c.examinationName FROM report_information r,complaint_handling c "
				+ "WHERE c.`code`= r.`code` " 
				+ "AND time >='"+ date1 +"%' " 
				+ "AND time <='"+ date2 +"%' " 
				+ "AND problemCategories=?";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, type).list();
		return list;
	}

	/**
	 * 查询formId
	 */
	@Override
	public List<?> findAccountAndSupervi(String tableName) {
		String sql = "select f.ID from tf_horizon_table t,tf_horizon_form f WHERE t.ID = f.TABLEID AND TABLENAME=?";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, tableName).list();
		return list;
	}

}
