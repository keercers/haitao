package com.thinvent.zhhd.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.BeanUtils;

import com.thinvent.zhhd.common.vo.MonitorPointDataVO;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "MonitorPointData")
@Accessors(chain = true)
@Setter
@Getter
public class MonitorPointData {
	@Id
	@Column(name = "mpdId", length = 36, nullable = false)
	private String mpdId;

	@Column(name = "dataType", length = 1)
	private String dataType;

	@Column(name = "isMerge", length = 1)
	private String isMerge;

	@Column(name = "mergeType", length = 1)
	private String mergeType;

	@Column(name = "shipId", length = 36)
	private String shipId;

	@Column(name = "mmsi", length = 20)
	private String mmsi;

	@Column(name = "cnName", length = 50)
	private String cnName;

	@Column(name = "enName", length = 50)
	private String enName;

	@Column(name = "shipCategory", length = 40)
	private String shipCategory;

	@Column(name = "cargoCategory", length = 40)
	private String cargoCategory;

	@Column(name = "length", length = 15)
	private Double length;

	@Column(name = "width", length = 15)
	private Double width;

	@Column(name = "freeboard", length = 15)
	private Double freeboard;

	@Column(name = "isEmpty", length = 1)
	private String isEmpty;

	@Column(name = "direction", length = 1)
	private String direction;

	@Column(name = "shipType", length = 20)
	private String shipType;

	@Column(name = "count", length = 10)
	private Integer count;

	@Column(name = "speed", length = 15)
	private Double speed;

	@Column(name = "dwt1", length = 15)
	private Double dwt1;

	@Column(name = "dwt2", length = 15)
	private Double dwt2;

//	@Column(name = "firstCargo", length = 50)
//	private String firstCargo;
//
//	@Column(name = "secondCargo", length = 50)
//	private String secondCargo;

	@Column(name = "isOverload", length = 1)
	private String isOverload;

	@Column(name = "isOverlimit", length = 1)
	private String isOverlimit;

	@Column(name = "findTime", length = 7)
	private Date findTime;

	@Column(name = "mpId", length = 36)
	private String mpId;

	@Column(name = "deivceId", length = 36)
	private String deivceId;

	@Column(name = "reportId", length = 36)
	private String reportId;

	@Column(name = "dataFlag", length = 1)
	private String dataFlag;

	@Column(name = "dataStatus", length = 1)
	private String dataStatus;

	@Column(name = "IsIllegalDocking", length = 1)
	private String isIllegalDocking;

	@Transient
	private List<MonitorPointPhoto> photoList;
	@Transient
	private List<MonitorPointVideo> videoList;

	// Rfid数据属性；
	@Column(name = "rfidBoatName", length = 36)
	private String rfidBoatName;

	@Column(name = "rfidStationId", length = 36)
	private String rfidStationId;

	@Column(name = "rfidState", length = 36)
	private String rfidState;

	@Column(name = "rfidTime", length = 7)
	private Date rfidTime;

	@Column(name = "rfidRemark", length = 1)
	private String rfidRemark;

	public MonitorPointDataVO convertToMonitorPointDataVO() {
		MonitorPointDataVO monitorPointDataVO = new MonitorPointDataVO();
		BeanUtils.copyProperties(this, monitorPointDataVO);
		return monitorPointDataVO;
	}
}
