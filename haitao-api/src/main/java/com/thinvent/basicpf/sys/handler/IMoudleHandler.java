package com.thinvent.basicpf.sys.handler;

import java.util.List;
import java.util.Map;

import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.MoudleVO;

public interface IMoudleHandler {
	
	public List<MoudleVO> getByLevel(String moudleLevel, String userId)throws ThinventBaseException;
	public Object getTreeBySign(String moudleSign, String userId)throws ThinventBaseException;
	public String getForbidList(String userId) throws ThinventBaseException;
	public List<Map> getMoudleTree(String moudleSign, String userId) throws ThinventBaseException;

}
