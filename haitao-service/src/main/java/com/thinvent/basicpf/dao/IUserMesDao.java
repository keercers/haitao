package com.thinvent.basicpf.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.thinvent.basicpf.model.User;
import com.thinvent.basicpf.model.UserMes;

public interface IUserMesDao extends CrudRepository<User, Integer> {
	/**
	 * 保存UserMes实体
	 * @param userMes
	 * @return
	 */
	public UserMes save(UserMes userMes);
	
	/**
	 * 根据userId 查询 user_mes
	 * @param userId
	 * @return
	 */
	@Query("from UserMes a where a.userId = ?1")
	public UserMes getUserMesByUserId(String userId);
	
	/**
	 * 根据身份证号查询用户
	 * @param certificateNum
	 * @return
	 */
	@Query("from UserMes a where a.certificateNum = ?1")
	public UserMes getUserMesByCertificateNum(String certificateNum);
}
