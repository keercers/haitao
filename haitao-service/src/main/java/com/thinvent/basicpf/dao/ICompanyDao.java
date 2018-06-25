package com.thinvent.basicpf.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.thinvent.basicpf.model.Company;

import java.util.List;

public interface ICompanyDao extends JpaRepository<Company, Integer>{

	public Page<Company> queryByComNameLikeAndComAddrLikeAndEnableOrderById(String comName, String comAddr, int enable, Pageable pageRequest);
	public Company findOneByComIdAndEnable(String comId, int enable);
	public Company findOneByComParentIdAndEnable(String comParentId, int enable);
	public Company findOneByComNameAndEnable(String comName, int enable);
	public void deleteByComId(String comId);
	public List<Company> findAllByEnable(int enable);
}
