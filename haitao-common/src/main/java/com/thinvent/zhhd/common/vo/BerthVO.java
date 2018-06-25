package com.thinvent.zhhd.common.vo;

import com.thinvent.library.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class BerthVO {
	public BerthVO() {
		this.berthId = UUIDUtil.getUuid();
	}	
	private String berthId;
	private String berthCode;
	private String berthName;
	private String wharfId;
	private String remark;
}
