package com.thinvent.basicpf.util;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.thinvent.basicpf.dao.IRoleDao;
import com.thinvent.basicpf.dao.IRoleMoudleDao;
import com.thinvent.basicpf.model.Role;
import com.thinvent.basicpf.model.RoleMoudle;
import com.thinvent.library.util.UUIDUtil;
import com.thinvent.library.vo.MoudleVO;

@Configuration
public class ModuleUtil {

	@Autowired
	private IRoleDao roleDao;

	@Autowired
	private IRoleMoudleDao roleMoudleDao;

	// 初始化角色模块
	public void initRoleModule(MoudleVO moudleVO) {
    	List<Role> roleList = roleDao.findAllRole();
    	Date date = new Date();
    	for (Role role : roleList) {
			RoleMoudle roleMoudle = new RoleMoudle();
			if("系统管理员".equals(role.getRoleName())) {
				roleMoudle.setEnable(1);
			}else {
				roleMoudle.setEnable(0);
			}
			roleMoudle.setCreateTime(date);
			roleMoudle.setUpdateTime(date);
			roleMoudle.setRoleId(role.getRoleId());
			roleMoudle.setMoudelId(moudleVO.getMoudleId());
			roleMoudle.setRoleModuleId(UUIDUtil.getUuid());
			roleMoudleDao.save(roleMoudle);
		}
	}
	// 清除角色模块
	public void clearRoleModule(String moudleId) {
		List<RoleMoudle> roleMoudleList = roleMoudleDao.findByMoudelIdAndEnable(moudleId, 1);
    	for (RoleMoudle roleMoudle : roleMoudleList) {
    		roleMoudle.setEnable(0);
    		roleMoudleDao.save(roleMoudle);
		}
	}
}
