package com.nus.sgevent.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notification")
@Data
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue
    private UUID notificationId; // 使用UUID作为主键类型
    private String notiMessage;
    private UUID eventId; // 假设EventId也是UUID类型
    private UUID userId;  // 假设UserId也是UUID类型
    private Date notificationDt;

    // Lombok @Data注解自动为所有字段生成getter和setter方法
}
