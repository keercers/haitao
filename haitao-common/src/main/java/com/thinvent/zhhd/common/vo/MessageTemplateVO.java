package com.thinvent.zhhd.common.vo;

import java.util.Date;

import com.thinvent.library.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class MessageTemplateVO {

	public MessageTemplateVO() {
		this.mtId = UUIDUtil.getUuid();
		this.modityTime = new Date();
	}

	private String mtId;

	private String text;

	private String creator;
	
	private String creatorName;

	private Date modityTime;
}
