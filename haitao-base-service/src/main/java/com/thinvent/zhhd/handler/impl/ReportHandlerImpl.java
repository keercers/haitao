package com.thinvent.zhhd.handler.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.birt.report.engine.api.DocxRenderOption;
import org.eclipse.birt.report.engine.api.EXCELRenderOption;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.EngineConstants;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.HTMLRenderContext;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.PDFRenderOption;
import org.eclipse.birt.report.engine.api.ReportEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinvent.library.Constants;
import com.thinvent.library.config.ServiceConfig;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.zhhd.common.vo.MonitorPointVO;
import com.thinvent.zhhd.dao.jpa.ReportDaoRepositoryImpl;
import com.thinvent.zhhd.handler.IReportHandler;
import com.thinvent.zhhd.util.FileReadAndExportUtil;

@Service
public class ReportHandlerImpl implements IReportHandler {
	
	@Autowired
	ReportDaoRepositoryImpl reportDao;

	String rpUrl = ServiceConfig.getServiceConfig(Constants.zhhdBaseService, "zhhd-config.reportUrl.url");
	
	@SuppressWarnings("deprecation")
	public Map<String, String> getReportData(Map<String, String> params) throws ThinventBaseException, EngineException{
		//引擎配置——设置和获取临时目录，BIRT，Servlet的背景
		EngineConfig config = new EngineConfig();
		String tmpUrl = System.getenv("TMP");
		
		//创建报告引擎
		ReportEngine engine = new ReportEngine(config);
		
		//打开报告设计-使用设计来修改设计，检索嵌入式图像等。
		String reportUrl = rpUrl + params.get("reportName") + ".rptdesign";
		IReportRunnable design = engine.openReportDesign(reportUrl);
		
		//创建任务来运行报告—使用任务执行和运行报告
		IRunAndRenderTask task = engine.createRunAndRenderTask(design);
		MonitorPointVO vo = new MonitorPointVO();
		vo = reportDao.getMpLocationByMpId(params.get("mpId"));
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("mpId", params.get("mpId"));
		paramMap.put("mpLocation", vo.getMpLocation());
		paramMap.put("startTime", params.get("startTime"));
		paramMap.put("endTime", params.get("endTime"));
		task.setParameterValues(paramMap);
		  
		//设置渲染上下文来处理url和图像locataions
		HTMLRenderContext renderContext = new HTMLRenderContext();
		renderContext.setImageDirectory("image");
		Map<String, Object> contextMap = new HashMap<>();
		contextMap.put(EngineConstants.APPCONTEXT_HTML_RENDER_CONTEXT, renderContext);
		task.setAppContext(contextMap);
		  
		//设置呈现选项-如文件或流输出， 
		//输出格式，是否可嵌入，等等
		HTMLRenderOption options = new HTMLRenderOption();
		options.setOutputFileName(tmpUrl + "/view/" + params.get("reportName") + ".html");
		options.setOutputFormat("html");
		task.setRenderOption(options);
		  
		//运行报告并销毁引擎
		task.run();
		task.close();
		  
		engine.destroy();
		
		String reportData = null;
		reportData = FileReadAndExportUtil.fileReadByType(params.get("reportName"), ".html");
		reportData = reportData.replace("width: 8in;", "width: 13.6in;");
		Map<String, String> returnMap = new HashMap<>();
		returnMap.put("reportData", reportData);
		return returnMap;
	}
	
	@SuppressWarnings("deprecation")
	public Map<String, String> exportReportData(Map<String, String> params) throws ThinventBaseException, EngineException{
		//引擎配置——设置和获取临时目录，BIRT，Servlet的背景
		EngineConfig config = new EngineConfig();
		String tmpUrl = System.getenv("TMP");
		
		//创建报告引擎
		ReportEngine engine = new ReportEngine(config);
		
		//打开报告设计-使用设计来修改设计，检索嵌入式图像等。
		String reportUrl = rpUrl + params.get("reportName") + ".rptdesign";
		IReportRunnable design = engine.openReportDesign(reportUrl);
		
		//创建任务来运行报告—使用任务执行和运行报告
		IRunAndRenderTask task = engine.createRunAndRenderTask(design);
		MonitorPointVO vo = new MonitorPointVO();
		vo = reportDao.getMpLocationByMpId(params.get("mpId"));
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("mpId", params.get("mpId"));
		paramMap.put("mpLocation", vo.getMpLocation());
		paramMap.put("startTime", params.get("startTime"));
		paramMap.put("endTime", params.get("endTime"));
		task.setParameterValues(paramMap);
		  
		//设置渲染上下文来处理url和图像locataions
		HTMLRenderContext renderContext = new HTMLRenderContext();
		renderContext.setImageDirectory("image");
		Map<String, Object> contextMap = new HashMap<>();
		contextMap.put(EngineConstants.APPCONTEXT_HTML_RENDER_CONTEXT, renderContext);
		task.setAppContext(contextMap);
		  
		//设置呈现选项-如文件或流输出， 
		//输出格式，是否可嵌入，等等
		Map<String, String> returnMap = new HashMap<>();
		String htmlUrl;
		if(FileReadAndExportUtil.isWin()){
			htmlUrl = tmpUrl + "\\view\\" + params.get("reportName");
		}else {
			htmlUrl = tmpUrl + "/view/" + params.get("reportName");
		}
		if(params.get("exportFormat").equals("Word")){
			DocxRenderOption options = new DocxRenderOption();
			options.setOutputFileName(htmlUrl + ".doc");
			options.setOutputFormat("doc");
			task.setRenderOption(options);
		}else if(params.get("exportFormat").equals("Excel")){
			EXCELRenderOption options = new EXCELRenderOption();
			options.setOutputFileName(htmlUrl + ".xls");
			options.setOutputFormat("xls");
			task.setRenderOption(options);
		}else{
			PDFRenderOption options = new PDFRenderOption();
			options.setOutputFileName(htmlUrl + ".pdf");
			options.setSupportedImageFormats("PNG;GIF;JPG;BMP;SWF;SVG");
			options.setOutputFormat("pdf");
			task.setRenderOption(options);
		}
		  
		//运行报告并销毁引擎
		task.run();
		task.close();
		  
		engine.destroy();
		
		try {
			String output = FileReadAndExportUtil.fileExport(params);
			returnMap.put("exportData", output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		returnMap.put("mpLocation", vo.getMpLocation());
		return returnMap;
	}
}
