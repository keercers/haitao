package com.thinvent.basicpf.zhhd.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReportExportUtil {
	
	private ReportExportUtil() {
	}
	
	public static void util(Map<String, Object> params, Map<String, Object> map, HttpServletResponse response) throws IOException {
		String reportFlag = "content-Type";
		String reportType;
		if("Word".equals(params.get("exportFormat"))){
			reportType = ".doc";
			response.setHeader(reportFlag, "application/msword");
		}else if("Excel".equals(params.get("exportFormat"))) {
			reportType = ".xls";
			response.setHeader(reportFlag, "application/vnd.ms-excel");
		}else {
			reportType = ".pdf";
			response.setHeader(reportFlag, "application/pdf");
		}
		String filename = "";
		String cnName = getCnName(params);
		Object flag = params.get("flag");
		if ("monitoring".equals(flag)){
			filename = cnName + "(" + map.get("mpLocation") + "：" + params.get("startTime") + "-" 
					+ params.get("endTime") + ")" + reportType;
		} else if ("operation".equals(flag)){
			filename = cnName + "(" + params.get("startTime") + "-" 
					+ params.get("endTime") + ")" + reportType;
		}
		
		if("0".equals(params.get("preview"))){
	        response.setHeader( "Content-Disposition", "attachment;filename="  + new String( filename.getBytes("gb2312"), "ISO8859-1" ));
		}
        ServletOutputStream sos = response.getOutputStream();
        InputStream fis = null;
        byte[] buffer = new byte[map.get("exportData").toString().length()];
		try {
			fis = getInputStreamByString(map.get("exportData").toString());
			while((fis.read(buffer, 0, buffer.length)) != -1){
	            sos.write(buffer, 0, buffer.length);
	        }
		} catch (IOException e) {
			log.error("reportExportUtil utilSecond export error: ", e);
			throw e;
		} finally {
			sos.close();
        	if(fis != null) {
        		fis.close();
        	}
        }
	}
	
	public static InputStream getInputStreamByString(String str) throws IOException{
		ByteArrayInputStream stream = null;
		try {
			stream = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
		} catch (IOException e) {
			log.error("reportExportUtil stream conversion error: ", e);
			throw e;
		}
		return stream;
	}
	
	public static String getCnName(Map<String, Object> params) {
		String cnName = null;
		String reportName = params.get("reportName").toString();
		Map<String, String> map = new HashMap<>();
		map.put("TrafficFlowMaxAvgByMonRp", "船舶交通量统计分析");
		map.put("TrafficFlowStatByDayRp", "船舶交通量汇总按天统计");
		map.put("TrafficFlowStatByMonRp", "船舶交通量汇总按月统计");
		map.put("TrafficFlowEOOStatByDayRp", "船舶交通量空重载汇总按天统计");
		map.put("TrafficFlowSumAvgByMonRp", "船舶交通量观测统计月报表");
		map.put("TrafficFlowOverLenStatByDayRp", "船舶超长统计报表");
		map.put("OprInsTimes", "巡检记录统计");
		map.put("OprInsRecPlan", "巡检计划记录统计");
		map.put("OprInsEqu", "巡检情况统计");
		map.put("OprAssetsType", "资产类型统计");
		map.put("OprAssetsStatus", "资产状态统计");
		map.put("OprAssetsAddress", "资产地点统计");
		map.put("OprAssetsBc", "厂商品牌统计");
		map.put("OprMafType", "故障类型统计");
		map.put("OprMafStatus", "故障状态统计");
		map.put("OprMafResolve", "故障处理结果统计");
		map.put("OprMafAtr", "设备故障情况统计");
		map.put("AlarmStatisticsAlarm", "报警事件处置统计");
		map.put("AlarmStatisticsShip", "违章船舶统计");
		for (Entry<String, String> entry : map.entrySet()) {
			if (entry.getKey().equals(reportName)) {
				cnName = entry.getValue();
			}
		}
		return cnName;
	}
	
	public static boolean isWin() {
		String os = System.getProperty("os.name");  
		return os.toLowerCase().startsWith("win")?true:false;
	}
}
