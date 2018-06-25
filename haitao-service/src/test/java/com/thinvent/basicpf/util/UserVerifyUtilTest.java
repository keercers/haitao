package com.thinvent.basicpf.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.thinvent.basicpf.model.User;
import com.thinvent.basicpf.model.UserMes;
import com.thinvent.basicpf.model.UserRole;
import com.thinvent.library.exception.DataNotExistException;
import com.thinvent.library.exception.ThinventBaseException;

public class UserVerifyUtilTest {
	
	@Test
    public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<UserVerifyUtil> constructor = UserVerifyUtil.class.getDeclaredConstructor();
        Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }
	
	@Test(expected=DataNotExistException.class)
	public void verifyUserTest() throws ThinventBaseException {
		User user = new User();
		UserVerifyUtil.verifyUser(user);
		user = null;
		UserVerifyUtil.verifyUser(user);
	}
	
	@Test(expected=DataNotExistException.class)
	public void verifyUserMesTest() throws ThinventBaseException  {
		UserMes userMes = new UserMes();
		UserVerifyUtil.verifyUserMes(userMes);
		userMes = null;
		UserVerifyUtil.verifyUserMes(userMes);
	}
	
	@Test(expected=DataNotExistException.class)
	public void verifyUserRoleTest() throws ThinventBaseException  {
		UserRole userRole = new UserRole();
		UserVerifyUtil.verifyUserRole(userRole);
		userRole = null;
		UserVerifyUtil.verifyUserRole(userRole);
	}
	
	@Test(expected=ThinventBaseException.class)
	public void verifyUserRoleListTest() throws ThinventBaseException  {
		List<UserRole> userRoleList = Lists.newArrayList();
		UserVerifyUtil.verifyUserRoleList(userRoleList);
		userRoleList = null;
		UserVerifyUtil.verifyUserRoleList(userRoleList);
	}
	
}
