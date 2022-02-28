package com.java.pojosimple.payload;

public class MasterPojo {

	private String name ;
	private int age ;
	private String skills[] ;
	private Details details ;
	
	public MasterPojo(String name, int age, String[] skills, String companyname ,String emailId ) {
	
		this.name = name;
		this.age = age;
		this.skills = skills;
		this.details = new Details(companyname, emailId);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String[] getSkills() {
		return skills;
	}

	public void setSkills(String[] skills) {
		this.skills = skills;
	}

	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}

}
