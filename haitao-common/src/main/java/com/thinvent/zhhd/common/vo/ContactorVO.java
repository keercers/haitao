package com.thinvent.zhhd.common.vo;

import java.util.Date;
import java.util.List;

import com.thinvent.library.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class ContactorVO {

	public ContactorVO() {
		this.contactId = UUIDUtil.getUuid();
		this.createTime = new Date();
		this.updateTime = new Date();
	}

	private String contactId;
	private String contactName;
	private String cellPhone;
	private String cnName;
	private String registerNo;
	private String mmsi;
	private String shipType;
	private Date createTime;
	private Date updateTime;
	private List<String> groupId;

}
