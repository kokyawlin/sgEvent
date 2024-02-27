package com.nus.sgevent.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class EventReview {
	@Id
	private String ReviewId;
	private String EventId;
	private String UserId;
	private String Rating;
	private String Comment;
	public String getReviewId() {
		return ReviewId;
	}
	public void setReviewId(String reviewId) {
		ReviewId = reviewId;
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
	public String getRating() {
		return Rating;
	}
	public void setRating(String rating) {
		Rating = rating;
	}
	public String getComment() {
		return Comment;
	}
	public void setComment(String comment) {
		Comment = comment;
	}
	
	
}
