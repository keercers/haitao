package com.thinvent.basicpf.sys.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.junit.Assert;
import org.junit.Test;

import com.thinvent.library.exception.DataNotExistException;
import com.thinvent.library.exception.DataVerException;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.CryptographyUtil;
import com.thinvent.library.vo.UserVO;

public class UserVerifyUtilTest {
	private static final String password = "111111";
	private static final String otherPass = "123456";
	
	@Test
    public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<UserVerifyUtil> constructor = UserVerifyUtil.class.getDeclaredConstructor();
        Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }
	
	@Test(expected=DataNotExistException.class)
	public void verifyLoginTest() throws ThinventBaseException {
		UserVerifyUtil.verifyLogin(null, null);
	}
	
	//verifyLogin分支
	@Test(expected=DataVerException.class)
	public void verifyLoginBranchOneTest() throws ThinventBaseException {
		UserVO user = new UserVO();
		UserVerifyUtil.verifyLogin(user, null);
	}
	
	//verifyLogin分支
	@Test(expected=DataVerException.class)
	public void verifyLoginBranchTwoTest() throws ThinventBaseException {
		UserVO user = new UserVO();
		user.setPassword(CryptographyUtil.md5(this.password));
		UserVerifyUtil.verifyLogin(user, this.password);
		UserVerifyUtil.verifyLogin(user, this.otherPass);
	}
	
	//verifyLogin分支
	@Test(expected=DataVerException.class)
	public void verifyLoginBranchThreeTest() throws ThinventBaseException {
		UserVO user = new UserVO();
		UserVerifyUtil.verifyLogin(user, "");
	}
	
	
	@Test(expected=DataNotExistException.class)
	public void verifyUpdatePassTest() throws ThinventBaseException {
		UserVerifyUtil.verifyUpdatePass(null, null, null);
	}
	
	//verifyUpdatePass分支
	@Test(expected=DataVerException.class)
	public void verifyUpdatePassBranchOneTest() throws ThinventBaseException {
		UserVO user = new UserVO();
		UserVerifyUtil.verifyUpdatePass(user, null, null);
	}
	
	//verifyUpdatePass两个参数
	@Test(expected=DataVerException.class)
	public void verifyUpdatePassTwoParamTest() throws ThinventBaseException {
		UserVerifyUtil.verifyUpdatePass("123456", "123457");
		UserVerifyUtil.verifyUpdatePass("123456", "123456");
	}
	
	//verifyUpdatePass分支
	@Test(expected=DataVerException.class)
	public void verifyUpdatePassBranchTwoTest() throws ThinventBaseException {
		UserVO user = new UserVO();
		String oldPassword = this.password;
		UserVerifyUtil.verifyUpdatePass(user, oldPassword, null);
	}
	
	//verifyUpdatePass分支
	@Test(expected=DataVerException.class)
	public void verifyUpdatePassBranchThreeTest() throws ThinventBaseException {
		UserVO user = new UserVO();
		String oldPassword = this.password;
		String newPassword = this.password;
		UserVerifyUtil.verifyUpdatePass(user, oldPassword, newPassword);
	}
	
	//verifyUpdatePass分支
	@Test(expected=DataVerException.class)
	public void verifyUpdatePassBranchFourTest() throws ThinventBaseException {
		UserVO user = new UserVO();
		String oldPassword = this.password;
		String newPassword = this.password;
		UserVerifyUtil.verifyUpdatePass(user, oldPassword, newPassword);
	}
	
	//verifyUpdatePass分支
	@Test(expected=DataVerException.class)
	public void verifyUpdatePassBranchFiveTest() throws ThinventBaseException {
		UserVO user = new UserVO();
		String oldPassword = this.otherPass;
		String newPassword = this.password;
		user.setPassword(CryptographyUtil.md5(this.password));
		UserVerifyUtil.verifyUpdatePass(user, oldPassword, newPassword);
	}
	
	//verifyUpdatePass分支
	@Test
	public void verifyUpdatePassBranchSixTest() throws ThinventBaseException {
		UserVO user = new UserVO();
		user.setPassword(CryptographyUtil.md5(password));
		UserVerifyUtil.verifyUpdatePass(user, password, "1");
	}
	
	//verifyUpdatePass分支
	@Test(expected = DataVerException.class)
	public void verifyUpdatePassBranchSevenTest() throws ThinventBaseException {
		UserVO user = new UserVO();
		user.setPassword(CryptographyUtil.md5(password));
		UserVerifyUtil.verifyUpdatePass(user, "", "1");
	}
	
	//verifyUpdatePass分支
	@Test(expected = DataVerException.class)
	public void verifyUpdatePassBranchEightTest() throws ThinventBaseException {
		UserVO user = new UserVO();
		user.setPassword(CryptographyUtil.md5(password));
		UserVerifyUtil.verifyUpdatePass(user, "1", "");
	}
	
	@Test(expected = DataVerException.class)
	public void verifyUpdateResultTest() throws ThinventBaseException {
		UserVerifyUtil.verifyUpdateResult("123456", "123456");
		String oldPassword = this.password;
		String newPassword = this.otherPass;
		UserVerifyUtil.verifyUpdateResult(oldPassword, newPassword);
	}
	
	@Test(expected = DataVerException.class)
	public void verifyUpdateResultStateTest() throws ThinventBaseException {
		String state = "300";
		UserVerifyUtil.verifyUpdateResult(state);
		state = "400";
		UserVerifyUtil.verifyUpdateResult(state);
	}
	
	//verifyUpdateResult分支
	@Test(expected = DataVerException.class)
	public void verifyUpdateResultStateBranchTest() throws ThinventBaseException {
		String state = "200";
		UserVerifyUtil.verifyUpdateResult(state);
		state = "400";
		UserVerifyUtil.verifyUpdateResult(state);
	}
	
	@Test(expected = DataVerException.class)
	public void verifySaveResultTest() throws ThinventBaseException {
		UserVO userVO = null;
		UserVerifyUtil.verifySaveResult(userVO);
		userVO = new UserVO();
		UserVerifyUtil.verifySaveResult(userVO);
	}
	
	@Test(expected = DataVerException.class)
	public void verifySaveResultStateTest() throws ThinventBaseException {
		String state = "200";
		UserVerifyUtil.verifySaveResult(state);
		state = "400";
		UserVerifyUtil.verifySaveResult(state);
	}
	
	@Test(expected = DataVerException.class)
	public void verifyQueryResultTest() throws ThinventBaseException {
		UserVO userVO = new UserVO();
		UserVerifyUtil.verifyQueryResult(userVO);
		userVO = null;
		UserVerifyUtil.verifyQueryResult(userVO);
	}
}
