package com.thinvent.basicpf.zhhd.util;

import com.thinvent.library.dim.TvtExceptionEnum;
import com.thinvent.library.exception.DataNotExistException;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.zhhd.common.vo.DrillVO;

public class DrillVerifyUtil {

	private DrillVerifyUtil() {
	}

	public static void verifyDrill(DrillVO drill) throws ThinventBaseException {
		if (drill == null) {
			throw new DataNotExistException(TvtExceptionEnum.DATA_NOTEXSIT.getIndex(),
					TvtExceptionEnum.DATA_NOTEXSIT.getName());
		}
	}
}
