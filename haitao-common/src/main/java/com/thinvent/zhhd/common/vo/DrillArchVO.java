package com.thinvent.zhhd.common.vo;

import java.util.Date;

import com.thinvent.library.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class DrillArchVO {

	public DrillArchVO() {
		this.pdaId = UUIDUtil.getUuid();
	}

	private String pdaId; // 演练档案ID

	private String drillId;

	private String oprMan;

	private String oprContent;

	private Date oprTime;
}
