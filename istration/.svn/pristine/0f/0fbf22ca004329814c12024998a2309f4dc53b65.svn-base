package com.henghao.istration.controller;



import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.istration.service.IAppLoadServcie;
import com.henghao.istration.util.Result;

/**
 * App文件下载
 * @author admin
 *
 */
@RequestMapping("/appload")
@Controller
public class AppLoadController {
	
	@Autowired
	private IAppLoadServcie appLoadService;

	@RequestMapping("/files")
	@ResponseBody
	public Result loadFile(String id, HttpServletResponse response) {
		Result  result = appLoadService.loadFile(id,response);
		return result;
	}
}
