package com.thinvent.basicpf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
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
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.google.common.collect.Lists;
import com.thinvent.basicpf.dao.IPositionDao;
import com.thinvent.basicpf.dao.IUserDao;
import com.thinvent.basicpf.dao.jpa.PositionDaoRepositoryImpl;
import com.thinvent.basicpf.handler.impl.PositionHandlerImpl;
import com.thinvent.basicpf.model.Position;
import com.thinvent.library.vo.PositionVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class PositionHandlerTest {
	
    @InjectMocks
    private PositionHandlerImpl positionHandler;

    @Mock
    private IPositionDao positionDao;

    @Mock
	private IUserDao userDao;
    
    @Mock
    private PositionDaoRepositoryImpl positionDaoRepository;;
    
    Pageable pageable = new PageRequest(0, 100, new Sort(Sort.Direction.DESC, "updateTime"));

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findByPosName() throws Exception {
    	Position position = new Position();
        List<Position> positions = Lists.newArrayList();
        
        position.setPosName("test_name");
        position.setPosLevel(1);
        positions.add(position);
        
        when(positionDao.listPosByPosName(position.getPosName(), pageable)).thenReturn(positions);
        
        List<PositionVO> listResult = positionHandler.findByPosName(position.getPosName(), pageable);
        Assert.assertEquals(1, listResult.get(0).getPosLevel());
        Assert.assertEquals("test_name", listResult.get(0).getPosName());
    }

    @Test
    public void findByPosId() throws Exception {
    	Position position = new Position();
    	
    	position.setPosId("test_posId");
    	position.setPosName("test_name");
        position.setPosLevel(1);
        
//    	分支一  position != null
        when(positionDao.findOneByPosIdAndEnable(position.getPosId(), 1)).thenReturn(position);
        
        PositionVO result = positionHandler.findByPosId(position.getPosId());
        Assert.assertEquals("test_posId", result.getPosId());
        Assert.assertEquals("test_name", result.getPosName());
        
//        分支二 position == null
        when(positionDao.findOneByPosIdAndEnable("test_posId", 1)).thenReturn(null);
        result = positionHandler.findByPosId(position.getPosId());
        Assert.assertEquals(null, result);
    }

    @Test
    public void listAllPositionByCondition() throws Exception {
    	PositionVO positionVO = new PositionVO();
    	Map<String,  Object> map = new HashMap();
    	List<PositionVO> pvoList = new ArrayList<>();
    	
    	positionVO.setPosId("test_posId");
    	positionVO.setPosName("test_name");
    	positionVO.setPosType("0");
    	pvoList.add(positionVO);
        map.put("prisonerList", pvoList);
        
    	when(positionDaoRepository.listAllPositionByCondition(pageable, "", "")).thenReturn(map);
    	
    	Map result = positionHandler.listAllPositionByCondition(pageable, "", "");
    	Assert.assertTrue(result != null);
    }

    @Test
    public void addPosition() throws Exception {
    	Position position = new Position();
    	
    	position.setPosName("test_name");
        position.setPosLevel(1);
        position.setDepId("");
        
        positionHandler.addPosition(position);
        ArgumentCaptor<Position> argumentCaptor = ArgumentCaptor.forClass(Position.class);
        verify(positionDao, times(1)).save(argumentCaptor.capture());
        assertEquals("", argumentCaptor.getValue().getDepId());
        assertEquals("test_name", argumentCaptor.getValue().getPosName());
        
        position.setDepId("test");
        positionHandler.addPosition(position);
        
        ArgumentCaptor<Position> argumentCaptor1 = ArgumentCaptor.forClass(Position.class);
        verify(positionDao, times(2)).save(argumentCaptor1.capture());
        assertEquals("test", argumentCaptor1.getValue().getDepId());
    }

    @Test
    public void updatePosition() throws Exception {
    	Position position = new Position();
    	
    	position.setPosId("test_posId");
    	position.setPosName("test_name");
    	position.setDepId("");
        
        when(positionDao.findOneByPosIdAndEnable(position.getPosId(), 1)).thenReturn(position);
        positionHandler.updatePosition(position);
        
        ArgumentCaptor<Position> argumentCaptor = ArgumentCaptor.forClass(Position.class);
        verify(positionDao, times(1)).save(argumentCaptor.capture());
        assertEquals("", argumentCaptor.getValue().getDepId());
        assertEquals("test_name", argumentCaptor.getValue().getPosName());
        
        //分支二
        position.setPosName("test_name_branch");
        position.setDepId("test");
        when(positionDao.findOneByPosIdAndEnable(position.getPosId(), 1)).thenReturn(position);
        positionHandler.updatePosition(position);
        
        ArgumentCaptor<Position> argumentCaptor1 = ArgumentCaptor.forClass(Position.class);
        verify(positionDao, times(2)).save(argumentCaptor1.capture());
        assertEquals("test", argumentCaptor1.getValue().getDepId());
        assertEquals("test_name_branch", argumentCaptor1.getValue().getPosName());
    }

    @Test
    public void deletePosition() throws Exception {
    	Position position = new Position();
    	
    	position.setPosId("test_posId");
    	position.setPosName("test_name");
        position.setPosLevel(1);
        
    	when(userDao.existsByPosIdAndEnable(position.getPosId())).thenReturn(false);
        when(positionDao.findOneByPosIdAndEnable(position.getPosId(), 1)).thenReturn(position);
        
        positionHandler.deletePosition(position.getPosId());
        verify(positionDao).save(position);
        Assert.assertEquals(0, position.getEnable());
    }
    
    @Test
    public void listAllPositionDistinctByName() throws Exception {
    	List<Position> positions = Lists.newArrayList();
    	Position position = new Position();
    	
    	position.setPosId("test_posId");
    	position.setPosName("test_name");
        positions.add(position);
        
        when(positionDao.listAllPositionDistinctByName()).thenReturn(positions);
        
        List<PositionVO> result = positionHandler.listAllPositionDistinctByName();
        Assert.assertEquals("test_name", result.get(0).getPosName());
    }
    
    @Test
    public void getPositionByPosNameAndPosType() throws Exception {
    	Position position = new Position();
    	
    	position.setPosId("test_posId");
    	position.setPosName("test_name");
    	position.setPosType("test_type");
    	
    	when(positionDao.findOneByPosNameAndPosTypeAndEnable(position.getPosName(), position.getPosType(), 1)).thenReturn(position);
    	
    	PositionVO result = positionHandler.getPositionByPosNameAndPosType(position.getPosName(), position.getPosType());
    	
    	assertEquals("test_name", result.getPosName());
    	assertEquals("test_type", result.getPosType());
    	
    	//分支二
    	when(positionDao.findOneByPosNameAndPosTypeAndEnable(position.getPosName(), position.getPosType(), 1)).thenReturn(null);
    	result = positionHandler.getPositionByPosNameAndPosType(position.getPosName(), position.getPosType());
    	
    	assertNull(result);
    }
    
    @Test
    public void findOneByPosName() throws Exception {
    	Position position = new Position();
    	
    	position.setPosId("test_posId");
    	position.setPosName("test_name");
    	when(positionDao.findOneByPosNameAndEnable(position.getPosName(), 1)).thenReturn(position);
    	PositionVO result = positionHandler.findOneByPosName(position.getPosName());
    	assertEquals(result.getPosName(), "test_name");
    	
    	when(positionDao.findOneByPosNameAndEnable(position.getPosName(), 1)).thenReturn(null);
    	result = positionHandler.findOneByPosName(position.getPosName());
    	assertNull(result);
    }
}
