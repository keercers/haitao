package com.thinvent.basicpf.zhhd;

import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSONObject;
import com.thinvent.basicpf.zhhd.adapt.IReportAdapt;
import com.thinvent.basicpf.zhhd.handler.impl.ReportHandlerImpl;
import com.thinvent.library.exception.ThinventBaseException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ReportHandlerImplTest {
	
	@Mock
	private IReportAdapt reportAdapt;
	
	@InjectMocks
	ReportHandlerImpl reportHandlerImpl;
	
	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getReportData() throws ThinventBaseException{
		Map<String, Object> params = new HashMap();
		JSONObject vo = null;
		when(reportAdapt.getReportData(params)).thenReturn(vo);
		params.put("reportName", "TrafficFlowMaxAvgByMonRp");
		params.put("mpId", "2d1b5ad7-1a60-4953-a7b1-ef1884690ae9");
		params.put("startTime", "2017-01");
		params.put("endTime", "2017-10");
		Map<String, Object> returnMap = reportHandlerImpl.getReportData(params);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void exportReportData() throws ThinventBaseException{
		Map<String, Object> params = new HashMap();
		Map<String, Object> map = null;
		when(reportAdapt.exportReportData(params)).thenReturn(map);
		params.put("exportFormat", "Word");
		params.put("reportName", "TrafficFlowMaxAvgByMonRp");
		params.put("mpId", "2d1b5ad7-1a60-4953-a7b1-ef1884690ae9");
		params.put("startTime", "2017-01");
		params.put("endTime", "2017-10");
		Map<String, Object> returnMap = reportHandlerImpl.exportReportData(params);
	}
}
