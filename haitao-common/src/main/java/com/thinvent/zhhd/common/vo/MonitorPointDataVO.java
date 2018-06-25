package com.thinvent.zhhd.common.vo;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class MonitorPointDataVO {
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
	private String firstCargo;
	private String secondCargo;
	private String isOverload;
	private String isOverlimit;
	private String isIllegalDocking;
	//@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date findTime;
	private String mpId;
	private String deivceId;
	private String reportId;
	private String dataFlag;
	private String dataStatus;
	private List<String> pictureUrl;
    private List<MonitorPointPhotoVO> photoList;
    private List<MonitorPointVideoVO> videoList;
    
    //Rfid数据属性；
    private String rfidBoatName;
    private String rfidStationId;
    private String rfidState;
    private Date rfidTime;
    private String rfidRemark;
    
}
