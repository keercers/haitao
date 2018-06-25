package com.thinvent.zhhd.common.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IntoNotifyVO {
	 private Date enterTime;
	 private String mpdId;
	 private String cnName;
	 private String mpId;
	 private String direction;
	 private String reportId;
	 private String mpLocation;
	 private String mpType;
	 private String mpTypeValue;
	 private String filePath;
	 private String svId;
	 private String violationType;
	 private String violationTypeValue;
	 private String comfirm;
	 private String comfirmor;
	 private String comfirmorName;
	 private Date comfirmDate;
}
