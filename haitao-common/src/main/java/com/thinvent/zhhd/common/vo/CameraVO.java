package com.thinvent.zhhd.common.vo;

import com.thinvent.library.util.UUIDUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class CameraVO {

	public CameraVO() {
		this.cameraId = UUIDUtil.getUuid();
	}

	private String cameraId;
	private String mpId;
	private String usage;
	private String direction;
	private Double lng;
	private Double lat;
	private String deviceType;
	private String ip;
	private Integer port;
	private String merchant;
	private String userName;
	private String pwd; 
	private String description;
	private String deviceTypeValue;
	private String mpName;
	private String deviceName;
	private String deviceNumber;
	private String vcnNumber;
	private String vcnType;
	private String vcnName;
	private String vcnUserName;
	private String vcnPassword;
	private String vcnIp;
	private String vcnPort;
	private String cameraCode;
}
