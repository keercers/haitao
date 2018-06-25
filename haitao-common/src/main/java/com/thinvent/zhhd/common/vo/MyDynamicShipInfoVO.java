package com.thinvent.zhhd.common.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class MyDynamicShipInfoVO {
    private String dsiId;
    private String mmsi;
    private Double lng;
    private Double lat;
    private Float speed;
    private String mpdId;
    private String enterMpdId;
    private Date enterTime;
    private Date aisTime;
    private Date mpdTime;
    private Double userX;
    private Double userY;
    private Float shipHeading;
    private Float shipCourseDirection;
    private Float shipTurnRate;
    private String shipStatus;
    private String cnName;
    private String enName;
    private String shipCategory;
    private String shipCategoryValue;
}
