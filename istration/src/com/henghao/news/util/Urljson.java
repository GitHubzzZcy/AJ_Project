package com.henghao.news.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;

import com.alibaba.fastjson.JSON;
import org.json.JSONObject;

/**
 * 通过url获取json数据
 * @author 周承耀
 *
 */
public class Urljson {

	  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    //  JSONObject object = json.getJSONObject("data");
			//object.get("chuqingli");
	    } finally {
	      is.close();
	     // System.out.println("同时 从这里也能看出 即便return了，仍然会执行finally的！");
	    }
	  }
	  public static com.alibaba.fastjson.JSONObject readFastJsonFromUrl(String url) throws IOException, JSONException {
		    InputStream is = new URL(url).openStream();
		    try {
		      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		      String jsonText = readAll(rd);
		      com.alibaba.fastjson.JSONObject json = JSON.parseObject(jsonText);
		      //JSONObject json = new JSONObject(jsonText);
		      return json;
		    //  JSONObject object = json.getJSONObject("data");
				//object.get("chuqingli");
		    } finally {
		      is.close();
		     // System.out.println("同时 从这里也能看出 即便return了，仍然会执行finally的！");
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
