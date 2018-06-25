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
public class ContactGroupVO {
	public ContactGroupVO() {
		this.groupId = UUIDUtil.getUuid();
		this.createTime = new Date();
	}

	private String groupId;
	private String groupName;
	private Date createTime;
	private List<String> contactId;
}
