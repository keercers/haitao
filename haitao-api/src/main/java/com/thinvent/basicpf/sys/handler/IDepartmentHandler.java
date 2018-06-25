package com.thinvent.basicpf.sys.handler;

import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.DepartmentVO;

import java.util.List;
import java.util.Map;

/**
 * Created by SCHX on 2017/6/19.
 */
public interface IDepartmentHandler {
    public DepartmentVO getDeptById (String deptId) throws ThinventBaseException;

    public DepartmentVO getDeptByName (String deptName) throws ThinventBaseException;

    public void addDepartment (DepartmentVO dept) throws ThinventBaseException;

    public void updateDepartment (DepartmentVO dept) throws ThinventBaseException;

    public void delDepartment (String deptId) throws ThinventBaseException;

    public List<DepartmentVO> getAllDepartmentsById (String deptId) throws ThinventBaseException;

    public List<Map> getTreeDepartmentsById(String deptId) throws ThinventBaseException;

    public List<Map> getTreeDepartmentsByUserId(String userId) throws ThinventBaseException;

	public String getDepartmentAllData() throws ThinventBaseException ;
}
