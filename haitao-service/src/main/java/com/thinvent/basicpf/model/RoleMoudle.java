package com.thinvent.basicpf.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_sys_role_moudle")
public class RoleMoudle {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	private int id;
	@Column(name="roleModuleId",length=36, nullable=false)
	private String roleModuleId;//ID
	@Column(name="moudelId",length=36)
	private String moudelId;//模块ID
	@Column(name="roleId",length=36)
	private String roleId;//角色ID
	@Column(name="enable",length=2)
	private int enable;//是否可用
	@Column(name="createUser",length=36)
	private String createUser;
	@Column(name="updateUser",length=36)
	private String updateUser;
	@Column(name="createTime")
	private Date createTime;
	@Column(name="updateTime")
	private Date updateTime = new Date();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleModuleId() {
		return roleModuleId;
	}
	public void setRoleModuleId(String roleModuleId) {
		this.roleModuleId = roleModuleId;
	}
	public String getMoudelId() {
		return moudelId;
	}
	public void setMoudelId(String moudelId) {
		this.moudelId = moudelId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public RoleMoudle() {}
	
	public RoleMoudle(RoleMoudle roleMoudle) {
		super();
		this.id = roleMoudle.getId();
		this.roleModuleId = roleMoudle.getRoleModuleId();
		this.moudelId = roleMoudle.getMoudelId();
		this.roleId = roleMoudle.getRoleId();
		this.enable = roleMoudle.getEnable();
		this.createUser = roleMoudle.getCreateUser();
		this.updateUser = roleMoudle.getUpdateUser();
		this.createTime = roleMoudle.getCreateTime();
		this.updateTime = roleMoudle.getUpdateTime();
	}
	
}
