package com.nus.sgevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nus.sgevent.model.Event;


public interface EventRepository extends JpaRepository<Event, Long> {
}
