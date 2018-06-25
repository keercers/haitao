package com.thinvent.zhhd.common.vo;

import java.util.Date;
import java.util.List;

import com.thinvent.library.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
@Accessors(chain = true)
@Setter
@Getter
public class LawEnforceProcessVO {
	public LawEnforceProcessVO() {
		this.lepId = UUIDUtil.getUuid();
	}
	
	private String lepId;

	private String leId;

    private Date lepStartTime;
    
    private String lepStartTimeString;

    private Date lepEndTime;
    
    private String lepEndTimeString;

    private Double lepMiles;

    private Integer lepTimes;

	private String lepTrack;

	private String pbId;

	private String pbName;

	private String pbMmsi;
	
	private PatrolBoatVO patrolBoatVO;
	
	private List<LawEnforcementVO>  lawEnforcementVOList;


}
