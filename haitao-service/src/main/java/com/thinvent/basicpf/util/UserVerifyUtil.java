package com.thinvent.basicpf.util;

import java.util.List;

import com.thinvent.basicpf.model.User;
import com.thinvent.basicpf.model.UserMes;
import com.thinvent.basicpf.model.UserRole;
import com.thinvent.library.dim.TvtExceptionEnum;
import com.thinvent.library.exception.DataNotExistException;
import com.thinvent.library.exception.ThinventBaseException;

public class UserVerifyUtil{
	
	private UserVerifyUtil(){}
	
	public static void verifyUser(User user) throws ThinventBaseException{
		if(user == null)
			throw new DataNotExistException(TvtExceptionEnum.DATA_NOTEXSIT.getIndex(), TvtExceptionEnum.DATA_NOTEXSIT.getName());
	}
	
	public static void verifyUserMes(UserMes userMes) throws ThinventBaseException{
		if(userMes == null)
			throw new DataNotExistException(TvtExceptionEnum.DATA_NOTEXSIT.getIndex(), TvtExceptionEnum.DATA_NOTEXSIT.getName());
	}
	
	public static void verifyUserRole(UserRole userRole) throws ThinventBaseException{
		if(userRole == null)
			throw new DataNotExistException(TvtExceptionEnum.DATA_NOTEXSIT.getIndex(), TvtExceptionEnum.DATA_NOTEXSIT.getName());
	}
	
	public static void verifyUserRoleList(List list) throws ThinventBaseException {
		if(list == null)
			throw new ThinventBaseException(TvtExceptionEnum.DATA_NOTEXSIT.getIndex(), TvtExceptionEnum.DATA_NOTEXSIT.getName());
	}

}
