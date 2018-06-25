package com.thinvent.zhhd.common.vo;

import java.sql.Clob;
import java.util.Date;
import com.thinvent.library.util.UUIDUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class TrackAbnomalVO {
	public TrackAbnomalVO(){
		this.unusuallyId = UUIDUtil.getUuid();
	}
	
	private  String unusuallyId;
	private  String unusuallyType;
	private  String shipKind;
	private  String mmsi;
	private  String reportId;
	private  String wharfId;
	private  Clob usuallyTrack;//Clob
	private  Date firstAisTime;
	private  Date unusuallyStartTime;
	private  Date unusuallyEndTime;
	private  String comfirm;
	private  String comfirmor;
	private  Date comfirmDate;
	private  String remark;
	private  String realWharfId;
 
}
