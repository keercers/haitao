package com.thinvent.basicpf.sys.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.junit.Assert;
import org.junit.Test;

import com.thinvent.library.exception.DataNotExistException;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.DepartmentVO;

public class DepartmentVerifyUtilTest {
    @Test
    public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<DepartmentVerifyUtil> constructor = DepartmentVerifyUtil.class.getDeclaredConstructor();
        Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }
    @Test(expected = DataNotExistException.class)
    public void verifyDeptTest() throws ThinventBaseException {
        DepartmentVO departmentVO = null;
        DepartmentVerifyUtil.verifyDept(departmentVO);
    }
}
