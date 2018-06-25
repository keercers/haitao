package com.thinvent.basicpf.sys.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.thinvent.basicpf.sys.handler.IUserHandler;
import com.thinvent.basicpf.zhhd.util.ImportUtil;
import com.thinvent.library.Constants;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.BeanOperateUtil;
import com.thinvent.library.util.ExportUtil;
import com.thinvent.library.util.StringUtil;
import com.thinvent.library.vo.UserVO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "sys/user")
public class UserController {
	
	@Autowired
	private IUserHandler userHandle;
	
	// 航道系统导出
	private String[] userHeader = {"用户名", "人员姓名", "部门", "岗位", "电话号码"};
	private String[] userHeaderEn = {"loginName", "userName", "deptName", "posName", "phone"};
	/**
	 * 请勿覆盖
	 * @author tsm
	 * @param loginName
	 * @param request
	 * @param response
	 * @return
	 * @throws ThinventBaseException
	 */
	@RequestMapping(value = "/getUserByLoginName", method = RequestMethod.GET)
	@ApiOperation(value = "根据登录名查找用户信息", notes = "用户")
	@ApiImplicitParam(name = "loginName", required = true, value = "登录名", dataType = "string", paramType = "query")
	@ResponseBody
	public Object findUserByLoginName(@RequestParam(value = "loginName", required = true) String loginName,
			HttpServletRequest request, HttpServletResponse response) throws ThinventBaseException {
		return userHandle.findUserInfoByLoginName(loginName);
	}
	
	/** 
	 * 用户登录
	 * @author wangxu
	 * @param loginName 登录名
	 * @param password 密码
	 * @return 200:正确；300：用户不存在；301：密码不正确
	 * @throws ThinventBaseException 
	 */
	@RequestMapping(value="/login", method = RequestMethod.GET)
	@ApiOperation(value="用户--登录", notes="登录校验")
	@ApiImplicitParams({
		@ApiImplicitParam(name="loginName",required=true, value = "登录名", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name="password",required=true, value = "密码", dataType = "string", paramType = "query")
	})
	@ResponseBody
	public Object login(@RequestParam(value = "loginName", required = true) String loginName,
			@RequestParam(value = "password", required = true) String password, HttpServletRequest request,
			HttpServletResponse response) throws ThinventBaseException {
		Map<String, Object> map = userHandle.checkUserByLoginName(loginName, password);
		response.addHeader(Constants.TOKEN_KEY, map.get(Constants.TOKEN_KEY).toString());
		log.info("token:"+map.get(Constants.TOKEN_KEY).toString());
		map.remove(Constants.TOKEN_KEY);
		return map;
	}
	
	/** 
	 * 用户退出
	 * @author wangxu
	 * @return 200:正确；300：失败；
	 */
	@RequestMapping(value="/logout", method = RequestMethod.POST)
	@ApiOperation(value="用户--退出", notes="用户退出")
	@ResponseBody
	public Object logout() {
		return userHandle.logOut();
	}
	
	/** 
	 * 密码修改
	 * @author wangxu
	 * @return 200:正确；300：失败；
	 */
	@RequestMapping(value="/changePass", method = RequestMethod.GET)
	@ApiOperation(value="用户--修改密码", notes="密码修改")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userId",required=true, value = "用户ID", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name="oldPassword",required=true, value = "旧密码", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name="newPassword",required=true, value = "新密码", dataType = "string", paramType = "query")
	})
	@ResponseBody
	public Object changePass(@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "oldPassword", required = true) String oldPassword,
			@RequestParam(value = "newPassword", required = true) String newPassword) throws ThinventBaseException {
		return userHandle.changePassword(userId, oldPassword, newPassword);
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	@ApiOperation(value="用户--重置密码", notes="重置密码")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userId",required=true, value = "userId", dataType = "string", paramType = "query")
	})
	@ResponseBody
	public void resetPassword(@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "newPwd", required = true) String newPwd) throws ThinventBaseException {
		userHandle.resetPassword(userId, newPwd);
	}
	
	/**
	 * 删除人员
	 * @author wcs
	 * @param deptId
	 * @return
	 */
	@RequestMapping(value="/deleteUser", method = RequestMethod.GET)
	@ApiOperation(value="人员--人员列表", notes="删除人员")
	@ApiImplicitParam(name = "userId", required = true, value = "用户ID", dataType = "string", paramType = "form")
	@ResponseBody
	public void deleteUser(@Valid @RequestParam(value = "userId", required = true) String userId) throws ThinventBaseException {
		this.userHandle.deleteUser(userId);
	}
	
	/**
	 * 创建人员
	 * @author wcs
	 * @param userVO
	 * @return
	 */
	@RequestMapping(value="/createUser", method = RequestMethod.POST)
	@ApiOperation(value="人员--人员列表", notes="创建人员")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userVO", required = true, value = "用户", dataType = "UserVO", paramType = "body")
	})
	@ResponseBody
	public void createUser(@Valid @RequestBody UserVO userVO) throws ThinventBaseException {
		userHandle.saveUser(userVO);
	}
	
	/**
	 * 更新人员
	 * @author wcs
	 * @param userVO
	 * @return
	 */
	@RequestMapping(value="/updateUser", method = RequestMethod.POST)
	@ApiOperation(value="人员--人员列表", notes="更新人员信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userVO", required = true, value = "用户", dataType = "UserVO", paramType = "body")
	})
	@ResponseBody
	public void updateUser(@Valid @RequestBody UserVO userVO) throws ThinventBaseException {
		userHandle.updateUser(userVO);
	}
	
	/**
	 * 查询人员详细信息
	 * @author wcs
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/getUserInfo", method = RequestMethod.GET)
	@ApiOperation(value="人员--人员列表", notes="查询人员详细信息")
	@ApiImplicitParam(name = "userId", required = true, value = "用户ID", dataType = "string", paramType = "query")
	@ResponseBody
	public Object getUserInfo(@RequestParam(value = "userId", required = true) String userId) throws ThinventBaseException {
		return this.userHandle.getUserInfo(userId);
	}
	
	/**
	 * 根据条件查询人员信息
	 * @author wcs
	 * @param deptId
	 * @param posName
	 * @param loginName
	 * @param username
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 * @throws ThinventBaseException
	 */
	@RequestMapping(value="/listUserByParams", method = RequestMethod.GET)
	@ApiOperation(value="人员--人员列表", notes="条件查询人员")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "deptId", required = false, value = "部门ID", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "posName", required = false, value = "岗位名称", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "loginName", required = false, value = "登录名", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "userName", required = false, value = "用户名", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "pageSize", required = true, value = "页大小", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "pageIndex", required = true, value = "页数", dataType = "string", paramType = "query")
	})
	public Object listUserByParams(@RequestParam(value = "deptId", required = false) String deptId,
			@RequestParam(value = "posName", required = false) String posName,
			@RequestParam(value = "loginName", required = false) String loginName,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "pageSize", required = true) int pageSize,
			@RequestParam(value = "pageIndex", required = true) int pageIndex) throws ThinventBaseException {
		return this.userHandle.listUserByCondition(deptId, posName, loginName, userName, pageSize, pageIndex, 0);
	}
	
	@RequestMapping(value="/listAllUser", method = RequestMethod.GET)
	public Object listAllUser() throws ThinventBaseException {
		return this.userHandle.listAllUser();
	}
	
	/**
	 * @author wcs
	 * @param workbook
	 * @param sheet
	 * @throws IOException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/exportUser", method = RequestMethod.GET)
	@ApiOperation(value="人员--人员列表导出", notes="导出部门人员")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "deptId", required = false, value = "部门ID", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "posName", required = false, value = "岗位名称", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "loginName", required = false, value = "登录名", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "userName", required = false, value = "用户名", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "pageSize", required = true, value = "页大小", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "pageIndex", required = true, value = "页数", dataType = "string", paramType = "query")
	})
	@ResponseBody
	public void exportExcel(@RequestParam(value = "deptId", required = false) String deptId,
			@RequestParam(value = "posName", required = false) String posName,
			@RequestParam(value = "loginName", required = false) String loginName,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "pageSize", required = true) int pageSize,
			@RequestParam(value = "pageIndex", required = true) int pageIndex,
    		HttpServletResponse response) throws ThinventBaseException, IOException {
		Map map = this.userHandle.listUserByCondition(deptId, posName, loginName, userName, pageSize, pageIndex, 1);
		List<UserVO> entities = (List<UserVO>) map.get("user");
		List<Map> mapList = new ArrayList<>();
		
		for (UserVO uvo : entities) {
			mapList.add(BeanOperateUtil.convertToMap(uvo));
		}
		
		ExportUtil.util(mapList, userHeaderEn, Arrays.asList(userHeader), response, "export-user_");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	@RequestMapping(value="/importUser", method = RequestMethod.POST)
	@ApiOperation(value="人员--人员列表导入", notes="导入部门人员")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "filename", required = false, value = "excel文件", dataType = "string", paramType = "query")
	})
	@ResponseBody
	public String uploadExcel(@RequestParam(value="file", required = true) MultipartFile file) throws IOException, ThinventBaseException {
		ImportUtil importUtil = new ImportUtil();
		
		InputStream inputStream = file.getInputStream();
		List<UserVO> userList = new ArrayList();
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);  
		HSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = sheet.iterator();
		while (iterator.hasNext()) {
			Row row = iterator.next();
			if (row.getRowNum() == 0 || importUtil.isRowEmpty(row)) {
				continue;
			}
			UserVO vo = new UserVO();
			Cell cell0 = row.getCell(0);
			cell0.setCellType(CellType.STRING);
			vo.setLoginName(StringUtil.getCellValue(cell0));
			Cell cell1 = row.getCell(1);
			cell1.setCellType(CellType.STRING);
			vo.setUserName(StringUtil.getCellValue(cell1));
			Cell cell2 = row.getCell(2);
			cell2.setCellType(CellType.STRING);
			vo.setDeptName(StringUtil.getCellValue(cell2));
			Cell cell3 = row.getCell(3);
			cell3.setCellType(CellType.STRING);
			vo.setPosName(StringUtil.getCellValue(cell3));
			Cell cell4 = row.getCell(4);
			cell4.setCellType(CellType.STRING);
			vo.setPhone(StringUtil.getCellValue(cell4));
			userList.add(vo);
		}
		
	    Map<String, Object> map = userHandle.importUser(userList);
	    List<Integer> emptyStr = (List<Integer>) map.get("emptyStr");
		List<Integer> loginName = (List<Integer>) map.get("loginName");
		List<Integer> deptName = (List<Integer>) map.get("deptName");
		List<Integer> posName = (List<Integer>) map.get("posName");
		int countUser = (Integer) map.get("countUser");
		//导入人员成功
		if(countUser == userList.size()) {
			return Constants.RES_SUCCESS + "，"  + Integer.toString(countUser);
		} else if(countUser >= 0 && countUser < userList.size()) {
			return "504" + "，" + listToString(emptyStr, loginName, deptName, posName);
		}else {
			// 导入人员失败
			return "509" + "，" + "-1";
		}
	}
	
	private String listToString(List<Integer> emptyStr, List<Integer> loginName, 
			List<Integer> deptName, List<Integer> posName) {
		StringBuilder sb = new StringBuilder("");
		if (emptyStr != null) {
			sb.append("empty=" + emptyStr.toString() + "，");
		}
		
		if (loginName != null) {
			sb.append("login=" + loginName.toString() + "，");
		}
		
		if (deptName != null) {
			sb.append("dept=" + deptName.toString() + "，");
		}
		
		if (posName != null) {
			sb.append("pos=" + posName.toString() + "，");
		}
		return sb.toString();
	}
}
