package com.thinvent.basicpf.util;

import com.thinvent.basicpf.model.Position;
import com.thinvent.library.exception.DataNotExistException;
import com.thinvent.library.exception.OptionException;
import com.thinvent.library.exception.ThinventBaseException;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class PositionVerifyUtilTest {

    @Test
    public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<PositionVerifyUtil> constructor = PositionVerifyUtil.class.getDeclaredConstructor();
        Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test(expected = DataNotExistException.class)
    public void verifyPosTest() throws ThinventBaseException {
        Position position = null;
        PositionVerifyUtil.verifyPos(position);
    }
    
    @Test(expected = OptionException.class)
    public void verifyDeletePosResultTest() throws ThinventBaseException {
        Boolean existsUser = true;
        PositionVerifyUtil.verifyDeletePosResult(existsUser);
    }
    
    @Test(expected = ThinventBaseException.class)
    public void verifyExistPos() throws ThinventBaseException {
    	Position position = new Position();
    	position.setPosName("test_name");
    	PositionVerifyUtil.verifyExistPos(position);
    	
    	Position pos = null;
    	PositionVerifyUtil.verifyExistPos(pos);
	}
}
