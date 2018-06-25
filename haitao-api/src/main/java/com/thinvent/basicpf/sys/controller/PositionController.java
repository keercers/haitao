package com.thinvent.basicpf.sys.controller;

import com.thinvent.basicpf.sys.handler.IPositionHandler;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.PositionVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "sys/position")
public class PositionController {
    @Autowired
    private IPositionHandler positionHandle;

    @GetMapping(value = "/getByPosId")
    @ApiOperation(value = "岗位--岗位查询", notes = "岗位查询")
    @ApiImplicitParam(name = "posId", required = true, value = "岗位ID", dataType = "string", paramType = "query")
    public PositionVO get(@RequestParam(value = "posId", required = true) String posId) throws ThinventBaseException{
        return positionHandle.getByPosId(posId);
    }

    @GetMapping(value = "/getPosList")
    @ApiOperation(value = "岗位--岗位列表查询", notes = "岗位列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "posName", required = false, value = "岗位名称", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "posType", required = false, value = "岗位类型", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "pageIndex", required = true, value = "页数", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", required = true, value = "每页显示条数", dataType = "int", paramType = "query")
    })
    public Object list(@RequestParam(value = "posName", required = false) String posName,
                           @RequestParam(value = "posType", required = false) String posType, 
                           @RequestParam(value = "pageIndex", required = true) Integer pageIndex,
                           @RequestParam(value = "pageSize", required = true) Integer pageSize) throws ThinventBaseException{
        return this.positionHandle.listPositionByCondition(posName, posType, pageIndex, pageSize);
    }
    
    @GetMapping(value = "/listAllPosition")
    public List listAllPositon() throws ThinventBaseException {
    	return this.positionHandle.listAllPosition();
    }

    @PostMapping(value = "/addPosition")
    @ApiOperation(value = "岗位--岗位保存", notes = "岗位保存")
    @ApiImplicitParam(name = "positionVO", required = true, value = "岗位", dataType = "PositionVO")
    public void add(@Valid @RequestBody PositionVO positionVO) throws ThinventBaseException{
        this.positionHandle.addPosition(positionVO);
    }


    @PostMapping(value = "/updatePosition")
    @ApiOperation(value = "岗位--岗位修改", notes = "岗位修改")
    @ApiImplicitParam(name = "positionVO", required = true, value = "岗位", dataType = "PositionVO")
    public void update(@Valid @RequestBody PositionVO positionVO) throws ThinventBaseException{
        this.positionHandle.updatePosition(positionVO);
    }

    @GetMapping(value = "/delPosition")
    @ApiOperation(value = "岗位--岗位删除", notes = "岗位删除")
    @ApiImplicitParam(name = "posId", required = true, value = "岗位ID", dataType = "string", paramType = "query")
    public void delete(@RequestParam(value = "posId", required = true) String posId) throws ThinventBaseException{
        this.positionHandle.deletePosition(posId);
    }

}
