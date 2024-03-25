package com.nus.sgevent.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nus.sgevent.entity.Event;
import com.nus.sgevent.entity.EventRegistration;
import com.nus.sgevent.entity.EventUser;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface EventRegisterRepository extends CrudRepository<EventRegistration, Integer> {

	@Query(value = "select event_registration.* from event_registration where event_id=?1",nativeQuery = true)
	EventUser SearchEventRegister(String event_id);
	
}
