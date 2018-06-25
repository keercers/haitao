package com.thinvent.basicpf.sys.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.thinvent.basicpf.sys.handler.ICompanyHandler;
import com.thinvent.library.Constants;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.JWTConfig;
import com.thinvent.library.vo.CompanyVO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "sys/company")
public class CompanyController {
	
	@Autowired
	private ICompanyHandler companyHandle;
	
	@RequestMapping(value="/queryByComId", method = RequestMethod.GET)
	@ApiOperation(value="公司--公司查询", notes="公司查询")
	@ApiImplicitParam(name="comId",required=true, value = "公司ID", dataType = "string", paramType = "query")
	@ResponseBody
	public CompanyVO queryByComId(@RequestParam(value = "comId", required = true) String comId)throws ThinventBaseException{
		return companyHandle.queryByComId(comId);
	}
	
	@RequestMapping(value="/saveCompany", method = RequestMethod.POST)
	@ApiOperation(value="公司--保存公司", notes="保存公司")
	@ApiImplicitParam(name="companyVO",required=true, value = "公司", dataType = "CompanyVO")
	@ResponseBody
	public String saveCompany(@RequestBody CompanyVO companyVO, HttpServletRequest request)throws ThinventBaseException{
		String userId = "";
		try {
			userId = JWTConfig.getUserByToken(request.getHeader(Constants.TOKEN_KEY)).getUserId();
		} catch (Exception e) {
			log.error("company saveCompany can not find userId(unlegal login): ", e);
		}
		if(companyVO.getComId()==null||"".equals(companyVO.getComId())||companyVO.getComId().trim().length()<=0){			
			companyVO.setCreateUser(userId);
			companyVO.setCreateTime(new Date());
			companyVO.setUpdateUser(userId);
			companyVO.setUpdateTime(new Date());
		} else {
			companyVO.setUpdateUser(userId);
			companyVO.setUpdateTime(new Date());
		}
		return companyHandle.saveCompany(companyVO);
	}
	
	@RequestMapping(value="/updateCompany", method = RequestMethod.POST)
	@ApiOperation(value="公司--公司修改", notes="公司修改")
	@ApiImplicitParam(name="companyVO",required=true, value = "公司", dataType = "CompanyVO")
	@ResponseBody
	public String updateCompany(@RequestBody CompanyVO companyVO, HttpServletRequest request)throws ThinventBaseException{
		String userId = "";
		try {
			userId = JWTConfig.getUserByToken(request.getHeader(Constants.TOKEN_KEY)).getUserId();
		} catch (Exception e) {
			log.error("company updateCompany can not find userId(unlegal login): ", e);
		}
		companyVO.setUpdateUser(userId);
		companyVO.setUpdateTime(new Date());
		return companyHandle.saveCompany(companyVO);
	}
	
	@RequestMapping(value="/deleteCompany", method = RequestMethod.GET)
	@ApiOperation(value="公司--公司删除", notes="公司删除")
	@ApiImplicitParam(name="comId",required=true, value = "公司ID", dataType = "string", paramType = "query")
	@ResponseBody
	public String deleteCompany(@RequestParam(value = "comId", required = true) String comId)throws ThinventBaseException{
		return companyHandle.deleteCompany(comId);
	}
	
	@RequestMapping(value="/queryCompanyList", method = RequestMethod.GET)
	@ApiOperation(value="公司--公司列表查询", notes="公司列表查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name="comName",required=false, value = "公司名称", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name="comAddr",required=false, value = "公司地址", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name="pageIndex",required=true, value = "页数", dataType = "int", paramType = "query"),
		@ApiImplicitParam(name="pageSize",required=true, value = "每页显示条数", dataType = "int", paramType = "query")
	})
	@ResponseBody
	public JSONObject queryCompanyList(@RequestParam(value = "comName", required = false) String comName,
			@RequestParam(value = "comAddr", required = false) String comAddr,
			@RequestParam int pageIndex, @RequestParam int pageSize)throws ThinventBaseException{
		return companyHandle.queryCompanyList(comName, comAddr, pageIndex, pageSize);
	}

	@GetMapping(value = "/listAllCompany")
	public List listAllCompany() throws ThinventBaseException {
		return this.companyHandle.queryAllCompany();
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
		return companyHandle.queryAllCompany(enable);
	}
}
