package com.henghao.entity.wzp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sa_user_notice")
/**
 * @author wzp
 * @description: 用户-公告中间表
 * @create on 2017/11/28.
 */
public class UserNotice {
    @Id
    @GeneratedValue
    private int id;
    private String gid; // 公告ID
    private String uid; // 用户ID
    private String user_isRead; // 是否已读（1：已读，0：未读）
    private String user_isDelete; // 是否删除（1：已删，0：未删）

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGid() {
        return this.gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUser_isRead() {
        return this.user_isRead;
    }

    public void setUser_isRead(String user_isRead) {
        this.user_isRead = user_isRead;
    }

    public String getUser_isDelete() {
        return this.user_isDelete;
    }

    public void setUser_isDelete(String user_isDelete) {
        this.user_isDelete = user_isDelete;
    }

    public UserNotice() {
    }

    public UserNotice(String gid, String uid, String user_isRead, String user_isDelete) {
        this.gid = gid;
        this.uid = uid;
        this.user_isRead = user_isRead;
        this.user_isDelete = user_isDelete;
    }

    public String toString() {
        return

                "User_Notice [id=" + this.id + ", gid=" + this.gid + ", uid=" + this.uid + ", user_isRead=" + this.user_isRead + ", user_isDelete=" + this.user_isDelete + "]";
    }
}
