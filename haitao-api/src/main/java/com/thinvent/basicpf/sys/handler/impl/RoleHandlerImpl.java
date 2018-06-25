package com.thinvent.basicpf.sys.handler.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.thinvent.basicpf.sys.adapt.IRoleAdapt;
import com.thinvent.basicpf.sys.adapt.IUserAdapt;
import com.thinvent.basicpf.sys.handler.IRoleHander;
import com.thinvent.basicpf.sys.util.RoleVerifyUtil;
import com.thinvent.library.Constants;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.RoleVO;
import com.thinvent.library.vo.UserRoleVO;
import com.thinvent.library.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RoleHandlerImpl implements IRoleHander {
      
	@Autowired
	private IRoleAdapt roleadapt;
	@Autowired
	private IUserAdapt userAdapt;
	
	private static final String STATUS = new String("status").intern();
	private static final String COUNT = new String("count").intern();
	private static final String ROLELIST = new String("roleList").intern();
	@Override
	public RoleVO findRoleByRoleId(String roleId) throws ThinventBaseException {
		  RoleVO  roleVO = this.roleadapt.findRoleByRoleId(roleId);
	      RoleVerifyUtil.verifyRole(roleVO);
	      roleVO.setUserRoleList(getUserRoleList(roleVO));
		  return roleVO;
	}

	@Override
	public Map<String,Object> getRoleList(String roleName, String roleType, int pageIndex, int pageSize)
			throws ThinventBaseException {
		Map<String, Object> map = new HashMap<>();
		Map mapRet = this.roleadapt.getRoleList(roleName, roleType, pageIndex, pageSize);
		int count = (int) mapRet.get(COUNT);
		List<Map<String, Object>> mapList = (List<Map<String, Object>>) mapRet.get(ROLELIST);
		List<RoleVO> roleVOS = new ArrayList<>();
		for(Map m : mapList) {
			String jsonStr = m.toString();
			RoleVO rvo = JSON.parseObject(jsonStr, RoleVO.class);
			rvo.setUserRoleList(getUserRoleList(rvo));
			roleVOS.add(rvo);
		}
		map.put(STATUS, Constants.RES_SUCCESS);
		map.put("content", roleVOS);
		map.put(COUNT, count);

		return map;
	}

	private List getUserRoleList(RoleVO roleVO) throws ThinventBaseException {
		List<UserRoleVO> userRoleVOList = this.roleadapt.getUserRoleByRoleId(roleVO.getRoleId());
		if(userRoleVOList.isEmpty()) {
			return userRoleVOList;
		}
		List<UserRoleVO> retList = new ArrayList<>();
		for(UserRoleVO vo : userRoleVOList) {
			try {
				UserVO userVO = this.userAdapt.findUserByUserId(vo.getUserId());
				if (userVO != null) {
					vo.setUserName(userVO.getUserName());
					retList.add(vo);
				}
			} catch (Exception e) {
				log.error("role getUserRoleList can not find user: ", e);
			}
		}
		return retList;
	}

	@Override
	public void addRole(RoleVO roleVO) throws ThinventBaseException {
		RoleVerifyUtil.verifyRole(roleVO);
		this.roleadapt.add(roleVO);
	}

	@Override
	public void updateRole(RoleVO roleVO) throws ThinventBaseException {
		RoleVerifyUtil.verifyRole(roleVO);
		this.roleadapt.update(roleVO);
	}

	@Override
	public void deleteRole(String roleId) throws ThinventBaseException {
		this.roleadapt.delete(roleId);
	}

	@Override
	public List findAllRole() throws ThinventBaseException {
		return this.roleadapt.findAllRole();
	}

	@Override
	public List<RoleVO> findAllRoleByUserId(String userId) throws ThinventBaseException {
		return roleadapt.findAllRoleByUserId(userId);
	}

}
