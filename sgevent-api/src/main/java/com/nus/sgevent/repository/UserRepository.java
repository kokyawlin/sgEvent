package com.nus.sgevent.repository;

import com.nus.sgevent.entity.EventUser;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Transactional
public interface UserRepository extends CrudRepository<EventUser, UUID> {
  @Query(
    value = "SELECT user_id, user_name, role_id, create_time, active_status, email_address, password FROM event_user WHERE email_address = ?1 AND password = ?2",
    nativeQuery = true
  )
  Optional<EventUser> checkUserLogin(String emailAddress, String password);

  @Modifying
  @Query(
    value = "update event_user set user_name=?1, email_address=?2, role_id=?3 where user_id=?4",
    nativeQuery = true
  )
  int UpdateUser(
    String UserName,
    String EmailAddress,
    int UserRole,
    UUID UserId
  );

  @Query(
    value = "Update event_user set password=?2 where user_name=?1",
    nativeQuery = true
  )
  boolean UpdatePassword(String UserName, String Password);

  @Query(
    value = "select event_user.* from event_user where email_address=?1",
    nativeQuery = true
  )
  EventUser SearchEventUser(String EmailAddress);

  @Query(
    value = "select event_user.* from event_user where user_name=?1",
    nativeQuery = true
  )
  EventUser SearchEventUserName(String UserName);
}
