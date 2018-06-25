package com.thinvent.zhhd.common.vo;

import com.thinvent.library.util.UUIDUtil;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class WaterWayVO {
	public WaterWayVO(){
		this.waterwayId = UUIDUtil.getUuid();
	}	
	private String waterwayId;
    private String waterwayName;
    private String orgId;
    private String orgName;
    private String waterwayLevel;
    private String waterwayLevelName;
    private String geometry;
    private String geometryType;
    private Float width;
    private String waterwayColor;
    private String waterwayNumber; // 航道编号，便于资产管理
    private String waterwayLocation; // 航道位置
}
