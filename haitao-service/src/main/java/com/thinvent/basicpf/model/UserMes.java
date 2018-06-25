package com.thinvent.basicpf.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_sys_user_mes")
public class UserMes {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	private int id;
	@Column(name="userId",length=36, nullable=false)
	private String userId;
	@Column(name="sex",length=2)
	private int sex;//0:女 1:男
	@Column(name="phone",length=20)
	private String phone;//手机号
	@Column(name="address",length=100)
	private String address;//地址
	@Column(name="birthday")
	private Date birthday;//生日
	@Column(name="interphone",length=20)
	private String interphone;//对讲机号
	@Column(name="policeNum",length=20)
	private String policeNum;//警号
	@Column(name="higherLevel",length=36)
	private String higherLevel;//上级人员ID
	@Column(name="tel",length=20)
	private String tel;//固定电话
	@Column(name="homeTel",length=20)
	private String homeTel;//住宅电话
	@Column(name="politicalStatus",length=2)
	private int politicalStatus;//0：群众 1：团员 2：民主党 3：党员  9：其他 
	@Column(name="nation",length=20)
	private String nation;//民族
	@Column(name="certificateType",length=2)
	private int certificateType;//证件类型(0：身份证  1：护照  2：工作证  3：律师证  9：其他)
	@Column(name="certificateNum",length=50)
	private String certificateNum;//证件号码
	@Column(name="education",length=2)
	private int education;//文化程度(0：初中 1：高中 2：大专 3：本科 4：硕士 5：博士 9：其他)
	@Column(name="school",length=50)
	private String school;//毕业院校
	@Column(name="email",length=50)
	private String email;//邮件地址
	@Column(name="remark",length=500)
	private String remark;//备注
	@Column(name="homepage")
	private int homepage;//首页
	@Column(name="userImages",length=50)
	private String userImages;//照片
	
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
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getInterphone() {
		return interphone;
	}
	public void setInterphone(String interphone) {
		this.interphone = interphone;
	}
	public String getPoliceNum() {
		return policeNum;
	}
	public void setPoliceNum(String policeNum) {
		this.policeNum = policeNum;
	}
	public String getHigherLevel() {
		return higherLevel;
	}
	public void setHigherLevel(String higherLevel) {
		this.higherLevel = higherLevel;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getHomeTel() {
		return homeTel;
	}
	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}
	public int getPoliticalStatus() {
		return politicalStatus;
	}
	public void setPoliticalStatus(int politicalStatus) {
		this.politicalStatus = politicalStatus;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public int getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(int certificateType) {
		this.certificateType = certificateType;
	}
	public String getCertificateNum() {
		return certificateNum;
	}
	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}
	public int getEducation() {
		return education;
	}
	public void setEducation(int education) {
		this.education = education;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getHomepage() {
		return homepage;
	}
	public void setHomepage(int homepage) {
		this.homepage = homepage;
	}
	public String getUserImages() {
		return userImages;
	}
	public void setUserImages(String userImages) {
		this.userImages = userImages;
	}
	
}
