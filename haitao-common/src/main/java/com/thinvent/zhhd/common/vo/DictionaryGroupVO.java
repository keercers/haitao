package com.thinvent.zhhd.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class DictionaryGroupVO {

	private String dictGroupId;
	private String dictGroupName;
	private int enabled;
}
