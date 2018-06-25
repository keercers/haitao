package com.thinvent.basicpf.handler;

import java.util.List;
import java.util.Map;

import com.thinvent.basicpf.model.Moudle;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.MoudleVO;

public interface IMenuHandler {
	
	public List<Moudle> menuAll();

	public Object menuList(String moudelName, int pageIndex, int pageSize);

	public void menuDel(String moudleId) throws ThinventBaseException;

	public Moudle menuOne(String moudleId);
	
	public String menuAdd(MoudleVO moudleVO) throws ThinventBaseException;
	
	public Map<String, String> menuUpdate(MoudleVO moudleVO) throws ThinventBaseException;
	
	public String findMaxMenuId();

}
