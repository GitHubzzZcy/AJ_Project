package com.henghao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.henghao.dao.ComplaintHandlingDao;
import com.henghao.entity.ComplaintHandling;

@Repository
public class ComplaintHandlingDaoImpl extends BaseDao<ComplaintHandling> implements ComplaintHandlingDao {

	/**
	 * 保存投诉 办理信息
	 */
	@Override
	public void addComplaintHandling() {
		// TODO Auto-generated method stub

	}

	/**
	 * 根据id 查询投诉办理信息
	 */
	@Override
	public List<?> complaintHandlingById(String code) {
		String sql = "SELECT r.informantName,r.informantIdCard,r.informantphone,r.informantPoliticalOutlook,"
				+ "r.informantAddress,r.informantLevel,r.informantsName,r.informantsCompany,"
				+ "r.informantsPosition,r.informantsAddress,r.informantsLevel,r.problemCategories,"
				+ "r.content,r.time,f.FILENAME,c.examinationResult,c.examinationName,"
				+ "c.examinationDescribe,c.`code` "
				+ "FROM report_information r,complaint_handling c ,report_information_file f "
				+ "WHERE c.`code`= r.`code` AND r.token = f.TOKEN " + "AND r.`code`=?";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, code).list();
		return list;
	}

	/**
	 * 更新投诉办理信息
	 */
	@Override
	public void updatecomplaintHandling(String code, String examinationResult, String examinationName,
			String examinationDescribe) {
		String sql = "update complaint_handling set examinationResult=?,examinationName=?,examinationDescribe=? where code = ?";
		try {
			getSession().createSQLQuery(sql).setParameter(0, examinationResult).setParameter(1, examinationName)
					.setParameter(2, examinationDescribe).setParameter(3, code).executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("------>>>" + e.getMessage());
		}

	}

	/**
	 * 时间段查询投诉类型条数
	 */
	@Override
	public List<?> reportType(String date1, String date2) {
		String sql = "SELECT problemCategories,COUNT(problemCategories) FROM report_information "
						+ "WHERE time >='"+date1+"%' "
						+ "AND time<='"+date2+"%' "
						+ "GROUP BY problemCategories";
		List<?> list = super.getSession().createSQLQuery(sql).list();
		return list;
	}

}
