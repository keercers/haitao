package com.thinvent.basicpf.zhhd.adapt.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thinvent.basicpf.zhhd.adapt.IReportAdapt;
import com.thinvent.library.Constants;
import com.thinvent.library.config.ServiceConfig;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.GetPostUtil;

@Service
public class ReportAdaptImpl implements IReportAdapt {

	private String basicUrl = ServiceConfig.getServiceConfig(Constants.zhhdBasicService, Constants.zhhdBaseServiceUrlKey);
	
	@Override
	public JSONObject getReportData(Map<String, Object> params) throws ThinventBaseException {
		Object reportName=params.get("reportName");
		Object startTime=params.get("startTime");
		Object endTime=params.get("endTime");
		StringBuilder sbf=new StringBuilder();
		sbf.append("&reportName=" + (reportName == null ? "" : reportName));
		sbf.append("&startTime=" + (startTime == null ? "" : startTime));
		sbf.append("&endTime=" + (endTime == null ? "" : endTime));
		String url = basicUrl+"reportView/get?" + sbf;
		url = url.replaceAll(" ", "%20");
		return JSON.parseObject(GetPostUtil.sendGet(url));  
	}
	
	@Override
	public Map<String, Object> exportReportData(Map<String, Object> params) throws ThinventBaseException {
		Object exportFormat=params.get("exportFormat");
		Object reportName=params.get("reportName");
		Object startTime=params.get("startTime");
		Object endTime=params.get("endTime");
		StringBuilder sbf=new StringBuilder();
		sbf.append("&exportFormat=" + (exportFormat == null ? "" : exportFormat));
		sbf.append("&reportName=" + (reportName == null ? "" : reportName));
		sbf.append("&startTime=" + (startTime == null ? "" : startTime));
		sbf.append("&endTime=" + (endTime == null ? "" : endTime));
		String url = basicUrl+"reportView/export?" + sbf;
		url = url.replaceAll(" ", "%20");
		return JSON.parseObject(GetPostUtil.sendGet(url));
	}
}
