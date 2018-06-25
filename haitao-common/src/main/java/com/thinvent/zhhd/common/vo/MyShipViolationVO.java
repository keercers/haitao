package com.thinvent.zhhd.common.vo;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class MyShipViolationVO {
	private String svId;
	private String cnName;
	private String enName;
	private String mmsi;
	private String shipCategory;
	private String shipCategoryValue;
	private String violationType;
	private String violationTypeValue;
	private String mpName;
	private String mpLocation;
	private String direction;
	private String speed;
	private String isMerge;
	private Date violationTime;
	private String mpdId;
	private String cameraId;
	private String remark;
	private String reason;
	private String comfirm;
	private String comfirmor;
	private Date comfirmDate;
	private String filePath;
	private String extension;
	private String width;
	private String height;
	private String mppId;
	private List<MonitorPointPhotoVO> photoList;
}
