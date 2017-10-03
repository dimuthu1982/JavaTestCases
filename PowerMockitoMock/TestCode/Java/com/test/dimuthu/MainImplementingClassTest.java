package com.test.dimuthu;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import junit.framework.TestCase;

@RunWith(PowerMockRunner.class)
@PrepareForTest({MyStaticClass.class,MainImplementingClass.class, ExternalClass.class})
public class MainImplementingClassTest extends TestCase {

	private MyStaticClass myStaticClass;

	private MainImplementingClass spyObject;

	private ExternalClass externalClass = new ExternalClass(10);

	public void setUp() throws Exception {
		spyObject = PowerMockito.spy(MainImplementingClass.getInstance());

	}

	/*
	 * Following is a sample of a class being mock. This can be done for Utility classes
	 */
	public void testCalculate() {
		mockStatic(MyStaticClass.class);
		myStaticClass = mock(MyStaticClass.class);

		when(MyStaticClass.getInstance()).thenReturn(myStaticClass);
		when(myStaticClass.returnNumber()).thenReturn(10);

		int returnVal = spyObject.calculate();
		assertEquals(501, returnVal);
	}

	/*
	 * Following is a sample of a private method being testing.
	 */
	public void testGetSecondNumber() throws Exception{
		int returnVal = Whitebox.invokeMethod(spyObject, "getPrivateMethodNumber", Mockito.eq((3)));
		assertEquals(4, returnVal);
	}

	/*
	 * Following is a sample of private method being mock
	 */
	public void testCalculate_MockPrivateMethod() throws Exception{
		PowerMockito.doReturn(30).when(spyObject, "getPrivateMethodNumber", Mockito.eq((1)));
		int returnVal = spyObject.calculate();
		assertEquals(301, returnVal);
	}

	/*
	 * Following is a sample of suppress a method called MainImplementingClass.getTempMethod()
	 */
	@Test(expected = NullPointerException.class)
	public void testCalculate_ExceptionMethod() throws Exception{
		PowerMockito.suppress(PowerMockito.method(MainImplementingClass.class, "getTempMethod"));
		PowerMockito.doThrow(new NullPointerException("Null pointer Occured in getTempMethod(")).when(spyObject).getTempMethod();

		spyObject.calculate();
	}

	/*
	 * Following sample has mocked the constructor creation on ExternalClass. With "whenNew" it returns an instance of externalClass when an instance is created with withArguments(50)
	 */
	@Test
	public void testCarlulateByExternalClass() throws Exception{
		PowerMockito.whenNew(ExternalClass.class).withArguments(50).thenReturn(externalClass);

		int returnVal = spyObject.calculateByExternalClass(50);

		assertEquals(1000, returnVal);
	}

}
