package com.henghao.istration.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class GoodMode {
	
	//perfromanceService引用
	public static  List<String> checkGoodMode(Object ob) throws Exception{
		//Map<String,Object> map = new HashMap<String, Object>();
		List<String> names = new ArrayList<String>();
		List<String> val = new ArrayList<String>();
		Field[] fields = ob.getClass().getDeclaredFields();//获取i对象的所有字段
		for(Field field:fields){ 
			field.setAccessible(true);
			String name = field.getName();
			String value;
			try {
				value = field.get(ob).toString();
			} catch (NullPointerException e) {
				value=null;
			}
			names.add(name);
			//为满足UserApeServiceImpl里，重复名字不重复添加
			if(!val.contains(value)) {
				val.add(value);
			}
		}
		for(int i=val.size()-1;i>-1;i--) {
			if(null == val.get(i)) {
				val.remove(i);
				names.remove(i);
			}
		}
		//map.put("name", names);
		//map.put("value", val);
		val.remove(0);
		return val;
}


	
}