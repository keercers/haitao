package com.thinvent.basicpf.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thinvent.basicpf.handler.IMoudleHandler;
import com.thinvent.library.exception.ThinventBaseException;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "moudle")
public class MoudleController {

	@Autowired
	private IMoudleHandler moudleHandle;
	
	@GetMapping(value = "/getMoudleByLevel")
    @ApiOperation(value = "模块--模块等级查询", notes = "根据等级查询模块列表")
    @ApiImplicitParam(name = "moudleLevel", required = true, value = "模块ID", dataType = "string", paramType = "query")
    public Object getByLevel(@RequestParam(value = "moudleLevel") String moudleLevel, @RequestParam(value = "userId") String userId) throws ThinventBaseException {
        return this.moudleHandle.findByMoudleLevelAndEnable(moudleLevel, userId);
    }
	
	@GetMapping(value = "/getMoudleTreeBySign")
    @ApiOperation(value = "模块--模块子节点查询", notes = "根据模块sign查询模块列表")
    @ApiImplicitParam(name = "moudleSign", required = true, value = "模块ID", dataType = "string", paramType = "query")
    public Object getTreeBySign(@RequestParam(value = "moudleSign") String moudleSign, @RequestParam(value = "userId") String userId) {
        return this.moudleHandle.findTreeByMoudleSignLike(moudleSign, userId);
    }
	
	@GetMapping(value = "/getAll")
    @ApiOperation(value = "模块--模块查询", notes = "查询所有模块列表")
    public Object getAll() {
        return this.moudleHandle.findByEnable();
    }
	
	@GetMapping(value = "/getForbidList")
	@ApiOperation(value = "模块--获取用户禁止权限", notes = "获取用户禁止权限")
	@ApiImplicitParam(name = "userId", required = true, value = "禁止权限", dataType = "string")
	public String getForbidList(@RequestParam(required = true, value = "userId") String userId) throws ThinventBaseException{
        return this.moudleHandle.getForbidList(userId);
	}
	
	@GetMapping(value = "/getMoudleTree")
	@ApiOperation(value = "模块--模块树查询", notes = "模块树查询")
	@ApiImplicitParam(name = "moudleSign", required = true, value = "模块ID", dataType = "String", paramType = "query")
	public List<Map> getMoudleTree(@RequestParam(value = "moudleSign") String moudleSign, @RequestParam(value = "userId") String userId) throws ThinventBaseException {
	    return this.moudleHandle.getMoudleTree(moudleSign, userId);
	}
}
