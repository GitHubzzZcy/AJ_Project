package com.henghao.vo;

import java.util.List;

/**
 * @author wzp
 * @description: 文件集合包装类
 * @create on 2017/12/6.
 */
public class FileVo {

    private List<String> pic;   // 图片集合
    private List<String> doc;   // 文书集合
    private List<String> video; // 印象集合

    public List<String> getPic() {
        return pic;
    }

    public void setPic(List<String> pic) {
        this.pic = pic;
    }

    public List<String> getDoc() {
        return doc;
    }

    public void setDoc(List<String> doc) {
        this.doc = doc;
    }

    public List<String> getVideo() {
        return video;
    }

    public void setVideo(List<String> video) {
        this.video = video;
    }


    public FileVo(List<String> pic, List<String> doc, List<String> video) {
        this.pic = pic;
        this.doc = doc;
        this.video = video;
    }

    public FileVo() {
    }
}
