package com.henghao.service.impl;

import org.springframework.stereotype.Service;

/**
 * @author wzp
 * @description: 公告service实现类
 * @create on 2017/11/28.
 */
@Service("noticeService")
//public class WZPNoticeServiceImpl implements IWZPNoticeService {
public class WZPNoticeServiceImpl {
    /*
    // 公告dao接口
    @Resource
    private IWZPNoticeDao noticeDao;

    public Result getIsReadList(String uid, String isRead) throws Exception {
        Result result;
        if ((uid == null) || ("".equals(uid))) {
            result = new Result(1, "系统繁忙", null);
            return result;
        }
        List<String> gidList;

        gidList = this.noticeDao.getUserUnread(uid);
        if (gidList.size() < 1) {
            result = new Result(1, "该用户还没有收到公告", null);
            return result;
        }
        for (int i = 0; i < gidList.size(); i++) {
            String gid = gidList.get(i);
            UserNotice UserNotice = this.noticeDao.findUserNotice(uid, gid);
            if (UserNotice == null) {
                UserNotice = new UserNotice(gid, uid, "0", "0");
                this.noticeDao.addUserNotice(UserNotice);
            }
        }
        result = new Result(0, "查询成功", this.noticeDao.getUnreadList(uid, isRead));
        return result;
    }

    public Result MarkAsRead(String gid, String uid) {
        Result result;
        if ((gid == null) || ("".equals(gid))) {
            result = new Result(1, "公告ID不能为空", null);
            return result;
        }
        if ((uid == null) || ("".equals(uid))) {
            result = new Result(1, "系统繁忙", null);
            return result;
        }
        UserNotice UserNotice;
        try {
            UserNotice = this.noticeDao.findUserNotice(uid, gid);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(1, "系统繁忙", null);
        }
        if (UserNotice == null) {
            result = new Result(1, "没有这条公告", null);
            return result;
        }
        if (UserNotice.getUser_isRead().equals("1")) {
            result = new Result(1, "该公告已经是已读状态，无需标记", null);
            return result;
        }
        int update;
        try {
            update = this.noticeDao.updateNoticeIsRead(uid, gid);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(1, "系统繁忙", null);
        }
        result = new Result(0, update + "条标记为已读成功", null);
        return result;
    }

    public Result deleteNotice(String gid, String uid) {
        Result result;
        if ((gid == null) || ("".equals(gid))) {
            result = new Result(1, "公告ID不能为空", null);
            return result;
        }
        if ((uid == null) || ("".equals(uid))) {
            result = new Result(1, "系统繁忙", null);
            return result;
        }
        UserNotice UserNotice;
        try {
            UserNotice = this.noticeDao.findUserNotice(uid, gid);
        } catch (Exception e1) {
            e1.printStackTrace();
            return new Result(1, "系统繁忙", null);
        }
        if ((UserNotice == null) || ("1".equals(UserNotice.getUser_isDelete()))) {
            result = new Result(1, "公告已删除或公告不存在", null);
            return result;
        }
        try {
            UserNotice.setUser_isDelete("1");
            this.noticeDao.deleteUserNotice(UserNotice);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(1, "系统繁忙", null);
        }
        result = new Result(0, "删除成功", null);
        return result;
    }

    public Result updateReadAll(String uid) {
        Result result;
        if ((uid == null) || ("".equals(uid))) {
            result = new Result(1, "系统繁忙", null);
            return result;
        }
        List<NoticeVo> list;
        try {
            list = this.noticeDao.getUnreadList(uid, "0");
        } catch (Exception e) {
            return new Result(1, "系统繁忙", null);
        }
        if (list.size() < 1) {
            result = new Result(1, "此用户没有未读公告", null);
            return result;
        }
        try {
            int readAll = this.noticeDao.updateReadAll(uid);
            result = new Result(0, uid + "用户：" + readAll + "条全部标记为已读成功", readAll);
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(1, "全部标记为已读是出错", null);
        }
        return result;
    }

    public Result findToPC() {
        Result result;
        try {
            Map<String, Object> map = this.noticeDao.findToPC();
            result = new Result(0, "查询最新公告成功", map);
        } catch (Exception e) {
            result = new Result(1, "查询最新公告出错", null);
        }
        return result;
    }

*/
}
