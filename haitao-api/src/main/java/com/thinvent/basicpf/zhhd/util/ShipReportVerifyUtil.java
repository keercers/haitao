package com.thinvent.basicpf.zhhd.util;

import com.thinvent.library.dim.TvtExceptionEnum;
import com.thinvent.library.exception.DataNotExistException;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.zhhd.common.vo.ShipReportVo;

public class ShipReportVerifyUtil {

	private ShipReportVerifyUtil() {
	}

	public static void verifyShipReport(ShipReportVo shipReport) throws ThinventBaseException {
		if (shipReport == null) {
			throw new DataNotExistException(TvtExceptionEnum.DATA_NOTEXSIT.getIndex(),
					TvtExceptionEnum.DATA_NOTEXSIT.getName());
		}
	}
}
