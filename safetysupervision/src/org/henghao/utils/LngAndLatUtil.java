package org.henghao.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
/**
 * 通过百度地图API获取地址经纬度
 * @author Long Tanglin
 * @since 2017-5-8 09:24:15
 */
public class LngAndLatUtil {
	public static String loadJSON(String url) {
		StringBuilder json = new StringBuilder();
		try {
			URL oracle = new URL(url);
			URLConnection yc = oracle.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				json.append(inputLine);
			}
			in.close();
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		return json.toString();
	}

	public static JSONObject getLngAndLat(String address) {
		JSONObject jsonObject = new JSONObject();
		Map<String, Double> map = new HashMap<String, Double>();
		String url = "http://api.map.baidu.com/geocoder/v2/?address=" + address
				+ "&output=json&ak=5bSX6lqF6irleeYplWXQVTFYz4tgw2Is";
		String json = loadJSON(url);
		JSONObject obj = JSONObject.fromObject(json);
		if (obj.get("status").toString().equals("0")) {
			double lng = obj.getJSONObject("result").getJSONObject("location").getDouble("lng");
			double lat = obj.getJSONObject("result").getJSONObject("location").getDouble("lat");
			map.put("lng", lng);
			map.put("lat", lat);
			jsonObject.put("status", 1);
			jsonObject.put("lng", lng);
			jsonObject.put("lat", lat);
		} else {
			jsonObject.put("status", 0);
			jsonObject.put("msg", "未找到匹配的经纬度，请输入详细的地址。");
		}
		return jsonObject;
	}

//	public static void main(String[] args) throws Exception {
//		getLngAndLat("贵州省贵阳市乌当区六盘水路启林创客小镇");
//	}

}
