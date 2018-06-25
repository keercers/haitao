package com.thinvent.basicpf.zhhd.handler.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.thinvent.basicpf.zhhd.adapt.IDictionaryItemAdapt;
import com.thinvent.basicpf.zhhd.handler.IDictionaryItemHandler;
import com.thinvent.basicpf.zhhd.util.DictionaryItemVerifyUtil;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.zhhd.common.vo.DictionaryItemVO;

@Service
public class DictionaryItemHandlerImpl implements IDictionaryItemHandler {

	@Autowired
	private IDictionaryItemAdapt sysDictionaryItemAdapt;

	@Override
	public JSONObject getAllDictionaryItemByDictGroupId(int pageIndex, int pageSize, String dictGroupId)
			throws ThinventBaseException {
		return sysDictionaryItemAdapt.findAllByDictGroupId(pageIndex, pageSize, dictGroupId);
	}

	@Override
	public void saveSysDictionaryItem(DictionaryItemVO sysDictionaryItemVO) throws ThinventBaseException {
		DictionaryItemVerifyUtil.verifySysDictionaryItem(sysDictionaryItemVO);
		sysDictionaryItemAdapt.saveSysDictionaryItem(sysDictionaryItemVO);
	}

	@Override
	public void updateSysDictionaryItem(DictionaryItemVO sysDictionaryItemVO) throws ThinventBaseException {
		DictionaryItemVerifyUtil.verifySysDictionaryItem(sysDictionaryItemVO);
		sysDictionaryItemAdapt.updateSysDictionaryItem(sysDictionaryItemVO);
	}

	@Override
	public DictionaryItemVO findSysDictionaryItemById(String sysDictionaryItemId) throws ThinventBaseException {
		return sysDictionaryItemAdapt.findSysDictionaryItemById(sysDictionaryItemId);
	}

	@Override
	public List<DictionaryItemVO> findAllEnabledDictItemsByDictGroupId(String dictGroupId)
			throws ThinventBaseException {
		return sysDictionaryItemAdapt.findAllEnabledDictItemsByDictGroupId(dictGroupId);
	}

	@Override
	public List<DictionaryItemVO> findAllDictItemsByDictGroupId(String dictGroupId) throws ThinventBaseException {
		return sysDictionaryItemAdapt.findAllDictItemsByDictGroupId(dictGroupId);
	}
}
