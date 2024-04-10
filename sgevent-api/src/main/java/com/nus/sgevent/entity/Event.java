package com.nus.sgevent.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "event")
@Data
@NoArgsConstructor
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "event_id")
  private UUID eventId; // 使用UUID作为主键类型

  @NotBlank
  @Column(name = "event_title")
  private String eventTitle;

  @NotBlank
  @Column(name = "event_desc")
  private String eventDesc;

  @Column(name = "event_create_dt")
  private Date eventCreateDt;

  @Column(name = "event_start_dt")
  private Date eventStartDt;

  @Column(name = "event_end_dt")
  private Date eventEndDt;

  @NotBlank
  @Column(name = "event_place")
  private String eventPlace;

  @Column(name = "event_capacity")
  private int eventCapacity;

  @Column(name = "event_owner_id")
  private String eventOwnerId;

  @NotBlank
  @Column(name = "event_status")
  private String eventStatus;

  @Column(name = "event_cover", length = 16777215)
  private String eventCover;
}
