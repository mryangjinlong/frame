package com.persist;

import com.model.Company;


public interface CompanyDao{
	
//	@Select("select * from c_Company where id = #{id}")
//	Company selectCompany(int id);
	public void add() ;
	
	public Company selectDetail();
}
