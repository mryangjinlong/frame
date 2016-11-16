package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Company;
import com.persist.CompanyDao;
import com.service.CompanyService;
@Service("companyService")
public class CompanyServiceImpl implements CompanyService{
	private CompanyDao companyDao;
	
	@Override
	public Boolean add(Company company) {
		System.out.println("companyServiceImpl.add    start");
		companyDao.add();
		System.out.println("companyServiceImpl.add    end");
		return true;
	}
	
	public CompanyDao getCompanyDao() {
		return companyDao;
	}
	
	@Autowired
	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	
}
