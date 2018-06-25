package com.thinvent.zhhd.common.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinvent.library.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;

/**   
* @Description: 巡检记录VO
* @author tanshumei  
*/
@Getter
@Setter
public class InspectionRecordVO {
	public InspectionRecordVO(){
		this.recordId=UUIDUtil.getUuid();
		this.enable = 1;
	}
	
	private String recordId; // id
	
    private String recordName; // 巡检记录名称
	
    private String planId; // 巡检计划ID
    
    private String planName;
    
    private String assetName;// 设备名称
    
    private String assetsNumber;// 设备编号
    
    private String startTimeString;
    
    private String endTimeString;
    
    private List peopleList; // 运维人员
    
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date startTime; // 巡检记录开始时间
	
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date endTime; // 巡检记录结束时间
	
    private String people; // 巡检记录人员
	
    private String remark; // 备注
    
    private List equipmentGroupAssetList; // 中间表List
    
    private int enable;
}
