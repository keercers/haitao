package com.thinvent.basicpf.sys.adapt.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.GetPostUtil;
import com.thinvent.library.vo.PositionVO;
import com.thinvent.basicpf.sys.adapt.IPositionAdapt;
import com.thinvent.basicpf.sys.util.URLUtil;

@Service
public class PositionAdaptImpl implements IPositionAdapt {
	private String basicUrl =  URLUtil.getUrl();

    @Override
    public PositionVO getByPosId(String posId) throws ThinventBaseException {
        String url = basicUrl + "position/getByPosId?posId=" + posId;
        String json = GetPostUtil.sendGet(url);
        if (json != null && !"".equals(json)) {
            return JSON.parseObject(json, PositionVO.class);
        } else
            return null;
    }

    @Override
    public void addPosition(PositionVO positionVO) throws ThinventBaseException {
        String url = basicUrl + "position/addPosition";
        String params = JSON.toJSONString(positionVO);
        GetPostUtil.sendPost(url, params);
    }

    @Override
    public void updatePosition(PositionVO positionVO) throws ThinventBaseException {
        String url = basicUrl + "position/updatePosition";
        String params = JSON.toJSONString(positionVO);
        GetPostUtil.sendPost(url, params);
    }

    @Override
    public void deletePosition(String posId) throws ThinventBaseException {
        String url = basicUrl + "position/delPosition?posId=" + posId;
        GetPostUtil.sendGet(url);
    }

    /**
     * add by wcs
     */
	@Override
	public List<PositionVO> getPosListByPosName(String posName, Integer pageIndex, Integer pageSize)
			throws ThinventBaseException {
		
		String url = basicUrl + "position/getPosByName?posName="+posName+"&pageIndex="+pageIndex+"&pageSize="+pageSize;
        String json = GetPostUtil.sendGet(url);
        if (json != null && !"".equals(json)) {
            return JSON.parseArray(json, PositionVO.class);
        } else
            return Collections.emptyList();
	}
	
	@Override
    public Map listPositionByConditions(String posName, String posType, 
    		int pageIndex, int pageSize) throws ThinventBaseException {
		Map map = new HashMap<>();
		String url = basicUrl+"position/listPositionByConditions?pageIndex="+pageIndex
				+"&pageSize="+pageSize+"&posName="+posName+"&posType="+posType;
		String json = GetPostUtil.sendGet(url);
		if(json!=null && !"".equals(json)){
			map = JSON.parseObject(json, Map.class);
		}
		return map;
	}

	@Override
	public List listAllPosition() throws ThinventBaseException {
		String url = basicUrl + "position/listAllPositionDistinctByName";
		String json = GetPostUtil.sendGet(url);
        if (json != null && !"".equals(json)) {
            return JSON.parseArray(json, PositionVO.class);
        } else
            return Collections.emptyList();
	}

	@Override
	public PositionVO getPositionByPosNameAndPosType(String posName, String posType) throws ThinventBaseException {
		String url = basicUrl + "position/getPositionByPosNameAndPosType?posName=" + posName + "&posType=" + posType;
        String json = GetPostUtil.sendGet(url);
        if (json != null && !"".equals(json)) {
            return JSON.parseObject(json, PositionVO.class);
        } else
            return null;
	}

	@Override
	public PositionVO getPositionByPosName(String posName) throws ThinventBaseException {
		String url = basicUrl + "position/getPositionByPosName?posName=" + posName;
        String json = GetPostUtil.sendGet(url);
        if (json != null && !"".equals(json)) {
            return JSON.parseObject(json, PositionVO.class);
        } else
            return null;
	}
}
