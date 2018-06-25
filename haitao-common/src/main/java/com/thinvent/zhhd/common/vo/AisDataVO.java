package com.thinvent.zhhd.common.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AisDataVO implements IAisDataVO {
	private String aisId;
    private String cnName;
    private String enName;
    private String callNo;
    private String mmsi;
    private String imo;
    private String shipCategory;
    private String deviceType;
    private Float shipLength;
    private Float shipWidth;
    private Float draugth;
    private Double lng;
    private Double lat;
    private Float shipHeading;
    private Float shipCourseDirection;
    private Float speed;
    private String status;
    private String port;
    private Date gpsTime;
    private Double userX;
    private Double userY;
    private Float shipTurnRate;

    private Float shipPath;
	private Date createTime;
}
