package model;
import java.io.Serializable;
import java.util.Date;


public class Client extends Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 118140142952654654L;
	private Date createdOn;

	public Client(String firstName, String secondName) {
		super(firstName, secondName);
		this.createdOn = new Date();
	}

	public Date getCreatedOn() {
		return (Date) createdOn.clone();
	}
	
}
