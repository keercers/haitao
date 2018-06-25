package com.thinvent.basicpf.sys.adapt;

import com.alibaba.fastjson.JSONObject;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.ConfigVO;

public interface ISysConfAdapt {
	
	public void addConfig(ConfigVO configVO) throws ThinventBaseException;

	public void updateConfig(ConfigVO configVO) throws ThinventBaseException;

	public void deleteConfig(String configId) throws ThinventBaseException;

	public JSONObject getByConfType(int confType, Integer pageIndex, Integer pageSize) throws ThinventBaseException;

	public ConfigVO getByConfId(String confId) throws ThinventBaseException;

	public ConfigVO findConfigByConfKey(String confKey) throws ThinventBaseException;
}
