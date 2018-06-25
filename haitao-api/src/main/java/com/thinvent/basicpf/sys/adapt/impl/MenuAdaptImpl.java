package com.thinvent.basicpf.sys.adapt.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.GetPostUtil;
import com.thinvent.library.vo.MoudleVO;
import com.thinvent.basicpf.sys.adapt.IMenuAdapt;
import com.thinvent.basicpf.sys.util.URLUtil;

@Service
public class MenuAdaptImpl implements IMenuAdapt{
	
	private String basicUrl =  URLUtil.getUrl();
	
	@Override
	public List<MoudleVO> menuAll() throws ThinventBaseException {
		String url = basicUrl+"menu/menuAll";
		String json = GetPostUtil.sendGet(url);
		if(json!=null){
			return JSON.parseArray(json, MoudleVO.class);
		}else
			return new ArrayList<>();
	}

	@Override
	public Object menuList(String moudelName, int pageIndex, int pageSize) throws ThinventBaseException {
		String url = basicUrl+"menu/menuList?moudelName="+moudelName+"&pageIndex="+pageIndex+"&pageSize="+pageSize;
		return GetPostUtil.sendGet(url);
	}

	@Override
	public void menuDel(String moudleId) throws ThinventBaseException {
		String url = basicUrl+"menu/menuDel?moudleId="+moudleId;
		GetPostUtil.sendGet(url);
	}
	@Override
	public String menuOne(String moudleId) throws ThinventBaseException {
		String url = basicUrl+"menu/menuOne?moudleId="+moudleId;
		return GetPostUtil.sendGet(url);
	}

	@Override
	public String menuAdd(MoudleVO moudle) throws ThinventBaseException {
		String url = basicUrl+"menu/menuAdd";
		String params = JSON.toJSONString(moudle);
		String json = GetPostUtil.sendPost(url, params);
		if(json!=null){
			return json;
		}else
			return "400";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> menuUpdate(MoudleVO moudle) throws ThinventBaseException {
		String url = basicUrl+"menu/menuUpdate";
		String params = JSON.toJSONString(moudle);
		String json = GetPostUtil.sendPost(url, params);
		if(json!=null){
			return JSON.parseObject(json, HashMap.class);
		}else
			return new HashMap<>();
	}
}
