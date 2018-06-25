package com.thinvent.basicpf.handler;

import java.util.Map;

import com.thinvent.basicpf.model.Moudle;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.MoudleVO;

public interface ISysRegisterHandler {

	public Object sysRegisterList(String moudelName, int pageIndex, int pageSize);

	public void sysRegisterDel(String moudleId) throws ThinventBaseException;

	public Moudle sysRegisterOne(String moudleId);
	
	public String sysRegisterAdd(MoudleVO moudleVO) throws ThinventBaseException;
	
	public Map<String, String> sysRegisterUpdate(MoudleVO moudleVO) throws ThinventBaseException;
	
	public String findMaxSysRegisterId();

}
