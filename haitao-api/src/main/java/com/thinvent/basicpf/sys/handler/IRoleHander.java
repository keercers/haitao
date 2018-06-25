package com.thinvent.basicpf.sys.handler;

import java.util.List;
import java.util.Map;

import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.RoleVO;

public interface IRoleHander {
	
	public RoleVO findRoleByRoleId(String roleId) throws ThinventBaseException;
	public List findAllRole() throws ThinventBaseException;
	public Map<String,Object> getRoleList(String roleName, String roleType, int pageIndex, int pageSize)throws ThinventBaseException;
    public void addRole(RoleVO roleVO) throws ThinventBaseException;
	public void updateRole(RoleVO roleVO)throws ThinventBaseException;
	public void deleteRole(String roleId)throws ThinventBaseException;
	public List<RoleVO> findAllRoleByUserId(String userId) throws ThinventBaseException;

}
