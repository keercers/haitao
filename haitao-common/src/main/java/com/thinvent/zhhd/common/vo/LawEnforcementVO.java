package com.thinvent.zhhd.common.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinvent.library.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class LawEnforcementVO {
	
	public LawEnforcementVO() {
		this.leId = UUIDUtil.getUuid();
		this.isReport = 0;
	}
	private String leId;
	private String leType;
	private String leTypeValue;
	private String leCause;
	private String leResult;
	private Double leMoney;
	private String leNumber;
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date leTime;
	private String leMan;
	private String pbId;
	private String pbName;
	private String leImage;
	private String remark;
	private String shipId;
	private int isReport;
	private String shipName;
	private String mmsi;
	private String shipImage;
	private String leTimeString;
}
