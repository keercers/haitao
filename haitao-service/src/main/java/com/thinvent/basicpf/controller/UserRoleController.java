package com.thinvent.basicpf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.thinvent.basicpf.handler.IUserRoleHandler;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.UserRoleVO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "userRole")
public class UserRoleController {
	@Autowired
	private IUserRoleHandler userRoleHandler;
	
	/**
	 * 保存用户角色信息
	 * @author wcs
	 * @param userRoleVO
	 * @return
	 */
	@RequestMapping(value = "/saveUserRole", method = RequestMethod.POST)
	@ApiOperation(value = "用户--用户保存", notes = "保存用户角色信息")
	@ApiImplicitParam(name = "userRoleVO", required = true, value = "用户角色", dataType = "string", paramType = "query")
	@ResponseBody
	public void saveUserRole(@RequestBody UserRoleVO userRoleVO) {
		userRoleHandler.save(userRoleVO);
	}
	
	/**
	 * 更新用户角色信息
	 * @author wcs
	 * @param userRoleVO
	 * @return
	 */
	@RequestMapping(value = "/updateUserRole", method = RequestMethod.POST)
	@ApiOperation(value = "用户--用户更新", notes = "更新用户角色信息")
	@ApiImplicitParam(name = "userRoleVO", required = true, value = "用户角色", dataType = "string", paramType = "query")
	@ResponseBody
	public void updateUserRole(@RequestBody UserRoleVO userRoleVO) {
		userRoleHandler.save(userRoleVO);
	}
	
	/**
	 * 查询用户角色信息
	 * @author wcs
	 * @param userId
	 * @return
	 * @throws ThinventBaseException 
	 */
	@RequestMapping(value = "/getUserRoleByUserId", method = RequestMethod.GET)
	@ApiOperation(value = "用户--用户保存", notes = "保存用户角色信息")
	@ApiImplicitParam(name = "userRoleVO", required = true, value = "用户角色", dataType = "string", paramType = "query")
	@ResponseBody
	public Object getUserRoleByUserId(@RequestParam(value = "userId", required = true) String userId) throws ThinventBaseException {
		return userRoleHandler.getUserRoleListByUserId(userId);
	}

	/**
	 * 根据角色Id查询用户角色信息
	 * @author lql
	 * @param roleId
	 * @return
	 * @throws ThinventBaseException
	 */
	@RequestMapping(value = "/getUserRoleByRoleId", method = RequestMethod.GET)
	@ApiOperation(value = "用户--用户保存", notes = "保存用户角色信息")
	@ApiImplicitParam(name = "userRoleVO", required = true, value = "用户角色", dataType = "string", paramType = "query")
	@ResponseBody
	public Object getUserRoleByRoleId(@RequestParam(value = "roleId", required = true) String roleId) throws ThinventBaseException {
		return userRoleHandler.getUserRoleListByRoleId(roleId);
	}

	
	@RequestMapping(value = "/queryUserIdsByRoleIds", method = RequestMethod.GET)
	public List<String> queryUserIdsByRoleIds(@RequestParam(value = "roleIds", required = true) String roleIds) throws ThinventBaseException {
		return userRoleHandler.queryUserIdsByRoleIds(roleIds);
	}
}
