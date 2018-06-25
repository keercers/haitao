package com.thinvent.basicpf.controller;

import com.thinvent.basicpf.handler.IPositionHandler;
import com.thinvent.basicpf.model.Position;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.PositionVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "position")
public class PositionController {

    @Autowired
    private IPositionHandler positionHandler;
    
    @Autowired
	private IPositionHandler positionService;

    @GetMapping(value = "/getByPosId")
    @ApiOperation(value = "岗位--岗位查询", notes = "岗位查询")
    @ApiImplicitParam(name = "posId", required = true, value = "岗位ID", dataType = "string", paramType = "query")
    public PositionVO get(@RequestParam(value = "posId", required = true) String posId) throws ThinventBaseException {
        return this.positionHandler.findByPosId(posId);
    }
    
    @GetMapping(value = "/getPositionByPosNameAndPosType")
    @ApiOperation(value = "岗位--岗位查询", notes = "岗位查询")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "posName", required = true, value = "岗位名称", dataType = "string", paramType = "query"),
    	@ApiImplicitParam(name = "posType", required = true, value = "岗位类型", dataType = "string", paramType = "query")
    })
    public PositionVO getPositionByPosNameAndPosType(@RequestParam(value = "posName", required = true) String posName,
    		@RequestParam(value = "posType", required = true) String posType) throws ThinventBaseException {
        return this.positionHandler.getPositionByPosNameAndPosType(posName, posType);
    }
    
    /**
     * @author wcs
     * @param posName
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws ThinventBaseException
     */
    @GetMapping(value = "/getPosByName")
    @ApiOperation(value = "岗位--岗位查询", notes = "岗位查询")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "posName", required = true, value = "岗位名称", dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "pageIndex", required = true, value = "页数", dataType = "int", paramType = "query"),
        @ApiImplicitParam(name = "pageSize", required = true, value = "每页显示条数", dataType = "int", paramType = "query")
    })
    public List<PositionVO> getPosByName(@RequestParam(value = "posName", required = true) String posName,
    		@RequestParam(value = "pageIndex", required = true) int pageIndex,
    		@RequestParam(value = "pageSize", required = true) int pageSize) throws ThinventBaseException {
        return this.positionHandler.findByPosName(posName, new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "updateTime")));
    }

    @GetMapping(value = "/listPositionByConditions")
    @ApiOperation(value = "岗位--岗位列表查询", notes = "岗位列表查询")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "pageIndex", required = true, value = "页数", dataType = "int", paramType = "query"),
        @ApiImplicitParam(name = "pageSize", required = true, value = "每页显示条数", dataType = "int", paramType = "query"),
        @ApiImplicitParam(name = "posName", required = false, value = "岗位名称", dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "posType", required = false, value = "岗位类型", dataType = "string", paramType = "query")
    })
    public Object listPositionByConditions(@RequestParam(value = "pageIndex", required = true) Integer pageIndex,
            @RequestParam(value = "pageSize", required = true) Integer pageSize,
    		@RequestParam(value = "posName", required = false) String posName,
            @RequestParam(value = "posType", required = false) String posType) throws ThinventBaseException {
    	Pageable pageable = new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "posSort"));
    	return this.positionService.listAllPositionByCondition(pageable, posName, posType);
    }

    @PostMapping(value = "/addPosition")
    @ApiOperation(value = "岗位--岗位保存", notes = "岗位保存")
    @ApiImplicitParam(name = "positionVO", required = true, value = "岗位", dataType = "PositionVO")
    public void add(@Valid @RequestBody PositionVO positionVO) throws ThinventBaseException {
        Position pos = new Position();
        BeanUtils.copyProperties(positionVO, pos, "posId");
        this.positionHandler.addPosition(pos);
    }

    @PostMapping(value = "/updatePosition")
    @ApiOperation(value = "岗位--岗位修改", notes = "岗位修改")
    @ApiImplicitParam(name = "positionVO", required = true, value = "岗位", dataType = "PositionVO")
    public void update(@Valid @RequestBody PositionVO positionVO) throws ThinventBaseException {
        Position position = new Position();
        BeanUtils.copyProperties(positionVO, position);
        this.positionHandler.updatePosition(position);
    }

    @GetMapping(value = "/delPosition")
    @ApiOperation(value = "岗位--岗位删除", notes = "岗位删除")
    @ApiImplicitParam(name = "posId", required = true, value = "岗位ID", dataType = "string", paramType = "query")
    public void delete(@RequestParam(value = "posId", required = true) String posId) throws ThinventBaseException {
        this.positionHandler.deletePosition(posId);
    }
    
    @GetMapping(value = "/listAllPositionDistinctByName")
    public List<PositionVO> listAllPosition() {
    	return this.positionHandler.listAllPositionDistinctByName();
    }
    
    @GetMapping(value = "/getPositionByPosName")
    @ApiOperation(value = "岗位--岗位查询", notes = "岗位查询")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "posName", required = true, value = "岗位名称", dataType = "string", paramType = "query")
    })
    public PositionVO findOneByPosName(@RequestParam(value = "posName", required = true) String posName) throws ThinventBaseException {
        return this.positionHandler.findOneByPosName(posName);
    }
}
