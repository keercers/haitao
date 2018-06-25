package com.thinvent.basicpf.sys.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thinvent.basicpf.sys.handler.IMenuHandler;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.MoudleVO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "sys/menu")
public class MenuController {

	@Autowired
	private IMenuHandler menuHandler;
	
	@GetMapping(value = "/menuAll")
	@ApiOperation(value = "模块--模块查询", notes = "查询所有模块列表")
	public Object menuAllApi() throws ThinventBaseException {
		return this.menuHandler.menuAll();
	}

	@GetMapping(value = "/menuList")
	@ApiOperation(value = "模块--模块查询", notes = "查询所有模块列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "moudelName", required = true, value = "查询字段"),
		@ApiImplicitParam(name = "pageIndex", required = true, value = "当前页"),
		@ApiImplicitParam(name = "pageSize", required = true, value = "每页数据量")
	})
	public Object menuListApi(@RequestParam(value = "moudelName") String moudelName,
			@RequestParam(value = "pageIndex") int pageIndex, @RequestParam(value = "pageSize") int pageSize) throws ThinventBaseException {
		return this.menuHandler.menuList(moudelName, pageIndex, pageSize);
	}

	@GetMapping(value = "/menuDel")
	@ApiOperation(value = "模块--模块查询", notes = "查询所有模块列表")
	@ApiImplicitParam(name = "moudleId", required = true, value = "当前页")
	public void menuDelApi(@RequestParam(value = "moudleId") String moudleId) throws ThinventBaseException {
		this.menuHandler.menuDel(moudleId);
	}


	@GetMapping(value = "/menuOne")
	@ApiOperation(value = "模块--模块查询", notes = "查询所有模块列表")
	@ApiImplicitParam(name = "moudleId", required = true, value = "当前页")
	public String menuOneApi(@RequestParam(value = "moudleId") String moudleId) throws ThinventBaseException {
		return this.menuHandler.menuOne(moudleId);
	}

	@PostMapping(value = "/menuUpdate")
	@ApiOperation(value = "模块--模块修改", notes = "修改模块内容")
	@ApiImplicitParam(name = "moudleVO", required = true, value = "模块", dataType = "MoudleVO")
	public Map<String, String> menuUpdateApi(@RequestBody MoudleVO moudleVO) throws ThinventBaseException {
		return this.menuHandler.menuUpdate(moudleVO);
	}

	@PostMapping(value = "/menuAdd")
	@ApiOperation(value = "模块--模块新增", notes = "新增模块内容")
	@ApiImplicitParam(name = "moudleVO", required = true, value = "模块", dataType = "MoudleVO")
	public Map<String, String> menuAddApi(@RequestBody MoudleVO moudleVO) throws ThinventBaseException {
		return this.menuHandler.menuAdd(moudleVO);
	}
}
