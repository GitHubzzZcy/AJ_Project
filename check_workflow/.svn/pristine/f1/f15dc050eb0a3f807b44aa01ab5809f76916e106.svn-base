package com.henghao.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.henghao.dao.IWorkTimeDao;
import com.henghao.entity.Result;
import com.henghao.service.IWorkTimeService;
@Service
public class WorkTimeSerivce implements IWorkTimeService {
	@Resource
	private IWorkTimeDao iwdao;
	//设置考勤时间段
	public Result setWorkTime(Integer deptNo,String starTime,String endTime){
		Result result = null;
		try {
			Map<String, Object> map = iwdao.setWorkTime(deptNo,starTime,endTime);
			result = new Result(0,"设置成功",map);
		} catch (Exception e) {
			result = new Result(1,"系统繁忙设置失败",null);
			e.printStackTrace();
		}
		return result;
	}

}
