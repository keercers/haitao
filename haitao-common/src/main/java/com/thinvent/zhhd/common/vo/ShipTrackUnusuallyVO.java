package com.thinvent.zhhd.common.vo;
import java.util.Date;
import com.thinvent.library.util.UUIDUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class ShipTrackUnusuallyVO {
	public ShipTrackUnusuallyVO(){
		this.unusuallyId = UUIDUtil.getUuid();
	}
	
	private  String unusuallyId;
	private  String unusuallyType;//异常类型 枚举 1:非法停靠；2:中途违停；3:轨迹异常；4:AIS信号丢失（60分钟没收到AIS信号）；5:船舶监控超时（AIS丢失超过48小时）
	private  String shipKind;
	private  String mmsi;
	private  String reportId;
	private  String wharfId;
	private  Date firstAisTime;
	private  Date unusuallyStartTime;
	private  Date unusuallyEndTime;
	private  String comfirm;
	private  String comfirmor;
	private  Date comfirmDate;
	private  String remark;
	private  String realWharfId;
	@Override
	public String toString() {
		return "ShipTrackUnusuallyVO [unusuallyId=" + unusuallyId + ", unusuallyType=" + unusuallyType + ", shipKind="
				+ shipKind + ", mmsi=" + mmsi + ", reportId=" + reportId + ", wharfId=" + wharfId + ", firstAisTime="
				+ firstAisTime + ", unusuallyStartTime=" + unusuallyStartTime + ", unusuallyEndTime=" + unusuallyEndTime
				+ ", comfirm=" + comfirm + ", comfirmor=" + comfirmor + ", comfirmDate=" + comfirmDate + ", remark="
				+ remark + ", realWharfId=" + realWharfId + "]";
	}
	
	
	 
    
	}

 

