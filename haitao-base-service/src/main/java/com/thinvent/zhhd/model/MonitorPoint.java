package com.thinvent.zhhd.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.BeanUtils;

import com.thinvent.zhhd.common.vo.MonitorPointVO;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "MonitorPoint")
@Accessors(chain = true)
@Setter
@Getter
public class MonitorPoint extends Monitor implements IMonitor {

	@Id
	@Column(name = "mpId", length = 36, nullable = false)
	private String mpId;

	@Column(name = "mpName", length = 50)
	private String mpName;

	@Column(name = "mpLocation", length = 50)
	private String mpLocation;

	@Column(name = "lng", columnDefinition = "double(18,15)")
	private Double lng;
	@Column(name = "lat", columnDefinition = "double(18,15)")
	private Double lat;
	@Column(name = "user_x", columnDefinition = "double(18,12)")
	private Double userX;
	@Column(name = "user_y", columnDefinition = "double(18,12)")
	private Double userY;

	@Column(name = "mpType", length = 20)
	private String mpType;
	@Column(name = "specialMark", length = 1)
	private String specialMark;
	@Column(name = "waterwayId", length = 36)
	private String waterwayId;

	@Column(name = "enable", length = 2)
	private int enable;

	@Column(name = "mpImage", length = 4000)
	private String mpImage; // 监控点图片

	@Transient
	private List<IDevice> deviceList = new ArrayList<IDevice>();
	@Transient
	private String mpd_mpId;
	@Transient
	private String mpd_direction;
	@Transient
	private String mpd_mpdIdNum;

	@Override
	public String getSpecialMark() {
		return specialMark;
	}

	@Override
	public void setSpecialMark(String specialMark) {
		this.specialMark = specialMark;
	}

	@Override
	public String getMonitorId() {
		return this.mpId;
	}

	@Override
	public void setMonitorId(String monitorId) {
		this.mpId = monitorId;
	}

	@Override
	public String getMonitorName() {
		return this.mpName;
	}

	@Override
	public void setMonitorName(String monitorName) {
		this.mpName = monitorName;
	}

	@Override
	public String getMonitorLocation() {
		return this.mpLocation;
	}

	@Override
	public void setMonitorLocation(String monitorLocation) {
		this.mpLocation = monitorLocation;
	}

	@Override
	public String getMonitorType() {
		return this.mpType;
	}

	@Override
	public void setMonitorType(String monitorType) {
		this.mpType = monitorType;
	}

	@Override
	public List<IDevice> getDeviceList() {
		return this.deviceList;
	}

	@Override
	public void setDeviceList(List<IDevice> deviceList) {
		this.deviceList = deviceList;

	}

	@Override
	public IDevice getDeviceById(String deviceId) {
		for (int i = 0; i < this.deviceList.size(); i++) {
			IDevice device = this.deviceList.get(i);
			if (device.getDeviceId() == deviceId) {
				return device;
			}
		}
		return null;
	}

	@Override
	public void addDevice(IDevice device) {
		if (!this.deviceList.contains(device)) {
			this.deviceList.add(device);
		}
	}

	@Override
	public void removeDeviceById(String deviceId) {
		int removeIndex = -1;
		for (int i = 0; i < this.deviceList.size(); i++) {
			IDevice device = this.deviceList.get(i);
			if (device.getDeviceId() == deviceId) {
				removeIndex = i;
				break;
			}
		}
		if (removeIndex != -1) {
			this.deviceList.remove(removeIndex);
		}
	}

	@Override
	public void clearDeviceList() {
		this.deviceList.clear();
	}

	public MonitorPointVO convertToMonitorPointVO() {
		MonitorPointVO monitorPointVO = new MonitorPointVO();
		BeanUtils.copyProperties(this, monitorPointVO);
		return monitorPointVO;
	}

}
