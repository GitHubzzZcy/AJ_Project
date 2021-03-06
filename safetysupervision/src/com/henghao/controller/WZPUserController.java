package com.henghao.controller;

import com.henghao.entity.Result;
import com.henghao.entity.wzp.ToHorizonUserEntity;
import com.henghao.entity.wzp.TorHorizonUserDeptEntity;
import com.henghao.service.IWZPUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wzp
 * @description: 工作流中的用户控制模块，主要用于APP端调用
 * @create 2017/11/28.
 */
@Controller
@RequestMapping("/user")
public class WZPUserController {

    // service接口
    @Resource
    IWZPUserService userService;

    /**
     * APP端 - 修改用户信息
     * @param userEntity 用户实体类
     * @param userDept 用户部门中间表-实体类
     * @return Result
     */
    @RequestMapping(value = "/updatePersonal",produces = "application/json;charset=utf-8")
    @ResponseBody
    public Result updatePersonal(ToHorizonUserEntity userEntity, TorHorizonUserDeptEntity userDept){
        Result result;
        try {
            result = userService.updatePersonal(userEntity,userDept);
        }catch (Exception e){
            result = new Result(1, "系统繁忙", null);
        }
        return result;
    }

    /**
     * APP端 - 根据用户ID查询用户所属部门ID
     * 用于APP做用户信息修改时调用。
     * @param userId 用户ID
     * @return 部门ID
     */
    @RequestMapping(value = "/selectDeptIdByUserId",produces = "application/json;charset=utf-8")
    @ResponseBody
    public Result selectDeptIdByUserId(String userId){
        Result result;
        try {
            result = userService.selectDeptIdByUserId(userId);
        }catch (Exception e){
            result = new Result(1, "系统繁忙", null);
        }
        return result;
    }


    /**
     * 用户登录成功后，将用户ID传到这里，用于查询个人代办数量。。。
     * 其实这样的设计并不科学，应该是将登录成功的用户信息保存到session中
     * 查询用户个人代办的时候，从session中取出用户ID，再去数据库查询，这样比较好。
     * 。。。。。。
     * @create create by wzp on 2017/12/1
     * @param uid 成功登录的用户ID
     * @return Result
     */
    @RequestMapping(value = "/getUserId",produces = "application/json;charset=utf-8")
    @ResponseBody
    public Result getUserId(String uid, HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        response.addHeader("Access-Control-Max-Age", "1800");//30 min
        Result result;
        try {
            result = userService.getUserId(uid);
        }catch (Exception e){
            result = new Result(1, "系统繁忙", null);
        }
        return result;
    }

    /**
     * 查询用户代办数量
     * 个人代办、待阅事宜
     * @create create by wzp on 2017/12/1
     * @return Result
     */
    @RequestMapping(value = "/selectUserCount",produces = "application/json;charset=utf-8")
    @ResponseBody
    public Result selectUserCount(){
        Result result;
        try {
            result = userService.selectUserCount();
        }catch (Exception e){
            result = new Result(1, "系统繁忙", null);
        }
        return result;
    }

    /**
     * 根据用户名查询诚信管理信息
     * @param username 用户名
     * @return Result
     */

    @RequestMapping(value = "/selectIntegrityByUserName",produces = "application/json;charset=utf-8")
    @ResponseBody
    public Result selectIntegrityByUserName(String username){
        Result result;
        try {
            result = userService.selectIntegrityByUserName(username);
        }catch (Exception e){

            result = new Result(1, "系统繁忙", null);
        }
        return result;
    }




}
