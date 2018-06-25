package com.thinvent.basicpf.sys.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.thinvent.library.exception.OptionException;
import com.thinvent.library.exception.ThinventBaseException;

public class CompanyVertifyUtilTest {

	@Test
	public void testCompanyVerifyUtil() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Constructor<CompanyVerifyUtil> constructor = CompanyVerifyUtil.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		constructor.newInstance();
		constructor.setAccessible(false);
	}
	@Test(expected = OptionException.class)
	public void testVerifyUpdate() throws ThinventBaseException{
		String stateSuccess = "200";
		CompanyVerifyUtil.verifyUpdate(stateSuccess);
		String state = "300";
		CompanyVerifyUtil.verifyUpdate(state);
	}
	@Test(expected = OptionException.class)
	public void testVerifyObject() throws ThinventBaseException{
		Object object = new Object();
		CompanyVerifyUtil.verifyObject(object);
		Object objectNull = null;
		CompanyVerifyUtil.verifyObject(objectNull);
	}

}
