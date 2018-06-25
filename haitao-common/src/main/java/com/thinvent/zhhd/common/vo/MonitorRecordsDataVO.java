package com.thinvent.zhhd.common.vo;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class MonitorRecordsDataVO {
    private String mpdId;
	private String dataType;
	private String isMerge;
	private String mergeType;
	private String shipId;
	private String mmsi;
	private String cnName;
	private String enName;
	private String shipCategory;
	private String cargoCategory;
	private Double length;
	private Double width;
	private Double freeboard;
	private String isEmpty;
	private String direction;
	private String shipType;
	private Integer count;
	private Double speed;
	private Double dwt1;
	private Double dwt2;
	private String fristCargo;
	private String secondCargo;
	private String isOverload;
	private String isOverlimit;
	private Date findTime;
	private String mpId;
	private String deivceId;
	private String reportId;
	private String dataFlag;
	private String dataStatus;
	private String mpName;
	private String mpLocation;
	private String messageId;
	private String status;
	private String description;
    private List<MonitorPointPhotoVO> photoList;
    private List<MonitorPointVideoVO> videoList;
}
