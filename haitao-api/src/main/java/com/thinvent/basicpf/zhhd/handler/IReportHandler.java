package com.thinvent.basicpf.zhhd.handler;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.thinvent.library.exception.ThinventBaseException;

public interface IReportHandler {
	public JSONObject getReportData(Map<String, Object> params) throws ThinventBaseException;
	
	public Map<String, Object> exportReportData(Map<String, Object> params) throws ThinventBaseException;
}
