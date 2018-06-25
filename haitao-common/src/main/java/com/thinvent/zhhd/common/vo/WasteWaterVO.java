package com.thinvent.zhhd.common.vo;
import com.thinvent.library.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
@Accessors(chain = true)
@Setter
@Getter
public class WasteWaterVO {
	public WasteWaterVO(){
		this.wwId = UUIDUtil.getUuid();
	}
	private String wwId;
	private String reportId;
    private String reportNo;
    private String tankNo;
    private String waterType;
    private Float amount;
    private String tankType;
    private String remark;

}
