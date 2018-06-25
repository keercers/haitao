package com.thinvent.zhhd.common.vo;

import com.thinvent.library.util.UUIDUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class KnowledgeBaseVO {

	public KnowledgeBaseVO() {
		this.knowBaseId = UUIDUtil.getUuid();
	}

	private String knowBaseId;

	private int enable;

	private String knowBaseName;
}
