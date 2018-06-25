package com.thinvent.basicpf.zhhd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.thinvent.basicpf.zhhd.handler.IDictionaryGroupHandler;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.zhhd.common.vo.DictionaryGroupVO;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "sys/dictionary")
public class DictionaryGroupController {

	@Autowired
	private IDictionaryGroupHandler sysDictionaryGroupHandler;

	@RequestMapping(value = "/querySysDictionaryGroupList", method = RequestMethod.GET)
	@ApiOperation(value = "基础设置-系统字典Group查询", notes = "系统字典Group")
	@ResponseBody
	public List<DictionaryGroupVO> querySysDictionaryGroupList() throws ThinventBaseException {
		return sysDictionaryGroupHandler.getAllSysDictionaryGroup();
	}
}
