package com.thinvent.basicpf.sys.adapt;

import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.PositionVO;

import java.util.List;
import java.util.Map;

public interface IPositionAdapt {

    public PositionVO getByPosId(String posId) throws ThinventBaseException;
    
    public List<PositionVO> getPosListByPosName(String posName, Integer pageIndex, Integer pageSize) throws ThinventBaseException;

    public void addPosition(PositionVO positionVO) throws ThinventBaseException;

    public void updatePosition(PositionVO positionVO) throws ThinventBaseException;

    public void deletePosition(String posId) throws ThinventBaseException;
    
    public Map listPositionByConditions(String posName, String posType, int pageIndex, int pageSize) throws ThinventBaseException;
    
    public List<PositionVO> listAllPosition() throws ThinventBaseException;

	public PositionVO getPositionByPosNameAndPosType(String posName, String posType) throws ThinventBaseException;

	public PositionVO getPositionByPosName(String posName) throws ThinventBaseException;

}
