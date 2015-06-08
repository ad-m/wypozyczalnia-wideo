package model;

import java.io.Serializable;
import java.math.BigDecimal;

import core.ListModel;

public class Video implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6327190683242673134L;
	private String title = "Alojz";
	private ListModel<CreditEntry> credits = new ListModel<CreditEntry>();
	private int diskTotal = 10;
	private int diskFree = 5;
	private BigDecimal perDay = new BigDecimal("5");

	public Video(String title, int diskTotal, int diskFree, BigDecimal perDay) {
		this.title = title;
		this.diskTotal = diskTotal;
		this.diskFree = diskFree;
		this.perDay = perDay;
	}

	public Video(String title, ListModel<CreditEntry> credits, int diskTotal,
			int diskFree, BigDecimal perDay) {
		super();
		this.title = title;
		this.credits = credits;
		this.diskTotal = diskTotal;
		this.diskFree = diskFree;
		this.perDay = perDay;
	}

	public Video() {
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



	public BigDecimal getPerDay() {
		return perDay;
	}

	public BigDecimal getPrice(int day) {
		return this.perDay.multiply(new BigDecimal(day));
	}

	public void setPerDay(BigDecimal perDay) {
		this.perDay = perDay;
	}

	@Override
	public String toString() {
		return "Video [title=" + title + ", diskTotal=" + diskTotal
				+ ", diskFree=" + diskFree + ", perDay=" + perDay + "]";
	}

}
