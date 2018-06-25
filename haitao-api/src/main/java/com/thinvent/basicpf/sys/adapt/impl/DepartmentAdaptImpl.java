package com.thinvent.basicpf.sys.adapt.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.GetPostUtil;
import com.thinvent.library.vo.DepartmentVO;
import com.thinvent.basicpf.sys.adapt.IDepartmentAdapt;
import com.thinvent.basicpf.sys.util.URLUtil;

@Service
public class DepartmentAdaptImpl implements IDepartmentAdapt{
	private String basicUrl =  URLUtil.getUrl();

    @Override
    public DepartmentVO getDepartmentById(String depId) throws ThinventBaseException {
        String url = basicUrl + "department/getDepartmentById?depId=" + depId;
        String json = GetPostUtil.sendGet(url);
        if (json != null && !"".equals(json)) {
            return JSON.parseObject(json, DepartmentVO.class);
        } else
            return null;
    }

    @Override
    public DepartmentVO getDepartmentByDepName(String depName) throws ThinventBaseException {
        String url = basicUrl + "department/getDeptByName?depName=" + depName;
        String json = GetPostUtil.sendGet(url);
        if (json != null && !"".equals(json)) {
            return JSON.parseObject(json, DepartmentVO.class);
        } else
            return null;
    }

    @Override
    public void addDepartment(DepartmentVO departmentVO) throws ThinventBaseException {
        String url = basicUrl + "department/addDepartment";
        String params = JSON.toJSONString(departmentVO);
        GetPostUtil.sendPost(url, params);
    }

    @Override
    public void updateDepartment(DepartmentVO departmentVO) throws ThinventBaseException {
        String url = basicUrl + "department/updateDepartment";
        String params = JSON.toJSONString(departmentVO);
        GetPostUtil.sendPost(url, params);
    }

    @Override
    public void delDepartment(String depId) throws ThinventBaseException {
        String url = basicUrl + "department/delDepartment?depId=" + depId;
        GetPostUtil.sendGet(url);
    }

    @Override
    public List<DepartmentVO> getAllDepartmentsById(String depId) throws ThinventBaseException {
        String url = basicUrl + "department/getAllDepartmentsById?depId=" + depId;
        String json = GetPostUtil.sendGet(url);
        if (json != null) {
            return JSON.parseArray(json, DepartmentVO.class);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Map> getTreeDepartmentsById(String depId) throws ThinventBaseException {
        String url = basicUrl + "department/getTreeDepartmentsById?depId=" + depId;
        String json = GetPostUtil.sendGet(url);
        if (json != null) {
            return JSON.parseArray(json, Map.class);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Map> getTreeDepartmentsByUserId(String userId) throws ThinventBaseException {
        String url = basicUrl + "department/getTreeDepartmentsByUserId?userId=" + userId;
        String json = GetPostUtil.sendGet(url);
        if (json != null) {
            return JSON.parseArray(json, Map.class);
        } else {
            return Collections.emptyList();
        }
    }

	@Override
	public String getDepartmentAllData() throws ThinventBaseException {
        String url = basicUrl + "department/getDepartmentAllData";
        return GetPostUtil.sendGet(url);
	}

	@Override
	public List<DepartmentVO> getDepartmentByName(String deptName) throws ThinventBaseException {
		String url = basicUrl + "department/getDepartmentByName?deptName=" + deptName;
        String json = GetPostUtil.sendGet(url);
        if (json != null && !"[]".equals(json)) {
            return JSON.parseArray(json, DepartmentVO.class);
        } else
            return Collections.emptyList();
	}
}
