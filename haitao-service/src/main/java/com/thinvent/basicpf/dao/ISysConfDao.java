package com.thinvent.basicpf.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.thinvent.basicpf.model.Config;

public interface ISysConfDao extends PagingAndSortingRepository<Config, Integer> ,CrudRepository<Config, Integer>{
	
	Page<Config> findByConfTypeAndEnable(int confType, int enable, Pageable pageable);

	Config findOneByConfIdAndEnable(String confId, int enable);

	Page<Config> findByEnable(int enable, Pageable pageable);

	Config findOneByConfKeyAndEnable(String confKey, int i);

}
