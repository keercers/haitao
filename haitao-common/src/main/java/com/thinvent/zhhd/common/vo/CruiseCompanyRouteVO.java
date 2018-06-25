package com.thinvent.zhhd.common.vo;

import com.thinvent.library.util.UUIDUtil;

import lombok.Data;
import lombok.experimental.Accessors;

/** 
* 类说明  中间表 巡查组织-查询条件
*/
@Accessors(chain = true)
@Data
public class CruiseCompanyRouteVO {
	
	public CruiseCompanyRouteVO(){
		this.ecoId = UUIDUtil.getUuid();
		this.enable = 1;
	}	
	private String ecoId; // 巡查组织id
	
	private String comId; // 巡查机构id 关联Company
	
	private String routeId; // 巡查路线id 关联CruiseRoute
	
	private int enable;
   
}
