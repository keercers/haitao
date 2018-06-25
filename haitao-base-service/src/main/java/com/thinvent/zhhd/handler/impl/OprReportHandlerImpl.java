package com.thinvent.zhhd.handler.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.EngineConstants;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.HTMLRenderContext;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.PDFRenderOption;
import org.eclipse.birt.report.engine.api.ReportEngine;
import org.springframework.stereotype.Service;

import com.thinvent.library.Constants;
import com.thinvent.library.config.ServiceConfig;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.zhhd.handler.IOprReportHandler;
import com.thinvent.zhhd.util.FileReadAndExportUtil;

@Service
public class OprReportHandlerImpl implements IOprReportHandler {
	
	String rpUrl = ServiceConfig.getServiceConfig(Constants.zhhdBaseService, "zhhd-config.reportUrl.url");
	
	@SuppressWarnings({"rawtypes", "deprecation" })
	public Map<String, String> getReportData(Map<String, String> params) throws ThinventBaseException, EngineException{
		String reportName = params.get("reportName");
		//引擎配置——设置和获取临时目录，BIRT，Servlet的背景
		EngineConfig config = new EngineConfig();
		String tmpUrl = System.getenv("TMP");
		
		//创建报告引擎
		ReportEngine engine = new ReportEngine(config);
		
		//打开报告设计-使用设计来修改设计，检索嵌入式图像等。
		String reportUrl = null;
		try {
			reportUrl = rpUrl + reportName + ".rptdesign";
		} catch (Exception e) {
			Map<String, String> returnMap = new HashMap<>();
			return returnMap;
		}
		IReportRunnable design = engine.openReportDesign(reportUrl);
		
		//创建任务来运行报告—使用任务执行和运行报告
		IRunAndRenderTask task = engine.createRunAndRenderTask(design);
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("startTime", params.get("startTime"));
		paramMap.put("endTime", params.get("endTime"));
		task.setParameterValues(paramMap);
		  
		//设置渲染上下文来处理url和图像locataions
		HTMLRenderContext renderContext = new HTMLRenderContext();
		renderContext.setImageDirectory("image");
		Map<String, Object> contextMap = new HashMap<>();
		contextMap.put(EngineConstants.APPCONTEXT_HTML_RENDER_CONTEXT, renderContext);
		task.setAppContext(contextMap);
		
		//设置呈现选项-如文件或流输出， 输出格式，是否可嵌入，等等
		HTMLRenderOption options = new HTMLRenderOption();
		options.setOutputFileName(tmpUrl + "/view/" + reportName + ".html");
		options.setOutputFormat("html");
		task.setRenderOption(options);

		//运行报告并销毁引擎
		task.run();

		task.close();
		  
		engine.destroy();

		String chartUrl = null;
		if (!reportName.substring(reportName.length()-4, reportName.length()).equals("List")) {
			String reportUrlHost = ServiceConfig.getServiceConfig(Constants.zhhdBaseService, "zhhd-config.web.fileUrlHost");
			String host = ServiceConfig.getServiceConfig(Constants.zhhdBaseService, "zhhd-config.FTP.Host");
	        int port = Integer.parseInt(ServiceConfig.getServiceConfig(Constants.zhhdBaseService, "zhhd-config.FTP.Port"));
	        String userName = ServiceConfig.getServiceConfig(Constants.zhhdBaseService, "zhhd-config.FTP.UserName");
	        String password = ServiceConfig.getServiceConfig(Constants.zhhdBaseService, "zhhd-config.FTP.Password");
			String localUrl = tmpUrl + "/view/" + reportName + ".html";
			String localImageUrl = tmpUrl + "/view/image";
			InputStream htmlInput = null;
			InputStream svgInput = null;
			try {
				htmlInput = new FileInputStream(new File(localUrl));
				FileReadAndExportUtil.fileUpload(host, port, userName, password, "report", reportName + ".html", htmlInput);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			List resultList = new ArrayList();
			try {
				FileReadAndExportUtil.findFiles(localImageUrl, "*.svg", resultList);
				for (int i = 0; i < resultList.size(); i++) {
					String img = resultList.get(i).toString();
					svgInput = new FileInputStream(new File(img));
					int index = img.indexOf("image");
					FileReadAndExportUtil.fileUpload(host, port, userName, password, "report/image", img.substring(index + 6, img.length()), svgInput);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			chartUrl = reportUrlHost + "/report/" + reportName + ".html";
		}
		
		Map<String, String> returnMap = new HashMap<>();
		String reportData = null;
		reportData = FileReadAndExportUtil.fileReadByType(params.get("reportName"), ".html");
		if (reportName.substring(reportName.length()-4, reportName.length()).equals("List")) {
			returnMap.put(reportName, reportData);
		} else {
			returnMap.put(reportName, chartUrl);
		}
		return returnMap;
	}
	
	@SuppressWarnings("deprecation")
	public Map<String, String> exportReportData(Map<String, String> params) throws ThinventBaseException, EngineException{
		String reportName = params.get("reportName");
		//引擎配置——设置和获取临时目录，BIRT，Servlet的背景
		EngineConfig config = new EngineConfig();
		String tmpUrl = System.getenv("TMP");
		
		//创建报告引擎
		ReportEngine engine = new ReportEngine(config);
		
		//打开报告设计-使用设计来修改设计，检索嵌入式图像等。
		String reportUrl = rpUrl + reportName + ".rptdesign";
		IReportRunnable design = engine.openReportDesign(reportUrl);
		
		//创建任务来运行报告—使用任务执行和运行报告
		IRunAndRenderTask task = engine.createRunAndRenderTask(design);
		Map<String, String> paramMap = new HashMap<>();
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
			htmlUrl = tmpUrl + "\\view\\" + reportName;
		}else {
			htmlUrl = tmpUrl + "/view/" + reportName;
		}
		PDFRenderOption options = new PDFRenderOption();
		options.setOutputFileName(htmlUrl + ".pdf");
		options.setSupportedImageFormats("PNG;GIF;JPG;BMP;SWF;SVG");
		options.setOutputFormat("pdf");
		task.setRenderOption(options);
		
		//运行报告并销毁引擎
		task.run();
		task.close();
		  
		engine.destroy();
		
		try {
			params.put("exportFormat", "PDF");
			String output = FileReadAndExportUtil.fileExport(params);
			returnMap.put(reportName, output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnMap;
	}
}
