package com.thinvent.zhhd.common.vo;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class MyMonitorPointDataVO {
    private String enterTime;
	private String createTime;
	private String filePath;
	private String deviceId;
	private String mmsi;
	private String direction;
	private String speed;
	private String shipCategory;
	private String cnName;
	private String enName;
	private String mpdId;
	private String dictItemValue;
	private String remark;
	private String comfirm;
}
