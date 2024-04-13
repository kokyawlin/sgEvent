package com.nus.sgevent.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "event_registration")
@Data
@NoArgsConstructor
@IdClass(RegistrationKey.class)
public class EventRegistration {

  @Id
  private UUID userId; // 假设UserId也是UUID类型

  @Id
  private UUID eventId; // 假设EventId也是UUID类型

  private Date registerDt;

  private String registerStatus;
  // Lombok @Data注解自动为所有字段生成getter和setter方法
}
