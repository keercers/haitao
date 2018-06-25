package com.thinvent.zhhd.common.vo;

import java.util.List;

import com.thinvent.library.util.UUIDUtil;

import lombok.Data;
import lombok.experimental.Accessors;


@Accessors(chain = true)
@Data
public class CruiseRouteVO {
	public CruiseRouteVO(){
		this.routeId = UUIDUtil.getUuid();
		this.enable = 1;
	}	
	private String routeId;
	private String routeName;	
	private String routerDescription;	
    private int enable; // 是否可用
    private List companyRouteList; // 中间表list
    private List monitorList; // 监控点list 里面放置monitorPointId, sortValue, 摄像头 cameraList
}
