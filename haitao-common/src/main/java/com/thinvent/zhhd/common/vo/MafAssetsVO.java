package com.thinvent.zhhd.common.vo;

import com.thinvent.library.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;

/**
* 故障资产表VO   
* @Description: MafAssetsVO
* @author hananxin  
*/
@Getter
@Setter
public class MafAssetsVO {
	
	public MafAssetsVO(){
		this.mafAssetsId = UUIDUtil.getUuid();
	}
	private String mafAssetsId; // id
	
	private String assetsId; // 巡检设备ID即资产ID\
	
	private String assetsNumber; //资产编号
	
	private String assetsName; //资产名称
	
	private String resolveEnd;  //处理结果
	
	private String resolveEndValue; // 处理结果值
	
	private String status;     //资产状态
	
	private String statusValue; //资产状态值
	 
    private String mafId; // 故障管理表 ID    
}
