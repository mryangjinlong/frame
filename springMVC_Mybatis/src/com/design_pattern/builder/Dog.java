package com.design_pattern.builder;

public class Dog {
		
	private int age;
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMasterName() {
		return masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	private int sex;
	private String name;
	private String masterName;
	
	
	public Dog(Builder builder){
		this.age = builder.getAge();
		this.sex = builder.getSex();
		this.name = builder.getName();
		this.masterName = builder.getMasterName();
	}
	public static class Builder{
		private int age;
		private int sex;
		private String name;
		private String masterName;
		
		
		public Dog build(){
			return new Dog(this);
		}
		
		
		public int getAge() {
			return age;
		}
		public Builder setAge(int age) {
			this.age = age;
			return this;
		}
		public int getSex() {
			return sex;
		}
		public Builder setSex(int sex) {
			this.sex = sex;
			return this;
		}
		public String getName() {
			return name;
		}
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		public String getMasterName() {
			return masterName;
		}
		public Builder setMasterName(String masterName) {
			this.masterName = masterName;
			return this;
		}
	}
	
	public static void main(String[] args) {
		Dog dog = new Dog.Builder().setAge(1).setMasterName("long")
								   .setName("little").setSex(1).build();
		System.out.println(dog.getAge());
		System.out.println(dog.getMasterName());
		System.out.println(dog.getName());
		System.out.println(dog.getSex());
	}
}
