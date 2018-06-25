package com.thinvent.basicpf.sys.adapt.impl;

import org.springframework.stereotype.Service;

import com.thinvent.basicpf.sys.adapt.IRoleMoudleAdapt;
import com.thinvent.basicpf.sys.util.URLUtil;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.GetPostUtil;

@Service
public class RoleMoudleAdaptImpl implements IRoleMoudleAdapt{
	private String basicUrl =  URLUtil.getUrl();
	
	@Override
	public String getListByRoleId(String roleId) throws ThinventBaseException{
		String url = basicUrl+"roleMoudle/getListByRoleId?roleId=" + roleId;
		return GetPostUtil.sendGet(url);
	}

	@Override
	public String save(String roleId, String userId, String choices) throws ThinventBaseException {
		String url = basicUrl+"roleMoudle/save?userId=" + userId +"&choices="+choices+"&roleId="+roleId;
		return GetPostUtil.sendGet(url);
	}
}