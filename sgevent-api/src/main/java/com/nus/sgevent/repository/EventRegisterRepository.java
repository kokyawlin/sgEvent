package com.nus.sgevent.repository;
import java.util.UUID;
import com.nus.sgevent.entity.EventRegistration;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Transactional
public interface EventRegisterRepository
  extends CrudRepository<EventRegistration, UUID> {
  @Query(
    value = "select event_registration.* from event_registration where event_id=?1",
    nativeQuery = true
  )
  Iterable<EventRegistration> SearchEventRegister(UUID event_id);

  @Transactional
  @Modifying
  @Query(
    value = "delete event_registration.* from event_registration where event_id=?1 and user_id=?2",
    nativeQuery = true
  )
  void deleteByIds(UUID event_id, UUID user_id);
}
