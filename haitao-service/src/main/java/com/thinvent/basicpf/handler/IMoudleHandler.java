package com.thinvent.basicpf.handler;

import java.util.List;
import java.util.Map;

import com.thinvent.basicpf.model.Moudle;
import com.thinvent.library.exception.ThinventBaseException;

public interface IMoudleHandler {
	
	public List<Moudle> findByMoudleLevelAndEnable(String moudleLevel, String userId) throws ThinventBaseException;
	
	public List<Map> findTreeByMoudleSignLike(String moudleSign,String userId);
	
	public List<Moudle> findByEnable();
	
	public String getForbidList(String userId);

	public List<Map> getMoudleTree(String moudleSign, String userId) throws ThinventBaseException;

}
