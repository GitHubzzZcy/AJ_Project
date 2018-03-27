package com.henghao.util;
/**
 * @explan 距离工具类
 * @author 王章鹏
 * @time 2017-6-15 下午5:31:21
 */
public class DistanceUtil {

	private static final Double PI = Math.PI;  
	  
    private static final Double PK = 180 / PI; 
    
    /**
     * 计算两点(经纬度)间的实际距离，返回单位 ：m(米)
     * 创建时间：2017-6-15 下午5:32:05
     * @param lat_a
     * @param lng_a
     * @param lat_b
     * @param lng_b
     * @return
     */
    public static double getDistanceFromTwoPoints(double lat_a, double lng_a, double lat_b, double lng_b) {  
        double t1 = Math.cos(lat_a / PK) * Math.cos(lng_a / PK) * Math.cos(lat_b / PK) * Math.cos(lng_b / PK);  
        double t2 = Math.cos(lat_a / PK) * Math.sin(lng_a / PK) * Math.cos(lat_b / PK) * Math.sin(lng_b / PK);  
        double t3 = Math.sin(lat_a / PK) * Math.sin(lat_b / PK);  
        double tt = Math.acos(t1 + t2 + t3);  
        return 6366000 * tt;  
    }
}
