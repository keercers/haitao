package com.thinvent.basicpf.handler.impl;


import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.thinvent.basicpf.dao.ISysConfDao;
import com.thinvent.basicpf.handler.ISysConfHandler;
import com.thinvent.basicpf.model.Config;
import com.thinvent.basicpf.util.ConfigVerifyUtil;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.ConfigVO;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class SysConfHandlerImpl implements ISysConfHandler {
	
	@Autowired
	private ISysConfDao sysConfDao;
	
	@Override
	public Page<Config> findSysParam(int confType, Pageable pageable) {
		if (10 == confType) {
			return this.sysConfDao.findByEnable(1, pageable);
		} else
		return this.sysConfDao.findByConfTypeAndEnable(confType, 1, pageable);
	}

	@Override
	public void saveSysParam(ConfigVO configVO) {
		Config config = new Config();
		BeanUtils.copyProperties(configVO, config);
		config.setId(0);
		this.sysConfDao.save(config);
	}

	@Override
	public void updateSysParam(ConfigVO configVO) throws ThinventBaseException {
		Config config = new Config();
		BeanUtils.copyProperties(configVO, config);
		Config oldConfig = sysConfDao.findOneByConfIdAndEnable(config.getConfId(), 1);
		Date createTime = oldConfig.getCreateTime();
		ConfigVerifyUtil.verifyConf(oldConfig);
		config.setCreateTime(createTime);
        sysConfDao.save(config);
	}

	@Override
	public void deleteSysParam(String confId) throws ThinventBaseException {
		Config config = sysConfDao.findOneByConfIdAndEnable(confId, 1);
		ConfigVerifyUtil.verifyConf(config);
        config.setEnable(0);
        sysConfDao.save(config);
	}

	@Override
	public Config findByConfId(String confId) {
		return this.sysConfDao.findOneByConfIdAndEnable(confId, 1);
	}

	@Override
	public Config findConfigByConfKey(String confKey) throws ThinventBaseException {
		return this.sysConfDao.findOneByConfKeyAndEnable(confKey, 1);
	}

}
