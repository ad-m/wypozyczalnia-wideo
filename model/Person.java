package model;

import java.io.Serializable;

public class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6416675483722440724L;
	private String firstName;
	private String secondName;
	public Person(String firstName, String secondName) {
		super();
		this.firstName = firstName;
		this.secondName = secondName;
	}
	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", secondName=" + secondName
				+ "]";
	}
	
}
