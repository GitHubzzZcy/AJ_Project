package com.henghao.dao;

import java.util.List;

import com.henghao.entity.Personle;

public interface ApprovalDao extends IBaseDao<Personle> {
	/**
	 * 查询个人案件
	 * @param userId
	 * @return
	 */
	public List<?> findMyApproval(String userId);
	/**
	 * 查询人员部门 和 名称
	 * @param userId
	 * @return
	 */
	public List<?> findpersonnelInfo(String userId);
	/**
	 * 查询部门人员
	 * @return
	 */
	public List<?> findLeaderPersonnelInfo();
	/**
	 * 查询部门人员 局领导除外
	 * @return
	 */
	public List<?> findDeptPersonnelInfonation();
	/**
	 * 个人未办理件数
	 * @param userId
	 * @return
	 */
	public List<?> findMyApprovalNum(String userId);
	/**
	 * 按时间段查询三重一大信息
	 * @param date1
	 * @param date2
	 * @return
	 */
	public List<?>  findMajor(String date1,String date2,String type);
	/**
	 * 根据ID查询三重一大文档
	 * @param id
	 * @return
	 */
	public List<?>  findMajorImg(String id,String name);

}
