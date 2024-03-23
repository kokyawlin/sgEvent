package com.nus.sgevent.repository;

import com.nus.sgevent.entity.Event;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface EventReviewRepository extends CrudRepository<Event, Integer> {}
