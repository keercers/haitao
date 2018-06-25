package com.thinvent.basicpf;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.thinvent.basicpf.dao.ISysConfDao;
import com.thinvent.basicpf.handler.impl.SysConfHandlerImpl;
import com.thinvent.basicpf.model.Config;
import com.thinvent.library.util.UUIDUtil;
import com.thinvent.library.vo.ConfigVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class SysConfHandlerTest {
	
	@Mock
	private ISysConfDao sysConfDao;
	
	@InjectMocks
	private SysConfHandlerImpl sysConfHandler;
	
	private int pageIndex = 1;
	private int pageSize = 10;
	private PageRequest pageable = new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "updateTime")); 
	
	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void saveSysParam() throws Exception{
		ConfigVO configVO = new ConfigVO();
		
		configVO.setConfType(5);
		configVO.setEnable(1);
		configVO.setCreateUser("ssc");

		ArgumentCaptor<Config> argument = ArgumentCaptor.forClass(Config.class);
		sysConfHandler.saveSysParam(configVO);
				
		verify(sysConfDao, times(1)).save(argument.capture());
		Assert.assertEquals("ssc", argument.getValue().getCreateUser());
		Assert.assertEquals(5, argument.getValue().getConfType());
	}
	
	@Test
	public void findSysParam() throws Exception {
		List<Config> demolist = new ArrayList<>();
		ConfigVO configVO = new ConfigVO();
		Config config = new Config();
		
		configVO.setConfType(5);
		configVO.setEnable(1);
		configVO.setCreateUser("ssc");
		configVO.setConfId("1234");
		BeanUtils.copyProperties(configVO, config);
		demolist.add(config);
		
		Page<Config> page = new PageImpl<>(demolist, pageable, 10);
		//分支
		when(sysConfDao.findByEnable(1, pageable)).thenReturn(page);
		
		Page<Config> listResult = sysConfHandler.findSysParam(10, pageable);
		Assert.assertEquals(5, listResult.getContent().get(0).getConfType());
		Assert.assertEquals("1234", listResult.getContent().get(0).getConfId());
		//分支
		when(sysConfDao.findByConfTypeAndEnable(5, 1, pageable)).thenReturn(page);
		
		listResult = sysConfHandler.findSysParam(5, pageable);
		Assert.assertEquals(5, listResult.getContent().get(0).getConfType());
		Assert.assertEquals("1234", listResult.getContent().get(0).getConfId());
	}
	
	@Test
	public void updateSysParam() throws Exception {
		ConfigVO configVO = new ConfigVO();
		Config config = new Config();
		
		configVO.setConfType(6);
		configVO.setEnable(1);
		configVO.setCreateTime(new Date());
		configVO.setCreateUser("ssc");
		BeanUtils.copyProperties(configVO, config);

		ArgumentCaptor<Config> argument = ArgumentCaptor.forClass(Config.class);
		when(sysConfDao.findOneByConfIdAndEnable(config.getConfId(), 1)).thenReturn(config);
		sysConfHandler.updateSysParam(configVO);
				
		verify(sysConfDao, times(1)).save(argument.capture());
		Assert.assertEquals("ssc", argument.getValue().getCreateUser());
		Assert.assertEquals(6, argument.getValue().getConfType());
	}
	
	@Test
	public void deleteSysParam() throws Exception {
		ConfigVO configVO = new ConfigVO();
		Config config = new Config();
		
		configVO.setConfType(5);
		configVO.setEnable(1);
		configVO.setCreateUser("ssc");
		BeanUtils.copyProperties(configVO, config);
		
		ArgumentCaptor<Config> argument = ArgumentCaptor.forClass(Config.class);
		when(sysConfDao.findOneByConfIdAndEnable(config.getConfId(), 1)).thenReturn(config);
		sysConfHandler.deleteSysParam(config.getConfId());
		
		verify(sysConfDao, times(1)).save(argument.capture());
		Assert.assertEquals("ssc", argument.getValue().getCreateUser());
		Assert.assertEquals(5, argument.getValue().getConfType());
		Assert.assertEquals(0, argument.getValue().getEnable());
	}
	
	@Test
	public void findByConfId() throws Exception {
		ConfigVO configVO = new ConfigVO();
		Config config = new Config();
		
		configVO.setConfType(5);
		configVO.setEnable(1);
		configVO.setCreateUser("ssc");
		configVO.setConfId(UUIDUtil.getUuid());
		BeanUtils.copyProperties(configVO, config);
		
		when(sysConfDao.findOneByConfIdAndEnable(config.getConfId(), 1)).thenReturn(config);
		Config configReturn = sysConfHandler.findByConfId(config.getConfId());
		Assert.assertEquals(5, configReturn.getConfType());
		Assert.assertEquals(1, configReturn.getEnable());
		Assert.assertEquals("ssc", configReturn.getCreateUser());
	}
}
