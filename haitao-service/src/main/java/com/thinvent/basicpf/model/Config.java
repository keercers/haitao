package com.thinvent.basicpf.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_sys_config")
public class Config {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	private int id;
	@Column(name="confId",length=36, nullable=false)
	private String confId;//ID
	@Column(name="confKey",length=125)
	private String confKey;//属性key值
	@Column(name="confValue",length=125)
	private String confValue;//属性value值
	@Column(name="confType",length=2)
	private int confType;//属性类型 0：基本设置  1：业务设置   2：同步配置  3：备份设置  4：安全配置  5:视频设置  9：其他配置
	@Column(name="confDesc",length=200)
	private String confDesc;//属性描述
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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getConfId() {
		return confId;
	}
	public void setConfId(String confId) {
		this.confId = confId;
	}
	public String getConfKey() {
		return confKey;
	}
	public void setConfKey(String confKey) {
		this.confKey = confKey;
	}
	public String getConfValue() {
		return confValue;
	}
	public void setConfValue(String confValue) {
		this.confValue = confValue;
	}
	public int getConfType() {
		return confType;
	}
	public void setConfType(int confType) {
		this.confType = confType;
	}
	public String getConfDesc() {
		return confDesc;
	}
	public void setConfDesc(String confDesc) {
		this.confDesc = confDesc;
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
