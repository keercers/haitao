package com.thinvent.basicpf.zhhd.handler.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.thinvent.basicpf.zhhd.adapt.IReportAdapt;
import com.thinvent.basicpf.zhhd.handler.IReportHandler;
import com.thinvent.library.exception.ThinventBaseException;

@Service
public class ReportHandlerImpl implements IReportHandler {

	@Autowired
	private IReportAdapt reportAdapt;

	@Override
	public JSONObject getReportData(Map<String, Object> params) throws ThinventBaseException {
		return this.reportAdapt.getReportData(params);
	}
	
	@Override
	public Map<String, Object> exportReportData(Map<String, Object> params) throws ThinventBaseException {
		return this.reportAdapt.exportReportData(params);
	}
}
