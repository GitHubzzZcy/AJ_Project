package com.henghao.entity.wzp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Entity;
import java.sql.Timestamp;
/**
 * @author wzp
 * @description 执法模块中，企业的经纬度保存，实体类
 * @create on 2017/12/21.
 */
@Entity
@Table(name = "sa_law_ent")
public class LawEntLatLng {

    @Id
    @Column(length = 32)
    private String lawID;   // id
    @Column(unique = true,nullable = false)  // 设置企业名称字段 唯一
    private String lawEnt;  // 企业名称
    @Column(length = 32,nullable = false)
    private String entLng;  // 企业经度
    @Column(length = 32,nullable = false)
    private String entLat;  // 企业纬度
    private Timestamp saveTime;  // 保存时间


    /**
     * 空构造
     */
    public LawEntLatLng() {
    }

    /**
     * 全参数构造
     */
    public LawEntLatLng(String lawID, String lawEnt, String entLng, String entLat, Timestamp saveTime) {
        this.lawID = lawID;
        this.lawEnt = lawEnt;
        this.entLng = entLng;
        this.entLat = entLat;
        this.saveTime = saveTime;
    }

    public String getLawID() {
        return lawID;
    }

    public void setLawID(String lawID) {
        this.lawID = lawID;
    }

    public String getLawEnt() {
        return lawEnt;
    }

    public void setLawEnt(String lawEnt) {
        this.lawEnt = lawEnt;
    }

    public String getEntLng() {
        return entLng;
    }

    public void setEntLng(String entLng) {
        this.entLng = entLng;
    }

    public String getEntLat() {
        return entLat;
    }

    public void setEntLat(String entLat) {
        this.entLat = entLat;
    }

    public Timestamp getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(Timestamp saveTime) {
        this.saveTime = saveTime;
    }


}
