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
@Table(name="m_sys_moudle")
@Accessors(chain = true)
@Setter
@Getter
public class Moudle implements Comparable<Moudle>{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	private int id;
	@Column(name="moudleId",length=36, nullable=false)
	private String moudleId;//ID
	@Column(name="moudleLevel",length=11)
	private String moudleLevel;//等级
	@Column(name="moudleName",length=50)
	private String moudleName;//模块名称
	@Column(name="moudleType",length=50)
	private String moudleType;//模块类型 1：系统；2：菜单；3：功能
	@Column(name="moudleSign",length=50)
	private String moudleSign;//模块标识
	@Column(name="moudleUrl",length=150)
	private String moudleUrl;//模块URL
	@Column(name="moudleIcon",length=150)
	private String moudleIcon;//模块图标
	@Column(name="moudleParentId",length=36)
	private String moudleParentId;//父级ID
	@Column(name="moudleSort")
	private String moudleSort;//排序
	@Column(name="isleaf",length=2)
	private String isleaf;//是否有下级   1：无下级  0：有下级
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

	
	
	@Override
	public int hashCode() {
		return this.moudleId.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Moudle){
			Moudle moudle = (Moudle) obj;
			return this.moudleId==moudle.getMoudleId();
		}
		return false;
	}

	@Override
	public int compareTo(Moudle o) {
		// TODO Auto-generated method stub
		return Integer.valueOf(this.moudleSort) - Integer.valueOf(o.moudleSort);
	}
}
