package model;
import java.io.Serializable;
import java.util.Date;


public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7804928219497875499L;
	private Client client;
	private ListModel<OrderEntry> data = new ListModel<>();
	private Date deadline;
	private boolean done = false;
	
	public Order(Client client, ListModel<OrderEntry> data, Date deadline, boolean done) {
		super();
		this.client = client;
		this.data = data;
		this.deadline = deadline;
		this.done = done;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	@Override
	public String toString() {
		return "Order [client=" + client + ", data=" + data + ", deadline="
				+ deadline + ", done=" + done + "]";
	}
	
}
