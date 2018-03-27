package com.henghao.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.henghao.dao.UserPosionDao;
import com.henghao.entity.User_position_log;
/**
 * 人员记位置录dao实现类
 * @类功能说明：  
 * @类修改者：  
 * @修改日期：  
 * @修改说明：  
 * @公司名称：恒昊  
 * @作者： 宁万龙
 * @创建时间：2017-6-15 下午4:18:53  
 * @版本：V1.0
 */
@Repository("Pdao")
public class UserPosionDaoImpl extends BaseDao<User_position_log> implements UserPosionDao {

	@Override
	public void add(User_position_log t) {
		super.add(t);
	}
	@SuppressWarnings("unchecked")
	public List<User_position_log> findPositionByDate(String uname,Date stratDate,Date endDate){
		System.out.println(uname+"***"+stratDate+"***"+endDate);
		String hql="FROM User_position_log WHERE name like ? AND ctime BETWEEN ? AND ?";
		List<User_position_log> list=(List<User_position_log>)super.getSession().createQuery(hql).
				setParameter(0, "%"+uname+"%").setParameter(1, stratDate).setParameter(2, endDate).list();		
		return list;
	}
}
