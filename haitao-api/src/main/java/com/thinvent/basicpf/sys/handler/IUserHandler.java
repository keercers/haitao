package com.thinvent.basicpf.sys.handler;

import java.util.List;
import java.util.Map;

import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.UserVO;

public interface IUserHandler {
	
	/**
	 * 根据用户名称查找userId
	 * @author tsm
	 * @param loginName
	 */
	public Map<String, Object> findUserInfoByLoginName(String loginName) throws ThinventBaseException;
	
	public Map<String, Object> checkUserByLoginName(String loginName, String password) throws ThinventBaseException;
	public Map logOut();
	public Map changePassword(String userId, String oldPassword, String newPassword) throws ThinventBaseException;
	public String getUserIdFromSession();
	public Map getUserInfo(String userId) throws ThinventBaseException;

	public Map listUserByDepartmentId(String departmentId, int pageSize, int pageIndex) throws ThinventBaseException;
	public void deleteUser(String userId) throws ThinventBaseException;
	public void saveUser(UserVO userVO) throws ThinventBaseException;
	public Map<String, Object> importUser(List<UserVO> list) throws ThinventBaseException;
	public void updateUser(UserVO userVO) throws ThinventBaseException;
	public Map listUserByCondition(String deptId, String posName, String loginName, String username, int pageSize, int pageIndex, int isAll) throws ThinventBaseException;
	public Map listAllUser() throws ThinventBaseException;
	public void resetPassword(String userId, String newPwd) throws ThinventBaseException;
}
