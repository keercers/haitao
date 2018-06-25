package com.thinvent.basicpf.sys;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSONObject;
import com.thinvent.basicpf.sys.adapt.IDepartmentAdapt;
import com.thinvent.basicpf.sys.adapt.IPositionAdapt;
import com.thinvent.basicpf.sys.handler.impl.DepartmentHandlerImpl;
import com.thinvent.basicpf.sys.handler.impl.PositionHandlerImpl;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.DepartmentVO;
import com.thinvent.library.vo.PositionVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class PositionHandlerTest {

    @Mock
    private IPositionAdapt positionAdapt;
    
    @Mock
	private IDepartmentAdapt departmentAdapt;

    @InjectMocks
    private PositionHandlerImpl positionHandler;
    
    @InjectMocks
    private DepartmentHandlerImpl departmentHandler;
    
    @InjectMocks
    private PositionHandlerImpl positionHandlerImpl;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getByPosId() throws ThinventBaseException {
        PositionVO positionVO = new PositionVO();
        
        positionVO.setPosId("1");
        positionVO.setPosName("测试岗位-yekai");
        
        when(positionAdapt.getByPosId("1")).thenReturn(positionVO);
        
        PositionVO result = positionHandler.getByPosId("1");
        Assert.assertEquals("1", result.getPosId());
        Assert.assertEquals("测试岗位-yekai", result.getPosName());
    }
    
    @Test
    public void listPositionByCondition() throws ThinventBaseException {
    	PositionVO positionVO = new PositionVO();
    	DepartmentVO dvo = new DepartmentVO();
    	
    	String posName = "";
    	String posType = "";
    	int pageIndex = 1;
		int pageSize = 10;
		
		JSONObject json = new JSONObject();
		json.put("posId", positionVO.getPosId());
		
		List<Map<String, Object>> listMap = new ArrayList();
		Map<String,  Object> map = new HashMap();
		listMap.add(json);
		map.put("positionList", listMap);
		map.put("count", 1);
		
		when(departmentAdapt.getDepartmentById(positionVO.getDepId())).thenReturn(dvo);
		when(positionAdapt.getByPosId(positionVO.getPosId())).thenReturn(positionVO);
		when(positionAdapt.listPositionByConditions(posName, posType, pageIndex, pageSize)).thenReturn(map);
		
		Map ret = positionHandler.listPositionByCondition(posName, posType, pageIndex, pageSize);
		Object count = ret.get("count");
		String result = (String) ret.get("status");
		Assert.assertEquals(1, count);
		Assert.assertEquals("200", result);
    }
    
    @Test
    public void getDeptName() throws Exception {
    	DepartmentVO dvo = new DepartmentVO();
    	
//    	分支一 posType != 1
    	dvo.setDepId("1");
    	dvo.setDepName("测试岗位-zj");
    	
		String result = positionHandlerImpl.getDeptName("1", "2");
    	Assert.assertEquals("", result);
    	
//    	分支二 posType == 1 dvo == null 	
    	when(departmentAdapt.getDepartmentById("1")).thenReturn(null);
    	
		result = positionHandlerImpl.getDeptName("1", "1");
    	Assert.assertEquals("", result);
    	
//    	分支三 posType == 1 dvo != null 
    	dvo.setDepId("1");
    	dvo.setDepName("测试岗位-zj");
    	
    	when(departmentAdapt.getDepartmentById("1")).thenReturn(dvo);
    	
    	result = positionHandlerImpl.getDeptName("1", "1");
    	Assert.assertEquals("测试岗位-zj", result);
    }

    @Test
    public void addPosition() throws Exception {
        PositionVO positionVO = new PositionVO();
        
        positionVO.setPosId("1");
        positionVO.setPosName("测试岗位-yekai");
        
        positionHandler.addPosition(positionVO);
        verify(positionAdapt).addPosition(positionVO);
    }

    @Test
    public void updatePosition() throws Exception {
        PositionVO positionVO = new PositionVO();
        
        positionVO.setPosId("1");
        positionVO.setPosName("测试岗位-yekai");
        
        positionHandler.updatePosition(positionVO);
        verify(positionAdapt).updatePosition(positionVO);
    }

    @Test
    public void delete() throws Exception {
    	PositionVO positionVO = new PositionVO();
        
        positionVO.setPosId("1");
        positionVO.setPosName("posName");
        
        positionHandler.deletePosition("1");
        verify(positionAdapt).deletePosition("1");
    }
    
    @Test
    public void listAllPosition() throws Exception {
    	List<PositionVO> pvoList = new ArrayList<>();
    	PositionVO positionVO = new PositionVO();
        
        positionVO.setPosId("111");
        positionVO.setPosName("posName");
    	pvoList.add(positionVO);
    	
        when(positionAdapt.listAllPosition()).thenReturn(pvoList);
        List<PositionVO> result = positionHandler.listAllPosition();
        Assert.assertEquals("posName", result.get(0).getPosName());
    }
}
