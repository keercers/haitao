package com.thinvent.basicpf.sys.util;

import com.thinvent.library.Constants;
import com.thinvent.library.dim.TvtExceptionEnum;
import com.thinvent.library.exception.OptionException;
import com.thinvent.library.exception.ThinventBaseException;

public class CompanyVerifyUtil {

	private CompanyVerifyUtil(){}

	public static void verifyUpdate(String state) throws ThinventBaseException{
		if(!Constants.RES_SUCCESS.equals(state)){
			throw new OptionException(TvtExceptionEnum.OPT_SAVE_FAULSE.getIndex(), TvtExceptionEnum.OPT_SAVE_FAULSE.getName());
		}
	}
	
	public static void verifyObject(Object object) throws ThinventBaseException{
		if(object == null){
			throw new OptionException(TvtExceptionEnum.OPT_QUERY_FAULSE.getIndex(), TvtExceptionEnum.OPT_QUERY_FAULSE.getName());
		}
	}
}
