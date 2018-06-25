package com.thinvent.zhhd.common.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class RouteVO {
	
	private String routeId; // 路线id
    
    private String routeName;//路线名称 
	
    private String description;	//描述
    
	private String orgId; //机构id
	
	private String orgName; //机构名称
	
    private int enable; // 是否可用    
    
    private String userId;   
    
}
