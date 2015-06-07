package model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7804928219497875499L;
	private Client client;
	private ListModel<OrderEntry> data = new ListModel<>();
	private Date createdOn = new Date();
	private Date returndate = null;
	
	public ListModel<OrderEntry> getData() {
		return data;
	}
	public void setData(ListModel<OrderEntry> data) {
		this.data = data;
	}
	public Order(Client client, ListModel<OrderEntry> data, Date createdOn) {
		super();
		this.client = client;
		this.data = data;
		this.setCreatedOn(createdOn);
	}

	public int getAge(){
		Date d = (returndate == null ? new Date() : this.returndate);
        return (int)( (d.getTime() - this.createdOn.getTime()) / (1000 * 60 * 60 * 24));
	}
	
	
	public Order() {
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getReturnDate() {
		return returndate;
	}
	public BigDecimal TotalValue() {
		BigDecimal r = new BigDecimal("0");
		for(OrderEntry el: this.data){
			BigDecimal s = el.getVideo().getPerDay();
			r = r.add(s);
		}
		r = r.multiply(new BigDecimal(this.getAge()));
		return r;
		
	}
	public void setReturndate(Date returndate) {
		this.returndate = returndate;
	}
	@Override
	public String toString() {
		return "Order [client=" + client + ", TotalValue()=" + TotalValue()
				+ "]";
	}
	
	public boolean hasVideo(Video video){
		for(OrderEntry el: this.data){
			if(el.getVideo().equals(video)){
				return true;
			}
		}
		return false;
	}

}
