package com.thinvent.zhhd.common.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
@Accessors(chain = true)
@Setter
@Getter
public class VoilationMonitorAisVO {
	private String mmsi;
	private String cnName;
	private String enName;
	private String shipCategory;
	private Date enterTime;
	private String svId;
	private String dictItemValue;
	private String direction;
	private String mpdId;
	private String speed;
	private String mpLocation;
	private String filePath;
	private String mpType;
	private String comfirm;



}
