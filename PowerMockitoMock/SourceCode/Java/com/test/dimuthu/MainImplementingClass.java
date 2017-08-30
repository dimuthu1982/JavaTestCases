package com.test.dimuthu;

public class MainImplementingClass {

	private static MainImplementingClass myImplementingClass = new MainImplementingClass();
	
	private MainImplementingClass(){
		
	}
	
	public static MainImplementingClass getInstance(){
		return myImplementingClass;
	}
	
	public int calculate() {
		return MyStaticClass.getInstance().returnNumber() * getPrivateMethodNumber(1) * 10 + getTempMethod();
	}
	
	private int getPrivateMethodNumber(int number){
		return 4 + number;
	}
	
	public int getTempMethod(){
		return 1;
	}
	
}
