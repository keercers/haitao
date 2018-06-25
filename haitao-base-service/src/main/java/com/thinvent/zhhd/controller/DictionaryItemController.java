package com.thinvent.zhhd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.zhhd.common.vo.DictionaryItemVO;
import com.thinvent.zhhd.handler.IDictionaryItemHandler;
import com.thinvent.zhhd.model.SysDictionaryItem;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "dictionary")
public class DictionaryItemController {
	
	@Autowired
	private IDictionaryItemHandler sysDictionaryItemService;
	
	@GetMapping(value="/querySysDictionaryItemListByDictGroupId")
	@ApiOperation(value="系统字典-系统字典Item查询", notes="系统字典Item")
	@ApiImplicitParams({
		@ApiImplicitParam(name="pageIndex",required=true, value = "页数", dataType = "int", paramType = "query"),
		@ApiImplicitParam(name="pageSize",required=true, value = "每页显示条数", dataType = "int", paramType = "query"),
		@ApiImplicitParam(name="dictGroupId",required=true, value = "dictGroupId", dataType = "String", paramType = "query")
	})
	@ResponseBody
	public Page<SysDictionaryItem> querySysDictionaryItemListByDictGroupId(@RequestParam int pageIndex, @RequestParam int pageSize, @RequestParam String dictGroupId){
		return this.sysDictionaryItemService.findAllByDictGroupId(pageIndex, pageSize, dictGroupId);
	}
	
	@RequestMapping(value = "/saveSysDictionaryItem", method = RequestMethod.POST)
    @ApiOperation(value = "系统字典--系统字典Item保存", notes = "系统字典Item保存")
    @ApiImplicitParam(name = "sysDictionaryItemVO", required = true, value = "系统字典Item", dataType = "SysDictionaryItemVO")
	@ResponseBody
	public void saveSysDictionaryItem(@RequestBody DictionaryItemVO sysDictionaryItemVO){
		this.sysDictionaryItemService.save(sysDictionaryItemVO);
	}
	
	@RequestMapping(value = "/updateSysDictionaryItem", method = RequestMethod.POST)
    @ApiOperation(value = "系统字典--系统字典Item修改", notes = "系统字典Item修改")
    @ApiImplicitParam(name = "sysDictionaryItemVO", required = true, value = "系统字典Item", dataType = "SysDictionaryItemVO")
	@ResponseBody
	public Object updateSysDictionaryItem(@RequestBody DictionaryItemVO sysDictionaryItemVO) throws ThinventBaseException{
		return this.sysDictionaryItemService.update(sysDictionaryItemVO);
	}
	
	@GetMapping(value="/queryBySysDictionaryItemId")
    @ApiOperation(value = "系统字典--系统字典Item按id查找", notes = "系统字典Item按id查找")
	@ApiImplicitParam(name="sysDictionaryItemId",required=true, value = "系统字典ItemID", dataType = "string", paramType = "query")
	public Object queryBySysDictionaryItemId(@RequestParam(required = true) String sysDictionaryItemId){
		SysDictionaryItem sysDictionaryItem = this.sysDictionaryItemService.findSysDictionaryItemById(sysDictionaryItemId);
		return sysDictionaryItem;
	}
	
	@GetMapping(value="/queryAllEnabledDictItemsByDictGroupId")
    @ApiOperation(value = "系统字典--已启用的系统字典Item按groupId查找", notes = "已启用的系统字典Item按groupId查找")
	@ApiImplicitParam(name="dictGroupId",required=true, value = "系统字典groupID", dataType = "string", paramType = "query")
	@ResponseBody
	public List<DictionaryItemVO> queryAllEnabledDictItemsByDictGroupId(@RequestParam(value = "dictGroupId", required = true) String dictGroupId) throws ThinventBaseException{
		return this.sysDictionaryItemService.findAllEnabledDictItemsByDictGroupId(dictGroupId);
	}
	
	@GetMapping(value="/queryAllDictItemsByDictGroupId")
    @ApiOperation(value = "系统字典--系统字典Item按groupId查找", notes = "系统字典Item按groupId查找")
	@ApiImplicitParam(name="dictGroupId",required=true, value = "系统字典groupID", dataType = "string", paramType = "query")
	@ResponseBody
	public List<DictionaryItemVO> queryAllDictItemsByDictGroupId(@RequestParam(value = "dictGroupId", required = true) String dictGroupId) throws ThinventBaseException{
		return this.sysDictionaryItemService.findAllDictItemsByDictGroupId(dictGroupId);
	}
}
