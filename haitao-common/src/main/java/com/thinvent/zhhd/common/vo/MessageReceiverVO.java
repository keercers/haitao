package com.thinvent.zhhd.common.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class MessageReceiverVO {
	
	private String mrId;
	
    private String fullName;
	
    private String cellPhone;
	
    private String isShipman;
	
    private String msgId;
	
    private String userId;
    
    private String reportStat;
    
    private String reportStatDes;
}
