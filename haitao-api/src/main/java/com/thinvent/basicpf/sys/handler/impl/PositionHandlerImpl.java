package com.thinvent.basicpf.sys.handler.impl;

import com.alibaba.fastjson.JSON;
import com.thinvent.basicpf.sys.adapt.IDepartmentAdapt;
import com.thinvent.basicpf.sys.adapt.IPositionAdapt;
import com.thinvent.basicpf.sys.handler.IPositionHandler;
import com.thinvent.basicpf.sys.util.PositionVerifyUtil;
import com.thinvent.library.Constants;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.DepartmentVO;
import com.thinvent.library.vo.PositionVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionHandlerImpl implements IPositionHandler {

    @Autowired
    private IPositionAdapt positionAdpat;
    
    @Autowired
	private IDepartmentAdapt departmentAdapt;
    
    private static final String STATUS = new String("status").intern();
	private static final String POSITION = new String("position").intern();
	private static final String POSITIONLIST = new String("positionList").intern();
	private static final String COUNT = new String("count").intern();

    @Override
    public PositionVO getByPosId(String posId) throws ThinventBaseException {
        return positionAdpat.getByPosId(posId);
    }

    @Override
    public void addPosition(PositionVO positionVO) throws ThinventBaseException {
        PositionVerifyUtil.verifyPos(positionVO);
        positionAdpat.addPosition(positionVO);
    }

    @Override
    public void updatePosition(PositionVO positionVO) throws ThinventBaseException {
        PositionVerifyUtil.verifyPos(positionVO);
        positionAdpat.updatePosition(positionVO);
    }

    @Override
    public void deletePosition(String posId) throws ThinventBaseException {
        positionAdpat.deletePosition(posId);
    }
    
    @SuppressWarnings("rawtypes")
    @Override
    public Map listPositionByCondition(String posName, String posType, Integer pageIndex, Integer pageSize) throws ThinventBaseException {
    	Map<String, Object> map = new HashMap<>();
		Map mapRet = positionAdpat.listPositionByConditions(posName, posType, pageIndex, pageSize);
		int count = (int) mapRet.get(COUNT);
		List<Map<String, Object>> mapList = (List<Map<String, Object>>) mapRet.get(POSITIONLIST);
		List<PositionVO> positionVOs = new ArrayList<>();
		for(Map m : mapList) {
			String jsonStr = m.toString();
			PositionVO pvo = JSON.parseObject(jsonStr, PositionVO.class);
			pvo.setDeptName(getDeptName(pvo.getDepId(), pvo.getPosType()));
			positionVOs.add(pvo);
		}
		map.put(STATUS, Constants.RES_SUCCESS);
		map.put(POSITION, positionVOs);
		map.put(COUNT, count);
		
		return map;
    }
    
    public String getDeptName(String depId, String posType) throws ThinventBaseException {
    	if(!"1".equals(posType))
    		return "";
    	DepartmentVO dvo = departmentAdapt.getDepartmentById(depId);
    	if(dvo == null)
    		return "";
    	return dvo.getDepName();
	}
    
	@Override
	public List listAllPosition() throws ThinventBaseException {
		return positionAdpat.listAllPosition();
	}

	public PositionVO getByName(String name) throws ThinventBaseException {
		return positionAdpat.getPositionByPosName(name);
	}
}
