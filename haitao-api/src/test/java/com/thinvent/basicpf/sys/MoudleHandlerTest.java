package com.thinvent.basicpf.sys;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.thinvent.basicpf.sys.adapt.IMoudleAdapt;
import com.thinvent.basicpf.sys.handler.impl.MoudleHandlerImpl;
import com.thinvent.library.vo.MoudleVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class MoudleHandlerTest {
	
	@Mock
	private IMoudleAdapt moudleAdapt;
	@InjectMocks
	private MoudleHandlerImpl moudleHandler;

	MoudleVO moudleVO = new MoudleVO();

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getByLevel() throws Exception{
		//构造dao方法返回对象
		List<MoudleVO> demoList = new ArrayList<>();
		when(moudleAdapt.getByLevel("1","1")).thenReturn(demoList);
		moudleHandler.getByLevel("1","1");
	}

	@Test
	public void getTreeBySign() throws Exception{
		when(moudleAdapt.getTreeBySign("1-2-", "1")).thenReturn("");
		moudleHandler.getTreeBySign("1-2-", "1");
	}
	
	@Test
	public void getForbidList() throws Exception{
		when(moudleAdapt.getForbidList("1-2-")).thenReturn("");
		moudleHandler.getForbidList("1-2-");
	}	
}
