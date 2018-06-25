package com.thinvent.basicpf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.thinvent.basicpf.model.UserRole;


public interface IUserRoleDao extends CrudRepository<UserRole, Integer>  {
	
	@Query("from UserRole a where a.userId = ?1 and a.enable = 1")
	public List<UserRole> getUserRoleByUserId(String userId);

	@Query("from UserRole a where a.roleId = ?1 and a.enable = 1")
	public List<UserRole> getUserRoleByRoleId(String roleId);
	
	@Query("from UserRole a where a.id = ?1 and a.enable = 1")
	public UserRole getUserRoleById(int id);
	
	@Query("from UserRole a where a.userId = ?1 and a.roleId = ?2")
	public UserRole getUserRoleByUserIdAndRoleId(String userId, String roleId);

	@Query("select a.userId from UserRole a where a.roleId in ?1 and a.enable = 1")
	public List<String> getUserIdsByRoleIds(List<String> roleIdList);
}
