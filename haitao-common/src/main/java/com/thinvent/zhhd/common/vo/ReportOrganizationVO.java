package com.thinvent.zhhd.common.vo;

import java.util.Date;

import com.thinvent.library.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
@Accessors(chain = true)
@Setter
@Getter
public class ReportOrganizationVO {
	public ReportOrganizationVO(){
		this.roId = UUIDUtil.getUuid();
	}
	
	private String roId;
	
	private String reportId;
	
    private String reportNo;
	
	
    private String reportType;
	
	
    private String captain;
	
	
    private String captainNo;
	
	
    private String agent;
	
	
    private String reportOrganization;
	
	
    private String reporter;
	
	
    private String reporterNo;
	
	
    private Date reporterDate;
	
	
    private String emergencyContact;
	

    private String emergencyPhone;
	
	
    private String remark;
	
	
    private String approved;
	
	
    private String approveAuthority;
	
	
    private String approver;
	
	
	
    private Date approveDate;
	
	
	
    private String approveContent;

}
