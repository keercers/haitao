package com.thinvent.basicpf;


import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.thinvent.basicpf.dao.IDepartmentDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.thinvent.basicpf.dao.ICompanyDao;
import com.thinvent.basicpf.handler.impl.CompanyHandlerImpl;
import com.thinvent.basicpf.model.Company;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.CompanyVO;

@RunWith(value = SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class CompanyHandlerTest {

	@Mock
	private ICompanyDao companyDao;
	@Mock
	private IDepartmentDao departmentDao;
	
	@InjectMocks
	private CompanyHandlerImpl companyHandlerImpl;
	Company company = new Company();
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		company.setComId("comId");
		company.setComName("comName");
	}
	
	@Test
	public void testQueryByCondition() throws ThinventBaseException{
		String comName = "";
		String comAddr = "";
		List<Company> companyList = new ArrayList<>();
		companyList.add(company);
		Page<Company> page = new PageImpl(companyList);
		PageRequest pageRequest = new PageRequest(1, 10);
		when(companyDao.queryByComNameLikeAndComAddrLikeAndEnableOrderById("%"+comName+"%", "%"+comAddr+"%", 1, pageRequest)).thenReturn(page);
		Page<Company> result = companyHandlerImpl.queryByCondition(comName, comAddr, pageRequest);
		Assert.assertTrue(result!=null);
		Assert.assertTrue("comName".equals(result.getContent().get(0).getComName()));
	}
	@Test
	public void testAddCompany() throws ThinventBaseException{
		Company company = new Company();
		CompanyVO cvo = new CompanyVO();
		cvo.setComName("comName").setComAddr("comAddress").setComId(null);
		when(companyDao.save(company)).thenReturn(company);
		String result = companyHandlerImpl.addCompany(cvo);
		Assert.assertTrue("200".equals(result));
		Company company1 = new Company();
		Company companyResult = new Company();
		companyResult.setComName("comName");
		companyResult.setComId("comId");
		when(companyDao.findOneByComIdAndEnable("comId",1)).thenReturn(companyResult);
		CompanyVO cvo1 = new CompanyVO();
		cvo1.setComName("comName").setComAddr("comAddress").setComId("comId");
		when(companyDao.save(company1)).thenReturn(company1);
		String result1 = companyHandlerImpl.addCompany(cvo1);
		Assert.assertTrue("200".equals(result1));
	}
	@Test
	public void testDeleteCompany() throws ThinventBaseException{
		when(departmentDao.existsByComIdAndEnable("12")).thenReturn(false);
		when(companyDao.findOneByComIdAndEnable("12", 1)).thenReturn(company);
		when(companyDao.findOneByComParentIdAndEnable("12",1)).thenReturn(null);
		String result = companyHandlerImpl.deleteCompany("12");
		Assert.assertTrue("200".equals(result));
	}
	@Test
	public void testFindComByComId() throws ThinventBaseException{
		when(companyDao.findOneByComIdAndEnable("12", 1)).thenReturn(company);
		Company result = companyHandlerImpl.findComByComId("12");
		Assert.assertTrue(result!=null);
		Assert.assertTrue("comName".equals(result.getComName()));
	}
	@Test
	public void testListAllCompany() throws ThinventBaseException {
		List<Company> list = new ArrayList<>();
		list.add(company);
		when(companyDao.findAllByEnable(1)).thenReturn(list);
		List<CompanyVO> listCompanyVO = companyHandlerImpl.listAllCompany();
		Assert.assertTrue(listCompanyVO!=null);
	}
	
	@Test
	public void testListAllCompanyByEnable() throws ThinventBaseException {
		List<Company> list = new ArrayList<>();
		List<CompanyVO> listCompanyVO;
		list.add(company);
		when(companyDao.findAll()).thenReturn(list);
		listCompanyVO = companyHandlerImpl.listAllCompanyByEnable("");
		
		when(companyDao.findAllByEnable('1')).thenReturn(list);
		listCompanyVO = companyHandlerImpl.listAllCompanyByEnable("1");
		
		Assert.assertTrue(listCompanyVO!=null);
	}
}
