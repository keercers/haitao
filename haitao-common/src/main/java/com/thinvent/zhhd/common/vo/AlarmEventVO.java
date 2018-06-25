package com.thinvent.zhhd.common.vo;

import java.util.Date;
import com.thinvent.library.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@Accessors(chain = true)
@Setter
@Getter
public class AlarmEventVO {
	public AlarmEventVO() {
		this.alarmId = UUIDUtil.getUuid();
	}
	
	private String alarmId;	

    private String alarmName;

    private String alarmType;
    
    private String alarmTypeValue;

    private String mmsi;

	private Date alarmTime;
	
	private String alarmTimeString;
}

