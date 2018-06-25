package com.thinvent.zhhd.common.vo;

import com.thinvent.library.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class MaritimeStationVO {
	
	public MaritimeStationVO() {
		this.msId = UUIDUtil.getUuid();
		this.enable = 1;
	}
	private String msId;
	private String msName;
	private String msLocation;
	private String orgId;
	private Double lng;
	private Double lat;
	private String linkman;
	private String linkway;
	private String remark;
	private String msImage;
	private int enable;
}
