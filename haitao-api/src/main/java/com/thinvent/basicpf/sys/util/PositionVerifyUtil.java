package com.thinvent.basicpf.sys.util;

import com.thinvent.library.dim.TvtExceptionEnum;
import com.thinvent.library.exception.DataNotExistException;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.PositionVO;

/**
 * Created by KYe on 2017/6/9.
 */
public class PositionVerifyUtil {

    private PositionVerifyUtil() {
    }

    public static void verifyPos(PositionVO position) throws ThinventBaseException {
        if (position == null)
            throw new DataNotExistException(TvtExceptionEnum.DATA_NOTEXSIT.getIndex(), TvtExceptionEnum.DATA_NOTEXSIT.getName());
    }
}


