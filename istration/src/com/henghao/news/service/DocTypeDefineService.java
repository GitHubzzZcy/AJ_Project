package com.henghao.news.service;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.henghao.news.dao.DocTypeDefineDao;
import com.henghao.news.entity.DocTypeDefine;

@Service
public class DocTypeDefineService {

	@Resource
	private DocTypeDefineDao docTypeDefineDao;
	
	//根据dtid查询文书定义表
	public DocTypeDefine queryDocTypeDefineByDtid(int dtid) {
		DocTypeDefine findById = docTypeDefineDao.findById(dtid);
		return findById;
	}
	//根据计划检查的pid查询存在的文书定义列表
	public Set<DocTypeDefine> queryDocTypeDefineByPid(int pid) {
		Set<DocTypeDefine> set = docTypeDefineDao.queryDocTypeDefineByPid(pid);
		return set;
	}
	//添加文书定义
	public void add(DocTypeDefine docTypeDefine) {
		docTypeDefineDao.add(docTypeDefine);
	}
}
