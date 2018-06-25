package com.thinvent.zhhd.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.thinvent.zhhd.model.SysDictionaryItem;

public interface IDictionaryItemDao extends JpaRepository<SysDictionaryItem, Integer>  {

	public Page<SysDictionaryItem> findAllByDictGroupId(String dictGroupId, Pageable pageRequest);

//	public List<SysDictionaryItem> findAllByDictGroupId(String dictGroupId, Pageable pageRequest);
	
	@SuppressWarnings("unchecked")
	public SysDictionaryItem save(SysDictionaryItem sysDictionaryItem);
	
	@Query("from SysDictionaryItem a  where a.dictItemId=?1")
	public SysDictionaryItem findSysDictionaryItemBySysDictionaryItemId(String sysDictionaryItemId);
	
	@Query("from SysDictionaryItem a  where a.dictGroupId=?1 and enabled=1")
	public List<SysDictionaryItem> findAllByDictGroupId(String dictGroupId);
	
	@Query("from SysDictionaryItem a  where a.dictGroupId=?1")
	public List<SysDictionaryItem> findAllDictionaryItemsByDictGroupId(String dictGroupId);
}