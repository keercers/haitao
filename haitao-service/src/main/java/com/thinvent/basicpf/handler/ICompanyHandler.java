package com.thinvent.basicpf.handler;

import com.thinvent.library.vo.CompanyVO;
import org.springframework.data.domain.Page;

import com.thinvent.basicpf.model.Company;
import com.thinvent.library.exception.ThinventBaseException;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ICompanyHandler {

	public Page<Company> queryByCondition(String comName, String comAddr, PageRequest page) throws ThinventBaseException;
	public String addCompany(CompanyVO company) throws ThinventBaseException;
	public String deleteCompany(String comId) throws ThinventBaseException;
	public Company findComByComId(String comId) throws ThinventBaseException;
	public List<CompanyVO> listAllCompany() throws ThinventBaseException;
	
	/**
	 * 根据enable查询 
	 * @author tsm
	 * @return
	 * @throws ThinventBaseException
	 */
	public List<CompanyVO> listAllCompanyByEnable(String enable);
}
