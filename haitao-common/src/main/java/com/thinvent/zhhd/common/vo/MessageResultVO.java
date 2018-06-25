package com.thinvent.zhhd.common.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class MessageResultVO {

	private List<MessageReportVO> msgReportList;
	
	private String submitStat;
	
	private String submitStatDes;
	
	private String msgId;
}
