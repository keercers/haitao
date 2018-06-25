package com.thinvent.basicpf.handler;

import com.thinvent.basicpf.model.Role;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.RoleVO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


public interface IRoleHandler {
	
	public Role findRoleByRoleId(String roleId);
	public Map listAllRoleByCondition(Pageable pageable, String roleName, String roleType);
	public List<Role> findAllRole() throws ThinventBaseException;
	//save 可做保存和更新操作
	public void addRole(RoleVO roleVO) throws ThinventBaseException;
	public void update(RoleVO roleVO) throws ThinventBaseException;
	public void delete(String roleId)throws ThinventBaseException;
	public List<RoleVO> findAllRoleByUserId(String userId);
}
