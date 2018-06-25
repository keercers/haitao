package com.thinvent.basicpf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.thinvent.basicpf.handler.IRoleHandler;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.RoleVO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "role")
public class RoleController {
	@Autowired
	private IRoleHandler roleHander;

	/**
	 * 
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/getRoleByRoleId", method = RequestMethod.GET)
	@ApiOperation(value = "角色--角色查询", notes = "根据角色ID查询角色")
	@ApiImplicitParam(name = "roleId", required = true, value = "角色ID", dataType = "string", paramType = "query")
	@ResponseBody
	public Object getRoleByRoleId(@RequestParam(value = "roleId", required = true) String roleId) {
		return this.roleHander.findRoleByRoleId(roleId);
	}

	/**
	 * @author lql
	 * @param roleVO
	 * @return
	 * @throws ThinventBaseException
	 */

	@RequestMapping(value = "/updateRole", method = RequestMethod.POST)
	@ApiOperation(value = "角色--角色修改", notes = "修改角色")
	@ApiImplicitParam(name = "roleVO", required = true, value = "角色", dataType = "RoleVO")
	@ResponseBody
	public void updateRole(@RequestBody RoleVO roleVO) throws ThinventBaseException {
		this.roleHander.update(roleVO);
	}

	/**
	 * @author lql
	 * @param roleVO
	 * @return
	 */
	@RequestMapping(value = "/addRole", method = RequestMethod.POST)
	@ApiOperation(value = "角色--角色增加", notes = "保存新角色")
	@ApiImplicitParam(name = "roleVO", required = true, value = "角色", dataType = "RoleVO")
	@ResponseBody
	public void addRole(@Valid @RequestBody RoleVO roleVO) throws ThinventBaseException {
		this.roleHander.addRole(roleVO);
	}

	/**
	 * 
	 * 
	 * @param roleId
	 * @return
	 * @throws ThinventBaseException
	 */
	@GetMapping(value = "/deleteRole")
	@ApiOperation(value = "角色--角色删除", notes = "角色删除")
	@ApiImplicitParam(name = "roleId", required = true, value = "roleId", dataType = "string",paramType = "query")
    public void deleteRole(@RequestParam (value = "roleId", required = true) String roleId) throws ThinventBaseException {
		this.roleHander.delete(roleId);
	}

	@GetMapping(value = "/getRoleList")
	@ApiOperation(value = "角色--角色列表查询", notes = "角色列表查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "roleName", required = false, value = "角色名称", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "roleType", required = false, value = "角色类型", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "pageIndex", required = true, value = "页数", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "pageSize", required = true, value = "每页显示条数", dataType = "int", paramType = "query") })
	public Object list(@RequestParam(value = "roleName", required = false) String roleName,
			@RequestParam(value = "roleType", required = false) String roleType,
			@RequestParam(value = "pageIndex", required = true) Integer pageIndex,
			@RequestParam(value = "pageSize", required = true) Integer pageSize) throws ThinventBaseException {
		Pageable pageable = new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "updateTime"));
		return this.roleHander.listAllRoleByCondition(pageable, roleName, roleType);
	}

	@GetMapping("/findAllRole")
	public List findAllRole() throws ThinventBaseException {
		return this.roleHander.findAllRole();
	}

	/**
	 * 
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/findAllRoleByUserId", method = RequestMethod.GET)
	@ApiOperation(value = "角色--角色查询", notes = "根据用户ID查询角色")
	@ApiImplicitParam(name = "userId", required = true, value = "用户ID", dataType = "String", paramType = "query")
	@ResponseBody
	public List<RoleVO> findAllRoleByUserId(@RequestParam(value = "userId", required = true) String userId) {
		return roleHander.findAllRoleByUserId(userId);
	}
}
