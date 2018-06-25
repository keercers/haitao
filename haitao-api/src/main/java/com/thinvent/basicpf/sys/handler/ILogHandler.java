package com.thinvent.basicpf.sys.handler;

import java.util.Map;

import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.LogVO;

public interface ILogHandler {

	void saveLog(LogVO logVO) throws ThinventBaseException;

	Map listLogByParams(String logType, String userName, String system, String maxDate, String minDate, int pageSize, int pageIndex) throws ThinventBaseException;

}
