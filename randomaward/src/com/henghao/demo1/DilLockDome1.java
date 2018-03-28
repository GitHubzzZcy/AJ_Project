package com.henghao.demo1;

public class DilLockDome1 {

	public static void main(String[] args) {
		DilLock  lock1 = new DilLock(true);
		DilLock  lock2 = new DilLock(false);
		lock1.start();
		lock2.start();
	}
}
