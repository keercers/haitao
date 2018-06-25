package com.thinvent.basicpf;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.thinvent.basicpf.dao.IUserDao;
import com.thinvent.basicpf.dao.IUserMesDao;
import com.thinvent.basicpf.dao.IUserRoleDao;
import com.thinvent.basicpf.handler.impl.DepartmentHandlerImpl;
import com.thinvent.basicpf.handler.impl.PositionHandlerImpl;
import com.thinvent.basicpf.handler.impl.RoleHandlerImpl;
import com.thinvent.basicpf.handler.impl.UserHandlerImpl;
import com.thinvent.basicpf.handler.impl.UserRoleHandlerImpl;
import com.thinvent.basicpf.model.Role;
import com.thinvent.basicpf.model.User;
import com.thinvent.basicpf.model.UserMes;
import com.thinvent.basicpf.model.UserRole;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.RoleVO;
import com.thinvent.library.vo.UserMesVO;
import com.thinvent.library.vo.UserRoleVO;
import com.thinvent.library.vo.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
public class UserHandlerTest {
	
	@Mock
	private IUserDao userDao;
	
	@Mock
	private IUserMesDao userMesDao;
	
	@Mock
	private IUserRoleDao userRoleDao;
	
	@InjectMocks
	private UserHandlerImpl userHandler;
	
	@InjectMocks
	private RoleHandlerImpl roleHandler;
	
	@InjectMocks
	private DepartmentHandlerImpl departmentHandler;
	
	@InjectMocks
	private PositionHandlerImpl positionHandler;
	
	@InjectMocks
	private UserRoleHandlerImpl userRoleHandler;
	
	private Query query;
	
	private UserVO userVO = new UserVO();
	
	private User user = new User();
	
	private UserMesVO userMesVO = new UserMesVO();
	
	private UserMes userMes = new UserMes();
	
	private UserRoleVO userRoleVO = new UserRoleVO();
	
	private UserRole userRole = new UserRole();
	
	private RoleVO roleVO = new RoleVO();
	
	private Role role = new Role();
	
	@Test
	public void listUserByCondition() throws ThinventBaseException {
		user.setLoginName("test");
		when(userDao.findUser("test")).thenReturn(user);
		UserVO result = userHandler.findUser("test");
		Assert.assertTrue("查询test用户", result != null);
		Assert.assertTrue("查询test用户", result.getLoginName().equals(user.getLoginName()));
	}
	
	@Test
	public void findUserByUserIdTest() throws ThinventBaseException {
		user.setUserId(userVO.getUserId());
		userMes.setUserId(userVO.getUserId());
		when(userDao.findUserByUserId(userVO.getUserId())).thenReturn(user);
		when(userMesDao.getUserMesByUserId(userVO.getUserId())).thenReturn(userMes);
		when(userMesDao.getUserMesByUserId(userVO.getUserId())).thenReturn(this.userMes);
		UserVO result = userHandler.findUserByUserId(userVO.getUserId());
		Assert.assertTrue("根据用户ID查询用户", result != null);
		Assert.assertTrue("根据用户ID查询用户", result.getUserId().equals(user.getUserId()));
	}
	
	@Test
	public void updateUserTest() throws ThinventBaseException {
		User userOld = new User();
		User ret = new User();
		user.setUserId(userVO.getUserId());
		BeanUtils.copyProperties(user, userOld);
		BeanUtils.copyProperties(user, ret);
		user.setUserName("test_new");
		ret.setUserName("test_new");
		when(userDao.findUserByUserId(userVO.getUserId())).thenReturn(user);
		when(userDao.save(user)).thenReturn(ret);
		userHandler.updateUser(userVO);
		Assert.assertTrue("更新用户部分字段", !ret.getUserName().equals(userOld.getUserName()));
	}
	
	@Test
	public void userSaveTest() {
		UserVO userVO = new UserVO();
		List list = new ArrayList<>();		
		userVO.setUserId(this.userVO.getUserId());
		userRole.setUserId(this.userVO.getUserId());
		userRole.setRoleId(roleVO.getRoleId());
		Map map = new HashMap<>();
		map.put("userId", userVO.getUserId());
		map.put("roleId", roleVO.getRoleId());
		list.add(map);
		userVO.setUserRoleList(list);
		
		user.setUserId(this.userVO.getUserId());
		userMes.setUserId(this.userVO.getUserId());
		userHandler.save(userVO);
	}
	
	@Test
	public void updateUserAllInfoTest() throws ThinventBaseException {
		List<UserRole> userRoleRet = new ArrayList<>();
		List userRoleUVO = new ArrayList<>();
		
		userVO.setId(1);
		user.setUserId(userVO.getUserId());
		userMes.setUserId(userVO.getUserId());
		userRole.setUserId(userVO.getUserId());
		userRole.setRoleId(roleVO.getRoleId());
		
		Map map = new HashMap<>();
		map.put("userId", userVO.getUserId());
		map.put("roleId", roleVO.getRoleId());
		userRoleUVO.add(map);
		
		userRoleRet.add(userRole);
		userVO.setUserRoleList(userRoleUVO);
		userVO.setUserImages("test/test.jpg");
		userMes.setUserImages("test/test.jpg");
		when(userDao.findUserByUserId(userVO.getUserId())).thenReturn(user);
		when(userMesDao.getUserMesByUserId(userVO.getUserId())).thenReturn(userMes);
		when(userRoleDao.getUserRoleByUserId(userVO.getUserId())).thenReturn(userRoleRet);
		
//		未修改权限
		userHandler.updateUserAllInfo(userVO);
		
//		增加了权限分支
		Map mapAdd = new HashMap<>();
		RoleVO roleVONew = new RoleVO();
		mapAdd.put("roleId", roleVONew.getRoleId());
		mapAdd.put("userId", userVO.getUserId());
		userRoleUVO.add(mapAdd);
		userVO.setUserRoleList(userRoleUVO);
		
		UserRole ur = new UserRole();
		ur.setRoleId(roleVONew.getRoleId());
		ur.setUserId(userVO.getUserId());
		ur.setEnable(1);
		when(userRoleDao.getUserRoleByUserIdAndRoleId(ur.getUserId(), ur.getRoleId())).thenReturn(ur);
		userHandler.updateUserAllInfo(userVO);
		
		when(userRoleDao.getUserRoleByUserIdAndRoleId(ur.getUserId(), ur.getRoleId())).thenReturn(null);
		userHandler.updateUserAllInfo(userVO);
		
//		删除了权限分支
		userVO.setUserRoleList(null);
		userHandler.updateUserAllInfo(userVO);
	}
	
	@Test
	public void countUserTest() {
		List<User> users = new ArrayList<>();
		user.setUserId(userVO.getUserId());
		users.add(user);
		int count = 1;
		when(userDao.listAllUser()).thenReturn(users);
		Map map = userHandler.listAllUser();
		List<UserVO> vos = (List<UserVO>) map.get("userList");
		int result = (int) map.get("count");
		Assert.assertTrue("查询总用户", count == result);
		Assert.assertTrue("查询总用户", vos.size() == result);
		Assert.assertTrue("查询总用户", vos.get(0).getUserId().equals(userVO.getUserId()));
	}
	
	@Test
	public void deleteUserTest() throws ThinventBaseException {
		List<UserRole> userRoleList = new ArrayList<>();
		userRoleList.add(userRole);
		user.setUserId(userVO.getUserId());
		when(userDao.findUserByUserId(userVO.getUserId())).thenReturn(user);
		when(userRoleDao.getUserRoleByUserId(userVO.getUserId())).thenReturn(userRoleList);
		userHandler.deleteUser(userVO.getUserId());
	}
	
}
