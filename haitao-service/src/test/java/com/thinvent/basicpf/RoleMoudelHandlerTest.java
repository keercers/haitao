package com.thinvent.basicpf;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.thinvent.basicpf.dao.IRoleMoudleDao;
import com.thinvent.basicpf.dao.jpa.RoleMoudelDaoImpl;
import com.thinvent.basicpf.handler.impl.RoleMoudleHandlerImpl;
import com.thinvent.basicpf.model.RoleMoudle;

@RunWith(value = SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class RoleMoudelHandlerTest {
	
	@InjectMocks
	private RoleMoudleHandlerImpl roleMoudelHandlerImpl;

	@Mock
	private IRoleMoudleDao roleMoudelDao;
	
	@Mock
	private RoleMoudelDaoImpl roleMoudelDaoImpl;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testQueryList(){
		String roleId = "16494813-7c76-4d0a-9c5d-0830ab92b557";
		RoleMoudle roleMoudel = new RoleMoudle();
		roleMoudel.setRoleId(roleId);
        //设置方法的预期返回值  
        when(roleMoudelHandlerImpl.queryList(roleId)).thenReturn(Arrays.asList(roleMoudel));  
        List<RoleMoudle> list =  roleMoudelHandlerImpl.queryList(roleId);  
        //junit测试  
        Assert.assertEquals(roleId, list.get(0).getRoleId());  
	}
	
	@Test
	public void testSave(){
		String userId = "16494813-7c76-4d0a-9c5d-0830ab92b557";
		String roleId = "16494813-7c76-4d0a-9c5d-0830ab92b557";
		String choices = "1-2";

		RoleMoudle roleMoudelMoudelId = new RoleMoudle();
		roleMoudelMoudelId.setRoleId(roleId);
		roleMoudelMoudelId.setMoudelId("1");
		roleMoudelMoudelId.setEnable(0);
		
		RoleMoudle roleMoudel = new RoleMoudle();
		roleMoudel.setRoleId(roleId);
		roleMoudel.setMoudelId("3");
		roleMoudel.setEnable(0);
        
        when(roleMoudelDaoImpl.getListRoleMoudleByUserId(userId)).thenReturn(Arrays.asList(roleMoudel,roleMoudelMoudelId));

        when(roleMoudelDao.findByRoleIdAndMoudelId(roleId, "1")).thenReturn(roleMoudelMoudelId);
        when(roleMoudelDao.findByRoleIdAndMoudelId(roleId, "3")).thenReturn(roleMoudel);

        roleMoudelHandlerImpl.save(roleId, userId, choices);
        when(roleMoudelDao.findByRoleIdAndMoudelId(roleId, "1")).thenReturn(null);

        roleMoudelHandlerImpl.save(roleId, userId, choices);
	}
}
