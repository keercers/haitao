package com.thinvent.basicpf.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.thinvent.basicpf.model.Moudle;

public interface ISysRegisterDao extends CrudRepository<Moudle, Integer> {
	/**
	 * 根据等级查询菜单
	 * @param moudleLevel 等级
	 * @param enable 是否有效
	 * @return
	 */
	
	public List<Moudle> findAll();
	
	@SuppressWarnings("unchecked")
	public Moudle save(Moudle moudle);
	
	public Moudle findByMoudleIdAndEnable(String moudleId, int enable);

	public Page<Moudle> queryByMoudleNameLikeAndMoudleTypeAndEnable(String moudelName, String moudleType, int enable, Pageable page);

	public List<Moudle> findByMoudleParentIdAndEnable(String moudleId, int i);
	
	
}
