package com.thinvent.basicpf.sys.adapt;

import java.util.List;
import java.util.Map;

import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.RoleVO;
import com.thinvent.library.vo.UserRoleVO;

public interface IRoleAdapt {
	public RoleVO findRoleByRoleId(String roleId) throws ThinventBaseException;
	public List findAllRole() throws ThinventBaseException;
	public Map getRoleList(String roleName, String roleType, int pageIndex, int pageSize)throws ThinventBaseException;
	public void add(RoleVO roleVO) throws ThinventBaseException;
	public void update(RoleVO roleVO)throws ThinventBaseException;
	public void delete(String roleId)throws ThinventBaseException;
	public List<UserRoleVO> getUserRoleByRoleId(String roleId) throws ThinventBaseException;
	public List<RoleVO> findAllRoleByUserId(String userId) throws ThinventBaseException;
	
}
