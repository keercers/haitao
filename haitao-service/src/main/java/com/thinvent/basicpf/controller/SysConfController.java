package com.thinvent.basicpf.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thinvent.basicpf.handler.ISysConfHandler;
import com.thinvent.basicpf.model.Config;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.ConfigVO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "sysconf")
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
    public Config get(@RequestParam(value = "confId", required = true) String confId)  throws ThinventBaseException{
        return this.sysConfHandler.findByConfId(confId);
    }
	
	@GetMapping(value = "/querySysParam")
	@ApiOperation(value="配置--配置参数查询", notes="配置参数查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name="confType",required=true, value = "参数组", dataType = "int", paramType = "query"),
		@ApiImplicitParam(name="pageIndex",required=true, value = "页数", dataType = "int", paramType = "query"),
		@ApiImplicitParam(name="pageSize",required=true, value = "每页显示条数", dataType = "int", paramType = "query")
	})
	public Page<Config> querySysConfParam (@RequestParam(value = "confType", required = true) int confType,
			@RequestParam(value = "pageIndex", required = true) Integer pageIndex,
			@RequestParam(value = "pageSize", required = true) Integer pageSize) throws ThinventBaseException{
		return sysConfHandler.findSysParam(confType, new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "updateTime")));
	}
	
	@PostMapping(value = "/saveSysParam")
	@ApiOperation(value="配置--新增配置参数", notes="新增配置参数")
	@ApiImplicitParam(name="configVO",required=true, value = "参数", dataType = "configVO")
	public void saveSysParam(@Valid @RequestBody ConfigVO configVO) throws ThinventBaseException{
		this.sysConfHandler.saveSysParam(configVO);
	}
	
	@PostMapping(value = "/updateSysParam")
    @ApiOperation(value = "配置--配置参数修改", notes = "配置参数修改")
    @ApiImplicitParam(name = "configVO", required = true, value = "参数", dataType = "configVO")
    public void update(@Valid @RequestBody ConfigVO configVO) throws ThinventBaseException{
        this.sysConfHandler.updateSysParam(configVO);
    }
	
	@GetMapping(value = "/deleteSysParam")
	@ApiOperation(value = "配置--配置参数删除", notes = "配置参数删除")
	@ApiImplicitParam(name = "confId", required = true, value = "配置ID", dataType = "String", paramType = "query")
	public void delete(@RequestParam(value = "confId") String confId) throws ThinventBaseException{
	    this.sysConfHandler.deleteSysParam(confId);
	}
	
	@GetMapping(value = "/findConfigByConfKey")
    @ApiOperation(value = "配置--配置查询", notes = "配置查询")
    @ApiImplicitParam(name = "confKey", required = true, value = "配置ID", dataType = "string", paramType = "query")
    public Config findConfigByConfKey(@RequestParam(value = "confKey", required = true) String confKey)  throws ThinventBaseException{
        return this.sysConfHandler.findConfigByConfKey(confKey);
    }
}
