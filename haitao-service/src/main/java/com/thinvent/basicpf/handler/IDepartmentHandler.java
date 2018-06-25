package com.thinvent.basicpf.handler;

import java.util.List;
import java.util.Map;

import com.thinvent.basicpf.model.Department;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.DepartmentVO;

/**
 * Created by SCHX on 2017/6/13.
 */
public interface IDepartmentHandler {
    Department getDepartmentById(String depId) throws ThinventBaseException;

    Department getDepartmentByDeptName(String depName) throws ThinventBaseException;

    void addDepartment(Department department) throws ThinventBaseException;

    void updateDepartment(Department department) throws ThinventBaseException;

    void delDepartment(String depId) throws ThinventBaseException;

    List<Department> getAllDeptById(String depId) throws ThinventBaseException;

    List<Map> getDeptTree(String depId) throws ThinventBaseException;

    List<Map> getDeptTreeByUserId(String userId) throws ThinventBaseException;

	String getDepartmentAllData();

	List<DepartmentVO> getDepartmentByName(String deptName) throws ThinventBaseException;
}
