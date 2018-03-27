package com.henghao.test;

import com.henghao.util.DateUtils;
import com.henghao.util.GsonUtil;
import com.henghao.util.WZPCaseUtil;
import com.henghao.vo.CaseUserVo;
import net.sf.json.JSONObject;
import org.henghao.utils.BaiduUtil;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

/**
 * @author wzp
 * @description: 测试类
 * @create on 2017/12/5.
 */
public class Test1 {

    public static void main(String[] args) {
//        dateTest();
        // 经纬度获取
//        getLatLon();
        // 访问url接口
//        urlTest();
        // 获取当前月第一天
//        month3Test();
        // 时间戳测试
        TimestampTest();

    }

    /**
     * java.sql.tomeStamp测试
     */
    private static void TimestampTest() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        System.out.println(ts.toString());
    }

    /**
     * 获取当前月第一天测试
     */
    private static void month3Test() {
        String month3 = DateUtils.month3();
        System.out.println(month3);
        String currentDate = DateUtils.getCurrentDate("yyyy-MM-dd");
        System.out.println(currentDate);

    }

    /**
     * 访问url，返回json测试
     */
    private static void urlTest() {

        String url = "http://222.85.156.33:8082/istration/userGrade/querySaveRedisGrade?start=2017-11-01&end=2017-11-05&name=%E9%82%93%E5%8F%91%E6%9D%83";
        String json = GsonUtil.loadJSON(url);
        System.out.println(json);
        CaseUserVo caseUserVo = WZPCaseUtil.userScroFromJson(new CaseUserVo(), json);
        System.out.println(caseUserVo);
    }

    /**
     * 获取经纬度测试
     */
    public static void getLatLon() {
        JSONObject lngLat = BaiduUtil.getLngLat("重庆富侨保健服务有限公司贵阳分公司");
        System.out.println(lngLat);

    }



    private static void dateTest() {

        String currentDate = DateUtils.getCurrentDate("yyyy-MM");
        System.out.println(currentDate);

        String start = "2017-12-05 21:08:09";
        String end = "2017-10-05 21:08:09";

        Date date1 = null;
        try {
            date1 = DateUtils.parseDateFromString(start);
            Date date_now = DateUtils.parseDateFromString(end);
            // 计算两个日期之间相差的天数
            int subDays = DateUtils.getSubDays(date1, date_now);
            System.out.println(subDays);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }


    /**
     * 删除数组指定元素
     * @param arr 原数组
     * @param num 要删除的数
     * @return 删除后的数组
     */
    public static int[] remove(int[] arr, int num) {
        //统计num的个数
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                count++;
            }
        }

        //创建一个新的数组
        int[] newArr = new int[arr.length - count];

        int index = 0; //新数组使用的索引值
        //把非的数据存储到新数组中。
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != num) {
                newArr[index] = arr[i];
                index++;
            }
        }
        return newArr;
    }


}
