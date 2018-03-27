package com.henghao.istration.service;

import javax.servlet.http.HttpServletResponse;

import com.henghao.istration.util.Result;

public interface IAppLoadServcie {

	public Result loadFile(String id, HttpServletResponse response);

}
