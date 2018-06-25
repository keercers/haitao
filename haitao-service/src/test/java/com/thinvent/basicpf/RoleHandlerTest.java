package com.thinvent.basicpf;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thinvent.basicpf.dao.jpa.RoleDaoRepositoryImpl;
import com.thinvent.basicpf.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.thinvent.basicpf.dao.IMoudleDao;
import com.thinvent.basicpf.dao.IRoleDao;
import com.thinvent.basicpf.dao.IRoleMoudleDao;
import com.thinvent.basicpf.dao.IUserRoleDao;
import com.thinvent.basicpf.handler.impl.RoleHandlerImpl;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.RoleVO;
import com.thinvent.library.vo.UserRoleVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
public class RoleHandlerTest {
	@Mock
	private IRoleDao roledao;
	@Mock
	private IUserRoleDao userRoleDao;
	@Mock
	private IMoudleDao moudleDao;
	@Mock
	private IRoleMoudleDao roleMoudleDao;
	@Mock
	private RoleDaoRepositoryImpl roleDaoRepository;;
	@InjectMocks
	private RoleHandlerImpl roleHandler;

	private RoleVO roleVO = new RoleVO();
	private Role role = new Role();
	private Pageable pageable = new PageRequest(0,100);
	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		roleVO.setRoleName("小雨测试").setRoleId("813139");
		role.setRoleId("813139").setRoleName("小雨测试").setRoleType("1").setEnable(1);
	}

	 @Test
	   public void getRoleById() throws ThinventBaseException {
		 when(roledao.getByRoleId("813139")).thenReturn(role);
		 Role result = roleHandler.findRoleByRoleId("813139");
		 Assert.assertTrue(result != null);
		 Assert.assertEquals("813139",result.getRoleId());
		 Assert.assertEquals("小雨测试",result.getRoleName());
	 }

	    @Test
	    public void deleteRole() throws Exception {
	    	List<UserRole> urList = new ArrayList();
	    	List<RoleMoudle> rmList = new ArrayList();
	    	UserRole ur = new UserRole();
	    	urList.add(ur);
	    	RoleMoudle rm = new RoleMoudle();
	    	rmList.add(rm);
	        when(roledao.getByRoleId(role.getRoleId())).thenReturn(role);
	        when(userRoleDao.getUserRoleByRoleId(roleVO.getRoleId())).thenReturn(urList);
	        when(roleMoudleDao.findByRoleIdAndEnable(roleVO.getRoleId(), 1)).thenReturn(rmList);
	        roleHandler.delete("813139");
	        verify(roledao).save(role);
	        verify(roleMoudleDao).save(rm);
	        verify(userRoleDao).save(ur);
			Assert.assertTrue(role.getEnable() == 0);
	    }

	    @Test
		public void addRole() throws Exception {
			List list = new ArrayList<>();
			Map<String,Object> map = new HashMap<>();
			map.put("userId","1");
			list.add(map);
			roleVO.setUserRoleList(list);
			Moudle moudle = new Moudle();
			moudle.setMoudleName("moudle_test").setMoudleId("1");
			List<Moudle> moudleList = new ArrayList<>();
			moudleList.add(moudle);
			when(moudleDao.findByEnable(1)).thenReturn(moudleList);
 			roleHandler.addRole(roleVO);
			ArgumentCaptor<Role> argument = ArgumentCaptor.forClass(Role.class);
			verify(roledao, times(1)).save(argument.capture());
			Assert.assertEquals("小雨测试", argument.getValue().getRoleName());
			Assert.assertEquals("813139", argument.getValue().getRoleId());
		}

		@Test
		public void findAllRole() throws Exception {
			List<Role> roleList = new ArrayList();
			roleList.add(role);
			when(roledao.findAllRole()).thenReturn(roleList);
			List<RoleVO> result = roleHandler.findAllRole();
			Assert.assertEquals("小雨测试",result.get(0).getRoleName());
			Assert.assertEquals("1",result.get(0).getRoleType());
		}
		@Test
		public void updateRole() throws Exception {
			List<Map<String, Object>> userRoleList = new ArrayList<>();
			List<UserRole> urList = new ArrayList<>();
			UserRoleVO urVO = new UserRoleVO();
			UserRole ur = new UserRole();
			ur.setRoleId(roleVO.getRoleId());
			ur.setUserRoleId(urVO.getUserRoleId());
			urList.add(ur);
			Map<String, Object> map = new HashMap<>();
			map.put("userId", "123");
			userRoleList.add(map);
			
			when(roledao.getByRoleId(roleVO.getRoleId())).thenReturn(role);
			when(userRoleDao.getUserRoleByRoleId(roleVO.getRoleId())).thenReturn(urList);
			when(userRoleDao.getUserRoleByUserIdAndRoleId("123", roleVO.getRoleId())).thenReturn(ur);
			when(roleHandler.findRoleByRoleId("813139")).thenReturn(role);
			
			// branch userRoleVOList == null
			roleHandler.update(roleVO);
			verify(roledao).save(role);

			// branch userRoleVOList != null
			roleVO.setUserRoleList(userRoleList);
			
			// branch ur != null
			roleHandler.update(roleVO);
			
			// branch ur == null
			when(userRoleDao.getUserRoleByUserIdAndRoleId("123", roleVO.getRoleId())).thenReturn(null);
			roleHandler.update(roleVO);
			
			// branch entry not containsKey("userId")
			map.clear();
			roleHandler.update(roleVO);
		}
	    @Test
	    public void listRoleByRoleNameAndRoleType() throws Exception {
			Map<String,  Object> map = new HashMap();
			List<RoleVO> vos = new ArrayList<>();
			Map<String,  Object> result = new HashMap();
			vos.add(roleVO);
			map.put("roleList",vos);
			when(roleDaoRepository.listAllRoleByCondition(pageable, "测试角色", "0")).thenReturn(map);
			result = roleHandler.listAllRoleByCondition(pageable, "测试角色", "0");
			Assert.assertTrue(result != null);
			Assert.assertEquals(vos, result.get("roleList"));
	    }
}
