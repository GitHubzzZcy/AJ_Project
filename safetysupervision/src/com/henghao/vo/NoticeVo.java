package com.henghao.vo;

/**
 * @author wzp
 * @description: 公告包装类
 * @create on 2017/11/28.
 */
public class NoticeVo {

    private String gid; // 公告ID
    private String uid; // 用户ID
    private String gonggao_title;   // 公告标题
    private String gonggao_author;  // 公告作者
    private String gonggao_content; // 公告内容
    private String gonggao_imageUrl;// 公告图片
    private String gonggao_sendDate;// 公告发布日期

    public String getGid() {
        return this.gid;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getGonggao_title() {
        return this.gonggao_title;
    }

    public void setGonggao_title(String gonggao_title) {
        this.gonggao_title = gonggao_title;
    }

    public String getGonggao_author() {
        return this.gonggao_author;
    }

    public void setGonggao_author(String gonggao_author) {
        this.gonggao_author = gonggao_author;
    }

    public String getGonggao_content() {
        return this.gonggao_content;
    }

    public void setGonggao_content(String gonggao_content) {
        this.gonggao_content = gonggao_content;
    }

    public String getGonggao_imageUrl() {
        return this.gonggao_imageUrl;
    }

    public void setGonggao_imageUrl(String gonggao_imageUrl) {
        this.gonggao_imageUrl = gonggao_imageUrl;
    }

    public String getGonggao_sendDate() {
        return this.gonggao_sendDate;
    }

    public void setGonggao_sendDate(String gonggao_sendDate) {
        this.gonggao_sendDate = gonggao_sendDate;
    }

    public NoticeVo() {
    }

    public NoticeVo(String gid, String uid, String gonggao_title, String gonggao_author, String gonggao_content, String gonggao_imageUrl, String gonggao_sendDate) {
        this.gid = gid;
        this.uid = uid;
        this.gonggao_title = gonggao_title;
        this.gonggao_author = gonggao_author;
        this.gonggao_content = gonggao_content;
        this.gonggao_imageUrl = gonggao_imageUrl;
        this.gonggao_sendDate = gonggao_sendDate;
    }

    public String toString() {
        return

                "Notice [gid=" + this.gid + ", uid=" + this.uid + ", gonggao_title=" + this.gonggao_title + ", gonggao_author=" + this.gonggao_author + ", gonggao_content=" + this.gonggao_content + ", gonggao_imageUrl=" + this.gonggao_imageUrl + ", gonggao_sendDate=" + this.gonggao_sendDate + "]";
    }
}
