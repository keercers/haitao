package com.thinvent.basicpf.sys.handler.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.StringUtil;
import com.thinvent.basicpf.sys.adapt.IUserRoleAdapt;
import com.thinvent.basicpf.sys.handler.IUserRoleHandler;

@Service
public class UserRoleHandlerImpl implements IUserRoleHandler{
	
	@Autowired
	private IUserRoleAdapt userRoleAdaptImpl;

	@Override
	public List<String> queryUserIdsByRoleIds(String roleIds) throws ThinventBaseException {
		String result = userRoleAdaptImpl.queryUserIdsByRoleIds(roleIds);
		JSONArray jsonArray = JSONArray.parseArray(result);
		return StringUtil.jSONArrayToList(jsonArray);
	}

}
