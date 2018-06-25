package com.thinvent.zhhd.common.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UndeclaredWarningVO {
	private String svId;
	private String mmsi;
	private String violationType;
	private String mpdId;
	private Date violationTime;
	private String comfirm;
	private String comfirmor;
	private Date comfirmDate;
	private String remark;
	private String shipId;
	private String cnName;
	private String enName;
	private String shipCategory;
	private String shipCategoryName;
}
