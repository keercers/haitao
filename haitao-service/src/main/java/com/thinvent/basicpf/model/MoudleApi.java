package com.thinvent.basicpf.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_sys_moudle_api")
public class MoudleApi {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	private int id;
	@Column(name="moudleApiId",length=36, nullable=false)
	private String moudleApiId;//ID
	@Column(name="moudleId",length=36, nullable=false)
	private String moudleId;//moudleID
	@Column(name="moudleApiName",length=50)
	private String moudleApiName;//模块接口名称
	@Column(name="moudleApiUrl",length=150)
	private String moudleApiUrl;//模块接口URL
	@Column(name="moudleApiValue",length=2)
	private int moudleApiValue;//模块操作权限值
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
	public String getMoudleApiId() {
		return moudleApiId;
	}
	public void setMoudleApiId(String moudleApiId) {
		this.moudleApiId = moudleApiId;
	}
	public String getMoudleId() {
		return moudleId;
	}
	public void setMoudleId(String moudleId) {
		this.moudleId = moudleId;
	}
	public String getMoudleApiName() {
		return moudleApiName;
	}
	public void setMoudleApiName(String moudleApiName) {
		this.moudleApiName = moudleApiName;
	}
	public String getMoudleApiUrl() {
		return moudleApiUrl;
	}
	public void setMoudleApiUrl(String moudleApiUrl) {
		this.moudleApiUrl = moudleApiUrl;
	}
	public int getMoudleApiValue() {
		return moudleApiValue;
	}
	public void setMoudleApiValue(int moudleApiValue) {
		this.moudleApiValue = moudleApiValue;
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
