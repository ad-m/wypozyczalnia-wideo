package model;

import java.io.Serializable;


public class Video implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6327190683242673134L;
	private String title = "";
	private ListModel<CreditEntry> credits = new ListModel<CreditEntry>();
	private int diskTotal = 10;
	private int diskFree = 5;
	public Video(String title, int diskTotal,
			int diskFree) {
		this.title = title;
		this.diskTotal = diskTotal;
		this.diskFree = diskFree;
	}
	public Video(String title, ListModel<CreditEntry> credits, int diskTotal,
			int diskFree) {
		super();
		this.title = title;
		this.credits = credits;
		this.diskTotal = diskTotal;
		this.diskFree = diskFree;
	}
	public Video() {
		// TODO Auto-generated constructor stub
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ListModel<CreditEntry> getCredits() {
		return credits;
	}
	public int getDiskTotal() {
		return diskTotal;
	}
	public void setDiskTotal(int diskTotal) {
		this.diskTotal = diskTotal;
	}
	public int getDiskFree() {
		return diskFree;
	}
	public void setDiskFree(int diskFree) {
		this.diskFree = diskFree;
	}
	public String getTitle() {
		return title;
	}
	public static Video factory(){
		return new Video("John", 10, 5);
	}
	@Override
	public String toString() {
		return "Video [title=" + title + ", disk=" + diskTotal
				+ " / " + diskFree + "]";
	}
	
}
