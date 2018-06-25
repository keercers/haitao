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
public class MonitorPointVideoVO {

	public MonitorPointVideoVO() {
		this.mpvId = UUIDUtil.getUuid();
	}

	private String mpvId;

	private String mpdId;

	private String cameraId;

	private String cameraCode;

	private String messageId;

	private String sourceId;

	private String fileId;

	private String filePath;

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date captureTime;

	private String width;

	private String heigth;

	private String length;

	private String extension;

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date createTime;

	private String mppId;
}
