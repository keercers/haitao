package com.thinvent.basicpf.zhhd.adapt;

import java.util.List;

import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.zhhd.common.vo.DictionaryGroupVO;

@FunctionalInterface
public interface IDictionaryGroupAdapt {
	
	public List<DictionaryGroupVO> findAll() throws ThinventBaseException;
}
