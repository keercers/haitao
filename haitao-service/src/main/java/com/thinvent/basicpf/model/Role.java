package com.thinvent.basicpf.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name="m_sys_role")
@Accessors(chain = true)
@Setter
@Getter
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	private int id;
	@Column(name="roleId",length=36, nullable=false)
	private String roleId;//ID
	@Column(name="roleName",length=50)
	private String roleName;//角色名称
	@Column(name="roleDesc",length=125)
	private String roleDesc;//角色描述
	@Column(name="roleType")
	private String roleType;//角色类型（0：全局角色，1：部门角色，2：个人角色 ，9：其他）
	@Column(name="isdefault",length=2)
	private int isdefault;//0：否 1：是  整表唯一值为1
	@Column(name="enable",length=2)
	private int enable;//是否可用
	@Column(name="remark",length=500)
	private String remark;//备注
	@Column(name="createUser",length=36)
	private String createUser;
	@Column(name="updateUser",length=36)
	private String updateUser;
	@Column(name="createTime")
	private Date createTime;
	@Column(name="updateTime")
	private Date updateTime = new Date();
	
}
