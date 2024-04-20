package com.nus.sgevent.repository;

import com.nus.sgevent.entity.Event;
import java.util.Date;
import java.util.UUID;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Transactional
public interface EventRepository extends CrudRepository<Event, UUID> {
  @Modifying
  @Query(
    value = "Update event set event_title=?2, event_desc=?3, event_cover=?4, event_place=?5, event_start_dt=?6, event_end_dt=?7, event_capacity=?8 where event_id=?1",
    nativeQuery = true
  )
  int UpdateEvent(
    UUID eventId,
    String eventTitle,
    String eventDesc,
    String eventCover,
    String eventPlace,
    Date eventStartDt,
    Date eventEndDt,
    int eventCapacity
  );

  @Query(
    value = "select event.* from event where event_title like %?1%",
    nativeQuery = true
  )
  Event SearchEventByTitle(String event_title);

  @Query(
    value = "select event.* from event where event_id=?1",
    nativeQuery = true
  )
  Event QueryEventById(String event_id);
}
