package com.thinvent.basicpf;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.thinvent.basicpf.dao.IMoudleDao;
import com.thinvent.basicpf.dao.IRoleMoudleDao;
import com.thinvent.basicpf.dao.IUserRoleDao;
import com.thinvent.basicpf.handler.impl.MoudleHandlerImpl;
import com.thinvent.basicpf.model.Moudle;
import com.thinvent.basicpf.model.RoleMoudle;
import com.thinvent.basicpf.model.UserRole;
import com.thinvent.library.vo.MoudleVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class MoudleHandlerTest{

	@InjectMocks
	private MoudleHandlerImpl moudleHandler;
	
	@Mock
	private IMoudleDao moudleDao;
	
	@Mock
	private IUserRoleDao userRoleDao;

	@Mock
	private IRoleMoudleDao roleMoudleDao;
	
	MoudleVO moudleVO = new MoudleVO();
	Moudle moudle = new Moudle();
	
	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		moudleVO.setMoudleName("测试-wangxu");
	}
	
	@Test
	public void findByMoudleLevelAndEnable() throws Exception{
		//构造dao方法返回对象
		List<Moudle> demoList = new ArrayList<>();
		moudle.setMoudleLevel("1");
		moudle.setEnable(1);
		moudle.setMoudleId("1");
		demoList.add(moudle);
		when(moudleDao.getMoudleByUserId("1")).thenReturn(demoList);
		demoList = moudleHandler.getListMoudleByUserId("1");
		when(moudleDao.findByMoudleLevelAndMoudleIdAndEnable("1", "1", 1)).thenReturn(moudle);
		//测试handler方法返回的是否正确
		moudleHandler.findByMoudleLevelAndEnable("1","1");
	}
	
	@Test
	public void findTreeByMoudleSignLike() throws Exception{
		//构造dao方法返回对象
		List<Moudle> demoList = new ArrayList<>();
		moudle.setMoudleLevel("1");
		moudle.setEnable(1);
		moudle.setMoudleId("1");
		demoList.add(moudle);
		List<String> moudleIdList = new ArrayList<>();
		moudleIdList.add("1");
		when(moudleDao.getMoudleByUserId("1")).thenReturn(demoList);
		when(moudleDao.findByMoudleSignLikeAndMoudleIdInAndEnable("1", moudleIdList, 1)).thenReturn(demoList);
		moudleHandler.findTreeByMoudleSignLike("1","1");
	}
	
	@Test
	public void getMoudleTree() throws Exception{
		//构造dao方法返回对象
		List<Moudle> demoList = new ArrayList<>();
		moudle.setMoudleSign("1-");
		moudle.setMoudleLevel("1");
		moudle.setEnable(1);
		moudle.setMoudleId("1");
		demoList.add(moudle);
		List<String> moudleIdList = new ArrayList<>();
		moudleIdList.add("1");
		when(moudleDao.getMoudleByUserId("1")).thenReturn(demoList);
		when(moudleDao.findByMoudleSignLikeAndMoudleIdInAndEnable("1-%", moudleIdList, 1)).thenReturn(demoList);
		moudleHandler.getMoudleTree("1-","1");
	}
	
	@Test
	public void findByEnable() throws Exception{
		List<Moudle> demoList = new ArrayList<>();
		moudle.setEnable(1);
		demoList.add(moudle);
		when(moudleDao.findByEnable(1)).thenReturn(demoList);
		moudleHandler.findByEnable();
	}
	@Test
	public void findMaxMoudleId() throws Exception{
	}

	@SuppressWarnings("static-access")
	@Test
	public void constrTreeList() {
		Moudle moudleFir = new Moudle();
		moudleFir.setMoudleSign("0-1-");
		
		Moudle moudleSec = new Moudle();
		moudleSec.setMoudleSign("0-");
		
		List<Moudle> list = new ArrayList<>();
		list.add(moudleFir);
		list.add(moudleSec);
		moudleHandler.constrTreeList(list, "0-");
	}
	@Test
	public void getListMoudleByUserId() {

    	String userId = "1";
    	String roleId = "1";
		String moudelId = "1";
		
    	List<UserRole> listUserRole = new ArrayList<>();
    	UserRole userRole = new UserRole();
    	userRole.setUserId(userId);
    	userRole.setRoleId(roleId);
    	listUserRole.add(userRole);

    	List<RoleMoudle> listRoleMoudle = new ArrayList<>();
    	RoleMoudle roleMoudle = new RoleMoudle();
    	roleMoudle.setRoleId(roleId);
    	roleMoudle.setMoudelId(moudelId);
    	listRoleMoudle.add(roleMoudle);
    	
		Moudle moudle = new Moudle();
		moudle.setMoudleId(moudelId);
    	
    	when(userRoleDao.getUserRoleByUserId(userId)).thenReturn(listUserRole);
    	
		when(roleMoudleDao.findByRoleIdAndEnable(roleId, 1)).thenReturn(listRoleMoudle);
		
		when(moudleDao.findByMoudleIdAndEnable(moudelId, 1)).thenReturn(moudle);
		
		moudleHandler.getListMoudleByUserId(userId);
	}
	
	@Test
	public void getFatureMoudle() {

		Moudle moudle = new Moudle();
		String moudelId = "1";
		String moudleParentId = "2";
		moudle.setMoudleId(moudelId);
		moudle.setMoudleParentId(moudleParentId);
		
		Moudle moudleFather = new Moudle();
		String moudelFatherId = "2";
		moudleFather.setMoudleId(moudelFatherId);
		
    	List<Moudle> listMoudle = new ArrayList<>();
    	
		when(moudleDao.findByMoudleIdAndEnable(moudelId, 1)).thenReturn(moudleFather);
		
		moudleHandler.getFatherMoudle(listMoudle, moudle);
	}
	
	@Test
	public void getForbidList() {

		String userId = "1";
		String moudleId = "1";
		String moudleUrl = "1";

    	Moudle moudle = new Moudle();
    	moudle.setMoudleId(moudleId);
    	moudle.setMoudleUrl(moudleUrl);

    	Moudle moudleSec = new Moudle();
    	moudleSec.setMoudleId("2");
    	moudleSec.setMoudleUrl("");

    	Moudle moudleThree = new Moudle();
    	moudleThree.setMoudleId("3");
    	
    	Moudle moudleFour = new Moudle();
    	moudleFour.setMoudleId("4");
    	Moudle moudleFir = null;
    	
    	List<Moudle> listMoudle = new ArrayList<>();
    	listMoudle.add(moudle);
    	listMoudle.add(moudleSec);
    	listMoudle.add(moudleThree);
    	listMoudle.add(moudleFour);
    	
    	when(moudleDao.getAccesses(userId)).thenReturn(listMoudle);
    	when(moudleDao.findByMoudleIdAndMoudleTypeAndEnable(moudleId, "3", 1)).thenReturn(moudle);
    	when(moudleDao.findByMoudleIdAndMoudleTypeAndEnable("2", "3", 1)).thenReturn(moudleSec);
    	when(moudleDao.findByMoudleIdAndMoudleTypeAndEnable("3", "3", 1)).thenReturn(moudleThree);
    	when(moudleDao.findByMoudleIdAndMoudleTypeAndEnable("4", "3", 1)).thenReturn(moudleFir);
    	
		moudleHandler.getForbidList(userId);
	}
	
	@Test
	public void getForbidMoudlesByUserId() {
		
    	String userId = "1";
    	String roleId = "1";
		String moudelId = "1";
		
    	List<UserRole> listUserRole = new ArrayList<>();
    	UserRole userRole = new UserRole();
    	userRole.setUserId(userId);
    	userRole.setRoleId(roleId);
    	listUserRole.add(userRole);

    	List<RoleMoudle> listRoleMoudle = new ArrayList<>();
    	RoleMoudle roleMoudle = new RoleMoudle();
    	roleMoudle.setRoleId(roleId);
    	roleMoudle.setMoudelId(moudelId);
    	listRoleMoudle.add(roleMoudle);
    	
    	when(userRoleDao.getUserRoleByUserId(userId)).thenReturn(listUserRole);
		when(roleMoudleDao.findByRoleIdAndEnable(roleId, 0)).thenReturn(listRoleMoudle);
		when(moudleDao.findByMoudleIdAndEnable(moudelId, 1)).thenReturn(moudle);
	}
}
