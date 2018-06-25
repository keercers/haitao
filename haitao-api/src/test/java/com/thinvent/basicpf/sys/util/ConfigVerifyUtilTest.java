package com.thinvent.basicpf.sys.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thinvent.library.Constants;
import com.thinvent.library.exception.DataNotExistException;
import com.thinvent.library.exception.OptionException;
import com.thinvent.library.vo.ConfigVO;

@RunWith(SpringJUnit4ClassRunner.class)
public class ConfigVerifyUtilTest {
	
	ConfigVO configVO = new ConfigVO();
	
	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Constructor<ConfigVerifyUtil> constructor = ConfigVerifyUtil.class.getDeclaredConstructor();
		Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}
	
	@Test(expected = DataNotExistException.class)
	public void verifyConf() throws Exception {
		ConfigVerifyUtil.verifyConf(configVO);
		Assert.assertTrue("configVO为空", configVO != null);
		configVO = null;
		ConfigVerifyUtil.verifyConf(configVO);
		Assert.assertTrue("configVO为空", configVO != null);
	}
	
	@Test(expected = DataNotExistException.class)
	public void verifyAddConfResult() throws Exception {
		ConfigVerifyUtil.verifyAddConfResult(configVO);
		Assert.assertTrue("configVO为空", configVO != null);
		configVO = null;
		ConfigVerifyUtil.verifyAddConfResult(configVO);
		Assert.assertTrue("configVO为空", configVO != null);
	}
	
	@Test(expected = DataNotExistException.class)
	public void verifyUpdateConfResult() throws Exception {
		ConfigVO newConfigVo = new ConfigVO();
		ConfigVO oldConfigVo = new ConfigVO();
		ConfigVerifyUtil.verifyUpdateConfResult(newConfigVo, oldConfigVo);
		Assert.assertTrue("newConfigVo&&oldConfigVo为空", newConfigVo != null && oldConfigVo!= null);
		oldConfigVo = null;
		newConfigVo = null;
		ConfigVerifyUtil.verifyUpdateConfResult(newConfigVo, oldConfigVo);
		Assert.assertTrue("newConfigVo&&oldConfigVo为空", newConfigVo != null && oldConfigVo!= null);
	}
	
	@Test(expected = DataNotExistException.class)
	public void verifyUpdateConfResulta() throws Exception {
		ConfigVO newConfigVo = new ConfigVO();
		ConfigVO oldConfigVo = new ConfigVO();
		oldConfigVo = null;
		newConfigVo.setId(1);
		ConfigVerifyUtil.verifyUpdateConfResult(newConfigVo, oldConfigVo);
		Assert.assertTrue("newConfigVo&&oldConfigVo为空", newConfigVo != null && oldConfigVo!= null);
	}
	
	@Test(expected = OptionException.class)
	public void verifyDeleteConfResult() throws Exception {
		String code = "200";
		ConfigVerifyUtil.verifyDeleteConfResult(code);
		Assert.assertTrue("删除失败", code.equals(Constants.RES_SUCCESS));
		code = "300";
		ConfigVerifyUtil.verifyDeleteConfResult(code);
		Assert.assertTrue("删除失败", code.equals(Constants.RES_SUCCESS));
	}
}
