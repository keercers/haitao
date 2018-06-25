package com.thinvent.basicpf.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.thinvent.basicpf.model.Log;

public interface ILogDao extends PagingAndSortingRepository<Log, Integer> ,CrudRepository<Log, Integer>{
	
	
}
