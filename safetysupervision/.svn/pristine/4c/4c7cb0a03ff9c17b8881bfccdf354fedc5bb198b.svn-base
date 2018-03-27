package com.henghao.service;

import com.henghao.entity.Result;
import com.henghao.entity.wzp.ToHorizonUserEntity;
import com.henghao.entity.wzp.TorHorizonUserDeptEntity;

/**
 * 用户service接口
 * create by wzp on 2017/11/28
 */
public interface IWZPUserService {

    /**
     * APP端 - 修改用户信息
     * @param userEntity 用户实体类
     * @param userDept 用户部门实体类
     * @return Result
     */
    Result updatePersonal(ToHorizonUserEntity userEntity, TorHorizonUserDeptEntity userDept);

    /**
     * APP端 - 根据用户ID查询用户所属部门ID
     * 用于APP做用户信息修改时调用。
     * @param userId 用户ID
     * @return 部门ID
     */
    Result selectDeptIdByUserId(String userId);

    /**
     * 用户登录成功后，将用户ID传到这里
     * @create create by wzp on 2017/12/1
     * @param uid 成功登录的用户ID
     * @return Result
     */
    Result getUserId(String uid);

    /**
     * 查询用户代办数量
     * 个人代办、待阅事宜
     * @create create by wzp on 2017/12/1
     * @return Result
     */
    Result selectUserCount();

    /**
     * 根据用户名查询诚信管理信息
     * @param username 用户名
     * @return Result
     */
    Result selectIntegrityByUserName(String username);
}
