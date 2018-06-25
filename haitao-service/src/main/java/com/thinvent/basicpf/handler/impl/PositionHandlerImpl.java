package com.thinvent.basicpf.handler.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.thinvent.basicpf.dao.IPositionDao;
import com.thinvent.basicpf.dao.IUserDao;
import com.thinvent.basicpf.dao.jpa.PositionDaoRepositoryImpl;
import com.thinvent.basicpf.handler.IPositionHandler;
import com.thinvent.basicpf.model.Position;
import com.thinvent.basicpf.util.PositionVerifyUtil;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.PositionVO;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class PositionHandlerImpl implements IPositionHandler {

    @Autowired
    private IPositionDao positionDao;
    
    @Autowired
	private IUserDao userDao;
    
    @Autowired
	private PositionDaoRepositoryImpl positionDaoRepository;
    
    private static final String POSITIONLIST = new String("positionList").intern();

    @Override
    public void addPosition(Position position) throws ThinventBaseException {
    	Position pos;
    	if(!"".equals(position.getDepId())){
    		pos = positionDao.findByPosNameAndPosTypeAndDepIdAndEnable(position.getPosName(), position.getPosType(), position.getDepId(), 1);
    	}else {
    		pos = positionDao.findByPosNameAndPosTypeAndEnableAndDepIdIsNull(position.getPosName(), position.getPosType(), 1);
    	}
    	PositionVerifyUtil.verifyExistPos(pos);
        // 将id设置为0,强制做新增保存
        position.setId(0);
        positionDao.save(position);
    }

    @Override
    public void updatePosition(Position position) throws ThinventBaseException {
    	Position pos;
    	if(!"".equals(position.getDepId())){
    		pos = positionDao.findByPosNameAndPosTypeAndDepIdAndEnableAndPosIdNot(position.getPosName(), position.getPosType(), position.getDepId(), 1, position.getPosId());
    	} else {
    		pos = positionDao.findByPosNameAndPosTypeAndDepIdIsNullAndEnableAndPosIdNot(position.getPosName(), position.getPosType(), 1, position.getPosId());
    	}
    	PositionVerifyUtil.verifyExistPos(pos);
    	
    	Position oldPosition = positionDao.findOneByPosIdAndEnable(position.getPosId(), 1);
        PositionVerifyUtil.verifyPos(oldPosition);
        position.setId(oldPosition.getId()).setPosId(oldPosition.getPosId());
        positionDao.save(position);
    }

    @Override
    public PositionVO findByPosId(String posId) throws ThinventBaseException {
        Position position = positionDao.findOneByPosIdAndEnable(posId, 1);
        if(position == null)
        	return null;
        return position.convertToPositionVO();
    }

    @Override
    public void deletePosition(String posId) throws ThinventBaseException {
    	Boolean existsUser = this.userDao.existsByPosIdAndEnable(posId);
		PositionVerifyUtil.verifyDeletePosResult(existsUser);
        Position position = positionDao.findOneByPosIdAndEnable(posId, 1);
        PositionVerifyUtil.verifyPos(position);
        position.setEnable(0);
        positionDao.save(position);
    }
    
    @Override
    public Map listAllPositionByCondition(Pageable pageable, String posName, String posType){
    	Map ret = positionDaoRepository.listAllPositionByCondition(pageable, posName, posType);
		List<PositionVO> pvoList = (List<PositionVO>)ret.get(POSITIONLIST);
		ret.put(POSITIONLIST, pvoList);
		return ret;
    }
    
    @Override
    public List<PositionVO> listAllPositionDistinctByName() {
    	List<Position> positions = positionDao.listAllPositionDistinctByName();
    	List<PositionVO> pvo = new ArrayList();
    	positions.forEach(position -> pvo.add(position.convertToPositionVO()));
    	return pvo;
    }

    @Override
    public List<PositionVO> findByPosName(String posName, Pageable pageable) {
        List<Position> positions = positionDao.listPosByPosName(posName, pageable);
        return positions.stream().map(Position::convertToPositionVO).collect(Collectors.toList());
    }

	@Override
	public PositionVO getPositionByPosNameAndPosType(String posName, String posType) throws ThinventBaseException {
		Position position = positionDao.findOneByPosNameAndPosTypeAndEnable(posName, posType, 1);
		PositionVO positionVO = new PositionVO();
		if (position == null) {
			return null;
		}
		BeanUtils.copyProperties(position, positionVO);
		return positionVO;
	}

    @Override
    public PositionVO findOneByPosName(String posName) {
		Position position = positionDao.findOneByPosNameAndEnable(posName, 1);
		PositionVO positionVO = new PositionVO();
		if (position == null) {
			return null;
		}
		BeanUtils.copyProperties(position, positionVO);
		return positionVO;
    }
}
