package com.thinvent.basicpf.sys.adapt;

import com.alibaba.fastjson.JSONObject;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.CompanyVO;

import java.util.List;

public interface ICompanyAdapt {
	
	public CompanyVO queryByComId(String comId)throws ThinventBaseException;
	public String saveCompany(CompanyVO companyVO) throws ThinventBaseException;
	public String deleteCompany( String comId) throws ThinventBaseException;
	public JSONObject queryCompanyList(String comName, String comAddr, int pageIndex, int pageSize) throws ThinventBaseException;
	public List<CompanyVO> queryAllCompany() throws ThinventBaseException;
	public List<CompanyVO> queryAllCompany(String enable) throws ThinventBaseException;
}
