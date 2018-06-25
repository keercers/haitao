package com.thinvent.zhhd.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class ComplexVO {
	private String findtime;
	private String direction;
	private String kcCount;
	private String phCount;
	private String yhCount;
	private String gcCount;
	private String gzCount;
	private String tcCount;
	private String qtCount;
	private String countTotal;
	private String kcDwt;
	private String phDwt;
	private String yhDwt;
	private String gcDwt;
	private String gzDwt;
	private String tcDwt;
	private String qtDwt;
	private String dwtTotal;
}
