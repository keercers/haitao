package com.thinvent.basicpf.sys.handler.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinvent.basicpf.sys.adapt.IMenuAdapt;
import com.thinvent.basicpf.sys.handler.IMenuHandler;
import com.thinvent.basicpf.sys.handler.IUserHandler;
import com.thinvent.library.Constants;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.MoudleVO;

@Service
public class MenuHandlerImpl implements IMenuHandler {

	@Autowired
	private IMenuAdapt menuAdapt;
	
	@Autowired
	private IUserHandler userHandler;

	@Override
	public List<MoudleVO> menuAll() throws ThinventBaseException{
		return this.menuAdapt.menuAll();
	}

	@Override
	public Object menuList(String moudelName, int pageIndex, int pageSize) throws ThinventBaseException {
		return this.menuAdapt.menuList(moudelName, pageIndex, pageSize);
	}

	@Override
	public void menuDel(String moudleId) throws ThinventBaseException {
		menuAdapt.menuDel(moudleId);
	}
	@Override
	public String menuOne(String moudleId) throws ThinventBaseException {
		return menuAdapt.menuOne(moudleId);
	}

	@Override
	public Map<String, String> menuAdd(MoudleVO moudle) throws ThinventBaseException {
		String userId = userHandler.getUserIdFromSession();
		moudle.setUpdateUser(userId);
		moudle.setUpdateTime(new Date());
		moudle.setCreateTime(new Date());
		moudle.setCreateUser(userId);
		this.menuAdapt.menuAdd(moudle);
		Map<String, String> map = new HashMap<>();
		map.put("status", Constants.RES_SUCCESS);
		map.put("msg", "保存模块成功");
		return map;
	}

	@Override
	public Map<String, String> menuUpdate(MoudleVO moudle) throws ThinventBaseException{
		String userId = userHandler.getUserIdFromSession();
		moudle.setUpdateUser(userId);
		moudle.setUpdateTime(new Date());
		return this.menuAdapt.menuUpdate(moudle);
	}
}
