package com.test.dimuthu;

import junit.framework.TestCase;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

@RunWith(PowerMockRunner.class)
@PrepareForTest({MyStaticClass.class})
public class MainImplementingClassTest extends TestCase {

	private MyStaticClass myStaticClass;

	private MainImplementingClass spyObject;

	public void setUp() throws Exception {
		spyObject = PowerMockito.spy(MainImplementingClass.getInstance());

	}

	/*
	 * Following is a sample of a class being mock. This can be done for Utility classes
	 */
	public void testCalculate() {
		PowerMockito.mockStatic(MyStaticClass.class);
		myStaticClass = PowerMockito.mock(MyStaticClass.class);
		PowerMockito.when(MyStaticClass.getInstance()).thenReturn(myStaticClass);
		
		PowerMockito.when(myStaticClass.returnNumber()).thenReturn(10);
		int returnVal = spyObject.calculate();
		assertEquals(500, returnVal);
	}
	
	/*
	 * Following is a sample of a private method being testing.
	 */
	public void testGetSecondNumber() throws Exception{
		int returnVal = Whitebox.invokeMethod(spyObject, "getSecondNumber", Mockito.eq((3)));
		assertEquals(4, returnVal);
	}
}
