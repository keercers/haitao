package com.thinvent.zhhd.common.vo;

import com.thinvent.library.util.UUIDUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class ShipDrillVO {

	public ShipDrillVO() {
		this.shipId = UUIDUtil.getUuid();
	}

	private String shipId;

	private String cnName;

	private String enName;

	private String registerNo;

	private String shipType;

	private String owner;

	private String operator;

	private String mmsi;

	private String shipCategory;

	private String shipCategoryValue;

	private String rfidNum;

	private String shipImage;

	private String enable;
}
