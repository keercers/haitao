package com.thinvent.basicpf.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thinvent.basicpf.model.Moudle;
import com.thinvent.library.exception.DataNotExistException;
import com.thinvent.library.exception.DataVerException;

@RunWith(SpringJUnit4ClassRunner.class)
public class MoudleVerifyUtilTest {

	Moudle moudle = new Moudle();

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testConstructorIsPrivate()
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Constructor<MoudleVerifyUtil> constructor = MoudleVerifyUtil.class.getDeclaredConstructor();
		Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	@Test(expected = DataNotExistException.class)
	public void verifyMoudle() throws Exception {
		moudle.setMoudleSign("0-1-");
		MoudleVerifyUtil.verifyMoudle(moudle);

		moudle.setMoudleSign(null);
		MoudleVerifyUtil.verifyMoudle(moudle);

		moudle = null;
		MoudleVerifyUtil.verifyMoudle(moudle);
	}

	@Test(expected = DataVerException.class)
	public void verifyMoudleParent() throws Exception {
		MoudleVerifyUtil.verifyMoudleParent(moudle);

		moudle = null;
		MoudleVerifyUtil.verifyMoudleParent(moudle);
	}

	@Test(expected = DataVerException.class)
	public void verifyListChilds() throws Exception {
		List<Moudle> list = new ArrayList<>();
		MoudleVerifyUtil.verifyListChilds(list);
		list.add(moudle);
		MoudleVerifyUtil.verifyListChilds(list);
	}
}
