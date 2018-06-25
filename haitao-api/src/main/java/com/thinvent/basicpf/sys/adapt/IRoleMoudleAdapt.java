package com.thinvent.basicpf.sys.adapt;

import com.thinvent.library.exception.ThinventBaseException;

public interface IRoleMoudleAdapt {

	public String getListByRoleId(String roleId) throws ThinventBaseException;
	public String save(String roleId, String userId, String choices) throws ThinventBaseException;

}
