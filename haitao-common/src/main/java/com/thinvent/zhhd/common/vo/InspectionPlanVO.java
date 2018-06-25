package com.thinvent.zhhd.common.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinvent.library.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;

/**   
* @Description: 巡检计划VO 
* @author tanshumei  
*/
@Getter
@Setter
public class InspectionPlanVO {
	
	public InspectionPlanVO(){
		this.enable = 1;
		this.planId = UUIDUtil.getUuid();
	}
	
	private String planId;
	
    private String planName; // 巡检计划名称
    
    private String assetsId; // 资产ID 即设备ID
    
    private String assetName; // 资产名称即设备名称
    
    private String assetsNumber; // 资产编号即设备编号
    
    private String people; // 运维人员名称拼接字段
    
    private List peopleList; // 运维人员list
	
    private String startTimeString; // 巡检计划开始时间
	
    private String endTimeString; // 巡检计划结束时间
    
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date startTime; // 巡检计划开始时间
	
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date endTime; // 巡检计划结束时间
	
    private String remark; // 备注
    
    private int enable; // 是否可用

	private List equipmentGroupAssetList; // 中间表List
	
	
}
