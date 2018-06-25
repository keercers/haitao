package com.thinvent.basicpf.handler.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.thinvent.basicpf.dao.ILogDao;
import com.thinvent.basicpf.dao.jpa.LogDaoRepositoryImpl;
import com.thinvent.basicpf.handler.ILogHandler;
import com.thinvent.basicpf.model.Log;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.LogVO;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class LogHandlerImpl implements ILogHandler {

	@Autowired
	private ILogDao logDao;
	
	@Autowired
	private LogDaoRepositoryImpl logDaoRepository;
	
	@Override
	public void saveLog(LogVO logVO) throws ThinventBaseException{
		Log log = new Log();
		BeanUtils.copyProperties(logVO, log);
		log.setId(0);
		this.logDao.save(log);
	}

	@Override
	public Map listLogByConditions(Pageable pageable, String logType, String userName, String system,
			Date maxDate, Date minDate) throws ThinventBaseException {
		return this.logDaoRepository.listLogByConditions(pageable, logType, userName, system, maxDate, minDate);
	}
}
