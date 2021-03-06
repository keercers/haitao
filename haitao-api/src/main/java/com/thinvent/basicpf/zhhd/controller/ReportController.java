package com.thinvent.basicpf.zhhd.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.thinvent.basicpf.zhhd.handler.IReportHandler;
import com.thinvent.basicpf.zhhd.util.ReportExportUtil;
import com.thinvent.library.exception.ThinventBaseException;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "monitoring/reportView")
public class ReportController {

	@Autowired
	private IReportHandler reportService;
	
	@RequestMapping(value="/get", method = RequestMethod.GET)
	@ApiOperation(value="报表管理", notes="报表管理")
	@ApiImplicitParams({
		@ApiImplicitParam(name="reportName",required=true, value = "reportName", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name="startTime",required=true, value = "startTime", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name="endTime",required=true, value = "endTime", dataType = "string", paramType = "query"),
	})
	@ResponseBody
	public Map getReportData(
			@RequestParam(value = "reportName", required = true) String reportName,
			@RequestParam(value = "startTime", required = true) String startTime, 
			@RequestParam(value = "endTime", required = true) String endTime) throws ThinventBaseException {
		Map<String, Object> params = new HashMap();
		params.put("reportName", reportName);
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		return this.reportService.getReportData(params);
	}
	
	@RequestMapping(value="/export", method = RequestMethod.GET)
	@ApiOperation(value="报表导出", notes="报表导出")
	@ApiImplicitParams({
		@ApiImplicitParam(name="exportFormat",required=true, value = "exportFormat", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name="preview",required=true, value = "preview", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name="reportName",required=true, value = "reportName", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name="startTime",required=true, value = "startTime", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name="endTime",required=true, value = "endTime", dataType = "string", paramType = "query"),
	})
	@ResponseBody
	public void exportReportData(
			@RequestParam(value = "exportFormat", required = true) String exportFormat,
			@RequestParam(value = "preview", required = true) String preview,
			@RequestParam(value = "reportName", required = true) String reportName,
			@RequestParam(value = "startTime", required = true) String startTime, 
			@RequestParam(value = "endTime", required = true) String endTime,
			HttpServletResponse response) throws ThinventBaseException, IOException {
		Map<String, Object> params = new HashMap();
		params.put("exportFormat", exportFormat);
		params.put("preview", preview);
		params.put("reportName", reportName);
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		params.put("flag", "monitoring");
		Map<String, Object> map = this.reportService.exportReportData(params);
		ReportExportUtil.util(params, map, response);
	}
}
