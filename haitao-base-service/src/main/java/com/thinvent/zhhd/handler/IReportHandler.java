package com.thinvent.zhhd.handler;

import java.util.Map;

import org.eclipse.birt.report.engine.api.EngineException;

import com.thinvent.library.exception.ThinventBaseException;

public interface IReportHandler {
	public Map<String, String> getReportData(Map<String, String> params) throws ThinventBaseException, EngineException;

	public Map<String, String> exportReportData(Map<String, String> params) throws ThinventBaseException, EngineException;
}
