package com.nus.sgevent.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nus.sgevent.entity.Event;
import com.nus.sgevent.entity.EventUser;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface EventRepository extends CrudRepository<Event, Integer> {

	@Query(value = "Update event_user.* from event_user where user_id=?1 and password=?2 and email_address=?3 and user_role=?4 ",nativeQuery = true)
	boolean UpdateUser(String UserId,String Password, String UserName, String EmailAddress, int UserRole);
	
	@Query(value = "select event.* from event where event_id=?1",nativeQuery = true)
	EventUser SearchEventById(String EvnetId);
	
}
