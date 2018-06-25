package com.thinvent.zhhd.common.vo;

import com.thinvent.library.util.UUIDUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class MonitorPointVO {

	public MonitorPointVO() {
		this.mpId = UUIDUtil.getUuid();
		this.enable = 1;
	}

	private String mpId;
	private String mpName;
	private String mpLocation;
	private Double lng;
	private Double lat;
	private Double userX;
	private Double userY;
	private String mpType;
	private String specialMark;
	private String enterDirection;
	private String exitDirection;
	private Integer enable;
	private String waterwayId;
	private String mpTypeValue;
	private String mpImage;
}
