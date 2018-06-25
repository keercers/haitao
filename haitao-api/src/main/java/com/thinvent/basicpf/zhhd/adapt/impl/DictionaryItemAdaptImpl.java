package com.thinvent.basicpf.zhhd.adapt.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thinvent.basicpf.zhhd.adapt.IDictionaryItemAdapt;
import com.thinvent.library.Constants;
import com.thinvent.library.config.ServiceConfig;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.GetPostUtil;
import com.thinvent.zhhd.common.vo.DictionaryItemVO;

@Service
public class DictionaryItemAdaptImpl implements IDictionaryItemAdapt {

	private String basicUrl = ServiceConfig.getServiceConfig(Constants.zhhdBasicService, Constants.zhhdBaseServiceUrlKey);

	@Override
	public JSONObject findAllByDictGroupId(int pageIndex, int pageSize, String dictGroupId)
			throws ThinventBaseException {
		String url = basicUrl + "dictionary/querySysDictionaryItemListByDictGroupId?" + "&pageIndex=" + pageIndex
				+ "&pageSize=" + pageSize + "&dictGroupId=" + dictGroupId;
		return JSON.parseObject(GetPostUtil.sendGet(url));
	}

	@Override
	public void saveSysDictionaryItem(DictionaryItemVO sysDictionaryItemVO) throws ThinventBaseException {
		String url = basicUrl + "dictionary/saveSysDictionaryItem";
		String params = JSON.toJSONString(sysDictionaryItemVO);
		GetPostUtil.sendPost(url, params);
	}

	@Override
	public void updateSysDictionaryItem(DictionaryItemVO sysDictionaryItemVO) throws ThinventBaseException {
		String url = basicUrl + "dictionary/updateSysDictionaryItem";
		String params = JSON.toJSONString(sysDictionaryItemVO);
		GetPostUtil.sendPost(url, params);
	}

	@Override
	public DictionaryItemVO findSysDictionaryItemById(String sysDictionaryItemId) throws ThinventBaseException {
		String url = basicUrl + "dictionary/queryBySysDictionaryItemId?sysDictionaryItemId=" + sysDictionaryItemId;
		String json = GetPostUtil.sendGet(url);
		if (json != null) {
			return JSON.parseObject(json, DictionaryItemVO.class);
		} else
			return null;
	}

	@Override
	public List<DictionaryItemVO> findAllEnabledDictItemsByDictGroupId(String dictGroupId)
			throws ThinventBaseException {
		String url = basicUrl + "dictionary/queryAllEnabledDictItemsByDictGroupId?dictGroupId=" + dictGroupId;
		String json = GetPostUtil.sendGet(url);
		if (json != null) {
			return JSON.parseArray(json, DictionaryItemVO.class);
		} else
			return Collections.emptyList();
	}

	@Override
	public List<DictionaryItemVO> findAllDictItemsByDictGroupId(String dictGroupId) throws ThinventBaseException {
		String url = basicUrl + "dictionary/queryAllDictItemsByDictGroupId?dictGroupId=" + dictGroupId;
		String json = GetPostUtil.sendGet(url);
		if (json != null) {
			return JSON.parseArray(json, DictionaryItemVO.class);
		} else
			return Collections.emptyList();
	}
}
