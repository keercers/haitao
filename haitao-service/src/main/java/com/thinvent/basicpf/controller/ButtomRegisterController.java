package com.thinvent.basicpf.controller;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thinvent.basicpf.handler.IButtomRegisterHandler;
import com.thinvent.basicpf.model.Moudle;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.MoudleVO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "buttomRegister")
public class ButtomRegisterController {

	@Autowired
	private IButtomRegisterHandler buttomRegisterHandle;
	
	@GetMapping(value = "/buttomRegisterList")
	@ApiOperation(value = "模块--模块查询", notes = "查询所有模块列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "moudelName", required = true, value = "查询字段"),
		@ApiImplicitParam(name = "moudelId", required = true, value = "当前模块"),
		@ApiImplicitParam(name = "pageIndex", required = true, value = "当前页"),
			@ApiImplicitParam(name = "pageSize", required = true, value = "每页数据量") })
	public Object buttomRegisterList(@RequestParam(value = "moudelName") String moudelName, @RequestParam(value = "moudelId") String moudelId,
			@RequestParam(value = "pageIndex") int pageIndex, @RequestParam(value = "pageSize") int pageSize) {
		return this.buttomRegisterHandle.buttomRegisterList(moudelId, moudelName, pageIndex, pageSize);
	}

	@GetMapping(value = "/buttomRegisterDel")
	@ApiOperation(value = "模块--模块查询", notes = "查询所有模块列表")
	@ApiImplicitParam(name = "moudleId", required = true, value = "当前页")
	public void buttomRegisterDel(@RequestParam(value = "moudleId") String moudleId) throws ThinventBaseException {
		this.buttomRegisterHandle.buttomRegisterDel(moudleId);
	}
	
	@GetMapping(value = "/buttomRegisterOne")
	@ApiOperation(value = "模块--模块查询", notes = "查询所有模块列表")
	@ApiImplicitParam(name = "moudleId", required = true, value = "当前页")
	public Moudle buttomRegisterOne(@RequestParam(value = "moudleId") String moudleId) {
		return this.buttomRegisterHandle.buttomRegisterOne(moudleId);
	}
	
	@PostMapping(value = "/buttomRegisterAdd")
    @ApiOperation(value = "模块--模块新增", notes = "新增模块内容")
    @ApiImplicitParam(name = "moudleVO", required = true, value = "模块", dataType = "MoudleVO")
    public String buttomRegisterAdd(@RequestBody MoudleVO moudleVO) throws ThinventBaseException {
        return this.buttomRegisterHandle.buttomRegisterAdd(moudleVO);
    }
	
	@PostMapping(value = "/buttomRegisterUpdate")
    @ApiOperation(value = "模块--模块修改", notes = "修改模块内容")
    @ApiImplicitParam(name = "moudleVO", required = true, value = "模块", dataType = "MoudleVO")
    public Map<String, String> buttomRegisterUpdate(@RequestBody MoudleVO moudleVO) throws ThinventBaseException {
        return this.buttomRegisterHandle.buttomRegisterUpdate(moudleVO);
    }
}
