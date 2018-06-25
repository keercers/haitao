package com.thinvent.zhhd.common.vo;

import com.thinvent.library.util.UUIDUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class PreplanVO {

	public PreplanVO() {
		this.preplanId = UUIDUtil.getUuid();
	}

	private String preplanId;

	private String preplanName;

	private String preplanImage;
}
