package com.henghao.demo3;


public class ListDemo {

	public static void main(String[] args) {
		int[] list = {9,5,6,3,1,6,8,7,2};
		for(int i=1; i<list.length; i++) {
			for(int j=0; j<list.length-i; j++) {
				if(list[j] > list[j+1]) {
					int temp = list[j];
					list[j] = list[j+1];
					list[j+1] = temp;
				}
			}
		}
		for (int i : list) {
			System.out.println(i);
		}
	}
}
