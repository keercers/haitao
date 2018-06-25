package com.thinvent.basicpf.sys.adapt.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.GetPostUtil;
import com.thinvent.library.vo.LogVO;
import com.thinvent.basicpf.sys.adapt.ILogAdapt;
import com.thinvent.basicpf.sys.util.URLUtil;

@Service
public class LogAdaptImpl implements ILogAdapt {
	private String basicUrl =  URLUtil.getUrl();

	@Override
	public void saveLog(LogVO logVO) throws ThinventBaseException {
		String url = basicUrl + "log/saveLog";
	    String params = JSON.toJSONString(logVO);
	    GetPostUtil.sendPost(url, params);
	}

	@Override
	public Map listLogByParams(String logType, String userName, String system, String maxDate, String minDate, int pageSize,
			int pageIndex) throws ThinventBaseException {
		String url = basicUrl + "log/listLogByParams?"
				+ "logType=" + logType + "&"
				+ "userName=" + userName + "&"
				+ "system=" + system + "&"
				+ "maxDate=" + maxDate + "&"
				+ "minDate=" + minDate + "&"
				+ "pageSize=" + pageSize + "&"
				+ "pageIndex=" + pageIndex;
        String json = GetPostUtil.sendGet(url);
        if (json != null && !"".equals(json)) {
            return JSON.parseObject(json, Map.class);
        } else
            return null;
	}

}
