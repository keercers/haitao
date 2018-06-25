package com.thinvent.basicpf.sys;

import static org.mockito.Mockito.when;

import com.alibaba.fastjson.JSONArray;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.basicpf.sys.adapt.IUserRoleAdapt;
import com.thinvent.basicpf.sys.handler.impl.UserRoleHandlerImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserRoleHandlerImplTest {

    @InjectMocks
    private UserRoleHandlerImpl userRoleHandlerImpl;

    @Mock
    private IUserRoleAdapt userRoleAdaptImpl;

    @Test
    public void queryUserIdsByRoleIds() throws ThinventBaseException{
        String roleIds = "roleIds";
        List userIdList = new ArrayList<>();
        String userId = "userId";
        userIdList.add(userId);
        String userIdSt = JSONArray.toJSONString(userIdList);
        when(userRoleAdaptImpl.queryUserIdsByRoleIds(roleIds)).thenReturn(userIdSt);
        userRoleHandlerImpl.queryUserIdsByRoleIds(roleIds);
    }
}
