package com.henghao.news.service;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.henghao.news.dao.JPushToUserDao;
import com.henghao.news.entity.JPushToUser;

@Service
public class JPushToUserService {
	
	@Resource
	private JPushToUserDao jPushToUserDao;

	//根据用户id查询所有会议mid
	public List<Integer> queryJPushByUserAllmid(String uid) {
		List<Integer> jPushToUserUserAllmid = jPushToUserDao.queryJPushByUserAllmid(uid);
		HashSet<Integer> h =  new  HashSet<Integer>(jPushToUserUserAllmid);
		jPushToUserUserAllmid.clear();
		jPushToUserUserAllmid.addAll(h);
		return jPushToUserUserAllmid;
	}
	//根据msg_id查询对应的mid
	public Integer queryJPushToUserMidByMsgId(Long msg_id) {
		Integer mid = jPushToUserDao.queryJPushToUserMidByMsgId(msg_id);
		return mid;
	}
	//根据msg_id查询对象
	public JPushToUser queryByMsgId(Long msg_id,String uid) {
		JPushToUser jPushToUser = jPushToUserDao.queryByMsgId(msg_id, uid);
		return jPushToUser;
	}
	
	//根据id查询对象
	public JPushToUser queryByCid(int cid) {
		JPushToUser jPushToUser = jPushToUserDao.findById(cid);
		return jPushToUser;
	}
	//更新对象
	public void updateJPushToUser(JPushToUser jPushToUser) {
		jPushToUserDao.update(jPushToUser);
	}
	//保存或更新一个对象
	public void addJPushToUser(JPushToUser jPushToUser) {
		jPushToUserDao.saveOrUpdate(jPushToUser);
	}
	//根据会议id查询所有消息
	public List<JPushToUser> queryByMid(int mid) {
		List<JPushToUser> relist = jPushToUserDao.queryByMid(mid);
		return relist;
	}
	//根据用户和会议id查询消息
	public List<JPushToUser> queryByMidUser(int mid, String uid) {
		List<JPushToUser> relist = jPushToUserDao.queryByMidUser(mid, uid);
		return relist;
	}
	//根据uid查询未查看的消息
	public List<JPushToUser> queryJPushToUserUnRead(String uid) {
		List<JPushToUser> list = jPushToUserDao.queryJPushToUserUnRead(uid);
		return list;
	}

}
