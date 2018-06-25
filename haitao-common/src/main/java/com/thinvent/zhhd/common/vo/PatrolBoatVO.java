package com.thinvent.zhhd.common.vo;

import com.thinvent.library.util.UUIDUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class PatrolBoatVO {

	public PatrolBoatVO() {
		this.id = UUIDUtil.getUuid();
		this.enable = 1;
	}
	private String id;
	private String name;
	private String type;
 	private String mmsi;
	private Float length;
	private Float width;
	private String msId;
	private String msName;
	private String linkMan;
	private String linkway;
	private String pbImage;
	private String remark;
	private int enable;
	private String oprMan;
	private CameraVO camera;
}
