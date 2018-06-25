package com.thinvent.zhhd.common.vo;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class ShipViolationVO {
	private String svId;
	private String cnName;
	private String enName;
	private String mmsi;
	private String shipCategory;
	private String violationType;
	private Date violationTime;
	private String mpdId;
	private String cameraId;
	private String reason;
	private String comfirm;
	private String comfirmor;
	private Date comfirmDate;
	private String remark;
	private List<String> svIds;
}
