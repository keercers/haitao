package com.thinvent.zhhd.common.vo;

import com.thinvent.library.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**   
* @Description: RFIDVO
* @author tanshumei  
* @date 2017年12月7日 下午1:59:25 
*/
@Accessors(chain = true)
@Getter
@Setter
public class RFIDVO {
	
	public RFIDVO() {
		this.rfidId = UUIDUtil.getUuid();
		this.enable = 1;
	}
	
	private String rfidId;
	
	private String rfidNum; // rfid编号
	
    private String rfidName; // rfid 名称
    
    private String waterwayId; // 所属航道
    
    private String rfidLocation; // rfid 实际地理位置
    
    private Double rfidLongitudeEn; // 经度(入)
    
    private Double rfidLongitudeEx; // 经度(出)
    
    private Double rfidLatitudeEn; // 纬度(入)
    
    private Double rfidLatitudeEx; // 纬度(出)
    
    private String mpId; // 视频识别点
    
    private String remark; // 备注
    
    private int enable;
}
