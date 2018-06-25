package com.thinvent.basicpf.sys.adapt;

import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.DepartmentVO;

import java.util.List;
import java.util.Map;

/**
 * Created by SCHX on 2017/6/13.
 */
public interface IDepartmentAdapt {
    public DepartmentVO getDepartmentById(String depId) throws ThinventBaseException;

    public DepartmentVO getDepartmentByDepName(String depName) throws ThinventBaseException;

    public void addDepartment(DepartmentVO departmentVO) throws ThinventBaseException;

    public void updateDepartment(DepartmentVO departmentVO) throws ThinventBaseException;

    public void delDepartment(String depId) throws ThinventBaseException;

    public List<DepartmentVO> getAllDepartmentsById(String depId) throws ThinventBaseException;

    public List<Map> getTreeDepartmentsById(String depId) throws ThinventBaseException;

    public List<Map> getTreeDepartmentsByUserId(String userId) throws ThinventBaseException;

	public String getDepartmentAllData() throws ThinventBaseException ;

	public List<DepartmentVO> getDepartmentByName(String deptName) throws ThinventBaseException;
}
