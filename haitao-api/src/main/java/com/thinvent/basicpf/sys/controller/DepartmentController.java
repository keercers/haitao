package com.thinvent.basicpf.sys.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thinvent.basicpf.sys.handler.IDepartmentHandler;
import com.thinvent.library.Constants;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.JWTConfig;
import com.thinvent.library.vo.DepartmentVO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by SCHX on 2017/6/16.
 */

@Slf4j
@RestController
@RequestMapping(value = "sys/department")
public class DepartmentController {
    @Autowired
    private IDepartmentHandler departmentHandler;

    @GetMapping(value = "/getByDeptId")
    @ApiOperation(value = "部门--部门查询", notes = "部门查询")
    @ApiImplicitParam(name = "deptId", required = true, value = "部门ID", dataType = "string", paramType = "query")
    public Object get(@RequestParam(value = "deptId", required = true) String deptId) throws ThinventBaseException {
        return this.departmentHandler.getDeptById(deptId);
    }

    @GetMapping(value = "/getByDeptName")
    @ApiOperation(value = "部门--部门名字查询", notes = "部门名字查询")
    @ApiImplicitParam(name = "deptName", required = true, value = "部门名称", dataType = "string", paramType = "query")
    public Object getByName(@RequestParam(value = "deptName", required = true) String deptName) throws ThinventBaseException {
        return this.departmentHandler.getDeptByName(deptName);
    }

    @PostMapping(value = "/addDepartment")
    @ApiOperation(value = "部门--部门保存", notes = "部门保存")
    @ApiImplicitParam(name = "departmentVO", required = true, value = "部门", dataType = "DepartmentVO")
    public void add(@Valid @RequestBody DepartmentVO departmentVO, HttpServletRequest request) throws ThinventBaseException {
    	String userId = "";
		try {
			userId = JWTConfig.getUserByToken(request.getHeader(Constants.TOKEN_KEY)).getUserId();
		} catch (Exception e) {
			log.error("department addDepartment can not find userId(unlegal login): ", e);
		}
    	departmentVO.setCreateTime(new Date());
        departmentVO.setCreateUser(userId);
        departmentVO.setUpdateTime(new Date());
        departmentVO.setUpdateUser(userId);
        this.departmentHandler.addDepartment(departmentVO);
    }

    @PostMapping(value = "/updateDepartment")
    @ApiOperation(value = "部门--部门修改", notes = "部门修改")
    @ApiImplicitParam(name = "departmentVO", required = true, value = "部门", dataType = "DepartmentVO")
    public void update(@Valid @RequestBody DepartmentVO departmentVO, HttpServletRequest request) throws ThinventBaseException {
    	String userId = "";
		try {
			userId = JWTConfig.getUserByToken(request.getHeader(Constants.TOKEN_KEY)).getUserId();
		} catch (Exception e) {
			log.error("department updateDepartment can not find userId(unlegal login): ", e);
		}
    	departmentVO.setUpdateTime(new Date());
        departmentVO.setUpdateUser(userId);
        this.departmentHandler.updateDepartment(departmentVO);
    }

    @GetMapping(value = "/delDepartment")
    @ApiOperation(value = "部门--部门删除", notes = "部门删除")
    @ApiImplicitParam(name = "deptId", required = true, value = "部门ID", dataType = "string", paramType = "query")
    public void delete(@RequestParam(value = "deptId", required = true) String deptId) throws ThinventBaseException {
        this.departmentHandler.delDepartment(deptId);
    }

    @GetMapping(value = "/getDeptListById")
    @ApiOperation(value = "部门--部门list", notes = "部门list")
    @ApiImplicitParam(name = "deptId", required = true, value = "部门ID", dataType = "string", paramType = "query")
    public Object getDeptListById(@RequestParam(value = "deptId", required = true) String deptId) throws ThinventBaseException {
        return this.departmentHandler.getAllDepartmentsById(deptId);
    }

    @GetMapping(value = "/getDeptTreeById")
    @ApiOperation(value = "部门--部门Tree", notes = "部门Tree")
    @ApiImplicitParam(name = "deptId", required = true, value = "部门ID", dataType = "string", paramType = "query")
    public Object getDeptTreeById(@RequestParam(value = "deptId", required = true) String deptId) throws ThinventBaseException {
        return this.departmentHandler.getTreeDepartmentsById(deptId);
    }

    @GetMapping(value = "/getDeptTreeByUserId")
    @ApiOperation(value = "部门--部门Tree", notes = "部门Tree")
    public Object getDeptTreeByUserId(HttpServletRequest request, HttpServletResponse response) throws ThinventBaseException {
    	String userId = "";
		try {
			userId = JWTConfig.getUserByToken(request.getHeader(Constants.TOKEN_KEY)).getUserId();
		} catch (Exception e) {
			log.error("department updateDepartment can not find userId(unlegal login): ", e);
		}
        return this.departmentHandler.getTreeDepartmentsByUserId(userId);
    }
    
    @GetMapping(value = "/getDepartmentAllData")
    @ApiOperation(value = "部门--所有部门", notes = "所有部门")
    public String getDepartmentAllData() throws ThinventBaseException {
        return this.departmentHandler.getDepartmentAllData();
    }
}
