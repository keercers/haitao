package com.thinvent.basicpf.sys.util;

import com.thinvent.library.dim.TvtExceptionEnum;
import com.thinvent.library.exception.DataNotExistException;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.DepartmentVO;

public class DepartmentVerifyUtil {

    private DepartmentVerifyUtil () {}

    public static void verifyDept(DepartmentVO department) throws ThinventBaseException {
        if (department == null) {
        	throw new DataNotExistException(TvtExceptionEnum.DATA_NOTEXSIT.getIndex(), TvtExceptionEnum.DATA_NOTEXSIT.getName());
        }
    }

}
