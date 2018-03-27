package com.henghao.entity.wzp;

import javax.persistence.*;

/**
 * @author wzp
 * @description:
 * @create on 2017/12/5.
 */
@Entity
@Table(name = "aj_sjz", schema = "hz", catalog = "")
public class AjSjz {
    private String id;
    private String parentid;
    private String sm;
    private String time;
    private String clzt;
    private String fj;
    private String xdwssj;

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
    @Column(name = "PARENTID")
    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    @Basic
    @Column(name = "SM")
    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    @Basic
    @Column(name = "TIME")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Basic
    @Column(name = "CLZT")
    public String getClzt() {
        return clzt;
    }

    public void setClzt(String clzt) {
        this.clzt = clzt;
    }

    @Basic
    @Column(name = "FJ")
    public String getFj() {
        return fj;
    }

    public void setFj(String fj) {
        this.fj = fj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AjSjz ajSjz = (AjSjz) o;

        if (id != null ? !id.equals(ajSjz.id) : ajSjz.id != null) return false;
        if (parentid != null ? !parentid.equals(ajSjz.parentid) : ajSjz.parentid != null) return false;
        if (sm != null ? !sm.equals(ajSjz.sm) : ajSjz.sm != null) return false;
        if (time != null ? !time.equals(ajSjz.time) : ajSjz.time != null) return false;
        if (clzt != null ? !clzt.equals(ajSjz.clzt) : ajSjz.clzt != null) return false;
        if (fj != null ? !fj.equals(ajSjz.fj) : ajSjz.fj != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (parentid != null ? parentid.hashCode() : 0);
        result = 31 * result + (sm != null ? sm.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (clzt != null ? clzt.hashCode() : 0);
        result = 31 * result + (fj != null ? fj.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "XDWSSJ")
    public String getXdwssj() {
        return xdwssj;
    }

    public void setXdwssj(String xdwssj) {
        this.xdwssj = xdwssj;
    }
}
