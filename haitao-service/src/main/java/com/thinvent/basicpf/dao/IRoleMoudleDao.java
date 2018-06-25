package com.thinvent.basicpf.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.thinvent.basicpf.model.RoleMoudle;

public interface IRoleMoudleDao extends CrudRepository<RoleMoudle, Integer> {

	public List<RoleMoudle> findByRoleIdAndEnable(String roleId, int enable);
	public List<RoleMoudle> findByRoleId(String roleId);
	public List<RoleMoudle> findByMoudelIdAndEnable(String moudelId, int enable);
	public RoleMoudle findByRoleIdAndMoudelId(String roleId, String moudelId);

}
