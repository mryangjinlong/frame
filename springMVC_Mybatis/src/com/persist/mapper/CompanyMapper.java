package com.persist.mapper;

import org.apache.ibatis.annotations.Select;

import com.model.Company;

public interface CompanyMapper {
	@Select("select * from c_Company where id = #{id}")
	  Company selectCompany(int id);
}
