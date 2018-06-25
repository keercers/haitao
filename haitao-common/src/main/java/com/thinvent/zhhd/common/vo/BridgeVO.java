package com.thinvent.zhhd.common.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinvent.library.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class BridgeVO {
	
	public BridgeVO() {
		this.bridgeId = UUIDUtil.getUuid();
	}
	
	private String bridgeId;
    private String bridgeName;
    private String waterwayId;
    private Float mileage;
    private Integer wayNum;
    private Float c_height;
    private Float c_width;
    private Float s_height;
    private Float span;
    private Float leftAltitude;
    private Float rightAltitude;
    private Float maxWaterLevel;
    private Float minWaterLevel;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date buildTime;
    private String manager;
    private String geometryType;
    private String geometry;
    private String bridgeImage; //桥梁图片
    private String bridgeNumber; //桥梁编号
    private String bridgeLocation; // 位置
    private Integer sortValue;

    
}
