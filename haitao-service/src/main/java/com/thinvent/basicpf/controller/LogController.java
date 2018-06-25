package com.thinvent.basicpf.controller;

import java.text.ParseException;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.thinvent.basicpf.handler.ILogHandler;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.StringUtil;
import com.thinvent.library.vo.LogVO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "log")
public class LogController {

	@Autowired
	private ILogHandler logHandler;
	
	@PostMapping(value = "/saveLog")
	@ApiOperation(value="日志--新增日志", notes="新增日志")
	@ApiImplicitParam(name="logVO",required=true, value = "日志", dataType = "LogVO")
	public void saveLog(@Valid @RequestBody LogVO logVO) throws ThinventBaseException{
		this.logHandler.saveLog(logVO);
	}
	
	@GetMapping(value="/listLogByParams")
	@ApiOperation(value="日志--日志列表", notes="条件查询日志")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "logType", required = false, value = "日志类型", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "userName", required = false, value = "人员名字", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "system", required = false, value = "系统", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "maxDate", required = false, value = "最大日志时间", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "minDate", required = false, value = "最小日志时间", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "pageSize", required = true, value = "页大小", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "pageIndex", required = true, value = "页数", dataType = "string", paramType = "query")
	})
	@ResponseBody
	public Object listLogByConditions(@RequestParam(value = "pageSize", required = true) int pageSize,
			@RequestParam(value = "pageIndex", required = true) int pageIndex,
			@RequestParam(value = "logType", required = false) String logType,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "system", required = false) String system,
			@RequestParam(value = "maxDate", required = false) String maxDate,
			@RequestParam(value = "minDate", required = false) String minDate) throws ThinventBaseException, ParseException {
		Date maxTime = StringUtil.dateFormat(maxDate);
		Date minTime = StringUtil.dateFormat(minDate);
		return this.logHandler.listLogByConditions(new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "id")), logType, userName, system, maxTime, minTime);
	}
}
