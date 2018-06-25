package com.thinvent.basicpf.util;

import com.thinvent.basicpf.model.Position;
import com.thinvent.library.dim.TvtExceptionEnum;
import com.thinvent.library.exception.DataNotExistException;
import com.thinvent.library.exception.OptionException;
import com.thinvent.library.exception.ThinventBaseException;

/**
 * Created by KYe on 2017/6/12.
 */
public class PositionVerifyUtil {

    private PositionVerifyUtil() {
    }

    public static void verifyPos(Position position) throws ThinventBaseException {
        if (position == null)
            throw new DataNotExistException(TvtExceptionEnum.DATA_NOTEXSIT.getIndex(), TvtExceptionEnum.DATA_NOTEXSIT.getName());
    }
    
    public static void verifyDeletePosResult(Boolean existsUser) throws ThinventBaseException {
		if (existsUser) {
			throw new OptionException(TvtExceptionEnum.DATA_VER_POS_USER_CHILD_EXSIT.getIndex(), TvtExceptionEnum.DATA_VER_POS_USER_CHILD_EXSIT.getName());
		}
	}
    
    public static void verifyExistPos(Position pos) throws ThinventBaseException {
		if (pos != null) {
			throw new ThinventBaseException(TvtExceptionEnum.DATA_VER_POS_EXIST.getIndex(), TvtExceptionEnum.DATA_VER_POS_EXIST.getName());
		}
	}
}