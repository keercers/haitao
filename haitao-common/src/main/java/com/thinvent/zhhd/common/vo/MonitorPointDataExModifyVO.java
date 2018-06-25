package com.thinvent.zhhd.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class MonitorPointDataExModifyVO {
	private String dataType;
	private String merge;
	private String beginTime;
	private String endTime;
	private String isEmpty;
	private String mpId;
	private String dataFlag;
	private String dwtFrom;
	private String dwtTo;
	private String lengthFrom;
	private String lengthTo;
	private String widthFrom;
	private String widthTo;
	private String freeboardFrom;
	private String freeboardTo;
	private String shipName;
	private String shipType;
	private String direction;
	private String isOverLoad;
	private String mpType;
	private int pageIndex;
	private int pageSize;
}
