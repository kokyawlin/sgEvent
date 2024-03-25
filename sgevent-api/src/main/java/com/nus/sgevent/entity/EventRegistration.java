package com.nus.sgevent.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class EventRegistration {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String RegisterId;
	private String UserId;
	private Date RegisterDt;
	private int EventId;
	private String RegisterStatus;
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
	public int getEventId() {
		return EventId;
	}
	public void setEventId(int eventId) {
		EventId = eventId;
	}
	public String getRegisterStatus() {
		return RegisterStatus;
	}
	public void setRegisterStatus(String registerStatus) {
		RegisterStatus = registerStatus;
	}
	
	
	
	
}
