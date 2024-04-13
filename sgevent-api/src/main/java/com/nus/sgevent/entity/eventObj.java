package com.nus.sgevent.entity;

import java.util.List;
import lombok.Data;

@Data
public class eventObj extends Event {

  public eventObj(Event event) {
    this.setEventId(event.getEventId());
    this.setEventTitle(event.getEventTitle());
    this.setEventDesc(event.getEventDesc());
    this.setEventStartDt(event.getEventStartDt());
    this.setEventEndDt(event.getEventEndDt());
    this.setEventPlace(event.getEventPlace());
    this.setEventCapacity(event.getEventCapacity());
    this.setEventOwnerId(event.getEventOwnerId());
    this.setEventStatus(event.getEventStatus());
    this.setEventCover(event.getEventCover());
  }

  private List<EventRegistration> registrationList;
}
