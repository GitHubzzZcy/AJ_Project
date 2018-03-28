package com.henghao.demo3;


public class IntegerTest {

	public static void main(String[] args) {
		int list[] = {4,9,5,2,3,8,6,7,1};
		int tmp = 0;
		for(int i=1; i<list.length; i++) {
			for(int j=0; j<list.length-i; j++) {
				if(list[j] > list[j+1]) {
					tmp = list[j];
					list[j] = list[j+1];
					list[j+1] = tmp;
				}
			}
		}
		for (int i : list) {
			System.out.print(i+"-");
		}
		
	}
}
