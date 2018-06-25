package com.thinvent.basicpf.sys.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.thinvent.basicpf.sys.handler.ISysConfHandler;
import com.thinvent.library.Constants;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.JWTConfig;
import com.thinvent.library.vo.ConfigVO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "sys/sysconf")
public class SysConfController {
	@Autowired
	private ISysConfHandler sysConfHandler;
	
	/** 
	 * 按照条件检索对应的系统配置信息
	 * @author shensc
	 * 
	 */
	@GetMapping(value = "/getByConfId")
    @ApiOperation(value = "配置--配置查询", notes = "配置查询")
    @ApiImplicitParam(name = "confId", required = true, value = "配置ID", dataType = "string", paramType = "query")
    public Object get(@RequestParam(value = "confId", required = true) String confId) throws ThinventBaseException{
        return sysConfHandler.getByConfId(confId);
    }
	
	@GetMapping(value = "/querySysParam")
	@ApiOperation(value = "配置--系统配置查询", notes = "配置查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name="confType",required=true, value = "参数组", dataType = "int", paramType = "query"),
		@ApiImplicitParam(name = "pageIndex", required = true, value = "页数", dataType = "int", paramType = "query"),
        @ApiImplicitParam(name = "pageSize", required = true, value = "每页显示条数", dataType = "int", paramType = "query")
	})
	public JSONObject list(@RequestParam(value = "confType", required = false) int confType, 
			@RequestParam(value = "pageIndex", required = true) Integer pageIndex,
            @RequestParam(value = "pageSize", required = true) Integer pageSize) throws ThinventBaseException{
		return this.sysConfHandler.getConfList(confType, pageIndex, pageSize);
	}
	
    @PostMapping(value = "/saveSysParam")
    @ApiOperation(value = "配置--配置保存", notes = "配置保存")
    @ApiImplicitParam(name = "configVO", required = true, value = "配置", dataType = "ConfigVO")
    public void save(@Valid @RequestBody ConfigVO configVO, HttpServletRequest request) throws ThinventBaseException{
    	String userId = "";
		try {
			userId = JWTConfig.getUserByToken(request.getHeader(Constants.TOKEN_KEY)).getUserId();
		} catch (Exception e) {
			log.error("sysParam saveSysParam can not find userId(unlegal login): ", e);
		}
		configVO.setCreateTime(new Date());
		configVO.setCreateUser(userId);
		configVO.setUpdateTime(new Date());
		configVO.setUpdateUser(userId);
		this.sysConfHandler.addConfig(configVO);
    }
    
    @PostMapping(value = "/updateSysParam")
    @ApiOperation(value = "配置--配置修改", notes = "配置修改")
    @ApiImplicitParam(name = "configVO", required = true, value = "配置", dataType = "ConfigVO")
    public void update(@Valid @RequestBody ConfigVO configVO, HttpServletRequest request) throws ThinventBaseException{
    	String userId = "";
		try {
			userId = JWTConfig.getUserByToken(request.getHeader(Constants.TOKEN_KEY)).getUserId();
		} catch (Exception e) {
			log.error("sysParam updateSysParam can not find userId(unlegal login): ", e);
		}
		configVO.setUpdateTime(new Date());
		configVO.setUpdateUser(userId);
		this.sysConfHandler.updateConfig(configVO);
    }
    
    @GetMapping(value = "/deleteSysParam")
    @ApiOperation(value = "配置--配置删除", notes = "配置删除")
    @ApiImplicitParam(name = "confId", required = true, value = "配置ID", dataType = "String", paramType = "query")
    public void delete(@RequestParam(value = "confId", required = true) String confId) throws ThinventBaseException{
    	this.sysConfHandler.deleteConfig(confId);
    }
}	
