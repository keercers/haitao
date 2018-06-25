package com.thinvent.basicpf.sys.handler;

import java.util.Map;

import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.MoudleVO;

public interface IButtomRegisterHandler {

	public Object buttomRegisterList(String moudelId, String moudelName, int pageIndex, int pageSize)throws ThinventBaseException;
	public void buttomRegisterDel(String moudleId) throws ThinventBaseException;
	public String buttomRegisterOne(String moudleId) throws ThinventBaseException;
	public Map<String, String> buttomRegisterAdd(MoudleVO moudle) throws ThinventBaseException;
	public Map<String, String> buttomRegisterUpdate(MoudleVO moudle)throws ThinventBaseException;

}
