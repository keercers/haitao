package com.thinvent.basicpf.sys;

import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSONObject;
import com.thinvent.basicpf.sys.adapt.IDepartmentAdapt;
import com.thinvent.basicpf.sys.adapt.IPositionAdapt;
import com.thinvent.basicpf.sys.adapt.IUserAdapt;
import com.thinvent.basicpf.sys.handler.impl.UserHandlerImpl;
import com.thinvent.library.Constants;
import com.thinvent.library.config.CoreConfig;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.CryptographyUtil;
import com.thinvent.library.util.SessionUtil;
import com.thinvent.library.vo.DepartmentVO;
import com.thinvent.library.vo.PositionVO;
import com.thinvent.library.vo.RoleVO;
import com.thinvent.library.vo.UserRoleVO;
import com.thinvent.library.vo.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class UserHandlerImplTest {
	@Mock
	private IUserAdapt userAdapt;
	
	@InjectMocks
	private UserHandlerImpl userHandler;
	
	@Mock
	private IDepartmentAdapt departmentAdapt;
	
	@Mock
	private IPositionAdapt positionAdapt;
	
	private UserVO userVO = new UserVO();
	
	private DepartmentVO dvo = new DepartmentVO();
	
	private RoleVO rvo = new RoleVO();
	
	private UserRoleVO urvo = new UserRoleVO();
	
	private PositionVO pvo = new PositionVO();
	
	public UserHandlerImplTest() {
		rvo.setRoleName("test role");
		dvo.setDepName("test department");
		pvo.setPosName("test position");
		urvo.setRoleId(rvo.getRoleId());
		urvo.setUserId(userVO.getUserId());
	}
	
	@Test
	public void checkUserByLoginNameTest() throws ThinventBaseException {
		String loginName = "admin";
		userVO.setLoginName("amdin");
		userVO.setPassword(CryptographyUtil.md5("111111"));
		
		when(userAdapt.findUser(loginName)).thenReturn(userVO);
		
		Map map = userHandler.checkUserByLoginName(loginName, "111111");
		UserVO user = (UserVO) map.get("user");
		String result = (String) map.get("status");
		Assert.assertTrue("检查用户登录名", result != null);
		Assert.assertTrue("检查用户登录名", "200".equals(result));
		Assert.assertTrue("检查用户登录名", userVO.getLoginName().equals(user.getLoginName()) 
				&& userVO.getPassword().equals(user.getPassword()));
	}
	
	@Test
	public void logOutTest() {
		Map map = userHandler.logOut();
		String result = (String) map.get("status");
		Assert.assertTrue("退出登录", result != null);
		Assert.assertTrue("退出登录", "200".equals(result));
	}
	
	@Test
	public void changePasswordTest() throws ThinventBaseException, IllegalAccessException, InvocationTargetException {
		UserVO user = new UserVO();
		String oldPassword = "111111";
		String newPassword = "123456";
		String ret = "200";
		userVO.setPassword(CryptographyUtil.md5("111111"));
		BeanUtils.copyProperties(userVO, user);
		when(userAdapt.findUserByUserId(userVO.getUserId())).thenReturn(userVO);
		Map map = userHandler.changePassword(userVO.getUserId(), oldPassword, newPassword);
		UserVO userRet = (UserVO) map.get("user");
		String result = (String) map.get("status");
		Assert.assertTrue("修改密码", result != null);
		Assert.assertTrue("修改密码", "200".equals(result));
		Assert.assertTrue("修改密码", userRet.getPassword().equals(CryptographyUtil.md5("123456")));
		Assert.assertTrue("修改密码", !user.getPassword().equals(userRet.getPassword()));
		
		SessionUtil.setSessionValue(Constants.SESSION_USER, userVO);
		map = userHandler.changePassword(userVO.getUserId(), newPassword, oldPassword);
		userRet = (UserVO) map.get("user");
		result = (String) map.get("status");
		Assert.assertTrue("修改密码", result != null);
		Assert.assertTrue("修改密码", "200".equals(result));
		Assert.assertTrue("修改密码", userRet.getPassword().equals(CryptographyUtil.md5("111111")));
		Assert.assertTrue("修改密码", user.getPassword().equals(userRet.getPassword()));
		
		
	}
	
	@Test
	public void getSessionTest() {
		SessionUtil.setSessionValue(Constants.SESSION_USER, userVO);
		String result = userHandler.getUserIdFromSession();
		Assert.assertTrue("获取session", result != null);
		Assert.assertTrue("获取session", userVO.getUserId().equals(result));
	}
	
	@Test
	public void deleteUserTest() throws ThinventBaseException {
		userVO.setEnable(1);
		userHandler.deleteUser(userVO.getUserId());
	}
	
	@Test
	public void updateUserTest() throws ThinventBaseException {
		String ret = "200";
		UserVO user = new UserVO();
		UserVO uvo = new UserVO();
		userVO.setPassword(CryptographyUtil.md5("111111"));
		userVO.setUserName("test");
		userVO.setLoginName("test");
		BeanUtils.copyProperties(userVO, user);
		userVO.setUserName("test_result");
		when(userAdapt.findUserByUserId(userVO.getUserId())).thenReturn(userVO);
		when(userAdapt.findUserByLoginName(userVO.getLoginName())).thenReturn(uvo);
		userHandler.updateUser(userVO);
	}
	
	@Test
	public void saveUserTest() throws ThinventBaseException {
		String ret = "200";
		userVO.setPassword(CryptographyUtil.md5("111111"));
		userVO.setLoginName("test");
		when(userAdapt.findUserByLoginName(userVO.getLoginName())).thenReturn(null);
		userHandler.saveUser(userVO);
		
		userVO.setLoginName("");
		when(userAdapt.findUserByLoginName(userVO.getLoginName())).thenReturn(null);
		userHandler.saveUser(userVO);
	}
	
	@Test
	public void getUserInfoTest() throws ThinventBaseException {
		List<UserRoleVO> urvoList = new ArrayList<>();
		urvoList.add(urvo);
		userVO.setRoleId(rvo.getRoleId());
		userVO.setDepId(dvo.getDepId());
		userVO.setPosId(pvo.getPosId());
		when(userAdapt.findUserByUserId(userVO.getUserId())).thenReturn(userVO);
		when(positionAdapt.getByPosId(userVO.getPosId())).thenReturn(pvo);
		when(departmentAdapt.getDepartmentById(userVO.getDepId())).thenReturn(dvo);
		when(userAdapt.getUserRoleByUserId(userVO.getUserId())).thenReturn(urvoList);
		when(userAdapt.getRoleByRoleId(rvo.getRoleId())).thenReturn(rvo);
		
		Map map = userHandler.getUserInfo(userVO.getUserId());
		String result = (String) map.get("status");
		Assert.assertTrue("获取人员详细信息", result != null);
		Assert.assertTrue("获取人员详细信息", "200".equals(result));
		
		when(userAdapt.findUserByUserId(userVO.getUserId())).thenReturn(userVO);
		when(positionAdapt.getByPosId(userVO.getPosId())).thenReturn(null);
		when(departmentAdapt.getDepartmentById(userVO.getDepId())).thenReturn(null);
		when(userAdapt.getUserRoleByUserId(userVO.getUserId())).thenReturn(urvoList);
		when(userAdapt.getRoleByRoleId(rvo.getRoleId())).thenReturn(rvo);
		
		map = userHandler.getUserInfo(userVO.getUserId());
		result = (String) map.get("status");
		Assert.assertTrue("获取人员详细信息", result != null);
		Assert.assertTrue("获取人员详细信息", "200".equals(result));
	}
	
	@Test
	public void listUserByDepartmentIdTest() throws ThinventBaseException {
		int pageSize = 10;
		int pageIndex = 1;
		userVO.setDepId(dvo.getDepId());
		String departmentId = userVO.getDepId();
		List<DepartmentVO> dvoList = new ArrayList<>();
		List<UserVO> uvo = new ArrayList<>();
		List<String> deptIds = new ArrayList<>();
		List<UserRoleVO> urvoList = new ArrayList<>();
		urvoList.add(urvo);
		deptIds.add(dvo.getDepId());
		dvoList.add(dvo);
		uvo.add(userVO);
		when(departmentAdapt.getAllDepartmentsById(departmentId)).thenReturn(dvoList);
		when(userAdapt.listUserByDeptIds(deptIds, pageSize, pageIndex)).thenReturn(uvo);
		when(positionAdapt.getByPosId(userVO.getPosId())).thenReturn(pvo);
		when(userAdapt.getUserRoleByUserId(userVO.getUserId())).thenReturn(urvoList);
		when(userAdapt.getRoleByRoleId(rvo.getRoleId())).thenReturn(rvo);
		
		Map map = userHandler.listUserByDepartmentId(userVO.getDepId(), pageSize, pageIndex);
		List<UserVO> uvos = (List<UserVO>) map.get("user");
		String result = (String) map.get("status");
		Assert.assertTrue("根据部门查找用户", result != null);
		Assert.assertTrue("根据部门查找用户", "200".equals(result));
		Assert.assertTrue("根据部门查找用户", uvos.get(0).getUserId().equals(userVO.getUserId()));
	}
	
	@Test
	public void listUserByDepartmentIdBranchTest() throws ThinventBaseException {
		int pageSize = 10;
		int pageIndex = 1;
		userVO.setDepId(dvo.getDepId());
		String departmentId = userVO.getDepId();
		List<DepartmentVO> dvoList = new ArrayList<>();
		List<UserVO> uvo = new ArrayList<>();
		List<String> deptIds = new ArrayList<>();
		List<UserRoleVO> urvoList = new ArrayList<>();
		deptIds.add(dvo.getDepId());
		dvoList.add(dvo);
		uvo.add(userVO);
		when(departmentAdapt.getAllDepartmentsById(departmentId)).thenReturn(dvoList);
		when(userAdapt.listUserByDeptIds(deptIds, pageSize, pageIndex)).thenReturn(uvo);
		when(positionAdapt.getByPosId(userVO.getPosId())).thenReturn(pvo);
		when(userAdapt.getUserRoleByUserId(userVO.getUserId())).thenReturn(urvoList);
		when(userAdapt.getRoleByRoleId(rvo.getRoleId())).thenReturn(rvo);
		
		Map map = userHandler.listUserByDepartmentId(userVO.getDepId(), pageSize, pageIndex);
		List<UserVO> uvos = (List<UserVO>) map.get("user");
		String result = (String) map.get("status");
		Assert.assertTrue("根据部门查找用户——分支", result != null);
		Assert.assertTrue("根据部门查找用户——分支", "200".equals(result));
		Assert.assertTrue("根据部门查找用户——分支", uvos.get(0).getUserId().equals(userVO.getUserId()));
	}
	
	@Test
	public void listUserByConditionTest() throws ThinventBaseException {
		PositionVO pvo = new PositionVO();
		pvo.setPosId("123");
		pvo.setPosType("1");
		pvo.setPosName("123");
		String loginName = "";
		String userName = "";
		int pageIndex = 1;
		int pageSize = 10;
		List<String> deptIds = new ArrayList<>();
		List<String> posIds = new ArrayList<>();
		List<DepartmentVO> dvoList = new ArrayList<>();
		deptIds.add(dvo.getDepId());
		posIds.add(pvo.getPosId());
		dvoList.add(dvo);
		
		JSONObject json = new JSONObject();
		json.put("userId", userVO.getUserId());
		json.put("posId", "123");
		List<UserRoleVO> urVOList = new ArrayList();
		urVOList.add(urvo);
		List<PositionVO> pvoList = new ArrayList();
		pvoList.add(pvo);
		
		List<Map<String, Object>> listMap = new ArrayList();
		Map<String,  Object> map = new HashMap();
		listMap.add(json);
		map.put("userList", listMap);
		map.put("count", 1);
		
		when(userAdapt.getRoleByRoleId(rvo.getRoleId())).thenReturn(rvo);
		when(userAdapt.getUserRoleByUserId(userVO.getUserId())).thenReturn(urVOList);
		when(departmentAdapt.getDepartmentById(userVO.getDepId())).thenReturn(dvo);
		when(positionAdapt.getByPosId(userVO.getPosId())).thenReturn(pvo);
		when(userAdapt.findUserByUserId(userVO.getUserId())).thenReturn(userVO);
		
		// branch posIds.isEmpty()
		Map ret = null; 
		ret = userHandler.listUserByCondition(userVO.getDepId(), pvo.getPosName(), loginName, userName, pageSize, pageIndex, 0);

		pvo.setPosName("test position");
		userVO.setDepId(dvo.getDepId());
		when(positionAdapt.listAllPosition()).thenReturn(pvoList);
		when(departmentAdapt.getAllDepartmentsById(dvo.getDepId())).thenReturn(dvoList);
		when(userAdapt.listUserByConditions(deptIds, posIds, loginName, userName, pageSize, pageIndex, 0)).thenReturn(map);
		when(positionAdapt.getByPosId(userVO.getPosId())).thenReturn(pvo);
		ret = userHandler.listUserByCondition(userVO.getDepId(), pvo.getPosName(), loginName, userName, pageSize, pageIndex, 0);
		
		Object count = ret.get("count");
		String result = (String) ret.get("status");
		Assert.assertTrue("查找人员", count != null && (int)count == 1);
		Assert.assertTrue("查找人员", "200".equals(result));
	}
	
	@Test
	public void importUserTest() throws ThinventBaseException {
		List<UserVO> info = new ArrayList<>();
		List<UserVO> uvoList = new ArrayList<>();
		List<DepartmentVO> dvoList = new ArrayList<>();
		DepartmentVO departmentVO = new DepartmentVO();
		
		userVO.setPassword("123456");
		userVO.setPosName("部门岗位(个人)");
		uvoList.add(userVO);
		dvoList.add(departmentVO);
		info.add(userVO);
		
//		CoreConfig config = new CoreConfig();
//		config.defaultPwd = "123456";
		
		when(userAdapt.findUserByLoginName(userVO.getLoginName())).thenReturn(userVO);
		when(departmentAdapt.getDepartmentByName(userVO.getDeptName())).thenReturn(dvoList);
		when(positionAdapt.getPositionByPosNameAndPosType("123", "3")).thenReturn(pvo);
		
		Map<String, Object> map = userHandler.importUser(info);
		Assert.assertEquals(0, map.get("countUser"));
		
		when(userAdapt.findUserByLoginName(userVO.getLoginName())).thenReturn(userVO);
		when(departmentAdapt.getDepartmentByName(userVO.getDeptName())).thenReturn(dvoList);
		when(positionAdapt.getPositionByPosNameAndPosType("123", "3")).thenReturn(pvo);
		
		map = userHandler.importUser(null);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void listAllUserTest() throws ThinventBaseException {
		List<UserRoleVO> urVOList = new ArrayList();
		PositionVO pvo = new PositionVO();
		pvo.setPosId("123");
		pvo.setPosType("1");
		urVOList.add(urvo);
		userVO.setDeptName("test dept");
		userVO.setPosName("test position");
		userVO.setPosId("123");
		JSONObject json = new JSONObject();
		json.put("userId", userVO.getUserId());
		json.put("posId", "123");
		List<Map<String, Object>> listMap = new ArrayList();
		Map<String,  Object> map = new HashMap();
		listMap.add(json);
		map.put("userList", listMap);
		map.put("count", 1);
		when(userAdapt.getRoleByRoleId(rvo.getRoleId())).thenReturn(rvo);
		when(userAdapt.getUserRoleByUserId(userVO.getUserId())).thenReturn(urVOList);
		when(departmentAdapt.getDepartmentById(userVO.getDepId())).thenReturn(dvo);
		when(positionAdapt.getByPosId(userVO.getPosId())).thenReturn(pvo);
		when(userAdapt.findUserByUserId(userVO.getUserId())).thenReturn(userVO);
		when(userAdapt.listAllUser()).thenReturn(map);
		userHandler.listAllUser();
		
		pvo.setPosId("123");
		pvo.setPosType("2");
		when(userAdapt.getRoleByRoleId(rvo.getRoleId())).thenReturn(rvo);
		when(userAdapt.getUserRoleByUserId(userVO.getUserId())).thenReturn(urVOList);
		when(departmentAdapt.getDepartmentById(userVO.getDepId())).thenReturn(dvo);
		when(positionAdapt.getByPosId(userVO.getPosId())).thenReturn(pvo);
		when(userAdapt.findUserByUserId(userVO.getUserId())).thenReturn(userVO);
		when(userAdapt.listAllUser()).thenReturn(map);
		userHandler.listAllUser();
		
		pvo.setPosId("123");
		pvo.setPosType("0");
		when(userAdapt.getRoleByRoleId(rvo.getRoleId())).thenReturn(rvo);
		when(userAdapt.getUserRoleByUserId(userVO.getUserId())).thenReturn(urVOList);
		when(departmentAdapt.getDepartmentById(userVO.getDepId())).thenReturn(dvo);
		when(positionAdapt.getByPosId(userVO.getPosId())).thenReturn(pvo);
		when(userAdapt.findUserByUserId(userVO.getUserId())).thenReturn(userVO);
		when(userAdapt.listAllUser()).thenReturn(map);
		userHandler.listAllUser();
	}
	
	@Test
	public void resetPasswordTest() throws ThinventBaseException {
		userVO.setPassword(CryptographyUtil.md5("111111"));
		when(userAdapt.findUserByUserId(userVO.getUserId())).thenReturn(userVO);
		userHandler.resetPassword(userVO.getUserId(), "123456");
	}
	
}
