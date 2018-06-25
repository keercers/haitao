package com.thinvent.zhhd.common.vo;

import com.thinvent.library.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OPSOrganizationVO {
	public OPSOrganizationVO(){
		this.opsOrgId = UUIDUtil.getUuid();
	}
	private String opsOrgId;
	private String opsId;
	private String opsName;
	private String orgId;
	private String orgName;
}
