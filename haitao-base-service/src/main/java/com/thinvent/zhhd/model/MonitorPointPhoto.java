package com.thinvent.zhhd.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name="MonitorPointPhoto")
@Accessors(chain = true)
@Setter
@Getter
public class MonitorPointPhoto {
	@Id
	@Column(name="mppId",length=36,nullable=false)
    private String mppId;
	
	@Column(name="mpdId",length=36,nullable=false)
    private String mpdId;
	
	@Column(name="cameraId",length=36)
    private String cameraId;

	@Column(name="width",length=10)
    private Integer width;
	
	@Column(name="height",length=10)
    private Integer height;
	
	@Column(name="extension",length=20)
    private String extension;
	
	@Column(name="filePath",length=256)
    private String filePath;
	
	@Column(name="createTime",length=7)
    private Date createTime;
}
