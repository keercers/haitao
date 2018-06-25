package com.thinvent.basicpf.zhhd.adapt.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.thinvent.basicpf.zhhd.adapt.IDictionaryGroupAdapt;
import com.thinvent.library.Constants;
import com.thinvent.library.config.ServiceConfig;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.GetPostUtil;
import com.thinvent.zhhd.common.vo.DictionaryGroupVO;

@Service
public class DictionaryGroupAdaptImpl implements IDictionaryGroupAdapt {

	private String basicUrl = ServiceConfig.getServiceConfig(Constants.zhhdBasicService, Constants.zhhdBaseServiceUrlKey);

	@Override
	public List<DictionaryGroupVO> findAll() throws ThinventBaseException {
		List<DictionaryGroupVO> list = new ArrayList<>();
		String url = basicUrl + "dictionary/querySysDictionaryGroupList";
		String json = GetPostUtil.sendGet(url);
		if (json != null && !"".equals(json)) {
			list = JSON.parseArray(json, DictionaryGroupVO.class);
			return list;
		} else
			return list;
	}
}
