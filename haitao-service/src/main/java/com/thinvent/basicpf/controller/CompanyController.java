package com.thinvent.basicpf.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thinvent.basicpf.handler.ICompanyHandler;
import com.thinvent.basicpf.model.Company;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.CompanyVO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "company")
public class CompanyController {

	@Autowired
	private ICompanyHandler companyService;

	@GetMapping(value = "/queryByComId")
	@ApiOperation(value="公司--公司查询", notes="公司查询")
	@ApiImplicitParam(name="comId",required=true, value = "公司ID", dataType = "string", paramType = "query")
	public Object queryByComId(@RequestParam(required = true) String comId) throws ThinventBaseException {
		return companyService.findComByComId(comId);
	}

	@GetMapping(value = "/queryCompanyList")
	@ApiOperation(value="公司--公司列表查询", notes="公司列表查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name="comName",required=false, value = "公司名称", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name="comAddr",required=false, value = "公司地址", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name="pageIndex",required=true, value = "页数", dataType = "int", paramType = "query"),
		@ApiImplicitParam(name="pageSize",required=true, value = "每页显示条数", dataType = "int", paramType = "query")
	})
	public Page<Company> queryCompanyList(@RequestParam(value = "comName", required = false) String comName, 
			@RequestParam (value = "comAddr", required = false)String comAddr,
			@RequestParam int pageIndex, @RequestParam int pageSize) throws ThinventBaseException {
		return companyService.queryByCondition(comName, comAddr, new PageRequest(pageIndex - 1, pageSize));
	}

	@PostMapping(value = "/saveCompany")
	@ApiOperation(value="公司--公司保存", notes="公司保存")
	@ApiImplicitParam(name="companyVO",required=true, value = "公司", dataType = "CompanyVO")
	public String saveCompany(@RequestBody CompanyVO companyVO, HttpServletRequest request, HttpServletResponse response) throws ThinventBaseException {
		return companyService.addCompany(companyVO);
	}

	@GetMapping(value = "/deleteCompany")
	@ApiOperation(value="公司--公司删除", notes="公司删除")
	@ApiImplicitParam(name="comId",required=true, value = "公司ID", dataType = "string", paramType = "query")
	public String deleteCompany(@RequestParam String comId) throws ThinventBaseException {
		return companyService.deleteCompany(comId);
	}

	@GetMapping(value = "/listAllCompany")
	@ApiOperation(value = "单位--所有单位加载", notes = "所有单位加载")
	public List<CompanyVO> listAllCompany() throws ThinventBaseException {
		return companyService.listAllCompany();
	}
	
	/**
	 * 根据enable查询 
	 * @author tsm
	 * @return
	 * @throws ThinventBaseException
	 */
	@GetMapping(value = "/listAllCompanyByEnable")
	@ApiOperation(value = "单位--所有单位加载", notes = "所有单位加载")
	@ApiImplicitParam(name="enable",required = false, value = "删除标识符", dataType = "string", paramType = "query")
	public List<CompanyVO> listAllCompanyByEnable(@RequestParam String enable) throws ThinventBaseException {
		return companyService.listAllCompanyByEnable(enable);
	}
}
