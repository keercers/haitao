package com.thinvent.basicpf.sys;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.thinvent.basicpf.sys.adapt.ISysRegisterAdapt;
import com.thinvent.basicpf.sys.handler.IUserHandler;
import com.thinvent.basicpf.sys.handler.impl.SysRegisterHandlerImpl;
import com.thinvent.library.vo.MoudleVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class SysRegisterHandlerImplTest {

	@InjectMocks
	private SysRegisterHandlerImpl sysRegisterHandlerImpl;

	@Mock
	private IUserHandler userHandler;

	@Mock
	private ISysRegisterAdapt sysRegisterAdapt;
	
	@Test
	public void getList() throws Exception{
		sysRegisterHandlerImpl.sysRegisterList("", 1, 1);
	}
	
	@Test
	public void delMoudle() throws Exception{
		sysRegisterHandlerImpl.sysRegisterDel("");
	}
	
	@Test
	public void getMoudel() throws Exception{
		sysRegisterHandlerImpl.sysRegisterOne("");
	}

	@Test
	public void saveMoudle() throws Exception{
		MoudleVO moudleVO = new MoudleVO();
		sysRegisterHandlerImpl.sysRegisterAdd(moudleVO);
	}
	
	@Test
	public void updateMoudle() throws Exception{
		MoudleVO moudleVO = new MoudleVO();
		sysRegisterHandlerImpl.sysRegisterUpdate(moudleVO);
	}
}
