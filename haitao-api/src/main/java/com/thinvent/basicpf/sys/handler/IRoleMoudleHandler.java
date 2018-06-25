package com.thinvent.basicpf.sys.handler;

import com.thinvent.library.exception.ThinventBaseException;

public interface IRoleMoudleHandler {

	public String getListByRoleId(String roleId) throws ThinventBaseException;
	
	public String save(String roleId, String userId, String choices) throws ThinventBaseException;

}
