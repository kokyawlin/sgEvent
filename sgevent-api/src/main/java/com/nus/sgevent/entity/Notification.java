package com.nus.sgevent.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Notification {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String NotificationId;
	private String NotiMessage;
	private String EventId;
	private String UserId;
	private Date NotificationDt;
	public String getNotificationId() {
		return NotificationId;
	}
	public void setNotificationId(String notificationId) {
		NotificationId = notificationId;
	}
	public String getNotiMessage() {
		return NotiMessage;
	}
	public void setNotiMessage(String notiMessage) {
		NotiMessage = notiMessage;
	}
	public String getEventId() {
		return EventId;
	}
	public void setEventId(String eventId) {
		EventId = eventId;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public Date getNotificationDt() {
		return NotificationDt;
	}
	public void setNotificationDt(Date notificationDt) {
		NotificationDt = notificationDt;
	}
	
	
	
	
}
