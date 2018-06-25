package com.thinvent.basicpf.zhhd.adapt;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.zhhd.common.vo.DictionaryItemVO;

public interface IDictionaryItemAdapt {
	public JSONObject findAllByDictGroupId(int pageIndex, int pageSize, String dictGroupId) throws ThinventBaseException;
	public void saveSysDictionaryItem(DictionaryItemVO sysDictionaryItemVO) throws ThinventBaseException;
	public void updateSysDictionaryItem(DictionaryItemVO sysDictionaryItemVO) throws ThinventBaseException;
	public DictionaryItemVO findSysDictionaryItemById(String sysDictionaryItemId) throws ThinventBaseException;
	
	public List<DictionaryItemVO> findAllEnabledDictItemsByDictGroupId(String dictGroupId) throws ThinventBaseException;
	public List<DictionaryItemVO> findAllDictItemsByDictGroupId(String dictGroupId) throws ThinventBaseException;
}
