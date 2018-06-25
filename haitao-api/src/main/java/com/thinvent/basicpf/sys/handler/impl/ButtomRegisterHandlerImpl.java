package com.thinvent.basicpf.sys.handler.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinvent.basicpf.sys.adapt.IButtomRegisterAdapt;
import com.thinvent.basicpf.sys.handler.IButtomRegisterHandler;
import com.thinvent.basicpf.sys.handler.IUserHandler;
import com.thinvent.library.Constants;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.MoudleVO;

@Service
public class ButtomRegisterHandlerImpl implements IButtomRegisterHandler {

	@Autowired
	private IButtomRegisterAdapt buttomRegisterAdapt;
	
	@Autowired
	private IUserHandler userHandler;

	@Override
	public Object buttomRegisterList(String moudelId, String moudelName, int pageIndex, int pageSize) throws ThinventBaseException {
		return this.buttomRegisterAdapt.buttomRegisterList(moudelId, moudelName, pageIndex, pageSize);
	}

	@Override
	public void buttomRegisterDel(String moudleId) throws ThinventBaseException {
		buttomRegisterAdapt.buttomRegisterDel(moudleId);
	}
	@Override
	public String buttomRegisterOne(String moudleId) throws ThinventBaseException {
		return buttomRegisterAdapt.buttomRegisterOne(moudleId);
	}

	@Override
	public Map<String, String> buttomRegisterAdd(MoudleVO moudle) throws ThinventBaseException {
		String userId = userHandler.getUserIdFromSession();
		moudle.setUpdateUser(userId);
		moudle.setUpdateTime(new Date());
		moudle.setCreateTime(new Date());
		moudle.setCreateUser(userId);
		this.buttomRegisterAdapt.buttomRegisterAdd(moudle);
		Map<String, String> map = new HashMap<>();
		map.put("status", Constants.RES_SUCCESS);
		map.put("msg", "保存模块成功");
		return map;
	}

	@Override
	public Map<String, String> buttomRegisterUpdate(MoudleVO moudle) throws ThinventBaseException{
		String userId = userHandler.getUserIdFromSession();
		moudle.setUpdateUser(userId);
		moudle.setUpdateTime(new Date());
		return this.buttomRegisterAdapt.buttomRegisterUpdate(moudle);
	}
}
