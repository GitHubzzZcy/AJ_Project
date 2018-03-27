package com.henghao.dao;

import com.henghao.entity.ReportInformationFile;

public interface ReportInformationFileDao extends IBaseDao<ReportInformationFile> {

	/**
	 * 文件
	 * @param reportInformation
	 */
	public void addReportInformationFile(ReportInformationFile report);
}
