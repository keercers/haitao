package com.thinvent.basicpf.sys.util;

import com.thinvent.library.dim.TvtExceptionEnum;
import com.thinvent.library.exception.DataNotExistException;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.RoleVO;

public class RoleVerifyUtil {
	
	private RoleVerifyUtil() {}

	public static void verifyRole(RoleVO roleVO) throws ThinventBaseException {
		if (roleVO == null)
			throw new DataNotExistException(TvtExceptionEnum.DATA_NOTEXSIT.getIndex(), TvtExceptionEnum.DATA_NOTEXSIT.getName());
	}

	public static void verifyAddRoleResult(RoleVO role) throws ThinventBaseException {
		if (role == null)
			throw new DataNotExistException(TvtExceptionEnum.DATA_NOTEXSIT.getIndex(), TvtExceptionEnum.DATA_NOTEXSIT.getName());
	}

	public static void verifyUpdateRoleResult(RoleVO role, RoleVO last) throws ThinventBaseException {
		if (role == null || last == null)
			throw new DataNotExistException(TvtExceptionEnum.DATA_NOTEXSIT.getIndex(), TvtExceptionEnum.DATA_NOTEXSIT.getName());
		
	}

}
