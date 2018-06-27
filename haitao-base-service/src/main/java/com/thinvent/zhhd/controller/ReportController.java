package com.thinvent.zhhd.controller;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.birt.report.engine.api.EngineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.zhhd.handler.IReportHandler;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "reportView")
public class ReportController {

	@Autowired
	private IReportHandler reportService;
	
	@GetMapping(value="/get")	
	@ApiOperation(value="报表管理", notes="报表管理")
	@ApiImplicitParams({
		@ApiImplicitParam(name="reportName",required=true, value = "reportName", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name="startTime",required=true, value = "startTime", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name="endTime",required=true, value = "endTime", dataType = "string", paramType = "query"),
	})
	@ResponseBody
	public Map<String, String> getReportData(
			@RequestParam(value = "reportName", required = true) String reportName,
			@RequestParam(value = "startTime", required = true) String startTime, 
			@RequestParam(value = "endTime", required = true) String endTime) throws ThinventBaseException, EngineException {
		Map<String, String> params = new HashMap<>();
		params.put("reportName", reportName);
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		return this.reportService.getReportData(params);
	}
	
	@GetMapping(value="/export")	
	@ApiOperation(value="报表导出", notes="报表导出")
	@ApiImplicitParams({
		@ApiImplicitParam(name="exportFormat",required=true, value = "exportFormat", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name="reportName",required=true, value = "reportName", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name="startTime",required=true, value = "startTime", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name="endTime",required=true, value = "endTime", dataType = "string", paramType = "query"),
	})
	@ResponseBody
	public Map<String, String> exportReportData(
			@RequestParam(value = "exportFormat", required = true) String exportFormat,
			@RequestParam(value = "reportName", required = true) String reportName,
			@RequestParam(value = "startTime", required = true) String startTime, 
			@RequestParam(value = "endTime", required = true) String endTime) throws ThinventBaseException, EngineException {
		Map<String, String> params = new HashMap<>();
		params.put("exportFormat", exportFormat);
		params.put("reportName", reportName);
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		return this.reportService.exportReportData(params);
	}
}
