package com.thinvent.basicpf.sys.adapt.impl;


import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thinvent.basicpf.sys.adapt.ISysConfAdapt;
import com.thinvent.basicpf.sys.util.URLUtil;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.GetPostUtil;
import com.thinvent.library.vo.ConfigVO;

@Service
public class SysConfAdaptImpl implements ISysConfAdapt {
	private String basicUrl =  URLUtil.getUrl();

	@Override
	public JSONObject getByConfType(int confType, Integer pageIndex, Integer pageSize) throws ThinventBaseException {
		String url = basicUrl + "sysconf/querySysParam?"
				+ "confType=" + confType + "&"
				+ "enable=1" + "&"
				+ "pageSize=" + pageSize + "&"
				+ "pageIndex=" + pageIndex;
        String json = GetPostUtil.sendGet(url);
        if (json != null && !"".equals(json)) {
            return JSON.parseObject(json);
        } else
            return null;
	}

	@Override
	public void addConfig(ConfigVO configVO) throws ThinventBaseException {
		 String url = basicUrl + "sysconf/saveSysParam";
	     String params = JSON.toJSONString(configVO);
	     GetPostUtil.sendPost(url, params);
	}

	@Override
	public void updateConfig(ConfigVO configVO) throws ThinventBaseException {
		String url = basicUrl + "sysconf/updateSysParam";
        String params = JSON.toJSONString(configVO);
        GetPostUtil.sendPost(url, params);
	}

	@Override
	public void deleteConfig(String confId) throws ThinventBaseException {
		String url = basicUrl + "sysconf/deleteSysParam?confId=" + confId;
		GetPostUtil.sendGet(url);
    }

	@Override
	public ConfigVO getByConfId(String confId) throws ThinventBaseException{
		String url = basicUrl + "sysconf/getByConfId?confId=" + confId;
        String json = GetPostUtil.sendGet(url);
        if (json != null && !"".equals(json)) {
            return JSON.parseObject(json, ConfigVO.class);
        } else
            return null;
	}

	@Override
	public ConfigVO findConfigByConfKey(String confKey) throws ThinventBaseException {
		String url = basicUrl + "sysconf/findConfigByConfKey?confKey=" + confKey;
        String json = GetPostUtil.sendGet(url);
        if (json != null && !"".equals(json)) {
            return JSON.parseObject(json, ConfigVO.class);
        } else
            return null;
	}
}
