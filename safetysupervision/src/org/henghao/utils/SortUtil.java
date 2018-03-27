package org.henghao.utils;

import java.util.List;

/**
 * 排序工具类
 * @author Long Tanglin
 * @since 2017-5-11 16:22:06
 */
public class SortUtil {

	/**
	 * 集合排序 升序
	 * 
	 * @param list
	 * @return
	 */
	public static List<Double> sortAsc(List<Double> list) {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size() - 1; i++) {
				for (int j = 1; j < list.size() - i; j++) {
					if (list.get(j - 1) > list.get(j)) {
						double temp = list.get(j - 1);
						list.set(j - 1, list.get(j));
						list.set(j, temp);
					}
				}
			}
		}
		return list;
	}
}
