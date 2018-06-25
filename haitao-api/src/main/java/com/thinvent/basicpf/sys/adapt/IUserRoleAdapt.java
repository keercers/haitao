package com.thinvent.basicpf.sys.adapt;

import com.thinvent.library.exception.ThinventBaseException;

@FunctionalInterface
public interface IUserRoleAdapt {

	public String queryUserIdsByRoleIds(String roleIds) throws ThinventBaseException ;

}
