package com.thinvent.zhhd.common.vo;

import com.thinvent.library.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;

/**   
* @author tanshumei  
*/
@Getter
@Setter
public class OPSVO {
	public OPSVO() {
		this.opsId = UUIDUtil.getUuid();
		this.enable = 1;
	}
	
	private String opsId;
	private String opsName;
	private String opsTel;
	private String orgId;
	private String orgName;
	private int enable;
}
