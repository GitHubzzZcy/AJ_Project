package com.henghao.istration.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.henghao.istration.dao.IAppLoadDao;
import com.henghao.istration.service.IAppLoadServcie;
import com.henghao.istration.util.FileLoad;
import com.henghao.istration.util.Result;

@Service("appLoadService")
public class AppLoadServiceImpl implements IAppLoadServcie {

	@Resource
	private IAppLoadDao appLoadDao;
	@Override
	public Result loadFile(String id, HttpServletResponse response) {
		Result result = null;
		try {
			String path = appLoadDao.loadFile(id);
			path = "/D:/_tmp/"+path;
			FileLoad load = new FileLoad();
			load.downloadLocal(path, response);
		} catch (Exception e) {
			result = new Result(1,"下载失败",null);
			e.printStackTrace();
		}
		return result;
	}

}
