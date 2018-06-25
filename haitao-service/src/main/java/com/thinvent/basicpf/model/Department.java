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
@Table(name="m_sys_department")
@Accessors(chain = true)
@Setter
@Getter
public class Department {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	private int id;
	@Column(name="depId",length=36, nullable=false)
	private String depId;//ID
	@Column(name="depName",length=125)
	private String depName;//部门名称
	@Column(name="depParentId",length=36)
	private String depParentId;//上级部门
	@Column(name="comId",length=36)
	private String comId;//单位ID
	@Column(name="depLevel")
	private int depLevel;//部门等级
	@Column(name="depLeader",length=20)
	private String depLeader;//部门负责人
	@Column(name="depSort")
	private int depSort;//部门排序
	@Column(name="isleaf",length=2)
	private int isleaf;//1：无下级部门  0：有下级部门
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
