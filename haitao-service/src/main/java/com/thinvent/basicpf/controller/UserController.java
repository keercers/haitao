package com.thinvent.basicpf.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.thinvent.basicpf.handler.IUserHandler;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.UserVO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "user")
public class UserController {

	@Autowired
	private IUserHandler userService;
	
	@GetMapping(value = "/getUsersByDepId")
    @ApiOperation(value = "用户--部门下所有用户", notes = "根据部门查询用户")
	@ApiImplicitParam(name="depId",required=true, value = "部门Id", dataType = "string", paramType = "query")
    public List<UserVO> getUsersByDepId(@RequestParam(value = "depId", required = true) String depId) throws ThinventBaseException {
        return this.userService.getUsersByDepId(depId);
    }
	
	/** 
	 * 根据用户登录名获取用户信息
	 * @author wangxu
	 * @param loginName 登录名
	 * @throws ThinventBaseException 
	 */
	@RequestMapping(value="/getUserByLoginName", method = RequestMethod.GET)
	@ApiOperation(value="用户--用户查询", notes="根据登录名查询用户")
	@ApiImplicitParam(name="loginName",required=true, value = "登录名", dataType = "string", paramType = "query")
	@ResponseBody
	public Object findUserByLoginName(@RequestParam(value = "loginName", required = true) String loginName, HttpServletRequest request,
			HttpServletResponse response) throws ThinventBaseException {
		return userService.findUser(loginName);
	}
	
	/** 
	 * 根据用户ID获取用户信息
	 * @author wangxu
	 * @param userId 用户ID
	 */
	@RequestMapping(value="/getUserByUserId", method = RequestMethod.GET)
	@ApiOperation(value="用户--用户查询", notes="根据用户ID查询用户")
	@ApiImplicitParam(name="userId",required=true, value = "用户ID", dataType = "string", paramType = "query")
	@ResponseBody
	public Object findUserByUserId(@RequestParam(value = "userId", required = true) String userId) throws ThinventBaseException {
		return userService.findUserByUserId(userId);
	}
	
	/** 
	 * 保存用户
	 * @author wangxu
	 * @param userVO 用户信息
	 */
	@RequestMapping(value="/saveUser", method = RequestMethod.POST)
	@ApiOperation(value="用户--用户保存", notes="保存用户")
	@ApiImplicitParam(name="userVO",required=true, value = "用户", dataType = "UserVO")
	public void saveUser(@RequestBody UserVO userVO) {
		this.userService.save(userVO);
	}
	
	@RequestMapping(value="/saveUserList", method = RequestMethod.POST)
	@ApiOperation(value="用户--用户保存", notes="保存用户")
	@ApiImplicitParam(name="userVO",required=true, value = "用户", dataType = "string")
	public void saveUserList(@RequestBody List<UserVO> uvoList) throws ThinventBaseException{
		this.userService.saveUserList(uvoList);
	}
	
	/** 
	 * 修改用户部分信息
	 * @author wangxu
	 * @param userVO 用户信息
	 */
	@RequestMapping(value="/updateUser", method = RequestMethod.POST)
	@ApiOperation(value="用户--修改用户", notes="修改用户")
	@ApiImplicitParam(name="userVO",required=true, value = "用户", dataType = "UserVO")
	public void updateUser(@RequestBody UserVO userVO) throws ThinventBaseException{
		this.userService.updateUser(userVO);
	}
	
	/** 
	 * 修改用户部分信息
	 * @author wangxu
	 * @param userVO 用户信息
	 */
	@RequestMapping(value="/deleteUser", method = RequestMethod.GET)
	@ApiOperation(value="用户--删除用户", notes="删除用户")
	@ApiImplicitParam(name="userId",required=true, value = "用户", dataType = "string")
	public void deleteUser(@RequestParam String userId) throws ThinventBaseException{
		this.userService.deleteUser(userId);
	}
	
	/** 
	 * 修改用户全部信息
	 * @author wcs
	 * @param userVO 用户信息
	 */
	@RequestMapping(value="/updateUserAllInfo", method = RequestMethod.POST)
	@ApiOperation(value="用户--用户修改", notes="修改用户")
	@ApiImplicitParam(name="userVO",required=true, value = "用户", dataType = "UserVO")
	public void updateUserAllInfo(@RequestBody UserVO userVO) throws ThinventBaseException{
		this.userService.updateUserAllInfo(userVO);
	}
	
	/**
	 * 多条件查询员工信息
	 * @author wcs
	 * @param pageSize
	 * @param pageIndex
	 * @param loginName
	 * @param userName
	 * @param deptIds
	 * @param posIds
	 * @return
	 */
	@RequestMapping(value="/listUserByConditions", method = RequestMethod.POST)
	@ApiOperation(value="人员--人员列表", notes="动态查询员工信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageSize", required = true, value = "页大小", dataType = "int", paramType = "query"),
		@ApiImplicitParam(name = "pageIndex", required = true, value = "页数", dataType = "int", paramType = "query"),
		@ApiImplicitParam(name = "isAll", required = true, value = "是否全部数据", dataType = "int", paramType = "query"),
		@ApiImplicitParam(name = "loginName", required = false, value = "登录名", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "userName", required = false, value = "用户名", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "deptIds", required = false, value = "部门id", dataType = "list", paramType = "query"),
		@ApiImplicitParam(name = "posIds", required = false, value = "岗位id", dataType = "list", paramType = "query")
	})
	@ResponseBody
	public Object listUserByConditions(@RequestParam(value = "pageSize", required = true) int pageSize,
			@RequestParam(value = "pageIndex", required = true) int pageIndex,
			@RequestParam(value = "isAll", required = true) int isAll,
			@RequestParam(value = "loginName", required = false) String loginName,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "deptIds", required = false) List<String> deptIds,
			@RequestParam(value = "posIds", required = false) List<String> posIds) {
		Pageable pageable = new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "id"));
		if (isAll == 0) {
			return this.userService.listAllUserByCondition(pageable, deptIds, posIds, loginName, userName);
		}else {
			return this.userService.listAllUserByCondition(null, deptIds, posIds, loginName, userName);
		}
	}
	
	@ApiOperation(value="人员--人员列表", notes="查询全部员工信息")
	@RequestMapping(value="/listAllUser", method = RequestMethod.GET)
	public Object listAllUser() {
		return this.userService.listAllUser();
	}
}
