package com.model;

public class Company implements Entity ,AbstractEntity{
	private Long id;
	private String loginName;
	private Employee employee;
	
	public void saySth(){
		System.out.println("这是saySth方法");
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	@Override
	public void print() {
		System.out.println("this is company");
	}

	public Company() {
	}

	public Company(Long id, String loginName, Employee employee) {
		this.id = id;
		this.loginName = loginName;
		this.employee = employee;
	}
}
