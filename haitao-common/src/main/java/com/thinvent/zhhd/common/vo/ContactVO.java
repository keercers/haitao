package com.thinvent.zhhd.common.vo;

import java.util.List;

import com.thinvent.library.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class ContactVO {
	
	public ContactVO() {
		this.groupId = UUIDUtil.getUuid();
	}
	
	private String groupId;
	private String groupName;
	private List<ContactorVO> contactor;
}
