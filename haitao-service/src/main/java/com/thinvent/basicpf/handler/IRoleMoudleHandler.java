package com.thinvent.basicpf.handler;

import java.util.List;

import com.thinvent.basicpf.model.RoleMoudle;

public interface IRoleMoudleHandler {

	public List<RoleMoudle> queryList(String roleId);
	
	public String save(String roleId, String userId, String choices);

}
