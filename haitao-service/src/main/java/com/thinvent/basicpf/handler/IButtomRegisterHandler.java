package com.thinvent.basicpf.handler;

import java.util.Map;

import com.thinvent.basicpf.model.Moudle;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.MoudleVO;

public interface IButtomRegisterHandler {

	public Object buttomRegisterList(String moudelId, String moudelName, int pageIndex, int pageSize);

	public void buttomRegisterDel(String moudleId) throws ThinventBaseException;

	public Moudle buttomRegisterOne(String moudleId);
	
	public String buttomRegisterAdd(MoudleVO moudleVO) throws ThinventBaseException;
	
	public Map<String, String> buttomRegisterUpdate(MoudleVO moudleVO) throws ThinventBaseException;
	
	public String findMaxButtomRegisterId();

}
