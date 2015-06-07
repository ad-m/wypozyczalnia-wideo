package model;

import java.io.Serializable;

public class OrderEntry implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6859337266210509212L;
	private Video video;

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public OrderEntry(Video video) {
		super();
		this.video = video;
	}

	public OrderEntry() {
	}

	
	@Override
	public String toString() {
		return "Entry [video=" + video + "]";
	}
	
}
