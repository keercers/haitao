package com.thinvent.basicpf.sys.adapt.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.GetPostUtil;
import com.thinvent.library.vo.MoudleVO;
import com.thinvent.basicpf.sys.adapt.IButtomRegisterAdapt;
import com.thinvent.basicpf.sys.util.URLUtil;

@Service
public class ButtomRegisterAdaptImpl implements IButtomRegisterAdapt{
	
	private String basicUrl =  URLUtil.getUrl();

	@Override
	public Object buttomRegisterList(String moudelId, String moudelName, int pageIndex, int pageSize) throws ThinventBaseException {
		String url = basicUrl+"buttomRegister/buttomRegisterList?moudelName="+moudelName+"&pageIndex="+pageIndex+"&pageSize="+pageSize+"&moudelId="+moudelId;
		return GetPostUtil.sendGet(url);
	}

	@Override
	public void buttomRegisterDel(String moudleId) throws ThinventBaseException {
		String url = basicUrl+"buttomRegister/buttomRegisterDel?moudleId="+moudleId;
		GetPostUtil.sendGet(url);
	}
	@Override
	public String buttomRegisterOne(String moudleId) throws ThinventBaseException {
		String url = basicUrl+"buttomRegister/buttomRegisterOne?moudleId="+moudleId;
		return GetPostUtil.sendGet(url);
	}

	@Override
	public String buttomRegisterAdd(MoudleVO moudle) throws ThinventBaseException {
		String url = basicUrl+"buttomRegister/buttomRegisterAdd";
		String params = JSON.toJSONString(moudle);
		String json = GetPostUtil.sendPost(url, params);
		if(json!=null){
			return json;
		}else
			return "400";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> buttomRegisterUpdate(MoudleVO moudle) throws ThinventBaseException {
		String url = basicUrl+"buttomRegister/buttomRegisterUpdate";
		String params = JSON.toJSONString(moudle);
		String json = GetPostUtil.sendPost(url, params);
		if(json!=null){
			return JSON.parseObject(json, HashMap.class);
		}else
			return new HashMap<>();
	}
}
