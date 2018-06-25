package com.thinvent.basicpf.zhhd.handler.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinvent.basicpf.zhhd.adapt.IDictionaryGroupAdapt;
import com.thinvent.basicpf.zhhd.handler.IDictionaryGroupHandler;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.zhhd.common.vo.DictionaryGroupVO;

@Service
public class DictionaryGroupHandlerImpl implements IDictionaryGroupHandler {

	@Autowired
	private IDictionaryGroupAdapt sysDictionaryGroupAdapter;

	@Override
	public List<DictionaryGroupVO> getAllSysDictionaryGroup() throws ThinventBaseException {
		return sysDictionaryGroupAdapter.findAll();
	}
}
