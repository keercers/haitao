package com.thinvent.basicpf.handler;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thinvent.basicpf.model.Config;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.ConfigVO;

public interface ISysConfHandler {

	
	public void deleteSysParam(String confId) throws ThinventBaseException;

	public Page<Config> findSysParam(int confType, Pageable pageable) throws ThinventBaseException;

	public Config findByConfId(String confId) throws ThinventBaseException;

	public void updateSysParam(ConfigVO configVO) throws ThinventBaseException;

	public void saveSysParam(ConfigVO configVO) throws ThinventBaseException;

	public Config findConfigByConfKey(String confKey) throws ThinventBaseException;

	
}
