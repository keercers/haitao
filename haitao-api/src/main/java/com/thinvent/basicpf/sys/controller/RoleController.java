package com.thinvent.basicpf.sys.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thinvent.library.Constants;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.JWTConfig;
import com.thinvent.library.vo.RoleVO;
import com.thinvent.basicpf.sys.handler.IRoleHander;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "sys/role")
public class RoleController {

	@Autowired
	IRoleHander roleHander;

	@GetMapping(value = "/getByRoleId")
	@ApiOperation(value = "角色--角色查询", notes = "角色查询")
	@ApiImplicitParam(name = "roleId", required = true, value = "角色ID", dataType = "string", paramType = "query")
	public Object get(@RequestParam(value = "roleId", required = true) String roleId) throws ThinventBaseException {
		return this.roleHander.findRoleByRoleId(roleId);
	}

	@GetMapping(value = "/getRoleList")
	@ApiOperation(value = "角色--角色列表查询", notes = "角色列表查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "roleName", required = false, value = "角色名称", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "roleType", required = false, value = "角色类型", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "pageIndex", required = true, value = "页数", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "pageSize", required = true, value = "每页显示条数", dataType = "int", paramType = "query") })
	public Map<String,Object> list(@RequestParam(value = "roleName", required = false) String roleName,
								   @RequestParam(value = "roleType", required = false) String roleType,
								   @RequestParam(value = "pageIndex", required = true) Integer pageIndex,
								   @RequestParam(value = "pageSize", required = true) Integer pageSize) throws ThinventBaseException {
		return this.roleHander.getRoleList(roleName, roleType, pageIndex, pageSize);
	}

	@PostMapping(value = "/addRole")
	@ApiOperation(value = "角色--角色保存", notes = "角色保存")
	@ApiImplicitParam(name = "roleVO", required = true, value = "角色", dataType = "RoleVO")
	public void add(@Valid @RequestBody RoleVO roleVO, HttpServletRequest request) throws ThinventBaseException {
		String userId = "";
		try {
			userId = JWTConfig.getUserByToken(request.getHeader(Constants.TOKEN_KEY)).getUserId();
		} catch (Exception e) {
			log.error("role addRole can not find userId(unlegal login): ", e);
		}
		roleVO.setCreateUser(userId);
		roleVO.setUpdateUser(userId);
		this.roleHander.addRole(roleVO);
	}

	@PostMapping(value = "/updateRole")
	@ApiOperation(value = "角色--角色修改", notes = "角色修改")
	@ApiImplicitParam(name = "roleVO", required = true, value = "角色", dataType = "RoleVO")
	public void update(@Valid @RequestBody RoleVO roleVO, HttpServletRequest request) throws ThinventBaseException {
		String userId = "";
		try {
			userId = JWTConfig.getUserByToken(request.getHeader(Constants.TOKEN_KEY)).getUserId();
		} catch (Exception e) {
			log.error("role updateRole can not find userId(unlegal login): ", e);
		}
		roleVO.setUpdateUser(userId);
		this.roleHander.updateRole(roleVO);
	}

	@GetMapping(value = "/deleteRole")
	@ApiOperation(value = "角色--角色删除", notes = "角色删除")
	@ApiImplicitParam(name = "roleId", required = true, value = "角色ID", dataType = "string", paramType = "query")
	public void delete(@RequestParam(value = "roleId", required = true) String roleId) throws ThinventBaseException {
		this.roleHander.deleteRole(roleId);
	}

	@GetMapping("/findAllRole")
	public Object findAllRole() throws ThinventBaseException {
		return this.roleHander.findAllRole();
	}
}
