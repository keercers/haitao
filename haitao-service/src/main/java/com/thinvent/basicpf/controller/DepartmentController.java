package com.thinvent.basicpf.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thinvent.basicpf.handler.IDepartmentHandler;
import com.thinvent.basicpf.model.Department;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.DepartmentVO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * Created by SCHX on 2017/6/13.
 */
@RestController
@RequestMapping(value = "department")
public class DepartmentController {
    @Autowired
    private IDepartmentHandler departmentHandler;

    @GetMapping(value = "/getDepartmentById")
    @ApiOperation(value = "部门--部门init", notes = "部门init")
    @ApiImplicitParam(name = "depId", required = true, value = "部门Id", dataType = "String", paramType = "query")
    public Department get(@RequestParam(value = "depId", required = true) String depId) throws ThinventBaseException {
        return this.departmentHandler.getDepartmentById(depId);
    }

    @GetMapping(value = "/getDeptByName")
    @ApiOperation(value = "部门--根据名字获取部门", notes = "根据名字获取部门")
    @ApiImplicitParam(name = "depName", required = true, value = "部门名称", dataType = "String", paramType = "query")
    public Department getByName(@RequestParam(value = "depName", required = true) String depName) throws ThinventBaseException {
        return this.departmentHandler.getDepartmentByDeptName(depName);
    }
    
    @GetMapping(value = "/getDepartmentByName")
    @ApiOperation(value = "部门--部门", notes = "部门")
    @ApiImplicitParam(name = "deptName", required = true, value = "部门名", dataType = "String", paramType = "query")
    public List<DepartmentVO> getDepartmentByName(@RequestParam(value = "deptName", required = true) String deptName) throws ThinventBaseException {
        return this.departmentHandler.getDepartmentByName(deptName);
    }

    @PostMapping(value = "/addDepartment")
    @ApiOperation(value = "部门--部门新增", notes = "部门新增")
    @ApiImplicitParam(name = "departmentVO", required = true, value = "部门", dataType = "DepartmentVO")
    public void add(@Valid @RequestBody DepartmentVO departmentVO) throws ThinventBaseException {
        Department department = new Department();
        BeanUtils.copyProperties(departmentVO, department);
        this.departmentHandler.addDepartment(department);
    }

    @PostMapping(value = "/updateDepartment")
    @ApiOperation(value = "部门--部门修改", notes = "部门修改")
    @ApiImplicitParam(name = "departmentVO", required = true, value = "部门", dataType = "DepartmentVO")
    public void update(@Valid @RequestBody DepartmentVO departmentVO) throws ThinventBaseException {
        Department department = new Department();
        BeanUtils.copyProperties(departmentVO, department);
        this.departmentHandler.updateDepartment(department);
    }

    @GetMapping(value = "/delDepartment")
    @ApiOperation(value = "部门--部门删除", notes = "部门删除")
    @ApiImplicitParam(name = "depId", required = true, value = "部门ID", dataType = "String", paramType = "query")
    public void delete(@RequestParam(value = "depId") String depId) throws ThinventBaseException {
        this.departmentHandler.delDepartment(depId);
    }

    @GetMapping(value = "/getAllDepartmentsById")
    @ApiOperation(value = "部门--所有部门list查询", notes = "所有部门list查询")
    @ApiImplicitParam(name = "depId", required = true, value = "部门ID", dataType = "String", paramType = "query")
    public List<Department> getAllDepartmentsById(@RequestParam(value = "depId") String depId) throws ThinventBaseException {
        return this.departmentHandler.getAllDeptById(depId);
    }

    @GetMapping(value = "/getTreeDepartmentsById")
    @ApiOperation(value = "部门--部门树查询", notes = "部门树查询")
    @ApiImplicitParam(name = "depId", required = true, value = "部门ID", dataType = "String", paramType = "query")
    public List<Map> getTreeDepartmentsById(@RequestParam(value = "depId") String depId) throws ThinventBaseException {
        return this.departmentHandler.getDeptTree(depId);
    }

    @GetMapping(value = "/getTreeDepartmentsByUserId")
    @ApiOperation(value = "部门--部门树查询", notes = "部门树查询")
    @ApiImplicitParam(name = "userId", required = true, value = "用户ID", dataType = "String", paramType = "query")
    public List<Map> getTreeDepartmentsByUserId(@RequestParam(value = "userId") String userId) throws ThinventBaseException {
        return this.departmentHandler.getDeptTreeByUserId(userId);
    }

    @GetMapping(value = "/getDepartmentAllData")
    @ApiOperation(value = "部门--所有部门", notes = "所有部门")
    public String getDepartmentAllData() throws ThinventBaseException {
        return this.departmentHandler.getDepartmentAllData();
    }
}
