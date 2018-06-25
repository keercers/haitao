package com.thinvent.basicpf.sys.adapt.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.thinvent.basicpf.sys.adapt.IUserAdapt;
import com.thinvent.basicpf.sys.util.URLUtil;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.GetPostUtil;
import com.thinvent.library.vo.RoleVO;
import com.thinvent.library.vo.UserRoleVO;
import com.thinvent.library.vo.UserVO;

@Service
public class UserAdaptImpl implements IUserAdapt{
	private String basicUrl =  URLUtil.getUrl();
	
	@Override
	public List<UserVO> findUsersByDepId(String depId) throws ThinventBaseException {
		String url = basicUrl + "user/getUsersByDepId?depId="+depId;
		String json = GetPostUtil.sendGet(url);
		if (json != null && !"".equals(json)) {
			return JSON.parseArray(json, UserVO.class);
		} else
			return Collections.emptyList();
	}
	
	public UserVO findUser(String loginName)throws ThinventBaseException{
		String url = basicUrl+"user/getUserByLoginName?loginName="+loginName;
		String json = GetPostUtil.sendGet(url);
		if(json!=null && !"".equals(json)){
			return JSON.parseObject(json, UserVO.class);
		}else
			return null;
	}

	@Override
	public UserVO findUserByUserId(String userId) throws ThinventBaseException{
		String url = basicUrl+"user/getUserByUserId?userId="+userId;
		String json = GetPostUtil.sendGet(url);
		if(json!=null && !"".equals(json)){
			return JSON.parseObject(json, UserVO.class);
		}else
			return null;
	}
	
	@Override
	public UserVO findUserByLoginName(String loginName) throws ThinventBaseException {
		String url = basicUrl+"user/getUserByLoginName?loginName="+loginName;
		String json = GetPostUtil.sendGet(url);
		if(json!=null && !"".equals(json)){
			return JSON.parseObject(json, UserVO.class);
		}
		
		return null;
	}
	
	@Override
	public Map listAllUser() throws ThinventBaseException {
		Map map = new HashMap<>();
		String url = basicUrl+"user/listAllUser";
		String json = GetPostUtil.sendGet(url);
		if(json!=null && !"".equals(json)){
			map = JSON.parseObject(json, Map.class);
		}
		
		return map;
	}

	@Override
	public void saveUser(UserVO userVO) throws ThinventBaseException{
		String url = basicUrl+"user/saveUser";
		String params = JSON.toJSONString(userVO);
		GetPostUtil.sendPost(url, params);
	}
	
	@Override
	public void updateUser(UserVO userVO) throws ThinventBaseException{
		String url = basicUrl+"user/updateUser";
		String params = JSON.toJSONString(userVO);
		GetPostUtil.sendPost(url, params);
	}
	
	@Override
	public void resetPassword(String userId) throws ThinventBaseException {
		String url = basicUrl + "user/resetPassword?userId=" + userId;
		GetPostUtil.sendGet(url);
	}
	
	@Override
	public void deleteUser(String userId) throws ThinventBaseException {
		String url = basicUrl + "user/deleteUser?userId=" + userId;
		GetPostUtil.sendGet(url);
	}
	
	@Override
	public void updateUserAllInfo(UserVO userVO) throws ThinventBaseException{
		String url = basicUrl+"user/updateUserAllInfo";
		String params = JSON.toJSONString(userVO);
		GetPostUtil.sendPost(url, params);
	}
	
	@Override
	public List<UserVO> listUserByDeptIds(List<String> deptIds, int pageSize, int pageIndex) throws ThinventBaseException {
		List<NameValuePair> list = new ArrayList<>();
		list.add(new BasicNameValuePair("deptIds", listToString(deptIds)));
		list.add(new BasicNameValuePair("pageSize", String.valueOf(pageSize)));
		list.add(new BasicNameValuePair("pageIndex", String.valueOf(pageIndex)));
		
		List<UserVO> userVOs = new ArrayList<>();
		String url = basicUrl+"user/listUserByDeptIds";
		String json = GetPostUtil.sendPost(url, list);
		if(json!=null && !"".equals(json)){
			userVOs = JSON.parseArray(json, UserVO.class);
		}
		
		return userVOs;
	}

	@Override
	public List<UserRoleVO> getUserRoleByUserId(String userId) throws ThinventBaseException {
		List<UserRoleVO> userRoleVOs = new ArrayList<>();
		String url = basicUrl + "userRole/getUserRoleByUserId?userId=" + userId;
		String json = GetPostUtil.sendGet(url);
		if(json != null && !"".equals(json)) {
			userRoleVOs = JSON.parseArray(json, UserRoleVO.class);
		}
		
		return userRoleVOs;
	}

	@Override
	public RoleVO getRoleByRoleId(String roleId) throws ThinventBaseException {
		String url = basicUrl + "role/getRoleByRoleId?roleId=" + roleId;
		String json = GetPostUtil.sendGet(url);
		if(json != null && !"".equals(json)) {
			return JSON.parseObject(json, RoleVO.class);
		}
		
		return null;
	}

	@Override
	public Map listUserByConditions(List<String> deptIds, List<String> posIds, String loginName, String userName,
			int pageSize, int pageIndex, int isAll) throws ThinventBaseException {
		Map map = new HashMap<>();
		String url = basicUrl+"user/listUserByConditions";
		List<NameValuePair> list = new ArrayList<>();
		
		list.add(new BasicNameValuePair("deptIds", listToString(deptIds)));
		list.add(new BasicNameValuePair("posIds", listToString(posIds)));
		list.add(new BasicNameValuePair("pageSize", String.valueOf(pageSize)));
		list.add(new BasicNameValuePair("pageIndex", String.valueOf(pageIndex)));
		list.add(new BasicNameValuePair("loginName", loginName));
		list.add(new BasicNameValuePair("userName", userName));
		list.add(new BasicNameValuePair("isAll", String.valueOf(isAll)));
		
		String json = GetPostUtil.sendPost(url, list);
		if(json!=null && !"".equals(json)){
			map = JSON.parseObject(json, Map.class);
		}
		
		return map;
	}

	private String listToString(List list) {
		String listStr = list.toString();
		return listStr.substring(listStr.indexOf('[') + 1, listStr.indexOf(']'));
	}

	@Override
	public void saveUser(List<UserVO> uvoList) throws ThinventBaseException {
		String url = basicUrl+"user/saveUserList";
		String params = JSON.toJSONString(uvoList);
		GetPostUtil.sendPost(url, params);
	}
	
}
