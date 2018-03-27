package com.henghao.dao.impl;

import com.henghao.dao.IWZPNoticeDao;
import com.henghao.entity.wzp.UserNotice;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

import com.henghao.vo.NoticeVo;

import java.util.List;
import java.util.Map;

import org.hibernate.transform.Transformers;

/**
 * @author wzp
 * @description: 公告dao实现类
 * @create on 2017/12/3.
 */
//@Repository("noticeDao")
@SuppressWarnings("all")
public class WZPNoticeDaoImpl extends WZPBaseDaoImpl<UserNotice> implements IWZPNoticeDao {

    // 返回的map
    Map<String, Object> map = new HashMap();

    /**
     * 全部标记为已读
     * @param uid 用户ID
     * @return
     * @throws Exception
     */
    public int updateReadAll(String uid) throws Exception {
        String hql = "UPDATE UserNotice SET user_isRead=1 where uid=:uid AND user_isRead='0' ";
        int update = super.getSession().createQuery(hql).setParameter("uid", uid).executeUpdate();
        return update;
    }

    /**
     * PC端查看最新一条公告
     * @return
     * @throws Exception
     */
    public Map<String, Object> findToPC() throws Exception {
        String sql = "SELECT s.ID as gid,t.USERID as uid,t.ACTIONTIME as gonggao_sendDate," +
                "s.TITLE gonggao_title,s.GONGGAO_AUTHOR gonggao_author,s.GONGGAO_CONTENT gonggao_content  " +
                "FROM AJ_FBGG s,twr_hz_instance tr,tw_hz_log t " +
                "WHERE t.WORKID=tr.WORKID " +
                "AND s.ID=tr.DATAID " +
                "AND t.FLOWID='ggfb' " +
                "AND t.NEXTNODEIDS='End3;' " +
                "AND s.GGFH='通过' " +
                "ORDER BY t.ACTIONTIME DESC " +
                "LIMIT 1";

        NoticeVo notice = (NoticeVo) super.getSession().createSQLQuery(sql)
                .setResultTransformer(Transformers.aliasToBean(NoticeVo.class))
                .uniqueResult();
        this.map.put("notice", notice);
        return this.map;
    }

    /**
     * 标记为已读，单条
     * @param uid 用户ID
     * @param gid 公告ID
     * @return
     */
    public int updateNoticeIsRead(String uid, String gid) {

        String hql = "UPDATE UserNotice SET user_isRead='1' WHERE gid=:gid AND uid=:uid";
        return super.getSession().createQuery(hql).setParameter("gid", gid).setParameter("uid", uid).executeUpdate();
    }

    /**
     * 查询用户未读公告列表
     * @param uid 用户ID
     * @param isRead 是否已读（0：未读，1：已读）
     * @return
     * @throws Exception
     */
    public List<NoticeVo> getUnreadList(String uid, String isRead) throws Exception {

        String sql = "SELECT s.ID as gid,t.USERID as uid,t.ACTIONTIME as gonggao_sendDate," +
                "s.TITLE gonggao_title,s.GONGGAO_AUTHOR gonggao_author,s.GONGGAO_CONTENT gonggao_content " +
                "FROM AJ_FBGG s,twr_hz_instance tr,tw_hz_log t " +
                "WHERE t.WORKID=tr.WORKID " +
                "AND s.ID=tr.DATAID " +
                "AND t.FLOWID='ggfb' " +
                "AND t.NEXTNODEIDS='End3;' " +
                "AND s.GGFH='通过' " +
                "AND s.ID IN (SELECT gid FROM sa_user_notice WHERE uid=:uid AND user_isRead=:isRead AND user_isDelete=0)";

        List<NoticeVo> list = super.getSession().createSQLQuery(sql)
                .setParameter("uid", uid)
                .setParameter("isRead", isRead)
                .setResultTransformer(Transformers.aliasToBean(NoticeVo.class))
                .list();
        return list;
    }

    /**
     * 查询本地用户-公告表
     * @param uid 用户ID
     * @param gid 公告ID
     * @return
     * @throws Exception
     */
    public UserNotice findUserNotice(String uid, String gid) throws Exception {
        String hql = "FROM UserNotice WHERE uid=:uid AND gid=:gid";
        UserNotice userNotice = (UserNotice) super.getSession().createQuery(hql)
                .setParameter("uid", uid)
                .setParameter("gid", gid)
                .uniqueResult();
        return userNotice;
    }

    /**
     * 用户删除公告
     * @param userNotice
     * @throws Exception
     */
    public void deleteUserNotice(UserNotice userNotice) throws Exception {

        String hql = "UPDATE UserNotice SET user_isDelete=1 WHERE id=:id";
        super.getSession().createQuery(hql).setParameter("id", Integer.valueOf(userNotice.getId())).executeUpdate();
    }

    /**
     * 添加本地：用户-公告表
     * @param userNotice
     * @throws Exception
     */
    public void addUserNotice(UserNotice userNotice) throws Exception {
        super.getSession().save(userNotice);
    }

    /**
     * 获取用户未读
     * @param paramString
     * @return
     * @throws Exception
     */
    public List<String> getUserUnread(String uid) throws Exception {
        String sql = "SELECT s.ID FROM AJ_FBGG s WHERE s.GGFH='通过'";
        List<String> list = super.getSession().createSQLQuery(sql).list();
        return list;
    }

}
