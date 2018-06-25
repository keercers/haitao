package com.thinvent.basicpf.sys.handler.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.thinvent.basicpf.sys.adapt.IDepartmentAdapt;
import com.thinvent.basicpf.sys.adapt.IPositionAdapt;
import com.thinvent.basicpf.sys.adapt.IUserAdapt;
import com.thinvent.basicpf.sys.handler.IUserHandler;
import com.thinvent.basicpf.sys.util.UserVerifyUtil;
import com.thinvent.library.Constants;
import com.thinvent.library.config.CoreConfig;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.CryptographyUtil;
import com.thinvent.library.util.ExportAndImportCommonUtil;
import com.thinvent.library.util.JWTConfig;
import com.thinvent.library.util.SessionUtil;
import com.thinvent.library.vo.DepartmentVO;
import com.thinvent.library.vo.PositionVO;
import com.thinvent.library.vo.RoleVO;
import com.thinvent.library.vo.UserRoleVO;
import com.thinvent.library.vo.UserVO;

@Service
public class UserHandlerImpl implements IUserHandler{
	
	@Autowired
	private IUserAdapt userAdapt; 
	
	@Autowired
	private IDepartmentAdapt departmentAdapt;
	
	@Autowired
	private IPositionAdapt positionAdapt;
	
	private static final String STATUS = new String("status").intern();
	private static final String USER = new String("user").intern();
	private static final String USERLIST = new String("userList").intern();
	private static final String COUNT = new String("count").intern();
	private static final String COUNTUSER = new String("countUser").intern();
	/**
	 * 根据用户名称查找userId
	 * @author tsm
	 */
	@Override
	public Map<String, Object> findUserInfoByLoginName(String loginName) throws ThinventBaseException {
		UserVO user = userAdapt.findUser(loginName);
		Map<String, Object> map = new HashMap<>();
		map.put(STATUS, Constants.RES_SUCCESS);
		map.put(USER, user);
		return map;
	}
	
	@Override
	public Map<String, Object> checkUserByLoginName(String loginName, String password) throws ThinventBaseException{
		UserVO user = userAdapt.findUser(loginName);
		String msg = "登录成功！";
		UserVerifyUtil.verifyLogin(user, password);
		SessionUtil.setSessionValue(Constants.SESSION_USER, user);
		String token = JWTConfig.getTokenByUser(user);
		
		Map<String, Object> map = new HashMap();
		map.put("token", token);
		map.put(STATUS, Constants.RES_SUCCESS);
		map.put("msg", msg);
		map.put(USER, user);
		return map;
	}
	
	@Override
	public Map logOut() {
		Map map = new HashMap();
		SessionUtil.setSessionValue(Constants.SESSION_USER, null);
		map.put(STATUS, Constants.RES_SUCCESS);
		map.put("msg", "退出成功！");
		return map;
	}
	
	@Override
	public Map changePassword(String userId, String oldPassword, String newPassword) throws ThinventBaseException{
		Map map = new HashMap();
		UserVO user = userAdapt.findUserByUserId(userId);
		String msg = "修改密码成功";
		//输入条件校验
		UserVerifyUtil.verifyUpdatePass(user, oldPassword, newPassword);
		user.setPassword(CryptographyUtil.md5(newPassword));
		user.setUpdateUser(this.getUserIdFromSession());
		userAdapt.updateUser(user);
		//如果当前系统登录的用户==被修改密码的用户，重新保存用户信息
		UserVO loginUser = (UserVO)SessionUtil.getSessionValue(Constants.SESSION_USER);
		if(loginUser!=null&&loginUser.getUserId().equals(user.getUserId()))
			SessionUtil.setSessionValue(Constants.SESSION_USER, user);
		map.put(USER, user);
		map.put(STATUS, Constants.RES_SUCCESS);
		map.put("msg", msg);
		return map;
	}
	
	@Override
	public String getUserIdFromSession(){
		UserVO user = (UserVO)SessionUtil.getSessionValue(Constants.SESSION_USER);
		return user == null ? "" : user.getUserId();
	}

	@Override
	public Map listUserByDepartmentId(String departmentId, int pageSize, int pageIndex) throws ThinventBaseException {
		List<DepartmentVO> departments = departmentAdapt.getAllDepartmentsById(departmentId);
		List<UserVO> allUser = new ArrayList<>();
		List<String> deptIds = new ArrayList<>();
		
		for(DepartmentVO vo : departments) {
			deptIds.add(vo.getDepId());
		}
		
		List<UserVO> users = userAdapt.listUserByDeptIds(deptIds, pageSize, pageIndex);
		for(UserVO uvo : users) {
			uvo.setDeptName(getUserDeptName(departments, uvo.getDepId()));
			PositionVO position = positionAdapt.getByPosId(uvo.getPosId());
			uvo.setPosName(position.getPosName());
			uvo.setUserRoleList(getRoleListName(uvo));
			allUser.add(uvo);
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put(STATUS, Constants.RES_SUCCESS);
		map.put(USER, allUser);
		return map;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Map listUserByCondition(String deptId, String posName, String loginName, 
			String userName, int pageSize, int pageIndex, int isAll) throws ThinventBaseException {
		Map<String, Object> map = new HashMap<>();
		List<String> posIds = new ArrayList<>();
		List<String> deptIds = new ArrayList<>();
		
		if(posName != null && !"".equals(posName)) {
			List<PositionVO> listPosition = positionAdapt.listAllPosition();
			
			for (PositionVO pvo : listPosition) {
				if (pvo.getPosName().contains(posName)) {
					posIds.add(pvo.getPosId());
				}
			}
			
			if(posIds.isEmpty()) {
				map.put(STATUS, Constants.RES_SUCCESS);
				map.put(USER, posIds);
				map.put(COUNT, 0);
				return map;
			}
		}
		
		List<DepartmentVO> depts = null;
		if(deptId != null && !"".equals(deptId)) {
			depts = departmentAdapt.getAllDepartmentsById(deptId);
			for(DepartmentVO dvo : depts) {
				deptIds.add(dvo.getDepId());
			}
		}
		
		Map mapRet = userAdapt.listUserByConditions(deptIds, posIds, loginName, userName, pageSize, pageIndex, isAll);
		int count = (int) mapRet.get(COUNT);
		List<Map<String, Object>> mapList = (List<Map<String, Object>>) mapRet.get(USERLIST);
		List<UserVO> userVOs = new ArrayList<>();
		for(Map m : mapList) {
			String jsonStr = m.toString();
			UserVO uvo = JSON.parseObject(jsonStr, UserVO.class);
			uvo.setDeptName(getUserDeptName(depts, uvo.getDepId()));
			uvo.setPosName(getPosName(uvo.getPosId()));
			uvo.setUserRoleList(getRoleListName(uvo));
			userVOs.add(uvo);
		}
		
		map.put(STATUS, Constants.RES_SUCCESS);
		map.put(USER, userVOs);
		map.put(COUNT, count);
		
		return map;
	}
	
	private String getPosName(String posId) throws ThinventBaseException {
		PositionVO position = positionAdapt.getByPosId(posId);
		String ret = "";
		if(position != null) {
			switch (position.getPosType()) {
				case "0":
					ret = position.getPosName() + "(全局)";
					break;
				case "1":
					ret = position.getPosName() + "(部门)";
					break;
				case "2":
					ret = position.getPosName() + "(个人)";
					break;
				case "9":
					ret = position.getPosName() + "(其他)";
					break;
				default:
					break;
			}
		}
		return ret;	
	}
	
	private String getUserDeptName(List<DepartmentVO> dvoList, String id) throws ThinventBaseException {
		String deptName = "";
		
		if(dvoList != null) {
			for(DepartmentVO dvo : dvoList) {
				if(dvo.getDepId().equals(id)) {
					deptName = dvo.getDepName();
				}
			}
		}
		
		if("".equals(deptName)) {
			DepartmentVO deptVO = departmentAdapt.getDepartmentById(id);
			if(null != deptVO) {
				deptName = deptVO.getDepName();
			}
		}
		return deptName;
	}
	
	@SuppressWarnings("rawtypes")
	private List getRoleListName(UserVO userVO) throws ThinventBaseException {
		List<UserRoleVO> userRoleVOList = this.userAdapt.getUserRoleByUserId(userVO.getUserId());
		if(userRoleVOList.isEmpty()) {
			return userRoleVOList;
		}
		
		List<UserRoleVO> retList = new ArrayList<>();
		for(UserRoleVO vo : userRoleVOList) {
			RoleVO roleVO = this.userAdapt.getRoleByRoleId(vo.getRoleId());
			if(roleVO != null) {
				vo.setRoleName(roleVO.getRoleName());
			}
			retList.add(vo);
		}
		return retList;
	}
	
	@Override
	public void deleteUser(String userId) throws ThinventBaseException {
		userAdapt.deleteUser(userId);
	}
	
	@Override
	public void resetPassword(String userId, String newPwd) throws ThinventBaseException {
		UserVO userVO = userAdapt.findUserByUserId(userId);
		UserVerifyUtil.verifyQueryResult(userVO);
		UserVerifyUtil.verifyUpdatePass(userVO.getPassword(), CryptographyUtil.md5(newPwd));
		userVO.setPassword(CryptographyUtil.md5(newPwd));
		userAdapt.updateUser(userVO);
	}

	@Override
	public void updateUser(UserVO userVO) throws ThinventBaseException {
		UserVO userVOF = userAdapt.findUserByLoginName(userVO.getLoginName());
		UserVO userVOFind = userAdapt.findUserByUserId(userVO.getUserId());
		//校验是否存在该用户名
		UserVerifyUtil.verifyQueryResult(userVOFind);
		if (userVOF != null) {
			UserVerifyUtil.verifyUserEquals(userVOF, userVO);
		}
		//校验是否密码被非法修改
		UserVerifyUtil.verifyUpdateResult(userVOFind.getPassword(), userVO.getPassword());
		
		userVO.setUpdateUser(this.getUserIdFromSession());
		userAdapt.updateUserAllInfo(userVO);
	}

	@Override
	public void saveUser(UserVO userVO) throws ThinventBaseException {
		// 必填项校验
		if("".equals(userVO.getLoginName())) {
			return;
		}
		// 判断是否已存在该用户,loginName
		UserVO userVON = userAdapt.findUserByLoginName(userVO.getLoginName());
		UserVerifyUtil.verifySaveResult(userVON);
		
		userVO.setCreateUser(this.getUserIdFromSession());
		// 导入的用户设置默认密码
		String pwd = userVO.getPassword() == null ? CoreConfig.getSystemCoreConfiguration().defaultPwd : userVO.getPassword();
		userVO.setPassword(CryptographyUtil.md5(pwd));
		userAdapt.saveUser(userVO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> importUser(List<UserVO> uvoList) throws ThinventBaseException {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> mapRet = new HashMap<>();
		List<Integer> emptyStr = new ArrayList<>();
		List<Integer> loginName = new ArrayList<>();
		List<Integer> deptName = new ArrayList<>();
		List<Integer> posName = new ArrayList<>();
		
		if(uvoList == null) {
			map.put(COUNTUSER, 0);
			return map;
		}
		
		int countUser = 0;
		for(UserVO uvo : uvoList) {
			PositionVO pvo = new PositionVO();
			
			UserVO userVON = userAdapt.findUserByLoginName(uvo.getLoginName());
			List<DepartmentVO> dvoList = departmentAdapt.getDepartmentByName(uvo.getDeptName());
			
			if (!"".equals(uvo.getPosName())) {
				String[] strings = uvo.getPosName().split("\\(");
				String posType = ExportAndImportCommonUtil.posType(strings[1].split("\\)")[0] + "岗位");
				pvo = positionAdapt.getPositionByPosNameAndPosType(strings[0], posType);
			}
			
			mapRet = ExportAndImportCommonUtil.userImport(uvo, userVON, countUser, dvoList, pvo, emptyStr, loginName, deptName, posName, map);
			
			uvo.setCreateUser(this.getUserIdFromSession());
			// 导入的用户设置默认密码
			String pwd = CoreConfig.getSystemCoreConfiguration().defaultPwd == null 
					? "123456" : CoreConfig.getSystemCoreConfiguration().defaultPwd;
			uvo.setPassword(CryptographyUtil.md5(pwd));
			countUser++;
		}

		emptyStr = (List<Integer>) mapRet.get("emptyStr");
		loginName = (List<Integer>) mapRet.get("loginName");
		deptName = (List<Integer>) mapRet.get("deptName");
		posName = (List<Integer>) mapRet.get("posName");
		
		loginName = getLoginName(uvoList, loginName);
		
		if (emptyStr.isEmpty() && loginName.isEmpty() && posName.isEmpty() && deptName.isEmpty()) {
			userAdapt.saveUser(uvoList);
			mapRet.put(COUNTUSER, countUser);
		}
		return mapRet;
	}
	
	public List<Integer> getLoginName(List<UserVO> uvoList, List<Integer> loginName) throws ThinventBaseException {
		for (int i = 0; i < uvoList.size(); i++) {
			UserVO vo1 = uvoList.get(i);
			for (int j = i+1; j < uvoList.size(); j++) {
				UserVO vo2 = uvoList.get(j);
				if (vo1.getLoginName().equals(vo2.getLoginName())) {
					loginName.add(j+2);
				}
			}
		}
		return loginName;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Map getUserInfo(String userId) throws ThinventBaseException {
		UserVO userVO = userAdapt.findUserByUserId(userId);
		UserVerifyUtil.verifyQueryResult(userVO);
		PositionVO position = positionAdapt.getByPosId(userVO.getPosId());
		if(position == null) {
			userVO.setPosId("");
			userVO.setPosName("");
		}
		
		DepartmentVO departmentVO = departmentAdapt.getDepartmentById(userVO.getDepId());
		if (departmentVO == null) {
			userVO.setDepId("");
			userVO.setDeptName("");
		} else {
			userVO.setDeptName(departmentVO.getDepName());
		}
		
		userVO.setUserRoleList(getRoleListName(userVO));
		Map<String, Object> map = new HashMap<>();
		map.put(STATUS, Constants.RES_SUCCESS);
		map.put(USER, userVO);
		return map;
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map listAllUser() throws ThinventBaseException {
		Map mapRet = userAdapt.listAllUser();
		List<Map<String, Object>> mapList = (List<Map<String, Object>>) mapRet.get(USERLIST);
		List<UserVO> userVOs = new ArrayList<>();
		for(Map m : mapList) {
			String jsonStr = m.toString();
			UserVO uvo = JSON.parseObject(jsonStr, UserVO.class);
			uvo.setDeptName(getUserDeptName(null, uvo.getDepId()));
			uvo.setPosName(getPosName(uvo.getPosId()));
			uvo.setUserRoleList(getRoleListName(uvo));
			userVOs.add(uvo);
		}
		Map<String, Object> map = new HashMap<>();
		map.put(STATUS, Constants.RES_SUCCESS);
		map.put(USER, userVOs);
		map.put(COUNT, userVOs.size());
		return map;
	}
}
