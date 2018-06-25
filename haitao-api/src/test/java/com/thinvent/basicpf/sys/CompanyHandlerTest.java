package com.thinvent.basicpf.sys;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSONObject;
import com.thinvent.basicpf.sys.adapt.ICompanyAdapt;
import com.thinvent.basicpf.sys.handler.impl.CompanyHandlerImpl;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.CompanyVO;

import java.util.ArrayList;
import java.util.List;


@RunWith(value = SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class CompanyHandlerTest {

	@Mock
	private ICompanyAdapt companyAdapt;
	@InjectMocks
	private CompanyHandlerImpl companyHandlerImpl;
	private CompanyVO companyVO = new CompanyVO();
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		companyVO.setComId("comId");
		companyVO.setComName("comName");
	}
	
	@Test
	public void testQueryByCondition() throws ThinventBaseException{
		String comName = "";
		String comAddr = "";
		String json = "{'size':1,'totalPages':1,'content':[{'comContacts':'某某警官','comAddr':'上海市','comTel':''," +
				"'comId':'1','comName':'上海市看守所','comParentId':'comId'}],'first':true,'totalElements':14}";
		JSONObject jsonObject = JSONObject.parseObject(json);
		when(companyAdapt.queryByComId("comId")).thenReturn(companyVO);
		when(companyAdapt.queryCompanyList(comName, comAddr, 1, 10)).thenReturn(jsonObject);
		JSONObject result = companyHandlerImpl.queryCompanyList(comName, comAddr, 1, 10);
		Assert.assertTrue(result != null);
		String json0 = "{'size':1,'totalPages':1,'content':[{'comContacts':'某某警官','comAddr':'上海市','comTel':''," +
				"'comId':'1','comName':'上海市看守所','comParentId':'comId'}],'first':true,'totalElements':14}";
		JSONObject jsonObject0 = JSONObject.parseObject(json0);
		when(companyAdapt.queryCompanyList(comName, comAddr, 1, 10)).thenReturn(jsonObject0);
		JSONObject result0 = companyHandlerImpl.queryCompanyList(comName, comAddr, 1, 10);
		Assert.assertTrue(result0 != null);
		String json1 = "{'size':1,'totalPages':1,'content':[{'comContacts':'某某警官','comAddr':'上海市','comTel':''," +
				"'comId':'1','comName':'上海市看守所','comParentId':''}],'first':true,'totalElements':14}";
		JSONObject jsonObject1 = JSONObject.parseObject(json1);
		when(companyAdapt.queryCompanyList(comName, comAddr, 1, 10)).thenReturn(jsonObject1);
		JSONObject result1 = companyHandlerImpl.queryCompanyList(comName, comAddr, 1, 10);
		Assert.assertTrue(result1 != null);
	}
	@Test
	public void testAddCompany() throws ThinventBaseException{
		ArgumentCaptor<CompanyVO> argument = ArgumentCaptor.forClass(CompanyVO.class);
		companyHandlerImpl.saveCompany(companyVO);
		verify(companyAdapt, times(1)).saveCompany(argument.capture());
		Assert.assertEquals("comName", argument.getValue().getComName());
		Assert.assertEquals("comId", argument.getValue().getComId());
	}
	@Test
	public void testDeleteCompany() throws ThinventBaseException{
		companyHandlerImpl.deleteCompany("12");
		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
		verify(companyAdapt, times(1)).deleteCompany(argument.capture());
		Assert.assertEquals("12", argument.getValue());
	}
	@Test
	public void testFindComByComId() throws ThinventBaseException{
		when(companyAdapt.queryByComId("12")).thenReturn(companyVO);
		CompanyVO result = companyHandlerImpl.queryByComId("12");
		Assert.assertTrue(result != null);
		Assert.assertTrue(result.getComName().equals("comName"));
	}
	
	@Test
	public void queryAllCompany() throws ThinventBaseException{
		List<CompanyVO> companyVOS = new ArrayList<>();
		companyVOS.add(companyVO);
		when(companyAdapt.queryAllCompany()).thenReturn(companyVOS);
		List<CompanyVO> result = companyHandlerImpl.queryAllCompany();
		Assert.assertTrue(result != null);
		Assert.assertTrue(result.get(0).getComName().equals("comName"));
	}
	
	@Test
	public void testListAllCompanyByEnable() throws ThinventBaseException{
		List<CompanyVO> companyVOS = new ArrayList<>();
		companyVOS.add(companyVO);
		
		when(companyAdapt.queryAllCompany("1")).thenReturn(companyVOS);
		List<CompanyVO> result = companyHandlerImpl.queryAllCompany("1");
		Assert.assertTrue(result != null);
		Assert.assertTrue(result.get(0).getComName().equals("comName"));
	}
}
