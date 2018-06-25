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

import com.thinvent.library.exception.OptionException;

@RunWith(SpringJUnit4ClassRunner.class)
public class MoudleVerifyUtilTest {

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test( expected = OptionException.class )
	public void httpVerify() throws Exception {
		String status = "200";
		MoudleVerifyUtil.httpVerify(status);
		
		status = "300";
		MoudleVerifyUtil.httpVerify(status);
	}
	
	@Test
    public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<MoudleVerifyUtil> constructor = MoudleVerifyUtil.class.getDeclaredConstructor();
        Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }
}
