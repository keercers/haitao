package com.thinvent.basicpf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thinvent.basicpf.handler.IRoleMoudleHandler;
import com.thinvent.basicpf.model.RoleMoudle;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="roleMoudle")
public class RoleMoudleController {

	@Autowired
	private IRoleMoudleHandler roleMoudleHandler;

	@GetMapping(value = "/getListByRoleId")
	@ApiOperation(value = "通过角色ID查找角色权限", notes="查找列表", responseContainer="列表", response = RoleMoudle.class)
	@ApiImplicitParam(name="roleId", value="roleId", required = true, dataType = "string", paramType = "query")
	public List<RoleMoudle> getList(String roleId){
		return roleMoudleHandler.queryList(roleId);
	}
	
	@GetMapping(value = "/save")
	@ApiOperation(value = "通过角色ID保存角色权限", notes="保存角色权限", responseContainer="保存", response = String.class)
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name="roleId", value="roleId", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name="userId", value="userId", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name="choices", value="choices", required = true, dataType = "string", paramType = "save")
			})
	public String save(String roleId, String userId, String choices){
		return roleMoudleHandler.save(roleId, userId, choices);
	}
}
