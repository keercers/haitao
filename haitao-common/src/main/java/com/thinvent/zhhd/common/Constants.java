package com.thinvent.zhhd.common;

import java.util.Arrays;
import java.util.List;

/**
 * @author tanshumei
 */
public class Constants {
	private Constants() {
	}
	
	// 成功代码
	public static final String RES_SUCCESS = "200";

	public static final List<String> rfidHeader = Arrays.asList("编号", "RFID名称", "位置");

	public static final String[] rfidHeaderEn = { "rfidNum", "rfidName", "rfidLocation" };

	public static final List<String> waterWayHeader = Arrays.asList("编号", "航道名称", "管辖单位", "航道等级", "航道位置", "航道宽度", "图形类型",  "航道颜色");

	public static final String[] waterWayHeaderEn = { "waterwayNumber", "waterwayName", "orgName", "waterwayLevelName", "waterwayLocation", "width",
			"geometry", "waterwayColor" };
	
	public static final String[] emergencyHeader = {"应急事件名称","事件类型","执行人","巡逻艇","时间","事件描述","处置结果"};
	
	public static final String[] emergencyHeaderEn = {"eventName","typeValue","userName","boatName","executeTimeString","eventDescribe","disposalResult"};
	
	
	public static final List<String> patrolBoatHeader =  Arrays.asList("巡逻艇名称", "MMSI", "所属海事站所", "联系电话");

	public static final String[] patrolBoatHeaderEn = {"name", "mmsi", "msName", "linkway"};
	
	public static final List<String> shipHeader = Arrays.asList("船舶名称", "船舶登记号", "船舶类型", "MMSI", "总载重吨", "船舶所有人", "联系电话");

	public static final String[] shipHeaderEn = { "cnName", "registerNo", "shipCategoryValue", "mmsi", "dwt", "owner", "phoneNum" };
	
	public static final List<String> assetsHeader = Arrays.asList("资产编号", "资产名称", "资产类型", "地点", "负责人", "状态");
	
	public static final String[] assetsEn = {"assetsNumber", "assetsName", "assetsTypeValue", "assetsAddress", "principal", "statusValue"};

	public static final List<String> lawEnforcementHeader = Arrays.asList("船名船号", "事件类型", "执法原因", "执法结果","执法巡逻艇", "时间");
	
	public static final String[] lawEnforcementHeaderEn = {"shipName", "leTypeValue", "leCause", "leResult", "pbName", "leTimeString"};
	
	public static final String[] lawEnforceProcessHeaderEn = {"pbName", "lepStartTimeString", "lepEndTimeString", "lepMiles","lepTimes"};
	
	public static final List<String> lawEnforceProcessHeader = Arrays.asList("巡逻艇", "开始时间", "结束时间", "公里数","执法次数");

	public static final List<String> planHeader = Arrays.asList("巡检计划名称", "巡检内容编号", "巡检人员","计划开始时间","计划结束时间");
	
	public static final String[] planHeaderEn = {"planName", "assetsNumber", "people","startTimeString","endTimeString"};

	public static final List<String> recordHeader = Arrays.asList("巡检记录名称", "所属计划","巡检人员","巡检内容编号","计划开始时间","计划结束时间");
	
	public static final String[] recordHeaderEn = {"recordName", "planName","people", "assetsNumber", "startTimeString","endTimeString"};

	public static final List<String> OPSHeader = Arrays.asList("运维人员名称", "电话", "所属机构");

	public static final String[] OPSHeaderEn = { "opsName", "opsTel", "orgName"};
	
	public static final String[] alarmEventHeaderEn = {"alarmName", "alarmTypeValue", "alarmTimeString"};
	
	public static final List<String> alarmEventHeader = Arrays.asList("事件名称", "事件类型", "报警时间");
	
	public static final List<String> malfunctionHeader = Arrays.asList("故障编号", "设备编号", "故障类型", "报障时间","故障状态");
		
    public static final String[] malfunctionHeaderEn = {"mafNumber", "assetsNumber", "mafTypeValue","repDateString", "mafStatus"};

}
