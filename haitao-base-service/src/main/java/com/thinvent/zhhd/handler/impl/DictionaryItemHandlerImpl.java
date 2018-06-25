package com.thinvent.zhhd.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.thinvent.library.Constants;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.zhhd.common.vo.DictionaryItemVO;
import com.thinvent.zhhd.dao.IDictionaryItemDao;
import com.thinvent.zhhd.handler.IDictionaryItemHandler;
import com.thinvent.zhhd.model.SysDictionaryItem;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class DictionaryItemHandlerImpl implements IDictionaryItemHandler {

	@Autowired
	private IDictionaryItemDao sysDictionaryItemDao;

	@Override
	public Page<SysDictionaryItem> findAllByDictGroupId(int pageIndex, int pageSize, String dictGroupId) {
		Sort sort = new Sort(Direction.ASC, "dictItemId");// 排序
		PageRequest pageRequest = new PageRequest(pageIndex - 1, pageSize, sort);
		return sysDictionaryItemDao.findAllByDictGroupId(dictGroupId, pageRequest);
	}

	@Override
	public void save(DictionaryItemVO sysDictionaryItemVO) {
		SysDictionaryItem sysDictionaryItem = new SysDictionaryItem();
		BeanUtils.copyProperties(sysDictionaryItemVO, sysDictionaryItem);
		this.sysDictionaryItemDao.save(sysDictionaryItem);
	}

	@Override
	public String update(DictionaryItemVO sysDictionaryItemVO) throws ThinventBaseException {
		SysDictionaryItem sysDictionaryItem = this.sysDictionaryItemDao
				.findSysDictionaryItemBySysDictionaryItemId(sysDictionaryItemVO.getDictItemId());
		BeanUtils.copyProperties(sysDictionaryItemVO, sysDictionaryItem);
		sysDictionaryItemDao.save(sysDictionaryItem);
		return Constants.RES_SUCCESS;
	}

	@Override
	public SysDictionaryItem findSysDictionaryItemById(String sysDictionaryItemId) {
		return sysDictionaryItemDao.findSysDictionaryItemBySysDictionaryItemId(sysDictionaryItemId);
	}
	
	
	@Override
	public List<DictionaryItemVO> findAllEnabledDictItemsByDictGroupId(String dictGroupId)
			throws ThinventBaseException {
		List<SysDictionaryItem> resultList = sysDictionaryItemDao.findAllByDictGroupId(dictGroupId);
		List<DictionaryItemVO> list = new ArrayList<>();
		DictionaryItemVO nullVO = new DictionaryItemVO().setDictItemValue("全部");
		list.add(nullVO);
		for (SysDictionaryItem item : resultList){
			DictionaryItemVO vo = new DictionaryItemVO();
			BeanUtils.copyProperties(item, vo);
			list.add(vo);
		}
		return list;
	}

	@Override
	public List<DictionaryItemVO> findAllDictItemsByDictGroupId(String dictGroupId) throws ThinventBaseException {
		List<SysDictionaryItem> resultList = sysDictionaryItemDao.findAllDictionaryItemsByDictGroupId(dictGroupId);
		List<DictionaryItemVO> list = new ArrayList<>();
		DictionaryItemVO nullVO = new DictionaryItemVO().setDictItemValue("全部");
		list.add(nullVO);
		for (SysDictionaryItem item : resultList){
			DictionaryItemVO vo = new DictionaryItemVO();
			BeanUtils.copyProperties(item, vo);
			list.add(vo);
		}
		return list;
	}
}
