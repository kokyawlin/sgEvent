package com.nus.sgevent.repository;

import com.nus.sgevent.entity.Event;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Transactional
public interface EventReviewRepository extends CrudRepository<Event, UUID> {}
