package com.henghao.util;

import java.util.UUID;

/**
 * @author wzp
 * @description: 自定义UUID工具类
 * @create on 2017/12/1.
 */
public class UUidUtil {

    /**
     * 获取GS开头的32为UUID
     *
     * @time 2017-7-6
     * @return
     */
    public static String getGSUUid32() {

        String s = UUID.randomUUID().toString();// 用来生成数据库的主键id非常不错。。
        // 去掉“-”符号
        String str = s.substring(0, 8) + s.substring(9, 13)
                + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
        return "GS" + str.substring(2);
    }

    /**
     * 获取自定UUID，以传入的head变量开头，如果为空，默认以‘GS'开头。
     * 若head变量长度大于2，则以前面两个字符开头
     * @param head UUID的开头两个字符
     * @return
     */
    public static String getUuidFromHead32(String head){
        String s = UUID.randomUUID().toString();
        // 去掉“-”符号
        String str = s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
        // 字母大写
        str = str.toUpperCase();
        // head为空，默认使用GS开头（good soft）
        if (head == null || "".equals(head)) return "GS" + str.substring(2);
        // head长度超过2，截取前面两个字符
        if (head.length() > 2) head = head.substring(0,2);
        return head + str.substring(2);
    }
}
