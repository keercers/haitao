package com.thinvent.basicpf.sys.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thinvent.basicpf.sys.handler.IMoudleHandler;
import com.thinvent.library.Constants;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.JWTConfig;
import com.thinvent.library.vo.UserVO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "sys/moudle")
public class MoudleController {

	@Autowired
	private IMoudleHandler moudleHandle;
	
	@GetMapping(value = "/getMoudleByLevel")
	@ApiOperation(value = "模块--模块等级查询", notes = "根据等级查询模块列表")
	@ApiImplicitParam(name = "moudleLevel", required = true, value = "模块ID", dataType = "string", paramType = "query")
	public Object getByLevel(@RequestParam(value = "moudleLevel") String moudleLevel, HttpServletRequest request,
			HttpServletResponse response) throws ThinventBaseException {
		String tokenIn = request.getHeader(Constants.TOKEN_KEY);
		UserVO user = null;
		try {
			user = JWTConfig.getUserByToken(tokenIn);
		} catch (Exception e) {
			log.error("moudle getMoudleByLevel can not find userId(unlegal login): ", e);
		}
		if(user != null) {
			return this.moudleHandle.getByLevel(moudleLevel, user.getUserId());
		}else {
			return "";
		}
	}

	@GetMapping(value = "/getMoudleTreeBySign")
	@ApiOperation(value = "模块--模块子节点查询", notes = "根据模块sign查询模块列表")
	@ApiImplicitParam(name = "moudleSign", required = true, value = "模块ID", dataType = "string", paramType = "query")
	public Object getTreeBySign(@RequestParam(value = "moudleSign") String moudleSign, HttpServletRequest request,
			HttpServletResponse response) throws ThinventBaseException {
		String tokenIn = request.getHeader(Constants.TOKEN_KEY);
		UserVO user = null;
		try {
			user = JWTConfig.getUserByToken(tokenIn);
		} catch (Exception e) {
			log.error("moudle getMoudleTreeBySign can not find userId(unlegal login): ", e);
		}
		if(user != null) {
			return this.moudleHandle.getTreeBySign(moudleSign, user.getUserId());
		}else {
			return "";
		}
	}

	@GetMapping(value = "/getForbid")
	@ApiOperation(value = "模块--获取用户禁止权限", notes = "获取用户禁止权限")
	public String getForbidList(HttpServletRequest request, HttpServletResponse response) throws ThinventBaseException {
		String tokenIn = request.getHeader(Constants.TOKEN_KEY);
		UserVO user = null;
		try {
			user = JWTConfig.getUserByToken(tokenIn);
		} catch (Exception e) {
			log.error("moudle getForbid can not find userId(unlegal login): ", e);
		}
		if(user != null) {
			return this.moudleHandle.getForbidList(user.getUserId());
		}else {
			return "";
		}
	}
	
	@GetMapping(value = "/getMoudleTree")
    @ApiOperation(value = "模块--模块Tree", notes = "模块Tree")
    @ApiImplicitParam(name = "moudleSign", required = true, value = "模块sign", dataType = "string", paramType = "query")
    public List<Map> getTreeAreasById(@RequestParam(value = "moudleSign", required = true) String moudleSign, HttpServletRequest request) throws ThinventBaseException {
		String tokenIn = request.getHeader(Constants.TOKEN_KEY);
		UserVO user = null;
		try {
			user = JWTConfig.getUserByToken(tokenIn);
		} catch (Exception e) {
			log.error("moudle getMoudleTree can not find userId(unlegal login): ", e);
		}
		if(user != null) {
			return this.moudleHandle.getMoudleTree(moudleSign, user.getUserId());
		}else {
			return Collections.emptyList();
		}
    }
}
