package com.thinvent.basicpf.sys.adapt.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.GetPostUtil;
import com.thinvent.library.vo.MoudleVO;
import com.thinvent.basicpf.sys.adapt.IMoudleAdapt;
import com.thinvent.basicpf.sys.util.URLUtil;

@Service
public class MoudleAdaptImpl implements IMoudleAdapt{
	private String basicUrl =  URLUtil.getUrl();

	private static final String USERID = new String("&userId=").intern();
	
	@Override
	public List<MoudleVO> getAll() throws ThinventBaseException {
		String url = basicUrl+"moudle/getAll";
		String json = GetPostUtil.sendGet(url);
		if(json!=null){
			return JSON.parseArray(json, MoudleVO.class);
		}else
			return new ArrayList<>();
	}
	
	@Override
	public List<MoudleVO> getByLevel(String moudleLevel, String userId) throws ThinventBaseException {
		String url = basicUrl+"moudle/getMoudleByLevel?moudleLevel="+moudleLevel+USERID+userId;
		String json = GetPostUtil.sendGet(url);
		if(json!=null){
			return JSON.parseArray(json, MoudleVO.class);
		}else
			return new ArrayList<>();
	}

	@Override
	public Object getTreeBySign(String moudleSign, String userId) throws ThinventBaseException{
		String url = basicUrl+"moudle/getMoudleTreeBySign?moudleSign="+moudleSign+USERID+userId;
		String json = GetPostUtil.sendGet(url);
		if(json!=null){
			return json;
		}else
			return null;
	}

	@Override
	public String getForbidList(String userId) throws ThinventBaseException {
		String url = basicUrl+"moudle/getForbidList?userId="+userId;
		return GetPostUtil.sendGet(url);
	}

	@Override
	public List<Map> getMoudleTree(String moudleSign, String userId) throws ThinventBaseException {
		String url = basicUrl + "moudle/getMoudleTree?moudleSign=" + moudleSign + USERID + userId;
        String json = GetPostUtil.sendGet(url);
        if (json != null && !"".equals(json)) {
            return JSON.parseArray(json, Map.class);
        } else {
            return Collections.emptyList();
        }
	}
}
