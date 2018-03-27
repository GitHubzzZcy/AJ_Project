package com.henghao.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author 王章鹏
 * @time 2017-8-30 上午9:42:06
 */
@SuppressWarnings("all")
public interface IWZPBaseDao<T> {

	/**
	 * 保存一个对象
	 * 
	 * @param o
	 * @return
	 */
	public T save(T o);

	/**
	 * 保存实体类对象 <br>
	 * 创建时间：2017-6-22 上午10:59:15
	 * 
	 * @param o
	 * @param entityClass
	 * @return
	 */
	public <T> T save(T o, Class<T> entityClass);

	/**
	 * 删除一个对象
	 * 
	 * @param o
	 */
	public void delete(T o);

	/**
	 * 更新一个对象
	 * 
	 * @param o
	 */
	public void update(T o);

	/**
	 * 修改
	 * 
	 * @time 2017-8-14
	 * @param o
	 * @param entityClass
	 */
	public <T> void update(T o, Class<T> entityClass);

	/**
	 * 保存或更新对象
	 * 
	 * @param o
	 */
	public void saveOrUpdate(T o);

	/**
	 * 保存或更新对象
	 * 
	 * @param o
	 */
	public <O> void saveOrUpdate(O o, Class<O> entityClass);

	/**
	 * select count(*) from 类
	 * 
	 * @param hql
	 * @return
	 */
	public Integer getCount(String sql);

	/**
	 * 根据ID查询实体类<br>
	 * 若不指定entityClazz，则默认返回继承IBaseDao的实体类。<br>
	 * 若没有实体类注入<T>，则返回空 创建时间：2017-6-19 上午10:19:32
	 * 
	 * @param id
	 *            实体类ID
	 * @param entityClazz
	 *            返回实体类的class
	 * @return
	 */
	public <T> T getById(Serializable id, Class<T> entityClazz);

	/**
	 * 根据ID查询实体类 创建时间：2017-6-20 上午9:44:14
	 * 
	 * @param id
	 * @return
	 */
	public T getById(Serializable id);

	/**
	 * 创建时间：2017-6-19 下午6:01:15<br>
	 * 
	 * 查询实体类集合
	 * 
	 * @param queryString
	 *            查询语句
	 * @param entityClass
	 *            需要返回的类的class
	 * @return
	 */
	public <T> List<T> getEntityList(CharSequence queryString,
                                     Class<T> entityClass);

	/**
	 * 获取单个实体类，只能查询实体类，不能查询包装类
	 * 
	 * @time 2017-8-17
	 * @param queryString
	 * @param entityClass
	 * @return
	 */
	public <T> T getEntity(CharSequence queryString, Class<T> entityClass);

	/**
	 * 执行数据库更新操作
	 * 
	 * @param sql
	 * @return 更新的记录条数
	 */
	Integer executeSqlUpdate(String sql);

	/**
	 * 查询返回单个结果集 <br>
	 * 创建时间：2017-6-22 下午5:41:19
	 * 
	 * @param sql
	 * @return
	 */
	public List<String> getStrings(String sql);

	/**
	 * 查询返回唯一结果
	 * 
	 * @time 2017-8-10
	 * @param sql
	 * @return 单个结果
	 */
	public String getString(String sql);

	/**
	 * 获取包装类集合<br>
	 * 要求：查询出来的字段，包装类中必须全部有，并且类型必须全部正确对应
	 * 
	 * @time 2017-7-3
	 * @param sql
	 * @param voClass
	 * @return
	 */
	public <T> List<T> getVoList(String sql, Class<T> voClass);


	/**
	 * 获取单个包装类
	 * 
	 * @time 2017-8-10
	 * @param sql
	 * @param voClass
	 * @return
	 */
	public <T> T getVo(String sql, Class<T> voClass);

}
