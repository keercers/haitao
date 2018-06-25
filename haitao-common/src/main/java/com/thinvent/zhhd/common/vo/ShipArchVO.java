package com.thinvent.zhhd.common.vo;

import java.util.Date;

import com.thinvent.library.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class ShipArchVO {

	public ShipArchVO() {
		this.spaId = UUIDUtil.getUuid();
	}

	private String spaId; // 船舶档案ID
	private String shipId;
	private String oprMan;
	private String oprContent;
	private Date oprTime;
}
