package com.thinvent.zhhd.common.vo;

import java.util.List;

import com.thinvent.library.util.UUIDUtil;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class CruiseNoteVO {
	
	public CruiseNoteVO(){
		this.noteId = UUIDUtil.getUuid();
	}
	
	private String noteId;
	private String routeId; // 路线ID
	private String monitorPointId; //监控点ID
	private String cameraId; // 摄像头ID
	private int sortValue; // 排序
	private List cameraList;
}
