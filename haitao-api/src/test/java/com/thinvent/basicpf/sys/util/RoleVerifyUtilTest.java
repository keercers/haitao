package com.thinvent.basicpf.sys.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.junit.Assert;
import org.junit.Test;

import com.thinvent.library.exception.DataNotExistException;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.RoleVO;

public class RoleVerifyUtilTest {
   
	
    @Test
    public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<RoleVerifyUtil> constructor = RoleVerifyUtil.class.getDeclaredConstructor();
        Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }
	@Test(expected=DataNotExistException.class)
	public  void verifyRole() throws ThinventBaseException
	{
		RoleVO roleVO =new RoleVO();
		roleVO.setRoleId("8172");
		roleVO.setRoleName("小雨");
		RoleVerifyUtil.verifyRole(roleVO);
		RoleVO roleVO1= null;
		RoleVerifyUtil.verifyRole(roleVO1);
	}
	
	@Test(expected=ThinventBaseException.class)  
	public void verifyAddRoleResultTwo() throws ThinventBaseException {
		RoleVO roleVO1 = new RoleVO();
		RoleVerifyUtil.verifyAddRoleResult(roleVO1);
		roleVO1= null;
		RoleVerifyUtil.verifyAddRoleResult(roleVO1);
    }
	
	
	@Test(expected=ThinventBaseException.class) 
	public void verifyUpdateRoleResult() throws ThinventBaseException
	{
		RoleVO roleVO =new RoleVO();
		roleVO.setRoleId("8172");
		roleVO.setRoleName("小雨");
		roleVO.setId(-1);
		RoleVO roleVO1= null;
		RoleVerifyUtil.verifyUpdateRoleResult(roleVO, roleVO1);
	}
	@Test(expected=ThinventBaseException.class) 
	public void verifyUpdateRoleResultOne() throws ThinventBaseException
	{
		RoleVO roleVO = new RoleVO();
		RoleVO roleVO1= new RoleVO();
		RoleVerifyUtil.verifyUpdateRoleResult(roleVO, roleVO1);
		roleVO = null;
		roleVO1 = null;
		RoleVerifyUtil.verifyUpdateRoleResult(roleVO, roleVO1);
	}
	
}