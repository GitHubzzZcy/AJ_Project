/**
 * 
 */
package com.henghao.dao;

import java.util.List;
import java.util.Map;

import com.henghao.entity.Enterise;
import com.henghao.util.PageBaen;

public interface EnteriseDao extends IBaseDao<Enterise> {
	/**
	 * 根据区域统计企业
	 * @return
	 */
	public Map<String,Object> findByQy();
	/**
	 * 按监管类型查询企业统计
	 * @return
	 */
	public Map<String,Object> findByJglx();
	/**
	 * 按监管部门分类查询企业统计
	 * @return
	 */
	public Map<String,Object> findByJgbm();
	/**
	 * 按监管级别查询企业统计
	 * @return
	 */
	public Map<String,Object> findByJgjb();
	/**
	 * 按日期条件查询个区域企业隐患统计
	 */
	public Map<String,Object> findYhByCondition(String condition);
	/**
	 * 按日期条件查询各监管部门企业隐患统计
	 */
	public Map<String,Object> findYhByBmCondttion(String condition);
	/**
	 * 按日期条件查询各监管类型企业隐患统计
	 */
	public Map<String,Object> findYhByLxCondttion(String condition);
	/**
	 * 按日期条件查询个区域企业自查自报统计
	 */
	public Map<String,Object> findYhByConditionZczb(String condition);
	/**
	 * 按日期条件查询各监管部门企业自查自报统计
	 */
	public Map<String,Object> findYhByBmCondttionZczb(String condition);
	/**
	 * 按日期条件查询各监管类型企业自查自报统计
	 */
	public Map<String,Object> findYhByLxCondttionZczb(String condition);
	/**
	 * 查询用户信息，返回用户id，姓名，部门，岗位
	 * @param condition
	 * @return
	 */
	public Map<String,Object> findAllUser(String condition);
	/**
	 * 查询最新的指定数量的隐患记录,num获取数量
	 * @param num
	 * @return
	 */
	public Map<String,Object> findHiddByNum(int num);
	/**
	 * 查询最新诚信建设内容，传入最新的条数
	 * @param num
	 * @return
	 */
	public Map<String,Object> findCxjs(int num);
	/**
	 * 查询最新安全宣传教育，传入最新的条数
	 * @param num
	 * @return
	 */
	public Map<String,Object> findSafeTeache(int num);
	
	
	
	/**
	 * 按照用户名查询他今年的隐患排查超时的数目，按年查询
	 */
	public Map<String,Object> findHiddenChaosshi(String user,String date);
	/**
	 * 按照用户名查询他今年的隐患排查成功的数目，按年查询
	 */
	public Map<String,Object> findHiddenSuccess(String user,String date);
	/**
	 * 按照用户名查询他今年的隐患排查的整改情况：1未整改、2整改中、3已整,其他的数量，按年查询
	 */
	public Map<String,Object> findHiddenAllNumByzgstatus(String user,String date);
	/**
	 * 按照用户名查询他今年的隐患排查的整改情况数量：按企业类型分类，按年查询
	 */
	public Map<String,Object> findHiddenAllNumByEnlx(String user,String date);
	/**
	 * 按照用户名查询他今年的隐患排查的整改情况数量：按企业区域划分，按年查询
	 */
	public Map<String,Object> findHiddenAllNumByEnqh(String user,String date);
	/**
	 * 按照用户名查询他今年的隐患排查的整改情况数量：按整改季度统计，按年查询
	 */
	public Map<String,Object> findHiddenAllNumBySession(String user,String date);
	/**
	 * 按照用户名查询他今年的隐患排查的全部数量，按年查询
	 */
	public int findHiddenAllNum(String user,String date);
	/**
	 * 按照用户名查询分析他的各办事时间差长度统计
	 */
	public Map<String,Object> findTimeLength(String startdate, String enddate,String user);
	/**
	 * 查询 指定时间 所有有隐患排查记录的企业
	 */
	public Map<String,Object> findAllHidEnByDate(String entName,String startDate,String endDate);
	/**
	 * 由 企业id 查询指定企业 指定时间 的隐患排查情况
	 */
	public Map<String,Object> findHidEnByDate_EnId(String entId,String startDate,String endDate); 
	/**
	 * 根据姓名和日前查询执法人员的执法企业和位置
	 */
	public Map<String,Object> findZfPosition(String username,String startDate,String endDate,PageBaen pb); 
	/**
	 * 查询某人指定时间执法情况
	 */
	public List<Object[]> findZfByDate(String name,String startDate,String endDate);


}
