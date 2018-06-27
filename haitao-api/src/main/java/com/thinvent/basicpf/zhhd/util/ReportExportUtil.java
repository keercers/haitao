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
			filename = cnName + "(" + params.get("startTime") + "-" 
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
		map.put("TrafficFlowMaxAvgByMonRp", "单位统计分析");
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
