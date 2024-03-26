package com.nus.sgevent.repository;

import com.nus.sgevent.entity.Notification;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface NotificationRepository
  extends CrudRepository<Notification, Integer> {
  @Query(
    value = "select notification.* from notification where notification_id=?1 and event_id=?2 ",
    nativeQuery = true
  )
  List<Notification> RetrieveNotification(
    String NotificationId,
    String EventId
  );
}
