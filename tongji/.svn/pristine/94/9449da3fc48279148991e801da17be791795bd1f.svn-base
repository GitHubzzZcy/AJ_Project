package com.henghao.dao;

import java.util.List;
import java.util.Map;

import com.henghao.vo.VoEnterprise;
import com.henghao.vo.ZFTimeVo;

/**
 * 企业统计dao层接口
 * @author 王章鹏
 * @time 2017-4-13
 */

public interface IWZPEnterpriseDao{
	
	
	
	/** <pre>
	 * 传入sql语句，dao层执行，返回int<pre>
	 * sql所需参数拼接在sql语句中,不做参数注入
	 * 用于执行统计sql语句(count(*))
	 * </pre>
	 * @param sql {@code String} sql语句
	 * @param countName {@code String} 统计别名    count(*)的别名
	 * @author 王章鹏
	 * @time 2017-4-27
	 */
	public int getCount(String sql,String countName);
	
	/**
	 * 所有存在隐患的企业集合
	 * @return {@link VoEnterprise} 企业包装类结合
	 * @throws Exception
	 * @author 王章鹏
	 * @time 2017-4-13
	 */
	public List<VoEnterprise> getEnterpriseList() throws Exception;
	
	/**
	 * 企业统计，包括共有企业数和上报企业数
	 * @return map集合
	 * @throws Exception
	 * @author 王章鹏
	 * @time 2017-4-13
	 */
	public Map<String, Object> EnterpriseCount()throws Exception;
	
	/**
	 * 根据sql语句查询，返回包装类VO集合。<br>
	 * 要求包装类必须包含sql查询出来的所有字段<br>
	 * 表字段与类属性，区分大小写！！！！
	 * 创建时间：2017-5-25 下午2:53:31
	 * @param sql
	 * @param cls 返回的vo类字节码
	 * @return
	 * @throws Exception
	 */
	public List<Object> getObjVo(String sql,Class<? extends Object> cls)throws Exception;
	
	/**
	 * 传入sql语句，查询ZFTimeVo对象集合
	 * 创建时间：2017-6-5 上午10:58:09
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List<ZFTimeVo> getZFTimeVoList(String sql) throws Exception;
	
}

