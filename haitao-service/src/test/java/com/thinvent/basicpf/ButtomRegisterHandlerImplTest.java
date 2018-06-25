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

import com.thinvent.basicpf.dao.IButtomRegisterDao;
import com.thinvent.basicpf.dao.IRoleDao;
import com.thinvent.basicpf.dao.IRoleMoudleDao;
import com.thinvent.basicpf.handler.impl.ButtomRegisterHandlerImpl;
import com.thinvent.basicpf.model.Moudle;
import com.thinvent.basicpf.model.Role;
import com.thinvent.basicpf.model.RoleMoudle;
import com.thinvent.basicpf.util.ModuleUtil;
import com.thinvent.library.vo.MoudleVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ButtomRegisterHandlerImplTest {

	@InjectMocks
	private ButtomRegisterHandlerImpl buttomRegisterHandlerImpl;

	@Mock
	private IRoleDao roleDao;
	
	@Mock
	private IRoleMoudleDao roleMoudleDao;

	@Mock
    private IButtomRegisterDao buttomRegisterDao;

	@Mock
	private ModuleUtil moduleUtil;
	
	@Test
	public void getList() throws Exception{
		buttomRegisterHandlerImpl.buttomRegisterList("", "", 1, 1);
	}
	
	@Test
	public void delMoudle() throws Exception{
		List<Moudle> list = new ArrayList<>();
		Moudle mudle = new Moudle();
		mudle.setMoudleId("1");
		mudle.setMoudleParentId("1");
		List<RoleMoudle> roleMoudleList = new ArrayList<>();
		RoleMoudle roleMoudle = new RoleMoudle();
		roleMoudleList.add(roleMoudle);
		when(buttomRegisterDao.findByMoudleParentIdAndEnable(mudle.getMoudleParentId(), 1)).thenReturn(list);
		when(buttomRegisterDao.findByMoudleIdAndEnable("1", 1)).thenReturn(mudle);
		when(roleMoudleDao.findByMoudelIdAndEnable("1", 1)).thenReturn(roleMoudleList);
		buttomRegisterHandlerImpl.buttomRegisterDel("1");
	}
	
	@Test
	public void getMoudel() throws Exception{
		buttomRegisterHandlerImpl.buttomRegisterOne("");
	}

	@Test
	public void saveMoudle() throws Exception{
		MoudleVO moudleVO = new MoudleVO();
		Moudle moudle = new Moudle();
		moudle.setMoudleId("1");
		Moudle moudleParent = new Moudle();
		moudleParent.setMoudleParentId("1");
		moudleParent.setMoudleLevel("1");
		moudleParent.setMoudleId("1");
		when(buttomRegisterDao.findByMoudleIdAndEnable(moudleVO.getMoudleParentId(), 1)).thenReturn(moudleParent);
		when(buttomRegisterDao.save(moudle)).thenReturn(moudle);
		List<Role> roleList = new ArrayList<>();
		Role role = new Role();
		role.setRoleName("INIT");
		roleList.add(role);
		when(roleDao.findAllRole()).thenReturn(roleList);
		buttomRegisterHandlerImpl.buttomRegisterAdd(moudleVO);
	}
	
	@Test
	public void findMaxMoudleId() throws Exception{
		List<Moudle> moudleList = new ArrayList<>();
		Moudle moudle = new Moudle();
		moudle.setMoudleId("1");
		moudleList.add(moudle);
		when(buttomRegisterDao.findAll()).thenReturn(moudleList);
		buttomRegisterHandlerImpl.findMaxButtomRegisterId();
	}
	
	@Test
	public void updateMoudle() throws Exception{
		MoudleVO moudleVO = new MoudleVO();
		moudleVO.setMoudleId("1");
		Moudle moudle = new Moudle();
		when(buttomRegisterDao.findByMoudleIdAndEnable("1", 1)).thenReturn(moudle);
		buttomRegisterHandlerImpl.buttomRegisterUpdate(moudleVO);
	}
}
