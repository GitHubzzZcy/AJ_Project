package com.henghao.news.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.henghao.news.entity.DocTypeDefine;

@Repository
public class DocTypeDefineDao extends BaseDaoImpl<DocTypeDefine> {

	public Set<DocTypeDefine> queryDocTypeDefineByPid(int pid) {
		String hql = "FROM DocTypeDefine WHERE pid=?";
		@SuppressWarnings("unchecked")
		List<DocTypeDefine> list = super.getSession().createQuery(hql).setParameter(0, pid).list();
		Set<DocTypeDefine> set = new HashSet<DocTypeDefine>(list);
		return set;
	}

}
