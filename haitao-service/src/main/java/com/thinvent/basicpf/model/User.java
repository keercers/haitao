package com.thinvent.basicpf.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.thinvent.library.vo.UserVO;

@Entity
@Table(name="m_sys_user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	private int id;
	@Column(name="userId",length=36, nullable=false)
	private String userId;//用户ID
	@Column(name="userName",length=50)
	private String userName;//用户名
	@Column(name="loginName",length=50)
	private String loginName;//登录名
	@Column(name="password",length=100)
	private String password;//密码
	@Column(name="depId",length=36)
	private String depId;//部门ID
	@Column(name="posId",length=36)
	private String posId;//岗位ID
	@Column(name="enable",length=2)
	private int enable;//是否可用
	@Column(name="sysUser",length=2)
	private int sysUser;//是否系统用户1：是；0：否
	@Column(name="loginIp",length=50)
	private String loginIp;//最后登录IP
	@Column(name="phone",length=11)
	private String phone;//电话号码
	@Column(name="loginTime")
	private Date loginTime;//最后登录时间
	@Column(name="endFunction")
	private int endFunction;//最后操作模块
	@Column(name="pageFlag",length=2)
	private int pageFlag;//默认开关0：默认页面  1：上次最后操作页面
	@Column(name="createUser",length=36)
	private String createUser;
	@Column(name="updateUser",length=36)
	private String updateUser;
	@Column(name="createTime")
	private Date createTime;
	@Column(name="updateTime")
	private Date updateTime = new Date();
	@Column(name="remark")
	private String remark;//备注
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getPosId() {
		return posId;
	}
	public void setPosId(String posId) {
		this.posId = posId;
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
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public int getEndFunction() {
		return endFunction;
	}
	public void setEndFunction(int endFunction) {
		this.endFunction = endFunction;
	}
	public int getPageFlag() {
		return pageFlag;
	}
	public void setPageFlag(int pageFlag) {
		this.pageFlag = pageFlag;
	}
	
	public UserVO convertToUserVO() {
		UserVO userVO = new UserVO();
		BeanUtils.copyProperties(this, userVO);
		return userVO;
	}
}
