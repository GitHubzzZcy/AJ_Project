package com.henghao.istration.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.henghao.istration.dao.ILoginDao;
import com.henghao.istration.entity.User;
import com.henghao.istration.service.ILoginService;
import com.henghao.istration.util.Result;
import com.henghao.news.service.RedisService;
import com.horizon.util.encrypt.DESEDE;
@Service("loginService")
public class LoginServiceImpl implements ILoginService {

	@Resource
	private ILoginDao loginDao;
	@Resource
	private RedisService redisService;
	
	@Override
	public Result login(String username, String password,HttpServletRequest request,
            HttpServletResponse response) {
		Result result = null;
		try {
			if(null == username || null == password) {
				return result = new Result(1,"用户名或密码不能为空！",null);
			}
			String sqlpass = loginDao.query(username);
			User users = loginDao.queryuser(username);
			if(sqlpass == null) {
				return result = new Result(1,"用户名不存在！",null);
			} 
			String headPath = this.queryHeadPath(users.getID());
			users.setHeadPath(headPath);
			password = DESEDE.encryptIt(password);//加密
			try {
				//登录时去查询登录错误次数
				String value = redisService.get("Login-"+username);
				if(value != null) {
					int index = Integer.parseInt(redisService.get("Login-"+username));
					//如果等于5次
					if(index == 5) {
						//限制1小时
						return new Result(1, "连续输入错误密码超5次，限制登录1小时！",null);
					}
				}
			} catch (Exception e) {
				System.out.println("Login**********用户登录Redis错误，错误5次限制登录无效！");
			}
			//DESEDE.decryptIt("ED94C2C049B041FD");//解密
			if(password.equals(sqlpass)) {
				//CookieUtils.setCookie(request, response, COOKIE_NAME, users.getID());
				result = new Result(0,"登录成功",users);
				try {
					redisService.set("Login-"+username, "0");
				} catch (Exception e) {
					System.out.println("Login**********用户登录Redis错误，错误5次限制登录无效！");
				}
			}else{
				int num = 0;
				try {
					//登录错误时
					String dbvalue = redisService.get("Login-"+username);
					if(dbvalue != null) {
						int dbCount = Integer.parseInt(redisService.get("Login-"+username));
						redisService.set("Login-"+username, String.valueOf(dbCount+1), 60*60);
						num = dbCount + 1;
					}else{
						redisService.set("Login-"+username, "1", 60*60);
					}
				} catch (Exception e) {
					System.out.println("Login**********用户登录Redis错误，错误5次限制登录无效！");
				}
				if(num <= 1) {
					result = new Result(1,"密码错误！",null);
				}
				if(num > 1) {
					result = new Result(1,"连续输入错误密码"+num+"次！",null);
				}
			}
		} catch (Exception e) {
			result = new Result(1,"系统繁忙，请稍后在试！",null);
			e.printStackTrace();
		}
		return result;
	}
	
	
	@Override
	public Result findAll() {
		Result result = null;
		try {
			List<String> namelist = new ArrayList<String>();
			List<User> list = loginDao.findAllUser();
			for (int x = 0; x<list.size(); x++) {
				String headPath = list.get(x).getHeadPath();
				if("测试壹".equals(list.get(x).getNAME())) {
					list.set(x, null);
					continue;
				}
				if("测试贰".equals(list.get(x).getNAME())) {
					list.set(x, null);
					continue;
				}
				if("测试叁".equals(list.get(x).getNAME())) {
					list.set(x, null);
					continue;
				}
				if("管理员".equals(list.get(x).getNAME())) {
					list.set(x, null);
					continue;
				}
				if("安监管理员".equals(list.get(x).getNAME())) {
					list.set(x, null);
					continue;
				}
				
				if(null != headPath) {
					//获取文件后缀
					String substring = headPath.substring(headPath.length()-3,headPath.length());
					//事先规定上传的图片只能是jpg格式
					if(!("jpg".equals(substring) | "JPG".equals(substring))) {
						list.set(x, null);
						continue;
					}
					namelist.add(list.get(x).getNAME());
					for (int i=0; i<namelist.size(); i++) {
						String string = namelist.get(i);
						int indexOf = namelist.indexOf(string);
						if(indexOf != i) {
							list.set(indexOf, null);
						}
					}
				}else{
					list.get(x).setHeadPath("head.jpg");
				}
			}
			for(int y =0;y<list.size(); y++) {
				list.remove(null);
			}
			result = new Result(0,"查询成功",list);
		} catch (Exception e) {
			result = new Result(1,"查询失败",null);
			e.printStackTrace();
		}
		return result;
	}

	//根据用户id查询用户头像名
	@Override
	public String queryHeadPath(String userid) {
		String pathName = loginDao.queryHeadPath(userid);
		return pathName;
	}

}
