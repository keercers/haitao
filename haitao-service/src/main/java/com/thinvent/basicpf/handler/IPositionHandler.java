package com.thinvent.basicpf.handler;

import com.thinvent.basicpf.model.Position;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.PositionVO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IPositionHandler {
	void addPosition(Position position) throws ThinventBaseException;

	void updatePosition(Position position) throws ThinventBaseException;
	
	PositionVO findByPosId(String posId) throws ThinventBaseException;

	void deletePosition(String posId) throws ThinventBaseException;
	
	List<PositionVO> findByPosName(String posName, Pageable pageable);

	public Map listAllPositionByCondition(Pageable pageable, String posName, String posType);
	
	List<PositionVO> listAllPositionDistinctByName();

	PositionVO getPositionByPosNameAndPosType(String posName, String posType) throws ThinventBaseException;
	
	PositionVO findOneByPosName(String posName) throws ThinventBaseException;
}
