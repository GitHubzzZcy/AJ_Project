package com.henghao.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.henghao.entity.Result;

/**
 * @Description: URL获取json数据
 * @author zcy  
 * @date 2017年11月15日 下午2:50:08
 * @version 1.0
 */
public class Urljson {
	
	public static void redisToUrl(String url) {
			try {
				new URL(url).openStream();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	//Redis线程请求url readJsonRedisUrl
	public static String readJsonRedisUrl(String url) {
		InputStream is = null;
		try {
			is = new URL(url).openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			Result result = JSON.parseObject(jsonText, Result.class);
			Object data = result.getData();
			//只返回接口的data数据存入redis
			String jsonString = JSON.toJSONString(data);
			return jsonString;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    } finally {
	      is.close();
	    }
	  }
	  
	  private static String readAll(Reader rd) throws IOException {
		    StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = rd.read()) != -1) {
		      sb.append((char) cp);
		    }
		    return sb.toString();
		  }
	  
	  
	  //百度地图接口
	  public static JSONObject JsonUrl(String url) throws IOException, JSONException {
		    InputStream is = new URL(url).openStream();
		    try {
		      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		      String jsonText = read(rd);
		      //renderOption&&renderOption({"status":0,"result":{"location":{"lng":106.78312900941234,"lat":26.642458513705024},"precise":0,"confidence":25,"level":"道路"}})
		      //需要把前面的截掉
		      String substring = jsonText.substring(27, jsonText.length()-1);
		      JSONObject json = new JSONObject(substring);
		      return json;
		    } finally {
		      is.close();
		    }
		  }
		  private static String read(Reader rd) throws IOException {
			    StringBuilder sb = new StringBuilder();
			    int cp;
			    while ((cp = rd.read()) != -1) {
			      sb.append((char) cp);
			    }
			    return sb.toString();
			  }
}
