package com.henghao.vo;

import com.henghao.entity.wzp.AjZfdata;

import java.util.List;

/**
 * @author wzp
 * @description: 案件详情数据展示
 * @create on 2017/12/6.
 */
public class CaseDetailVo {

    // 执法数据
    private AjZfdata zfdata;
    // 人员数据
    private List<CaseUserVo> userVoList;
    // 执法图片
    FileVo fileVo;
    // 执法时间轴
    List<CaseSJZVo> sjzVos;


    public AjZfdata getZfdata() {
        return zfdata;
    }

    public void setZfdata(AjZfdata zfdata) {
        this.zfdata = zfdata;
    }

    public List<CaseUserVo> getUserVoList() {
        return userVoList;
    }

    public void setUserVoList(List<CaseUserVo> userVoList) {
        this.userVoList = userVoList;
    }

    public List<CaseSJZVo> getSjzVos() {
        return sjzVos;
    }

    public void setSjzVos(List<CaseSJZVo> sjzVos) {
        this.sjzVos = sjzVos;
    }

    public FileVo getFileVo() {
        return fileVo;
    }

    public void setFileVo(FileVo fileVo) {
        this.fileVo = fileVo;
    }

    /**
     * 全参数构造
     *
     */
    public CaseDetailVo(AjZfdata zfdata, List<CaseUserVo> userVoList, FileVo fileVo, List<CaseSJZVo> sjzVos) {
        this.zfdata = zfdata;
        this.userVoList = userVoList;
        this.fileVo = fileVo;
        this.sjzVos = sjzVos;
    }

    public CaseDetailVo() {

    }
}
