package com.thinvent.basicpf.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_sys_company")
public class Company {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	private int id;
	@Column(name="comId",length=36, nullable=false)
	private String comId;//ID
	@Column(name="comName",length=125)
	private String comName;//单位名称
	@Column(name="comParentId",length=36)
	private String comParentId;//上级单位
	@Column(name="comAddr",length=255)
	private String comAddr;//单位地址
	@Column(name="comTel",length=20)
	private String comTel;//单位电话
	@Column(name="comContacts",length=20)
	private String comContacts;//单位联系人
	@Column(name="comHomepage",length=125)
	private String comHomepage;//单位主页
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
	private Date updateTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComId() {
		return comId;
	}
	public void setComId(String comId) {
		this.comId = comId;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getComParentId() {
		return comParentId;
	}
	public void setComParentId(String comParentId) {
		this.comParentId = comParentId;
	}
	public String getComAddr() {
		return comAddr;
	}
	public void setComAddr(String comAddr) {
		this.comAddr = comAddr;
	}
	public String getComTel() {
		return comTel;
	}
	public void setComTel(String comTel) {
		this.comTel = comTel;
	}
	public String getComContacts() {
		return comContacts;
	}
	public void setComContacts(String comContacts) {
		this.comContacts = comContacts;
	}
	public String getComHomepage() {
		return comHomepage;
	}
	public void setComHomepage(String comHomepage) {
		this.comHomepage = comHomepage;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	
}
