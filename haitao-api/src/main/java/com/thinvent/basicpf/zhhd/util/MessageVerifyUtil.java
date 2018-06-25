package com.thinvent.basicpf.zhhd.util;

import com.thinvent.library.dim.TvtExceptionEnum;
import com.thinvent.library.exception.DataNotExistException;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.zhhd.common.vo.MessageVO;

public class MessageVerifyUtil {
	private MessageVerifyUtil() {
	}

	public static void verifyMessageVO(MessageVO messageVO) throws ThinventBaseException {
		if (messageVO == null) {
			throw new DataNotExistException(TvtExceptionEnum.DATA_NOTEXSIT.getIndex(),
					TvtExceptionEnum.DATA_NOTEXSIT.getName());
		}
	}
}
