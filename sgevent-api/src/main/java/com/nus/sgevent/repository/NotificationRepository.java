package com.nus.sgevent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nus.sgevent.entity.Event;
import com.nus.sgevent.entity.EventUser;
import com.nus.sgevent.entity.Notification;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface NotificationRepository extends CrudRepository<Notification, Integer> {
	
	@Query(value = "select notification.* from notification where notification_id=?1 and event_id=?2 ",nativeQuery = true)
	List<Notification> RetrieveNotification(String NotificationId,String EventId);
	
	

	
	
}
