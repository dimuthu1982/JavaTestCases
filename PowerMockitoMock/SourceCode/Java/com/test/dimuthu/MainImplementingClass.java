package com.test.dimuthu;

public class MainImplementingClass {

	private static MainImplementingClass myImplementingClass = new MainImplementingClass();
	
	private MainImplementingClass(){
		
	}
	
	public static MainImplementingClass getInstance(){
		return myImplementingClass;
	}
	
	public int calculate() {
		return MyStaticClass.getInstance().returnNumber() * getSecondNumber(1) * 10;
	}
	
	private int getSecondNumber(int number){
		return 4 + number;
	}
	
}
