package com.thinvent.zhhd.common.vo;

import java.util.Date;

import com.thinvent.library.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class PatrolBoatArchivesVO {
	
	public PatrolBoatArchivesVO() {
		this.pbaId = UUIDUtil.getUuid();
	}
	private String pbaId;
	private String pbId;
	private String oprMan;
	private String oprContent;
	private Date oprTime;
}
