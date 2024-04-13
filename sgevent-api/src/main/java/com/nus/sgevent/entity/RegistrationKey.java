package com.nus.sgevent.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class RegistrationKey implements Serializable {

  protected UUID eventId;
  protected UUID userId;

  public RegistrationKey() {}

  public RegistrationKey(UUID eventId, UUID userId) {
    this.eventId = eventId;
    this.userId = userId;
  }
}
