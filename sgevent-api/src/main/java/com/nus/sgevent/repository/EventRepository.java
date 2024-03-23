package com.nus.sgevent.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.nus.sgevent.entity.Event;
import com.nus.sgevent.entity.EventUser;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface EventRepository extends CrudRepository<Event, Integer> {

	@Query(value = "Update event set event_status=?3 where event_title=?1 and owner_id=?2",nativeQuery = true)
	boolean UpdateEvent(String EventTitle,String OwnerId, String EventStatus);
	

	@Query(value = "select event.* from event where event_title=?1",nativeQuery = true)
	EventUser SearchEventById(String event_title);
	
}
