package com.thinvent.basicpf.zhhd.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.thinvent.basicpf.zhhd.handler.IDictionaryItemHandler;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.zhhd.common.vo.DictionaryItemVO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "sys/dictionary")
public class DictionaryItemController {

	@Autowired
	private IDictionaryItemHandler sysDictionaryItemHandler;

	@RequestMapping(value = "/querySysDictionaryItemListByDictGroupId", method = RequestMethod.GET)
	@ApiOperation(value = "系统字典-系统字典Item查询", notes = "系统字典Item")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageIndex", required = true, value = "页数", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "pageSize", required = true, value = "每页显示条数", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "dictGroupId", required = true, value = "dictGroupId", dataType = "String", paramType = "query") })
	@ResponseBody
	public JSONObject querySysDictionaryItemListByDictGroupId(@RequestParam int pageIndex, @RequestParam int pageSize,
			@RequestParam String dictGroupId) throws ThinventBaseException {
		return this.sysDictionaryItemHandler.getAllDictionaryItemByDictGroupId(pageIndex, pageSize, dictGroupId);
	}

	@PostMapping(value = "/addSysDictionaryItem")
	@ApiOperation(value = "系统字典--系统字典Item保存", notes = "系统字典Item保存")
	@ApiImplicitParam(name = "sysDictionaryItemVO", required = true, value = "系统字典Item", dataType = "SysDictionaryItemVO")
	public void save(@Valid @RequestBody DictionaryItemVO sysDictionaryItemVO) throws ThinventBaseException {
		this.sysDictionaryItemHandler.saveSysDictionaryItem(sysDictionaryItemVO);
	}

	@PostMapping(value = "/updateSysDictionaryItem")
	@ApiOperation(value = "系统字典--系统字典Item修改", notes = "系统字典Item修改")
	@ApiImplicitParam(name = "sysDictionaryItemVO", required = true, value = "系统字典Item", dataType = "SysDictionaryItemVO")
	public void update(@Valid @RequestBody DictionaryItemVO sysDictionaryItemVO) throws ThinventBaseException {
		this.sysDictionaryItemHandler.updateSysDictionaryItem(sysDictionaryItemVO);
	}

	@RequestMapping(value = "/queryBySysDictionaryItemId", method = RequestMethod.GET)
	@ApiOperation(value = "系统字典--系统字典Item按id查找", notes = "系统字典Item按id查找")
	@ApiImplicitParam(name = "sysDictionaryItemId", required = true, value = "系统字典ItemID", dataType = "string", paramType = "query")
	@ResponseBody
	public DictionaryItemVO queryBySysDictionaryItemId(
			@RequestParam(value = "sysDictionaryItemId", required = true) String sysDictionaryItemId)
			throws ThinventBaseException {
		return sysDictionaryItemHandler.findSysDictionaryItemById(sysDictionaryItemId);
	}

	@RequestMapping(value = "/queryAllEnabledDictItemsByDictGroupId", method = RequestMethod.GET)
	@ApiOperation(value = "系统字典--已启用的系统字典Item按groupId查找", notes = "已启用的系统字典Item按groupId查找")
	@ApiImplicitParam(name = "dictGroupId", required = true, value = "系统字典groupID", dataType = "string", paramType = "query")
	@ResponseBody
	public List<DictionaryItemVO> queryAllEnabledDictItemsByDictGroupId(
			@RequestParam(value = "dictGroupId", required = true) String dictGroupId) throws ThinventBaseException {
		return sysDictionaryItemHandler.findAllEnabledDictItemsByDictGroupId(dictGroupId);
	}
	
	@RequestMapping(value = "/queryAllDictItemsByDictGroupId", method = RequestMethod.GET)
	@ApiOperation(value = "系统字典--系统字典Item按groupId查找", notes = "系统字典Item按groupId查找")
	@ApiImplicitParam(name = "dictGroupId", required = true, value = "系统字典groupID", dataType = "string", paramType = "query")
	@ResponseBody
	public List<DictionaryItemVO> queryAllDictItemsByDictGroupId(
			@RequestParam(value = "dictGroupId", required = true) String dictGroupId) throws ThinventBaseException {
		return sysDictionaryItemHandler.findAllDictItemsByDictGroupId(dictGroupId);
	}
}
