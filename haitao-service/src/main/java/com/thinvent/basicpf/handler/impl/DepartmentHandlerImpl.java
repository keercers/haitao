package com.thinvent.basicpf.handler.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thinvent.basicpf.dao.IPositionDao;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.thinvent.basicpf.dao.IDepartmentDao;
import com.thinvent.basicpf.handler.IDepartmentHandler;
import com.thinvent.basicpf.handler.IUserHandler;
import com.thinvent.basicpf.model.Department;
import com.thinvent.basicpf.util.DepartmentVerifyUtil;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.DepartmentVO;
import com.thinvent.library.vo.UserVO;

/**
 * Created by SCHX on 2017/6/13.
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class DepartmentHandlerImpl implements IDepartmentHandler {
    @Autowired
    private IDepartmentDao departmentDao;
    @Autowired
    private IPositionDao positionDao;
    @Autowired
    private IUserHandler userHandler;

    @Override
    public Department getDepartmentById(String depId) throws ThinventBaseException {
        return this.departmentDao.findOneByDepIdAndEnable(depId, 1);
    }

    @Override
    public Department getDepartmentByDeptName(String depName) throws ThinventBaseException {
        return this.departmentDao.findOneByDepNameAndEnable(depName, 1);
    }

    @Override
    public void addDepartment(Department department) throws ThinventBaseException {
        department.setId(0);
        Department existDepartment = this.departmentDao.findOneByDepNameAndEnable(department.getDepName(),1);
        DepartmentVerifyUtil.verifyDeptName(existDepartment);
        this.departmentDao.save(department);
    }

    @Override
    public void updateDepartment(Department department) throws ThinventBaseException {
        Department oldDepartment = this.departmentDao.findOneByDepIdAndEnable(department.getDepId(), 1);
        DepartmentVerifyUtil.verifyDept(oldDepartment);
        Department existDepartment = this.departmentDao.findOneByDepNameAndEnable(department.getDepName(),1);
        DepartmentVerifyUtil.verifyUpdateDept(existDepartment, department);
        department.setId(oldDepartment.getId()).setDepId(oldDepartment.getDepId());
        this.departmentDao.save(department);
    }

    @Override
    public void delDepartment(String depId) throws ThinventBaseException {
        Boolean existsDepartment = this.departmentDao.existsByDepParentIdAndEnable(depId);
        Boolean existsPosition = this.positionDao.existsByDepIdAndEnable(depId);
        DepartmentVerifyUtil.verifyDeleteDeptResult(existsDepartment, existsPosition);
        Department department = this.departmentDao.findOneByDepIdAndEnable(depId, 1);
        DepartmentVerifyUtil.verifyDept(department);
        department.setEnable(0);
        this.departmentDao.save(department);
    }

    @Override
    public List<Department> getAllDeptById(String depId) throws ThinventBaseException {
        List<Department> list = new ArrayList<>();
        getSubDepartment(list, depId);
        return list;
    }

    private void getSubDepartment(List<Department> list, String depId) throws ThinventBaseException {
        Department dept = this.departmentDao.findOneByDepIdAndEnable(depId, 1);
        if (dept != null) {
            list.add(dept);
        }
        List<Department> listDepartment = this.departmentDao.findByDepParentIdAndEnableOrderByDepSort(depId, 1);
        if (listDepartment != null && !listDepartment.isEmpty()) {
            for (Department item : listDepartment) {
                getSubDepartment(list, item.getDepId());
            }
        }
    }

    @Override
    public List<Map> getDeptTree(String depId) throws ThinventBaseException {
        Department dept = this.departmentDao.findOneByDepIdAndEnable(depId, 1);
        List<Department> list = getAllDeptById(depId);
        return getDeptTreeByList(list, dept.getDepParentId());
    }

    private static List<Map> getDeptTreeByList(List<Department> list, String depId) {
        List<Map> mapList = new ArrayList();
        if (!list.isEmpty()) {
            for (Department dep : list) {
                Map map = new HashMap();
                if (dep.getDepParentId().equals(depId)) {
                    map.put("id", dep.getDepId() == null ? "" : dep.getDepId());
                    map.put("depParentId", dep.getDepId() == null ? "" : dep.getDepParentId());
                    map.put("label", dep.getDepName() == null ? "" : dep.getDepName());
                    map.put("children", getDeptTreeByList(list, dep.getDepId()));
                    mapList.add(map);
                }
            }
        }
        return mapList;
    }

    @Override
    public List<Map> getDeptTreeByUserId(String userId) throws ThinventBaseException {
        UserVO user = userHandler.findUserByUserId(userId);
        return getDeptTree(user.getDepId());
    }

	@Override
	public String getDepartmentAllData() {
        List<Department> list = departmentDao.findByEnable(1);
        return JSONArray.toJSONString(list);
	}

	@Override
	public List<DepartmentVO> getDepartmentByName(String deptName) throws ThinventBaseException {
		List<Department> deptList = this.departmentDao.findByDepNameAndEnable(deptName, 1);
		List<DepartmentVO> dvoList = new ArrayList<>();
		if (!deptList.isEmpty()) {
			for (Department dept : deptList) {
				DepartmentVO departmentVO = new DepartmentVO();
				BeanUtils.copyProperties(dept, departmentVO);
				dvoList.add(departmentVO);
			}
		}
		return dvoList;
	}
}

