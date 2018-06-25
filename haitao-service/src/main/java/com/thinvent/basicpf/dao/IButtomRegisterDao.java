package com.thinvent.basicpf.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.thinvent.basicpf.model.Moudle;

public interface IButtomRegisterDao extends CrudRepository<Moudle, Integer> {
	/**
	 * 根据等级查询菜单
	 * @param moudleLevel 等级
	 * @param enable 是否有效
	 * @return
	 */
	public Page<Moudle> queryByMoudleParentIdAndMoudleNameLikeAndMoudleTypeAndEnable(String moudelId, String moudelName, String moudelType, int enable, Pageable page);
	
	@SuppressWarnings("unchecked")
	public Moudle save(Moudle moudle);
	
	public List<Moudle> findAll();
	
	public List<Moudle> findByMoudleParentIdAndEnable(String moudleId, int i);
	
	public Moudle findByMoudleIdAndEnable(String moudleId, int enable);
	
	public List<Moudle> findByMoudleSignLikeAndEnableOrderByMoudleLevel(String moudleSign, int enable);
}
