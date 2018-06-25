package com.thinvent.basicpf.sys.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.thinvent.basicpf.sys.adapt.ISysConfAdapt;
import com.thinvent.basicpf.sys.handler.ISysConfHandler;
import com.thinvent.basicpf.sys.util.ConfigVerifyUtil;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.ConfigVO;

@Service
public class SysConfHandlerImpl implements ISysConfHandler {

	@Autowired
    private ISysConfAdapt sysConfAdapt;

	@Override
	public JSONObject getConfList(int confType, Integer pageIndex, Integer pageSize) throws ThinventBaseException {
		return sysConfAdapt.getByConfType(confType, pageIndex, pageSize);
	}

	@Override
	public void addConfig(ConfigVO configVO) throws ThinventBaseException {
		ConfigVerifyUtil.verifyConf(configVO);
		ConfigVO cvo = sysConfAdapt.findConfigByConfKey(configVO.getConfKey());
		ConfigVerifyUtil.verifyConfKey(cvo);
		sysConfAdapt.addConfig(configVO);
	}

	@Override
	public void updateConfig(ConfigVO configVO) throws ThinventBaseException {
		ConfigVerifyUtil.verifyConf(configVO);
		ConfigVO cvo = sysConfAdapt.findConfigByConfKey(configVO.getConfKey());
		if (cvo != null && !configVO.getConfId().equals(cvo.getConfId())) {
			ConfigVerifyUtil.verifyConfKey(cvo);
		}
		sysConfAdapt.updateConfig(configVO);
	}

	@Override
	public void deleteConfig(String confId) throws ThinventBaseException {
		sysConfAdapt.deleteConfig(confId);
	}

	@Override
	public ConfigVO getByConfId(String confId) throws ThinventBaseException {
		ConfigVO configVO = sysConfAdapt.getByConfId(confId);
		ConfigVerifyUtil.verifyConf(configVO);
		return configVO;
	}
}
