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
public class EmergencyEventVO {
	public EmergencyEventVO(){
		this.eventId = UUIDUtil.getUuid();
	}
	private String eventId;
	private String eventName;          //应急事件名称
	private String eventType;          //事件类型
	private String executor;           //执行人
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date executeTime;        //时间
	private String executeBoat;       //执行巡逻艇
	private String images;           //上传
	private String eventDescribe;    //事件描述
	private String disposalResult;   //处置结果
	private int isReport;            //是否已上报   1上报  0 未上报
	private String typeValue;
	private String executeTimeString;
	private String userName;
	private String boatName;
}
