package model;

import java.io.Serializable;

public class CreditEntry implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5631926912184156113L;
	private Person person;
	private String role;
	public CreditEntry(String firstName, String secondName, String role) {
		this.person = new Person(firstName, secondName);
		this.role = role;
	}

	public CreditEntry(Person person, String role) {
		this.person = person;
		this.role = role;
	}

	@Override
	public String toString() {
		return "CreditEntry [person=" + person + ", role=" + role + "]";
	}

		
}
