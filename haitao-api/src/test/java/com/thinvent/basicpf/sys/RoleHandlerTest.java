package com.thinvent.basicpf.sys;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSONObject;
import com.thinvent.basicpf.sys.adapt.IRoleAdapt;
import com.thinvent.basicpf.sys.adapt.IUserAdapt;
import com.thinvent.basicpf.sys.handler.impl.RoleHandlerImpl;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.RoleVO;
import com.thinvent.library.vo.UserRoleVO;
import com.thinvent.library.vo.UserVO;

/**
 * Created by SCHX on 2017/8/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class RoleHandlerTest {
    @Mock
    private IRoleAdapt roleAdapt;
    @InjectMocks
    private RoleHandlerImpl roleHandler;
    @Mock
    private IUserAdapt userAdapt;
    
    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void findRoleByRoleId() throws ThinventBaseException {
        RoleVO roleVO = new RoleVO().setRoleId("1").setRoleName("roleName");
        when(roleAdapt.findRoleByRoleId("1")).thenReturn(roleVO);
        RoleVO result = roleHandler.findRoleByRoleId("1");
        Assert.assertTrue(result != null);
        Assert.assertEquals("1",result.getRoleId());
        Assert.assertEquals("roleName",result.getRoleName());
    }
    @Test
    public void getRoleList() throws ThinventBaseException {
        RoleVO roleVO = new RoleVO().setRoleId("1").setRoleName("roleName");
        JSONObject json = new JSONObject();
        List<UserRoleVO> listUserRoleVO = new ArrayList<>();
        UserRoleVO userRoleVO = new UserRoleVO().setRoleName("123").setRoleId("1");
        UserVO userVO = new UserVO();
        userVO.setUserName("3333");
        listUserRoleVO.add(userRoleVO);
        json.put("roleId",roleVO.getRoleId());
        json.put("roleName",roleVO.getRoleName());
        List<JSONObject> content = new ArrayList<>();
        content.add(json);
        JSONObject rolePage = new JSONObject();
        rolePage.put("roleList", content);
        rolePage.put("count", 1);
        when(roleAdapt.getRoleList("", "", 1, 10)).thenReturn(rolePage);
        when(userAdapt.findUserByUserId(userRoleVO.getUserId())).thenReturn(userVO);
        when(roleAdapt.getUserRoleByRoleId(roleVO.getRoleId())).thenReturn(listUserRoleVO);
        Map<String,Object> result= roleHandler.getRoleList("", "", 1, 10);
        Assert.assertTrue(result != null);
        List<RoleVO> li = (List)result.get("content");
        Assert.assertEquals("roleName",li.get(0).getRoleName());
    }
    @Test
    public void addRole() throws ThinventBaseException {
        RoleVO roleVO = new RoleVO().setRoleId("1").setRoleName("roleName");
        roleHandler.addRole(roleVO);
        ArgumentCaptor<RoleVO> argument = ArgumentCaptor.forClass(RoleVO.class);
        verify(roleAdapt, times(1)).add(argument.capture());
        Assert.assertEquals("roleName", argument.getValue().getRoleName());
        Assert.assertEquals("1", argument.getValue().getRoleId());
    }
    @Test
    public void updateRole() throws ThinventBaseException {
        RoleVO roleVO = new RoleVO().setRoleId("1").setRoleName("roleName");
        roleHandler.updateRole(roleVO);
        ArgumentCaptor<RoleVO> argument = ArgumentCaptor.forClass(RoleVO.class);
        verify(roleAdapt, times(1)).update(argument.capture());
        Assert.assertEquals("roleName", argument.getValue().getRoleName());
        Assert.assertEquals("1", argument.getValue().getRoleId());
    }
    @Test
    public void deleteRole() throws ThinventBaseException {
        roleHandler.deleteRole("1");
        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
        verify(roleAdapt, times(1)).delete(argument.capture());
        Assert.assertEquals("1", argument.getValue());
    }
    @Test
    public void findAllRole() throws  ThinventBaseException {
        RoleVO roleVO = new RoleVO().setRoleId("1").setRoleName("roleName");
        List<RoleVO> rvoList = new ArrayList<>();
        rvoList.add(roleVO);
        when(roleAdapt.findAllRole()).thenReturn(rvoList);
        List<RoleVO> result = roleHandler.findAllRole();
        Assert.assertTrue(result != null);
        Assert.assertEquals("1",result.get(0).getRoleId());
        Assert.assertTrue(result.get(0).getEnable() == 1);
        Assert.assertEquals("roleName",result.get(0).getRoleName());
    }
}
