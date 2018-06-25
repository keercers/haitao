package com.thinvent.basicpf.sys;

import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.DepartmentVO;
import com.thinvent.basicpf.sys.adapt.IDepartmentAdapt;
import com.thinvent.basicpf.sys.handler.impl.DepartmentHandlerImpl;
import com.thinvent.basicpf.sys.handler.impl.UserHandlerImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class DepartmentHandlerTest {
    @Mock
    private IDepartmentAdapt departmentAdapt;
    @InjectMocks
    private DepartmentHandlerImpl departmentHandler;
    @Mock
    private UserHandlerImpl userHandler;
    private DepartmentVO departmentVO = new DepartmentVO();

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        departmentVO.setDepId("3");
        departmentVO.setDepName("测试部门名称");
        departmentVO.setDepParentId("6");
        departmentVO.setEnable(1);
    }

    @Test
    public void getDeptById() throws ThinventBaseException {
        when(departmentAdapt.getDepartmentById("3")).thenReturn(departmentVO);
        DepartmentVO result = departmentHandler.getDeptById("3");
        Assert.assertTrue(result != null);
        Assert.assertEquals("3",result.getDepId());
        Assert.assertTrue(result.getEnable() == 1);
        Assert.assertEquals("测试部门名称",result.getDepName());
    }

    @Test
    public void addDepartment() throws ThinventBaseException {
        ArgumentCaptor<DepartmentVO> argument = ArgumentCaptor.forClass(DepartmentVO.class);
        departmentHandler.addDepartment(departmentVO);
        verify(departmentAdapt, times(1)).addDepartment(argument.capture());
        Assert.assertEquals("测试部门名称", argument.getValue().getDepName());
        Assert.assertEquals("6", argument.getValue().getDepParentId());
    }

    @Test
    public void updateDepartment() throws ThinventBaseException {
        departmentHandler.updateDepartment(departmentVO);
        ArgumentCaptor<DepartmentVO> argument = ArgumentCaptor.forClass(DepartmentVO.class);
        verify(departmentAdapt, times(1)).updateDepartment(argument.capture());
        Assert.assertEquals("测试部门名称", argument.getValue().getDepName());
        Assert.assertEquals("6", argument.getValue().getDepParentId());
    }

    @Test
    public void delDepartment() throws ThinventBaseException {
        departmentHandler.delDepartment("3");
        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
        verify(departmentAdapt, times(1)).delDepartment(argument.capture());
        Assert.assertEquals("3", argument.getValue());
    }

    @Test
    public void getAllDepartmentsById() throws ThinventBaseException {
        List<DepartmentVO> departmentVOList = new ArrayList<>();
        departmentVOList.add(departmentVO);
        when(departmentAdapt.getAllDepartmentsById("3")).thenReturn(departmentVOList);
        List<DepartmentVO> result = departmentHandler.getAllDepartmentsById("3");
        Assert.assertTrue(result != null);
        Assert.assertEquals("3",result.get(0).getDepId());
        Assert.assertTrue(result.get(0).getEnable() == 1);
        Assert.assertEquals("测试部门名称",result.get(0).getDepName());
    }

    @Test
    public void getTreeDepartmentsById() throws ThinventBaseException {
        List<Map> mapList = new ArrayList<>();
        Map map = new HashMap();
        map.put("id", "3");
        map.put("depParentId", "6");
        map.put("label", "测试部门名称");
        mapList.add(map);
        when(departmentAdapt.getTreeDepartmentsById("3")).thenReturn(mapList);
        List<Map> result = departmentHandler.getTreeDepartmentsById("3");
        Assert.assertTrue(result != null);
        Assert.assertEquals("3",result.get(0).get("id"));
    }

    @Test
    public void getTreeDepartmentsByUserId() throws ThinventBaseException {
        when(userHandler.getUserIdFromSession()).thenReturn("1");
        List<Map> mapList = new ArrayList<>();
        Map map = new HashMap();
        map.put("id", "3");
        map.put("depParentId", "6");
        map.put("label", "测试部门名称");
        mapList.add(map);
        when(departmentAdapt.getTreeDepartmentsByUserId("1")).thenReturn(mapList);
        List<Map> result = departmentHandler.getTreeDepartmentsByUserId("1");
        Assert.assertTrue(result != null);
        Assert.assertEquals("3",result.get(0).get("id"));
    }
    @Test
    public void getDeptByName() throws ThinventBaseException {
        when(departmentAdapt.getDepartmentByDepName("测试部门名称")).thenReturn(departmentVO);
        DepartmentVO result = departmentHandler.getDeptByName("测试部门名称");
        Assert.assertTrue(result != null);
        Assert.assertEquals("3",result.getDepId());
        Assert.assertTrue(result.getEnable() == 1);
        Assert.assertEquals("测试部门名称",result.getDepName());
    }

    @Test
    public void getDepartmentAllData() throws ThinventBaseException {
        when(departmentAdapt.getDepartmentAllData()).thenReturn("test1");
        String result = departmentHandler.getDepartmentAllData();
        Assert.assertEquals("test1",result);
    }
}
