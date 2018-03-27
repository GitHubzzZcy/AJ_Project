package org.henghao.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

/***
 * 百度地图API接口 工具类
 * 
 * @author Long Tanglin
 * @since 2017-5-9 13:59:46
 */
public class BaiduUtil {
	
	/*public static Properties prop = null;
	static {
		prop = new Properties();
		try {
			String path = BaiduUtil.class.getClassLoader().getResource("").getPath();
			String absPath = new File(path).getParentFile().getParentFile().getParentFile().getAbsolutePath()
					+ File.separator + "config" + File.separator;
			path = absPath + "baidu.properties";
			InputStream in = new BufferedInputStream(new FileInputStream(path));
			prop.load(in); // 加载属性列表
			in.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
*/
	
	public static String loadJSON(String url) {
		StringBuilder json = new StringBuilder();
		try {
			URL oracle = new URL(url);
			URLConnection yc = oracle.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"utf-8"));
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

	/***
	 * 获取地址的经纬度
	 * 返回的json串中，status为1，说明成功获取。
	 * @param address
	 *            地址
	 * @return json 经纬度
	 */
	public static JSONObject getLngLat(String address) {
		JSONObject jsonObject = new JSONObject();
		if (address != null)
			address = address.trim();
		String url = "http://api.map.baidu.com/geocoder/v2/?address=" + address + "&output=json&ak="
				+ CommonConstant.AK;
		String json = null;
		try {
			json = loadJSON(url);
		} catch (Exception e1) {
			System.out.println("api.map.baidu.com--->"+e1.getMessage());
		}
		JSONObject obj;

		if(json != null){
			try {
				obj = JSONObject.fromObject(json);
				if (obj.get("status").toString().equals("0")) {
					double lng = obj.getJSONObject("result").getJSONObject("location").getDouble("lng");
					double lat = obj.getJSONObject("result").getJSONObject("location").getDouble("lat");
					jsonObject.put("status", 1);
					jsonObject.put("lng", lng);
					jsonObject.put("lat", lat);
				} else {
					jsonObject.put("status", 0);
					jsonObject.put("msg", "未找到匹配的经纬度，请输入详细的地址。");
				}
			} catch (Exception e) {
				System.out.println("数据"+json + "不是JSON格式，转JSON格式异常！");
			}
		}
		
		return jsonObject;
	}

	/***
	 * 获取地理位置
	 * 
	 * @param lat
	 *            纬度
	 * @param lng
	 *            经度
	 * @return JSON 地理位置
	 * @throws IOException
	 */
	public static JSONObject getLocation(String lat, String lng) throws IOException { 
		String location = lat + "," + lng;
		URL url = new URL("http://api.map.baidu.com/geocoder?ak="+CommonConstant.AK+"&location="+location+"&output=json");
		URLConnection connection = url.openConnection();
		/*
		 * 然后把连接设为输出模式。URLConnection通常作为输入来使用，比如下载一个Web页。
		 * 通过把URLConnection设为输出，你可以把数据向你个Web页传送。下面是如何做：
		 */
		connection.setDoOutput(true);
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
		out.flush();
		out.close();
		// 一旦发送成功，用以下方法就可以得到服务器的回应：
		String res;
		InputStream l_urlStream;
		l_urlStream = connection.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(l_urlStream, "UTF-8"));
		StringBuilder sb = new StringBuilder("");
		while ((res = in.readLine()) != null) {
			sb.append(res.trim());
		}
		String str = sb.toString();
		JSONObject jsonObject = new JSONObject();
		if (StringUtils.isNotEmpty(str)) {
			int addStart = str.indexOf("formatted_address\":");
			int addEnd = str.indexOf("\",\"business");
			if (addStart > 0 && addEnd > 0) {
				String address = str.substring(addStart + 20, addEnd);
				jsonObject.put("address", address);
				return jsonObject;
			}
		}
		return jsonObject;
	}

	/***
	 * 获取两个地理坐标的直线距离
	 * 
	 * @param Lat_A
	 *            起点纬度
	 * @param Lng_A
	 *            起点经度
	 * @param Lat_B
	 *            终点纬度
	 * @param Lng_B
	 *            终点经度
	 * @return 距离 单位为"千米" 3位小数 ###.000
	 */
	public static double getDistance(double Lat_A, double Lng_A, double Lat_B, double Lng_B) {
		double ra = 6378.140;
		double rb = 6356.755;
		double flatten = (ra - rb) / ra;
		double rad_lat_A = Math.toRadians(Lat_A);
		double rad_lng_A = Math.toRadians(Lng_A);
		double rad_lat_B = Math.toRadians(Lat_B);
		double rad_lng_B = Math.toRadians(Lng_B);
		double pA = Math.atan(rb / ra * Math.tan(rad_lat_A));
		double pB = Math.atan(rb / ra * Math.tan(rad_lat_B));
		double xx = Math
				.acos(Math.sin(pA) * Math.sin(pB) + Math.cos(pA) * Math.cos(pB) * Math.cos(rad_lng_A - rad_lng_B));
		double c1 = (Math.sin((xx) - xx) * (Math.sin(pA) + Math.sin(pB)) * 2 / Math.cos(xx / 2) * 2);
		double c2 = (Math.sin(xx) + xx) * (Math.sin(pA) - Math.sin(pB)) * 2 / Math.sin(xx / 2) * 2;
		double dr = flatten / 8 * (c1 - c2);
		double distance = ra * (xx + dr);
		DecimalFormat format = new DecimalFormat("###.000");
		distance = Double.parseDouble(format.format(distance));
		return distance;
	}
	
	
	/***
	 *  获取两地最短行程距离 交通方式为驾车
	 * @param Lat_A 起点纬度
	 * @param Lng_A 起点经度
	 * @param Lat_B 终点纬度
	 * @param Lng_B 终点经度
	 * @return 两地的距离和最短耗时 数据 JSON格式
	 */
	public static JSONObject getMinimumTravel(String Lat_A, String Lng_A, String Lat_B, String Lng_B){
		JSONObject jsonObject = new JSONObject();
		Lat_A = Lat_A.trim();
		Lng_A = Lng_A.trim();
		Lat_B = Lat_B.trim();
		Lng_B = Lng_B.trim();
		String lnglats = Lat_A+","+Lng_A;
		String lnglate = Lat_B+","+Lng_B;
		String url = "http://api.map.baidu.com/routematrix/v2/driving?tactics=12&origins="+lnglats+
				"&destinations="+lnglate+"&output=json&ak="+CommonConstant.AK;
		String loadJSON = loadJSON(url);
		JSONObject jsonData = JSONObject.fromObject(loadJSON);//格式化为JSON
		JSONObject json = (JSONObject) ((JSONArray)jsonData.get("result")).get(0);
		JSONObject distance = (JSONObject)json.get("distance");
		JSONObject duration = (JSONObject)json.get("duration");
		
		jsonObject.put("distance", distance.get("value"));
		jsonObject.put("duration", duration.get("text"));
		return jsonObject;
	} 

	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
//		JSONObject lngLat = getLngLat("贵州省贵阳市南明区文昌南路29号负一层");
//		System.out.println(lngLat);

//		String path = BaiduUtil.class.getClassLoader().getResource("").getPath();
//		System.out.println(new File(path).getParentFile().getPath());
//		System.out.println(path);
		
		
		
	}
}
