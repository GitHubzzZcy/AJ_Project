package com.henghao.demo1;

public class DilLock extends Thread{

	private boolean flag;
	
	public DilLock(boolean flag) {
		this.flag = flag;
	}
	@Override
	public void run() {
		if (flag) {
			synchronized (LockObject.objA) {
				System.out.println("if objA");
				synchronized(LockObject.objB) {
					System.out.println("if objB");
				}
			} 
		}else{
			synchronized (LockObject.objB) {
				System.out.println("else objB");
				synchronized(LockObject.objA) {
					System.out.println("else objA");
				}
			}
		}
	}
}
