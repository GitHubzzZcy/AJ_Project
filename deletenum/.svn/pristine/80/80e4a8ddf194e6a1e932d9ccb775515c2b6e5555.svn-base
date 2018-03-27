package com.henghao.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.entity.Result;
import com.henghao.service.DeleteDataService;
@Controller
@RequestMapping("/dele")
public class DeleteDataAction {
	@Autowired
	private DeleteDataService deleteDataService;
	@RequestMapping("/delete")
	@ResponseBody
	 public Result deleteData(String tableName) throws Exception{
	    	return deleteDataService.deleteData(tableName);
	    }

}
