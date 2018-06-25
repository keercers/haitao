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
public class KnowledgeVO {

	public KnowledgeVO() {
		this.knowId = UUIDUtil.getUuid();
	}

	private String knowId;

	private String knowName;

	private String knowContent;

	private String knowCategory;

	private String knowBaseId;

	private String remark;

	private String creater;

	private String attachment;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date createTime;

	private int enable;
}
