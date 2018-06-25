package com.thinvent.basicpf.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name="m_sys_log")
@Accessors(chain = true)
@Data
public class Log {	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	private int id;
	@Column(name="userName", length=36)
	private String userName;//操作人员
	@Column(name="deptName", length=36)
	private String deptName;//操作人员的部门名
	@Column(name="logType", length=36)
	private String logType;//日志类型 (0:错误日志 1:数据新增 2:数据更新 3:数据删除 4:数据查看 5:数据导出 6:系统登录 7:系统退出 8:其他日志)
	@Column(name="logTime")
	private Date logTime;//日志时间
	@Column(name="system", length=36)
	private String system;//系统(1: 基础功能 2: 综合安全管理)
	@Column(name="moudleName", length=36)
	private String moudleName;//功能模块名
	@Column(name="operationDesc", length=36)
	private String operationDesc;//操作描述
	@Column(name="ipAddress", length=36)
	private String ipAddress;//操作人员ip地址
}
