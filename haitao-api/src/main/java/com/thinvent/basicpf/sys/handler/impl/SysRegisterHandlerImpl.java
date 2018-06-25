package com.thinvent.basicpf.sys.handler.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinvent.basicpf.sys.adapt.ISysRegisterAdapt;
import com.thinvent.basicpf.sys.handler.ISysRegisterHandler;
import com.thinvent.basicpf.sys.handler.IUserHandler;
import com.thinvent.library.Constants;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.MoudleVO;

@Service
public class SysRegisterHandlerImpl implements ISysRegisterHandler {

	@Autowired
	private ISysRegisterAdapt sysRegisterAdapt;
	
	@Autowired
	private IUserHandler userHandler;

	@Override
	public Object sysRegisterList(String moudelName, int pageIndex, int pageSize) throws ThinventBaseException {
		return this.sysRegisterAdapt.sysRegisterList(moudelName, pageIndex, pageSize);
	}

	@Override
	public void sysRegisterDel(String moudleId) throws ThinventBaseException {
		sysRegisterAdapt.sysRegisterDel(moudleId);
	}
	@Override
	public String sysRegisterOne(String moudleId) throws ThinventBaseException {
		return sysRegisterAdapt.sysRegisterOne(moudleId);
	}

	@Override
	public Map<String, String> sysRegisterAdd(MoudleVO moudle) throws ThinventBaseException {
		String userId = userHandler.getUserIdFromSession();
		moudle.setUpdateUser(userId);
		moudle.setUpdateTime(new Date());
		moudle.setCreateTime(new Date());
		moudle.setCreateUser(userId);
		this.sysRegisterAdapt.sysRegisterAdd(moudle);
		Map<String, String> map = new HashMap<>();
		map.put("status", Constants.RES_SUCCESS);
		map.put("msg", "保存模块成功");
		return map;
	}

	@Override
	public Map<String, String> sysRegisterUpdate(MoudleVO moudle) throws ThinventBaseException{
		String userId = userHandler.getUserIdFromSession();
		moudle.setUpdateUser(userId);
		moudle.setUpdateTime(new Date());
		return this.sysRegisterAdapt.sysRegisterUpdate(moudle);
	}
}
