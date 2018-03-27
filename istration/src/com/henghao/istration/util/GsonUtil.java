package com.henghao.istration.util;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
/**
 * 
 * @类功能说明：  将字符串解析成json数据去除null
 * @类修改�?�? 
 * @修改日期�? 
 * @修改说明�? 
 * @公司名称�? 
 * @作�?：陈文旭  
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

}
