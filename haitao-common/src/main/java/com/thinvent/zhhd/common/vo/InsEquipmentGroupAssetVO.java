package com.thinvent.zhhd.common.vo;

import com.thinvent.library.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;

/**
* 设备组资产表VO   
* @Description: InsEquipmentGroupAssetVO
* @author tanshumei  
*/
@Getter
@Setter
public class InsEquipmentGroupAssetVO {
	
	public InsEquipmentGroupAssetVO(){
		this.equipmentGroupAssetId = UUIDUtil.getUuid();
		this.enable = 1;
	}
	private String equipmentGroupAssetId; // id
	
	private String equipmentGroupId; // 巡检设备组ID
	
	private String assetsId; // 巡检设备ID即资产ID
	
	private String assetsName; // 资产名称即设备名称
	
	private String assetsNumber;
	
	private String assetsName2; // 设备编号和设备名称
	
    private String planId; // 计划ID
    
    private String planName;
    
    private String recordId; // 记录ID
    
    private String recordName;
    
    private int enable; // 删除标识
}
