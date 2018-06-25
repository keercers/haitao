package com.thinvent.zhhd.common.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class MonitorPointPhotoVO {
    private String mppId;
    private String mpdId;
    private String cameraId;
    private Integer width;
    private Integer height;
    private String extension;
    private String filePath;
    private Date createTime;
}
