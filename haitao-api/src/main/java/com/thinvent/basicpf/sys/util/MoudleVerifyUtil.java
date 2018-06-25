package com.thinvent.basicpf.sys.util;

import com.thinvent.library.Constants;
import com.thinvent.library.dim.TvtExceptionEnum;
import com.thinvent.library.exception.OptionException;
import com.thinvent.library.exception.ThinventBaseException;

public class MoudleVerifyUtil {
    
    private MoudleVerifyUtil(){}
    
    public static void httpVerify(String status) throws ThinventBaseException{
    	if(!Constants.RES_SUCCESS.equals(status))
    		throw new OptionException(TvtExceptionEnum.OPT_SAVE_FAULSE.getIndex(), TvtExceptionEnum.OPT_SAVE_FAULSE.getName());
    }
    
}
