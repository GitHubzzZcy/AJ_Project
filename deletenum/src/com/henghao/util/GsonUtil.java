package com.henghao.util;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.reflect.TypeToken;
/**
 * 
 * @类功能说明：  将字符串解析成json数据去除null
 * @类修改者：  
 * @修改日期：  
 * @修改说明：  
 * @公司名称：  
 * @作者：陈文旭  
 * @创建时间：Mar 8, 2017 3:27:13 PM  
 * @版本：V1.0
 */
public class GsonUtil {
	
	// 将Json数据解析成相应的映射对象
	     public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
	              Gson gson = new Gson();
	            T result = gson.fromJson(jsonData, type);
                return result;
                 }
	  // 将Json数组解析成相应的映射对象列表
	     public static <T> ArrayList<T> jsonToList(String json, Class<T> classOfT) {
	    		Type type = new TypeToken<ArrayList<JsonObject>>(){}.getType();
	    		ArrayList<JsonObject> jsonObjs = new Gson().fromJson(json, type);
	    		ArrayList<T> listOfT = new ArrayList<T>();
	    		for (JsonObject jsonObj : jsonObjs) {
	    		    listOfT.add(new Gson().fromJson(jsonObj, classOfT));
	    		}
	    		return listOfT;
	     }
	     /**
	      * 调用接口
	      * @param url
	      * @return
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

}
