package com.thinvent.basicpf.sys;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.webresources.war.Handler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.thinvent.basicpf.sys.adapt.IDepartmentAdapt;
import com.thinvent.basicpf.sys.adapt.ILogAdapt;
import com.thinvent.basicpf.sys.adapt.IMoudleAdapt;
import com.thinvent.basicpf.sys.adapt.IUserAdapt;
import com.thinvent.basicpf.sys.handler.impl.LogHandlerImpl;
import com.thinvent.library.vo.DepartmentVO;
import com.thinvent.library.vo.LogVO;
import com.thinvent.library.vo.MoudleVO;
import com.thinvent.library.vo.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class LogHandlerTest {
	
	@Mock
	private ILogAdapt logAdapt;
	
	@InjectMocks
	private LogHandlerImpl logHandlerImpl;
	
	@Mock 
	private IUserAdapt userAdapt;
	
	@Mock 
	private IDepartmentAdapt departmentAdapt;
	
	@Mock
	private IMoudleAdapt moudleAdapt;
	
	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void saveLog() throws Exception {
		LogVO logVO = new LogVO();
		
		logVO.setId(1);
		logVO.setDeptName("上海监所");
		logVO.setMoudleName("4");
		logVO.setSystem("1");
		
		// 捕获调用函数的参数
		ArgumentCaptor<LogVO> argument = ArgumentCaptor.forClass(LogVO.class);
		logHandlerImpl.saveLog(logVO);
								
		verify(logAdapt, times(1)).saveLog(argument.capture());
		Assert.assertEquals("上海监所", argument.getValue().getDeptName());
		Assert.assertEquals("4", argument.getValue().getMoudleName());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void listLogByParams() throws Exception {
		List<Map<String, Object>> logVOs = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> mapReturn = new HashMap<>();

		logVOs.add(map);
		mapReturn.put("count", 1);
		mapReturn.put("logList", logVOs);
		
		when(logAdapt.listLogByParams("", "", "", "", "", 10, 1)).thenReturn(mapReturn);
		
		Map<String, Object> mapResult = logHandlerImpl.listLogByParams("", "", "", "", "", 10, 1);
		int count = (Integer)mapResult.get("count");
		Assert.assertEquals(1, count);
	}
	
	@Test
	public void logSaveHandle() throws Exception {
		HttpServletRequest request = new MockHttpServletRequest("/getDeptTreeByUserId", "/getDeptTreeByUserId");
		HttpServletResponse response = new MockHttpServletResponse();
		DepartmentVO departmentVO = new DepartmentVO();
		List<MoudleVO> list = new ArrayList<>();
		MoudleVO moudleVO = new MoudleVO();
		Object handler = new Handler();
		UserVO userVO = new UserVO();
		
		when(moudleAdapt.getAll()).thenReturn(list);
		logHandlerImpl.logSaveHandle(request, response, handler, "1");
		Assert.assertTrue("moudleList为空", list.isEmpty());
		
		userVO.setUserId("1");
		userVO.setDepId("2");
		departmentVO.setDepId("2");
		departmentVO.setDepName("测试");
		moudleVO.setMoudleUrl("/saveCompany");
		moudleVO.setMoudleName("保存单位");
		moudleVO.setMoudleSign("0-1-6-9-");
		list.add(moudleVO);
		
		when(moudleAdapt.getAll()).thenReturn(list);
		when(userAdapt.findUserByUserId("1")).thenReturn(userVO);
		when(departmentAdapt.getDepartmentById("2")).thenReturn(departmentVO);
		
		//分支
		request = new MockHttpServletRequest("/login", "/login");
		logHandlerImpl.logSaveHandle(request, response, handler, "1");
		Assert.assertEquals("/login", request.getRequestURI());
		//分支
		request = new MockHttpServletRequest("/saveCompany", "/saveCompany");
		logHandlerImpl.logSaveHandle(request, response, handler, "1");
		Assert.assertEquals("/saveCompany", request.getRequestURI());
	}
}
