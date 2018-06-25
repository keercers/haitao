package com.thinvent.basicpf.sys.handler.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.thinvent.basicpf.sys.adapt.IDepartmentAdapt;
import com.thinvent.basicpf.sys.adapt.ILogAdapt;
import com.thinvent.basicpf.sys.adapt.IMoudleAdapt;
import com.thinvent.basicpf.sys.adapt.IUserAdapt;
import com.thinvent.basicpf.sys.handler.ILogHandler;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.LogUtil;
import com.thinvent.library.vo.LogVO;
import com.thinvent.library.vo.MoudleVO;
import com.thinvent.library.vo.UserVO;

@Service
public class LogHandlerImpl implements ILogHandler {

	@Autowired
	private ILogAdapt logAdapt;
	
	@Autowired
	private IUserAdapt userAdapt;
	
	@Autowired
	private IDepartmentAdapt departmentAdapt;
	
	@Autowired
	private IMoudleAdapt moudleAdapt;
	
	private List<MoudleVO> list = new ArrayList<>();
	
	@Override
	public void saveLog(LogVO logVO) throws ThinventBaseException {
		this.logAdapt.saveLog(logVO);
	}

	@Override
	public Map listLogByParams(String logType, String userName, String system, String maxDate, String minDate, int pageSize, int pageIndex)
			throws ThinventBaseException {
		Map<String, Object> map = new HashMap<>();
		
		Map mapRet = logAdapt.listLogByParams(logType, userName, system, maxDate, minDate, pageSize, pageIndex);
		int count = (int) mapRet.get("count");
		List<Map<String, Object>> mapList = (List<Map<String, Object>>) mapRet.get("logList");
		List<LogVO> logVOs = new ArrayList<>();
		for(Map m : mapList) {
			String jsonStr = m.toString();
			LogVO lvo = JSON.parseObject(jsonStr, LogVO.class);
			logVOs.add(lvo);
		}
		
		map.put("log", logVOs);
		map.put("count", count);
		return this.logAdapt.listLogByParams(logType, userName, system, maxDate, minDate, pageSize, pageIndex);
	}

	public List<Object> getMoudleName(String requestUri) throws ThinventBaseException {
		if (list.isEmpty()) {
			list = moudleAdapt.getAll();
		}
		return LogUtil.getMoudleNameAndSystem(list, requestUri);
	}
	
	public void logSaveHandle(HttpServletRequest request, HttpServletResponse response, Object handler, 
			String userId) throws Exception {
		String moudleName = "";
		String system = "";
		String operationDesc = "";
		String ipAddress = request.getRemoteAddr();
		String requestUriIntercept = LogUtil.stringIntercept(request.getRequestURI());
		String logType = LogUtil.getLogType(requestUriIntercept);
		List<Object> listResult = getMoudleName(requestUriIntercept);
		
		if ("login".equals(requestUriIntercept)) {
			moudleName = "用户登录";
			operationDesc = "用户" + logType;
			this.logWrite(logType, system, moudleName, operationDesc, ipAddress, userId);
		}
		
		if ((boolean)listResult.get(2)) {
			moudleName = (String)listResult.get(0);
			system = (String)listResult.get(1);
			operationDesc = "用户" + logType;
			if ("".equals(system)) {
				return;
			} else {
				this.logWrite(logType, system, moudleName, operationDesc, ipAddress, userId);
			}
		}
	}
	
	public void logWrite(String logType,String system, String moudleName, String operationDesc, String ipAddress, String userId) throws ThinventBaseException {
		UserVO userVO = userAdapt.findUserByUserId(userId);
		userVO.setDeptName(departmentAdapt.getDepartmentById(userVO.getDepId()).getDepName());
		LogVO logVO = LogUtil.logOperation(userVO.getLoginName(), userVO.getDeptName(), logType, system, moudleName, operationDesc, ipAddress);
		this.saveLog(logVO);
	}
}
