package com.thinvent.zhhd.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class MessageReportVO {
	
	private String reportStat;
	
	private String reportStatDes;
	
	private String phoneNumber;

}
