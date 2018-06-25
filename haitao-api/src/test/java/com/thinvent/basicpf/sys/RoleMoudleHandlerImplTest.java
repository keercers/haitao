package com.thinvent.basicpf.sys;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.thinvent.basicpf.sys.adapt.IRoleMoudleAdapt;
import com.thinvent.basicpf.sys.handler.impl.RoleMoudleHandlerImpl;
import com.thinvent.library.exception.ThinventBaseException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class RoleMoudleHandlerImplTest {

	@InjectMocks
	private RoleMoudleHandlerImpl roleMoudleHandlerImpl;
	
	@Mock
	private IRoleMoudleAdapt roleMoudleAdapt;
	
	@Test
	public void getListByRoleId() throws ThinventBaseException{
		String roleId = "fuzhi";
		roleMoudleHandlerImpl.getListByRoleId(roleId);
	}

	@Test
	public void save() throws ThinventBaseException {
		String roleId ="fuzhi";
		String userId = "fuzhi";
		String choices = "fuzhi";
		roleMoudleHandlerImpl.save(roleId, userId, choices);
	}
}
