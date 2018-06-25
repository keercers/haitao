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
public class DrillVO {

	public DrillVO() {
		this.drillId = UUIDUtil.getUuid();
	}

	private String drillId;

	private String drillName;

	private String preplanId;

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;

	private String score;

	private String assess;

	private String oprMan;

	private String shipId;

	private String mmsi;

	private String msgId;

	private String text;

	private String reportTime;

	private String fullName;

	private String drillImage;

	private String cameraIp;

	private long beginTime;

	private String duration;

	private String reserved;

	private String fileId;
}
