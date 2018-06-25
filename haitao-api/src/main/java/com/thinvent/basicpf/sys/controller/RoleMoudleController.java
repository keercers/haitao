package com.thinvent.basicpf.sys.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thinvent.basicpf.sys.handler.IRoleMoudleHandler;
import com.thinvent.library.Constants;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.JWTConfig;
import com.thinvent.library.vo.RoleMoudle;
import com.thinvent.library.vo.UserVO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "sys/rolemoudle")
@Slf4j
public class RoleMoudleController {

	@Autowired
	private IRoleMoudleHandler roleMoudleHandler;

	@RequestMapping(value = "/ListRoleMoudleByRoleId")
	@ApiOperation(value = "通过角色ID查找角色权限", notes="查找列表", responseContainer="列表", response = RoleMoudle.class)
	@ApiImplicitParam(name="roleId", value="roleId", required = true, dataType = "string", paramType = "query")
	public String getListByRoleId(@RequestParam(required = true, value = "roleId") String roleId) throws ThinventBaseException{
		return roleMoudleHandler.getListByRoleId(roleId);
	}
	
	@RequestMapping(value = "/saveRoleMoudle")
	@ApiOperation(value = "通过角色ID保存角色权限", notes="保存角色权限", responseContainer="保存", response = String.class)
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name="roleId", value="roleId", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name="choices[]", value="choices[]", required = true, dataType = "choices[]", paramType = "save")
			})
	public String save(@RequestParam(required = true, value = "roleId") String roleId, @RequestParam(required = true, value = "choices[]") long[] choices, HttpServletRequest request) throws ThinventBaseException{
		String tokenIn = request.getHeader(Constants.TOKEN_KEY);
		UserVO user = null;
		try {
			user = JWTConfig.getUserByToken(tokenIn);
		} catch (Exception e) {
			log.error("roleMoudle saveRoleMoudle can not find userId(unlegal login): ", e);
		}
		String result = "";
		String moudelIdList = "";
		if(user != null) {
			if(choices.length > 0){
				for(int i = 0; i < choices.length; i++){		
					moudelIdList += "-"+choices[i];
				}
				result = roleMoudleHandler.save(roleId, user.getUserId(), moudelIdList.substring(1));
			}else{
				result = roleMoudleHandler.save(roleId, user.getUserId(), moudelIdList);
			}
		}
		return result;
	}
}
