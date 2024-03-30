package com.nus.sgevent.entity;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "event_registration")
@Data
@NoArgsConstructor
public class EventRegistration {
    @Id
    private UUID registerId = UUID.randomUUID(); // 直接在字段声明时生成UUID值

    private UUID userId; // 假设UserId也是UUID类型

    private Date registerDt;

    private UUID eventId; // 假设EventId也是UUID类型

    private String registerStatus;

    // Lombok @Data注解自动为所有字段生成getter和setter方法
}
