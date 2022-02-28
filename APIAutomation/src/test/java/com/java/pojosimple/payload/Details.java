package com.java.pojosimple.payload;

public class Details {

	private String companyname ;
	private String emailId ;
	
	public Details(String companyname, String emailId) {
		this.companyname = companyname;
		this.emailId = emailId;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	
	
}
