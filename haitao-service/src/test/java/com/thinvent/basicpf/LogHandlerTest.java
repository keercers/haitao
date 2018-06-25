package com.thinvent.basicpf;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.thinvent.basicpf.dao.ILogDao;
import com.thinvent.basicpf.dao.jpa.LogDaoRepositoryImpl;
import com.thinvent.basicpf.handler.impl.LogHandlerImpl;
import com.thinvent.basicpf.model.Log;
import com.thinvent.library.vo.LogVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class LogHandlerTest {

	@Mock
	private ILogDao logDao;
	
	@InjectMocks
	private LogDaoRepositoryImpl logDaoRepositoryImpl;
	
	@InjectMocks
	private LogHandlerImpl logHandlerImpl;
	
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
		
		ArgumentCaptor<Log> argument = ArgumentCaptor.forClass(Log.class);
		logHandlerImpl.saveLog(logVO);
				
		verify(logDao, times(1)).save(argument.capture());
		Assert.assertEquals("上海监所", argument.getValue().getDeptName());
		Assert.assertEquals("4", argument.getValue().getMoudleName());
	}
}
