package com.thinvent.basicpf.sys.adapt.impl;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.GetPostUtil;
import com.thinvent.library.vo.CompanyVO;
import com.thinvent.basicpf.sys.adapt.ICompanyAdapt;
import com.thinvent.basicpf.sys.util.URLUtil;

import java.util.Collections;
import java.util.List;

@Service
public class CompanyAdaptImpl implements ICompanyAdapt {
	private String basicUrl =  URLUtil.getUrl();
	
	@Override
	public CompanyVO queryByComId(String comId) throws ThinventBaseException{
		String url = basicUrl+"company/queryByComId?comId="+comId;
		String json = GetPostUtil.sendGet(url);
		if(json!=null){
			return JSON.parseObject(json, CompanyVO.class);
		}else
			return null;
	}

	@Override
	public String saveCompany(CompanyVO companyVO) throws ThinventBaseException{
		String url = basicUrl+"company/saveCompany";
		String params = JSON.toJSONString(companyVO);
		String json = GetPostUtil.sendPost(url, params);
		if(json!=null){
			return json;
		}else
			return null;
	}

	@Override
	public String deleteCompany(String comId) throws ThinventBaseException{
		String url = basicUrl+"company/deleteCompany?comId="+comId;
		String json = GetPostUtil.sendGet(url);
		if(json!=null){
			return json;
		}else
			return null;
	}

	@Override
	public JSONObject queryCompanyList(String comName, String comAddr, int pageIndex, int pageSize) throws ThinventBaseException{
		String url = basicUrl+"company/queryCompanyList?"
				+ "comName="+comName+"&comAddr="+comAddr+"&pageIndex="+pageIndex+"&pageSize="+pageSize;
		return JSON.parseObject(GetPostUtil.sendGet(url));
	}

	@Override
	public List<CompanyVO> queryAllCompany() throws ThinventBaseException{
		String url = basicUrl + "company/listAllCompany";
		String json = GetPostUtil.sendGet(url);
		if (json != null && !"".equals(json)) {
			return JSON.parseArray(json, CompanyVO.class);
		} else
			return Collections.emptyList();
	}

	@Override
	public List<CompanyVO> queryAllCompany(String enable) throws ThinventBaseException {
		String url = basicUrl + "company/listAllCompanyByEnable?enable=" + enable;
		String json = GetPostUtil.sendGet(url);
		if (json != null && !"".equals(json)) {
			return JSON.parseArray(json, CompanyVO.class);
		} else {
			return Collections.emptyList();
		}
	}
}
