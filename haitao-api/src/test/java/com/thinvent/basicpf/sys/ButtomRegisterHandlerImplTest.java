package com.thinvent.basicpf.sys;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.thinvent.basicpf.sys.adapt.IButtomRegisterAdapt;
import com.thinvent.basicpf.sys.handler.IUserHandler;
import com.thinvent.basicpf.sys.handler.impl.ButtomRegisterHandlerImpl;
import com.thinvent.library.vo.MoudleVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ButtomRegisterHandlerImplTest {

	@InjectMocks
	private ButtomRegisterHandlerImpl buttomRegisterHandlerImpl;

	@Mock
	private IUserHandler userHandler;

	@Mock
	private IButtomRegisterAdapt buttomRegisterAdapt;
	
	@Test
	public void getList() throws Exception{
		buttomRegisterHandlerImpl.buttomRegisterList("", "", 1, 1);
	}
	
	@Test
	public void delMoudle() throws Exception{
		buttomRegisterHandlerImpl.buttomRegisterDel("");
	}
	
	@Test
	public void getMoudel() throws Exception{
		buttomRegisterHandlerImpl.buttomRegisterOne("");
	}

	@Test
	public void saveMoudle() throws Exception{
		MoudleVO moudleVO = new MoudleVO();
		buttomRegisterHandlerImpl.buttomRegisterAdd(moudleVO);
	}
	
	@Test
	public void updateMoudle() throws Exception{
		MoudleVO moudleVO = new MoudleVO();
		buttomRegisterHandlerImpl.buttomRegisterUpdate(moudleVO);
	}
}
