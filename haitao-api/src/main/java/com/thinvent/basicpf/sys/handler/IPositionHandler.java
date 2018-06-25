package com.thinvent.basicpf.sys.handler;

import java.util.List;
import java.util.Map;

import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.PositionVO;

public interface IPositionHandler {

    PositionVO getByPosId(String posId) throws ThinventBaseException;
    
    PositionVO getByName(String name)throws ThinventBaseException;

    void addPosition(PositionVO positionVO) throws ThinventBaseException;

    void updatePosition(PositionVO positionVO) throws ThinventBaseException;

    void deletePosition(String posId) throws ThinventBaseException;
    
    Map listPositionByCondition(String posName, String posType, Integer pageIndex, Integer pageSize) throws ThinventBaseException;
    
    List listAllPosition() throws ThinventBaseException;
}
