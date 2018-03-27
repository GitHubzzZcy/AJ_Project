package com.henghao.vo;

import java.util.List;

/**
 * @author wzp
 * @description: 诚信管理信息展示
 * @create on 2017/12/3.
 */
public class IntegrityVo {

    private String username;    // 姓名
    private String duty;    // 职务
    private String tel; // 电话
    private String SEX; //性别（1：女，0：男）
    private String deptName;    // 部门名字
    private String fileName;    // 用户头像文件名
    private String fileSuffix;  // 用户头像文件后缀名
    private String image;   // 用户头像
    private List<ScoreVo> scoreVoList; // 项和分数


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDuty() {
        return duty;
    }

    public String getSEX() {
        return SEX;
    }

    public void setSEX(String SEX) {
        this.SEX = SEX;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public List<ScoreVo> getScoreVoList() {
        return scoreVoList;
    }

    public void setScoreVoList(List<ScoreVo> scoreVoList) {
        this.scoreVoList = scoreVoList;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    @Override
    public String toString() {
        return "IntegrityVo{" +
                "username='" + username + '\'' +
                ", duty='" + duty + '\'' +
                ", tel='" + tel + '\'' +
                ", deptName='" + deptName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileSuffix='" + fileSuffix + '\'' +
                ", scoreVoList=" + scoreVoList +
                '}';
    }
}



