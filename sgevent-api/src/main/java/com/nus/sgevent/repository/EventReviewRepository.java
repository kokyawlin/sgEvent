package com.nus.sgevent.repository;

import com.nus.sgevent.entity.Event;
import com.nus.sgevent.entity.EventReview;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Transactional
public interface EventReviewRepository extends CrudRepository<Event, UUID> {
    static List<EventReview> findByEventId(UUID eventId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByEventId'");
    }
}
