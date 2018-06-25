package com.thinvent.basicpf.util;
import com.thinvent.basicpf.model.Config;
import com.thinvent.library.dim.TvtExceptionEnum;
import com.thinvent.library.exception.DataNotExistException;
import com.thinvent.library.exception.ThinventBaseException;

public class ConfigVerifyUtil {
	
    private ConfigVerifyUtil(){}
    
    public static void verifyConf(Config config) throws ThinventBaseException {
        if (config == null)
            throw new DataNotExistException(TvtExceptionEnum.DATA_NOTEXSIT.getIndex(), TvtExceptionEnum.DATA_NOTEXSIT.getName());
    }
    
    public static void verifyAddConf(Config config) throws ThinventBaseException {
        if (config == null)
        	throw new DataNotExistException(TvtExceptionEnum.DATA_NOTEXSIT.getIndex(), TvtExceptionEnum.DATA_NOTEXSIT.getName());
    }

    public static void verifyUpdateConfResult(Config oldConfig, Config newConfig) throws ThinventBaseException {
        if (oldConfig == null || newConfig == null)
        	throw new DataNotExistException(TvtExceptionEnum.DATA_NOTEXSIT.getIndex(), TvtExceptionEnum.DATA_NOTEXSIT.getName());
    }
}
