package com.thinvent.basicpf.handler;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.UserVO;

public interface IUserHandler {
	
	public UserVO findUser(String loginName) throws ThinventBaseException;
	public UserVO findUserByUserId(String userId) throws ThinventBaseException;
	public void save(UserVO userVO);
	public void updateUserAllInfo(UserVO userVO) throws ThinventBaseException;
	public void updateUser(UserVO userVO) throws ThinventBaseException;
	
	public Map listAllUserByCondition(Pageable pageable, List<String> deptIds, 
			List<String> posIds, String loginName, String userName);
	public Map listAllUser();
	public void deleteUser(String userId) throws ThinventBaseException;
	public void saveUserList(List<UserVO> uvoList) throws ThinventBaseException;
	public List<UserVO> getUsersByDepId(String depId) throws ThinventBaseException;
}
