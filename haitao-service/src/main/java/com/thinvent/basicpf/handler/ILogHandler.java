package com.thinvent.basicpf.handler;

import java.util.Date;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.LogVO;

public interface ILogHandler {

	void saveLog(LogVO logVO) throws ThinventBaseException;

	Map listLogByConditions(Pageable pageable, String logType, String userName, String system, Date maxDate, Date minDate) throws ThinventBaseException;

}
