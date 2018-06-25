package com.thinvent.basicpf.util;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.thinvent.basicpf.model.Config;
import com.thinvent.basicpf.util.ConfigVerifyUtil;
import com.thinvent.library.exception.DataNotExistException;

@RunWith(SpringJUnit4ClassRunner.class)
public class ConfigVerifyUtilTest {
	
	Config config = new Config();
	
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
	public void verifyConf() throws Exception{
		ConfigVerifyUtil.verifyConf(config);
		Assert.assertTrue("config为空", config != null);
		
		config = null;
		ConfigVerifyUtil.verifyConf(config);
		Assert.assertTrue("config为空", config != null);
	}
	
	@Test(expected = DataNotExistException.class)
	public void verifyAddConf() throws Exception {
		ConfigVerifyUtil.verifyAddConf(config);
		Assert.assertTrue("config为空", config != null);
		
		config = null;
		ConfigVerifyUtil.verifyAddConf(config);
		Assert.assertTrue("config为空", config != null);
	}
	
	@Test(expected = DataNotExistException.class)
	public void verifyUpdateConfResult() throws Exception {
		Config newConfig = new Config();
		Config oldConfig = new Config();
		ConfigVerifyUtil.verifyUpdateConfResult(newConfig, oldConfig);
		Assert.assertTrue("newConfig&&oldConfig为空", newConfig != null && oldConfig!= null);
		
		oldConfig = null;
		newConfig = null;
		ConfigVerifyUtil.verifyUpdateConfResult(newConfig, oldConfig);
		Assert.assertTrue("newConfig&&oldConfig为空", newConfig != null && oldConfig!= null);
	}
	
	@Test(expected = DataNotExistException.class)
	public void verifyUpdateConfResulta() throws Exception {
		Config newConfig = new Config();
		Config oldConfig = new Config();
		oldConfig = null;
		newConfig.setId(1);
		ConfigVerifyUtil.verifyUpdateConfResult(newConfig, oldConfig);
		Assert.assertTrue("newConfig&&oldConfig为空", newConfig != null && oldConfig!= null);
	}
}
