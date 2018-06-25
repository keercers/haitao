package com.thinvent.basicpf.util;

import com.thinvent.basicpf.model.Role;
import com.thinvent.library.dim.TvtExceptionEnum;
import com.thinvent.library.exception.DataNotExistException;
import com.thinvent.library.exception.OptionException;
import com.thinvent.library.exception.ThinventBaseException;


public class RoleVerifyUtil {

	private RoleVerifyUtil() {
	}

	public static void verifyrole(Role role) throws ThinventBaseException {
		if (role == null)
			throw new DataNotExistException(TvtExceptionEnum.DATA_NOTEXSIT.getIndex(), TvtExceptionEnum.DATA_NOTEXSIT.getName());
	}

	public static void verifyAddExistRole(Role role) throws ThinventBaseException {
		if (role != null)
			throw new OptionException(TvtExceptionEnum.DATA_VER_ROLE_ROLENAME_EXSIT.getIndex(), TvtExceptionEnum.DATA_VER_ROLE_ROLENAME_EXSIT.getName());
	}

	public static void verifyUpdateExistRole(Role role,Role oldRole) throws ThinventBaseException {
		if (role != null && !role.getRoleId().equals(oldRole.getRoleId()))
			throw new OptionException(TvtExceptionEnum.DATA_VER_ROLE_ROLENAME_EXSIT.getIndex(), TvtExceptionEnum.DATA_VER_ROLE_ROLENAME_EXSIT.getName());
	}

}
