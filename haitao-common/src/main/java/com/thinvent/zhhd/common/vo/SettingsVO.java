package com.thinvent.zhhd.common.vo;
import com.thinvent.library.util.UUIDUtil;
//@Accessors(chain = true)
//@Setter
//@Getter
/*
 * 注释掉lomback，是因为这里的sKey和sValue不知为何大小写混乱，update时，数据绑定不了。因此这样。
 * /
 */
public class SettingsVO {
	public SettingsVO() {
		this.sId = UUIDUtil.getUuid();
	}
	private String sId;
	
	private String sKey;

    private String sValue;

    private String sType;

	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

	public String getsKey() {
		return sKey;
	}

	public void setsKey(String sKey) {
		this.sKey = sKey;
	}

	public String getsValue() {
		return sValue;
	}

	public void setsValue(String sValue) {
		this.sValue = sValue;
	}

	public String getsType() {
		return sType;
	}

	public void setsType(String sType) {
		this.sType = sType;
	}


}
