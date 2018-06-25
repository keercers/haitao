package com.thinvent.basicpf;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.thinvent.basicpf.dao.IUserRoleDao;
import com.thinvent.basicpf.handler.impl.UserRoleHandlerImpl;
import com.thinvent.basicpf.model.UserRole;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.RoleVO;
import com.thinvent.library.vo.UserRoleVO;
import com.thinvent.library.vo.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class UserRoleHandlerTest {
	@Mock
	private IUserRoleDao userRoleDao;
	
	@InjectMocks
	private UserRoleHandlerImpl userRoleHandler;
	
	private UserRole userRole = new UserRole();
	
	private UserRoleVO userRoleVO = new UserRoleVO();
	
	private UserVO userVO = new UserVO();
	
	private RoleVO roleVO = new RoleVO();
	
	public UserRoleHandlerTest() {
		userRoleVO.setUserId(userVO.getUserId());
		userRoleVO.setRoleId(roleVO.getRoleId());
	}
	
	@Test
	public void saveTest() {
		when(userRoleDao.save(userRole)).thenReturn(userRole);
		userRoleHandler.save(userRoleVO);
	}
	
	@Test
	public void updateTest() throws ThinventBaseException {
		UserRoleVO urvo = new UserRoleVO();
		BeanUtils.copyProperties(userRoleVO, urvo);
		userRoleVO.setCreateUser("test_userRole update");
		when(userRoleDao.getUserRoleById(userRoleVO.getId())).thenReturn(userRole);
		when(userRoleDao.save(userRole)).thenReturn(userRole);
		userRoleHandler.update(userRoleVO);
	}
	
	@Test
	public void getUserRoleListByUserIdTest() throws ThinventBaseException {
		List<UserRole> urList = new ArrayList<>();
		userRole.setUserId(userVO.getUserId());
		userRole.setRoleId(roleVO.getRoleId());
		urList.add(userRole);
		when(userRoleDao.getUserRoleByUserId(userVO.getUserId())).thenReturn(urList);
		List<UserRoleVO> result = userRoleHandler.getUserRoleListByUserId(userVO.getUserId());
		Assert.assertTrue("查询userrole信息", result != null && 1 == result.size());
		Assert.assertTrue("查询userrole信息", result.get(0).getUserId().equals(userVO.getUserId()));
		Assert.assertTrue("查询userrole信息", result.get(0).getRoleId().equals(roleVO.getRoleId()));
	}
	
	@Test
	public void getUserRoleListByRoleId() throws ThinventBaseException {
		List<UserRole> userRoleList = new ArrayList<>();
		userRole.setUserId(userVO.getUserId());
		userRole.setRoleId(roleVO.getRoleId());
		userRoleList.add(userRole);
		when(userRoleDao.getUserRoleByRoleId(roleVO.getRoleId())).thenReturn(userRoleList);
		List<UserRoleVO> userRoleVOList = userRoleHandler.getUserRoleListByRoleId(roleVO.getRoleId());
		Assert.assertTrue("查询userrole信息", userRoleVOList != null && !userRoleVOList.isEmpty() && 1 == userRoleVOList.size());
		Assert.assertTrue("查询userrole信息", userRoleVOList.get(0).getUserId().equals(userVO.getUserId()));
		Assert.assertTrue("查询userrole信息", userRoleVOList.get(0).getRoleId().equals(roleVO.getRoleId()));
		
	}
}
