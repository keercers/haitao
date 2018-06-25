package com.thinvent.basicpf;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.thinvent.basicpf.dao.IMenuDao;
import com.thinvent.basicpf.dao.IRoleDao;
import com.thinvent.basicpf.dao.IRoleMoudleDao;
import com.thinvent.basicpf.handler.impl.MenuHandlerImpl;
import com.thinvent.basicpf.model.Moudle;
import com.thinvent.basicpf.model.Role;
import com.thinvent.basicpf.model.RoleMoudle;
import com.thinvent.basicpf.util.ModuleUtil;
import com.thinvent.library.vo.MoudleVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class MenuHandlerImplTest {

	@InjectMocks
	private MenuHandlerImpl menuHandlerImpl;

	@Mock
	private IRoleDao roleDao;
	
	@Mock
	private IRoleMoudleDao roleMoudleDao;

	@Mock
    private IMenuDao menuDao;

	@Mock
	private ModuleUtil moduleUtil;

	@Test
	public void getList() throws Exception{
		menuHandlerImpl.menuList("", 1, 1);
	}
	
	@Test
	public void delMoudle() throws Exception{
		List<Moudle> list = new ArrayList<>();
		Moudle mudle = new Moudle();
		mudle.setMoudleId("1");
		List<RoleMoudle> roleMoudleList = new ArrayList<>();
		RoleMoudle roleMoudle = new RoleMoudle();
		roleMoudleList.add(roleMoudle);
		when(menuDao.findByMoudleParentIdAndEnable("1", 1)).thenReturn(list);
		when(menuDao.findByMoudleIdAndEnable("1", 1)).thenReturn(mudle);
		when(roleMoudleDao.findByMoudelIdAndEnable("1", 1)).thenReturn(roleMoudleList);
		menuHandlerImpl.menuDel("1");
	}
	
	@Test
	public void getMoudel() throws Exception{
		menuHandlerImpl.menuOne("");
	}

	@Test
	public void saveMoudle() throws Exception{
		MoudleVO moudleVO = new MoudleVO();
		Moudle moudle = new Moudle();
		moudle.setMoudleParentId("1");
		moudle.setMoudleLevel("1");
		when(menuDao.findByMoudleIdAndEnable(moudleVO.getMoudleParentId(), 1)).thenReturn(moudle);
		List<Role> roleList = new ArrayList<>();
		Role role = new Role();
		role.setRoleName("INIT");
		roleList.add(role);
		when(roleDao.findAllRole()).thenReturn(roleList);
		menuHandlerImpl.menuAdd(moudleVO);
	}
	
	@Test
	public void findMaxMoudleId() throws Exception{
		List<Moudle> moudleList = new ArrayList<>();
		Moudle moudle = new Moudle();
		moudle.setMoudleId("1");
		moudleList.add(moudle);
		when(menuDao.findAll()).thenReturn(moudleList);
		menuHandlerImpl.findMaxMenuId();
	}
	
	@Test
	public void updateMoudle() throws Exception{
		List<Moudle> moudleList = new ArrayList<>();
		MoudleVO moudleVO = new MoudleVO();
		moudleVO.setMoudleId("1");
		moudleVO.setMoudleParentId("1");
		MoudleVO moudleVOSec = new MoudleVO();
		moudleVOSec.setMoudleId("1");
		moudleVOSec.setMoudleParentId("2");
		Moudle moudle = new Moudle();
		moudle.setMoudleParentId("1");
		moudle.setMoudleSign("1-");
		moudle.setMoudleLevel("1");
		moudle.setMoudleId("1");
		moudleList.add(moudle);
		when(menuDao.findByMoudleIdAndEnable("1", 1)).thenReturn(moudle);
		when(menuDao.findByMoudleIdAndEnable("2", 1)).thenReturn(moudle);
		when(menuDao.findByMoudleIdAndEnable(moudleVO.getMoudleId(), 1)).thenReturn(moudle);
		when(menuDao.findByMoudleSignLikeAndEnable("1-" + "%", 1)).thenReturn(moudleList);
		menuHandlerImpl.menuUpdate(moudleVO);
	}
	
	@Test
	public void updateMoudleBranch() throws Exception {
		List<Moudle> moudleList = new ArrayList<>();
		MoudleVO moudleVO = new MoudleVO();
		moudleVO.setMoudleId("1");
		moudleVO.setMoudleParentId("2");
		MoudleVO moudleVOSec = new MoudleVO();
		moudleVOSec.setMoudleId("1");
		moudleVOSec.setMoudleParentId("2");
		Moudle moudle = new Moudle();
		moudle.setMoudleParentId("1");
		moudle.setMoudleSign("1-");
		moudle.setMoudleLevel("1");
		moudle.setMoudleId("1");
		moudleList.add(moudle);
		when(menuDao.findByMoudleIdAndEnable(moudleVO.getMoudleId(), 1)).thenReturn(moudle);
		
		// branch parentId not equal
		Moudle moudleParent = new Moudle();
		moudleParent.setMoudleSign("2-");
		moudleParent.setMoudleLevel("2");
		moudleParent.setMoudleParentId("2");
		when(menuDao.findByMoudleIdAndEnable(moudleVO.getMoudleParentId(), 1)).thenReturn(moudleParent);
		when(menuDao.findByMoudleSignLikeAndEnable("1-"+"1-" + "%", 1)).thenReturn(moudleList);
		Map map = menuHandlerImpl.menuUpdate(moudleVO);
		
		String result = (String) map.get("status");
		Assert.assertTrue("updateMoudle", result.equals("200"));
	}
	
	@Test
	public void findByEnable() throws Exception{
		menuHandlerImpl.menuAll();
	}
}
