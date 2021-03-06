package com.thinvent.basicpf.sys.adapt;

import java.util.Map;

import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.MoudleVO;

public interface ISysRegisterAdapt {
	public Object sysRegisterList(String moudelName, int pageIndex, int pageSize)throws ThinventBaseException;
	public void sysRegisterDel(String moudleId) throws ThinventBaseException;
	public String sysRegisterOne(String moudleId) throws ThinventBaseException;
	public String sysRegisterAdd(MoudleVO moudle)throws ThinventBaseException;
	public Map<String, String> sysRegisterUpdate(MoudleVO moudle)throws ThinventBaseException;

}
