package com.henghao.vo;

/**
 * @author wzp
 * @description: 执法人员信息展示包装类
 * @create on 2017/12/6.
 */
public class CaseUserVo {

    // 姓名
    private String username;
    // 部门
    private String dept;
    // 职位
    private String POSITION;
    // 电话号码
    private String TELEPHONE;
    private String fileName;    // 用户头像文件名
    private String fileSuffix;  // 用户头像文件后缀名
    private String image;   // 用户头像
    /*
     * 诚信分数
     */
    private Double grade_de;   // 德
    private Double grade_ji;   // 绩
    private Double grade_neng; // 能
    private Double grade_qin;  // 勤
    private Double grade_lian; // 廉

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getPOSITION() {
        return POSITION;
    }

    public void setPOSITION(String POSITION) {
        this.POSITION = POSITION;
    }

    public String getTELEPHONE() {
        return TELEPHONE;
    }

    public void setTELEPHONE(String TELEPHONE) {
        this.TELEPHONE = TELEPHONE;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getGrade_de() {
        return grade_de;
    }

    public void setGrade_de(Double grade_de) {
        this.grade_de = grade_de;
    }

    public Double getGrade_ji() {
        return grade_ji;
    }

    public void setGrade_ji(Double grade_ji) {
        this.grade_ji = grade_ji;
    }

    public Double getGrade_neng() {
        return grade_neng;
    }

    public void setGrade_neng(Double grade_neng) {
        this.grade_neng = grade_neng;
    }

    public Double getGrade_qin() {
        return grade_qin;
    }

    public void setGrade_qin(Double grade_qin) {
        this.grade_qin = grade_qin;
    }

    public Double getGrade_lian() {
        return grade_lian;
    }

    public void setGrade_lian(Double grade_lian) {
        this.grade_lian = grade_lian;
    }

    @Override
    public String toString() {
        return "CaseUserVo{" +
                "username='" + username + '\'' +
                ", dept='" + dept + '\'' +
                ", POSITION='" + POSITION + '\'' +
                ", TELEPHONE='" + TELEPHONE + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileSuffix='" + fileSuffix + '\'' +
                ", image='" + image + '\'' +
                ", grade_de=" + grade_de +
                ", grade_ji=" + grade_ji +
                ", grade_neng=" + grade_neng +
                ", grade_qin=" + grade_qin +
                ", grade_lian=" + grade_lian +
                '}';
    }
}
