package com.thinvent.basicpf.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.thinvent.basicpf.dao.ICompanyDao;
import com.thinvent.basicpf.dao.IDepartmentDao;
import com.thinvent.basicpf.handler.ICompanyHandler;
import com.thinvent.basicpf.model.Company;
import com.thinvent.basicpf.util.CompanyVerifyUtil;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.DataVerifyUtil;
import com.thinvent.library.util.UUIDUtil;
import com.thinvent.library.vo.CompanyVO;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class CompanyHandlerImpl implements ICompanyHandler{

	@Autowired
	private ICompanyDao companyDao;
	@Autowired
	private IDepartmentDao departmentDao;
	
	@Override
	public Page<Company> queryByCondition(String comName, String comAddr, PageRequest page) {
		return companyDao.queryByComNameLikeAndComAddrLikeAndEnableOrderById("%"+comName+"%", "%"+comAddr+"%", 1, page);
	}
	@Override
	public String addCompany(CompanyVO companyVO) throws ThinventBaseException {
		Company company = new Company();
		BeanUtils.copyProperties(companyVO, company);
		Company existCompany = companyDao.findOneByComNameAndEnable(company.getComName(),1);
		if(companyVO.getComId()==null||"".equals(companyVO.getComId())||companyVO.getComId().trim().length()<=0){
			company.setComId(UUIDUtil.getUuid());
			CompanyVerifyUtil.verifyAddExistComName(existCompany);
		} else {
			Company companySource = findComByComId(companyVO.getComId());
			company.setCreateUser(companySource.getCreateUser());
			company.setCreateTime(companySource.getCreateTime());
			CompanyVerifyUtil.verifyUpdateExistComName(existCompany,companySource);
		}
		List<Company> comList = getAllParCom(company);
		Boolean flag = false;
		for (Company com: comList) {
			if (com.getComId().equals(company.getComParentId())) {
				flag = true;
			}
		}
		CompanyVerifyUtil.verifySaveCompany(flag);
		companyDao.save(company);
		return "200";
	}
	public List<Company> getAllParCom(Company company) throws ThinventBaseException  {
		List<Company> companyList = new ArrayList<>();
		return getParComList(companyList,company);
	}
	public List<Company> getParComList(List<Company> comList, Company company) throws ThinventBaseException {
		if (company != null) {
			comList.add(company);
			if (!"".equals(company.getComId())) {
				Company com = companyDao.findOneByComParentIdAndEnable(company.getComId(),1);
				getParComList(comList,com);
			}
		}
		return comList;
	}
	@Override
	public String deleteCompany(String comId) throws ThinventBaseException {
		Boolean existsDept = this.departmentDao.existsByComIdAndEnable(comId);
		CompanyVerifyUtil.verifyDeleteComResult(existsDept);
		Company childCompany = companyDao.findOneByComParentIdAndEnable(comId,1);
		CompanyVerifyUtil.verifyDelExistComChild(childCompany);
		Company company = companyDao.findOneByComIdAndEnable(comId, 1);
		company.setEnable(0);
		companyDao.save(company);
		return "200";
	}

	@Override
	public Company findComByComId(String comId) throws ThinventBaseException {
		Company companyResult = companyDao.findOneByComIdAndEnable(comId, 1);
		CompanyVerifyUtil.verifyObject(companyResult);
		return companyResult;
	}

	/**
	 * 加载所有的单位
	 */
	@Override
	public List<CompanyVO> listAllCompany() throws ThinventBaseException {
		List<Company> companies = companyDao.findAllByEnable(1);
		List<CompanyVO> companyList = new ArrayList<>();
		for(Company company : companies) {
			CompanyVO companyVO = new CompanyVO();
			BeanUtils.copyProperties(company, companyVO);
			companyList.add(companyVO);
		}
		return companyList;
	}
	@Override
	public List<CompanyVO> listAllCompanyByEnable(String enable) {
		List<Company> companies;

		String val = enable;
		if (StringUtils.isBlank(val)) {
			companies = companyDao.findAll();
		} else {
			companies = companyDao.findAllByEnable(Integer.parseInt(enable));
		}
		
		List<CompanyVO> companyList = new ArrayList<>();
		for(Company company : companies) {
			CompanyVO companyVO = new CompanyVO();
			BeanUtils.copyProperties(company, companyVO);
			companyList.add(companyVO);
		}
		return companyList;
	}
}
