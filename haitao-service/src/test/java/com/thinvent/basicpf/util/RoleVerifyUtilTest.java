package com.thinvent.basicpf.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.junit.Assert;
import org.junit.Test;

import com.thinvent.basicpf.model.Role;
import com.thinvent.library.exception.DataNotExistException;
import com.thinvent.library.exception.OptionException;
import com.thinvent.library.exception.ThinventBaseException;
public class RoleVerifyUtilTest {
	
	@Test
    public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<RoleVerifyUtil> constructor = RoleVerifyUtil.class.getDeclaredConstructor();
        Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }
	
	@Test(expected=DataNotExistException.class)
	public void verifyRoleTest() throws ThinventBaseException {
		
		Role role= new Role();
		role.setRoleId("567");
		RoleVerifyUtil.verifyrole(role);
		RoleVerifyUtil.verifyrole(null);
	}

	
	@Test(expected = OptionException.class)
	public void verifyAddExistRole() throws ThinventBaseException {
		RoleVerifyUtil.verifyAddExistRole(null);
		Role role = new Role();
		RoleVerifyUtil.verifyAddExistRole(role);
	}
	
	@Test(expected = OptionException.class)
	public void verifyUpdateExistRole() throws ThinventBaseException {
		Role oldRole = new Role();
		oldRole.setRoleId("test");
		RoleVerifyUtil.verifyUpdateExistRole(null, oldRole);
		
		Role role = new Role();
		role.setRoleId("test");
		RoleVerifyUtil.verifyUpdateExistRole(role, oldRole);
		
		role.setRoleId("test1");
		RoleVerifyUtil.verifyUpdateExistRole(role, oldRole);

	}
}
