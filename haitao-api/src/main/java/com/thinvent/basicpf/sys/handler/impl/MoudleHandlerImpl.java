package com.thinvent.basicpf.sys.handler.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinvent.basicpf.sys.adapt.IMoudleAdapt;
import com.thinvent.basicpf.sys.handler.IMoudleHandler;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.MoudleVO;

@Service
public class MoudleHandlerImpl implements IMoudleHandler {

	@Autowired
	private IMoudleAdapt moudleAdapt;
	
	@Override
	public List<MoudleVO> getByLevel(String moudleLevel, String userId) throws ThinventBaseException{
		return this.moudleAdapt.getByLevel(moudleLevel, userId);
	}

	@Override
	public Object getTreeBySign(String moudleSign, String userId) throws ThinventBaseException{
		return this.moudleAdapt.getTreeBySign(moudleSign, userId);
	}

	@Override
	public String getForbidList(String userId) throws ThinventBaseException {
		return this.moudleAdapt.getForbidList(userId);
	}

	@Override
	public List<Map> getMoudleTree(String moudleSign, String userId) throws ThinventBaseException {
		return this.moudleAdapt.getMoudleTree(moudleSign, userId);
	}
}
