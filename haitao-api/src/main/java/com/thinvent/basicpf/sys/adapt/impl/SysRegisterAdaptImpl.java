package com.thinvent.basicpf.sys.adapt.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.thinvent.basicpf.sys.adapt.ISysRegisterAdapt;
import com.thinvent.basicpf.sys.util.URLUtil;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.GetPostUtil;
import com.thinvent.library.vo.MoudleVO;

@Service
public class SysRegisterAdaptImpl implements ISysRegisterAdapt{
	
	private String basicUrl =  URLUtil.getUrl();

	@Override
	public Object sysRegisterList(String moudelName, int pageIndex, int pageSize) throws ThinventBaseException {
		String url = basicUrl+"sysRegister/sysRegisterList?moudelName="+moudelName+"&pageIndex="+pageIndex+"&pageSize="+pageSize;
		return GetPostUtil.sendGet(url);
	}

	@Override
	public void sysRegisterDel(String moudleId) throws ThinventBaseException {
		String url = basicUrl+"sysRegister/sysRegisterDel?moudleId="+moudleId;
		GetPostUtil.sendGet(url);
	}
	@Override
	public String sysRegisterOne(String moudleId) throws ThinventBaseException {
		String url = basicUrl+"sysRegister/sysRegisterOne?moudleId="+moudleId;
		return GetPostUtil.sendGet(url);
	}

	@Override
	public String sysRegisterAdd(MoudleVO moudle) throws ThinventBaseException {
		String url = basicUrl+"sysRegister/sysRegisterAdd";
		String params = JSON.toJSONString(moudle);
		String json = GetPostUtil.sendPost(url, params);
		if(json!=null){
			return json;
		}else
			return "400";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> sysRegisterUpdate(MoudleVO moudle) throws ThinventBaseException {
		String url = basicUrl+"sysRegister/sysRegisterUpdate";
		String params = JSON.toJSONString(moudle);
		String json = GetPostUtil.sendPost(url, params);
		if(json!=null){
			return JSON.parseObject(json, HashMap.class);
		}else
			return new HashMap<>();
	}
}
