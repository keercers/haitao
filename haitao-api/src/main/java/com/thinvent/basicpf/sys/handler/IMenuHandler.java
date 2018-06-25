package com.thinvent.basicpf.sys.handler;

import java.util.List;
import java.util.Map;

import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.MoudleVO;

public interface IMenuHandler {
	
	public List<MoudleVO> menuAll()throws ThinventBaseException;
	public Object menuList(String moudelName, int pageIndex, int pageSize)throws ThinventBaseException;
	public void menuDel(String moudleId) throws ThinventBaseException;
	public String menuOne(String moudleId) throws ThinventBaseException;
	public Map<String, String> menuAdd(MoudleVO moudle) throws ThinventBaseException;
	public Map<String, String> menuUpdate(MoudleVO moudle)throws ThinventBaseException;

}
