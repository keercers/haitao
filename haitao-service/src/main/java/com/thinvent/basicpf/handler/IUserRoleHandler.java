package com.thinvent.basicpf.handler;

import java.util.List;

import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.UserRoleVO;

public interface IUserRoleHandler {
	public void save(UserRoleVO userRole);
	
	public void update(UserRoleVO userRole) throws ThinventBaseException;
	
	public List<UserRoleVO> getUserRoleListByUserId(String userId) throws ThinventBaseException;

	public List<UserRoleVO> getUserRoleListByRoleId(String roleId) throws ThinventBaseException;

	public List<String> queryUserIdsByRoleIds(String roleIds);
}
