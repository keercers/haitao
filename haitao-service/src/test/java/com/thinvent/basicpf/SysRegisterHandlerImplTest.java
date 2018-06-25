package com.thinvent.basicpf;

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
import com.thinvent.basicpf.dao.ISysRegisterDao;
import com.thinvent.basicpf.handler.IUserHandler;
import com.thinvent.basicpf.handler.impl.SysRegisterHandlerImpl;
import com.thinvent.basicpf.model.Moudle;
import com.thinvent.basicpf.model.Role;
import com.thinvent.basicpf.model.RoleMoudle;
import com.thinvent.basicpf.util.ModuleUtil;
import com.thinvent.library.vo.MoudleVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class SysRegisterHandlerImplTest {

	@InjectMocks
	private SysRegisterHandlerImpl sysRegisterHandlerImpl;

	@Mock
	private IRoleDao roleDao;
	
	@Mock
	private IRoleMoudleDao roleMoudleDao;
	
	@Mock
	private IUserHandler userHandler;

	@Mock
    private ISysRegisterDao sysRegisterDao;

	@Mock
	private ModuleUtil moduleUtil;

	@Test
	public void getList() throws Exception{
		sysRegisterHandlerImpl.sysRegisterList("", 1, 1);
	}
	
	@Test
	public void delMoudle() throws Exception{
		List<Moudle> list = new ArrayList<>();
		Moudle mudle = new Moudle();
		mudle.setMoudleId("1");
		List<RoleMoudle> roleMoudleList = new ArrayList<>();
		RoleMoudle roleMoudle = new RoleMoudle();
		roleMoudleList.add(roleMoudle);
		when(sysRegisterDao.findByMoudleParentIdAndEnable("1", 1)).thenReturn(list);
		when(sysRegisterDao.findByMoudleIdAndEnable("1", 1)).thenReturn(mudle);
		when(roleMoudleDao.findByMoudelIdAndEnable("1", 1)).thenReturn(roleMoudleList);
		sysRegisterHandlerImpl.sysRegisterDel("1");
	}
	
	@Test
	public void getMoudel() throws Exception{
		sysRegisterHandlerImpl.sysRegisterOne("");
	}

	@Test
	public void saveMoudle() throws Exception{
		MoudleVO moudleVO = new MoudleVO();
		Moudle moudle = new Moudle();
		moudle.setMoudleParentId("1");
		moudle.setMoudleLevel("1");
		when(sysRegisterDao.findByMoudleIdAndEnable(moudleVO.getMoudleParentId(), 1)).thenReturn(moudle);
		List<Role> roleList = new ArrayList<>();
		Role role = new Role();
		role.setRoleName("INIT");
		roleList.add(role);
		when(roleDao.findAllRole()).thenReturn(roleList);
		sysRegisterHandlerImpl.sysRegisterAdd(moudleVO);
	}
	
	@Test
	public void findMaxMoudleId() throws Exception{
		List<Moudle> moudleList = new ArrayList<>();
		Moudle moudle = new Moudle();
		moudle.setMoudleId("1");
		moudleList.add(moudle);
		when(sysRegisterDao.findAll()).thenReturn(moudleList);
		sysRegisterHandlerImpl.findMaxSysRegisterId();
	}
	
	@Test
	public void updateMoudle() throws Exception{
		MoudleVO moudleVO = new MoudleVO();
		moudleVO.setMoudleId("1");
		Moudle moudle = new Moudle();
		when(sysRegisterDao.findByMoudleIdAndEnable("1", 1)).thenReturn(moudle);
		sysRegisterHandlerImpl.sysRegisterUpdate(moudleVO);
	}
}
