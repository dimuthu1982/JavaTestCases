package com.test.dimuthu;

public class MyStaticClass {

	private static MyStaticClass myStaticClass = new MyStaticClass();
	
	private MyStaticClass(){
		
	}
	
	public static MyStaticClass getInstance(){
		return myStaticClass;
	}
	
	public int returnNumber(){
		return 1;
	}
}
