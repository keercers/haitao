package com.thinvent.zhhd.common.vo;
import java.util.Date;
import java.util.List;
import com.thinvent.library.util.UUIDUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
/**
 * 故障管理表
 * By hax
 * **/
@Accessors(chain = true)
@Setter
@Getter
public class MalfunctionVO {
	
	public MalfunctionVO() {
		this.mafId = UUIDUtil.getUuid();
//		this.repDate = new Date();
	}
	private String mafId;
	private String mafNumber; //故障编号
    private String mafPhe;
    private String mafType;
    private String mafTypeValue;
    private String repDateString; //存报障string类型时间
    private Date repDate; 
    private String oprPerson;
    private String contact;
    private String message; 
    private String remark;
    private String mafStatus;
    private String repPeople;
    private String resolveEnd;   //处理结果
    private String resolveEndValue;
    private String resolvePerson;   //处理人员
    private Date resolveDate;        //处理时间
    private String mafReason;
    private String assetsNumber;
    private String malfunctionImage;
    private List mafAssetsList;   //资产故障中间表list 存储对应的多条数据

}
