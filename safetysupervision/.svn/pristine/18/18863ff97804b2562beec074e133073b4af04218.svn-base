package com.henghao.dao;
import com.henghao.entity.wzp.UserNotice;
import com.henghao.vo.NoticeVo;
import java.util.List;
import java.util.Map;

/**
 * @author wzp
 * @description: 公告dao接口
 * @create on 2017/12/3.
 */
@SuppressWarnings("all")
public interface IWZPNoticeDao extends IWZPBaseDao<UserNotice> {

    /**
     * 获取用户未读
     * @param paramString
     * @return
     * @throws Exception
     */
    List<String> getUserUnread(String paramString) throws Exception;

    /**
     * 查询本地用户-公告表
     * @param uid 用户ID
     * @param gid 公告ID
     * @return
     * @throws Exception
     */
    UserNotice findUserNotice(String uid, String gid) throws Exception;

    /**
     * PC端查看最新一条公告
     * @return
     * @throws Exception
     */
    Map<String, Object> findToPC() throws Exception;

    /**
     * 添加本地：用户-公告表
     * @param userNotice
     * @throws Exception
     */
    void addUserNotice(UserNotice userNotice) throws Exception;

    /**
     * 查询用户未读公告列表
     * @param uid 用户ID
     * @param isRead 是否已读（0：未读，1：已读）
     * @return
     * @throws Exception
     */
    List<NoticeVo> getUnreadList(String uid, String isRead) throws Exception;

    /**
     * 标记为已读，单条
     * @param uid 用户ID
     * @param gid 公告ID
     * @return
     */
    int updateNoticeIsRead(String uid, String gid);

    /**
     * 全部标记为已读
     * @param uid 用户ID
     * @return
     * @throws Exception
     */
    int updateReadAll(String uid) throws Exception;

    /**
     * 用户删除公告
     * @param userNotice
     * @throws Exception
     */
    void deleteUserNotice(UserNotice userNotice) throws Exception;
}

