package com.thinvent.zhhd.controller;

import java.io.File;
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
import com.thinvent.zhhd.handler.IOprReportHandler;
import com.thinvent.zhhd.util.FileReadAndExportUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "reportView")
@Slf4j
public class OprReportController {

	@Autowired
	private IOprReportHandler oprReportService;
	
	@GetMapping(value="/getOpr")	
	@ApiOperation(value="报表管理", notes="报表管理")
	@ApiImplicitParams({
		@ApiImplicitParam(name="fileName",required=true, value = "fileName", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name="reportName",required=true, value = "reportName", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name="startTime",required=true, value = "startTime", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name="endTime",required=true, value = "endTime", dataType = "string", paramType = "query"),
	})
	@ResponseBody
	public Map<String, Object> getReportData(
			@RequestParam(value = "fileName", required = true) String fileName,
			@RequestParam(value = "reportName", required = true) String reportName,
			@RequestParam(value = "startTime", required = true) String startTime, 
			@RequestParam(value = "endTime", required = true) String endTime) throws ThinventBaseException, EngineException {
		String tmpUrl = System.getenv("TMP");
		//清除资源文件夹原有图片资源
		FileReadAndExportUtil.deleteDir(new File(tmpUrl + "/view/image/"));
		Map<String, Object> map = new HashMap<>();
		Map<String, String> params = new HashMap<>();
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		Map<String, String> repMap = new HashMap<>();
		repMap.put("list","");
		repMap.put("chart","");
		try {
			params.put("reportName", fileName + reportName + "List");
			repMap.put("list", this.oprReportService.getReportData(params).get(params.get("reportName")));
		} catch (Exception e) {
			e.printStackTrace();
			log.info("报表列表加载失败！ ");
		}
		try {
			params.put("reportName", fileName + reportName + "Chart");
			repMap.put("chart", this.oprReportService.getReportData(params).get(params.get("reportName")));
		} catch (Exception e) {
			e.printStackTrace();
			log.info("报表图表加载失败！ ");
		}
		map.put("reportData", repMap);
		return map;
	}
	
	@GetMapping(value="/exportOpr")	
	@ApiOperation(value="报表导出", notes="报表导出")
	@ApiImplicitParams({
		@ApiImplicitParam(name="reportName",required=true, value = "reportName", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name="startTime",required=true, value = "startTime", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name="endTime",required=true, value = "endTime", dataType = "string", paramType = "query"),
	})
	@ResponseBody
	public Map<String, String> exportReportData(
			@RequestParam(value = "reportName", required = true) String reportName,
			@RequestParam(value = "startTime", required = true) String startTime, 
			@RequestParam(value = "endTime", required = true) String endTime) throws ThinventBaseException, EngineException {
		Map<String, String> map = new HashMap<>();
		Map<String, String> params = new HashMap<>();
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		map.put("exportData","");
		try {
			params.put("reportName", reportName+"Export");
			map.put("exportData", this.oprReportService.exportReportData(params).get(params.get("reportName")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
