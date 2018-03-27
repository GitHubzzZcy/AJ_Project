package com.henghao.util;




import java.security.MessageDigest;
/**
 * md5加密
 * @author 
 */
public class Md5Test {
     
     public static String toMD5(String token) {
    	 String tokens="";
          try {
               //生成实现指定摘要算法的 MessageDigest 对象。
               MessageDigest md = MessageDigest.getInstance("MD5");    
               //使用指定的字节数组更新摘要。
               md.update(token.getBytes());
               //通过执行诸如填充之类的最终操作完成哈希计算。
               byte b[] = md.digest();
               //生成具体的md5密码到buf数组
               
               int i;
               StringBuffer buf = new StringBuffer("");
               for (int offset = 0; offset < b.length; offset++) {
                    i = b[offset];
                    if (i < 0)
                        i += 256;
                    if (i < 16)
                        buf.append("0");
                    buf.append(Integer.toHexString(i));
               }
               tokens=buf.toString();
//               System.out.println("32位: " + buf.toString());// 32位的加密
//               System.out.println("16位: " + buf.toString().substring(8, 24));// 16位的加密，其实就是32位加密后的截取
          } 
          catch (Exception e) {
              e.printStackTrace();
          }
		return tokens;
     }
}













