package com.henghao.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Date;



/**
 * 微信上传图片下载到服务器持久化
 * @author 周承耀
 *
 */
public class Loadjpg {

	private static int index = 0;
	public static String loadjpg(String access_token, String serverId, String filePath) throws Exception {
		String path = null;
		DataInputStream dataInputStream = null;
		FileOutputStream fileOutputStream = null;
		try{
			String link = "https://api.weixin.qq.com/cgi-bin/media/get?access_token="+access_token+"&media_id="+serverId;
			URL url = null;
			String date = DateUtils.getFormatedDate(new Date(), "yyyyMMdd-HHmmss");
			url = new java.net.URL(link);
			dataInputStream = new DataInputStream(url.openStream()); 
			File file = new File(filePath+"/"+date+"-"+index+".jpg");
			fileOutputStream = new FileOutputStream(file);  
			 byte[] buffer = new byte[1024];  
			int b;
			while((b = dataInputStream.read(buffer)) != -1) {
				fileOutputStream.write(buffer, 0, b);  
			}
			path = filePath+"/"+date+"-"+index+".jpg";
			index++;
		}finally{
			if(dataInputStream != null) {
				dataInputStream.close();
			}if(fileOutputStream != null) {
				fileOutputStream.close();
			}
		}
		
		return path;   
	}
	
}
