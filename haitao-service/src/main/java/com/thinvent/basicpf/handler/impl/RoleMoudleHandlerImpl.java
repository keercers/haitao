package com.thinvent.basicpf.handler.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinvent.basicpf.dao.IRoleMoudleDao;
import com.thinvent.basicpf.dao.jpa.RoleMoudelDaoImpl;
import com.thinvent.basicpf.handler.IRoleMoudleHandler;
import com.thinvent.basicpf.model.RoleMoudle;
import com.thinvent.library.Constants;
import com.thinvent.library.util.UUIDUtil;


@Service
public class RoleMoudleHandlerImpl implements IRoleMoudleHandler{

	@Autowired
	private IRoleMoudleDao roleMoudleDao;
	@Autowired
	private RoleMoudelDaoImpl roleMoudelDaoImpl;
	
	@Override
	public List<RoleMoudle> queryList(String roleId) {
		return roleMoudleDao.findByRoleIdAndEnable(roleId, 1);
	}

	@Override
	public String save(String roleId, String userId, String choices) {
		Set<String> moudleIdInput = new HashSet<>();
		String[] moudelIds = choices.split("-");
		for(String moudelId: moudelIds){
			moudleIdInput.add(moudelId);
		}
		
		List<RoleMoudle> roleMoudleList = roleMoudelDaoImpl.getListRoleMoudleByUserId(userId);
		for(RoleMoudle roleMoudle: roleMoudleList){
			RoleMoudle roleMoudleSource = roleMoudleDao.findByRoleIdAndMoudelId(roleId, roleMoudle.getMoudelId());
			if (null == roleMoudleSource) {
				roleMoudleSource = new RoleMoudle();
				roleMoudleSource.setCreateTime(new Date());
				roleMoudleSource.setUpdateTime(new Date());
				roleMoudleSource.setCreateUser(userId);
				roleMoudleSource.setUpdateUser(userId);
				roleMoudleSource.setMoudelId(roleMoudle.getMoudelId());
				roleMoudleSource.setRoleId(roleId);
				roleMoudleSource.setRoleModuleId(UUIDUtil.getUuid());
			}
			if(moudleIdInput.contains(roleMoudle.getMoudelId())) {
				roleMoudleSource.setEnable(1);
			}else {
				roleMoudleSource.setEnable(0);
			}
			roleMoudleDao.save(roleMoudleSource);
		}
    	return Constants.RES_SUCCESS;
	}
}
