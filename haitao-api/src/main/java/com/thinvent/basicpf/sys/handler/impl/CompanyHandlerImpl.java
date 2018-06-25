package com.thinvent.basicpf.sys.handler.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thinvent.basicpf.sys.adapt.ICompanyAdapt;
import com.thinvent.basicpf.sys.handler.ICompanyHandler;
import com.thinvent.basicpf.sys.util.CompanyVerifyUtil;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.CompanyVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CompanyHandlerImpl implements ICompanyHandler {
	
	@Autowired
	private ICompanyAdapt companyAdapt;

	@Override
	public CompanyVO queryByComId(String comId) throws ThinventBaseException{
		CompanyVO companyVO = companyAdapt.queryByComId(comId);
		CompanyVerifyUtil.verifyObject(companyVO);
		return companyVO;
	}

	@Override
	public String saveCompany(CompanyVO companyVO) throws ThinventBaseException{
		return companyAdapt.saveCompany(companyVO);
	}

	@Override
	public String deleteCompany(String comId) throws ThinventBaseException{
		return companyAdapt.deleteCompany(comId);
	}

	@Override
	public JSONObject queryCompanyList(String comName, String comAddr, int pageIndex, int pageSize) throws ThinventBaseException{
		JSONObject json = companyAdapt.queryCompanyList(comName, comAddr, pageIndex, pageSize);
		Map<String,Object> companyVOMap = json;
		List<Map<String, Object>> mapList = (List<Map<String, Object>>) companyVOMap.get("content");
		List<CompanyVO> companyVOS = new ArrayList<>();
		for(Map m : mapList) {
			String jsonStr = m.toString();
			CompanyVO cvo = JSON.parseObject(jsonStr, CompanyVO.class);
			if ("".equals(cvo.getComParentId()) || cvo.getComParentId() == null) {
				cvo.setComParentName("");
			} else {
				cvo.setComParentName(getCompanyName(cvo.getComParentId()));
			}
			companyVOS.add(cvo);
		}
		json.put("content",companyVOS);
		return json;
	}

	public String getCompanyName(String companyId) throws ThinventBaseException {
		CompanyVO companyVO = new CompanyVO();
		try {
			companyVO = companyAdapt.queryByComId(companyId);
		} catch (Exception e) {
			log.error("company getCompanyName can not find companyName: ", e);
		}
		if(companyVO == null)
			return "";
		return companyVO.getComName();
	}

	@Override
	public List<CompanyVO> queryAllCompany() throws ThinventBaseException {
		return companyAdapt.queryAllCompany();
	}

	@Override
	public List<CompanyVO> queryAllCompany(String enable) throws ThinventBaseException {
		return companyAdapt.queryAllCompany(enable);
	}

}
