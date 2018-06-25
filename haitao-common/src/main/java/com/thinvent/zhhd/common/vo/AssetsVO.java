package com.thinvent.zhhd.common.vo;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinvent.library.util.UUIDUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class AssetsVO {
	
	public AssetsVO() {
		this.assetsId = UUIDUtil.getUuid();
	}
	
	private String assetsId;
    private String assetsNumber;
    private String assetsName;
    private String assetsType;
    private String assetsImage;
    private String assetsAddress;
    private String ip;
    private String principal;
    
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date assetsDate;
    private String assetsFinalDate;
    private String useDate;
    private String brand;
    private String company;
    private String phone;
    private String status;
    private String remark;
    
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date checkAndScrapDate;	
    
	private String checkAndScrapReason;	
	private String checkAndScrapPerson;  
	private String contact;
	private String assetsFile;
	private String userId;
    private String paraInfo; 
    private String assetsTypeValue;
    private String statusValue;
    private String resolveEnd;
    private String resolveEndValue;
    private String scrapPeople;
    private String scrapRamark;
    private String editRamark;  //编辑备注
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date scrapDate;
    
    private String AssetsAdmin;
    private String cameraType;
    private String cameraTypeValue;
    private String assetsAddressType;
    
	private String userName;  //用户名
	private String password;  //密码
	private String port;  //端口
    private String vcnType; //VCN版本类型
	private String vcnTypeValue;  //VCN版本类型值
}
