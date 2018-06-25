package com.thinvent.basicpf.zhhd.util;

import com.thinvent.library.dim.TvtExceptionEnum;
import com.thinvent.library.exception.DataNotExistException;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.zhhd.common.vo.DictionaryItemVO;

public class DictionaryItemVerifyUtil {

	private DictionaryItemVerifyUtil() {
	}

	public static void verifySysDictionaryItem(DictionaryItemVO sysDictionaryItem) throws ThinventBaseException {
		if (sysDictionaryItem == null) {
			throw new DataNotExistException(TvtExceptionEnum.DATA_NOTEXSIT.getIndex(),
					TvtExceptionEnum.DATA_NOTEXSIT.getName());
		}
	}
}
