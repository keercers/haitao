package com.thinvent.basicpf.sys.controller;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thinvent.basicpf.sys.handler.ILogHandler;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.LogVO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "sys/log")
public class LogController {
	
	@Autowired
	private ILogHandler logHandler;
	
	/** 
	 * 按照条件检索对应的系统日志信息
	 * @author shensc
	 * 
	 */
	@PostMapping(value = "/saveLog")
    @ApiOperation(value = "日志--日志保存", notes = "日志保存")
    @ApiImplicitParam(name = "logVO", required = true, value = "日志", dataType = "LogVO")
    public void save(@Valid @RequestBody LogVO logVO) throws ThinventBaseException{
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
	public Object listLogByParams(@RequestParam(value = "logType", required = false) String logType,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "system", required = false) String system,
			@RequestParam(value = "maxDate", required = false) String maxDate,
			@RequestParam(value = "minDate", required = false) String minDate,
			@RequestParam(value = "pageSize", required = true) int pageSize,
			@RequestParam(value = "pageIndex", required = true) int pageIndex) throws ThinventBaseException, ParseException {
		return this.logHandler.listLogByParams(logType, userName, system, maxDate, minDate, pageSize, pageIndex);
	}
}
