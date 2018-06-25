package com.thinvent.basicpf.dao;

import java.util.List;

import org.hibernate.criterion.CriteriaQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.thinvent.basicpf.model.User;

public interface IUserDao extends CrudRepository<User, Integer> {
	/**
	 * 根据用户登录名查询相关信息
	 * @update by xiaoyu UserName唯一性
	 * @param loginId
	 * @return
	 */
	@Query("from User a  where a.loginName=?1 and a.enable = 1")
	public User findUser(String loginName);
	/**
	 * 根据UserId 查询信息
	 * @param userId
	 * @return
	 */
	@Query("from User a  where a.userId=?1")
	public User findUserByUserId(String userId);
	
	/**
	 * 根据userName 查询信息
	 * @param userName
	 * @return
	 */
	@Query("from User a  where a.userName like %?1%  and a.enable = 1")
	public List<User> findUserByUserName(String userName);
	
	public User save(User user);
	
	/**
	 * 根据子部门编号查询部门人员
	 * @param departmentId
	 * @param pageable
	 * @return
	 */
	@Query("from User a where a.depId = ?1 and a.enable = 1")
	public List<User> listUserByChildDepartmentId(String departmentId, Pageable pageable);
	
	/**
	 * 根据子部门编号查询部门人员
	 * @param deptIds
	 * @param pageable
	 * @return
	 */
	@Query("from User a where a.enable = 1 and a.depId in ?1")
	public List<User> listUserByDeptIds(List<String> deptIds, Pageable pageable);
	
	/**
	 * 根据岗位编号查询用户
	 * @param positionId
	 * @return
	 */
	@Query("from User a where a.posId = ?1 and a.enable = 1")
	public List<User> listUserByPositionId(String positionId, Pageable pageable);
	
	
	@Query("from User a where a.enable = 1")
	public List<User> listAllUser(Pageable pageable);
	
	@Query("from User a where a.enable = 1")
	public List<User> listAllUserByCondition(Pageable pageable, CriteriaQuery queryCondition);
	
	@Query("from User a where a.enable = 1")
	public List<User> listAllUser();
	
	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.posId = ?1 AND u.enable = 1")
    Boolean existsByPosIdAndEnable(String posId);
	
	public List<User> findByDepIdAndEnable(String depId, int enable);
}
