package com.henghao.istration.service;

import com.henghao.istration.util.Result;

public interface IApproveService {

	public Result queryfirmname(String flowname);

	public Result findByApplyid(String applyid);

	public Result queryApproveClass();

	public Result queryfirmAll();

	public Result promise();

}
