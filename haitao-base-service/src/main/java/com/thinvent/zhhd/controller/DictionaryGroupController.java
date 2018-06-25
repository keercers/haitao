package com.thinvent.zhhd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.thinvent.zhhd.handler.IDictionaryGroupHandler;
import com.thinvent.zhhd.model.SysDictionaryGroup;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "dictionary")
public class DictionaryGroupController {

	@Autowired
	private IDictionaryGroupHandler sysDictionaryGroupService;
	
	@GetMapping(value="/querySysDictionaryGroupList")
	@ApiOperation(value="基础设置-系统字典-系统字典Group查询", notes="系统字典Group")
	@ResponseBody
	public List<SysDictionaryGroup> querySysDictionaryGroupList(){
		List<SysDictionaryGroup> result = sysDictionaryGroupService.getAllSysDictionaryGroup();
		return result;
	}
}
