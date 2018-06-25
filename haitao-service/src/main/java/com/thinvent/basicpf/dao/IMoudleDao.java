package com.thinvent.basicpf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.thinvent.basicpf.model.Moudle;

public interface IMoudleDao extends CrudRepository<Moudle, Integer> {
	/**
	 * 根据等级查询菜单
	 * @param moudleLevel 等级
	 * @param enable 是否有效
	 * @return
	 */
	
	public List<Moudle> findByEnable(int enable);
	
	public Moudle findByMoudleIdAndEnable(String moudleId, int enable);

	public Moudle findByMoudleLevelAndMoudleIdAndEnable(String moudleLevel, String moudleId, int enable);

	public List<Moudle> findByMoudleSignLikeAndMoudleIdInAndEnable(String string, List<String> list, int enable);

	public Moudle findByMoudleIdAndMoudleTypeAndEnable(String moudleId, String moudleType, int enable);

	public List<Moudle> findByMoudleTypeAndEnable(String string, int i);

	public List<Moudle> findByMoudleLevelAndMoudleIdInAndEnable(String moudleLevel, List<String> listMoudleId, int enable);
	
	@Query(value="select distinct m from Moudle m, RoleMoudle rm, UserRole ur where ur.userId = ?1 and ur.enable = '1' and ur.roleId = rm.roleId and rm.enable = '1' and rm.moudelId = m.moudleId and m.enable = '1' and m.moudleType = '3'")
	public List<Moudle> getAccesses(String userId);
	
	@Query(value="select distinct m from Moudle m, RoleMoudle rm, UserRole ur where ur.userId = ?1 and ur.enable = '1' and ur.roleId = rm.roleId and rm.enable = '1' and rm.moudelId = m.moudleId and m.enable = '1'")
	public List<Moudle> getMoudleByUserId(String userId);
}
