package com.thinvent.basicpf.sys.util;

import com.thinvent.library.Constants;
import com.thinvent.library.dim.TvtExceptionEnum;
import com.thinvent.library.exception.DataNotExistException;
import com.thinvent.library.exception.DataVerException;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.CryptographyUtil;
import com.thinvent.library.util.StringUtil;
import com.thinvent.library.vo.UserVO;

public class UserVerifyUtil{
	
	private UserVerifyUtil(){}
	
	public static void verifyLogin(UserVO user, String password) throws ThinventBaseException{
		if(user == null) {
			throw new DataNotExistException(TvtExceptionEnum.DATA_NOTEXSIT.getIndex(), TvtExceptionEnum.DATA_NOTEXSIT.getName());
		}
		if(password == null||password.trim().length()<=0)
			throw new DataVerException(TvtExceptionEnum.DATA_VER_PASSWORD_NULL.getIndex(), TvtExceptionEnum.DATA_VER_PASSWORD_NULL.getName());
		if(!CryptographyUtil.md5(password).equals(user.getPassword())){
			throw new DataVerException(TvtExceptionEnum.DATA_VER_USERNAME_PASSWORD_ERROR.getIndex(), 
					TvtExceptionEnum.DATA_VER_USERNAME_PASSWORD_ERROR.getName());
		}
	}
	
	public static void verifyUpdatePass(UserVO user, String oldPassword, String newPassword) throws ThinventBaseException{
		if(user == null)
			throw new DataNotExistException(TvtExceptionEnum.DATA_NOTEXSIT.getIndex(), TvtExceptionEnum.DATA_NOTEXSIT.getName());
		if(oldPassword == null||oldPassword.trim().length()<=0)
			throw new DataVerException(TvtExceptionEnum.DATA_VER_OLD_PASSWORD_NULL.getIndex(), TvtExceptionEnum.DATA_VER_OLD_PASSWORD_NULL.getName());
		if(newPassword == null||newPassword.trim().length()<=0)
			throw new DataVerException(TvtExceptionEnum.DATA_VER_NEW_PASSWORD_NULL.getIndex(), TvtExceptionEnum.DATA_VER_NEW_PASSWORD_NULL.getName());
		if(oldPassword.equals(newPassword))
			throw new DataVerException(TvtExceptionEnum.DATA_VER_OLE_NEW_PASSWORD_EQUAL.getIndex(), 
					TvtExceptionEnum.DATA_VER_OLE_NEW_PASSWORD_EQUAL.getName());
		if(!CryptographyUtil.md5(oldPassword).equals(user.getPassword())){
			throw new DataVerException(TvtExceptionEnum.DATA_VER_OLD_PASSWORD_ERROR.getIndex(), TvtExceptionEnum.DATA_VER_OLD_PASSWORD_ERROR.getName());
		}
	}
	
	public static void verifyUpdatePass(String oldPass, String newPass) throws ThinventBaseException {
		if(oldPass.equals(newPass))
			throw new DataVerException(TvtExceptionEnum.DATA_VER_OLE_NEW_PASSWORD_EQUAL.getIndex(), TvtExceptionEnum.DATA_VER_OLE_NEW_PASSWORD_EQUAL.getName());
	}
	
	public static void verifyUpdateResult(String oldPassword, String newPassword) throws ThinventBaseException{
		if(!oldPassword.equals(newPassword)){
			throw new DataVerException(TvtExceptionEnum.DATA_VER_FAULSE.getIndex(), TvtExceptionEnum.DATA_VER_FAULSE.getName());
		}
	}
	
	public static void verifyUpdateResult(String state) throws ThinventBaseException{
		if("300".equals(state)){
			throw new DataVerException(TvtExceptionEnum.DATA_NOTEXSIT.getIndex(), TvtExceptionEnum.DATA_NOTEXSIT.getName());
		}else if(!Constants.RES_SUCCESS.equals(state))
			throw new DataVerException(TvtExceptionEnum.OPT_UPDATE_FAULSE.getIndex(), TvtExceptionEnum.OPT_UPDATE_FAULSE.getName());
	}
	
	public static void verifySaveResult(UserVO userVO) throws ThinventBaseException{
		if(userVO != null) {
			throw new DataVerException(TvtExceptionEnum.DATA_VER_USERLOGINNAME_EXIST.getIndex(), TvtExceptionEnum.DATA_VER_USERLOGINNAME_EXIST.getName());
		}
	}
	
	public static void verifySaveResult(String state) throws ThinventBaseException{
		if("400".equals(state)) {
			throw new DataVerException(TvtExceptionEnum.OPT_SAVE_FAULSE.getIndex(), TvtExceptionEnum.OPT_SAVE_FAULSE.getName());
		}
	}
	
	public static void verifyQueryResult(UserVO userVO) throws ThinventBaseException{
		if(userVO == null) {
			throw new DataVerException(TvtExceptionEnum.DATA_NOTEXSIT.getIndex(), TvtExceptionEnum.DATA_NOTEXSIT.getName());
		}
	}
	
	public static void verifyUserEquals(UserVO src, UserVO target) throws ThinventBaseException{
		if(StringUtil.equals(src.getLoginName(), target.getLoginName()) 
				&& !StringUtil.equals(src.getUserId(), target.getUserId())) {
			throw new DataVerException(TvtExceptionEnum.DATA_VER_USERLOGINNAME_EXIST.getIndex(), TvtExceptionEnum.DATA_VER_USERLOGINNAME_EXIST.getName());
		}
	}
	
}
