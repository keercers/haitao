package com.thinvent.basicpf.sys.handler;

import com.alibaba.fastjson.JSONObject;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.ConfigVO;

public interface ISysConfHandler {

	public void addConfig(ConfigVO configVO) throws ThinventBaseException;

	public void updateConfig(ConfigVO configVO) throws ThinventBaseException;

	public void deleteConfig(String configId) throws ThinventBaseException;

	public JSONObject getConfList(int confType, Integer pageIndex, Integer pageSize) throws ThinventBaseException;

	public ConfigVO getByConfId(String confId) throws ThinventBaseException;

}
