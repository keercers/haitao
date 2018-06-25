package com.thinvent.basicpf.sys.handler.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinvent.basicpf.sys.adapt.IDepartmentAdapt;
import com.thinvent.basicpf.sys.handler.IDepartmentHandler;
import com.thinvent.basicpf.sys.util.DepartmentVerifyUtil;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.DepartmentVO;

@Service
public class DepartmentHandlerImpl implements IDepartmentHandler{

    @Autowired
    private IDepartmentAdapt departmentAdpat;

    @Override
    public DepartmentVO getDeptById(String deptId) throws ThinventBaseException {
        DepartmentVO departmentVO = departmentAdpat.getDepartmentById(deptId);
        DepartmentVerifyUtil.verifyDept(departmentVO);
        return departmentVO;
    }

    @Override
    public DepartmentVO getDeptByName(String deptName) throws ThinventBaseException {
        return departmentAdpat.getDepartmentByDepName(deptName);
    }

    @Override
    public void addDepartment(DepartmentVO dept) throws ThinventBaseException {
        DepartmentVerifyUtil.verifyDept(dept);
        departmentAdpat.addDepartment(dept);
    }

    @Override
    public void updateDepartment(DepartmentVO dept) throws ThinventBaseException {
        DepartmentVerifyUtil.verifyDept(dept);
        departmentAdpat.updateDepartment(dept);
    }

    @Override
    public void delDepartment(String deptId) throws ThinventBaseException {
        departmentAdpat.delDepartment(deptId);
    }

    @Override
    public List<DepartmentVO> getAllDepartmentsById(String deptId) throws ThinventBaseException {
        return departmentAdpat.getAllDepartmentsById(deptId);
    }

    @Override
    public List<Map> getTreeDepartmentsById(String deptId) throws ThinventBaseException {
        return departmentAdpat.getTreeDepartmentsById(deptId);
    }

    @Override
    public List<Map> getTreeDepartmentsByUserId(String userId) throws ThinventBaseException {
        return departmentAdpat.getTreeDepartmentsByUserId(userId);
    }

	@Override
	public String getDepartmentAllData()  throws ThinventBaseException {
        return departmentAdpat.getDepartmentAllData();
	}
}
