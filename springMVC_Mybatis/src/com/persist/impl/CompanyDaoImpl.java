package com.persist.impl;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.Company;
import com.persist.CompanyDao;
@Repository("companyDao")
public class CompanyDaoImpl implements CompanyDao{

	private SqlSessionTemplate sqlSessionTemplate;
	
	public void add() {
		System.out.println("companyDaoImpl.add  start");
		//mybatis sql语句映射有两种形式  1 xml　2 java映射类        较复杂时用xml比较好
		Company company1 = (Company)sqlSessionTemplate.selectOne("com.model.Company.selectOne",1009843);
//		Company company2 = selectDetail();
		System.out.println("company1.getLoginName() ======" + company1.getLoginName());
//		System.out.println(company2.getLoginName());
		System.out.println("companyDaoImpl.add  end");
	}

	@Override
	public Company selectDetail() {
		Company company1 = (Company)sqlSessionTemplate.selectOne("com.model.Company.selectDetail",1);
		return company1;
	}
	
	public SqlSessionTemplate getSqlSessionTemplate(){
		return sqlSessionTemplate;
	}
	@Autowired
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
		this.sqlSessionTemplate = sqlSessionTemplate;
	}



	



//	@Override
//	public Company selectCompany(int id){
//		CompanyDao dao = (CompanyDao) sqlSessionTemplate.getMapper(this.getClass());
//		return dao.selectCompany(id);
//		return null;
//	}


	
	
	
}
