package com.thinvent.basicpf.sys;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.thinvent.basicpf.sys.adapt.IMenuAdapt;
import com.thinvent.basicpf.sys.handler.IUserHandler;
import com.thinvent.basicpf.sys.handler.impl.MenuHandlerImpl;
import com.thinvent.library.vo.MoudleVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class MenuHandlerImplTest {

	@InjectMocks
	private MenuHandlerImpl menuHandlerImpl;

	@Mock
	private IUserHandler userHandler;

	@Mock
	private IMenuAdapt menuAdapt;

	@Test
	public void getAll() throws Exception{
		menuHandlerImpl.menuAll();
	}
	
	@Test
	public void getList() throws Exception{
		menuHandlerImpl.menuList("", 1, 1);
	}
	
	@Test
	public void delMoudle() throws Exception{
		menuHandlerImpl.menuDel("");
	}
	
	@Test
	public void getMoudel() throws Exception{
		menuHandlerImpl.menuOne("");
	}

	@Test
	public void saveMoudle() throws Exception{
		MoudleVO moudleVO = new MoudleVO();
		menuHandlerImpl.menuAdd(moudleVO);
	}
	
	@Test
	public void updateMoudle() throws Exception{
		MoudleVO moudleVO = new MoudleVO();
		menuHandlerImpl.menuUpdate(moudleVO);
	}
}
