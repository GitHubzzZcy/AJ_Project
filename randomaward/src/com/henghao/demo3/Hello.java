package com.henghao.demo3;
class A {
 
    static {
        System.out.print("1");
    }
 
    public A() {
        System.out.print("2");
    }
    public void ta() {
    	System.out.print("3");
    }
}
 
class B extends A{
 
    static {
        System.out.print("a");
    }
 
    public B() {
        System.out.print("b");
    }
    public void ta() {
    	System.out.print("c");
    }
}
 
public class Hello {
 
    public static void main(String[] args) {
        A ab = new B();
        ab.ta();
       ab = new B(); //1a2bc2b
    }
 
}