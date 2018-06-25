package com.thinvent.zhhd.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.thinvent.zhhd.common.vo.MonitorPointVideoVO;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "MonitorPointVideo")
@Accessors(chain = true)
@Setter
@Getter
public class MonitorPointVideo {
	@Id
	@Column(name = "mpvId", length = 36, nullable = false)
	private String mpvId;

	@Column(name = "mpdId", length = 36, nullable = false)
	private String mpdId;

	@Column(name = "caremaId", length = 36)
	private String caremaId;

	@Column(name = "cameraCode", length = 255)
	private String cameraCode;

	@Column(name = "messageId", length = 36)
	private String messageId;

	@Column(name = "sourceId", length = 36)
	private String sourceId;

	@Column(name = "fileId", length = 36)
	private String fileId;

	@Column(name = "filePath", length = 256)
	private String filePath;

	@Column(name = "captureTime")
	private Date captureTime;

	@Column(name = "width", length = 10)
	private Integer width;

	@Column(name = "height", length = 10)
	private Integer height;

	@Column(name = "length", length = 8)
	private String length;

	@Column(name = "extension", length = 20)
	private String extension;

	@Column(name = "createTime")
	private Date createTime;

	@Column(name = "mppId", length = 36)
	private String mppId;

	public MonitorPointVideoVO convertToMonitorPointVideoVO() {
		MonitorPointVideoVO monitorPointVideoVO = new MonitorPointVideoVO();
		BeanUtils.copyProperties(this, monitorPointVideoVO);
		return monitorPointVideoVO;
	}
}
