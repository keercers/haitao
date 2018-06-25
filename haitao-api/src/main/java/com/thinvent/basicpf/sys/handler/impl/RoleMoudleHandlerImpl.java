package com.thinvent.basicpf.sys.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinvent.basicpf.sys.adapt.IRoleMoudleAdapt;
import com.thinvent.basicpf.sys.handler.IRoleMoudleHandler;
import com.thinvent.library.exception.ThinventBaseException;

@Service
public class RoleMoudleHandlerImpl implements IRoleMoudleHandler{

	@Autowired
	private IRoleMoudleAdapt roleMoudleAdapt;
	
	@Override
	public String getListByRoleId(String roleId) throws ThinventBaseException{
		return roleMoudleAdapt.getListByRoleId(roleId);
	}

	@Override
	public String save(String roleId, String userId, String choices) throws ThinventBaseException {
		return roleMoudleAdapt.save(roleId, userId, choices);
	}
}
