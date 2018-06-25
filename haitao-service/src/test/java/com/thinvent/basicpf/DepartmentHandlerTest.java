package com.thinvent.basicpf;

import com.thinvent.basicpf.dao.IDepartmentDao;
import com.thinvent.basicpf.dao.IPositionDao;
import com.thinvent.basicpf.handler.impl.DepartmentHandlerImpl;
import com.thinvent.basicpf.handler.impl.UserHandlerImpl;
import com.thinvent.basicpf.model.Department;
import com.thinvent.basicpf.model.Role;
import com.thinvent.library.vo.DepartmentVO;
import com.thinvent.library.vo.UserVO;
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
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class DepartmentHandlerTest {
    @Mock
    private IDepartmentDao departmentDao;
    @Mock
    private IPositionDao positionDao;
    @Mock
    private UserHandlerImpl userHandler;

    @InjectMocks
    private DepartmentHandlerImpl departmentHandler;

    private Department department = new Department();

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        department.setDepId("test_depId").setDepName("测试部门名称").setDepParentId("3").setEnable(1);
    }

    @Test
    public void getDepartmentById() throws Exception {
        when(departmentDao.findOneByDepIdAndEnable("test_depId", 1)).thenReturn(department);
        Department result = departmentHandler.getDepartmentById("test_depId");
        Assert.assertTrue(result != null);
        Assert.assertTrue("test_depId".equals(result.getDepId()));
        Assert.assertTrue(result.getEnable() == 1);
        Assert.assertTrue("测试部门名称".equals(result.getDepName()));
    }

    @Test
    public void addDepartment() throws Exception {
        departmentHandler.addDepartment(department);
        ArgumentCaptor<Department> argument = ArgumentCaptor.forClass(Department.class);
        verify(departmentDao, times(1)).save(argument.capture());
        Assert.assertEquals("测试部门名称", argument.getValue().getDepName());
        Assert.assertEquals("test_depId", argument.getValue().getDepId());
    }

    @Test
    public void updateDepartment() throws Exception {
        when(departmentDao.findOneByDepIdAndEnable(department.getDepId(),1)).thenReturn(department);
        when(departmentDao.save(department)).thenReturn(department);
        departmentHandler.updateDepartment(department);
        ArgumentCaptor<Department> argument = ArgumentCaptor.forClass(Department.class);
        verify(departmentDao, times(1)).save(argument.capture());
        Assert.assertEquals("测试部门名称", argument.getValue().getDepName());
        Assert.assertEquals("test_depId", argument.getValue().getDepId());
    }

    @Test
    public void delDepartment() throws Exception {
        when(positionDao.existsByDepIdAndEnable(department.getDepId())).thenReturn(false);
        when(departmentDao.findOneByDepIdAndEnable(department.getDepId(),1)).thenReturn(department);
        departmentHandler.delDepartment("test_depId");
        Assert.assertTrue(department.getEnable() == 0);
    }

    @Test
    public void getAllDeptById() throws Exception {
        department.setDepId("6").setDepName("测试部门名称").setDepParentId("3").setEnable(1);
        Department department_child = new Department();
        department_child.setDepId("9").setDepName("测试部门名称2").setDepParentId("6").setEnable(1);
        List<Department> deptList_child = new ArrayList<>();
        deptList_child.add(department_child);
        when(departmentDao.findOneByDepIdAndEnable(department.getDepId(),1)).thenReturn(department);
        when(departmentDao.findByDepParentIdAndEnableOrderByDepSort(department.getDepId(),1)).thenReturn(deptList_child);
        when(departmentDao.findOneByDepIdAndEnable(department_child.getDepId(),1)).thenReturn(department_child);
        List<Department> resultList = departmentHandler.getAllDeptById("6");
        Assert.assertTrue(resultList!=null&&!resultList.isEmpty());
        Assert.assertTrue("6".equals(resultList.get(0).getDepId()));
        Assert.assertTrue("9".equals(resultList.get(1).getDepId()));
        when(departmentDao.findByDepParentIdAndEnableOrderByDepSort(department.getDepId(),1)).thenReturn(new ArrayList<>());
        resultList = departmentHandler.getAllDeptById("6");
        Assert.assertTrue(resultList!=null&&!resultList.isEmpty());
    }

    @Test
    public void getDeptTreeByUserId() throws Exception {
        UserVO userVo = new UserVO();
        userVo.setUserId("1");
        userVo.setDepId("6");
        userVo.setEnable(1);
        when(userHandler.findUserByUserId(userVo.getUserId())).thenReturn(userVo);
        department.setDepId("6").setDepName("测试部门名称").setDepParentId("3").setEnable(1);
        Department department_child = new Department();
        department_child.setDepId("9").setDepName("测试部门名称2").setDepParentId("6").setEnable(1);
        List<Department> deptList_child = new ArrayList<>();
        deptList_child.add(department_child);
        when(departmentDao.findOneByDepIdAndEnable(department.getDepId(),1)).thenReturn(department);
        when(departmentDao.findByDepParentIdAndEnableOrderByDepSort(department.getDepId(),1)).thenReturn(deptList_child);
        when(departmentDao.findOneByDepIdAndEnable(department_child.getDepId(),1)).thenReturn(department_child);
        List<Map> resultList = departmentHandler.getDeptTreeByUserId("1");
        Assert.assertTrue(resultList!=null&&!resultList.isEmpty());
        when(departmentDao.findOneByDepIdAndEnable(department.getDepId(),1)).thenReturn(department).thenReturn(null);
        when(departmentDao.findOneByDepIdAndEnable(department_child.getDepId(),1)).thenReturn(null);
        when(departmentDao.findByDepParentIdAndEnableOrderByDepSort(department.getDepId(),1)).thenReturn(new ArrayList<>());
        resultList = departmentHandler.getDeptTreeByUserId("1");
        Assert.assertTrue(resultList!=null);
        when(departmentDao.findOneByDepIdAndEnable(department.getDepId(),1)).thenReturn(department).thenReturn(null);
        when(departmentDao.findOneByDepIdAndEnable(department_child.getDepId(),1)).thenReturn(null);
        when(departmentDao.findByDepParentIdAndEnableOrderByDepSort(department.getDepId(),1)).thenReturn(null);
        resultList = departmentHandler.getDeptTreeByUserId("1");
        Assert.assertTrue(resultList!=null);
    }

    @Test
    public void getDepartmentByDeptName() throws Exception {
        when(departmentDao.findOneByDepNameAndEnable("测试部门名称", 1)).thenReturn(department);
        Department result = departmentHandler.getDepartmentByDeptName("测试部门名称");
        Assert.assertTrue(result != null);
        Assert.assertTrue("test_depId".equals(result.getDepId()));
        Assert.assertTrue(result.getEnable() == 1);
        Assert.assertTrue("测试部门名称".equals(result.getDepName()));
    }

    @Test
    public void getDepartmentByName() throws Exception {
        List<Department> deptList = new ArrayList<>();
        deptList.add(department);
        when(departmentDao.findByDepNameAndEnable("测试部门名称",1)).thenReturn(deptList);
        List<DepartmentVO> result = departmentHandler.getDepartmentByName("测试部门名称");
        Assert.assertTrue(result != null);
        Assert.assertTrue("test_depId".equals(result.get(0).getDepId()));
        Assert.assertTrue(result.get(0).getEnable() == 1);
        Assert.assertTrue("测试部门名称".equals(result.get(0).getDepName()));
    }


    @Test
    public void getDepartmentAllData() throws Exception {
        List<Department> deptList = new ArrayList<>();
        deptList.add(department);
        when(departmentDao.findByEnable(1)).thenReturn(deptList);
        String result = departmentHandler.getDepartmentAllData();
        Assert.assertTrue(result != null);
    }
}
