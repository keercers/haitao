package com.thinvent.basicpf.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.junit.Assert;
import org.junit.Test;

import com.thinvent.basicpf.model.Department;
import com.thinvent.library.exception.DataNotExistException;
import com.thinvent.library.exception.OptionException;
import com.thinvent.library.exception.ThinventBaseException;

public class DepartmentVerifyUtilTest {
	@Test
	public void testConstructorIsPrivate()
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Constructor<DepartmentVerifyUtil> constructor = DepartmentVerifyUtil.class.getDeclaredConstructor();
		Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	@Test(expected = DataNotExistException.class)
	public void verifyDeptTest() throws ThinventBaseException {
		Department department = new Department();
		DepartmentVerifyUtil.verifyDept(department);
		department = null;
		DepartmentVerifyUtil.verifyDept(department);
	}

	@Test(expected = OptionException.class)
	public void verifyDeleteDeptResultTest() throws ThinventBaseException {
		DepartmentVerifyUtil.verifyDeleteDeptResult(false, false);
		DepartmentVerifyUtil.verifyDeleteDeptResult(false, true);
	}
	
	@Test(expected = OptionException.class)
	public void verifyDeleteDeptResultTestTwo() throws ThinventBaseException {
		Boolean existsDept = true;
		Boolean existsPos = true;
		DepartmentVerifyUtil.verifyDeleteDeptResult(existsDept, existsPos);
	}
    
    @Test(expected = OptionException.class)
    public void verifyDeptName() throws ThinventBaseException {
        Department department = null;
        DepartmentVerifyUtil.verifyDeptName(department);
        Department dept = new Department();
        DepartmentVerifyUtil.verifyDeptName(dept);
    }
    
    @Test(expected = OptionException.class)
    public void verifyUpdateDept() throws ThinventBaseException {
        Department department = null;
        Department oldDepartment = null;
        DepartmentVerifyUtil.verifyUpdateDept(department, oldDepartment);
        Department dept = new Department();
        dept.setDepId("123");
        Department oldDept = new Department();
        oldDept.setDepId("321");
        DepartmentVerifyUtil.verifyUpdateDept(dept, oldDept);
    }
}
