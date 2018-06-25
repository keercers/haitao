package com.thinvent.basicpf.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thinvent.basicpf.handler.ISysRegisterHandler;
import com.thinvent.basicpf.model.Moudle;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.MoudleVO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "sysRegister")
public class SysRegisterController {

	@Autowired
	private ISysRegisterHandler sysRegisterHandler;
	
	@GetMapping(value = "/sysRegisterList")
	@ApiOperation(value = "模块--模块查询", notes = "查询所有模块列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "moudelName", required = true, value = "查询字段"),
			@ApiImplicitParam(name = "pageIndex", required = true, value = "当前页"),
			@ApiImplicitParam(name = "pageSize", required = true, value = "每页数据量") })
	public Object sysRegisterList(@RequestParam(value = "moudelName") String moudelName,
			@RequestParam(value = "pageIndex") int pageIndex, @RequestParam(value = "pageSize") int pageSize)
			throws ThinventBaseException {
		return this.sysRegisterHandler.sysRegisterList(moudelName, pageIndex, pageSize);
	}

	@GetMapping(value = "/sysRegisterDel")
	@ApiOperation(value = "模块--模块查询", notes = "查询所有模块列表")
	@ApiImplicitParam(name = "moudleId", required = true, value = "当前页")
	public void sysRegisterDel(@RequestParam(value = "moudleId") String moudleId) throws ThinventBaseException {
		this.sysRegisterHandler.sysRegisterDel(moudleId);
	}
	
	@GetMapping(value = "/sysRegisterOne")
	@ApiOperation(value = "模块--模块查询", notes = "查询所有模块列表")
	@ApiImplicitParam(name = "moudleId", required = true, value = "当前页")
	public Moudle sysRegisterOne(@RequestParam(value = "moudleId") String moudleId) throws ThinventBaseException {
		return this.sysRegisterHandler.sysRegisterOne(moudleId);
	}
	
	@PostMapping(value = "/sysRegisterUpdate")
    @ApiOperation(value = "模块--模块修改", notes = "修改模块内容")
    @ApiImplicitParam(name = "moudleVO", required = true, value = "模块", dataType = "MoudleVO")
    public Map<String, String> sysRegisterUpdate(@RequestBody MoudleVO moudleVO) throws ThinventBaseException{
        return this.sysRegisterHandler.sysRegisterUpdate(moudleVO);
    }
	
	@PostMapping(value = "/sysRegisterAdd")
    @ApiOperation(value = "模块--模块新增", notes = "新增模块内容")
    @ApiImplicitParam(name = "moudleVO", required = true, value = "模块", dataType = "MoudleVO")
    public String sysRegisterAdd(@RequestBody MoudleVO moudleVO) throws ThinventBaseException{
        return this.sysRegisterHandler.sysRegisterAdd(moudleVO);
    }
}
