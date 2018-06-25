package com.thinvent.basicpf.sys;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thinvent.basicpf.sys.adapt.ISysConfAdapt;
import com.thinvent.basicpf.sys.handler.IUserHandler;
import com.thinvent.basicpf.sys.handler.impl.SysConfHandlerImpl;
import com.thinvent.library.exception.DataVerException;
import com.thinvent.library.vo.ConfigVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class SysConfHandlerTest {
	
	@Mock
	private ISysConfAdapt sysConfAdapt;
	
	@InjectMocks
	private SysConfHandlerImpl sysConfHandler;
	
	@Mock
	private IUserHandler userHandler;
	
	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void getConfList() throws Exception {
		JSONObject demoJson = new JSONObject();
		ConfigVO configVO = new ConfigVO();
		
		configVO.setConfType(4);
		configVO.setEnable(1);
		configVO.setConfId("123");
		demoJson.put("configVO", configVO);
		
		when(sysConfAdapt.getByConfType(configVO.getConfType(), 1, 10)).thenReturn(demoJson);
		
		JSONObject jsonResult = sysConfHandler.getConfList(configVO.getConfType(), 1, 10);
		Map map = JSON.parseObject(jsonResult.toString(), Map.class);
		String string = map.get("configVO").toString();
		ConfigVO vo = JSON.parseObject(string, ConfigVO.class);
		
		Assert.assertEquals(4, vo.getConfType());
		Assert.assertEquals(1, vo.getEnable());
	}
	
	@Test
	public void addConfig() throws Exception {
		ConfigVO configVO = new ConfigVO();
		
		configVO.setId(1);
		configVO.setConfType(4);
		configVO.setEnable(1);
		configVO.setConfId("123");
		
		// 捕获调用函数的参数
		ArgumentCaptor<ConfigVO> argument = ArgumentCaptor.forClass(ConfigVO.class);
		sysConfHandler.addConfig(configVO);
						
		verify(sysConfAdapt, times(1)).addConfig(argument.capture());
		Assert.assertEquals(4, argument.getValue().getConfType());
		Assert.assertEquals(1, argument.getValue().getEnable());
	}
	
	@Test
	public void updateConfig() throws Exception {
		ConfigVO configVO = new ConfigVO();
		ConfigVO cvo = new ConfigVO();
		
		configVO.setId(1);
		configVO.setConfType(5);
		configVO.setEnable(1);
		configVO.setConfId("123");
		cvo.setId(1);
		cvo.setConfType(5);
		cvo.setEnable(1);
		cvo.setConfId("123");
		
		// 捕获调用函数的参数
		ArgumentCaptor<ConfigVO> argument = ArgumentCaptor.forClass(ConfigVO.class);
		when(sysConfAdapt.findConfigByConfKey(configVO.getConfKey())).thenReturn(cvo);
		sysConfHandler.updateConfig(configVO);
						
		verify(sysConfAdapt, times(1)).updateConfig(argument.capture());
		Assert.assertEquals(5, argument.getValue().getConfType());
		Assert.assertEquals(1, argument.getValue().getEnable());
	}
	
	@Test(expected = DataVerException.class)
	public void updateConfigBranch() throws Exception {
		ConfigVO configVO = new ConfigVO();
		ConfigVO cvo = new ConfigVO();
		
		configVO.setId(1);
		configVO.setConfType(5);
		configVO.setEnable(1);
		configVO.setConfId("123");
		cvo.setId(1);
		cvo.setConfType(5);
		cvo.setEnable(1);
		cvo.setConfId("234");
		
		// 捕获调用函数的参数
		ArgumentCaptor<ConfigVO> argument = ArgumentCaptor.forClass(ConfigVO.class);
		when(sysConfAdapt.findConfigByConfKey(configVO.getConfKey())).thenReturn(cvo);
		sysConfHandler.updateConfig(configVO);
						
		verify(sysConfAdapt, times(1)).updateConfig(argument.capture());
		Assert.assertEquals(5, argument.getValue().getConfType());
		Assert.assertEquals(1, argument.getValue().getEnable());
	}
	
	@Test
	public void deleteConfig() throws Exception {
		ConfigVO configVO = new ConfigVO();
		
		configVO.setId(1);
		configVO.setConfType(4);
		configVO.setEnable(1);
		configVO.setConfId("123");

		sysConfAdapt.deleteConfig(configVO.getConfId());
		
		sysConfHandler.deleteConfig(configVO.getConfId());
		Assert.assertEquals(4, configVO.getConfType());
		Assert.assertEquals("123", configVO.getConfId());
	}
	
	@Test
	public void getByConfId() throws Exception {
		ConfigVO configVO = new ConfigVO();
		
		configVO.setConfType(4);
		configVO.setEnable(1);
		configVO.setConfId("123");
		
		when(sysConfAdapt.getByConfId(configVO.getConfId())).thenReturn(configVO);
		
		ConfigVO vo = sysConfHandler.getByConfId(configVO.getConfId());
		Assert.assertEquals(4, vo.getConfType());
		Assert.assertEquals("123", vo.getConfId());
	}
}
