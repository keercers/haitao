package com.thinvent.zhhd.common.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class MonitorPointDataModifyVO {
	public MonitorPointDataModifyVO(){
		this.updateTime = new Date();
	}
	
	private String mpdId;
    private Float length; 
    private Float width; 
    private Float freeboard; 
	private String isEmpty; // 是否为空
	private String direction; 
    private Float speed; 
    private Float dwt;
	private String updateUser; // 更新人员
	private Date updateTime; // 更新时间
}
