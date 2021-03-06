package com.henghao.vo;


/**
 * @author wzp
 * @description: 投诉举报信息展示包装类
 * @create on 2017/12/4.
 */
//public class ComplaintVo implements Serializable {

public class ComplaintVo {
//    private static final long serialVersionUID = 2962273732711539070L;
    // 代码
    private String code;
    // 被投诉人姓名
    private String informantsName;
    // 举报类型
    private String problemCategories;
    // 举报时间
    private String time;
    // 举报内容
    private String content;
    // 检查结果
    private String examinationResult;
    // 检查人
    private String examinationName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInformantsName() {
        return informantsName;
    }

    public void setInformantsName(String informantsName) {
        this.informantsName = informantsName;
    }

    public String getProblemCategories() {
        return problemCategories;
    }

    public void setProblemCategories(String problemCategories) {
        this.problemCategories = problemCategories;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExaminationResult() {
        return examinationResult;
    }

    public void setExaminationResult(String examinationResult) {
        this.examinationResult = examinationResult;
    }

    public String getExaminationName() {
        return examinationName;
    }

    public void setExaminationName(String examinationName) {
        this.examinationName = examinationName;
    }

    @Override
    public String toString() {
        return "ComplaintVo{" +
                "code='" + code + '\'' +
                ", informantsName='" + informantsName + '\'' +
                ", problemCategories='" + problemCategories + '\'' +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                ", examinationResult='" + examinationResult + '\'' +
                ", examinationName='" + examinationName + '\'' +
                '}';
    }
}
