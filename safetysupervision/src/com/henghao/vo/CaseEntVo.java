package com.henghao.vo;

/**
 * @author wzp
 * @description: 执法企业数据展示包装类
 * @create on 2017/12/6.
 */
public class CaseEntVo {

    // 执法数据ID
    private String ID;
    // 企业名称
    private String ent;
    // 企业纬度
    private String lat;
    // 企业经度
    private String lng;
    // 责令书中的规定时间
    private String data;

    /*
     * 如有需要，可再添加
     */

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getEnt() {
        return ent;
    }

    public void setEnt(String ent) {
        this.ent = ent;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CaseEntVo{" +
                "ID='" + ID + '\'' +
                ", ent='" + ent + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
