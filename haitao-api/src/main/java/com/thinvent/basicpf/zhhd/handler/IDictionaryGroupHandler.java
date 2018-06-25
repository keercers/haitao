package com.thinvent.basicpf.zhhd.handler;

import java.util.List;

import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.zhhd.common.vo.DictionaryGroupVO;

@FunctionalInterface
public interface IDictionaryGroupHandler {
	public List<DictionaryGroupVO> getAllSysDictionaryGroup() throws ThinventBaseException;
}
