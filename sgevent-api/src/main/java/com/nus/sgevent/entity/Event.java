package com.nus.sgevent.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Event {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String EventId;
	private String EventTitle;
	private String EventDesc;
	private Date EventCreateDt;
	private Date EventStartDt;
	private Date EventEndDt;
	private String EventPlace;
	private String EventCapicity;
	private String EventOwnerId;
	private String EventStatus;
	public String getEventTitle() {
		return EventTitle;
	}
	public void setEventTitle(String eventTitle) {
		EventTitle = eventTitle;
	}
	public String getEventDesc() {
		return EventDesc;
	}
	public void setEventDesc(String eventDesc) {
		EventDesc = eventDesc;
	}
	public Date getEventCreateDt() {
		return EventCreateDt;
	}
	public void setEventCreateDt(Date eventCreateDt) {
		EventCreateDt = eventCreateDt;
	}
	public Date getEventStartDt() {
		return EventStartDt;
	}
	public void setEventStartDt(Date eventStartDt) {
		EventStartDt = eventStartDt;
	}
	public Date getEventEndDt() {
		return EventEndDt;
	}
	public void setEventEndDt(Date eventEndDt) {
		EventEndDt = eventEndDt;
	}
	public String getEventPlace() {
		return EventPlace;
	}
	public void setEventPlace(String eventPlace) {
		EventPlace = eventPlace;
	}
	public String getEventCapicity() {
		return EventCapicity;
	}
	public void setEventCapicity(String eventCapicity) {
		EventCapicity = eventCapicity;
	}
	public String getEventOwnerId() {
		return EventOwnerId;
	}
	public void setEventOwnerId(String eventOwnerId) {
		EventOwnerId = eventOwnerId;
	}
	public String getEventStatus() {
		return EventStatus;
	}
	public void setEventStatus(String eventStatus) {
		EventStatus = eventStatus;
	}
	
	
}
