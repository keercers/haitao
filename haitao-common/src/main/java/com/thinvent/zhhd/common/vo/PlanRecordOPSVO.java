package com.thinvent.zhhd.common.vo;

import com.thinvent.library.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;

/**   
* @Description: 计划记录运维人员中间表
* @author tanshumei  
*/
@Getter
@Setter
public class PlanRecordOPSVO {
	public PlanRecordOPSVO() {
		this.planRecordOPSId = UUIDUtil.getUuid();
		this.enable = 1;
	}
	private String planRecordOPSId;
	
	private String planId;
	
	private String planName;
	
	private String recordId;
	
	private String recordName;
	
	private String opsId;
	
	private String opsName;
	
	private int enable;
}
