package com.henghao.service;

import java.util.List;
import java.util.Map;

import com.henghao.entity.Result;



public interface WorkService {
	/**
	 * 执法人员
	 * @param id
	 * @return
	 */
	public Result searchPersonnel();
	/**
	 * 工作量
	 * @param id
	 * @return
	 */
	public Result searchWorkByName(String name);
	/**
	 * 超期率
	 * @param id
	 * @return
	 */
	public Result searchBeyondById(String name);
	/**
	 * 处罚偏离度
	 * @param id
	 * @return
	 */
	public Result searchPunishById(String name);
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Result searchIntimacyByName(String emplyName);
	
	/**
	 * 
	 * @param emplyName
	 * @return
	 */
	public Map<String, List<List<String>>> searchByEmplyName(String emplyName);
	/**
	 * 处罚次数
	 * @param punishName
	 * @return
	 */
	public Map<String, String> searchByPunishNum(String punishNum);
	/**
	 * 执法次数
	 * @param name
	 * @return
	 */
	public Map<String, String> searchByEnterpriseNum(String enforcementNum);
	/**
	 * 异常率
	 * @param name
	 * @return
	 */
	public Result searchAbnormalRateByName(String name);
	/**
	 * 实际次数
	 * @param name
	 * @return
	 */
	public Map<String, List<List<String>>> searchAbnormalRateByNum(String name);
	/**
	 * 标准次数
	 * @param name
	 * @return
	 */
	public Map<String, List<List<String>>> searchAbnormalRateByStandardNum(String name);
	
}
