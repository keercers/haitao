package com.thinvent.basicpf.zhhd.handler.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.thinvent.basicpf.zhhd.adapt.IOprReportAdapt;
import com.thinvent.basicpf.zhhd.handler.IOprReportHandler;
import com.thinvent.library.exception.ThinventBaseException;

@Service
public class OprReportHandlerImpl implements IOprReportHandler {

	@Autowired
	private IOprReportAdapt oprReportAdapt;

	@Override
	public JSONObject getReportData(Map<String, Object> params) throws ThinventBaseException {
		return this.oprReportAdapt.getReportData(params);
	}
	
	@Override
	public Map<String, Object> exportReportData(Map<String, Object> params) throws ThinventBaseException {
		return this.oprReportAdapt.exportReportData(params);
	}
}
