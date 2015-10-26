package org.jpbltl;

import java.util.Date;

/**
 * This class represents a pin on the timeline.
 * 
 * @author Thiago Vinicius Freire de Araujo Ribeiro <thiagovfar@gmail.com>
 */
public class Pin {
	
	public final String id;
	public final long time;
	public final int duration;
	
	private Pin(String id, long time, int duration) {
		if (id == null) {
			throw new IllegalArgumentException("\"id\" may not be null");
		}
		
		if (id.length() > 64) {
			throw new IllegalArgumentException("\"id\".length() must be <= 64");
		}
		
		this.id = id;
		this.time = time;
		this.duration = duration;
	}
	
	public Date getTimeAsDate() {
		return new Date(time);
	}
	
	public static class Builder {
		private String pinId;
		private long pinTime;
		private int pinDuration;
		
		public Builder withId(String id) {
			this.pinId = id;
			return this;
		}
		
		/**
		 * Assumed to be in UTC.
		 * @return
		 */
		public Builder withTime(Date time) {
			this.pinTime = time.getTime();
			return this;
		}
		
		public Builder withDurationInMinutes(int duration) {
			this.pinDuration = duration;
			return this;
		}
		
		public Pin build() {
			return new Pin(pinId, pinTime, pinDuration);
		}
		
	}
	
}
