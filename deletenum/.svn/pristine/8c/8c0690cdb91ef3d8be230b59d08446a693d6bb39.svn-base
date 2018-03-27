package com.henghao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henghao.dao.DeleteDataDao;
import com.henghao.dao.DeleteTableDao;
import com.henghao.entity.Result;
import com.henghao.service.DeleteDataService;
@Service("DeleteDataService")
public class DeleteDataServiceImpl implements DeleteDataService {
	@Autowired
	private DeleteDataDao deleteDataDao;
	@Autowired
	private DeleteTableDao deleteTableDao;
	@Override
	public Result deleteData(String tableName) {
		
		Result result = new Result();
		 try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace(); 
	        }
		try {
			deleteTableDao.deleteTableId();
			deleteDataDao.deleteData();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		String msg ="删除成功";
		result.setMsg(msg);
		return result;
	}

}
