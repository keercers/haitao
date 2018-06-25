package com.thinvent.basicpf.sys.controller;

import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.EnumUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by KYe on 2017/6/20.
 */
@RestController
@RequestMapping(value = "sys/enum")
public class EnumController {
    @GetMapping(value = "/listEnumValues")
    @ApiOperation(value = "枚举--枚举查询", notes = "枚举查询")
    @ApiImplicitParam(name = "enumName", required = true, value = "枚举类名称", dataType = "string", paramType = "query")
    public List get(@RequestParam(value = "enumName", required = true) String enumName) throws ThinventBaseException {
        return EnumUtil.parseToMaps(enumName);
    }
}
