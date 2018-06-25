package com.thinvent.basicpf.sys.adapt;

import java.util.List;
import java.util.Map;

import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.RoleVO;
import com.thinvent.library.vo.UserRoleVO;
import com.thinvent.library.vo.UserVO;

public interface IUserAdapt {
	
	public UserVO findUser(String loginName) throws ThinventBaseException;
	public UserVO findUserByUserId(String userId) throws ThinventBaseException;
	public Map listAllUser() throws ThinventBaseException;
	
	public void saveUser(UserVO userVO) throws ThinventBaseException;
	public void updateUser(UserVO userVO) throws ThinventBaseException;
	public void updateUserAllInfo(UserVO userVO) throws ThinventBaseException;
	public List<UserRoleVO> getUserRoleByUserId(String userId) throws ThinventBaseException;
	public RoleVO getRoleByRoleId(String roleId) throws ThinventBaseException;
	
	public List<UserVO> listUserByDeptIds(List<String> deptIds, int pageSize, int pageIndex) throws ThinventBaseException;
	public Map listUserByConditions(List<String> deptIds, List<String> posIds, 
			String loginName, String userName, int pageSize, int pageIndex, int isAll) throws ThinventBaseException;
	public UserVO findUserByLoginName(String loginName) throws ThinventBaseException;
	public void resetPassword(String userId) throws ThinventBaseException;
	public void deleteUser(String userId) throws ThinventBaseException;
	public void saveUser(List<UserVO> uvoList) throws ThinventBaseException;
	public List<UserVO> findUsersByDepId(String depId) throws ThinventBaseException;
}
