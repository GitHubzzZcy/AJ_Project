package com.henghao.util;

import com.henghao.vo.CaseUserVo;
import com.henghao.vo.SelectUserVo;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletResponse;

/**
 * @author wzp
 * @description: 执法案件相关业务帮助类
 * @create on 2017/12/6.
 */
public class WZPCaseUtil {


    /**
     * 完善CaseEntVo中企业的经纬度数据
     * 传入的list中，CaseEntVo的ID和ent属性值不能为空。
     * 因为这个帮助方法是根据ent（企业名称）查询出这个企业的经纬度，
     * 并将经纬度信息设置入CaseEntVo中的
     *
     * @param list 只有ID和ent（企业名字）的集合List<CaseEntVo>
     * @return 更新了经纬度数据之后的集合List<CaseEntVo>
     *//*
    public static List<CaseEntVo> updateCaseEntVoLatLng(List<CaseEntVo> list) {
        // 传入集合非空
        if (list == null || list.size() < 1)
            return list;
        // 将信息放入包装类中
		for (CaseEntVo caseEntVo : list) {
			if (caseEntVo != null) {
				// 企业名称
				String entName = caseEntVo.getEnt();
				// 根据企业名称查询经纬度
				JSONObject lngLat = BaiduUtil.getLngLat(entName);
				if (lngLat != null) {
					// 查询返回状态码
					Object res = lngLat.get("status");
					if (res != null) {
						String status = lngLat.get("status").toString();
						if ("1".equals(status)) {
							// 查询成功
							caseEntVo.setLat(lngLat.get("lat").toString()); // 经度
							caseEntVo.setLng(lngLat.get("lng").toString()); // 纬度
						}
					}
				}
			}
		}
        return list;
    }*/






    /**
     * 解决跨域
     *
     */
    public static synchronized HttpServletResponse cross(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        response.addHeader("Access-Control-Max-Age", "1800");//30 min
        return response;
    }

    /**
     * 解析json，将诚信分数封装进CaseUserVo中
     * @param userVo CaseUserVo
     * @param json 诚信接口返回的诚信分数json串
     * @return CaseUserVo
     */
    public static CaseUserVo userScroFromJson(CaseUserVo userVo,String json){

        if (json == null || "".equals(json))
            return userVo;
        JSONObject obj = JSONObject.fromObject(json);
        Object status = obj.get("status");
        // 返回status为0，说明成功返回
        if (status != null && "0".equals(status.toString())){
            JSONObject data = (JSONObject) obj.get("data");
            if (data != null){
                userVo.setGrade_de(parseObjToDouble(data.get("de_grade")));    // 德
                userVo.setGrade_ji(parseObjToDouble(data.get("ji_grade")));    // 绩
                userVo.setGrade_neng(parseObjToDouble(data.get("neng_grade")));  // 能
                userVo.setGrade_lian(parseObjToDouble(data.get("lian_grade")));  // 廉
                userVo.setGrade_qin(parseObjToDouble(data.get("qin_grade")));   // 勤
            }
        }
        return userVo;
    }

    /**
     * 将 Object 转为 Double
     * @param obj obj
     * @return Double
     */
    public static Double parseObjToDouble(Object obj){
        if (obj == null)
            return 0.0;
        Double rs;
        try {
            rs = Double.parseDouble(obj.toString());
        }catch (Exception e){
            rs = 0.0;
        }
        return rs;
    }

    /**
     * 将 Object 转为 int
     * @param obj obj
     * @return Double
     */
    public static int parseObjToInt(Object obj){
        if (obj == null)
            return 0;
        int rs;
        try {
            rs = Integer.parseInt(obj.toString());
        }catch (Exception e){
            rs = 0;
        }
        return rs;
    }

    /**
     * 解析json，SelectUserVo
     * @param json 诚信接口返回的诚信分数json串
     * @return CaseUserVo
     */
    public static SelectUserVo selectVoFromJson(String json) {
        SelectUserVo userVo = new SelectUserVo(0,0);
        if (json == null || "".equals(json))
            return userVo;
        JSONObject obj = JSONObject.fromObject(json);
        Object status = obj.get("status");
        // 返回status为0，说明成功返回
        if (status != null && "0".equals(status.toString())){
            JSONObject data = (JSONObject) obj.get("data");
            if (data != null){
                userVo.setGRDB(parseObjToInt(data.get("gerendaiban_count")));    // 个人代办
                userVo.setKYSY(parseObjToInt(data.get("keyueshiyi_count")));    // 可阅事宜
            }
        }
        return userVo;
    }


//    /**
//     * 获取执法案件中的SQL语句,查询ID和企业名称(SELECT ID,QY FROM table)
//     * @param type 类型
//     * @return SQL语句
//     */
//    public static StringBuffer getCaseIDAndQYSQL(String type){
//        // SQL语句
//        StringBuffer sb = new StringBuffer("SELECT ID,QY AS ent FROM aj_zfdata ");
//        // type为空时，查询所有
//        if ("".equals(type) || type == null)
//            return sb;
//        // 查询已办案
//        if ("closure".equals(type))
//            sb.append(" WHERE ZFJG='未整改' OR ZFJG='已整改' ");
//        // 查询正办案件
//        else if ("inforce".equals(type))
//            sb.append(" WHERE ZFJG='整改中' ");
//        sb.append(" AND ");
//        return sb;
//    }

}
