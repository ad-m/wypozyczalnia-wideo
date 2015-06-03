package model;

import java.io.Serializable;

public class CreditEntry implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5631926912184156113L;
	private String firstName;
	private String secondName;
	private String role;
	public CreditEntry(String firstName, String secondName, String role) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.role = role;
	}
	@Override
	public String toString() {
		return "CreditEntry [" + firstName + " " + secondName + " (" + role + ")]";
	}
	
		
}
