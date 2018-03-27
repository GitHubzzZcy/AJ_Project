package com.henghao.dao.impl;

import com.henghao.dao.IWZPUserDao;
import com.henghao.entity.wzp.ToHorizonUserEntity;
import org.springframework.stereotype.Repository;

/**
 * @author wzp
 * @description: 用户 - dao - 接口实现类
 * @create 2017/11/28.
 */
@Repository("userDao")
public class WZPUserDaoImpl extends WZPBaseDaoImpl<ToHorizonUserEntity> implements IWZPUserDao<ToHorizonUserEntity> {


}
