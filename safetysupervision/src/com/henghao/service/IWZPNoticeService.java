package com.henghao.service;

import com.henghao.entity.Result;

/**
 * @author wzp
 * @description: 公告service接口
 * @create on 2017/11/28.
 */
public interface IWZPNoticeService {
    /**
     * PC端查看最新一条公告
     * @return
     */
    Result findToPC();

    /**
     * APP - 获取用户已读公告列表
     * @param paramString1
     * @param paramString2
     * @return
     * @throws Exception
     */
    Result getIsReadList(String paramString1, String paramString2) throws Exception;

    /**
     * APP - 标记为已读
     * @param paramString1
     * @param paramString2
     * @return
     */
    Result MarkAsRead(String paramString1, String paramString2);

    /**
     * APP - 用户删除公告信息
     * @param paramString1
     * @param paramString2
     * @return
     */
    Result deleteNotice(String paramString1, String paramString2);

    /**
     * APP - 全部标记为已读
     * @param paramString
     * @return
     */
    Result updateReadAll(String paramString);
}
