package com.thinvent.basicpf.util;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.thinvent.basicpf.dao.IRoleDao;
import com.thinvent.basicpf.dao.IRoleMoudleDao;
import com.thinvent.basicpf.model.Moudle;
import com.thinvent.basicpf.model.Role;
import com.thinvent.basicpf.model.RoleMoudle;
import com.thinvent.library.vo.MoudleVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ModuleUtilTest {

	@InjectMocks
	private ModuleUtil moduleUtil;

	@Mock
	private IRoleDao roleDao;

	@Mock
	private IRoleMoudleDao roleMoudleDao;

	@Test
	public void saveMoudle() throws Exception {
		MoudleVO moudleVO = new MoudleVO();
		Moudle moudle = new Moudle();
		moudle.setMoudleId("1");
		List<Role> roleList = new ArrayList<>();
		Role role = new Role();
		roleList.add(role);
		when(roleDao.findAllRole()).thenReturn(roleList);
		moduleUtil.initRoleModule(moudleVO);
		
		role.setRoleName("INIT");
		roleList.add(role);
		when(roleDao.findAllRole()).thenReturn(roleList);
		moduleUtil.initRoleModule(moudleVO);
	}

	@Test
	public void delMoudle() throws Exception {
		Moudle mudle = new Moudle();
		mudle.setMoudleId("1");
		mudle.setMoudleParentId("1");
		List<RoleMoudle> roleMoudleList = new ArrayList<>();
		RoleMoudle roleMoudle = new RoleMoudle();
		roleMoudleList.add(roleMoudle);
		when(roleMoudleDao.findByMoudelIdAndEnable("1", 1)).thenReturn(roleMoudleList);
		moduleUtil.clearRoleModule(mudle.getMoudleId());
	}
}
