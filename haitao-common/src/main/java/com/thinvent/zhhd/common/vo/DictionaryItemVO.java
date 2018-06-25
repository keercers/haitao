package com.thinvent.zhhd.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class DictionaryItemVO {

	private String dictItemId;

	private String dictItemKey;

	private String dictItemValue;

	private String dictItemFullKey;

	private String dictItemFullValue;

	private String parentItemId;

	private String dictGroupId;

	private int enabled;
}
