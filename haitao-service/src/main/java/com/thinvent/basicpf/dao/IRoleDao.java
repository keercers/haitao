package com.thinvent.basicpf.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.thinvent.basicpf.model.Role;

public interface IRoleDao extends CrudRepository<Role, Integer>
{
	public Page<Role> findByEnable(int enable, Pageable pageable);

	@Query("from Role r where r.enable=1")
	public List<Role> findAllRole();

    @Query("from Role r where r.roleId=?1 and r.enable=1")
	public Role getByRoleId(String roleId);

	@Query("from Role r where r.roleName=?1 and r.enable=?2")
    public Role findOneByRoleNameAndEnable(String roleName, int enable);

	public Role save(Role role);
	
	@Query(value = "select distinct sr.* from sys_role sr left join sys_user_role sur on sr.role_id = sur.role_id where sur.user_id = ?1 and sr.enable = 1 and sur.enable = 1", nativeQuery = true)
	public List<Role> getByUserId(String userId);

}
