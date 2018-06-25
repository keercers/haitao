package com.thinvent.basicpf.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.thinvent.basicpf.model.Company;
import com.thinvent.library.exception.OptionException;
import com.thinvent.library.exception.ThinventBaseException;

public class CompanyVertifyUtilTest {

	@Test
	public void testCompanyVerifyUtil() throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor<CompanyVerifyUtil> constructor = CompanyVerifyUtil.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		constructor.newInstance();
		constructor.setAccessible(false);
	}

	@Test(expected = OptionException.class)
	public void testVerifyUpdate() throws ThinventBaseException {
		String stateSuccess = "200";
		CompanyVerifyUtil.verifyUpdate(stateSuccess);
		String state = "300";
		CompanyVerifyUtil.verifyUpdate(state);
	}

	@Test(expected = OptionException.class)
	public void testVerifyObject() throws ThinventBaseException {
		Object object = new Object();
		CompanyVerifyUtil.verifyObject(object);
		Object objectNull = null;
		CompanyVerifyUtil.verifyObject(objectNull);
	}

	@Test(expected = OptionException.class)
	public void testVerifyDeleteComResult() throws ThinventBaseException {
		CompanyVerifyUtil.verifyDeleteComResult(false);
		CompanyVerifyUtil.verifyDeleteComResult(true);
	}
	
	@Test(expected = OptionException.class)
	public void verifyAddExistComName() throws ThinventBaseException {
		CompanyVerifyUtil.verifyAddExistComName(null);
		Company company = new Company();
		CompanyVerifyUtil.verifyAddExistComName(company);
	}
	
	@Test(expected = OptionException.class)
	public void verifySaveCompany() throws ThinventBaseException {
		CompanyVerifyUtil.verifySaveCompany(false);
		CompanyVerifyUtil.verifySaveCompany(true);
	}
	
	@Test(expected = OptionException.class)
	public void verifyUpdateExistComName() throws ThinventBaseException {
		Company oldCompany = new Company();
		oldCompany.setComId("test");
		CompanyVerifyUtil.verifyUpdateExistComName(null, oldCompany);
		
		Company company = new Company();
		company.setComId("test");
		CompanyVerifyUtil.verifyUpdateExistComName(company, oldCompany);
		
		company.setComId("test1");
		CompanyVerifyUtil.verifyUpdateExistComName(company, oldCompany);
	}
	
	@Test(expected = OptionException.class)
	public void verifyDelExistComChild() throws ThinventBaseException {
		
		CompanyVerifyUtil.verifyDelExistComChild(null);
		
		Company company = new Company();
		CompanyVerifyUtil.verifyDelExistComChild(company);
	}

}
