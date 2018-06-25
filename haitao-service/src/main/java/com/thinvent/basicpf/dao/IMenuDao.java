package com.thinvent.basicpf.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.thinvent.basicpf.model.Moudle;

public interface IMenuDao extends CrudRepository<Moudle, Integer> {

	public List<Moudle> findByMoudleSignLikeAndEnable(String moudleSign, int enable);
	
	public List<Moudle> findByEnable(int enable);
	
	public List<Moudle> findAll();
	
	@SuppressWarnings("unchecked")
	public Moudle save(Moudle moudle);
	
	public Moudle findByMoudleIdAndEnable(String moudleId, int enable);

	public Page<Moudle> queryByMoudleNameLikeAndMoudleTypeAndEnable(String moudelName, String moudleType, int enable, Pageable page);

	public List<Moudle> findByMoudleParentIdAndEnable(String moudleId, int i);

	public List<Moudle> findByEnableAndMoudleTypeIn(int enable, List<String> moudleTypes);

	public Moudle findByMoudleNameAndEnable(String moudleName, int enable);
}
