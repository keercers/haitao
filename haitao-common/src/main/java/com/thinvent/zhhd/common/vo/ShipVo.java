package com.thinvent.zhhd.common.vo;

import java.util.Date;

import com.thinvent.library.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class ShipVo {
	public ShipVo() {
		this.shipId = UUIDUtil.getUuid();
	}

	private String shipId;
	private String shipCid;

	private String shipType;

	private String mmsi;

	private String registerNo;

	private String firstNo;

	private String checkNo;

	private String cnName;

	private String enName;

	private String imo;

	private String callNo;

	private String noteNo;

	private String isInlandRiver;

	private String country;

	private String route;

	private String checker;

	private String navigateArea;

	private String registry;

	private String maritimeAgency;

	private String shipCategory;
	
	private Date buildDate;

	private Float gt;

	private Float nt;

	private Float dwt;

	private String owner;

	private String operator;

	private String mainPower;

	private Integer mainNum;

	private String mainType;

	private Float length;

	private Float width;

	private Float depth;

	private String material;

	private String propellerCategory;

	private Integer propellerNum;

	private Integer boxNum;

	private Integer vehicleNum;

	private Integer visitorNum;

	private Float summerDraft;

	private Float minFreeboard;

	private String windLoading;

	private Float speed;

	private Integer minNum;

	private Integer saveNum;

	private String remark;

	private String opName;

	private Date opTime;

	private String shipTypeValue;

	private String shipKindValue;

	private String shipCategoryValue;

	private String shipImage;// new add

	private String phoneNum;// new add

	private String shipNum;

	private String aisNum;

	private String rfidNum;

	private String ShipKind;

	private String oprMan;

	private String linkMan;

	private String isBlack;

	private String blackType;

	private String blackTypeValue;

	private String blackRemark;
	
	private Date updateTime;

}
