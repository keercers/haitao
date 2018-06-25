package com.thinvent.basicpf.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.thinvent.basicpf.dao.IUserRoleDao;
import com.thinvent.basicpf.handler.IUserRoleHandler;
import com.thinvent.basicpf.model.UserRole;
import com.thinvent.basicpf.util.UserVerifyUtil;
import com.thinvent.library.Constants;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.StringUtil;
import com.thinvent.library.vo.UserRoleVO;

@Service
@Transactional(isolation=Isolation.READ_COMMITTED)
public class UserRoleHandlerImpl implements IUserRoleHandler {

	@Autowired
	private IUserRoleDao userRoleDao;
	
	@Override
	public void save(UserRoleVO userRoleVO) {
		UserRole userRole = new UserRole();
		BeanUtils.copyProperties(userRoleVO, userRole);
		userRoleDao.save(userRole);
	}

	@Override
	public void update(UserRoleVO userRoleVO) throws ThinventBaseException {
		UserRole userRole = userRoleDao.getUserRoleById(userRoleVO.getId());
		UserVerifyUtil.verifyUserRole(userRole);
		BeanUtils.copyProperties(userRoleVO, userRole);
		userRoleDao.save(userRole);
	}

	@Override
	public List<UserRoleVO> getUserRoleListByUserId(String userId) throws ThinventBaseException {
		List<UserRole> userRoleList = userRoleDao.getUserRoleByUserId(userId);
		List<UserRoleVO> userRoleVOList = new ArrayList<>();
		for(UserRole userRole : userRoleList) {
			UserRoleVO userRoleVO = new UserRoleVO();
			BeanUtils.copyProperties(userRole, userRoleVO);
			userRoleVOList.add(userRoleVO);
		}
		return userRoleVOList;
	}
	@Override
	public List<UserRoleVO> getUserRoleListByRoleId(String roleId) throws ThinventBaseException {
		List<UserRole> userRoleList = userRoleDao.getUserRoleByRoleId(roleId);
		List<UserRoleVO> userRoleVOList = new ArrayList<>();
		for(UserRole userRole : userRoleList) {
			UserRoleVO userRoleVO = new UserRoleVO();
			BeanUtils.copyProperties(userRole, userRoleVO);
			userRoleVOList.add(userRoleVO);
		}
		return userRoleVOList;
	}

	@Override
	public List<String> queryUserIdsByRoleIds(String roleIds) {
		List<String> roleIdList = StringUtil.stringSZToList(roleIds.split(Constants.UUID_EXAMPLE));
		return userRoleDao.getUserIdsByRoleIds(roleIdList);
	}
}
