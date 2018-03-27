package org.henghao.utils;

public class DateUtils {

	/**
	 * 将花费的时间转换成分钟数
	 * 
	 * @param doTime
	 *            花费的时间 数据库存储的时间格式形为“0 天 0 时 0 分”
	 * @return
	 */
	public static Integer getMinuteByDoTime(String doTime) {
		Integer millis = 0;
		if (doTime != null && !"".equals(doTime)) {
			Integer day = Integer.parseInt(doTime.substring(0, doTime.indexOf("天")).trim());
			Integer hour = Integer.parseInt(doTime.substring(doTime.indexOf("天") + 1, doTime.indexOf("时")).trim());
			Integer minute = Integer.parseInt(doTime.substring(doTime.indexOf("时") + 1, doTime.indexOf("分")).trim());
			millis = hour * 60 + minute + day * 24 * 60;
		}
		return millis;
	}

	
	public static void main(String[] args) {
//		System.out.println(getMinuteByDoTime("1 天 1 时 1 分"));
//		System.out.println(1 * 24 * 60 + 1 * 60 + 1);
	}
}
