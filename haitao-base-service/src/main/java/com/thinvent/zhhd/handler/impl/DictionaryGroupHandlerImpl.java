package com.thinvent.zhhd.handler.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.thinvent.zhhd.dao.IDictionaryGroupDao;
import com.thinvent.zhhd.handler.IDictionaryGroupHandler;
import com.thinvent.zhhd.model.SysDictionaryGroup;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class DictionaryGroupHandlerImpl implements IDictionaryGroupHandler {
	
	@Autowired
	private IDictionaryGroupDao sysDictionaryGroupDao;
	
	@Override
	public List<SysDictionaryGroup> getAllSysDictionaryGroup() {
		return this.sysDictionaryGroupDao.findAll();
	}
}
