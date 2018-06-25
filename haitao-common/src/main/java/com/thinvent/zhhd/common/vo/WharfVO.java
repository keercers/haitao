package com.thinvent.zhhd.common.vo;

import com.thinvent.library.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class WharfVO {
	public WharfVO() {
		this.wharfId = UUIDUtil.getUuid();
		this.enable = 1;
    }

    private String wharfId;
    private String wharfCid;
    private String wharfName;
    private String address;
    private String machine;
    private Integer localNum;
    private Integer foreignNum;
    private String thruputByYear;
    private String buildArea;
    private String areaType;
    private String areaUsage;
    private String waterwayId;
    private String legalPerson;
    private Float length;
    private Integer portNum;
    private String axProof;
    private String experiencePermits;
    private String geometryType;
    private String geometry;
    private String areaTypeValue;
    private String areaUsageValue;
    private String wharfType;
    private String wharfTypeValue;
    private String region;
    private String regionValue;
    private Integer sortValue;
    private String cargoInfo;
    private String orgId;
    private String description;
    private String contact;
    private String wharfImage; //码头图片
    private String wharfNumber; //码头编号
    private String geometryCenter;
    private String msId; 
    private String wharfCertificatePeriod;
    private String contactPhone;
    private int enable;
}
