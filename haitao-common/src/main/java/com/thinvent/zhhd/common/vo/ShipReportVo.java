package com.thinvent.zhhd.common.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinvent.library.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class ShipReportVo {
	public ShipReportVo(){
		this.reportId = UUIDUtil.getUuid();
	}
	
    private String reportId;
    private String shipCategory;
    private String berthName;
    private String shipCategoryValue;//add
    private String cnName;
    private String isEnter;
    private String cnStart;
    private String port;
    private Date reportTime;
    private String cargoName;//add
    private String mmsi;
    
    private String reportNo;
    private String reportBillType;
    private String shipId;
   
 
    private String businessType;
    private Float voyageNum;
    private String checker;
   
    private String enStart;
    private String berthCode;
   
    private Date elTime;
    private Date startTaskTime;
    private Date endTaskTime;
    private String flag;
    private String secondVessel;
    private String lighter;
    private String taskContent;
    private String shipImage;
    
    private String waterCleaningTank;
    private String additiveCleaningTank;
    private String crudeCleaningTank;
    private String gasCleaningTank;
    private Float wasteDischarge;

    
   

	}
 

