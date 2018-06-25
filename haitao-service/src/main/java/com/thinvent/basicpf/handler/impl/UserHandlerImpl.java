package com.thinvent.basicpf.handler.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.omg.CORBA.UserException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.thinvent.basicpf.dao.IUserDao;
import com.thinvent.basicpf.dao.IUserRoleDao;
import com.thinvent.basicpf.dao.jpa.UserDaoRepositoryImpl;
import com.thinvent.basicpf.handler.IUserHandler;
import com.thinvent.basicpf.model.User;
import com.thinvent.basicpf.model.UserRole;
import com.thinvent.basicpf.util.UserVerifyUtil;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.UserRoleVO;
import com.thinvent.library.vo.UserVO;

@Service
@Transactional(isolation=Isolation.READ_COMMITTED)
public class UserHandlerImpl implements IUserHandler {
	
	private static final String USERLIST = new String("userList").intern();
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IUserRoleDao userRoleDao;
	
	@Autowired
	private UserDaoRepositoryImpl userDaoRepository;
	
	@Override
	public List<UserVO> getUsersByDepId(String depId) throws ThinventBaseException {
		List<User> list = this.userDao.findByDepIdAndEnable(depId, 1);
	    return list.stream().map(User::convertToUserVO).collect(Collectors.toList());
	}
	
	@Override
	public UserVO findUser(String loginName) throws ThinventBaseException {
		User user = this.userDao.findUser(loginName);
		if(user == null) {
			return null;
		}
		UserVO userVO = new UserVO();
		BeanUtils.copyProperties(user, userVO);
		return userVO;
	}

	/**
	 * 获取部分信息
	 * @throws UserException 
	 */
	@Override
	public UserVO findUserByUserId(String userId) throws ThinventBaseException {
		User user = this.userDao.findUserByUserId(userId);
		UserVerifyUtil.verifyUser(user);
		UserVO userVO = new UserVO();
		BeanUtils.copyProperties(user, userVO);
		return userVO;
	}

	@Override
	public void save(UserVO userVO) {
		userVO.setCreateTime(new Date());
		User user = new User();
		//属性复制，将userVO的属性复制给user
		BeanUtils.copyProperties(userVO, user);
		this.userDao.save(user);
		//解析为LinkedHashMap，需转换
		List<Map<String, Object>> userRoleVOList = userVO.getUserRoleList();
		if(userRoleVOList != null) {
			for (Map entry : userRoleVOList) {
				UserRole userRole = new UserRole();
				UserRoleVO ur = new UserRoleVO();
				ur.setRoleId((String) entry.get("roleId"));
				ur.setCreateUser(userVO.getCreateUser());
				ur.setUserId(userVO.getUserId());
				BeanUtils.copyProperties(ur, userRole);
				this.userRoleDao.save(userRole);
			}
		}
	}
	
	@Override
	public void deleteUser(String userId) throws ThinventBaseException {
		User user = this.userDao.findUserByUserId(userId);
		UserVerifyUtil.verifyUser(user);
		user.setEnable(0);
		userDao.save(user);
		List<UserRole> userRoleList = this.userRoleDao.getUserRoleByUserId(userId);
		if (!userRoleList.isEmpty()) {
			for(UserRole ur : userRoleList) {
				ur.setEnable(0);
				userRoleDao.save(ur);
			}
		}
	}
	
	/**
	 * 仅修改 user 表的数据，修改其它表 使用 updateUserAllInfo 方法
	 */
	@Override
	public void updateUser(UserVO userVO) throws ThinventBaseException {
		userVO.setUpdateTime(new Date());
		User user = this.userDao.findUserByUserId(userVO.getUserId());
		UserVerifyUtil.verifyUser(user);
		BeanUtils.copyProperties(userVO, user);
		
		this.userDao.save(user);
	}
	
	@Override
	public void updateUserAllInfo(UserVO userVO) throws ThinventBaseException {
		userVO.setUpdateTime(new Date());
		User user = this.userDao.findUserByUserId(userVO.getUserId());
		BeanUtils.copyProperties(userVO, user, "id");
		
		List<UserRole> userRoleList = this.userRoleDao.getUserRoleByUserId(userVO.getUserId());
		List<UserRole> userRoleListTmp = new ArrayList<>();
		List<Map<String, Object>> userRoleVOList = userVO.getUserRoleList();
		List<String> curUserRoleIds = new ArrayList<>();
		
		if(userRoleVOList != null) {
			for (Map entry : userRoleVOList) {
				UserRole userRole = new UserRole();
				UserRoleVO ur = new UserRoleVO();
				ur.setRoleId((String) entry.get("roleId"));
				ur.setUpdateUser(userVO.getUpdateUser());
				ur.setUpdateTime(new Date());
				ur.setUserId(userVO.getUserId());
				BeanUtils.copyProperties(ur, userRole);
				userRoleListTmp.add(userRole);
				curUserRoleIds.add(userRole.getRoleId());
			}
		}
		
		List<String> existUserRoleIds = new ArrayList<>();
		for(UserRole ur : userRoleList) {
			existUserRoleIds.add(ur.getRoleId());
			if(!curUserRoleIds.contains(ur.getRoleId())) {
//				 如果当前列表中不包含数据库中查询的记录，则删除该用户对应的角色
				ur.setEnable(0);
				this.userRoleDao.save(ur);
				continue;
			}
		}
		
		for(UserRole ur : userRoleListTmp) {
			if(!existUserRoleIds.contains(ur.getRoleId())) {
//				 如果当前列表中包含了查询数组中没有的结果，则新增该角色
//				如果查询没有结果，则新增，否则更新现有
				UserRole usro = this.userRoleDao.getUserRoleByUserIdAndRoleId(ur.getUserId(), ur.getRoleId());
				if(usro != null) {
					usro.setEnable(1);
					this.userRoleDao.save(usro);
				} else {
					this.userRoleDao.save(ur);
				}
				
				continue;
			}
		}
		
		this.userDao.save(user);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Map listAllUserByCondition(Pageable pageable, List<String> deptIds, 
			List<String> posIds, String loginName, String userName) {
		Map ret = userDaoRepository.listAllUserByCondition(pageable, deptIds, posIds, loginName, userName);
		List<UserVO> uvoList = (List<UserVO>)ret.get(USERLIST);
		ret.put(USERLIST, uvoList);
		return ret;
	}

	/**
	 * 预留————全量导出用户等操作
	 */
	@Override
	public Map listAllUser() {
		List<User> userList = userDao.listAllUser();
		List<UserVO> vos = new ArrayList<>();
		for(User user : userList) {
			UserVO uvo = new UserVO();
			BeanUtils.copyProperties(user, uvo);
			vos.add(uvo);
		}
		
		Map map = new HashMap<>();
		map.put("count", vos.size());
		map.put(USERLIST, vos);
		return map;
	}

	@Override
	public void saveUserList(List<UserVO> uvoList) throws ThinventBaseException {
		List<User> userList = new ArrayList<>();
		for (UserVO uvo : uvoList) {
			User user = new User();
			BeanUtils.copyProperties(uvo, user);
			userList.add(user);
		}
		this.userDao.save(userList);
	}
	
}
