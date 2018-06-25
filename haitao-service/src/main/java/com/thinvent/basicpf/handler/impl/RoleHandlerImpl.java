package com.thinvent.basicpf.handler.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.thinvent.basicpf.dao.jpa.RoleDaoRepositoryImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.thinvent.basicpf.dao.IMoudleDao;
import com.thinvent.basicpf.dao.IRoleDao;
import com.thinvent.basicpf.dao.IRoleMoudleDao;
import com.thinvent.basicpf.dao.IUserRoleDao;
import com.thinvent.basicpf.handler.IRoleHandler;
import com.thinvent.basicpf.model.Moudle;
import com.thinvent.basicpf.model.Role;
import com.thinvent.basicpf.model.RoleMoudle;
import com.thinvent.basicpf.model.UserRole;
import com.thinvent.basicpf.util.RoleVerifyUtil;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.UUIDUtil;
import com.thinvent.library.vo.RoleVO;
import com.thinvent.library.vo.UserRoleVO;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class RoleHandlerImpl implements IRoleHandler {

	@Autowired
	private IRoleDao roleDao;

	@Autowired
	private IUserRoleDao userRoleDao;
	
	@Autowired
    private IMoudleDao moudleDao;
	
	@Autowired
	private IRoleMoudleDao roleMoudleDao;
	@Autowired
	private RoleDaoRepositoryImpl roleDaoRepository;

	private static final String USERID = "userId";
	private static final String ROLELIST = new String("roleList").intern();
	
	@Override
	public Role findRoleByRoleId(String roleId) {
		return this.roleDao.getByRoleId(roleId);
	}

	@Override
	public void addRole(RoleVO roleVO) throws ThinventBaseException  {
		Date date = new Date();
		roleVO.setCreateTime(date);
		roleVO.setUpdateTime(date);
		Role role = new Role();
		BeanUtils.copyProperties(roleVO, role);
		Role existRole = roleDao.findOneByRoleNameAndEnable(role.getRoleName(),1);
		RoleVerifyUtil.verifyAddExistRole(existRole);
		this.roleDao.save(role);
		List<Map<String, Object>> userRoleVOList = roleVO.getUserRoleList();
		if (!userRoleVOList.isEmpty()) {
			for (Map entry : userRoleVOList) {
				UserRole userRole = new UserRole();
				UserRoleVO urVo = new UserRoleVO();
				urVo.setRoleId(role.getRoleId());
				urVo.setUserId((String) entry.get(USERID));
				urVo.setCreateUser(roleVO.getCreateUser());
				urVo.setUpdateUser(roleVO.getUpdateUser());
				urVo.setCreateTime(new Date());
				urVo.setUpdateTime(new Date());
				BeanUtils.copyProperties(urVo, userRole);
				this.userRoleDao.save(userRole);
			}
		}
		
		// 初始化角色模块
		List<Moudle> moudelList = moudleDao.findByEnable(1);
		int enalbe = 0;
		if("INIT".equals(role.getRoleName())) {
			enalbe = 1;
		}
		for (Moudle moudle : moudelList) {
			RoleMoudle roleMoudle = new RoleMoudle();
			roleMoudle.setMoudelId(moudle.getMoudleId());
			roleMoudle.setRoleId(roleVO.getRoleId());
			roleMoudle.setEnable(enalbe);
			roleMoudle.setCreateTime(date);
			roleMoudle.setUpdateTime(date);
			roleMoudle.setCreateUser(roleVO.getCreateUser());
			roleMoudle.setUpdateUser(roleVO.getCreateUser());
			roleMoudle.setRoleModuleId(UUIDUtil.getUuid());
			roleMoudleDao.save(roleMoudle);
		}
	}

	@Override
	public void update(RoleVO roleVO) throws ThinventBaseException {
		Role role = this.roleDao.getByRoleId(roleVO.getRoleId());
		Role existRole = roleDao.findOneByRoleNameAndEnable(roleVO.getRoleName(),1);
		RoleVerifyUtil.verifyUpdateExistRole(existRole,role);
		RoleVerifyUtil.verifyrole(role);
		roleVO.setId(role.getId());
		BeanUtils.copyProperties(roleVO, role);
		this.roleDao.save(role);
		List<UserRole> list = this.userRoleDao.getUserRoleByRoleId(roleVO.getRoleId());
		for (UserRole ur : list) {
			ur.setEnable(0);
			this.userRoleDao.save(ur);
		}
		List<Map<String, Object>> userRoleVOList = roleVO.getUserRoleList();
		if (userRoleVOList == null) {
			return;
		}
		
		for (Map entry : userRoleVOList) {
			if (entry.containsKey("userId")) {
				UserRole ur = this.userRoleDao.getUserRoleByUserIdAndRoleId((String) entry.get(USERID),
						roleVO.getRoleId());
				if (ur != null) {
					ur.setEnable(1);
					this.userRoleDao.save(ur);
				} else {
					UserRole userRole = new UserRole();
					UserRoleVO urVo = new UserRoleVO();
					urVo.setRoleId(roleVO.getRoleId());
					urVo.setUserId((String) entry.get(USERID));
					urVo.setUpdateUser(roleVO.getUpdateUser());
					urVo.setUpdateTime(new Date());
					BeanUtils.copyProperties(urVo, userRole);
					this.userRoleDao.save(userRole);
				}
			}
		}
	}

	@Override
	public void delete(String roleId) throws ThinventBaseException {
		Role role = this.roleDao.getByRoleId(roleId);
		RoleVerifyUtil.verifyrole(role);
		role.setEnable(0);
		this.roleDao.save(role);
		List<UserRole> userRoleList = this.userRoleDao.getUserRoleByRoleId(roleId);
		for (UserRole ur : userRoleList) {
			ur.setEnable(0);
			this.userRoleDao.save(ur);
		}
		
		// 清除角色模块
		List<RoleMoudle> roleMoudleList = roleMoudleDao.findByRoleIdAndEnable(roleId, 1);
		for (RoleMoudle roleMoudle : roleMoudleList) {
			roleMoudle.setEnable(0);
			roleMoudleDao.save(roleMoudle);
		}
	}

	@Override
	public Map listAllRoleByCondition(Pageable pageable, String posName, String posType) {
		Map ret = roleDaoRepository.listAllRoleByCondition(pageable, posName, posType);
		List<RoleVO> rvoList = (List<RoleVO>)ret.get(ROLELIST);
		ret.put(ROLELIST, rvoList);
		return ret;
	}

	@Override
	public List findAllRole() throws ThinventBaseException {
		List<Role> roles = this.roleDao.findAllRole();
		List<RoleVO> rvos = new ArrayList();
		roles.forEach(role -> {
			RoleVO rvo = new RoleVO();
			BeanUtils.copyProperties(role, rvo);
			rvos.add(rvo);
		});

		return rvos;
	}

	@Override
	public List<RoleVO> findAllRoleByUserId(String userId) {
		List<Role> roles = roleDao.getByUserId(userId);
		List<RoleVO> roleVOs = new ArrayList<>();
		for (Role role : roles) {
			RoleVO roleVO = new RoleVO();
			BeanUtils.copyProperties(role, roleVO);
			roleVOs.add(roleVO);
		}
		return roleVOs;
	}

}
