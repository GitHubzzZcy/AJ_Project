package com.henghao.istration.service;

import com.henghao.istration.util.Result;

public interface IUserApeService {

	public Result queryApprove(String name);

	public Result queryBacklog(String name);

	public Result queryPromise(String name);

	public Result personnel(String name);

	public Result flowRoll(String name);

	public Result yearCount(String name);

}
