package com.thinvent.zhhd.common.vo;

import java.util.Date;



import com.thinvent.library.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class CargoVo {
	public CargoVo(){
		this.cargoId = UUIDUtil.getUuid();
	}
	
	private String cargoId;
	
	private String reportId;

    private String flowDirection;
	

    private String transfer;
	
	
    private String transit;
	
	
    private String specialCargo;
	
	
    private String cargoCategory;
	
	
    private String imdgCode;
	
	
    private String dangerType;
	
	
    private String cnName;
	
	
    private String enName;
	
	
    private String ladingBillNo;
	
	
    private Float density;
	
	
    private Float viscosity;
	
	
    private Float proportion;
	
	
    private String polluteType;
	
	
    private String oilType;
	
	
	
    private Float flashPoint;
	
	
	
    private String lpgType;
	

	
    private Float amount;
	

	
    private String taskPattern;
	

	
    private String cargoTankNo;
	
	
    private String cargoTankInerting;
	
	
    private Float temp;
	
	
    private String startPort;
	
	
    private String shipper;
	
	
    private String owner;
	

    private String reportStatus;
	

    private String checker;
	

    private Date checkerDate;
	

    private String checkContent;
	

    private String remark;
   
  
 
}
