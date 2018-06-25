package com.thinvent.basicpf.sys.handler;

import java.util.List;

import com.thinvent.library.exception.ThinventBaseException;

@FunctionalInterface
public interface IUserRoleHandler {

	public List<String> queryUserIdsByRoleIds(String roleIds) throws ThinventBaseException;

}
