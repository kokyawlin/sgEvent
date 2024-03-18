package com.nus.sgevent.repository;

import org.springframework.data.repository.CrudRepository;

import com.nus.sgevent.entity.Event;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface NotificationRepository extends CrudRepository<Event, Integer> {

	
	
}
