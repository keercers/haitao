
package com.thinvent.zhhd.common.vo;

import java.util.Date;
import com.thinvent.library.util.UUIDUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class AssetsArchivesVO {
	
	public AssetsArchivesVO() {
		this.Id = UUIDUtil.getUuid();
	}
	
	private String Id;
	private String assetsId;   
	private String assetsAdmin; 
	private String adminOperation;
	private Date adminDate;
	private Date oprDate; 
	private String oprReason;
	private String oprResult;
	private String oprPerson;
	private String contact;
	private String remark;
}