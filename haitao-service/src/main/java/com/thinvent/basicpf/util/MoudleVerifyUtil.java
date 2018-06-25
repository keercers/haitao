package com.thinvent.basicpf.util;

import java.util.List;

import com.thinvent.basicpf.model.Moudle;
import com.thinvent.library.dim.TvtExceptionEnum;
import com.thinvent.library.exception.DataNotExistException;
import com.thinvent.library.exception.DataVerException;
import com.thinvent.library.exception.ThinventBaseException;

public class MoudleVerifyUtil {

	private MoudleVerifyUtil(){}

	public static void verifyMoudle(Moudle moudle) throws ThinventBaseException{
		if(moudle == null)
			throw new DataNotExistException(TvtExceptionEnum.DATA_NOTEXSIT.getIndex(), TvtExceptionEnum.DATA_NOTEXSIT.getName());
		if(moudle.getMoudleSign() == null)
		{
			moudle.setMoudleSign("");
		}
	}
	public static void verifyMoudleRepeat(Moudle moudle) throws ThinventBaseException{
		if(moudle != null) {
			throw new DataNotExistException(TvtExceptionEnum.DATA_REPETITION.getIndex(), TvtExceptionEnum.DATA_REPETITION.getName());
		}
	}

	public static void verifyMoudleParent(Moudle moudle) throws ThinventBaseException{
		if(moudle == null)
			throw new DataVerException(TvtExceptionEnum.DATA_VER_MOU_PARENT_NOTEXSIT.getIndex(), TvtExceptionEnum.DATA_VER_MOU_PARENT_NOTEXSIT.getName());
	}
	
	public static void verifyListChilds(List<Moudle> list) throws ThinventBaseException{
		if(!list.isEmpty())
			throw new DataVerException(TvtExceptionEnum.DATA_VER_CHILD_EXSIT.getIndex(), TvtExceptionEnum.DATA_VER_CHILD_EXSIT.getName());
	}
	
}
