package com.henghao.entity.wzp;

import javax.persistence.*;

/**
 * 执法信息表 - 实体类
 * @author wzp
 * @description: hibernate逆向生成
 * @create on 2017/12/5.
 */
@Entity
@Table(name = "aj_zfdata", schema = "hz", catalog = "")
public class AjZfdata {
    private String id;
    private String zflb;    //执法类别
    private String zfbm;    //执法部门
    private String zfrs;    //执法人数
    private String zfry;    //执法人员
    private String zfdd;    //执法地点
    private String qy;    //企业/单位名称
    private String xz;    //企业/单位性质
    private String fzr;    //企业/单位负责人
    private String dh;    //企业/单位电话
    private String sfyh;    //是否存在安全隐患
    private String yhsm;    //安全隐患说明
    private String zfjg;    //执法结果
    private String zfryxx;    //执法人员信息
    private String zftu;    //执法图片
    private String zfyx;    //执法影像
    private String zfsjz;    //执时间轴
    private String data;    //复查日期

    @Id
    @Basic
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ZFLB")
    public String getZflb() {
        return zflb;
    }

    public void setZflb(String zflb) {
        this.zflb = zflb;
    }

    @Basic
    @Column(name = "ZFBM")
    public String getZfbm() {
        return zfbm;
    }

    public void setZfbm(String zfbm) {
        this.zfbm = zfbm;
    }

    @Basic
    @Column(name = "ZFRS")
    public String getZfrs() {
        return zfrs;
    }

    public void setZfrs(String zfrs) {
        this.zfrs = zfrs;
    }

    @Basic
    @Column(name = "ZFRY")
    public String getZfry() {
        return zfry;
    }

    public void setZfry(String zfry) {
        this.zfry = zfry;
    }

    @Basic
    @Column(name = "ZFDD")
    public String getZfdd() {
        return zfdd;
    }

    public void setZfdd(String zfdd) {
        this.zfdd = zfdd;
    }

    @Basic
    @Column(name = "QY")
    public String getQy() {
        return qy;
    }

    public void setQy(String qy) {
        this.qy = qy;
    }

    @Basic
    @Column(name = "XZ")
    public String getXz() {
        return xz;
    }

    public void setXz(String xz) {
        this.xz = xz;
    }

    @Basic
    @Column(name = "FZR")
    public String getFzr() {
        return fzr;
    }

    public void setFzr(String fzr) {
        this.fzr = fzr;
    }

    @Basic
    @Column(name = "DH")
    public String getDh() {
        return dh;
    }

    public void setDh(String dh) {
        this.dh = dh;
    }

    @Basic
    @Column(name = "SFYH")
    public String getSfyh() {
        return sfyh;
    }

    public void setSfyh(String sfyh) {
        this.sfyh = sfyh;
    }

    @Basic
    @Column(name = "YHSM")
    public String getYhsm() {
        return yhsm;
    }

    public void setYhsm(String yhsm) {
        this.yhsm = yhsm;
    }

    @Basic
    @Column(name = "ZFJG")
    public String getZfjg() {
        return zfjg;
    }

    public void setZfjg(String zfjg) {
        this.zfjg = zfjg;
    }

    @Basic
    @Column(name = "ZFRYXX")
    public String getZfryxx() {
        return zfryxx;
    }

    public void setZfryxx(String zfryxx) {
        this.zfryxx = zfryxx;
    }

    @Basic
    @Column(name = "ZFTU")
    public String getZftu() {
        return zftu;
    }

    public void setZftu(String zftu) {
        this.zftu = zftu;
    }

    @Basic
    @Column(name = "ZFYX")
    public String getZfyx() {
        return zfyx;
    }

    public void setZfyx(String zfyx) {
        this.zfyx = zfyx;
    }

    @Basic
    @Column(name = "ZFSJZ")
    public String getZfsjz() {
        return zfsjz;
    }

    public void setZfsjz(String zfsjz) {
        this.zfsjz = zfsjz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AjZfdata ajZfdata = (AjZfdata) o;

        if (id != null ? !id.equals(ajZfdata.id) : ajZfdata.id != null) return false;
        if (zflb != null ? !zflb.equals(ajZfdata.zflb) : ajZfdata.zflb != null) return false;
        if (zfbm != null ? !zfbm.equals(ajZfdata.zfbm) : ajZfdata.zfbm != null) return false;
        if (zfrs != null ? !zfrs.equals(ajZfdata.zfrs) : ajZfdata.zfrs != null) return false;
        if (zfry != null ? !zfry.equals(ajZfdata.zfry) : ajZfdata.zfry != null) return false;
        if (zfdd != null ? !zfdd.equals(ajZfdata.zfdd) : ajZfdata.zfdd != null) return false;
        if (qy != null ? !qy.equals(ajZfdata.qy) : ajZfdata.qy != null) return false;
        if (xz != null ? !xz.equals(ajZfdata.xz) : ajZfdata.xz != null) return false;
        if (fzr != null ? !fzr.equals(ajZfdata.fzr) : ajZfdata.fzr != null) return false;
        if (dh != null ? !dh.equals(ajZfdata.dh) : ajZfdata.dh != null) return false;
        if (sfyh != null ? !sfyh.equals(ajZfdata.sfyh) : ajZfdata.sfyh != null) return false;
        if (yhsm != null ? !yhsm.equals(ajZfdata.yhsm) : ajZfdata.yhsm != null) return false;
        if (zfjg != null ? !zfjg.equals(ajZfdata.zfjg) : ajZfdata.zfjg != null) return false;
        if (zfryxx != null ? !zfryxx.equals(ajZfdata.zfryxx) : ajZfdata.zfryxx != null) return false;
        if (zftu != null ? !zftu.equals(ajZfdata.zftu) : ajZfdata.zftu != null) return false;
        if (zfyx != null ? !zfyx.equals(ajZfdata.zfyx) : ajZfdata.zfyx != null) return false;
        if (zfsjz != null ? !zfsjz.equals(ajZfdata.zfsjz) : ajZfdata.zfsjz != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (zflb != null ? zflb.hashCode() : 0);
        result = 31 * result + (zfbm != null ? zfbm.hashCode() : 0);
        result = 31 * result + (zfrs != null ? zfrs.hashCode() : 0);
        result = 31 * result + (zfry != null ? zfry.hashCode() : 0);
        result = 31 * result + (zfdd != null ? zfdd.hashCode() : 0);
        result = 31 * result + (qy != null ? qy.hashCode() : 0);
        result = 31 * result + (xz != null ? xz.hashCode() : 0);
        result = 31 * result + (fzr != null ? fzr.hashCode() : 0);
        result = 31 * result + (dh != null ? dh.hashCode() : 0);
        result = 31 * result + (sfyh != null ? sfyh.hashCode() : 0);
        result = 31 * result + (yhsm != null ? yhsm.hashCode() : 0);
        result = 31 * result + (zfjg != null ? zfjg.hashCode() : 0);
        result = 31 * result + (zfryxx != null ? zfryxx.hashCode() : 0);
        result = 31 * result + (zftu != null ? zftu.hashCode() : 0);
        result = 31 * result + (zfyx != null ? zfyx.hashCode() : 0);
        result = 31 * result + (zfsjz != null ? zfsjz.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "data")
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
