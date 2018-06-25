package com.thinvent.basicpf.sys.adapt.impl;


import com.thinvent.library.vo.UserRoleVO;
import com.thinvent.basicpf.sys.adapt.IRoleAdapt;
import com.thinvent.basicpf.sys.util.URLUtil;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.GetPostUtil;
import com.thinvent.library.vo.RoleVO;

import java.util.*;


@Service
public class RoleAdaptImpl implements IRoleAdapt {
	private String basicUrl =  URLUtil.getUrl();
	
	@Override
	public RoleVO findRoleByRoleId(String roleId) throws ThinventBaseException {
		String url = basicUrl+"role/getRoleByRoleId?roleId="+roleId;
		String json = GetPostUtil.sendGet(url);
		if(json!=null && ! "".equalsIgnoreCase(json) ){
			return JSON.parseObject(json, RoleVO.class);
		}else
			return null;
	}

	@Override
	public Map getRoleList(String roleName, String roleType, int pageIndex, int pageSize)
			throws ThinventBaseException {
		Map map = new HashMap<>();
		String url = basicUrl + "role/getRoleList?"
                + "roleName=" + (roleName == null ? "" : roleName)
                + "&roleType=" + (roleType == null ? "" : roleType)
                + "&pageIndex=" + pageIndex
                + "&pageSize=" + pageSize;
        String json = GetPostUtil.sendGet(url);
		if(json!=null && !"".equals(json)){
			map = JSON.parseObject(json, Map.class);
		}
		return map;
    }


	@Override
	public void add(RoleVO roleVO) throws ThinventBaseException {
		String url = basicUrl+"role/addRole";
		String params = JSON.toJSONString(roleVO);
		GetPostUtil.sendPost(url, params);
	}

	@Override
	public void update(RoleVO roleVO) throws ThinventBaseException {
		String url = basicUrl+"role/updateRole";
		String params = JSON.toJSONString(roleVO);
		GetPostUtil.sendPost(url, params);
	}
	

	@Override
	public void delete(String roleId) throws ThinventBaseException {
	    String url = basicUrl+"role/deleteRole?roleId="+roleId;
        GetPostUtil.sendGet(url);
	}

	@Override

	public List<UserRoleVO> getUserRoleByRoleId(String roleId) throws ThinventBaseException {
		List<UserRoleVO> urVo = new ArrayList<>();
		String url = basicUrl + "userRole/getUserRoleByRoleId?roleId="+roleId;
		String json = GetPostUtil.sendGet(url);
		if(json!=null && ! "".equalsIgnoreCase(json) ){
			urVo = JSON.parseArray(json, UserRoleVO.class);
		}
		return urVo;
	}

	@Override
	public List findAllRole() throws ThinventBaseException {
		String url = basicUrl+"role/findAllRole";
		String json = GetPostUtil.sendGet(url);
		if(json!=null && ! "".equalsIgnoreCase(json)){
			return JSON.parseArray(json, RoleVO.class);
		} else {
			return Collections.emptyList();
		}
	}

	@Override
	public List<RoleVO> findAllRoleByUserId(String userId) throws ThinventBaseException {
		String url = basicUrl+"role/findAllRoleByUserId?userId="+userId;
		String json = GetPostUtil.sendGet(url);
		if(json!=null && ! "".equalsIgnoreCase(json)){
			return JSON.parseArray(json, RoleVO.class);
		} else {
			return Collections.emptyList();
		}
	}
}
