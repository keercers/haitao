package com.thinvent.basicpf.sys.adapt.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.RetrofitGetPostUtil;
import com.thinvent.basicpf.sys.adapt.IUserRoleAdapt;
import com.thinvent.basicpf.sys.util.URLUtil;

@Service
public class UserRoleAdaptImpl implements IUserRoleAdapt{

	String urlHead = URLUtil.getUrl();
	
	@Override
	public String queryUserIdsByRoleIds(String roleIds) throws ThinventBaseException {

		String url = "userRole/queryUserIdsByRoleIds";
		Map params = new HashMap<>();
		params.put("roleIds", roleIds);
		return RetrofitGetPostUtil.sendGet(urlHead, url, params);
	}

}
