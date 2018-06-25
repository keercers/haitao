package com.thinvent.basicpf.sys.util;

import com.thinvent.library.Constants;
import com.thinvent.library.dim.TvtExceptionEnum;
import com.thinvent.library.exception.DataNotExistException;
import com.thinvent.library.exception.DataVerException;
import com.thinvent.library.exception.OptionException;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.ConfigVO;

public class ConfigVerifyUtil {
    
    private ConfigVerifyUtil() {}
    
    public static void verifyConf(ConfigVO config) throws ThinventBaseException {
        if (config == null)
        	throw new DataNotExistException(TvtExceptionEnum.DATA_NOTEXSIT.getIndex(), TvtExceptionEnum.DATA_NOTEXSIT.getName());
    }

    public static void verifyAddConfResult(ConfigVO config) throws ThinventBaseException {
        if (config == null)
        	throw new DataNotExistException(TvtExceptionEnum.DATA_NOTEXSIT.getIndex(), TvtExceptionEnum.DATA_NOTEXSIT.getName());
    }

    public static void verifyUpdateConfResult(ConfigVO oldConfig, ConfigVO newConfig) throws ThinventBaseException {
        if (oldConfig == null || newConfig == null)
        	throw new DataNotExistException(TvtExceptionEnum.DATA_NOTEXSIT.getIndex(), TvtExceptionEnum.DATA_NOTEXSIT.getName());
    }

    public static void verifyDeleteConfResult(String code) throws ThinventBaseException {
        if (!Constants.RES_SUCCESS.equals(code))
        	throw new OptionException(TvtExceptionEnum.OPT_DEL_FAULSE.getIndex(), TvtExceptionEnum.OPT_DEL_FAULSE.getName());
    }
    
    public static void verifyConfKey(ConfigVO config) throws ThinventBaseException {
        if (config != null) {
        	throw new DataVerException(TvtExceptionEnum.DATA_VER_CONFIG_EXSIT.getIndex(), TvtExceptionEnum.DATA_VER_CONFIG_EXSIT.getName());
        }
    }
}
