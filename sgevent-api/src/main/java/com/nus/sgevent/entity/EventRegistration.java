package com.nus.sgevent.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class EventRegistration {
	@Id
	private String RegisterId;
	private String UserId;
	private Date RegisterDt;
	private Date EventId;
	private Date RegisterStatus;
	public String getRegisterId() {
		return RegisterId;
	}
	public void setRegisterId(String registerId) {
		RegisterId = registerId;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public Date getRegisterDt() {
		return RegisterDt;
	}
	public void setRegisterDt(Date registerDt) {
		RegisterDt = registerDt;
	}
	public Date getEventId() {
		return EventId;
	}
	public void setEventId(Date eventId) {
		EventId = eventId;
	}
	public Date getRegisterStatus() {
		return RegisterStatus;
	}
	public void setRegisterStatus(Date registerStatus) {
		RegisterStatus = registerStatus;
	}
	
	
	
	
}
