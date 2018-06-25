package com.thinvent.zhhd.common.vo;

import java.util.Date;
import java.util.List;

import com.thinvent.library.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;

/**   
* @Description: InsEquipmentGroupVO 
* @author tanshumei  
*/
@Getter
@Setter
public class InsEquipmentGroupVO {
	public InsEquipmentGroupVO(){
		this.equipmentGroupId = UUIDUtil.getUuid();
		this.createTime = new Date();
	}
	private String equipmentGroupId; // id
	
	private String equipmentGroupName; // 巡检设备组名称
	
	private Date createTime;// 创建时间
	
	private String userId; // 添加人
	
	private String remark; // 备注

    private List equipmentGroupAssetList; // 设备组资产中间表list
}
