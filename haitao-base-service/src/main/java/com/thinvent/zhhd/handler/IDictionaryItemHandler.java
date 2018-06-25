package com.thinvent.zhhd.handler;

import java.util.List;

import org.springframework.data.domain.Page;

import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.zhhd.common.vo.DictionaryItemVO;
import com.thinvent.zhhd.model.SysDictionaryItem;

public interface IDictionaryItemHandler {

	public Page<SysDictionaryItem> findAllByDictGroupId(int pageIndex, int pageSize, String dictGroupId);
//	public List<DictionaryItemVO> findAllByDictGroupId(int pageIndex, int pageSize, String dictGroupId);

	public void save(DictionaryItemVO sysDictionaryItemVO);
	
	public String update(DictionaryItemVO sysDictionaryItemVO) throws ThinventBaseException;
	
	public SysDictionaryItem findSysDictionaryItemById(String sysDictionaryItemId);
	
	
	public List<DictionaryItemVO> findAllEnabledDictItemsByDictGroupId(String dictGroupId) throws ThinventBaseException;

	public List<DictionaryItemVO> findAllDictItemsByDictGroupId(String dictGroupId) throws ThinventBaseException;
}
