package com.henghao.dao.impl;

import com.henghao.dao.IWZPBaseDao;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.SharedSessionContract;
import org.hibernate.transform.Transformers;
/**
 * @author wzp
 * @description: BaseDao实现类
 * @create 2017/11/28.
 */
@SuppressWarnings("all")
public class WZPBaseDaoImpl<T> implements IWZPBaseDao<T>{

    @Resource(name = "sessionFactory")
    SessionFactory sessionFactory;

    // 传进来的类的class文件
    protected Class<T> entityClazz;

    /*
     * 构造函数
     */
    public WZPBaseDaoImpl() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            this.entityClazz = (Class<T>) ((ParameterizedType) type)
                    .getActualTypeArguments()[0];
        } else {
            this.entityClazz = null;
        }

    }



    /* (non-Javadoc)
     * @see com.test.dao.IBaseDao#save(java.lang.Object)
     */
    public T save(T o) {

        return (T) sessionFactory.getCurrentSession().save(o);
    }

    /*
     * (non-Javadoc)
     * @see com.test.dao.IBaseDao#delete(java.lang.Object)
     */
    public void delete(T o) {
        sessionFactory.getCurrentSession().delete(o);
    }

    /*
     * (non-Javadoc)
     * @see com.test.dao.IBaseDao#update(java.lang.Object)
     */
    public void update(T o) {
        sessionFactory.getCurrentSession().update(o);
    }

    /*
     * (non-Javadoc)
     * @see com.test.dao.IBaseDao#saveOrUpdate(java.lang.Object)
     */
    public void saveOrUpdate(T o) {
        sessionFactory.getCurrentSession().saveOrUpdate(o);
    }

    /*
     * (non-Javadoc)
     * @see com.test.dao.IBaseDao#getCount(java.lang.String)
     */
    public Integer getCount(String sql) {

        Object obj = sessionFactory.getCurrentSession().createSQLQuery(sql).uniqueResult();
        if (obj == null) {
            return 0;
        }else{
            return ((Number) sessionFactory.getCurrentSession().createSQLQuery(sql).uniqueResult())
                    .intValue();
        }
    }

    /*
     * (non-Javadoc)
     * @see com.test.dao.IBaseDao#getById(java.io.Serializable, java.lang.Class)
     */
    public <T> T getById(Serializable id, Class<T> entityClazz) {
        if (id == null)
            return null;
        if (entityClazz == null)
            entityClazz = (Class<T>) this.entityClazz;
        return (T) get(entityClazz, id);
    }

    // 获取单个实体，根据实体类及实体的主键获取。
    public <T> T get(Class<T> entityClass, Serializable id) {

        return (T) sessionFactory.getCurrentSession().get(entityClass, id);
    }

    /*
     * (non-Javadoc)
     * @see com.test.dao.IBaseDao#getEntity(java.lang.CharSequence, java.lang.Class)
     */
    public <T> List<T> getEntityList(CharSequence queryString, Class<T> entityClass) {


        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryString.toString())
                .addEntity(entityClass);

        return query.list();
    }

    /*
     * (non-Javadoc)
     * @see com.test.dao.IBaseDao#getById(java.io.Serializable)
     */
    public T getById(Serializable id) {

        return (T) sessionFactory.getCurrentSession().get(entityClazz, id);
    }

    /*
     * (non-Javadoc)
     * @see com.henghao.dao.IBaseDao#save(java.lang.Object, java.lang.Class)
     */
    public <T> T save(T o, Class<T> entityClass) {

        return(T) sessionFactory.getCurrentSession().save(o);
    }

    /*
     * (non-Javadoc)
     * @see com.henghao.dao.IBaseDao#executeSqlUpdate(java.lang.String)
     */
    public Integer executeSqlUpdate(String sql) {

        return sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
    }

    /*
     * (non-Javadoc)
     * @see com.henghao.dao.IBaseDao#getStrings(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<String> getStrings(String sql) {

        return sessionFactory.getCurrentSession()
                .createSQLQuery(sql).list();
    }

    /* 包装类集合
     * (non-Javadoc)
     * @see com.henghao.dao.IBaseDao#getVoList(java.lang.String, java.lang.Class)
     */
    public <T> List<T> getVoList(String sql, Class<T> voClass) {
        // TODO Auto-generated method stub
        return sessionFactory.getCurrentSession().createSQLQuery(sql)
                .setResultTransformer(Transformers.aliasToBean(voClass)).list();
    }

    /*
     * (non-Javadoc)
     * @see com.henghao.dao.sqlserver.IBase_sqlServer#getString(java.lang.String)
     */
    public String getString(String sql) {
        // TODO Auto-generated method stub

        Object obj = sessionFactory.getCurrentSession().createSQLQuery(sql)
                .uniqueResult();
        if (obj == null) {
            return null;
        }
        return sessionFactory.getCurrentSession().createSQLQuery(sql)
                .uniqueResult().toString();
    }


    /*
     * (non-Javadoc)
     * @see com.henghao.dao.sqlserver.IBase_sqlServer#getVo(java.lang.String, java.lang.Class)
     */
    public <T> T getVo(String sql, Class<T> voClass) {
        // TODO Auto-generated method stub
        return (T) sessionFactory.getCurrentSession().createSQLQuery(sql)
                .setResultTransformer(Transformers.aliasToBean(voClass))
                .uniqueResult();
    }



    public <T> T getEntity(CharSequence queryString, Class<T> entityClass) {

        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryString.toString())
                .addEntity(entityClass);

        return (T) query.uniqueResult();
    }



    public <O> void saveOrUpdate(O o, Class<O> entityClass) {
        // TODO Auto-generated method stub
        sessionFactory.getCurrentSession().saveOrUpdate(o);
    }



    public <T> void update(T o, Class<T> entityClass) {
        // TODO Auto-generated method stub
        sessionFactory.getCurrentSession().update(o);
    }


    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
